<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorator="~{index(page_title='Accounts')}">
<head>
    <title>Gallery</title>
</head>
<body>
<div layout:fragment="extra_buttons">
    <a th:href="@{/customers}" class="btn btn-light btn-sm">Back to Customers List</a>
</div>
<div layout:fragment="content">
    <div class="row">
        <div class="col-md-12 col-lg-12">
            <div class="card">
                <div class="card-header">
                    <h4>Customer: <span th:text="${customer.getFirstName()}"></span> <span th:text="${customer.getLastName()}"></span></h4>
                </div>
                <div class="card-body">
                    <div class="content ">

                        <table class="table table-striped table-hover" id="Pages"  data-table="pages">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Account Number</th>
                                <th>Currency</th>
                                <th>Balance</th>
                                <th class="action-buttons">Actions</th>
                            </tr>
                            </thead>
                            <tbody>

                            <tr th:each="account : ${accounts}">
                                <td th:text="${account.getId()}"></td>
                                <td th:text="${account.getNumber()}"></td>
                                <td th:text="${account.getCurrency().getName()}"></td>
                                <td th:text="${account.getBalance()}"></td>
                                <td>
                                    <a th:href="@{/transactions(id=${account.getId()},cid=${cid})}" title="Transaction history" class="btn btn-primary btn-sm"><i class="fal fa-list"></i>Transactions</a>
                                    <a th:href="@{/accounts(action='charge',id=${account.getId()},cid=${cid})}" title="Charge Account's balance" class="btn btn-info btn-sm"><i class="fal fa-plus"></i>Charge Account</a>
                                    <a th:href="@{/accounts(action='transfer',id=${account.getId()},cid=${cid})}" title="Transfer" class="btn btn-warning btn-sm" th:if="${accounts.size()}>1"><i class="fal fa-exchange"></i>Transfer</a>
                                    <a th:href="@{/accounts(action='delete',id=${account.getId()},cid=${cid})}" title="Edit" class="btn btn-danger btn-sm"><i class="fal fa-trash"></i>Delete</a>

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