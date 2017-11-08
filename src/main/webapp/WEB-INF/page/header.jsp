<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String basePath = "/study/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<body>
<div>111
    <img src="<%=basePath%>pic/leaf.png" style="width: 60px"/>
    <c:if test="${user!= null }">
        Welcome ${user.userName} !
    </c:if>
    <c:if test="${user== null }">
        <a href="<%=basePath%>common/toLogin.html">登录</a>
    </c:if>
    <a href="<%=basePath%>common/shoppingCart.html">购物车</a>
    <a href="<%=basePath%>product/detail/all.html">商品</a>
    <c:if test="${user!= null }">
        <a href="<%=basePath%>user/viewOrder.html">我的订单</a>
    </c:if>
</div>
</body>
</html>
