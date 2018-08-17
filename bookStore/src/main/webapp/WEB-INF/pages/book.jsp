<%--
  Created by IntelliJ IDEA.
  User: CypherZ
  Date: 2018/8/13
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/common/common.jsp" %>
<jsp:useBean id="book" scope="request" type="com.cypher.bookstore.domain.Book"/>
<html>
<head>
    <title>${requestScope.book.title}</title>
</head>
<body>
<br><br>
<table align="center" style="border-collapse:separate; border-spacing:0 15px;">
    <tr>
        <td>书名:</td>
        <td>${book.title}</td>
    </tr>
    <tr>
        <td>作者:</td>
        <td>${book.author}</td>
    </tr>
    <tr>
        <td>单价:</td>
        <td>${book.price}</td>
    </tr>
    <tr>
        <td>出版时间:</td>
        <td>${book.publishingDate}</td>
    </tr>
    <tr>
        <td>评论:</td>
        <td>${book.remark}</td>
    </tr>
    <tr>
        <td><a href="bookServlet?method=getBooks&pageNo=${param.pageNo}">继续购物</a></td>
    </tr>
</table>
</body>
</html>
