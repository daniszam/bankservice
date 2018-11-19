<%@tag description="Navbar Tag" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="demo-list-action mdl-list"  >
    <form id="balance_type">
        <c:forEach var="item" items="${user.balances}" varStatus="loop">
            <div class="mdl-list__item">
    <span class="mdl-list__item-primary-content">
        <label for="${loop.index}" class="balance"><img src="<c:url value="/resources/bankcard.png"/>"></label>
         <span name="${item.getClass().simpleName}" id="${loop.index}" class="balance"/>
         <span>${item.getClass().simpleName}</span>
    </span>
                <a class="mdl-list__item-secondary-action" href="#">
                    <c:if test="${empty item.name}">
                    <span id="span${item.getClass().simpleName}${item.id}">${item.balance}</span>
                    </c:if>
                    <c:if test="${not empty item.name}">
                        <span id="span${item.name}${item.id}">${item.balance}</span>
                    </c:if>
                </a>
                <input class="mdl-slider mdl-js-slider" type="range"
                       name="balance_slider" min="0" max="100" value="0" tabindex="0"
                       oninput="this.form.amountInput.value=this.value">
                <input type="number" name="amountInput" min="0" max="20" value="0"
                       oninput="this.form.balance_slider.value=this.value" />
            </div>
        </c:forEach>
    </form>
</div>