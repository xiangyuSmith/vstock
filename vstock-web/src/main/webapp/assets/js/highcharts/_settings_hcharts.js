$(function(){
    Highcharts.setOptions({
        timezoneOffset: -8
    });
    $.getJSON('/index/brandMarket?brand=JORDAN', function (data) {
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
                gridLineColor: '#ADF29F',
                lineColor: '#ADF29F',
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
                borderColor: '#ADF29F',
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
                            [0, Highcharts.getOptions().colors[2]],
                            [1, Highcharts.Color(Highcharts.getOptions().colors[2]).setOpacity(0.5).get('rgba')]
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
                color: '#ADF29F',
                type: 'area',
                name: '销售总额',
                data: data
            }]
        });
    });

    $.getJSON('/index/brandMarket?brand=NIKE', function (data) {
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
                gridLineColor: '#F78181',
                lineColor: '#F78181',
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
                borderColor: '#F78181',
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
                            [0, Highcharts.getOptions().colors[8]],
                            [1, Highcharts.Color(Highcharts.getOptions().colors[8]).setOpacity(0.5).get('rgba')]
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
                color: '#F78181',
                type: 'area',
                name: '销售总额',
                data: data
            }]
        });
    });

    $.getJSON('/index/brandMarket?brand=YEZZY', function (data) {
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
                            [0, Highcharts.getOptions().colors[0]],
                            [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
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
                type: 'area',
                name: '销售总额',
                data: data
            }]
        });
    });




    /** end **/
});