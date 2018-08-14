<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: CypherZ
  Date: 2018/8/14
  Time: 4:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="shoppingCart" scope="session" type="com.cypher.bookstore.domain.ShoppingCart"/>
<html>
<head>
    <title>购物车</title>
</head>
<body>
<br>
<div style="text-align: center;">
    您的购物车中共有${shoppingCart.bookNumber}本书
<br>
<table align="center" style="border-collapse:separate; border-spacing:10px 15px; ">
    <tr>
        <th>书名</th>
        <th>数量</th>
        <th>单价</th>
        <th>小结</th>
    </tr>
    <c:forEach items="${shoppingCart.items}" var="item" >
        <tr>
            <td>${item.book.title}</td>
            <td><input type="text" size="2" value="${item.quantity}"></td>
            <td>${item.book.price}</td>
            <td>${item.itemMoney}</td>
            <td><a href="">删除</a></td>
        </tr>
    </c:forEach>
    <tr>
        <td>
            总金额为:${shoppingCart.totalMoney}元
        </td>
    </tr>

</table>
    <a href="${pageContext.request.contextPath}
            /bookServlet?method=getBooks&pageNo=${param.pageNo}&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}">继续购物</a>&nbsp;
    <a href="">清空购物车</a>&nbsp;
    <a href="">结账</a>
</div>
</body>
</html>
