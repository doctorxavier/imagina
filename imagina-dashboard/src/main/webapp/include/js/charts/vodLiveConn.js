var vodLiveConnChart;
var vodLiveConnOptions = {
		chart : {
			renderTo : 'vodLiveConnChart',
			type : 'area',
			animation: false
		},
		title : {
			text : 'On Demand + Live'
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
			}
		}
	};

function loadVodLiveConn(data) {
	var infos = data.infos;
	var dataBcn = [];
	var dataMad = [];
	
	for(var i in infos) {
		
		dataBcn.push({});
		dataBcn[i].x = infos[i].date;
		dataBcn[i].y = infos[i].info['bcn'].numConLive + infos[i].info['bcn'].numConVod;
		
		dataMad.push({});
		dataMad[i].x = infos[i].date;
		dataMad[i].y = infos[i].info['mad'].numConLive + infos[i].info['mad'].numConVod;
	}
	
	vodLiveConnOptions.series = [];
	
	vodLiveConnOptions.series.push({});
	vodLiveConnOptions.series[0].name = "Barcelona";
	vodLiveConnOptions.series[0].data = dataBcn; 
	
	vodLiveConnOptions.series.push({});
	vodLiveConnOptions.series[1].name = "Madrid";
	vodLiveConnOptions.series[1].data = dataMad;
	
	vodLiveConnOptions.rangeSelector = rangeSelector;
	
	//vodLiveConnChart = new Highcharts.Chart(vodLiveConnOptions);
	vodLiveConnChart = new Highcharts.StockChart(vodLiveConnOptions);
}

function pollVodLiveConn(data) {
	var dataBcn = {};
	var dataMad = {};

	var now = new Date();
	dataBcn.x = now.getTime(); // data.info.date;
	dataBcn.y = data.info.info['bcn'].numConLive + data.info.info['bcn'].numConVod;
	vodLiveConnChart.series[0].addPoint(dataBcn, false, true);

	dataMad.x = now.getTime(); // data.info.date;
	dataMad.y = data.info.info['mad'].numConLive + data.info.info['mad'].numConVod;
	vodLiveConnChart.series[1].addPoint(dataMad, false, true);
	
	vodLiveConnChart.redraw();
}