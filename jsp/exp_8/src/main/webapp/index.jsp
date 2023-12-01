<%@ page import="com.yuri.exp_8.listener.OnlineUserCounter" %><%--
  Created by IntelliJ IDEA.
  User: sunny
  Date: 2021/11/28
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
      <style>
          body{
              font-size: 20px;
			  
          }
      </style>
  </head>

  <body>
        <%@ include file="header.jsp"%>
      这是网站的首页<br>
      <a href="${pageContext.request.contextPath}/jsp/personal.jsp">进入个人中心</a><br>

        <%
            // 获取在线人数
            int onlineUsers = OnlineUserCounter.getOnlineUsers();
        %>

        <p>当前在线人数： <%= onlineUsers %></p>
  </body>
</html>
