<%--
  Created by IntelliJ IDEA.
  User: danis_zam
  Date: 11/11/2018
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="signUp" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Add Balance</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
    <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates|Comfortaa|Quicksand|IBM+Plex+Sans" rel="stylesheet">


    <link href="<c:url value="/CSS/bankassistant.css"/>" rel="stylesheet">
    <link href="<c:url value="/CSS/addBalance.css"/>" rel="stylesheet">
    <link href="<c:url value="/CSS/userPage.css"/>" rel="stylesheet">
    <link href="<c:url value="/CSS/myspace.css"/>" rel="stylesheet">

</head>
<body>

<div class="demo-layout mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header"/>
<div aria-expanded="false" role="button" tabindex="0" class="mdl-layout__drawer-button"></div>

<signUp:leftNavBar/>

<div class="add">
    <div class="add card" style="width: 860px; height: 540px; border-radius: 60px">
        <div class="background"></div>
        <div class="title-name">
            <h3>YOUR FINANCE ASSISTANT</h3>
        </div>
        <form id="singUp" method="post" style="width: 100%; height: 100%">
           <signUp:addBalance/>
            <button type="submit" value="create" >
                <img src="<c:url value="/resources/sign.png"/>">
            </button>
        </form>

    </div>
</div>
</body>
</html>
