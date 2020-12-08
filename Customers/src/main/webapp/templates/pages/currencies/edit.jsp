<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorator="~{index(page_title='Currency - Edit One')}">
<head>
    <title>Currency - Edit One</title>
</head>
<body>
<div layout:fragment="content">
    <form class="form-horizontal" method="POST" action="">
        <div class="row">
            <div class="col-xl-9 col-lg-8">
                <div class="card ">
                    <div class="card-header">
                        <h5>Main Settings</h5>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col">

                                <div class="form-group row">
                                    <label class="col-md-2 col-form-label text-md-right font-weight-bold" for="Name">Currency Short Name</label>
                                    <div class="col-md-10">
                                        <input type="text" name="Name" class="form-control" id="Name" value="" th:value="${currency.getName()}" maxlength="3" required/>
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
                        <h5>Update</h5>
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