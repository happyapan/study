<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String basePath = "/study/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


    <title>Login </title>


</head>

<body>
<h1>
     Please login in now !!!!
</h1>
<form action="<%=basePath%>system/userLogin.html" method="post">
    User Name:<br/>
    <input type="text" name="userName" id="userName"/><br/>
    Password:<br/>
    <input type="password" name="pwd" id="pwd"/><br/>
    <br/>
    <input type="submit" value="OK"/>
    <input type="reset" value="cancle"/>
</form>


</body>
</html>
