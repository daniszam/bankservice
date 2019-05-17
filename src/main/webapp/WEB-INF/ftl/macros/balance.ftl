<#macro balance checkbox>

<#list user.balances as item>
    <div class="mdl-list__item">
    <span class="mdl-list__item-primary-content">
    <div>
        <#if !item.icon.path?has_content>
            <label for="${item.id}" class="balance"><img src="/resources/bankcard.png"></label>
        <#else>
            <label for="${item.id}" class="balance"><img src="${item.icon.path}"></label>
        </#if>
        <#if checkbox>
            <input type="checkbox" name="${item.getClass().simpleName}" id="${item.id}" class="balance">
        <#else>
            <span name="${item.getClass().simpleName}" id="${item.id}" class="balance"/>
        </#if>
        <#if item.name?has_content>
            <span>${item.name}</span>
        <#else >
            <span>${item.getClass().simpleName}</span>
        </#if>
    </span>
        <a class="mdl-list__item-secondary-action" href="#">
            <span id="span_balance${item.id}">${item.count}</span>
        </a>
    </div>
    <#if !checkbox>
        <div style="display: flex; align-items: center; width: 30%; justify-content: space-between;align-items: center">
            <input class="slider" type="range"
                   name="balance_slider${item?index}" min="0" max="10000" value="0" tabindex="0"
                   oninput="this.form.amountInput${item?index}.value=this.value">
            <input type="number" id="${item.id}" name="amountInput${item?index}" min="0" max="10000" value="0"
                   id="${item.id}" oninput="this.form.balance_slider${item?index}.value=this.value"/>
        </div>
    </#if>
</div>
</#list>
</#macro>

