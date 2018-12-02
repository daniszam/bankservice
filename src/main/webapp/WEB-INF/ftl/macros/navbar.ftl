<#macro navbar>
<header class="mdl-layout__header mdl-layout__header--transparent">
    <div class="mdl-layout__header-row">
    <#if user??>
        <a class="mdl-navigation__link" href="/mySpace" style="text-underline: none">My Space</a>
        <div class="mdl-layout-spacer"></div>
    <#else>
        <nav class="mdl-navigation">
            <a class="mdl-navigation__link" href="/signIn">Login</a>
            <a class="mdl-navigation__link" href="/signUp">Sign Up</a>
        </nav>
    </#if>
    <#if user??>
        <nav class="mdl-navigation">
            <a class="mdl-navigation__link" href="/logout">Log out</a>
        </nav>
    </#if>
    </div>
</header>
<div class="title-name" style="text-align: center">
    <h1>YOUR FINANCE ASSISTANT</h1>
</div>


<div class="mdl-layout__drawer">
    <span class="mdl-layout-title">Bank assistant</span>
    <nav class="mdl-navigation">
    <#if user??>
        <a class="mdl-navigation__link" href="/mySpace">My Space</a>
        <a class="mdl-navigation__link" href="">Friends</a>
        <a class="mdl-navigation__link" href="">${user.firstName}</a>
        <a class="mdl-navigation__link" href="">Log Out</a>
    <#else>
        <a class="mdl-navigation__link" href="/signIn">Sign In</a>
        <a class="mdl-navigation__link" href="/signUp">Sign up</a>
    </#if>
    </nav>
</div>
<div aria-expanded="false" role="button" tabindex="0" class="mdl-layout__drawer-button" id="login_button">
    <i class="material-icons"></i>
</div>
<main class="mdl-layout__content">
</main>
</#macro>