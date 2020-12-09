<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorator="~{index(page_title='Transactions')}">
<head>
    <title>Gallery</title>
    <script layout:fragment="table_option">
        opt.order = [5, 'desc'];
    </script>
</head>
<body>
<div layout:fragment="extra_buttons">
    <a th:href="@{/accounts(cid=${param.cid})}" class="btn btn-light btn-sm">Back to Accounts List</a>
</div>
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
                                <th>Type</th>
                                <th>Number</th>
                                <th>Account</th>
                                <th>Amount</th>
                                <th>CreatedAt</th>
                                <th>Description</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr th:each="transaction : ${transactions}">
                                <td th:text="${transaction.getId()}"></td>
                                <td th:text="${transaction.getTransactionType().getName()}" th:class="${'transaction transaction-'+transaction.getTransactionType().getName().toLowerCase()}"></td>
                                <td th:text="${transaction.getNumber()}"></td>
                                <td th:text="${transaction.getAccount().getNumber()}"></td>
                                <td th:text="${transaction.getAmount()}"></td>
                                <td th:text="${#dates.format(transaction.getCreatedAt().toDate(),'dd-MM-YYYY HH:mm')}"></td>
                                <td th:text="${transaction.getDescription()}"></td>
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