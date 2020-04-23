var $table;
function initMainTable() {
    var queryUrl = '/logger/queryPage?rnd=' + Math.random()
    $table = $('#table').bootstrapTable({
        url: queryUrl,                      //请求后台的URL（*）
        method: 'GET',                      //请求方式（*）
        //toolbar: '#toolbar',              //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
        pageSize: 20,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                      //是否显示表格搜索
        strictSearch: true,
        showColumns: false,                  //是否显示所有的列（选择显示的列）
        showRefresh: false,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "_id",                     //每一行的唯一标识，一般为主键列
        showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        //得到查询的参数
        queryParams: function (params) {
            //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            var temp = {
                rows: params.limit,                         //页面大小
                page: (params.offset / params.limit) + 1,   //页码
                sort: params.sort,      //排序列名
                sortOrder: params.order //排位命令（desc，asc）
            };
            var appName = $("#appNameInput").val();
            if (appName != null && appName != '' && appName != undefined) {
                temp.appName = appName;
            }

            var level = $("#levelInput").val();
            if (level != null && level != '' && level != undefined) {
                temp.level = level;
            }

            var message = $("#messageInput").val();
            if (message != null && message != '' && message != undefined) {
                temp.message = message;
            }
            return temp;
        },
        columns: [{
            checkbox: false,
            visible: false                  //是否显示复选框
        }, {
            field: 'appName',
            title: '项目名称',
            sortable: true,
            width: 140
        }, {
            field: 'level',
            title: '级别',
            sortable: true,
            width: 100
        }, {
            field: 'message',
            title: '类容'
        }, {
            field: 'throwable',
            title: '堆栈',
            width: 120,
            formatter: throwableFormatter
        }, {
            field: 'createDate',
            title: '时间',
            width: 180
        }],
        onLoadSuccess: function () {
        },
        onLoadError: function () {
            showTips("数据加载失败！");
        }
    });
}

function throwableFormatter(value, row, index) {
    return "<a href='javaScript:void(0);' title='查看详情' onclick=showBg('" + index + "') target='_blank'>查看详情</a>"
}

function search() {
    $('#table').bootstrapTable('refresh');
}

function showBg(index) {
    var dataArr = $("#table").bootstrapTable('getData');
    var row = dataArr[index];
    var width = $("body").width();
    $("#dialog").css({"width": (width - 100) + "px", "height": 600 + "px"});
    $("#textBox").html(row.message+" "+row.throwable);
    $("#dialog").show();
}

//关闭灰色 jQuery 遮罩
function closeBg() {
    $("#dialog").hide();
}