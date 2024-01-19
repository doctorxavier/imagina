var madEdgesChart;
var madEdgesChartOptions = {
		chart : {
			renderTo : 'madEdgesChart',
			type : 'column',
			animation: false
		},
		title : {
			text : 'Edges Madrid'
		},
		subtitle : {
			text : 'LIVE + VOD'
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
			enabled : true
		},
		tooltip : {
			headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y} conexiones</b></td></tr>',
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
		series : [ {name: 'Live'}, {name:'Vod'} ]
	};

function pollMadEdges(data) {
	var edgeInfos = data.edgeInfos.info['mad'].edgeInfos;
	var systemLiveDatas = [];
	var systemVodDatas = [];
	
	var i = 1;
	for(var j in edgeInfos) {
		
		var systemDataLive = [];
		var systemDataVod = [];
		
		systemDataLive.push("edge0" + i);
		systemDataLive.push(edgeInfos["edge0" + i].numConLive);
		systemLiveDatas.push(systemDataLive);
		
		systemDataVod.push("edge0" + i);
		systemDataVod.push(edgeInfos["edge0" + i].numConVod);
		systemVodDatas.push(systemDataVod);
		i++;
	}
	
	if( i > 1 ) {
		madEdgesChart.series[0].setData(systemLiveDatas);
		madEdgesChart.series[1].setData(systemVodDatas)
	
		madEdgesChart.redraw();
	}
}

$(function() {	
	madEdgesChart = new Highcharts.Chart(madEdgesChartOptions);
});