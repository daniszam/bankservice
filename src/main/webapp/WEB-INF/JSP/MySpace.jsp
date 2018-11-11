<%--
  Created by IntelliJ IDEA.
  User: danis_zam
  Date: 09/11/2018
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mySpace" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
    <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>

    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates|Comfortaa|Quicksand|IBM+Plex+Sans" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">
    <link href="<c:url value="/CSS/bankassistant.css"/>" rel="stylesheet">
    <link href="<c:url value="/CSS/userPage.css"/>" rel="stylesheet">
    <link href="<c:url value="/CSS/myspace.css"/>" rel="stylesheet">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<c:url value="/JavaScript/postAjax.js" />"></script>
    <title>My Space</title>
</head>
<body>

<div class="demo-layout mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header"/>
<div aria-expanded="false" role="button" tabindex="0" class="mdl-layout__drawer-button"></div>

<mySpace:leftNavBar/>

<div class="card-list">
    <mySpace:circleStatistik/>
        <div class="card-list help">
            <mySpace:balance/>
            <mySpace:item/>
        </div>
</div>
<button type="submit" value="submit" form="items" id="send" style="width: 60px;
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
