<%--
  Created by IntelliJ IDEA.
  User: danis_zam
  Date: 22/11/2018
  Time: 22:04
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
    <script src="<c:url value="/JavaScript/getCity.js"/>"></script>
    <script src="<c:url value="/JavaScript/navbarCloseFunction.js"/>"></script>

    <title>Edit</title>
</head>
<body>





<mySpace:leftNavBar/>

<div class="card-list" style="z-index: 999;">
    <form style="z-index: 999">
        <mySpace:country countrys="${countrys}" citys="${citys}"/>
    </form>
</div>

</body>
</html>
