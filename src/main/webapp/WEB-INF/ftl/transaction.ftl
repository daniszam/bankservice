<#import "macros/leftnavBar.ftl" as nav>
<html>
<head>
    <title>control</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
    <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>

    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates|Comfortaa|Quicksand|IBM+Plex+Sans" rel="stylesheet">


    <link href="/resources/css/bankassistant.css" rel="stylesheet">
    <link href="/resources/css/userPage.css" rel="stylesheet">
    <link href="/resources/css/myspace.css" rel="stylesheet">
    <link href="/resources/css/controlBalance.css" rel="stylesheet">


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="/resources/JavaScript/increaseBalancePost.js"></script>
    <script src="/resources/JavaScript/chandgeColor.js" rel="stylesheet"></script>
    <title>My Space</title>
</head>
<body>

<@nav.leftNavBar></@nav.leftNavBar>

<div class="card-list" style="z-index: 999">
    <div class="card-list help" style="z-index: 999">
        <table border="0" style="margin-top: 10px;">
            <caption>Таблица транзакци</caption>
            <tr>
                <th>Price</th>
                <th>Balance</th>
                <th>Category</th>
                <th>Date</th>
            </tr>
           <#list transaction as item>
               <tr>
                   <th>${item.price}</th>
                   <th>${item.balanceName}</th>
                   <th>${item.category}</th>
                   <th>${item.date}</th>
               </tr>
           </#list>
        </table>
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