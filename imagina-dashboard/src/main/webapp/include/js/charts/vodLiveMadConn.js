var vodLiveMadConnChart;
var vodLiveMadConnChartOptions = {
		chart : {
			renderTo : 'vodLiveMadConnChart',
			type : 'area',
			animation: false
		},
		title : {
			text : 'On Demand + Live'
		},
		subtitle : {
			text : 'Madrid'
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

function loadVodLiveMadConn(data) {

	var infos = data.infos;
	var dataVod = [];
	var dataLive = [];
	
	for(var i in infos) {
		
		dataVod.push({});
		dataVod[i].x = infos[i].date;
		dataVod[i].y = infos[i].info['mad'].numConVod;
		
		dataLive.push({});
		dataLive[i].x = infos[i].date;
		dataLive[i].y = infos[i].info['mad'].numConLive;
	}
	
	vodLiveMadConnChartOptions.series = [];
	
	vodLiveMadConnChartOptions.series.push({});
	vodLiveMadConnChartOptions.series[0].name = "On Demand";
	vodLiveMadConnChartOptions.series[0].data = dataVod; 
	
	vodLiveMadConnChartOptions.series.push({});
	vodLiveMadConnChartOptions.series[1].name = "Live";
	vodLiveMadConnChartOptions.series[1].data = dataLive;
	
	vodLiveMadConnChartOptions.rangeSelector = rangeSelector;
	
	//vodLiveMadConnChart = new Highcharts.Chart(vodLiveMadConnChartOptions);
	vodLiveMadConnChart = new Highcharts.StockChart(vodLiveMadConnChartOptions);
	
}

function pollVodLiveMadConn(data) {

	var dataVod = {};
	var dataLive = {};

	var now = new Date();
	dataVod.x = now.getTime(); // data.info.date;
	dataVod.y = data.info.info['mad'].numConVod;
	vodLiveMadConnChart.series[0].addPoint(dataVod, false, true);

	dataLive.x = now.getTime(); // data.info.date;
	dataLive.y = data.info.info['mad'].numConLive;
	vodLiveMadConnChart.series[1].addPoint(dataLive, false, true);
	
	vodLiveMadConnChart.redraw();

}