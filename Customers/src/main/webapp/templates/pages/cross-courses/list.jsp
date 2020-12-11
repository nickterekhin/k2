<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorator="~{index(page_title='Cross-Courses')}">
<head>
    <title>Gallery</title>
</head>
<body>
<div layout:fragment="extra_buttons">
    <a th:href="@{/currencies}" class="btn btn-light btn-sm">Back to Currencies List</a>
</div>
<div layout:fragment="content">
    <div class="row">
        <div class="col-md-12 col-lg-12">
            <div class="card">
                <div class="card-header">
                    <h4>Currency: <span th:text="${currency.getName()}"></span></h4>
                </div>
                <div class="card-body">
                    <div class="content ">

                        <table class="table table-striped table-hover" id="Pages"  data-table="pages">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Cross</th>
                                <th>Amount</th>
                                <th class="action-buttons">Actions</th>
                            </tr>
                            </thead>
                            <tbody>

                            <tr th:each="course : ${crosses}">
                                <td th:text="${course.getId()}"></td>
                                <td th:text="${course.getFromCurrency().getName()}+'/'+${course.getToCurrency().getName()}"></td>
                                <td th:text="${course.getAmount()}"></td>
                                <td>
                                    <a th:href="@{/cross-courses/delete(id=${course.getId()},cid=${cid})}" title="Edit" class="btn btn-danger btn-sm"><i class="fal fa-trash"></i>Delete</a>&nbsp;

                                </td>
                            </tr>
                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>