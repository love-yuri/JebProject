<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        body {
            background-color: rgba(239, 231, 216, 0.99);
        }
    </style>
</head>
<body>
<h1 align="center"> 鲜花小铺 </h1>
<br/>
<center>
    <a href="login.html">&nbsp;&nbsp;登录&nbsp;&nbsp;</a>|<a href="register.html">&nbsp;&nbsp;注册&nbsp;&nbsp;</a>
</center>
<br><br>
<center>
    鲜花列表
</center>
<%@include file="flowerlist.jsp" %>

</body>
</html>
