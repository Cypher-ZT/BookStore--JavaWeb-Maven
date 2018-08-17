package com.cypher.bookstore.filter;


import javax.servlet.*;
import java.io.IOException;

/**
 * @author Cypher-Z
 * @date 2018/8/14 - 18:45
 */
public class EncodingFilter implements Filter {
	private FilterConfig filterConfig = null;

	@Override
	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String encoding = filterConfig.getServletContext().getInitParameter("encoding");
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}
}
