var globalBcnConnChart;
var globalBcnConnChartOptions = {
		chart : {
			renderTo : 'globalBcnConnChart',
			type : 'area',
			animation: false
		},
		title : {
			text : 'Conexiones global Barcelona'
		},
		subtitle : {
			text : 'Global - WAN/Edge'
		},
		xAxis: {
			type: 'datetime',
            showEmpty : false,
            format: '{value:%H:%M:%S}',
            tickInterval: tickInterval * 1000,
        },
		yAxis : {
			title : {
				text : 'Conexiones'
			},
		},
		tooltip : {
			headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.0f} conexiones</b></td></tr>',
            footerFormat: '</table>',
			shared : true,
			useHTML : true
		},
		plotOptions : {
			area : {
				lineColor : '#666666',
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
			}
		}
	};

function loadGlobalBcnConn(data) {

	var infos = data.infos;
	var dataGlobal = [];
	var dataVodLive = [];
	
	for(var i in infos) {
		
		dataGlobal.push({});
		dataGlobal[i].x = infos[i].date;
		dataGlobal[i].y = infos[i].info['bcn'].numCon;
		
		dataVodLive.push({});
		dataVodLive[i].x = infos[i].date;
		dataVodLive[i].y = infos[i].info['bcn'].numConLive + infos[i].info['bcn'].numConVod;
	}
	
	globalBcnConnChartOptions.series = [];
	
	globalBcnConnChartOptions.series.push({});
	globalBcnConnChartOptions.series[0].name = "Global";
	globalBcnConnChartOptions.series[0].data = dataGlobal; 
	
	globalBcnConnChartOptions.series.push({});
	globalBcnConnChartOptions.series[1].name = "WAN/Edge";
	globalBcnConnChartOptions.series[1].data = dataVodLive;
	
	globalBcnConnChartOptions.rangeSelector = rangeSelector;
	
	//globalBcnConnChart = new Highcharts.Chart(globalBcnConnChartOptions);
	globalBcnConnChart = new Highcharts.StockChart(globalBcnConnChartOptions);

}

function pollGlobalBcnConn(data) {

	var dataGlobal = {};
	var dataVodLive = {};

	var now = new Date();
	dataGlobal.x = now.getTime(); // data.info.date;
	dataGlobal.y = data.info.info['bcn'].numCon;
	globalBcnConnChart.series[0].addPoint(dataGlobal, false, true);

	dataVodLive.x = now.getTime(); // data.info.date;
	dataVodLive.y = data.info.info['bcn'].numConLive + data.info.info['bcn'].numConVod;
	globalBcnConnChart.series[1].addPoint(dataVodLive, false, true);
	
	globalBcnConnChart.redraw();

}