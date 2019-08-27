$(function () {
    layui.use(['form', 'laytpl'], function () {


        var form = layui.form, laytpl = layui.laytpl;


        $.post(api.user.info, function (r) {

                let data = r.data;


                form.val("infoForm", {
                        "username": data.username
                    }
                )


            }
        );

        /*    $.ajax({
                type: "post",
                url: api.user.info,
                dataType: "json",
                success: function (r) {

                    if (r.code == 200) {

                        let data = r.data;
                        form.val("infoForm", {
                                "username": data.username
                            }
                        )



                        /!* $("span[name='username']").render(data.name,function () {
                             alert(123);
                         })*!/


                    }
                }
            });*/
    });
})



