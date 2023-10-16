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
    <form class="w-[500px] bg-slate-500" action="addBook" method="post">
        <div class="w-full flex flex-col bg-slate-200 items-center justify-center">
            <div class="mt-3">
                <label>
                    ISBN:
                    <input type="text" placeholder="请输入书的书号" name="ISBN"
                           class="bg-slate-200 border-double border-4 ml-6 border-indigo-600">
                </label>
            </div>
            <div class="mt-3">
                <label>
                    书名:
                    <input type="text" placeholder="请输入书的名字" name="name"
                           class="bg-slate-200 border-double border-4 ml-6 border-indigo-600">
                </label>
            </div>
            <div class="mt-3">
                <label>
                    单价:
                    <input type="text" placeholder="请输入书的单价" name="price"
                           class="bg-slate-200 border-double border-4 ml-6 border-indigo-600">
                </label>
            </div>
            <button class="border-dotted border-2 p-1 mt-2 border-indigo-600">保存</button>
        </div>
    </form>
</div>
</body>
</html>