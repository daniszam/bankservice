<%@tag description="Navbar Tag" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<div class="add item">
    <div class="add item inputs">
        <div class="name box">
                <select id="types" name="types">
                    <c:forEach var="type" items="${types}" varStatus="loop">
                    <option value="${loop.index}">${type.getClass().simpleName}</option>
                    </c:forEach>
                </select>
                <div id="tt4" class="icon material-icons">help_outline</div>
                <div class="mdl-tooltip" data-mdl-for="tt4">
                    Type of balance
                </div>
            <input type="text" placeholder="name" id="balance_name" name="balance_name">
        </div>
        <div class="support box">
                <input  name="date" placeholder="  upDate" class="textbox-n" type="text"
                                  onfocus="(this.type='date')" id="date">
            <div id="tt3" class="icon material-icons">help_outline</div>
            <div class="mdl-tooltip" data-mdl-for="tt3">
                Date of your salary(not required)
            </div>

            <input type="text" pattern="[0-9]*(\.[0-9]+)?" id="sum" name="sum" placeholder="  count">
            <div id="tt1" class="icon material-icons">help_outline</div>
            <div class="mdl-tooltip" data-mdl-for="tt1">
                Insert count of money on your balance (not required)
            </div>
            <input type="text" pattern="[0-9]*(\.[0-9]+)?" id="upSum" name="upSum" placeholder="  count">
            <div id="tt2" class="icon material-icons">help_outline</div>
            <div class="mdl-tooltip" data-mdl-for="tt2">
                Insert your salary (not required)
            </div>
        </div>
        <div class="icon box" style="overflow: scroll" id="icons" name="icons">
             <div style="height: 100%">
            <c:forEach var="icon" items="${icons}" varStatus="loop">
                <input type="radio" id="icon_${loop.index}" name="icon" value="${loop.index}">
                <label for="icon_${loop.index}"><img src="<c:url value="${icon.path}"/>"></label>
            </c:forEach>
             </div>
        </div>
    </div>
</div>