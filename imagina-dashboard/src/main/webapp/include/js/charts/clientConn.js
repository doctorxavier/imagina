var clientConnChart;
var clientConnChartOptions = {
		chart: {
			renderTo : 'clientConnChart',
	        plotBackgroundColor: null,
	        plotBorderWidth: null,
	        plotShadow: false
	    },
	    legend: {
	    	backgroundColor: 'white'
	    },
	    title: {
	        text: 'Número de conexiones totales.'
	    },
	    tooltip: {
	    	headerFormat: '',
		    pointFormat: '<b>{point.name}: </b>{point.percentage:.1f}%'
	    },
	    plotOptions: {
	        pie: {
	            allowPointSelect: true,
	            cursor: 'pointer',
	            dataLabels: {
	                enabled: false
	            },
	            showInLegend: true
	        }
	    },
	    series: [{
	        type: 'pie',
	        name: 'Cliente'
	    }]
	};


function pollClientConn(data) {
	var systems = data.systems;	
	var systemDatas = [];
	
	for(var i in systems) {
		var systemData = [];
		systemData.push(systems[i].name);
		systemData.push(systems[i].count);
		systemDatas.push(systemData);
	}
	clientConnChart.series[0].setData(systemDatas);
	clientConnChart.redraw();
}

$(function() {	
	clientConnChart = new Highcharts.Chart(clientConnChartOptions);
});