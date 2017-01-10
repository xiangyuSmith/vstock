$(function(){
    Highcharts.setOptions({
        timezoneOffset: -8
    });
    var maxColor = '#F78181';
    var minColor = '#ADF29F';
    var result = "";
    var resultNum = 2;
    $.getJSON('/index/brandMarket?brand=JORDAN', function (data) {
        var s = parseInt(data[data.length-1].y) - parseInt(data[data.length-2].y);
        if( s > 0){
            result = maxColor;
            resultNum = 8;
        }else{
            result = minColor;
            resultNum = 2;
        }
        $('#containerA').highcharts({
            chart: {
                type: 'line',
                panning: true,
                panKey: 'shift'
            },
            title: {
                text: ''
            },
            xAxis: {
                labels: {
                    enabled: false
                },
                gridLineColor: result,
                lineColor: result,
                type: 'datetime',
                tickWidth: 0,
                dateTimeLabelFormats: {
                    millisecond: '%H:%M:%S.%L',
                    second: '%H:%M:%S',
                    minute: '%H:%M',
                    hour: '%H:%M',
                    day: '%m-%d',
                    week: '%m-%d',
                    month: '%Y-%m',
                    year: '%Y'
                }
            },
            tooltip: {
                borderColor: result,
                dateTimeLabelFormats: {
                    millisecond: '%H:%M:%S.%L',
                    second: '%H:%M:%S',
                    minute: '%H:%M',
                    hour: '%H:%M',
                    day: '%Y-%m-%d',
                    week: '%m-%d',
                    month: '%Y-%m',
                    year: '%Y'
                }
            },
            yAxis: {
                labels: {
                    enabled: false
                },
                title: {
                    text: ''
                }
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
            },
            credits: {
                enabled: false // 禁用版权信息
            },
            plotOptions: {
                area: {
                    fillColor: {
                        linearGradient: {
                            x1: 0,
                            y1: 0,
                            x2: 0,
                            y2: 1
                        },
                        stops: [
                            [0, Highcharts.getOptions().colors[resultNum]],
                            [1, Highcharts.Color(Highcharts.getOptions().colors[resultNum]).setOpacity(0.5).get('rgba')]
                        ]
                    },
                    marker: {
                        radius: 2
                    },
                    lineWidth: 1,
                    states: {
                        hover: {
                            lineWidth: 1
                        }
                    },
                    threshold: null
                }
            },
            series: [{
                color: result,
                type: 'area',
                name: '销售总额',
                data: data
            }]
        });
    });

    $.getJSON('/index/brandMarket?brand=NIKE', function (data) {
        var s = parseInt(data[data.length-1].y) - parseInt(data[data.length-2].y);
        if( s > 0){
            result = maxColor;
            resultNum = 8;
        }else{
            result = minColor;
            resultNum = 2;
        }
        $('#containerB').highcharts({
            chart: {
                type: 'line',
                panning: true,
                panKey: 'shift'
            },
            title: {
                text: ''
            },
            xAxis: {
                labels: {
                    enabled: false
                },
                gridLineColor: result,
                lineColor: result,
                type: 'datetime',
                tickWidth: 0,
                dateTimeLabelFormats: {
                    millisecond: '%H:%M:%S.%L',
                    second: '%H:%M:%S',
                    minute: '%H:%M',
                    hour: '%H:%M',
                    day: '%m-%d',
                    week: '%m-%d',
                    month: '%Y-%m',
                    year: '%Y'
                }
            },
            tooltip: {
                borderColor: result,
                dateTimeLabelFormats: {
                    millisecond: '%H:%M:%S.%L',
                    second: '%H:%M:%S',
                    minute: '%H:%M',
                    hour: '%H:%M',
                    day: '%Y-%m-%d',
                    week: '%m-%d',
                    month: '%Y-%m',
                    year: '%Y'
                }
            },
            yAxis: {
                labels: {
                    enabled: false
                },
                title: {
                    text: ''
                }
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
            },
            credits: {
                enabled: false // 禁用版权信息
            },
            plotOptions: {
                area: {
                    fillColor: {
                        linearGradient: {
                            x1: 0,
                            y1: 0,
                            x2: 0,
                            y2: 1
                        },
                        stops: [
                            [0, Highcharts.getOptions().colors[resultNum]],
                            [1, Highcharts.Color(Highcharts.getOptions().colors[resultNum]).setOpacity(0.5).get('rgba')]
                        ]
                    },
                    marker: {
                        radius: 2
                    },
                    lineWidth: 1,
                    states: {
                        hover: {
                            lineWidth: 1
                        }
                    },
                    threshold: null
                }
            },
            series: [{
                color: result,
                type: 'area',
                name: '销售总额',
                data: data
            }]
        });
    });

    $.getJSON('/index/brandMarket?brand=ADIDAS', function (data) {
        var s = parseInt(data[data.length-1].y) - parseInt(data[data.length-2].y);
        if( s > 0){
            result = maxColor;
            resultNum = 8;
        }else{
            result = minColor;
            resultNum = 2;
        }
        $('#containerC').highcharts({
            chart: {
                type: 'line',
                panning: true,
                panKey: 'shift'
            },
            title: {
                text: ''
            },
            xAxis: {
                labels: {
                    enabled: false
                },
                gridLineColor: result,
                lineColor: result,
                type: 'datetime',
                tickWidth:0,
                dateTimeLabelFormats: {
                    millisecond: '%H:%M:%S.%L',
                    second: '%H:%M:%S',
                    minute: '%H:%M',
                    hour: '%H:%M',
                    day: '%m-%d',
                    week: '%m-%d',
                    month: '%Y-%m',
                    year: '%Y'
                }
            },
            tooltip: {
                borderColor: result,
                dateTimeLabelFormats: {
                    millisecond: '%H:%M:%S.%L',
                    second: '%H:%M:%S',
                    minute: '%H:%M',
                    hour: '%H:%M',
                    day: '%Y-%m-%d',
                    week: '%m-%d',
                    month: '%Y-%m',
                    year: '%Y'
                }
            },
            yAxis: {
                labels: {
                    enabled: false
                },
                title: {
                    text: ''
                }
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled:false
            },
            credits:{
                enabled:false // 禁用版权信息
            },
            plotOptions: {
                area: {
                    fillColor: {
                        linearGradient: {
                            x1: 0,
                            y1: 0,
                            x2: 0,
                            y2: 1
                        },
                        stops: [
                            [0, Highcharts.getOptions().colors[resultNum]],
                            [1, Highcharts.Color(Highcharts.getOptions().colors[resultNum]).setOpacity(0.5).get('rgba')]
                        ]
                    },
                    marker: {
                        radius: 2
                    },
                    lineWidth: 1,
                    states: {
                        hover: {
                            lineWidth: 1
                        }
                    },
                    threshold: null
                }
            },
            series: [{
                color: result,
                type: 'area',
                name: '销售总额',
                data: data
            }]
        });
    });




    /** end **/
});