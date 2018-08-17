<%--
  Created by IntelliJ IDEA.
  User: CypherZ
  Date: 2018/8/14
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<html>
<head>
    <title>购物车</title>
</head>
<body>
<br><br><br>
<div style="text-align: center;">
    您的购物车为空
    <br>
    <a href="bookServlet?method=getBooks&pageNo=${param.pageNo}">继续购物</a>
</div>
</body>
</html>
