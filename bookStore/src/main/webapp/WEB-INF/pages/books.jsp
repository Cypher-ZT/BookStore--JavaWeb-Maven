<%--
  Created by IntelliJ IDEA.
  User: CypherZ
  Date: 2018/8/13
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/common/common.jsp" %>
<jsp:useBean id="bookPage" scope="request" type="com.cypher.bookstore.web.Page"/>
<html>
<head>
    <title>首页</title>
</head>
<script src="${pageContext.request.contextPath}/jquery-3.3.1.js"></script>
<script>
    $(function () {
        $("#pageNo").change(function () {
            var pageNo = $(this).val();
            pageNo = pageNo.trim(pageNo);
            var flag = true;
            var reg = /^\d+$/;
            if (!reg.test(pageNo)) {
                flag = false;
            } else {
                var pageNoInt = parseInt(pageNo);
                if (pageNoInt < 1 || pageNoInt > "${bookPage.pageCount}") {
                    flag = false;
                }
            }
            if (!flag) {
                $(this).val("");
                alert("输入的页码不合法");
                return;
            }
            window.location.href = "${pageContext.request.contextPath}/bookServlet?method=getBooks&pageNo="
                + pageNoInt + "&" + $("input:hidden").serialize();
        })
    })
</script>
<body>
<div style="text-align: center;">


    <br><br><br>
    <c:if test="${!empty param.title}">
        你成功将<span style="color: orange">${param.title}</span>加入到了您的购物车
        <br>
    </c:if>
    <c:if test="${!empty sessionScope.shoppingCart.books}">
        <jsp:useBean id="shoppingCart" scope="session" type="com.cypher.bookstore.domain.ShoppingCart"/>
        您的购物车共有${shoppingCart.bookNumber}本书
        <a href="bookServlet?method=forwardPage&page=shoppingCart.jsp&pageNo=${bookPage.pageNo}">查看购物车</a>
        <br><br>
    </c:if>
    <form action="bookServlet?method=getBooks" method="post">
        <label for="minPrice">Price:</label><input type="text" size="1" name="minPrice" id="minPrice"
                                                   value="${param.minPrice}">-
        <input type="text" size="1" name="maxPrice" value="${param.maxPrice}">
        <input type="submit" value="查找">
    </form>
    <br>
    <table align="center">
        <c:forEach items="${bookPage.pageList}" var="item">
            <tr>
                <td>
                    <a href="bookServlet?method=getBook&pageNo=${bookPage.pageNo}&id=${item.id}">${item.title}</a>
                    <br>
                        ${item.author}
                </td>
                <td>
                        ${item.price}
                </td>
                <td>
                    <a href="bookServlet?method=addToCart&pageNo=${bookPage.pageNo}&id=${item.id}&title=${item.title}">加入购物车</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br><br>
    共${bookPage.pageCount}页
    &nbsp;
    当前第${bookPage.pageNo}页
    &nbsp;
    <c:if test="${bookPage.hasPrev}">
        <a href="bookServlet?method=getBooks&pageNo=1">首页</a>
        &nbsp;
        <a href="bookServlet?method=getBooks&pageNo=${bookPage.prevNo}">上一页</a>
        &nbsp;
    </c:if>
    <c:if test="${bookPage.hasNext}">
        <a href="bookServlet?method=getBooks&pageNo=${bookPage.nextNo}">下一页</a>
        &nbsp;
        <a href="bookServlet?method=getBooks&pageNo=${bookPage.pageCount}">末页</a>
        &nbsp;
    </c:if>
    <label for="pageNo">转到</label> <input type="text" size="2" id="pageNo"> 页
</div>
</body>
</html>
