<%--
  Created by IntelliJ IDEA.
  User: yuri
  Date: 2023/9/30
  Time: 下午10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    double data_1 = 0, data_2 = 0, sum = 0;
    try {
        data_1 = Double.parseDouble(request.getParameter("data_1"));
        data_2 = Double.parseDouble(request.getParameter("data_2"));
        out.println(String.format("<h2>%f + %f = %f </h2>", data_1, data_2, data_1 + data_2));
    } catch (Exception e) {
        out.println("<h2>传入数据异常捏!</h2>");
    }
%>
</body>
</html>
