var vodLiveBcnConnChart;
var vodLiveBcnConnChartOptions = {
		chart : {
			renderTo : 'vodLiveBcnConnChart',
			type : 'area',
			animation: false
		},
		title : {
			text : 'On Demand + Live'
		},
		subtitle : {
			text : 'Barcelona'
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
			}
		},
		tooltip : {
			shared : true,
			valueSuffix : ' conexiones'
		},
		plotOptions : {
			area : {
				stacking : 'normal',
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

function loadVodLiveBcnConn(data) {

	var infos = data.infos;
	var dataVod = [];
	var dataLive = [];
	
	for(var i in infos) {
		
		dataVod.push({});
		dataVod[i].x = infos[i].date;
		dataVod[i].y = infos[i].info['bcn'].numConVod;
		
		dataLive.push({});
		dataLive[i].x = infos[i].date;
		dataLive[i].y = infos[i].info['bcn'].numConLive;
	}
	
	vodLiveBcnConnChartOptions.series = [];
	
	vodLiveBcnConnChartOptions.series.push({});
	vodLiveBcnConnChartOptions.series[0].name = "On Demand";
	vodLiveBcnConnChartOptions.series[0].data = dataVod; 
	
	vodLiveBcnConnChartOptions.series.push({});
	vodLiveBcnConnChartOptions.series[1].name = "Live";
	vodLiveBcnConnChartOptions.series[1].data = dataLive;
	
	vodLiveBcnConnChartOptions.rangeSelector = rangeSelector;
	
	//vodLiveBcnConnChart = new Highcharts.Chart(vodLiveBcnConnChartOptions);
	vodLiveBcnConnChart = new Highcharts.StockChart(vodLiveBcnConnChartOptions);
	
}

function pollVodLiveBcnConn(data) {

	var dataVod = {};
	var dataLive = {};

	var now = new Date();
	dataVod.x = now.getTime(); // data.info.date;
	dataVod.y = data.info.info['bcn'].numConVod;
	vodLiveBcnConnChart.series[0].addPoint(dataVod, false, true);

	dataLive.x = now.getTime(); // data.info.date;
	dataLive.y = data.info.info['bcn'].numConLive;
	vodLiveBcnConnChart.series[1].addPoint(dataLive, false, true);
	
	vodLiveBcnConnChart.redraw();

}