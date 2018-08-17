package com.cypher.bookstore.servlet;

import com.cypher.bookstore.domain.User;
import com.cypher.bookstore.service.BookStoreService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Cypher-Z
 * @date 2018/8/16 - 10:06
 */
public class UserServlet extends HttpServlet {
	private BookStoreService service =new BookStoreService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName =req.getParameter("userName");
		User user = service.getUserWithTrades(userName);
		if (user==null){
			resp.sendRedirect(req.getContextPath()+"/500.jsp");
			return;
		}
		req.setAttribute("user",user);
		req.getRequestDispatcher(req.getContextPath()+"/WEB-INF/trade/trades.jsp").forward(req,resp);
	}
}
