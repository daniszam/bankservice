<%@tag description="Navbar Tag" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:set var="user" value="${user}"/>

<header class="mdl-layout__header mdl-layout__header--transparent">
    <div class="mdl-layout__header-row">
        <c:if test="${not empty user}">
            <a class="mdl-navigation__link" href="<c:url value="/mySpace"/>" style="text-underline: none">My Space</a>
        <div class="mdl-layout-spacer"></div>
        </c:if>
        <c:if test="${empty user}">
        <nav class="mdl-navigation">
            <a class="mdl-navigation__link" href="<c:url value="/signIn"/>">Login</a>
            <a class="mdl-navigation__link" href="<c:url value="/signUp"/>">Sign Up</a>
        </nav>
        </c:if>
        <c:if test="${not empty user}">
            <nav class="mdl-navigation">
            <a class="mdl-navigation__link" href="<c:url value="/logout"/>">Log out</a>
            </nav>
        </c:if>
    </div>
</header>
<div class="title-name" style="text-align: center">
    <h1>YOUR FINANCE ASSISTANT</h1>
</div>


<div class="mdl-layout__drawer">
    <span class="mdl-layout-title">Bank assistant</span>
    <nav class="mdl-navigation">
    <c:if test="${not empty user}">
        <a class="mdl-navigation__link" href="<c:url value="/mySpace"/>">My Space</a>
        <a class="mdl-navigation__link" href="">Friends</a>
        <a class="mdl-navigation__link" href="">${user.firstName}</a>
        <a class="mdl-navigation__link" href="">Log Out</a>
    </c:if>
    <c:if test="${empty user}">
        <a class="mdl-navigation__link" href="<c:url value="/signIn"/>">Sign In</a>
        <a class="mdl-navigation__link" href="<c:url value="/signUp"/>">Sign up</a>
    </c:if>
    </nav>
</div>
<div aria-expanded="false" role="button" tabindex="0" class="mdl-layout__drawer-button" id="login_button">
    <i class="material-icons"></i>
</div>
<main class="mdl-layout__content">
</main>