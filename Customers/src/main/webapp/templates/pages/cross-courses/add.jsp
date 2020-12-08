<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorator="~{index(page_title='Cross-Course - Add New')}">
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
                        <h5>Main Settings <small> Currency: <span th:text="${currency.getName()}"></span></small></h5>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col">

                                <div class="form-group row">
                                    <label class="col-md-2 col-form-label text-md-right font-weight-bold" for="CrossCurrency">Cross Currency</label>
                                    <div class="col-md-10">
                                        <select name="CurrencyFrom" required id="CrossCurrency" class="form-control">
                                            <option th:each="currency : ${currencies}" th:value="${currency.getId()}" th:text="${currency.getName()}" th:selected="${currency.getId()} == param.CrossCurrency"></option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-md-2 col-form-label text-md-right font-weight-bold" for="Amount">Cross-Course</label>
                                    <div class="col-md-10">
                                        <input type="number" name="Amount" class="form-control" required id="Amount" th:value="${param.containsKey('Amount')}?${param.Amount}" min="0"  step="0.0001"/>
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
                        <h5>Publish</h5>
                    </div>
                    <div class="card-body">
                        <button type="submit" class="btn btn-block btn-success btn-md"><i class="fal fa-save"></i>Add New</button>
                    </div>
                </div>
            </div>

        </div>
    </form>
</body>
</html>