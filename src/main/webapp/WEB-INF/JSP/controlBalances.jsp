<%--
  Created by IntelliJ IDEA.
  User: danis_zam
  Date: 13/11/2018
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mySpace" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>control</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
    <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>

    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates|Comfortaa|Quicksand|IBM+Plex+Sans" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">

    <link href="<c:url value="/CSS/bankassistant.css"/>" rel="stylesheet">
    <link href="<c:url value="/CSS/userPage.css"/>" rel="stylesheet">
    <link href="<c:url value="/CSS/myspace.css"/>" rel="stylesheet">
    <link href="<c:url value="/CSS/controlBalance.css"/>" rel="stylesheet">


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<c:url value="/JavaScript/increaseBalancePost.js" />"></script>
    <title>My Space</title>
</head>
<body>

<mySpace:leftNavBar/>

<div class="card-list" style="z-index: 999">
    <div class="card-list help" style="z-index: 999">
        <form action="/addBalance" id="update_balance" style="z-index: 999">
            <mySpace:balance checkbox="false"/>
        </form>
    </div>
</div>
<button type="submit" value="submit" form="update_balance" id="send" style="width: 60px;
    height: 60px;
    position: absolute;
    right: 5%;
    bottom: 5%;
    border: solid;
    background: none;
    border-width: 2px;
    border-radius: 50%;">Send</button>
</body>
</html>
