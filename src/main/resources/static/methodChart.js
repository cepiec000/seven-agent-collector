var loadMinuteCountMethodData=function (appName,reTime) {
    var chart = echarts.init(document.getElementById("minuteCountMethodChart"),'macarons');
    chart.showLoading();
    $.get("/method/minuteCountMethod?appName=" + appName + "&reTime=" + reTime, function (result) {
        option = {
            tooltip : {
                trigger: 'axis',
                formatter: function (params, ticket, callback) {
                    var index = params[0].dataIndex;
                    var array = result.full;
                    return array[index];
                }

            },
            legend: {
                data:['每分钟访问']
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    data : result.axis
                }
            ],
            yAxis : [
                {
                    type : 'value',
                    splitArea : {show : true}
                }
            ],
            series : [
                {
                    name:'每分钟访问',
                    type:'bar',
                    data:result.data
                }
            ]
        }
        chart.setOption(option);
        if (result.axis!=null && result.axis.length>0) {
            chart.hideLoading();
        }
    });
}
var loadMethodCountData = function (appName, reTime) {
    var chart = echarts.init(document.getElementById("methodCountChart"),'macarons');
    chart.showLoading();
    $.get("/method/countMethod?appName=" + appName + "&reTime=" + reTime, function (result) {
        option = {
            tooltip : {
                trigger: 'axis',
                formatter: function (params, ticket, callback) {
                    var index = params[0].dataIndex;
                    var array = result.full;
                    return array[index];
                }

            },
            legend: {
                data:['访问次数']
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    data : result.axis
                }
            ],
            yAxis : [
                {
                    type : 'value',
                    splitArea : {show : true}
                }
            ],
            series : [
                {
                    name:'访问次数',
                    type:'bar',
                    data:result.data
                }
            ]
        }
        chart.setOption(option);
        if (result.axis!=null && result.axis.length>0) {
            chart.hideLoading();
        }
    });
}
var loadMethodList = function (appName, reTime) {
    var chart = echarts.init(document.getElementById("methodPaiHang"),'macarons');
    chart.showLoading();
    $.get("/method/queryTimeRankMethod?appName=" + appName, function (result) {
        option = {
            tooltip : {
                trigger: 'axis',
                formatter: function (params, ticket, callback) {
                    var index = params[0].dataIndex;
                    var array = result.full;
                    return array[index];
                }

            },
            legend: {
                data:['执行时间']
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    data : result.axis
                }
            ],
            yAxis : [
                {
                    type : 'value',
                    splitArea : {show : true},
                    axisLabel: {
                        formatter: '{value} ms'
                    }
                }
            ],
            series : [
                {
                    name:'执行时间',
                    type:'bar',
                    data:result.data
                }
            ]
        }
        chart.setOption(option);
        if (result.axis!=null && result.axis.length>0) {
            chart.hideLoading();
        }
    });
}
var loadMethodData = function () {
    var appName = $("#appNameSelect").val();
    var reTime = $("#reTimeSelect").val();
    loadMethodCountData(appName, reTime);
    loadMethodList(appName, reTime);
    loadMinuteCountMethodData(appName,reTime);
}