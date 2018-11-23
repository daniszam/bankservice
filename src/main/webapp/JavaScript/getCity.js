$(document).ready(function () {
    $.getJSON('http://gd.geobytes.com/GetCityDetails?callback=?', function(data) {
        var json = (JSON.stringify(data, null, 2));
        ajaxGet(json);
    });

    $('#country_selector').on('change', function (e) {
        console.log("yaa");
        if ($(this).val() == 0) return false;

        alert($(this).val());
        ajaxPost($(this))
    });
});

function ajaxPost(country) {
    var country = {country: country.val()};
    $.ajax({
        type: "POST",
        url: window.location,
        data: country,
        dataType: "json"
    }).done(function (citys) {
        console.log(citys);
        var option ='';
        $.each(citys, function (i) {
            option =option + '<option value="'+citys[i].city+'">'+citys[i].city+'</option>';
        });
        $('#city_selector').html(option);

    }).fail(function (t, tetx, er) {
        alert("Chose categorys and balances");
        console.log(t);
        console.log(tetx);
        console.log(er);
    });
}

function ajaxGet(ip) {
    console.log(ip);
    $.ajax({
        type:"GET",
        url: window.location,
        data: ip
    })
}