<%--
  Created by IntelliJ IDEA.
  User: yuri
  Date: 2023/9/30
  Time: 下午9:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>九九乘法表</title>
    <style>
        span {
            border: 1px solid black;
            padding: 3px;
            margin: 3px;
            display: inline-block;
        }
    </style>
</head>
<body>

<%
    for(int i = 1; i <= 9; i++) {
        for(int j = 1; j <= i ; j++) {
            out.print(String.format("<span>%d x %d = %d </span>", i, j, i * j));
        }
        out.println("<br>");
    }
%>
</body>
</html>
