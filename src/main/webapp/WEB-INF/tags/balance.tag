<%@tag description="Navbar Tag" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="demo-list-action mdl-list"  >
    <form id="balance_type">
    <c:forEach var="item" items="${user.balances}">
    <div class="mdl-list__item">
    <span class="mdl-list__item-primary-content">
        <label for="${item.getClass().simpleName}.${item.id}" class="balance"><img src="<c:url value="/resources/bankcard.png"/>"></label>
         <input type="checkbox" name="${item.getClass().simpleName}" id="${item.getClass().simpleName}.${item.id}" class="balance">
         <span>${item.getClass().simpleName}</span>
    </span>
        <a class="mdl-list__item-secondary-action" href="#"><span id="span${item.getClass().simpleName}${item.id}">${item.balance}</span></a>
    </div>
    </c:forEach>
    </form>
</div>