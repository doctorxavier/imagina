var liveBcnEdgesChart;
var liveBcnEdgesChartOptions = {
		chart : {
			renderTo : 'liveBcnEdgesChart',
			type : 'column',
			animation: false
		},
		title : {
			text : 'Edges Barcelona'
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

function pollLiveBcnEdges(data) {
	var edgeInfos = data.edgeInfos.info['bcn'].edgeInfos;
	var systemDatas = [];
	
	var i = 1;
	for(var j in edgeInfos) {
		
		var systemData = [];
		systemData.push("edge0" + i);
		systemData.push(edgeInfos["edge0" + i].numConLive);
		systemDatas.push(systemData);
		i++;
	}
	liveBcnEdgesChart.series[0].setData(systemDatas);
	
	liveBcnEdgesChart.redraw();
}

$(function() {	
	liveBcnEdgesChart = new Highcharts.Chart(liveBcnEdgesChartOptions);
});