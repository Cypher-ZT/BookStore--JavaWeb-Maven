<%--
  Created by IntelliJ IDEA.
  User: CypherZ
  Date: 2018/8/16
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" scope="request" type="com.cypher.bookstore.domain.User"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="text-align: center;">
    <br><br>
    User: &nbsp;&nbsp;${user.userName}
    <br><br>
    <table align="center" cellpadding="10" cellspacing="0">
        <tr>
            <td>
                <c:forEach items="${user.trades}" var="trade">
                    <table border="1" cellpadding="10" cellspacing="0">
                        <tr>
                            <td colspan="3">
                                TradeTime:&nbsp;&nbsp;${trade.tradeTime}
                            </td>
                        </tr>

                        <c:forEach items="${trade.items}" var="item">

                            <tr>
                                <th>书名</th>
                                <th>单价</th>
                                <th>数量</th>
                            </tr>
                            <tr>
                                <td>${item.book.title}</td>
                                <td>${item.book.price}</td>
                                <td>${item.quantity}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:forEach>
            </td>
        </tr>

    </table>
</div>
</body>
</html>
