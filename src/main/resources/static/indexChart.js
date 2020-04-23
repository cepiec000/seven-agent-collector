var loadThreadChartMap = function (appName, reTime) {
    var threadChart = echarts.init(document.getElementById("threadChart"),'macarons');
    threadChart.showLoading();
    $.get("/jvm/getJvmThreadCountInfo?appName=" + appName + "&reTime=" + reTime, function (result) {
        option = {
            title: {
                text: 'Thread count',
                subtext: '平均值 ' + result.average
            },
            tooltip: {
                trigger: 'axis'
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: result.axis
            },
            yAxis: {
                type: 'value'
            },
            series: [
                {
                    name: '线程数',
                    type: 'line',
                    stack: '总量',
                    data: result.data
                }
            ]
        };
        threadChart.setOption(option);
        if (result.axis!=null && result.axis.length>0) {
            threadChart.hideLoading();
        }
    });
}

var loadHeapMemoryMap = function (appName, reTime) {
    var heapMemoryChart = echarts.init(document.getElementById("heapMemoryChart"),'macarons');
    heapMemoryChart.showLoading();
    $.get("/jvm/getJvmHeadMemoryData?appName=" + appName + "&reTime=" + reTime, function (result) {
        optionHeadMemory = {
            title: {
                text: 'Heap memory usage (MB)'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: ['Max Heap', 'Used Heap', 'Committed Heap']
            },
            toolbox: {
                show: true
            },
            calculable: true,
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: false,
                    data: result.axis
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    axisLabel: {
                        formatter: '{value} MB'
                    }
                }
            ],
            series: [
                {
                    name: 'Used Heap',
                    type: 'line',
                    data: result.used
                },
                {
                    name: 'Max Heap',
                    type: 'line',
                    data: result.max
                },
                {
                    name: 'Committed Heap',
                    type: 'line',
                    data: result.committed
                }
            ]
        };


        heapMemoryChart.setOption(optionHeadMemory);
        if (result.axis!=null && result.axis.length>0) {
            heapMemoryChart.hideLoading();
        }
    });

}

var loadNonHeapMemoryMap = function (appName, reTime) {
    var nonHeapMemoryChart = echarts.init(document.getElementById("nonHeapMemoryChart"),'macarons');
    nonHeapMemoryChart.showLoading();
    $.get("/jvm/getJvmNonHeadMemoryData?appName=" + appName + "&reTime=" + reTime, function (result) {

        optionNonHeadMemory = {
            title: {
                text: 'Non Heap memory usage (MB)'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: ['Max Heap', 'Used Heap', 'Committed Heap']
            },
            toolbox: {
                show: true
            },
            calculable: true,
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: false,
                    data: result.axis
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    axisLabel: {
                        formatter: '{value} MB'
                    }
                }
            ],
            series: [
                {
                    name: 'Used Heap',
                    type: 'line',
                    data: result.used
                },
                {
                    name: 'Max Heap',
                    type: 'line',
                    data: result.max
                },
                {
                    name: 'Committed Heap',
                    type: 'line',
                    data: result.committed
                }
            ]
        };


        nonHeapMemoryChart.setOption(optionNonHeadMemory);
        if (result.axis!=null && result.axis.length>0) {
            nonHeapMemoryChart.hideLoading();
        }
    });

}

var loadEdenSpaceMap = function (appName, reTime) {
    var edenSpaceChart = echarts.init(document.getElementById("edenSpaceChart"),'macarons');
    edenSpaceChart.showLoading();
    $.get("/jvm/getEdenSpaceData?appName=" + appName + "&reTime=" + reTime, function (result) {
        edenSpaceOption = {
            title: {
                text: 'Eden Spac (MB)'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: ['Max Heap', 'Used Heap', 'Committed Heap']
            },
            toolbox: {
                show: true
            },
            calculable: true,
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: false,
                    data: result.axis
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    axisLabel: {
                        formatter: '{value} MB'
                    }
                }
            ],
            series: [
                {
                    name: 'Used Heap',
                    type: 'line',
                    data: result.used
                },
                {
                    name: 'Max Heap',
                    type: 'line',
                    data: result.max
                },
                {
                    name: 'Committed Heap',
                    type: 'line',
                    data: result.committed
                }
            ]
        };


        edenSpaceChart.setOption(edenSpaceOption);
        if (result.axis!=null && result.axis.length>0) {
            edenSpaceChart.hideLoading();
        }
    });

}

var loadSurvivorSpaceMap = function (appName, reTime) {
    var survivorSpaceChart = echarts.init(document.getElementById("survivorSpaceChart"),'macarons');
    survivorSpaceChart.showLoading();
    $.get("/jvm/getSurvivorSpaceData?appName=" + appName + "&reTime=" + reTime, function (result) {
        survivorSpaceOption = {
            title: {
                text: 'Survivor Spac (MB)'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: ['Max Heap', 'Used Heap', 'Committed Heap']
            },
            toolbox: {
                show: true
            },
            calculable: true,
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: false,
                    data: result.axis
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    axisLabel: {
                        formatter: '{value} MB'
                    }
                }
            ],
            series: [
                {
                    name: 'Used Heap',
                    type: 'line',
                    data: result.used
                },
                {
                    name: 'Max Heap',
                    type: 'line',
                    data: result.max
                },
                {
                    name: 'Committed Heap',
                    type: 'line',
                    data: result.committed
                }
            ]
        };


        survivorSpaceChart.setOption(survivorSpaceOption);
        if (result.axis!=null && result.axis.length>0) {
            survivorSpaceChart.hideLoading();
        }
    });
}
var loadOldGenMap = function (appName, reTime) {
    var oldGenChart = echarts.init(document.getElementById("oldGenChart"),'macarons');
    oldGenChart.showLoading();
    $.get("/jvm/getOldGenData?appName=" + appName + "&reTime=" + reTime, function (result) {
        oldGenOption = {
            title: {
                text: 'Old Gen (MB)'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: ['Max Heap', 'Used Heap', 'Committed Heap']
            },
            toolbox: {
                show: true
            },
            calculable: true,
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: false,
                    data: result.axis
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    axisLabel: {
                        formatter: '{value} MB'
                    }
                }
            ],
            series: [
                {
                    name: 'Used Heap',
                    type: 'line',
                    data: result.used
                },
                {
                    name: 'Max Heap',
                    type: 'line',
                    data: result.max
                },
                {
                    name: 'Committed Heap',
                    type: 'line',
                    data: result.committed
                }
            ]
        };

        oldGenChart.setOption(oldGenOption);
        if (result.axis!=null && result.axis.length>0) {
            oldGenChart.hideLoading();
        }
    });

}

var loadPremGenMap = function (appName, reTime) {
    var permGenChart = echarts.init(document.getElementById("permGenChart"),'macarons');
    permGenChart.showLoading();
    $.get("/jvm/getPermGenData?appName=" + appName + "&reTime=" + reTime, function (result) {
        permGenOption = {
            title: {
                text: 'Metaspace (MB)'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: ['Max Heap', 'Used Heap', 'Committed Heap']
            },
            toolbox: {
                show: true
            },
            calculable: true,
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: false,
                    data: result.axis
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    axisLabel: {
                        formatter: '{value} MB'
                    }
                }
            ],
            series: [
                {
                    name: 'Used Heap',
                    type: 'line',
                    data: result.used
                },
                {
                    name: 'Max Heap',
                    type: 'line',
                    data: result.max
                },
                {
                    name: 'Committed Heap',
                    type: 'line',
                    data: result.committed
                }
            ]
        };

        permGenChart.setOption(permGenOption);
        if (result.axis!=null && result.axis.length>0) {
            permGenChart.hideLoading();
        }
    });

}

var loadClassCountMap = function (appName, reTime) {

    var classCountChart = echarts.init(document.getElementById("classCountChart"),'macarons');
    classCountChart.showLoading();
    $.get("/jvm/getClassCountData?appName=" + appName + "&reTime=" + reTime, function (result) {
        classCountOption = {
            title: {
                text: 'Class Count (KB)'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: ['loaded', 'total loaded', 'unload']
            },
            toolbox: {
                show: true
            },
            calculable: true,
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: false,
                    data: result.axis
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    axisLabel: {
                        formatter: '{value} KB'
                    }
                }
            ],
            series: [
                {
                    name: 'loaded',
                    type: 'line',
                    data: result.loaded
                },
                {
                    name: 'total loaded',
                    type: 'line',
                    data: result.totalLoaded
                },
                {
                    name: 'unload',
                    type: 'line',
                    data: result.unload
                }
            ]
        };

        classCountChart.setOption(classCountOption);
        if (result.axis!=null && result.axis.length>0) {
            classCountChart.hideLoading();
        }
    });

}

var loadJvmData = function () {
    var appName = $("#appNameSelect").val();
    var reTime = $("#reTimeSelect").val();
    loadThreadChartMap(appName, reTime);
    loadHeapMemoryMap(appName, reTime);
    loadNonHeapMemoryMap(appName, reTime);
    loadEdenSpaceMap(appName, reTime);
    loadSurvivorSpaceMap(appName, reTime);
    loadOldGenMap(appName, reTime);
    loadPremGenMap(appName, reTime);
    loadClassCountMap(appName, reTime);
}