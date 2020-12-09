<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorator="~{index(page_title='Account - Add New')}">
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

                                <ul>
                                    <li><span>Account:</span><span th:text="${account.getNumber()}"></span> </li>
                                    <li><span>Currency:</span><span th:text="${account.getCurrency().getName()}"></span> </li>
                                    <li><span>Balance:</span><span th:text="${account.getBalance()}"></span></li>
                                </ul>
                                <div class="form-group row">
                                    <label class="col-md-2 col-form-label text-md-right font-weight-bold" for="Balance">Balance</label>
                                    <div class="col-md-10">
                                        <input type="number" name="Balance" class="form-control" required id="Balance" th:value="${param.containsKey('Balance')}?${param.Balance}" min="0"  step="0.01"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-md-10 offset-md-2 text-left">
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="Charge" id="charge-yes" value="1" checked>
                                            <label class="form-check-label" for="charge-yes">Charge</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="Charge" id="charge-no" value="0">
                                            <label class="form-check-label" for="charge-no">Withdraw</label>
                                        </div>
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