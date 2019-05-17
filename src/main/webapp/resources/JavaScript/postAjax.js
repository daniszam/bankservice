document.addEventListener("DOMContentLoaded", function () {
    $('form').submit(function (event) {
        var formID = $(this).attr('id');
        event.preventDefault();
        ajaxPost(formID);
    });


    function ajaxPost(formID) {

        var items = [];


        var balanceType = [];
        console.log("#" + formID + " :input");
        $("#" + formID + " :input").each(function () {
            if ($(this).val() > 0) {
                var item = {
                    id: $(this).attr("id"),
                    sumOfCosts: $(this).val()
                };
                items.push(item);
            }
        });
        $("#balance_type :input").each(function () {
            if ($(this).prop("checked")) {
                var type = {
                    id: $(this).attr("id")
                };
                balanceType.push(type)
            }
        });
        console.log(items);
        console.log(balanceType);
        var request_data = {
            "type": balanceType,
            "items": items
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
            $('form')[1].reset();
            $('form')[0].reset();
            $('#salary').attr('stroke-dasharray', percent + ',100');
            $('#text_salary').html(percent + '%');

            $.each(data, function (i) {
                console.log(data[i]);
                var span = data[i].count;
                $('#span_balance' + data[i].id).html(span);
            });
            var offset = 0;
            $.each(map, function (i) {
                if ($('#percent_count').text() == '0%') {
                    $('#percent_count').text('100%');
                    $('#percent_title').text('уходит на ' + map[0].name);
                }
                console.log(map[i]);
                if ($('#' + map[i].name + 'circle').length) {
                    $('#' + map[i].name + 'circle').attr('stroke-dasharray', map[i].sumOfCosts + ',100');
                    $('#' + map[i].name + 'circle').attr('stroke-dashoffset', '-' + offset);
                    $('#category_txt_sum_'+map[i].id).text(map[i].sumOfCosts);
                } else {
                    var circle = '<circle class="circle-chart__circle" stroke="rgb('
                        + map[i].color.red + ',' + map[i].color.green + ',' + map[i].color.blue + ')"' +
                        'stroke-width="2" stroke-dasharray="' + map[i].sumOfCosts + ',100"' +
                        ' stroke-dashoffset="-' + offset + '" id="' + map[i].name + 'circle" ' +
                        'stroke-linecap="round" fill="none" cx="16" cy="16" r="15" />';
                    $('#circle_box').append(parseSVG(circle));
                    $('#category_name_box').append('<div class="category box"><span>' + map[i].name + '</span><span id="category_txt_sum_'+map[i].id+'"> ' + map[i].sumOfCosts + '</span></div>');
                }
                offset = offset + map[i].sumOfCosts;
            })

        }).fail(function (t, tetx, er) {
            alert("Chose categorys and balances");
            console.log(t);
            console.log(tetx);
            console.log(er);
        });

        function parseSVG(s) {
            var div = document.createElementNS('http://www.w3.org/1999/xhtml', 'div');
            div.innerHTML = '<svg xmlns="http://www.w3.org/2000/svg">' + s + '</svg>';
            var frag = document.createDocumentFragment();
            while (div.firstChild.firstChild)
                frag.appendChild(div.firstChild.firstChild);
            return frag;
        }


        function resetData() {
            $("#snack_id").val("");
            $("#snack_name").val("");
        }

    }
});

