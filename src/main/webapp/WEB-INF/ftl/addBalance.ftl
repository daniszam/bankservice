<#import "macros/leftnavBar.ftl" as leftNavbar>
<#import "macros/аddBalance.ftl" as balance>
<html>
<head>
    <title>Add Balance</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
    <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates|Comfortaa|Quicksand|IBM+Plex+Sans" rel="stylesheet">

    <link rel="stylesheet" href="/CSS/bankassistant.css">
    <link href="/CSS/addBalance.css" rel="stylesheet">
    <link href="/CSS/userPage.css" rel="stylesheet">
    <link href="/CSS/myspace.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="/JavaScript/navbarCloseFunction.js"></script>
    <script src="/JavaScript/chandgeColor.js"></script>
</head>
<body>


<@leftNavbar.leftNavBar></@leftNavbar.leftNavBar>
<div class="add">
    <div class="add card" style="width: 860px; height: 540px; border-radius: 60px">
        <div class="background"></div>
        <div class="title-name">
            <h3>YOUR FINANCE ASSISTANT</h3>
        </div>
        <form id="singUp" method="post" style="width: 100%; height: 100%">
            <@balance.addBalance/>
            <button type="submit" value="create" >
                <img src="/resources/sign.png">
            </button>
        </form>
    </div>
</div>
</body>
</html>
