<#import "macros/leftnavBar.ftl" as nav>
<#import "macros/circleStatistik.ftl" as circle>
<#import "macros/balance.ftl" as balance>
<#import "macros/item.ftl" as item>
<html>
<head>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
    <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>

    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates|Comfortaa|Quicksand|IBM+Plex+Sans" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">

    <link href="/CSS/userPage.css" rel="stylesheet">
    <link href="/CSS/myspace.css" rel="stylesheet">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="/JavaScript/postAjax.js"></script>

    <link href="/CSS/bankassistant.css" rel="stylesheet">
    <script src="/JavaScript/chandgeColor.js" rel="stylesheet"></script>
    <title>My Space</title>
</head>
<body>

<@nav.leftNavBar/>

<div class="card-list">
    <@circle.circle/>
    <div class="card-list help">
        <div class="demo-list-action mdl-list"  >
            <form id="balance_type">
               <@balance.balance checkbox=true/>
            </form>
        </div>
        <@item.item/>
    </div>
</div>
<button type="submit" value="submit" form="items" id="send" style="width: 60px;
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
