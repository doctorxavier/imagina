var vodMadEdgesChart;
var vodMadEdgesChartOptions = {
		chart : {
			renderTo : 'vodMadEdgesChart',
			type : 'column',
			animation: false
		},
		title : {
			text : 'Edges Madrid'
		},
		subtitle : {
			text : 'VOD'
		},
		xAxis : {
			type : 'category',
			labels : {
				rotation : -45
			},
		},
		yAxis : {
			title : {
				text : 'Conexiones'
			},
		},
		legend : {
			enabled : false
		},
		tooltip : {
			headerFormat : 	'<span style="color:{series.color};padding:0">{point.key}: </span>' +
							'<span><b>{point.y}</b> conexiones</span>',
			pointFormat : '',
			footerFormat : '',
			shared : true,
			useHTML : true
		},
		plotOptions : {
			column : {
				pointPadding : 0.2,
				borderWidth : 0
			}
		},
		series : [{}]
	};

function pollVodMadEdges(data) {
	var edgeInfos = data.edgeInfos.info['mad'].edgeInfos;
	var systemDatas = [];
	
	var i = 1;
	for(var j in edgeInfos) {
		
		var systemData = [];
		systemData.push("edge0" + i);
		systemData.push(edgeInfos["edge0" + i].numConVod);
		systemDatas.push(systemData);
		i++;
	}
	vodMadEdgesChart.series[0].setData(systemDatas);
	
	vodMadEdgesChart.redraw();
}

$(function() {	
	vodMadEdgesChart = new Highcharts.Chart(vodMadEdgesChartOptions);
});