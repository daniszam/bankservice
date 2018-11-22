<%@tag description="Navbar Tag" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="countrys" type="java.util.List<models.LocationUser>" required="true" %>
<%@ attribute name="citys" type="java.util.List<models.LocationUser>" required="true" %>


<select id="country_selector">
    <c:forEach var="locationCountry" items="${countrys}">
        <option value="${locationCountry.country}">${locationCountry.country}</option>
    </c:forEach>
</select>

<select id="city_selector">
    <c:forEach var="locationCitys" items="${citys}">
        <option value="${locationCitys.city}">${locationCitys.city}</option>
    </c:forEach>
</select>