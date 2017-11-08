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
	<title>My Orders</title>
</head>

<body>

<jsp:include page="header.jsp"/>
<br/><br/>
<c:set var="totalPrice" scope="request" value="0.0"/>

<c:forEach var="oneOrder" items="${orderInfos}" varStatus="name">
	<b>Order ID : ${oneOrder.orderId}</b>
	<br/>
	Price:<fmt:formatNumber value="${oneOrder.price}" pattern="#,#00.0#"/>
	<br/>
	下单时间：${oneOrder.createDate}
	<br/><br/>
	<c:forEach var="oneProduct" items="${oneOrder.products}">
		Product Name : ${oneProduct.name}
		<br/>
		Product Price:${oneProduct.price}
		<br/>
		${oneProduct.desc}
		<br/>
	</c:forEach>
	<hr/>
</c:forEach>


<c:if test="${orderInfos== null || fn:length(orderInfos) == 0  }">
	您还没有订单！请<a href="<%=basePath%>product/detail/all.html">浏览商品</a>
</c:if>
</body>
</html>
