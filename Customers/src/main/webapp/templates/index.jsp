<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
    <title layout:title-pattern="$DECORATOR_TITLE - $CONTENT_TITLE">Bank - Terekhin Group</title>
    <object th:include="other/header::headTag" th:remove="tag"/>
</head>
<body class="customer-panel">
<nav class="navbar navbar-expand-md navbar-dark bg-dark p-0" style="margin-bottom: 0">
    <div class="container-fluid">

        <a class="navbar-brand" href="/admin">
            <img  src="/images/logo.light.png" alt="" height="50">
        </a>
        <a data-target="#navbar" data-toggle="collapse" type="button" class="navbar-toggler border-0">
            <i class="fal fa-bars text-white"></i>
        </a>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">

        </div>
    </div>
</nav>
<div style="clear: both;"></div>

<div class="admin-wrapper">
    <div th:insert="~{partials/navmenu::menu}" class="admin-sidebar collapse in d-none d-sm-block">
        <!--Include navigation links -->
    </div>
    <div class="container-fluid mb-5" id="main-content">
    <div class="page-head">
        <h3 class="float-left"><span id="sorting-label" th:text="${page_title}">Dashboard</span>
        </h3>

        <div class="float-right" id="manage-buttons" th:if="${template_path}!='/'">

            <a th:href="(${cid}?(@{{add_path}(add_path=${template_path},action='add',cid=${cid})}):@{{add_path}(add_path=${template_path},action='add')})" href="" class="btn btn-success add-btn btn-sm"><i class="fal fa-plus"></i>Add New</a>

            <a th:href="(${cid}?@{{view_all_path}(view_all_path=${template_path},cid=${cid})}:@{{view_all_path}(view_all_path=${template_path})})" class="btn btn-info add-btn btn-sm"><i class="fal fa-hand-point-right"></i>View All</a>

        </div>

        <div class="clearfix"></div>
    </div>
    <div class="row">
        <div class="col" id="notification-message">
           <!-- Notifications-->
        </div>
    </div>
    <div layout:fragment="content" class="container-fluid">

    </div>
</div>
</div>
</body>
</html>
