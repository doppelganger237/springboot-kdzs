layui.use(['table', 'element'], function () {

    var table = layui.table;
    var element = layui.element;


    table.render({
        elem: '#test'
        , url: '/wuliu/task'
        , method: "post"
        /* ,toolbar: '#toolbarDemo'*/
        , cellMinWidth: 30,

        cols: [[
            {type: 'checkbox'}
            , {field: 'id', title: '任务ID', width: 80,}
            , {field: 'userId', title: '用户ID', width: 80}
            , {
                field: 'weight', title: '重量', width: 120
            }
            , {field: 'volume', title: '体积', width: 120,templet:function (d) {

                var volume = d.volume;

                var v = (volume.length*volume.width*volume.height/1000000).toFixed(3);

                return v;
                }}
            , {field: 'startTime', title: '创建时间', width: 180}
            , {field: 'endTime', title: '完成时间', width: 180}
            , {title: '完成进度', width: 150,align:"center" ,templet: '#progress'}
            , {
                field: 'status', title: '状态', width: 80, templet: function (d) {
                    if (d.status == 0) {
                        return '<button class="layui-btn layui-btn-xs">处理中</button>'
                    } else if (d.status == 1) {
                        return '<button class="layui-btn layui-btn-normal layui-btn-xs">已完成</button>'
                    } else {
                        return '<span style="color: #FE2E2E;">任务失败</span>'
                    }
                }
            }
            , {field: 'from', title: '出发地', width: 250}
            , {field: 'destination', title: '目的地', width: 250}
            , {title: '操作', width: 100, toolbar: '#barDemo'}
        ]]

        , page: true
        ,done: function (res,curr,count) {
            /*alert(JSON.stringify(res))
            $.get("/wuliu/info/getJobProgress?id="+res.data.id,function (r) {
                element.progress('demo', d.data);
            });*/

            element.render();
        }
    });

/*    table.on('sort(test)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        console.log(obj.field); //当前排序的字段名
        console.log(obj.type); //当前排序类型：desc（降序）、asc（升序）、null（空对象，默认排序）
        console.log(this); //当前排序的 th 对象

        //尽管我们的 table 自带排序功能，但并没有请求服务端。
        //有些时候，你可能需要根据当前排序的字段，重新向服务端发送请求，从而实现服务端排序，如：
        table.reload('test', { //testTable是表格容器id
            initSort: obj //记录初始排序，如果不设的话，将无法标记表头的排序状态。 layui 2.1.1 新增参数
            , where: { //请求参数（注意：这里面的参数可任意定义，并非下面固定的格式）
                field: obj.field //排序字段
                , order: obj.type //排序方式
            }
        });
    });*/

    //头工具栏事件
    table.on('toolbar(test)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case 'getCheckData':
                var data = checkStatus.data;
                layer.alert(JSON.stringify(data));
                break;
            case 'getCheckLength':
                var data = checkStatus.data;
                layer.msg('选中了：' + data.length + ' 个');
                break;
            case 'isAll':
                layer.msg(checkStatus.isAll ? '全选' : '未全选');
                break;
        }
        ;
    });


 /*   function screen() {
        //获取当前窗口的宽度
        var width = $(window).width();
        if (width > 1200) {
            return 3;   //大屏幕
        } else if (width > 992) {
            return 2;   //中屏幕
        } else if (width > 768) {
            return 1;   //小屏幕
        } else {
            return 0;   //超小屏幕
        }
    }*/
    //监听行工具事件
    table.on('tool(test)', function (obj) {
        var data = obj.data;

        if (obj.event === 'del') {
            layer.confirm('真的删除行么', function (index) {
                obj.del();
                layer.close(index);
            });
        } else if (obj.event === 'edit') {

           layer.open({
                type: 2,
                title:"结果查看",
               area:$(window).width()<768? ['80%', '80%']:["600px","600px"],
               maxmin: true,
                //maxmin: true,area: ['300px', '300px'],
                content: ['/wuliu/info/'+data.id]//这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']


           }

               );

            /*layer.prompt({
                formType: 2
                , value: data.email
            }, function (value, index) {
                obj.update({
                    email: value
                });
                layer.close(index);
            });*/
        }
    });
});