<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Title</title>
</head>
<body>
<div th:fragment="notification">
    <div th:class="${'alert '+message.type}">
        <p th:text="${message.message}"></p>
    </div>
</div>
</body>
</html>
