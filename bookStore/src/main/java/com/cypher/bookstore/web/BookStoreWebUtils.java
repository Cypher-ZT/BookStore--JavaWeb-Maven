package com.cypher.bookstore.web;

import com.cypher.bookstore.domain.ShoppingCart;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Cypher-Z
 * @date 2018/8/14 - 4:01
 */
public class BookStoreWebUtils {
	public static ShoppingCart getShoppingCart(HttpServletRequest request){
		Object object = request.getSession().getAttribute("shoppingCart");
		ShoppingCart shoppingCart;
		if (object instanceof ShoppingCart){
			shoppingCart = (ShoppingCart) object;
		}else {
			shoppingCart = new ShoppingCart();
			request.getSession().setAttribute("shoppingCart",shoppingCart);
		}
		return shoppingCart;
	}
}
