<%--
  Created by IntelliJ IDEA.
  User: yuri
  Date: 2023/10/22
  Time: 下午9:01
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: yuri
  Date: 2023/10/22
  Time: 下午7:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <style type="text/css">
    * {
      padding: 0;
      margin: 0;
      box-sizing: border-box;
    }
    input {
      font-size: 33px;
      height: 50px;
    }
  </style>
</head>
<body>
<h1 style="text-align: center; font-size: 40px">EL隐含对象获取</h1>
<div style="height: 3px;background-color: rgb(128,128,128); margin-top: 23px"></div>
<div style="display: flex; align-items: center; flex-direction: column">
  <form action="getParam" method="post" style="margin-top: 30px">
    <h1>输入param参数值:</h1>
    <label style="font-size: 33px">
      param:
      <input type="text" name="inputParam">
      <input type="submit">
    </label>
  </form>
  <div style="font-size: 40px">${inputParam}</div>
  <div style="font-size: 40px">${userAgent}</div>
</div>
</body>
</html>

