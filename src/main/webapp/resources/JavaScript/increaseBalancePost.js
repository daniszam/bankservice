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
            if($(this).attr('type')=='number' && $(this).val()>0) {
                var item = {
                    id: $(this).attr("id"),
                    sum: $(this).val()
                };
                items.push(item);
            }

        });

        $.ajax({
            type: "POST",
            url: window.location,
            contentType: "application/json",
            data:JSON.stringify(items),
            //     {
            //     items: JSON.stringify(items)
            // },
            dataType: "json"
        }).done(function (data) {
            console.log(data);
            alert("Succesful");
            $('form')[0].reset();
            $.each(data, function (i) {
                var span = data[i].sum;
                $('#span'+data[i].id).html(span);
            });
        }).fail(function (t, tetx, er) {
            console.log(t.responseText);
            console.log(tetx);
            console.log(er);
        });


    }});

