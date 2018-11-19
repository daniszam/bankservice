<%@tag description="Navbar Tag" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:set var="user" value="${user}"/>
<div class="demo-drawer mdl-layout__drawer" aria-hidden="false">
    <header class="demo-drawer-header">
        <img src="${user.img}" class="demo-avatar">
        <div class="demo-avatar-dropdown">
            <div style="width: 90%">
            <span style="margin-left: 10%">${user.email}</span>
            </div>
            <div class="mdl-layout-spacer"></div>
            <button id="accbtn" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
                <i class="material-icons" role="presentation">arrow_drop_down</i>
                <span class="visuallyhidden">Accounts</span>
            </button>
            <ul class="mdl-menu mdl-menu--bottom-right mdl-js-menu mdl-js-ripple-effect" for="accbtn">
                <li class="mdl-menu__item">${user.email}</li>
                <li class="mdl-menu__item"><i class="material-icons">add</i>Add another account...</li>
            </ul>
        </div>
    </header>
    <nav class="demo-navigation mdl-navigation">
        <a class="mdl-navigation__link" href="/home"><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">home</i>home</a>
        <a class="mdl-navigation__link" href="/mySpace"><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">inbox</i>my space</a>
        <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">delete</i>Trash</a>
        <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">report</i>Spam</a>
        <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">forum</i>Chat</a>
        <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">flag</i>Updates</a>
        <a class="mdl-navigation__link" href="/controlBalance"><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">local_offer</i>balances</a>
        <a class="mdl-navigation__link" href="/addBalance"><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">shopping_cart</i>add balance</a>
        <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">people</i>Friends</a>
        <div class="mdl-layout-spacer"></div>
        <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">help_outline</i><span class="visuallyhidden">Help</span></a>
    </nav>
</div>