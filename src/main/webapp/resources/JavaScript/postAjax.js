document.addEventListener("DOMContentLoaded", function () {
    $('form').submit(function (event) {
        var formID = $(this).attr('id');
        event.preventDefault();
        ajaxPost(formID);
    });



    function ajaxPost(formID) {

        var items = [];


        var balanceType = [];
        console.log("#"+formID+" :input");
        $("#"+formID+" :input").each(function () {
            if($(this).val()>0) {
                var item = {
                    id: $(this).attr("id"),
                    sumOfCosts: $(this).val()
                };
                items.push(item);
            }
        });
        $("#balance_type :input").each(function () {
            if($(this).prop("checked")) {
                var type = {
                    id : $(this).attr("id")
                };
                balanceType.push(type)
            }
        });
        console.log(items);
        console.log(balanceType);
        var request_data = {
            "type": balanceType,
            "items" : items
        };
        console.log(JSON.stringify(request_data));
        $.ajax({
            type: "POST",
            url: window.location,
            contentType: "application/json",
            data: JSON.stringify(request_data),
            dataType: "json"
        }).done(function (datas) {
            datas = JSON.parse(datas);
            console.log(datas[2]);
            var data = datas[0];
            var percent = datas[1];

            var map = datas[2];

            console.log(datas);
            alert("Succesful");
            $('form')[1].reset();
            $('form')[0].reset();
            $('#salary').attr('stroke-dasharray',percent+',100');
            $('#text_salary').html(percent+'%');
            $.each(data, function (i) {
                var span = data[i].balance;
                $('#span_balance'+ data[i].id).html(span);
            });
            var offset=0;
            $.each(map, function (i) {
                console.log(map[i]);
                if($('#'+map[i].name+'circle').length) {
                    $('#' + map[i].name + 'circle').attr('stroke-dasharray', map[i].sumOfCosts + ',100');
                    $('#' + map[i].name + 'circle').attr('stroke-dashoffset', '-' + offset);
                }else{
                    var circle = '<circle class="circle-chart__circle" stroke="black" ' +
                        'stroke-width="2" stroke-dasharray="'+map[i].sumOfCosts+',100"' +
                        ' stroke-dashoffset="-'+ offset+'" id="'+map[i].name+'circle" ' +
                        'stroke-linecap="round" fill="none" cx="16" cy="16" r="15" />';
                    $('#circle_box').append(circle);
                    $('#category_name_box').append('<div class="category box"><span>'+map[i].name+'</span></div>');
                }
                offset = offset+map[i].sumOfCosts;
            })

        }).fail(function (t, tetx, er) {
            alert("Chose categorys and balances");
            console.log(t);
            console.log(tetx);
            console.log(er);
            });

    function resetData(){
        $("#snack_id").val("");
        $("#snack_name").val("");
    }

}});

