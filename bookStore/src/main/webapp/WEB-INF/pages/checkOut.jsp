<%--
  Created by IntelliJ IDEA.
  User: CypherZ
  Date: 2018/8/15
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/common/common.jsp" %>
<jsp:useBean id="shoppingCart" scope="session" type="com.cypher.bookstore.domain.ShoppingCart"/>
<html>
<head>
    <title>结账</title>
</head>
<script src="${pageContext.request.contextPath}/jquery-3.3.1.js"></script>
<script>
    $(function () {
        $("form:first").submit(function () {
            var userName = $.trim($("#userName").val());
            var accountId = $.trim($("#accountId").val());
            var flag = true;
            if (userName === "") {
                $("#nameError").html("信用卡姓名不可为空");
                flag = false;
            }
            if (accountId === "") {
                $("#idError").html("信用卡账号不可为空");
                flag = false;
            }
            var reg = /^\d+$/;
            if (!reg.test(accountId)) {
                $("#idError").html("信用卡账号格式不正确");
                flag = false;
            }
            if (flag) {
                $("#oldUserName:hidden").val(userName);
                $("#oldAccountId:hidden").val(accountId);
            }
            return flag;
        })
    })
</script>
<body>
<br><br>
<div style="text-align: center;">
    您的购物车中共有<span id="bookNumber">${shoppingCart.bookNumber}</span>本书
    总金额为:<span id="totalMoney">${shoppingCart.totalMoney}</span>元
    <br><br>

    <c:if test="${(!empty requestScope.errorType)&&requestScope.errorType==3}">
        <span style="color: red">您所购买的以下图书库存不足</span><br>
        <c:forEach items="${requestScope.quantityErrorBooks}" var="errorBook">
            ${errorBook.title}&nbsp; 库存为${errorBook.storeNumber} <br>
        </c:forEach>
        <br>
    </c:if>

    <c:if test="${(!empty requestScope.errorType)&&requestScope.errorType==4}">
        <span style="color: red">您的账户余额不足</span><br>
    </c:if>


    <form action="bookServlet?method=cash&pageNo=${param.pageNo}" method="post">
        <input type="hidden" id="oldUserName" name="oldUserName" value="${param.oldUserName}">
        <input type="hidden" id="oldAccountId" name="oldAccountId" value="${param.oldAccountId}">
        <table align="center" style="border-collapse:separate; border-spacing:10px 15px; ">
            <tr>
                <th><label for="userName">信用卡姓名:</label></th>
                <td><input type="text" id="userName" name="userName" value="${param.oldUserName}"></td>
                <c:if test="${(!empty requestScope.errorType)&&requestScope.errorType==1}">
                    <td><span style="color: red">您输入的信用卡姓名不存在</span></td>
                </c:if>
                <td><span id="nameError" style="color: red"></span></td>
            </tr>
            <tr>
                <th><label for="accountId">信用卡账号:</label></th>
                <td><input type="text" id="accountId" name="accountId" value="${param.oldAccountId}"></td>
                <c:if test="${(!empty requestScope.errorType)&&requestScope.errorType==2}">
                    <td><span style="color: red">您输入的信用卡姓名与账号不匹配</span></td>
                </c:if>
                <td><span id="idError" style="color: red"></span></td>
            </tr>
            <tr>
                <td colspan="2" align="right"><input type="submit" value="确定"><br></td>
            </tr>
            <tr>
                <td><a href="bookServlet?method=getBooks&pageNo=${param.pageNo}">继续购物</a></td>
                <td><a href="bookServlet?method=forwardPage&page=shoppingCart.jsp&pageNo=${param.pageNo}">查看购物车</a>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
