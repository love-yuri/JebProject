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
<div style="display: flex; align-items: center; justify-content: center">
    <table>
        <tr>
            <th>说明</th>
            <th>EL表达式</th>
            <th>结果</th>
        </tr>
        <tr>
            <td>加</td>
            <td><%="${1 + 2}"%></td>
            <td>${1 + 2}</td>
        </tr>
        <tr>
            <td>减</td>
            <td><%="${-4 - 2}"%></td>
            <td>${-4 - 2}</td>
        </tr>
        <tr>
            <td>除</td>
            <td><%="${3 div 4}"%></td>
            <td>${3 div 4}</td>
        </tr>
        <tr>
            <td>取余</td>
            <td><%="${10 % 4}"%></td>
            <td>${10 % 4}</td>
        </tr>
        <tr>
            <td>条件求值</td>
            <td><%="${(1 == 2) ? 3 : 4}"%></td>
            <td>${(1 == 2) ? 3 : 4}</td>
        </tr>
        <tr>
            <td>数字-大于</td>
            <td><%="${1 gt 2}"%></td>
            <td>${1 gt 2}</td>
        </tr>
        <tr>
            <td>字符-不等于</td>
            <td><%="${'abe' ne 'ade'}"%></td>
            <td>${'abe' ne 'ade'}</td>
        </tr>
        <tr>
            <td>与</td>
            <td><%="${true and true}"%></td>
            <td>${true and true}</td>
        </tr>
        <tr>
            <td>或</td>
            <td><%="${true || false}"%></td>
            <td>${true || false}</td>
        </tr>
        <tr>
            <td>空判断</td>
            <td><%="${not true}"%></td>
            <td>${not true}</td>
        </tr>
        <tr>
            <td>空判断</td>
            <td><%="${empty \"2008\"}"%></td>
            <td>${empty "2008"}</td>
        </tr>
        <tr>
            <td>空判断</td>
            <td><%="${empty null}"%></td>
            <td>${empty null}</td>
        </tr>

    </table>
</div>
</body>
</html>
