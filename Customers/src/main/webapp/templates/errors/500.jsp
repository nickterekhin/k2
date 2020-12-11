<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorator="~{index(page_title='Fatal Error')}">
<head>
    <title>Gallery</title>
</head>
<body>
<div layout:fragment="content">
<h1>Something went wrong! Give me a chance and i will fix it!</h1>
    <a href="/" class="btn btn-success btn-sn">Start again</a>
</div>
</body>
</html>