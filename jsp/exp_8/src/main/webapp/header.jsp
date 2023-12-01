<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sunny
  Date: 2021/11/27
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>

<body>
    <a href="${pageContext.request.contextPath}/index.jsp">首页</a>
    <c:if test="${empty loginUser}">
        <a href="${pageContext.request.contextPath}/jsp/login.jsp">登录</a>
    </c:if>
    <c:if test="${not empty loginUser}">
        欢迎你，${loginUser.uname} |
        <a href="${pageContext.request.contextPath}/UserServlet?action=logout">退出</a>
    </c:if>
    <a href="${pageContext.request.contextPath}/jsp/main.jsp">主页</a>
    <a href="${pageContext.request.contextPath}/jsp/cart.jsp">购物车</a>
    <a href="${pageContext.request.contextPath}/jsp/personal.jsp">个人中心</a><br>
    <hr>
</body>
</html>
