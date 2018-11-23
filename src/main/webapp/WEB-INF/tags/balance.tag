<%@tag description="Navbar Tag" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@attribute name="checkbox" type="java.lang.Boolean" %>


    <c:forEach var="item" items="${user.balances}" varStatus="loop">
    <div class="mdl-list__item">
    <span class="mdl-list__item-primary-content">
    <div>
        <c:if test="${empty item.icon.path}">
            <label for="${loop.index}" class="balance"><img src="<c:url value="/resources/bankcard.png"/>"></label>
        </c:if>
        <c:if test="${not empty item.icon.path}">
            <label for="${loop.index}" class="balance"><img src="<c:url value="${item.icon.path}"/>"></label>
        </c:if>
        <c:if test="${checkbox}">
         <input type="checkbox" name="${item.getClass().simpleName}" id="${loop.index}" class="balance">
        </c:if>
        <c:if test="${not checkbox}">
            <span name="${item.getClass().simpleName}" id="${item.getClass().simpleName}-${item.id}" class="balance"/>
        </c:if>
        <c:if test="${not empty item.name}">
         <span>${item.name}</span>
        </c:if>
        <c:if test="${empty item.name}">
        <span>${item.getClass().simpleName}</span>
        </c:if>
    </span>
        <a class="mdl-list__item-secondary-action" href="#">
        <c:if test="${empty item.name}">
            <span id="span${item.getClass().simpleName}${item.id}">${item.balance}</span>
        </c:if>
        <c:if test="${not empty item.name}">
            <span id="span${item.name}${item.id}">${item.balance}</span>
        </c:if>
        </a>
    </div>
        <c:if test="${not checkbox}">
            <div style="display: flex; align-items: center; width: 30%; justify-content: space-between;align-items: center">
            <input class="slider" type="range"
                   name="balance_slider${loop.index}" min="0" max="10000" value="0" tabindex="0"
                   oninput="this.form.amountInput${loop.index}.value=this.value">
            <input type="number" id="${loop.index}" name="amountInput${loop.index}" min="0" max="10000" value="0"
                   id="${loop.index}" oninput="this.form.balance_slider${loop.index}.value=this.value" />
            </div>
        </c:if>
    </div>
    </c:forEach>





