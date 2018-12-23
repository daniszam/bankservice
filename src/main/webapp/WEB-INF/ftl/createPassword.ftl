<#import "macros/signForm.ftl" as sign>
<html>
<head>
    <title>Create Password</title>
    <head>
        <title>Sign Up</title>

        <link href="https://fonts.googleapis.com/css?family=Comfortaa" rel="stylesheet">
        <link href="/CSS/signCard.css" rel="stylesheet">
        <link href="/CSS/bankassistant.css" rel="stylesheet">
        <script src="/JavaScript/chandgeColor.js" rel="stylesheet"></script>
        <style>
            #password {
                width: 80%;
                margin: 0;
                position: absolute;
            }

            label {
                position: relative;
                margin-top: 40%;
                margin-left: -25%;
                font-size: xx-large;
            }
        </style>
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

        <form id="singUp" method="post"
              style="width: 100%; height: 100%; display: flex; align-items: center; justify-content: center">
                <@sign.signForm data=false email=false remember=false/>
            <label>${email}</label>
        </form>

    </div>
</div>
</body>
</html>
