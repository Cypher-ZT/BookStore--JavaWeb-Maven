package com.cypher.bookstore.servlet;

import com.cypher.bookstore.domain.Book;
import com.cypher.bookstore.domain.ShoppingCart;
import com.cypher.bookstore.service.BookService;
import com.cypher.bookstore.web.BookStoreWebUtils;
import com.cypher.bookstore.web.CriteriaBook;
import com.cypher.bookstore.web.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Cypher-Z
 * @date 2018/8/13 - 18:04
 */
public class BookServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	private BookService bookService = new BookService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String methodName = req.getParameter("method");
		try {
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			method.invoke(this, req, resp);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

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
		Page<Book> page = bookService.getPage(criteriaBook);
		req.setAttribute("bookPage", page);
		req.getRequestDispatcher(req.getContextPath() + "/WEB-INF/pages/books.jsp").forward(req, resp);
	}

	protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Book book = validateBook(req, resp);
		if (book == null) return;
		req.setAttribute("book", book);
		req.getRequestDispatcher(req.getContextPath() + "/WEB-INF/pages/book.jsp").forward(req, resp);
	}

	protected void addToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Book book = validateBook(req, resp);
		if (book == null) return;
		ShoppingCart shoppingCart = BookStoreWebUtils.getShoppingCart(req);
		bookService.addToCart(shoppingCart, book);
		getBooks(req,resp);
	}

	private Book validateBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idStr = req.getParameter("id");
		int id = -1;
		Book book = null;
		try {
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException ignored) {
		}
		if (id > 0) {
			book = bookService.getBook(id);
		}
		if (book == null) {
			req.getRequestDispatcher(req.getContextPath() + "/error/bookNotFoundError.jsp").forward(req, resp);
			return null;
		}
		return book;
	}
}
