<%@tag description="Navbar Tag" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:set var="user" value="${user}"/>

<header class="mdl-layout__header mdl-layout__header--transparent">
    <div class="mdl-layout__header-row">

        <span class="mdl-layout-title">Menu</span>

        <div class="mdl-layout-spacer"></div>

        <c:if test="${empty user}">
        <nav class="mdl-navigation">
            <a class="mdl-navigation__link" href="">Login</a>
            <a class="mdl-navigation__link" href="">Sign Up</a>
        </nav>
        </c:if>
    </div>
</header>
<div class="title-name" style="text-align: center">
    <h1>YOUR FINANCE ASSISTANT</h1>
</div>

<c:if test="${not empty user}">
    <div class="mdl-layout__drawer">
        <span class="mdl-layout-title">Bank assistant</span>
        <nav class="mdl-navigation">
            <a class="mdl-navigation__link" href="/mySpace">My Space</a>
            <a class="mdl-navigation__link" href="">Friends</a>
            <a class="mdl-navigation__link" href="">${user.firstName}</a>
            <a class="mdl-navigation__link" href="">Log Out</a>
        </nav>
    </div>
</c:if>
<main class="mdl-layout__content">
</main>