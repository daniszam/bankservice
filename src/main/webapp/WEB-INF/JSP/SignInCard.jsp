<%--
  Created by IntelliJ IDEA.
  User: danis_zam
  Date: 11/11/2018
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="signUp" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Sign In</title>
    <link href="<c:url value="/CSS/bankassistant.css"/>" rel="stylesheet">
    <link href="<c:url value="/CSS/signCard.css"/>" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Comfortaa" rel="stylesheet">
</head>
<body>
<div class="signUp">
    <div class="signUp card" style="width: 860px; height: 540px; border-radius: 60px">
        <div class="background"></div>
        <div class="title-name" style="position: absolute; left: 5%;top: 5%">
            <h2>YOUR FINANCE ASSISTANT</h2>
        </div>
        <div class="logo" style="position: absolute; left: 70%; right: 3%; top: 5%">
            <img src="<c:url value="/resources/payway.png"/>" style="width: 100%">
        </div>
        <form id="singUp" method="post" style="width: 100%; height: 100%">
            <signUp:signForm  email="true">
            </signUp:signForm>
        </form>

    </div>
</div>
<div class="auth link">
    <form method="post" style="width: 100%">
        <button type="submit">
            <input type="hidden" name="vkauth" id="vkauth" value="vkauth">
            <img src="<c:url value="/resources/vk-logo.png"/>" style="width: 100%">
        </button>
    </form>
</div>
</body>
</html>