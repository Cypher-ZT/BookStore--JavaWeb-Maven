<%--
  Created by IntelliJ IDEA.
  User: CypherZ
  Date: 2018/8/13
  Time: 18:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        response.sendRedirect(request.getContextPath()+"/bookServlet?method=getBooks");
    %>
</body>
</html>
