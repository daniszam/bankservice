<#macro circle>
<#assign dashoffset=0>
<div id="category_name_box" style="z-index: 999">
    <#list categories as category>
        <div class="category box">
            <span>${category.name}</span>
            <span>${category.percent}</span>
        </div>
    </#list>
</div>
<section>
    <svg class="circle-chart" id="circle_box" viewbox="0 0 32 32" width="200" height="200" xmlns="http://www.w3.org/2000/svg">
        <circle class="circle-chart__background" stroke="#efefef" stroke-width="2" fill="none" cx="16" cy="16" r="15" />
        <#list categories as category>
            <circle class="circle-chart__circle" stroke="rgb(${category.color.red},${category.color.green},${category.color.blue})" stroke-width="2"
                    stroke-dasharray="${category.percent},100" stroke-dashoffset="-${dashoffset}"
                    id="${category.name}circle" stroke-linecap="round" fill="none" cx="16" cy="16" r="15" />
            <#assign dashoffset=dashoffset+category.percent>
        </#list>
        <g class="circle-chart__info">
            <text class="circle-chart__percent" x="15" y="14" alignment-baseline="central" text-anchor="middle" font-size="8">${randomPercent}%</text>
            <text class="circle-chart__subline" x="15" y="18.5" alignment-baseline="central" text-anchor="middle" font-size="2">You spent to ${randomCategory.name}</text>
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
</#macro>