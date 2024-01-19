var streamingChart;
var streamingChartOptions = {
		chart : {
			renderTo : 'streamingChart',
			animation: false
		},
        title: {
            text: 'BCN/MAD Streaming',
        },
        xAxis: {
            type: 'datetime',
            showEmpty : false,
            format: '{value:%H:%M:%S}',
            tickInterval: tickInterval * 1000,
        },
        yAxis : {
			title : {
				text : 'Mbps'
			},
		},
		tooltip : {
			headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.2f} Mbps</b></td></tr>',
            footerFormat: '</table>',
			shared : true,
			useHTML : true
		},
		plotOptions : {
			line : {
				//lineColor : '#666666',
				lineWidth : 1,
				turboThreshold : turboThreshold,
				marker : {
					enabled : false,
					symbol : 'circle',
					radius : 1,
					states : {
						hover : {
							enabled : true
						}
					}
				},
				dataGrouping : dataGrouping
			},
			area : {
				lineColor : '#666666',
				fillOpacity : 0.30,
				lineWidth : 1,
				turboThreshold : turboThreshold,
				marker : {
					enabled : false,
					symbol : 'circle',
					radius : 1,
					states : {
						hover : {
							enabled : true
						}
					}
				},
				dataGrouping : {
					dateTimeLabelFormats : {
						millisecond : [ '%H:%M:%S.%L' ],
						second : [ '%H:%M:%S' ],
						minute : [ '%H:%M' ],
						hour : [ '%d-%m-%Y<br/>%H:%M' ],
						day : [ '%Y<br/>%d-%m' ],
						week : [ '%Y<br/>%d-%m' ],
						month : [ '%m-%Y' ],
						year : [ '%Y' ]
					}
				}
			}
		}
    };

function loadStreaming(data) {
	var infos = data.infos;
	
	var dataUp = [];
	var dataDown = [];
	
	var dataBcnUp = [];
	var dataMadUp = [];
	
	var dataBcnDown = [];
	var dataMadDown = [];
	
	i = 0;
	for(var j in infos) {
		
		dataUp.push({});
		dataUp[i].x = infos[j].date;
		dataUp[i].y = infos[j].info['bcn'].upload + infos[j].info['mad'].upload;
		
		dataDown.push({});
		dataDown[i].x = infos[j].date;
		dataDown[i].y = infos[j].info['bcn'].download + infos[j].info['mad'].download;
		
		dataBcnUp.push({});
		dataBcnUp[i].x = infos[j].date;
		dataBcnUp[i].y = infos[j].info['bcn'].upload;
		
		dataMadUp.push({});
		dataMadUp[i].x = infos[j].date;
		dataMadUp[i].y = infos[j].info['mad'].upload;
		
		dataBcnDown.push({});
		dataBcnDown[i].x = infos[j].date;
		dataBcnDown[i].y = infos[j].info['bcn'].download;
		
		dataMadDown.push({});
		dataMadDown[i].x = infos[j].date;
		dataMadDown[i].y = infos[j].info['mad'].download;
		
		i++;
	}
	
	streamingChartOptions.series = [];
	
	streamingChartOptions.series.push({});
	streamingChartOptions.series[0].type = 'area';
	streamingChartOptions.series[0].name = "Global Upload";
	streamingChartOptions.series[0].data = dataUp;
	
	streamingChartOptions.series.push({});
	streamingChartOptions.series[1].type = 'area';
	streamingChartOptions.series[1].name = "Global Download";
	streamingChartOptions.series[1].data = dataDown;
	
	streamingChartOptions.series.push({});
	streamingChartOptions.series[2].name = "Barcelona Upload";
	streamingChartOptions.series[2].data = dataBcnUp; 
	
	streamingChartOptions.series.push({});
	streamingChartOptions.series[3].name = "Madrid Upload";
	streamingChartOptions.series[3].data = dataMadUp;
	
	streamingChartOptions.series.push({});
	streamingChartOptions.series[4].name = "Barcelona Download";
	streamingChartOptions.series[4].data = dataBcnDown; 
	
	streamingChartOptions.series.push({});
	streamingChartOptions.series[5].name = "Madrid Download";
	streamingChartOptions.series[5].data = dataMadDown;
	
	streamingChartOptions.rangeSelector = rangeSelector;
	
	//streamingChart = new Highcharts.Chart(streamingChartOptions);
	streamingChart = new Highcharts.StockChart(streamingChartOptions);
}

function pollStreaming(data) {
	var dataBcnUp = {};
	var dataMadUp = {};
	
	var dataBcnDown = {};
	var dataMadDown = {};
	
	var dataUp = {};
	var dataDown = {};

	var now = new Date();
	
	dataUp.x = now.getTime(); // data.info.date;
	dataUp.y = data.info.info['bcn'].upload + data.info.info['mad'].upload;
	streamingChart.series[0].addPoint(dataUp, false, true);
	
	dataDown.x = now.getTime(); // data.info.date;
	dataDown.y = data.info.info['bcn'].download + data.info.info['mad'].download;
	streamingChart.series[1].addPoint(dataDown, false, true);
	
	dataBcnUp.x = now.getTime(); // data.info.date;
	dataBcnUp.y = data.info.info['bcn'].upload;
	streamingChart.series[2].addPoint(dataBcnUp, false, true);

	dataMadUp.x = now.getTime(); // data.info.date;
	dataMadUp.y = data.info.info['mad'].upload;
	streamingChart.series[3].addPoint(dataMadUp, false, true);
	
	dataBcnDown.x = now.getTime(); // data.info.date;
	dataBcnDown.y = data.info.info['bcn'].download;
	streamingChart.series[4].addPoint(dataBcnDown, false, true);

	dataMadDown.x = now.getTime(); // data.info.date;
	dataMadDown.y = data.info.info['mad'].download;
	streamingChart.series[5].addPoint(dataMadDown, false, true);
	
	streamingChart.redraw();
}