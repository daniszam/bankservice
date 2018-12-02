<#macro item>
<div class="demo-list-action mdl-list">
    <form method="post" id="items">
        <#list category as item>
            <div class="mdl-list__item">
                <span class="mdl-list__item-primary-content">
                    <i class="material-icons">class</i>
                <span>${item.name}</span>
                <div style="position: absolute; right: 5%; width: 30%">
                  <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <input class="mdl-textfield__input" type="text" pattern="-?[0-9]*(\.[0-9]+)?" id="${item.id}"
                           name="${item.name}">
                    <label class="mdl-textfield__label" for="${item.id}">price</label>
                    <span class="mdl-textfield__error">Input is not a number!</span>
                  </div>
                </div>
                </span>
            </div>
        </#list>
    </form>
</div>
</#macro>

