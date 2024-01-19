var liveMadEdgesChart;
var liveMadEdgesChartOptions = {
		chart : {
			renderTo : 'liveMadEdgesChart',
			type : 'column',
			animation: false
		},
		title : {
			text : 'Edges Madrid'
		},
		subtitle : {
			text : 'Live'
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
			headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.0f} conexiones</b></td></tr>',
            footerFormat: '</table>',
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

function pollLiveMadEdges(data) {
	var edgeInfos = data.edgeInfos.info['mad'].edgeInfos;
	var systemDatas = [];
	
	var i = 1;
	for(var j in edgeInfos) {
		
		var systemData = [];
		systemData.push("edge0" + i);
		systemData.push(edgeInfos["edge0" + i].numConLive);
		systemDatas.push(systemData);
		i++;
	}
	liveMadEdgesChart.series[0].setData(systemDatas);
	
	liveMadEdgesChart.redraw();
}

$(function() {	
	liveMadEdgesChart = new Highcharts.Chart(liveMadEdgesChartOptions);
});