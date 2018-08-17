package com.cypher.bookstore.filter;

import com.cypher.bookstore.utils.JDBCUtils;
import com.cypher.bookstore.web.ConnectionContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Cypher-Z
 * @date 2018/8/16 - 9:06
 */
public class TransactionFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Connection connection = null;
		try {
			connection = JDBCUtils.getConnection();
			connection.setAutoCommit(false);
			ConnectionContext.getInstance().bind(connection);
			chain.doFilter(request, response);
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (connection != null) {
					connection.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.sendRedirect(req.getContextPath() + "/500.jsp");
		} finally {
			ConnectionContext.getInstance().remove();
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
