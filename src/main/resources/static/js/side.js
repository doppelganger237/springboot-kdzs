let isMobile = checkMobile();

var navWidth = '230px', option = 300;

var pathUri = window.location.href;
layui.use(['element', 'layer'], function () {

    let element = layui.element, layer = layui.layer;
    $.ajax({
        type: "post",
        url: api.user.menus,
        dataType: "json",
        success: function (r) {
            getMenus(r.data);
            element.render('nav');
        }
    });


    element.on('nav(layout-left)', function (elem) {

        let id = elem[0].getAttribute('id');
        if (id == 'sideMenu') {

            //var side = $(".layui-layout-body");

            $(".layui-layout-body").toggleClass("hide-side")
                $("#flexible").toggleClass("layui-icon-shrink-right")
            $("#flexible").toggleClass("layui-icon-spread-left")

        }


    });

    $(".layui-side .layui-side-scroll").on('mouseover', "li", function () {

        if ($('.layui-layout-body').hasClass('hide-side')) {
            let that = this;
            layer.tips($(this).find('cite').html(), that, {
                time: 0
            })
        }
    });
    $(".layui-side .layui-side-scroll").on('mouseleave', "li", function () {
        if ($('.layui-layout-body').hasClass('hide-side')) {
            layer.closeAll('tips')
        }
    });

});

function checkMobile() {
    if ((navigator.userAgent
        .match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i))) {
        return true
    }
    return false
}


// 一坨狗屎, 以后再慢慢优化
function getMenus(data) {

    var ul = $("<ul class='layui-nav layui-nav-left layui-nav-tree' lay-filter='layui-nav-left'></ul>");
    for (i in data) {
        var parent = data[i];
        var li = $("<li class='layui-nav-item'></li>");
        var a = $("<a></a>");

        if (parent.url == null) {
            $(a).attr('href', 'javascript:;');
        } else {
            $(a).attr('href', parent.url);
        }
        if (parent.icon != null) {
            $(a).append($("<i></i>").attr('class', parent.icon));
        }

        a.append($("<cite></cite>").text(parent.name));
        li.append(a);
        if (parent.hasChildren == null) {

            if (pathUri.indexOf(parent.url) > 0) {

                li.addClass("layui-nav-itemed");
                li.addClass("layui-this");
            }


        } else {
            var childrens = parent.children;
            for (var j = 0; j < childrens.length; j++) {

                children = childrens[j];
                var dl = $("<dl class='layui-nav-child'></dl>");

                var dd = $("<dd></dd>");
                if (pathUri.indexOf(children.url) > 0) {
                    li.addClass("layui-nav-itemed");
                    dd.addClass("layui-this");
                }

                var a_children = $("<a></a>");
                a_children.attr('href', children.url);
                a_children.append($("<cite></cite>").text(children.name));
                dd.append(a_children);
                dl.append(dd);
                li.append(dl);
            }

        }

        ul.append(li);

        $(".layui-side-scroll").append(ul);

    }

}
