<#assign form=JspTaglibs["http://www.springframework.org/tags/form"] />
<#import "macros/signForm.ftl" as sign>
<!DOCTYPE html>
<html>
<head>
    <title>Sign In</title>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
    <link href="/resources/css/signCard.css" rel="stylesheet">
            <link href="/resources/css/bankassistant.css" rel="stylesheet">
            <script src="/resources/JavaScript/chandgeColor.js" rel="stylesheet"></script>
    <link href="https://fonts.googleapis.com/css?family=Comfortaa" rel="stylesheet">
</head>
<body>
<a class="title-name" style="text-decoration: none; position: absolute; z-index: 1; left:1%;" href="/home"><h2>YOUR FINANCE ASSISTANT</h2></a>
<div class="signUp">
    <div class="signUp card" style="width: 860px; height: 540px; border-radius: 60px">
        <div class="background"></div>
        <div class="title-name" style="position: absolute; left: 5%;top: 5%">
            <h2>YOUR FINANCE ASSISTANT</h2>
        </div>
        <div class="logo" style="position: absolute; left: 70%; right: 3%; top: 5%">
            <img src="/resources/payway.png" style="width: 100%">
        </div>

        <form method="post" style="width: 100%; height: 100%">

            <input id="email" name="email" placeholder="email"/>

            <input id="password" name="password" type="password" placeholder="password"/>

            <div style="position: absolute; bottom: 5%;left: 5%">
                <input type="checkbox" name="remember" id="save_me">
                <label for="remember" style="text-align: left">Remember me</label>
            </div>

            <div class="logo" style="background: white; position: absolute; left: 70%; right: 3%; top: 80%">
                <button id="submit" type="submit" value="Sign Up" accesskey="enter">
                    <img src="/resources/visa.png" style="width: 100%">
                </button>
            </div>
        </form>
    </div>
</div>
<div class="auth link">
    <form style="width: 100%" action="/vkAuth">
        <button type="submit">
            <input type="hidden" name="vkauth" id="vkauth" value="vkauth">
            <img src="/resources/vk-logo.png" style="width: 100%">
        </button>
    </form>
</div>
</body>
</html>