<#macro signForm data email remember>
<#if email>
    <input id="email" name="email" placeholder="  email" type="email">
</#if>
<#if data>
    <div class="birthday">
        <label for="date" id="birthday_label">birthday</label>
        <input id="birthday" name="birthday" placeholder="  upDate" class="textbox-n" type="text"
               onfocus="(this.type='date')" id="date">
    </div>
</#if>
<input id="password" name="password" placeholder="  password" type="password">
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
