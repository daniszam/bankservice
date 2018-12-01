<%--
  Created by IntelliJ IDEA.
  User: danis_zam
  Date: 08/11/2018
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="signUp" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sign Up</title>

    <script src="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.2/jquery.min.js"/>"></script>

    <link href="<c:url value="/CSS/signCard.css"/>" rel="stylesheet">
    <link href="<c:url value="/CSS/bankassistant.css"/>" rel="stylesheet">
    <script src="<c:url value="/JavaScript/chandgeColor.js"/> " rel="stylesheet"></script>
    <link href="https://fonts.googleapis.com/css?family=Comfortaa" rel="stylesheet">
</head>
<body>
 <a class="title-name" style="text-decoration: none; position: absolute; z-index: 1; left:1%" href="<c:url value="/home"/>"><h2>YOUR FINANCE ASSISTANT</h2></a>
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
            <signUp:signForm data="true" email="true">
            </signUp:signForm>
        </form>

    </div>
</div>
<div class="auth link">
    <form style="width: 100%" action="<c:url value="/vkAuth"/>">
        <button type="submit">
            <input type="hidden" name="vkauth" id="vkauth" value="vkauth">
            <img src="<c:url value="/resources/vk-logo.png"/>" style="width: 100%">
        </button>
    </form>
</div>
</body>
</html>