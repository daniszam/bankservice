<#macro leftNavBar>
<div class="mdl-layout__container" style="top: 0;">
    <div class="demo-layout mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header" style="height: -webkit-fill-available">
        <header class="demo-header mdl-layout__header" style="box-shadow: none; background: none">
            <div aria-expanded="false" role="button" tabindex="0" class="mdl-layout__drawer-button"><i class="material-icons" style="margin-top: 10px;"></i></div>
        </header>

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
                <a class="mdl-navigation__link" href="/controlBalance"><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">local_offer</i>balances</a>
                <a class="mdl-navigation__link" href="/addBalance"><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">shopping_cart</i>add balance</a>
                <div class="mdl-layout-spacer"></div>
            </nav>
        </div>
    </div>
</div>
</#macro>