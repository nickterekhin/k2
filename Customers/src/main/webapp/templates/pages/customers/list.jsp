<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorator="~{index(page_title='Customers')}">
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
                                    <th>FirstName</th>
                                    <th>LastName</th>
                                    <th class="action-buttons">Actions</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr th:each="customer : ${customers}">
                                    <td th:text="${customer.getId()}"></td>
                                    <td th:text="${customer.getFirstName()}"></td>
                                    <td th:text="${customer.getLastName()}"></td>
                                    <td>
                                        <a th:href="@{/customers(action='edit',id=${customer.getId()})}" title="Edit" class="btn btn-warning btn-sm"><i class="fal fa-pencil"></i>Edit</a>&nbsp;
                                        <a th:href="@{/accounts(cid=${customer.getId()})}" title="Account" class="btn btn-info btn-sm"><i class="fal fa-wallet"></i>Account</a>&nbsp;
                                        <a th:href="@{/customers(id=${customer.getId()})}" title="Delete" class="btn btn-danger btn-sm"><i class="fal fa-trash"></i>Delete</a>&nbsp;
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