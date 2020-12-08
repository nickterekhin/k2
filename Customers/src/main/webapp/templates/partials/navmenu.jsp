<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

<body>

<ul th:fragment="menu" class="sidenav metismenu mb-5">
   <li class="parent">
      <a class="" href="/"><i class="fal fa-home"></i>Home</a>
   </li>
   <li class="parent">
      <a class="" th:href="@{/customers}"><i class="fal fa-users-crown"></i>Customers</a>
   </li>
   <li class="parent">
      <a class="" th:href="@{/currencies}"><i class="fal fa-dollar-sign"></i>Currencies</a>
   </li>
</ul>

</body>

</html>