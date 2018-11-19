<%@ tag import="models.Category" %>
<%@tag description="Navbar Tag" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach var="category" items="category"/>

<c:set var="dashoffset" value="${0}"/>
<div id="category_name_box">
    <c:forEach items="${percentMap}" var="percentMap">
        <div class="category box">
            <span>${percentMap.key.name}</span>
        </div>
    </c:forEach>
</div>
<section>
    <svg class="circle-chart" id="circle_box" viewbox="0 0 32 32" width="200" height="200" xmlns="http://www.w3.org/2000/svg">
        <circle class="circle-chart__background" stroke="#efefef" stroke-width="2" fill="none" cx="16" cy="16" r="15" />
        <c:forEach items="${percentMap}" var="percentMap">
            <c:set var="color1"><%= java.lang.Math.round(java.lang.Math.random() * 255) %></c:set>
            <c:set var="color2"><%= java.lang.Math.round(java.lang.Math.random() * 255) %></c:set>
            <c:set var="color3"><%= java.lang.Math.round(java.lang.Math.random() * 255) %></c:set>
            <circle class="circle-chart__circle" stroke="rgb(${color1},${color2},${color3})" stroke-width="2" stroke-dasharray="${percentMap.value},100" stroke-dashoffset="-${dashoffset}" id="${percentMap.key.name}circle" stroke-linecap="round" fill="none" cx="16" cy="16" r="15" />
            <c:set var="dashoffset" value="${dashoffset + percentMap.value}"/>
        </c:forEach>
        <g class="circle-chart__info">
            <text class="circle-chart__percent" x="15" y="14" alignment-baseline="central" text-anchor="middle" font-size="8">${randomPercent}%</text>
            <text class="circle-chart__subline" x="15" y="18.5" alignment-baseline="central" text-anchor="middle" font-size="2">You spent to ${randomCategory}</text>
        </g>
    </svg>
</section>

<section>
    <svg class="circle-chart" viewbox="0 0 32 32" width="200" height="200" xmlns="http://www.w3.org/2000/svg">
        <circle class="circle-chart__background" stroke="#efefef" stroke-width="2" fill="none" cx="16" cy="16" r="15" />
        <circle class="circle-chart__circle circle-chart__circle--negative" id="salary" stroke="#00acc1" stroke-width="2" stroke-dasharray="${percent},100" stroke-linecap="round" fill="none" cx="16" cy="16" r="15" />
        <g class="circle-chart__info">
            <text class="circle-chart__percent" id="text_salary" x="15" y="14.5" alignment-baseline="central" text-anchor="middle" font-size="8">${percent}%</text>
            <text class="circle-chart__subline" x="15" y="18.5" alignment-baseline="central" text-anchor="middle" font-size="2">Oh no :(</text>
        </g>
    </svg>
</section>

