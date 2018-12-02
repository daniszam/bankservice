<#macro controlBalances>
<div class="demo-list-action mdl-list">
    <form id="balance_type">
        <#list user.balances as item>
            <div class="mdl-list__item">
    <span class="mdl-list__item-primary-content">
        <label for="${item?index}" class="balance"><img src="<c:url value="/resources/bankcard.png"/>"></label>
         <span name="${item.getClass().simpleName}" id="${item?index}" class="balance"/>
         <span>${item.getClass().simpleName}</span>
    </span>
                <a class="mdl-list__item-secondary-action" href="#">
                    <#if !item.name?has_content>
                        <span id="span${item.getClass().simpleName}${item.id}">${item.balance}</span>
                    <#else>
                        <span id="span${item.name}${item.id}">${item.balance}</span>
                    </#if>
                </a>
                <input class="mdl-slider mdl-js-slider" type="range"
                       name="balance_slider" min="0" max="100" value="0" tabindex="0"
                       oninput="this.form.amountInput.value=this.value">
                <input type="number" name="amountInput" min="0" max="20" value="0"
                       oninput="this.form.balance_slider.value=this.value"/>
            </div>
        </#list>
    </form>
</div>
</#macro>