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
                    value: $(this).val(),
                    name: $(this).attr("name")
                };
                items.push(item);
            }
        });
        $("#balance_type :input").each(function () {
            if($(this).prop("checked")) {
                var type = {
                    id : $(this).attr("id"),
                    type : $(this).attr("name")
                };
                balanceType.push(type)
            }
        });


        $.ajax({
            type: "POST",
            url: window.location,
            data: {
                type: JSON.stringify(balanceType),
                items:JSON.stringify(items)
            },
            dataType: "json"
        }).done(function (datas) {
            var data = datas[0];
            var percent = datas[1];

            var map = datas[2];

            console.log(data);
            alert("Succesful");
            $('form')[1].reset();
            $('form')[0].reset();
            $('#salary').attr('stroke-dasharray',percent.percent+',100');
            $('#text_salary').html(percent.percent+'%');
            $.each(data, function (i) {
                var span = data[i].balance;
                $('#span'+data[i].id).html(span);
            });
            var offset=0;
            $.each(map, function (i) {
                console.log(map[i]);
                if($('#'+map[i].category.name+'circle').length) {
                    $('#' + map[i].category.name + 'circle').attr('stroke-dasharray', map[i].percent + ',100');
                    $('#' + map[i].category.name + 'circle').attr('stroke-dashoffset', '-' + offset);
                }else{
                    var circle = '<circle class="circle-chart__circle" stroke="black" ' +
                        'stroke-width="2" stroke-dasharray="'+map[i].percent+',100"' +
                        ' stroke-dashoffset="-'+ offset+'" id="'+map[i].category.name+'circle" ' +
                        'stroke-linecap="round" fill="none" cx="16" cy="16" r="15" />';
                    $('#circle_box').append(circle);
                    $('#category_name_box').append('<div class="category box"><span>'+map[i].category.name+'</span></div>');
                }
                offset = offset+map[i].percent;
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

