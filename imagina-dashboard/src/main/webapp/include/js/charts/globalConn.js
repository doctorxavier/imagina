var globalConnChart;
var globalConnChartOptions = {
		chart : {
			renderTo : 'globalConnChart',
			type : 'area', 
			animation: false
		},
		title : {
			text : 'Conexiones Global'
		},
		subtitle : {
			text : 'BCN/MAD'
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
			}, 
			areaspline : {
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

function loadGlobalConn(data) {
	var infos = data.infos;
	var dataBcn = [];
	var dataMad = [];
	
	for(var i in infos) {
		
		dataBcn.push({});
		dataBcn[i].x = infos[i].date;
		dataBcn[i].y = infos[i].info['bcn'].numCon;
		
		dataMad.push({});
		dataMad[i].x = infos[i].date;
		dataMad[i].y = infos[i].info['mad'].numCon;
	}
	
	globalConnChartOptions.series = [];
	
	globalConnChartOptions.series.push({});
	globalConnChartOptions.series[0].name = "Barcelona";
	globalConnChartOptions.series[0].data = dataBcn; 
	
	globalConnChartOptions.series.push({});
	globalConnChartOptions.series[1].name = "Madrid";
	globalConnChartOptions.series[1].data = dataMad;
	
	globalConnChartOptions.rangeSelector = rangeSelector;
	
	//globalConnChart = new Highcharts.Chart(globalConnChartOptions);
	globalConnChart = new Highcharts.StockChart(globalConnChartOptions);
}

function pollGlobalConn(data) {
	var dataBcn = {};
	var dataMad = {};

	var now = new Date();
	dataBcn.x = now.getTime(); // data.info.date;
	dataBcn.y = data.info.info['bcn'].numCon;
	globalConnChart.series[0].addPoint(dataBcn, false, true);

	dataMad.x = now.getTime(); // data.info.date;
	dataMad.y = data.info.info['mad'].numCon;
	globalConnChart.series[1].addPoint(dataMad, false, true);
	
	globalConnChart.redraw();
}
