<!DOCTYPE html>
<html lang="en">
<head th:fragment="headTag">
    <link rel="stylesheet" href="../../assets/css/lib/bootstrap.min.css" th:href="@{/assets/css/lib/bootstrap.min.css}"/>
    <link rel="stylesheet" href="../../assets/css/lib/bootstrap-grid.min.css" th:href="@{/assets/css/lib/bootstrap-grid.min.css}"/>
    <link rel="stylesheet" href="../../assets/css/lib/bootstrap-reboot.min.css" th:href="@{/assets/css/lib/bootstrap-reboot.min.css}"/>
    <link rel="stylesheet" href="../../assets/css/lib/bootstrap-colorpicker.min.css" th:href="@{/assets/css/lib/bootstrap-colorpicker.min.css}"/>
    <link rel="stylesheet" href="../../assets/css/lib/font-awesome-pro.min.css" th:href="@{/assets/css/lib/font-awesome-pro.min.css}"/>
    <link rel="stylesheet" href="../../assets/tables/dataTables.bootstrap4.min.css" th:href="@{/assets/tables/dataTables.bootstrap4.min.css}"/>
    <link rel="stylesheet" href="../../assets/css/style.css" type="text/css" th:href="@{/assets/css/style.css}"/>

<script type="text/javascript" src="../../assets/js/lib/jquery.min.js" th:src="@{/assets/js/lib/jquery.min.js}"></script>
<script type="text/javascript" src="../../assets/js/lib/bootstrap.min.js"  th:src="@{/assets/js/lib/bootstrap.min.js}"></script>
<script type="text/javascript" src="../../assets/tables/datatables.min.js"  th:src="@{/assets/tables/datatables.min.js}"></script>

<script type="text/javascript" src="../../assets/js/functions.js"  th:src="@{/assets/js/functions.js}"></script>
    <script type="text/javascript">

            $(document).ready(function(){
                if($('.table').length>0) {
                    customer_tables.init_table('.table');
                }

            });

    </script>
</head>
</html>
