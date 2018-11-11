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
                    value: $(this).val()
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
            console.log(data);
            alert("Succesful");
            $('form')[1].reset();
            $('#salary').attr('stroke-dasharray',percent.percent+',100');
            $('#text_salary').html(percent.percent+'%');
            $.each(data, function (i) {
                var span = data[i].balance;
                $('#span'+data[i].name+data[i].id).html(span);
            });
        }).fail(function (t, tetx, er) {
                console.log(t);
            console.log(tetx);
            console.log(er);
            });

    function resetData(){
        $("#snack_id").val("");
        $("#snack_name").val("");
    }

}});

