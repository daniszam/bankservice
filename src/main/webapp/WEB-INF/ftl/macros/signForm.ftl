<#assign form=JspTaglibs["http://www.springframework.org/tags/form"] />
<#macro signForm data email remember>

<#if email>
    <@form.label id="email" path="email">Email</@form.label>
    <@form.input id="email" path="email"/>
    <@form.errors path="email" /><br>
</#if>

<#if data>
    <div class="birthday">
        <label for="birthday" id="date">
        <input onclick="(this.type='date')" class="textbox-n" id="birthday" name="birthday"/>
    </div>
</#if>

<@form.label id="password" path="password">Password</@form.label>
<@form.input path="password"/>
<@form.errors path="password" /><br>

<#if remember>
    <div style="position: absolute; bottom: 5%;left: 5%">
        <input type="checkbox" name="save_me" id="save_me">
        <label for="save_me" style="text-align: left">Remember me</label>
    </div>
</#if>

<div class="logo" style="background: white; position: absolute; left: 70%; right: 3%; top: 80%">
    <button id="submit" type="submit" value="Sign Up" accesskey="enter">
        <img src="/resources/visa.png" style="width: 100%">
    </button>
</div>
</#macro>
