<#import "macros/leftnavBar.ftl" as nav>
<#import "macros/balance.ftl" as balance>
<html>
<head>
    <title>control</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
    <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>

    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates|Comfortaa|Quicksand|IBM+Plex+Sans" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">

    <link href="/CSS/userPage.css" rel="stylesheet">
    <link href="/CSS/myspace.css" rel="stylesheet">
    <link href="/CSS/controlBalance.css" rel="stylesheet">


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="/JavaScript/increaseBalancePost.js"></script>
    <link href="/CSS/bankassistant.css" rel="stylesheet">
            <script src="/JavaScript/chandgeColor.js" rel="stylesheet"></script>
    <title>My Space</title>
</head>
<body>

<@nav.leftNavBar></@nav.leftNavBar>

<div class="card-list" style="z-index: 999">
    <div class="card-list help" style="z-index: 999">
        <form action="/addBalance" id="update_balance" style="z-index: 999">
           <@balance.balance checkbox=false></@balance.balance>
        </form>
    </div>
</div>
<button type="submit" value="submit" form="update_balance" id="send" style="width: 60px;
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
