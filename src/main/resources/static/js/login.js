layui.use(['form', 'layer'], function () {

    let form = layui.form, layer = layui.layer;
    form.on('submit(login)', function (data) {


        var loading = layer.load();
        var ajax = $.ajax({
            type: "post",
            url: api.user.login,
            data: data.field,
            dataType: "json",
            success: function (r) {

                if (r.code == 200) {
                    location.href = 'index';
                } else {
                    layer.msg(r.message, {icon: 5});
                    ;
                }


            }
        });

        $.when(ajax).done(function () {
            layer.close(loading)
        });
        return false;
    });

});

