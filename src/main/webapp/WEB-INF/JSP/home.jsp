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


    <title>Money aasistance</title>

</head>
<body>




<div class="demo-layout-transparent mdl-layout mdl-js-layout"/>


<nav:navbar/>
<h6 style="position: absolute;bottom: 5%; right: 5%">
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
<div class="card-list" style="width: 90%; margin-left: 5%; margin-top:2%;" >
    <div class="demo-card-square mdl-card mdl-shadow--2dp">
        <img src="https://freefrontend.com/assets/img/css-carousels/pure-css-carousel.png">
        <div class="mdl-card__title mdl-card--expand">
            <h2 class="mdl-card__title-text">Update</h2>
        </div>
        <div class="mdl-card__supporting-text">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit.
            Aenan convallis.
        </div>
        <div class="mdl-card__actions mdl-card--border">
            <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
                View Updates
            </a>
        </div>
    </div>
    <div class="demo-card-square mdl-card mdl-shadow--2dp">
        <img src="https://freefrontend.com/assets/img/css-carousels/pure-css-carousel.png">
        <div class="mdl-card__title mdl-card--expand">
            <h2 class="mdl-card__title-text">Update</h2>
        </div>
        <div class="mdl-card__supporting-text">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit.
            Aenan convallis.
        </div>
        <div class="mdl-card__actions mdl-card--border">
            <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
                View Updates
            </a>
        </div>
    </div><div class="demo-card-square mdl-card mdl-shadow--2dp" >
    <img src="https://freefrontend.com/assets/img/css-carousels/pure-css-carousel.png">
    <div class="mdl-card__title mdl-card--expand">
        <h2 class="mdl-card__title-text">Update</h2>
    </div>
    <div class="mdl-card__supporting-text">
        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
        Aenan convallis.
    </div>
    <div class="mdl-card__actions mdl-card--border">
        <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
            View Updates
        </a>
    </div>
</div>

</div>



</body>
</html>
