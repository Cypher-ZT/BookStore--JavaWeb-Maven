<%--
  Created by IntelliJ IDEA.
  User: CypherZ
  Date: 2018/8/16
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/common/common.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="text-align: center;">
    <br><br>
    <form action="userServlet" method="post">
        <label for="userName">用户名:</label><input type="text" id="userName" name="userName">
        <input type="submit" value="提交">
    </form>
</div>
</body>
</html>
