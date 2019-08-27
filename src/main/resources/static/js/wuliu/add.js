layui.use(['form', 'layer'], function () {
    var form = layui.form, layer = layui.layer;
    form.verify({

        validateNumber: function (value) {

            if (!/^(\d|([1-9]\d+))(\.\d{1,2})?$/.test(value)) {

                return "输入的数字格式不对!"
            }
        }
    })

    // 初始化省级地址
    $.ajax({

        type: "post",
        url: "/area/list",
        dataType: "json",
        success: function (r) {
            data = r.data;
            //alert(JSON.stringify(data))

            $.each(data, function (index, item) {

                $("select[name='province']").append(new Option(item.name, item.code))
                $("select[name='province_to']").append(new Option(item.name, item.code))
            })

            form.render("select")

        }


    });

    form.on("submit(submit)", function (data) {



        $.ajax({

            type: "post",
            contentType: "application/json;charset=UTF-8",
            url: "/wuliu/addPost",
            data: JSON.stringify(data.field),
            dataType: "json",
            success: function (r) {
                if (r.code = 200) {
                    layer.open({

                        content: "提交任务成功!",
                        yes: function (index, layero) {
                            layer.close(index);
                            location.href = '/wuliu/task';
                        },
                        cancel: function (index, layero) {

                            layer.close(index);
                            location.href = '/wuliu/task';
                        }
                    })
                } else {

                    layer.alert(r.message(), {icon: 5});
                }


            }

        })


    })


    form.on('select(province)', function (data) {
        $.ajax({

            type: "post",
            url: "/area/getCityByProvinceId",
            data: {id: data.elem[data.elem.selectedIndex].value},
            dataType: "json",
            success: function (r) {
                data = r.data;

                $("select[name='city']").html('');
                $("select[name='area']").html('');
                $.each(data, function (index, item) {
                    $("select[name='city']").append(new Option(item.name, item.code))
                })

                form.render("select")


                $('select[name="city"]').siblings("div.layui-form-select").find('dl dd[lay-value=' + data[0].code + ']').click();

            }


        });


    });


    form.on('select(province_to)', function (data) {
        $.ajax({

            type: "post",
            url: "/area/getCityByProvinceId",
            data: {id: data.elem[data.elem.selectedIndex].value},
            dataType: "json",
            success: function (r) {
                data = r.data;

                $("select[name='city_to']").html('');

                $("select[name='area_to']").html('');
                $.each(data, function (index, item) {


                    $("select[name='city_to']").append(new Option(item.name, item.code))
                })

                form.render("select")


                $('select[name="city_to"]').siblings("div.layui-form-select").find('dl dd[lay-value=' + data[0].code + ']').click();

            }


        });


    });


    form.on('select(city)', function (data) {


        $.ajax({

            type: "post",
            url: "/area/getAreaByCityId",
            data: {id: data.elem[data.elem.selectedIndex].value},
            dataType: "json",
            success: function (r) {
                data = r.data;

                $("select[name='area']").html('');
                $.each(data, function (index, item) {

                    $("select[name='area']").append(new Option(item.name, item.code))
                })

                form.render("select")

            }


        });


    });

    form.on('select(city_to)', function (data) {


        $.ajax({

            type: "post",
            url: "/area/getAreaByCityId",
            data: {id: data.elem[data.elem.selectedIndex].value},
            dataType: "json",
            success: function (r) {
                data = r.data;

                $("select[name='area_to']").html('');
                $.each(data, function (index, item) {


                    $("select[name='area_to']").append(new Option(item.name, item.code))
                })

                form.render("select")

            }


        });


    });

});

