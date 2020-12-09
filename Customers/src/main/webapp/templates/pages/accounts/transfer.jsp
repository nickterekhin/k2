<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorator="~{index(page_title='Account - Transfering')}">
<head>
    <title>Customers - Add New</title>
</head>
<body>
<div layout:fragment="content">
    <form class="form-horizontal" method="POST" action="">
        <div class="row">
            <div class="col-xl-9 col-lg-8">
                <div class="card ">
                    <div class="card-header">
                        <h5>Main Settings <small> Customer: <span th:text="${customer.getFirstName()}"></span> <span th:text="${customer.getLastName()}"></span> </small></h5>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col">

                                <div class="form-group row">
                                    <label class="col-md-2 col-form-label text-md-right font-weight-bold" for="fromAccount">From Account</label>
                                    <div class="col-md-10">
                                        <input type="text" id="fromAccount" th:value="${account.getNumber()+' ('+account.getCurrency().getName()+') - Balance: '+account.getBalance()}" class="form-control" readonly>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-md-2 col-form-label text-md-right font-weight-bold" for="toAccount">To Account</label>
                                    <div class="col-md-10">
                                        <select name="toAccount" required id="toAccount" class="form-control">
                                            <option th:each="acc : ${accounts}" th:value="${acc.getId()}" th:text="${acc.getNumber() + ' ('+acc.getCurrency().getName()+')'}"></option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-md-2 col-form-label text-md-right font-weight-bold" for="Amount">Amount to Transfer</label>
                                    <div class="col-md-10">
                                        <input type="number" name="Amount" class="form-control" required id="Amount" th:value="${param.containsKey('Balance')}?${param.Balance}" min="0"  step="0.01"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

            <div class="col-xl-3 col-lg-4">
                <div class="card ">
                    <div class="card-header">
                        <h5>Action</h5>
                    </div>
                    <div class="card-body">
                        <button type="submit" class="btn btn-block btn-success btn-md"><i class="fal fa-save"></i>Submit</button>
                    </div>
                </div>
            </div>

        </div>
    </form>
</body>
</html>