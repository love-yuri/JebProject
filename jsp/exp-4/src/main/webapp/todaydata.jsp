<%--
  Created by IntelliJ IDEA.
  User: yuri
  Date: 2023/9/30
  Time: 下午9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.LocalDate" %>

<html>

<head>
  <title>日期显示</title>
</head>
<body>

<%! LocalDate today = LocalDate.now(); %>

今天的日期是 : <%= today%>
</body>

</html>
