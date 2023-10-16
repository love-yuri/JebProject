<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <script src="./js/index.js"></script>
</head>
<body>
<div class="flex w-screen h-screen items-center justify-center">
    <div class="w-[500px] bg-slate-500">
        <h1 class="text-center text-slate-800 font-semibold text-[26px]">图书列表</h1>
        <div class="flex flex-row">
            <div class="ml-auto"></div>
            <a class="mr-6 text-blue-800" href="addBook.jsp">添加图书</a>
        </div>
        <div class="h-1 bg-slate-600 mt-2 mb-2"></div>
        <div class="divider"> </div>
        <div class="flex flex-row  justify-between bg-slate-400">
            <span class="ml-3">ISBN</span>
            <span>书名</span>
            <span>单价</span>
            <span class="mr-3">操作</span>
        </div>
        <c:forEach var="book" items="${allBook}" varStatus="status">
            <form action="deleteBook" method="post" class="flex flex-row  justify-between bg-slate-400">
                <span class="ml-3">${book.ISBN}</span>
                <span>${book.name}</span>
                <span>${book.price}</span>
                <button class="mr-3 text-emerald-600" name="${status.index}">删除</button>
            </form>
        </c:forEach>
    </div>
</div>
</body>
</html>