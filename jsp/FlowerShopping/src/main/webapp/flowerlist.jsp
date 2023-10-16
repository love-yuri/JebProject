<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table width="60%" border="1" align="center">
    <tr>
        <th>编号</th>
        <th>名称</th>
        <th>图片</th>
        <th>价格</th>
        <th>介绍</th>
        <th>数量</th>
        <th>加购</th>
    </tr>

    <c:forEach var="flower" items="${flowerList}">
        <tr style="text-align: center">
            <td style="width: 50px">${flower.fid}</td>
            <td style="width: 100px">${flower.name}</td>
            <td ><img width="200" src="${flower.pic}"></td>
            <td style="width: 150px">${flower.price}</td>
            <td>${flower.info}</td>
            <td style="width: 50px">${flower.stock}</td>
            <td style="width: 50px">100</td>
            <td style="width: 250px"><a href=""> 加入购物车</a></td>

        </tr>
    </c:forEach>

</table>
</body>
</html>
