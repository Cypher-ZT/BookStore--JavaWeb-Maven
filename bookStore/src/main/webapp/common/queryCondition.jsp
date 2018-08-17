<script src="${pageContext.request.contextPath}/jquery-3.3.1.js"></script>
<script>
    $(function () {
        $("a").each(function () {
            this.onclick = function () {
                var serializeVal = $("input:hidden").serialize();
                window.location.href = this.href + "&" + serializeVal;
                return false;
            };
        });
    });
</script>

<input type="hidden" name="minPrice" value="${param.minPrice}">
<input type="hidden" name="maxPrice" value="${param.maxPrice}">

