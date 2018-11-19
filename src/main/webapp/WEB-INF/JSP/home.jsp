<%--
  Created by IntelliJ IDEA.
  User: danis_zam
  Date: 04/11/2018
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
    <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>

    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates|Comfortaa|Quicksand|IBM+Plex+Sans" rel="stylesheet">
    <link href="<c:url value="/CSS/bankassistant.css"/>" rel="stylesheet">


    <title>Money assistance</title>

</head>
<body>




<div class="demo-layout-transparent mdl-layout mdl-js-layout"/>


<nav:navbar/>
<h6 style="position: absolute;top: 10%; right: 10%">
    <a href="/home?thema=black">Dark</a>|<a href="/home?thema=standart">Standart</a></a>
</h6>

<style>
    body{
        background: ${thema};
    <c:if test="${thema eq 'standart'}">
        background: #99CCFF;
    </c:if>
    }
    .mdl-layout__header--transparent.mdl-layout__header--transparent{
        background: ${thema};
    <c:if test="${thema eq 'standart'}">
        background: #99CCFF;
    </c:if>
    }
</style>
<div class="card-list" style="width: 90%; margin-left: 5%; margin-top:2%; margin-bottom: 5%" >
    <div class="demo-card-square mdl-card mdl-shadow--2dp">
        <img style="width: 350px; height: 300px" src="https://freefrontend.com/assets/img/css-carousels/pure-css-carousel.png">
        <div class="mdl-card__title mdl-card--expand">
            <h2 class="mdl-card__title-text">Salary</h2>
        </div>
        <div class="mdl-card__supporting-text">
            Add salaries to automatically increase your finances.
        </div>
    </div>
    <div class="demo-card-square mdl-card mdl-shadow--2dp">
        <img style="width: 350px; height: 300px" src="http://www.financedais.com/wp-content/uploads/2016/04/control-your-finances.jpg">
        <div class="mdl-card__title mdl-card--expand">
            <h2 class="mdl-card__title-text">Control</h2>
        </div>
        <div class="mdl-card__supporting-text">
            Сontrol your finances
        </div>
        <div class="mdl-card__actions mdl-card--border">
        <c:if test="${empty user}">
            <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect" href="/signUp">
                Sign Up
            </a>
        </c:if>
        </div>
    </div><div class="demo-card-square mdl-card mdl-shadow--2dp" >
    <img style="width: 350px; height: 300px" src="<c:url value="/resources/circle.png"/>">
    <div class="mdl-card__title mdl-card--expand">
        <h2 class="mdl-card__title-text">Check your statistik</h2>
    </div>
    <div class="mdl-card__supporting-text">
        Pie chart clearly shows your expenses.
    </div>
    <div class="mdl-card__actions mdl-card--border">
        <c:if test="${empty user}">
        <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect" href="/signUp">
            Sign Up
        </a>
        </c:if>
    </div>
</div>

</div>



</body>
</html>
