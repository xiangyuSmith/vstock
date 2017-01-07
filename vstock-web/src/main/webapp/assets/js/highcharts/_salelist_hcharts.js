$(function() {
    Highcharts.setOptions({
        timezoneOffset: -8
    });
});

function saleHcharGet(Url) {
    $.getJSON(Url, function (data) {
        $('#containerSale').highcharts({
            chart: {
                type: 'line',
                panning: true,
                panKey: 'shift'
            },
            title: {
                text: ''
            },
            xAxis: {
                // labels: {
                //     enabled: false
                // },
                gridLineColor: '#ADF29F',
                lineColor: '#ADF29F',
                type: 'datetime',
                minTickInterval: Number,
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
                // allowDecimals: false,
                // tickInterval: 10000,
                // min:0,
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
                name: '订单金额',
                data: data
            }]
        });
    });
}
