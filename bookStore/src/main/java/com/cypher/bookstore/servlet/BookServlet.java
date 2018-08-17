package com.cypher.bookstore.servlet;

import com.cypher.bookstore.domain.*;
import com.cypher.bookstore.service.BookStoreService;
import com.cypher.bookstore.web.BookStoreWebUtils;
import com.cypher.bookstore.web.CriteriaBook;
import com.cypher.bookstore.web.Page;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author Cypher-Z
 * @date 2018/8/13 - 18:04
 */
public class BookServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	private BookStoreService bookStoreService = new BookStoreService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		String methodName = req.getParameter("method");
		try {
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			method.invoke(this, req, resp);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	//	得到查询条件所对应的书籍集合
	protected void getBooks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageNoStr = req.getParameter("pageNo");
		String minPriceStr = req.getParameter("minPrice");
		String maxPriceStr = req.getParameter("maxPrice");
		int pageNo = 1;
		int minPrice = 0;
		int maxPrice = Integer.MAX_VALUE;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException ignored) {
		}
		try {
			minPrice = Integer.parseInt(minPriceStr);
		} catch (NumberFormatException ignored) {
		}
		try {
			maxPrice = Integer.parseInt(maxPriceStr);
		} catch (NumberFormatException ignored) {
		}
		CriteriaBook criteriaBook = new CriteriaBook(minPrice, maxPrice, pageNo);
		Page<Book> page = bookStoreService.getPage(criteriaBook);
		req.setAttribute("bookPage", page);
		req.getRequestDispatcher(req.getContextPath() + "/WEB-INF/pages/books.jsp").forward(req, resp);
	}

	//	查看书籍详情
	protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Book book = validateBook(req, resp);
		if (book == null) return;
		req.setAttribute("book", book);
		req.getRequestDispatcher(req.getContextPath() + "/WEB-INF/pages/book.jsp").forward(req, resp);
	}

	//	添加书籍到购物车(遗憾 无法妥善解决刷新重新提交导致书籍数量继续增加 暂待springMvc拦截器解决)
	protected void addToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		if (isRepeatSubmit(req)) {
//			System.out.println("请不要重复提交");
//			return;
//		}
//		req.getSession().removeAttribute("token");//移除session中的token
//		System.out.println("处理用户提交请求！！");


		Book book = validateBook(req, resp);
		if (book == null) return;
		ShoppingCart shoppingCart = BookStoreWebUtils.getShoppingCart(req);
		bookStoreService.addToCart(shoppingCart, book);

		getBooks(req, resp);

	}

//	private boolean isRepeatSubmit(HttpServletRequest req) {
//		String client_token = req.getParameter("token");
//		//1、如果用户提交的表单数据中没有token，则用户是重复提交了表单
//		if (client_token == null) {
//			return true;
//		}
//		//取出存储在Session中的token
//		String server_token = (String) req.getSession().getAttribute("token");
//		//2、如果当前用户的Session中不存在Token(令牌)，则用户是重复提交了表单
//		if (server_token == null) {
//			return true;
//		}
//		//3、存储在Session中的Token(令牌)与表单提交的Token(令牌)不同，则用户是重复提交了表单
//		if (!client_token.equals(server_token)) {
//			return true;
//		}
//		return false;
//	}


	//	删除购物项
	protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idStr = req.getParameter("id");
		int id = Integer.parseInt(idStr);
		ShoppingCart shoppingCart = BookStoreWebUtils.getShoppingCart(req);
		shoppingCart.removeItem(id);
		if (shoppingCart.isEmpty()) {
			req.getRequestDispatcher(req.getContextPath() + "/WEB-INF/pages/emptyCart.jsp").forward(req, resp);
		} else
			req.getRequestDispatcher(req.getContextPath() + "/WEB-INF/pages/shoppingCart.jsp").forward(req, resp);
	}

	//清空购物车
	protected void clearShoppingCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ShoppingCart shoppingCart = BookStoreWebUtils.getShoppingCart(req);
		shoppingCart.clear();
		req.getRequestDispatcher(req.getContextPath() + "/WEB-INF/pages/emptyCart.jsp").forward(req, resp);
	}

	//	更新购物项书籍数量
	protected void updateItemQuantity(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String idStr = req.getParameter("id");
		String quantityStr = req.getParameter("quantity");
		int id = -1;
		int quantity = -1;
		ShoppingCart shoppingCart = BookStoreWebUtils.getShoppingCart(req);
		try {
			id = Integer.parseInt(idStr);
			quantity = Integer.parseInt(quantityStr);
		} catch (NumberFormatException ignored) {
		}
		if (id > 0 && quantity >= 0) {
			shoppingCart.updateItemQuantity(id, quantity);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("totalMoney", shoppingCart.getTotalMoney());
		map.put("bookNumber", shoppingCart.getBookNumber());
		Gson gson = new Gson();
		String json = gson.toJson(map);
		resp.setContentType("text/javascript");
		resp.getWriter().print(json);
	}

	//	为使WEB-INF下jsp页面可访问的跳转方法
	protected void forwardPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String page = req.getParameter("page");
		req.getRequestDispatcher(req.getContextPath() + "/WEB-INF/pages/" + page).forward(req, resp);
	}

	//	结账 errorType 1.用户不存在 2.用户名账号不匹配 3.书籍库存不足 4.用户余额不足
	protected void cash(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("userName");
		int accountId = -1;
		String accountIdStr = req.getParameter("accountId");
		try {
			accountId = Integer.parseInt(accountIdStr);
		} catch (NumberFormatException ignored) {
		}

		ShoppingCart shoppingCart = BookStoreWebUtils.getShoppingCart(req);
		User user = checkUserExist(userName);
		if (user == null) {
			dispatch(req, resp, 1);
		} else if (!checkUser(user, accountId)) {
			dispatch(req, resp, 2);
		} else if (!checkItemCount(req, shoppingCart)) {
			dispatch(req, resp, 3);
		} else if (!checkBalance(accountId, shoppingCart)) {
			dispatch(req, resp, 4);
		} else {
			bookStoreService.batchUpdateStoreNumberAndSalesNumber(shoppingCart.getItems());
			bookStoreService.updateBalance(accountId, shoppingCart.getTotalMoney());
			Trade trade = new Trade(user.getUserId(), new Date());
			int tradeId = bookStoreService.insert(trade);
			bookStoreService.batchSave(getTradeItemsByShoppingCart(shoppingCart, tradeId));
			shoppingCart.clear();
			resp.sendRedirect(req.getContextPath() + "/success.jsp");
		}
	}

	private Collection<TradeItem> getTradeItemsByShoppingCart(ShoppingCart shoppingCart, int tradeId) {
		ArrayList<TradeItem> tradeItems = new ArrayList<>();
		for (ShoppingCartItem shoppingCartItem : shoppingCart.getItems()) {
			tradeItems.add(new TradeItem(shoppingCartItem.getBook().getId(), shoppingCartItem.getQuantity(), tradeId));
		}
		return tradeItems;
	}

	//	结账验证错误所用转发
	private void dispatch(HttpServletRequest req, HttpServletResponse resp, int errorType) throws ServletException, IOException {
		req.setAttribute("errorType", errorType);
		req.getRequestDispatcher(req.getContextPath() + "/WEB-INF/pages/checkOut.jsp").forward(req, resp);
	}

	//余额验证
	private boolean checkBalance(int accountId, ShoppingCart shoppingCart) {
		Account account = bookStoreService.getAccount(accountId);
		return account.getBalance() >= shoppingCart.getTotalMoney();
	}

	//库存验证 并将库存不足的书籍集合setAttribute
	private boolean checkItemCount(HttpServletRequest req, ShoppingCart shoppingCart) {
		Map<Integer, ShoppingCartItem> map = shoppingCart.getBooks();
		Set<Book> books = new HashSet<>();
		boolean flag = true;
		for (Map.Entry<Integer, ShoppingCartItem> itemEntry : map.entrySet()) {
			int storeNumber = bookStoreService.getStoreNumber(itemEntry.getKey());
			int quantity = itemEntry.getValue().getQuantity();
			if (storeNumber < quantity) {
				flag = false;
				books.add(itemEntry.getValue().getBook());
			}
		}
		if (!books.isEmpty()) {
			req.setAttribute("quantityErrorBooks", books);
		}
		return flag;
	}

	//用户名与账号匹配验证
	private boolean checkUser(User user, int accountId) {
		if (user == null) {
			return false;
		}
		return accountId == user.getAccountId();
	}

	//	验证用户名所对应用户是否存在
	private User checkUserExist(String userName) {
		return bookStoreService.getUser(userName);
	}

	//	验证id所对应书籍是否存在
	private Book validateBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idStr = req.getParameter("id");
		int id = -1;
		Book book = null;
		try {
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException ignored) {
		}
		if (id > 0) {
			book = bookStoreService.getBook(id);
		}
		if (book == null) {
			req.getRequestDispatcher(req.getContextPath() + "/WEB-INF/error/bookNotFoundError.jsp").forward(req, resp);
			return null;
		}
		return book;
	}
}
