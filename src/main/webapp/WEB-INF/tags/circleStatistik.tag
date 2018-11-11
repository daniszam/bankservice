<%@tag description="Navbar Tag" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section>
    <svg class="circle-chart" viewbox="0 0 30 30" width="200" height="200" xmlns="http://www.w3.org/2000/svg">
        <circle class="circle-chart__background" stroke="#efefef" stroke-width="2" fill="none" cx="15" cy="15" r="14" />
        <circle class="circle-chart__circle" stroke="#00acc1" stroke-width="2" stroke-dasharray="5,100" stroke-linecap="round" fill="none" cx="15" cy="15" r="14" />
        <circle class="circle-chart__circle" stroke="black"  stroke-width="2" stroke-dasharray="25,100" stroke-dashoffset="-5" stroke-linecap="round" fill="none" cx="15" cy="15" r="14" />
        <g class="circle-chart__info">
            <text class="circle-chart__percent" x="15" y="14" alignment-baseline="central" text-anchor="middle" font-size="8">30%</text>
            <text class="circle-chart__subline" x="15" y="18.5" alignment-baseline="central" text-anchor="middle" font-size="2">Yay 30% progress!</text>
        </g>
    </svg>
</section>

<section>
    <svg class="circle-chart" viewbox="0 0 30 30" width="200" height="200" xmlns="http://www.w3.org/2000/svg">
        <circle class="circle-chart__background" stroke="#efefef" stroke-width="2" fill="none" cx="15" cy="15" r="14" />
        <circle class="circle-chart__circle circle-chart__circle--negative" id="salary" stroke="#00acc1" stroke-width="2" stroke-dasharray="${percent},100" stroke-linecap="round" fill="none" cx="15" cy="15" r="14" />
        <g class="circle-chart__info">
            <text class="circle-chart__percent" id="text_salary" x="15" y="14.5" alignment-baseline="central" text-anchor="middle" font-size="8">${percent}%</text>
            <text class="circle-chart__subline" x="15" y="18.5" alignment-baseline="central" text-anchor="middle" font-size="2">Oh no :(</text>
        </g>
    </svg>
</section>

<section>
    <svg class="circle-chart" viewbox="0 0 30 30" width="200" height="200" xmlns="http://www.w3.org/2000/svg">
        <circle class="circle-chart__background" stroke="#efefef" stroke-width="2" fill="none" cx="15" cy="15" r="14" />
        <circle class="circle-chart__circle circle-chart__circle--negative" stroke="#00acc1" stroke-width="2" stroke-dasharray="10,100" stroke-linecap="round" fill="none" cx="15" cy="15" r="14" />
        <g class="circle-chart__info">
            <text class="circle-chart__percent" x="15" y="14.5" alignment-baseline="central" text-anchor="middle" font-size="8">-10%</text>
            <text class="circle-chart__subline" x="15" y="20.5" alignment-baseline="central" text-anchor="middle" font-size="2">Oh no :(</text>
        </g>
    </svg>
</section>