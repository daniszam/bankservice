<#import "macros/leftnavBar.ftl" as leftNavbar>
<#import "macros/аddBalance.ftl" as balance>
<html>
<head>
    <title>Add Balance</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
    <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates|Comfortaa|Quicksand|IBM+Plex+Sans" rel="stylesheet">

    <link rel="stylesheet" href="/resources/css/bankassistant.css">
    <link href="/resources/css/addBalance.css" rel="stylesheet">
    <link href="/resources/css/userPage.css" rel="stylesheet">
    <link href="/resources/css/myspace.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="/resources/JavaScript/navbarCloseFunction.js"></script>
    <script src="/resources/JavaScript/chandgeColor.js"></script>
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

            <div class="add item">
                <div class="add item inputs">
                    <div class="name box">
                        <select id="balance" name="user_balance">
                            <#list balances as item>
                                <option value="${item.id}">${item.name}</option>
                            </#list>
                        </select>

                        <div id="tt4" class="icon material-icons">help_outline</div>
                        <div class="mdl-tooltip" data-mdl-for="tt4">
                            Balance
                        </div>
                    </div>
                    <div class="support box">
                        <input  name="upDate" placeholder="  upDate" class="textbox-n" type="text"
                                onfocus="(this.type='date')" id="date">
                        <div id="tt3" class="icon material-icons">help_outline</div>
                        <div class="mdl-tooltip" data-mdl-for="tt3">
                            Date of your credit
                        </div>

                        <input  name="endDate" placeholder="  endDate" class="textbox-n" type="text"
                                onfocus="(this.type='date')" id="endDate">
                        <div id="tt3" class="icon material-icons">help_outline</div>
                        <div class="mdl-tooltip" data-mdl-for="tt3">
                            Date of your credit
                        </div>

                        <input type="text" pattern="[0-9]*(\.[0-9]+)?" id="sum" name="sum" placeholder="  count">
                        <div id="tt1" class="icon material-icons">help_outline</div>
                        <div class="mdl-tooltip" data-mdl-for="tt1">
                            Insert count of money on your credit
                        </div>
                    </div>
                </div>
            </div>

            <button type="submit" value="create" >
                <img src="/resources/sign.png">
            </button>
        </form>
    </div>
</div>
</body>
</html>
