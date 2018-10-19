<%--
  Created by IntelliJ IDEA.
  User: danis_zam
  Date: 09/10/2018
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <style>
        body {
            font-family: 'Poiret One', cursive; !important;
            background: #e3f2fd !important;
        }

        input[type=date]{
            background: #fff;
            border: none;
            border-radius: 8px;
            font-size: 27px;
            font-family: 'Raleway', sans-serif;
            height: 72px;
            width: 99.5%;
            margin-bottom: 10px;
            opacity: 1;
            text-indent: 10px;
            transition: all .2s ease-in-out;
        }

    </style>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="https://fonts.googleapis.com/css?family=Racing Sans One|Poiret One" rel="stylesheet">
    <link type="text/css" href="${pageContext.request.contextPath}/CSS/sign.css" rel="stylesheet">

</head>
<body>

<h1>${locale.get("signup.login.message")}<a href="/signUp?lang=Ru">RU</a>|<a href="/signUp?lang=En">EN</a> </h1>
<form id="singUp" method="post">
    <label id="Sign">${locale.get("signup.signup")}</label>
    <div id="wrapper">
        <input id="firstName" name="firstName" placeholder="${locale.get("signup.firstName")}" type="text">
        <input id="lastName" name="lastName" placeholder="${locale.get("signup.lastName")}" type="text">
        <input id="email" name="email" placeholder="${locale.get("signup.email")}" type="text">
        <input id="password" name="password" placeholder="${locale.get("signup.password")}" type="password">
        <input id="repassword" name="repassword" placeholder="${locale.get("signup.rePassword")}" type="password">
        <input id="birthday" name="birthday" placeholder="${locale.get("signup.date")}" class="textbox-n" type="text" onfocus="(this.type='date')"  id="date">
        <input name="sex" id="Male" type="radio" value="Male">
        <label for="Male" style="text-align: left">${locale.get("signup.male")}</label>
        <input name="sex" id="Female" type="radio" value="Female">
        <label for="Female">${locale.get("signup.female")}</label>
    </div>

    <button id="submit" type="submit" value="${locale.get("signup.signup")}">
    <span>
      ${locale.get("signup.signup")}
    </span>
    </button>
</form>
<div id="hint"><b>Click on the tabs</b></div>



<div>
    <h1 id="checkBox" style="margin-left: 2%"></h1>
</div>


<nav class="navbar navbar-expand-lg navbar-default fixed-bottom" style="background-color: #e3f2fd; position: fixed;bottom: 2%; left: 2%;" >
    <div class="container-fluid">
        <p><b id="zd" class="text-left" style="color: black;">&copy; 2018 Zamaliev Danis</b></p>
        <a href="#" class="btn-right" role="button" aria-disabled="true" style="color: black;"><b>To Site</b></a>
    </div>
</nav>

</body>
</html>
