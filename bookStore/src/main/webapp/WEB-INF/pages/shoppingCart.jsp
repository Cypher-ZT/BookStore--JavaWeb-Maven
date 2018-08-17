<%--
  Created by IntelliJ IDEA.
  User: CypherZ
  Date: 2018/8/14
  Time: 4:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/common/common.jsp" %>
<jsp:useBean id="shoppingCart" scope="session" type="com.cypher.bookstore.domain.ShoppingCart"/>
<html>
<head>
    <title>购物车</title>
</head>
<script src="${pageContext.request.contextPath}/jquery-3.3.1.js"></script>
<script>
    $(function () {
        $(".quantity").change(function () {
            var quantityStr = $.trim(this.value);
            var oldQuantity = $.trim(this.id);
            var title = $(this).parent("td").parent("tr").find("td:first").text();
            var id = $.trim(this.name);
            var flag = true;
            var reg = /^\d+$/;
            var quantity;
            if (reg.test(quantityStr)) {
                quantity = parseInt(quantityStr);
                if (quantity < 0) {
                    flag = false;
                }
            }
            else {
                flag = false;
            }
            if (!flag) {
                alert("输入的数值不合法");
                $(this).val(oldQuantity);
                return;
            }
            if (quantity === 0) {
                var zeroFlag = confirm("您确定要删除" + title + "这一项吗?");
                if (zeroFlag) {
                    var $a = $(this).parent("td").parent("tr").find("td:last").find("a");
                    $a[0].onclick();
                    return;
                } else {
                    $(this).val(oldQuantity);
                    return;
                }
            }
            var url = "bookServlet";
            var args = {"method": "updateItemQuantity", "id": id, "quantity": quantityStr};
            $.post(url, args, function (data) {
                var bookNumber = data.bookNumber;
                var totalMoney = data.totalMoney;
                $("#bookNumber").text(bookNumber);
                $("#totalMoney").text(totalMoney);
            }, "JSON");
            //小结
            var price = $(this).parent("td").next("td").text();
            $(this).parent("td").next("td").next("td").text((quantity * price).toFixed(1));

            //    原本的quantity 用于数值错误
            this.id = quantityStr;
        });
        $(".delete").click(function () {
            var title = $(this).parent("td").parent("tr").find("td:first").text();
            return confirm("您确定要删除" + title + "这一项吗?");
        })
    })
</script>
<body>
<br>
<div style="text-align: center;">
    您的购物车中共有<span id="bookNumber">${shoppingCart.bookNumber}</span>本书
    <br>
    <table align="center" style="border-collapse:separate; border-spacing:10px 15px; ">
        <tr>
            <th>书名</th>
            <th>数量</th>
            <th>单价</th>
            <th>小结</th>
        </tr>
        <c:forEach items="${shoppingCart.items}" var="item">
            <tr>
                <td>${item.book.title}</td>
                <td><input type="text" size="2" name="${item.book.id}" value="${item.quantity}" id="${item.quantity}"
                           class="quantity"></td>
                <td>${item.book.price}</td>
                <td>${item.itemMoney}</td>
                <td><a class="delete"
                       href="bookServlet?method=deleteItem&id=${item.book.id}&pageNo=${param.pageNo}">删除</a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td>
                总金额为:<span id="totalMoney">${shoppingCart.totalMoney}</span>元
            </td>
        </tr>

    </table>
    <a href="bookServlet?method=getBooks&pageNo=${param.pageNo}">继续购物</a>&nbsp;
    <a href="bookServlet?method=clearShoppingCart&pageNo=${param.pageNo}">清空购物车</a>&nbsp;
    <a href="bookServlet?method=forwardPage&page=checkOut.jsp&pageNo=${param.pageNo}">结账</a>
</div>
</body>
</html>
