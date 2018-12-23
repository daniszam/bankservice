<#import "macros/leftnavBar.ftl" as nav>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
    <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>

    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates|Comfortaa|Quicksand|IBM+Plex+Sans" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">

    <link href="/CSS/userPage.css" rel="stylesheet">
    <link href="/CSS/myspace.css" rel="stylesheet">

    <link href="/CSS/bankassistant.css" rel="stylesheet">
    <script src="/JavaScript/chandgeColor.js" rel="stylesheet"></script>
    <title>Category</title>

</head>
<body>
    <form method="POST" style="margin-left: 40%; margin-top: 5%; z-index: 1001">
        <div class="mdl-textfield mdl-js-textfield" style="z-index: 999">
            <input class="mdl-textfield__input" type="text" id="sample1" name="category" style="z-index:999;">
            <label class="mdl-textfield__label" for="sample1">Text...</label>
        </div>
        <button type="submit" style="z-index: 1001">Submit</button>
    </form>
    <#if categories??>
        <table>
        <tr><th>Category</th><th>Color</th></tr>
        <#list categories as item>
            <tr><td>${item.name}</td><td style="color: rgb(${item.color.red},${item.color.green},${item.color.blue})">(${item.color.red},${item.color.green},${item.color.blue})</td></tr>
        </#list>
        </table>
    </#if>
</body>
</html>
