<%--
  Created by IntelliJ IDEA.
  User: CypherZ
  Date: 2018/8/13
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/common/common.jsp" %>
<html>
<head>
    <title>错误</title>
</head>
<body>
<div style="text-align: center">
    <br><br>
    <span style="color: red">错误:您所需的图书不存在</span>
    <br><br>
    <a href="bookServlet?method=getBooks&pageNo=${param.pageNo}&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}">继续购物</a>
    <br><br>
    <a href="${pageContext.request.contextPath}/bookServlet?method=getBooks">返回首页</a>
</div>
</body>
</html>
