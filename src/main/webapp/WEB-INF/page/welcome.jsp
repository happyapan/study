<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String basePath = "/study/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'helloWorld.jsp' starting page</title>


	</head>

	<body>
	<jsp:include page="header.jsp" />
		<h1>
			Message : ${message}
		</h1>

		<br/><br/><br/><br/>
	    <%--<a href="user/message">A message for you!</a>--%>

	</body>
</html>
