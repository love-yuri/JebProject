<%--
  Created by IntelliJ IDEA.
  User: yuri
  Date: 2023/10/22
  Time: 下午7:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        * {
            padding: 0;
            margin: 0;
            box-sizing: border-box;
        }
        table {
            width: 700px;
            margin-top: 50px;
            border-collapse: collapse;
        }
        tr, td, th {
            border: 5px solid rgb(128, 128, 128);
            font-size: 24px;
        }
    </style>
</head>
<body>
<h1 style="text-align: center; font-size: 40px">EL-表达式语言 - 运算</h1>
<div style="height: 3px;background-color: rgb(128,128,128); margin-top: 23px"></div>
<div style="display: flex; align-items: center; justify-content: center; flex-direction: column">
    <table>
        <tr>
            <th>国家</th>
            <th>首都</th>
        </tr>
        <c:forEach items="${capitals}" var="country">
            <tr>
                <td>${country.key}</td>
                <td>${country.value}</td>
            </tr>

        </c:forEach>
    </table>
    <table style="margin-top: 40px">
        <tr>
            <th>国家</th>
            <th>城市</th>
        </tr>
        <c:forEach items="${bigcities}" var="city">
            <c:set var="cities" value="" scope="page" />
            <c:forEach items="${city.value}" var="item" varStatus="index">
                <c:set var="cities" value="${cities}${item} ,  "  scope="page" />
            </c:forEach>
            <tr>
                <td>${city.key}</td>
                <td>${cities}</td>
            </tr>
        </c:forEach>
<%--        <tr>--%>
<%--            <td>${result}</td>--%>
<%--        </tr>--%>
    </table>
</div>
</body>
</html>
