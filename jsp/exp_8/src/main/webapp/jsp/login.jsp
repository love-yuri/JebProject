<%--
  Created by IntelliJ IDEA.
  User: sunny
  Date: 2021/11/27
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
    <title>登录</title>
</head>

<body>
    <%@include file="../header.jsp"%>
    <form action="${pageContext.request.contextPath}/UserServlet?action=login" method="post">
        <input type="text" name="uname" placeholder="请输入用户名" required><br>
        <input type="password" name="pwd" placeholder="请输入密码" required><br>
        <input type="submit" value="登录"><br>
        ${msg}
    </form>
</body>
</html>
