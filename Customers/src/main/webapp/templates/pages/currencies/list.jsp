<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorator="~{index(page_title='Currencies')}">
<head>
    <title>Gallery</title>
</head>
<body>
<div layout:fragment="content">
    <div class="row">
        <div class="col-md-12 col-lg-12">
            <div class="card">
                <div class="card-body">
                    <div class="content ">

                        <table class="table table-striped table-hover" id="Pages"  data-table="pages">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Currency</th>
                                <th>Cross-Courses</th>
                                <th class="action-buttons">Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr th:each="currency : ${currencies}">
                                <td th:text="${currency.getId()}"></td>
                                <td th:text="${currency.getName()}"></td>
                                <td><ul th:if="${currency.getCrossCoursesFrom().size()}!=0">
                                    <li th:each="cr : ${currency.getCrossCoursesFrom()}">
                                        <span th:text="${cr.getToCurrency().getName()}+' >> '+${cr.getAmount()}"></span>
                                    </li>
                                </ul><span style="color:#6f1414" th:if="${currency.getCrossCoursesFrom().size()}==0"><i class="fa fa-times"></i></span></td>
                                <td>
                                    <a th:href="@{/currencies/eddit(id=${currency.getId()})}" title="Edit" class="btn btn-warning btn-sm"><i class="fal fa-pencil"></i>Edit</a>&nbsp;
                                    <a th:href="@{/cross-courses(cid=${currency.getId()})}" title="Cross-Courses" class="btn btn-info btn-sm"><i class="fal fa-sync-alt"></i>Cross-Courses</a>&nbsp;
                                    <a th:href="@{/currencies/delete(id=${currency.getId()})}" title="Edit" class="btn btn-danger btn-sm"><i class="fal fa-trash"></i>Delete</a>&nbsp;

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