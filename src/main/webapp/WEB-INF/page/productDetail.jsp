<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String basePath = "/study/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Product detail</title>
</head>

<body>
<jsp:include page="header.jsp" />
<c:forEach var="oneProduct" items="${product}" varStatus="name">
    <h1>
        Name : ${oneProduct.name}
    </h1>

    <br/>
    Price:${oneProduct.price}
    <br/><br/>
    ${oneProduct.desc}
    <br/><br/>
    <a href="<%=basePath%>user/addCart/${oneProduct.id}">Add To Cart</a>

    <hr/>
</c:forEach>

<%--<a href="user/message">A message for you!</a>--%>

</body>
</html>
