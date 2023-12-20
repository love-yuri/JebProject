<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yuri
  Date: 2023/12/14
  Time: 上午8:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <h1>欢迎你, ${currentUser}</h1>
  <h2>目前已经注册的用户列表如下</h2>

  <table border="1">
    <tr>
      <td>用户名</td>
      <td>密码</td>
    </tr>
    <c:forEach items="${users}" var="user">
      <tr>
        <td>${user.key}</td>
        <td>${user.value}</td>
      </tr>
    </c:forEach>
  </table>
</body>
</html>
