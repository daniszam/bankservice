<%@tag description="Navbar Tag" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<div class="add item">
    <div class="add item inputs">
        <select id="types" name="types">
            <c:forEach var="type" items="${types}" varStatus="loop">
            <option value="${loop.index}">${type.getClass().simpleName}</option>
            </c:forEach>
        </select>

            <input  name="date" placeholder="  upDate" class="textbox-n" type="text"
                   onfocus="(this.type='date')" id="date">

        <input type="text" pattern="[0-9]*(\.[0-9]+)?" id="sum" name="sum" placeholder="  count">
        <input type="text" pattern="[0-9]*(\.[0-9]+)?" id="upSum" name="upSum" placeholder="  count">
    </div>
</div>