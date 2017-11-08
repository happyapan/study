<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String basePath = "/study/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>My Shopping Cart</title>
</head>

<body>

<jsp:include page="header.jsp"/>
<br/><br/>
<c:set var="totalPrice" scope="request" value="0.0"/>
<c:forEach var="oneProduct" items="${cartProducts}" varStatus="name">
    Name : ${oneProduct.name}
    <br/>
    Price:${oneProduct.price}
    <br/>
    ${oneProduct.desc}
    <br/>
    <a href="<%=basePath%>user/delete/${oneProduct.id}">Delete</a>
    <hr/>
    <c:set var="totalPrice" value="${totalPrice+oneProduct.price}"></c:set>

</c:forEach>
<c:if test="${cartProducts!= null && fn:length(cartProducts) > 0}">
    <h1>
        Total: <fmt:formatNumber value="${totalPrice}" pattern="#,#00.0#"/>
    </h1>

    <a href="<%=basePath%>user/placeOrder.html">下单</a>
</c:if>

<c:if test="${cartProducts== null || fn:length(cartProducts) == 0  }">
    您还没有挑选商品！请<a href="<%=basePath%>product/detail/all.html">浏览商品</a>
</c:if>
</body>
</html>
