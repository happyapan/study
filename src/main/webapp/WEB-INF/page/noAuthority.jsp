<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String basePath = "/study/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


    <title></title>


</head>

<body>
<jsp:include page="header.jsp" />
<h1>
    你没有权限操作，请先登录！
  <a href="<%=basePath%>common/toLogin.html">login in now !!!!</a>
</h1>


</body>
</html>
