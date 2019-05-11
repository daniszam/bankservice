<#assign form=JspTaglibs["http://www.springframework.org/tags/form"] />
<#import "macros/signForm.ftl" as sign>
<!DOCTYPE html>
<html>
<head>
    <title>Sign Up</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>

    <link href="/resources/css/signCard.css" rel="stylesheet">
            <link href="/resources/css/bankassistant.css" rel="stylesheet">
            <script src="/resources/JavaScript/chandgeColor.js" rel="stylesheet"></script>
    <link href="https://fonts.googleapis.com/css?family=Comfortaa" rel="stylesheet">
</head>
<body>
<a class="title-name" style="text-decoration: none; position: absolute; z-index: 1; left:1%" href="/home"><h2>YOUR FINANCE ASSISTANT</h2></a>
<div class="signUp">
    <div class="signUp card" style="width: 860px; height: 540px; border-radius: 60px">
        <div class="background"></div>
        <div class="title-name" style="position: absolute; left: 5%;top: 5%">
            <h2>YOUR FINANCE ASSISTANT</h2>
        </div>
        <div class="logo" style="position: absolute; left: 70%; right: 3%; top: 5%">
            <img src="/resources/payway.png" style="width: 100%">
        </div>
        <@form.form method="post" modelAttribute="user">
            <@sign.signForm data=true email=true remember=false></@sign.signForm>
        </@form.form>

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