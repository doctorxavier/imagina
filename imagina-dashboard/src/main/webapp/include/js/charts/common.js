//var range = 30 * 1000;// 10800 * 1000; // 3 hours
var tickInterval = 600; //600; // 10 minutes
var turboThreshold = 10001;
var pollDataConnTimer;

var rangeSelector = {
		buttons: [{
			count: 10,
			type: 'minute',
			text: '10M'
		}, {
			count: 30,
			type: 'minute',
			text: '30M'
		}, {
			count: 180,
			type: 'minute',
			text: '3H'
		}, {
			
			type: 'all',
			text: 'All'
		}],
		inputEnabled: false,
		selected: 0
	};

var dataGrouping = {
		dateTimeLabelFormats : {
			   millisecond: ['%A, %b %e, %H:%M:%S.%L', '%A, %b %e, %H:%M:%S.%L', '-%H:%M:%S.%L'],
			   second: ['%A, %b %e, %H:%M:%S', '%A, %b %e, %H:%M:%S', '-%H:%M:%S'],
			   minute: ['%A, %b %e, %H:%M', '%A, %b %e, %H:%M', '-%H:%M'],
			   hour: ['%A, %b %e, %H:%M', '%A, %b %e, %H:%M', '-%H:%M'],
			   day: ['%A, %b %e, %Y', '%A, %b %e', '-%A, %b %e, %Y'],
			   week: ['Week from %A, %b %e, %Y', '%A, %b %e', '-%A, %b %e, %Y'],
			   month: ['%B %Y', '%B', '-%B %Y'],
			   year: ['%Y', '%Y', '-%Y']
			}
	};

document.write('<script type="text/javascript" src="include/js/charts/clientConn.js"></script>');
document.write('<script type="text/javascript" src="include/js/charts/streaming.js"></script>');
document.write('<script type="text/javascript" src="include/js/charts/globalConn.js"></script>');
document.write('<script type="text/javascript" src="include/js/charts/vodLiveConn.js"></script>');
document.write('<script type="text/javascript" src="include/js/charts/globalBcnConn.js"></script>');
document.write('<script type="text/javascript" src="include/js/charts/globalMadConn.js"></script>');
document.write('<script type="text/javascript" src="include/js/charts/vodLiveBcnConn.js"></script>');
document.write('<script type="text/javascript" src="include/js/charts/vodLiveMadConn.js"></script>');
document.write('<script type="text/javascript" src="include/js/charts/bcnEdges.js"></script>');
document.write('<script type="text/javascript" src="include/js/charts/madEdges.js"></script>');
//document.write('<script type="text/javascript" src="include/js/charts/vodBcnEdges.js"></script>');
//document.write('<script type="text/javascript" src="include/js/charts/liveBcnEdges.js"></script>');
//document.write('<script type="text/javascript" src="include/js/charts/vodMadEdges.js"></script>');
//document.write('<script type="text/javascript" src="include/js/charts/liveMadEdges.js"></script>');

function pollDataSystems() {
	$.getJSON(serverUrl + "systems.json?callback=?", function(data, textStatus, jqXHR) {
		pollClientConn(data);
	}).done(function(data, textStatus, jqXHR) {
		//console.log("second success");
	}).fail(function(jqXHR,  textStatus, errorThrow) {
		 var err = textStatus + ", " + errorThrow;
		 console.log( "Request Failed: " + err );
	}).always(function(data) {
		//console.log("complete");
	});
}

function pollDataEdges() {
	$.getJSON(serverUrl + "edgeInfos.json?callback=?", function(data, textStatus, jqXHR) {
		pollBcnEdges(data);
		pollMadEdges(data);
		//pollVodBcnEdges(data);
		//pollLiveBcnEdges(data);
		//pollVodMadEdges(data);
		//pollLiveMadEdges(data);
	}).done(function(data, textStatus, jqXHR) {
		//console.log("second success");
	}).fail(function(jqXHR,  textStatus, errorThrow) {
		 var err = textStatus + ", " + errorThrow;
		 console.log( "Request Failed: " + err );
	}).always(function(data) {
		//console.log("complete");
	});
}

function pollDataConn() {
	$.getJSON(serverUrl + "info.json?callback=?", function(data, textStatus, jqXHR) {
	}).done(function(data, textStatus, jqXHR) {
		pollStreaming(data);
		pollGlobalConn(data);
		pollGlobalBcnConn(data);
		pollGlobalMadConn(data);
		pollVodLiveConn(data);
		pollVodLiveBcnConn(data);
		pollVodLiveMadConn(data);
	}).fail(function(jqXHR,  textStatus, errorThrow) {
		 var err = textStatus + ", " + errorThrow;
		 console.log( "Request Failed: " + err );
	}).always(function(data) {
		//console.log("complete poll");
	});
}

/*function loadDataRangeConn() {

	$.getJSON(serverUrl + "infos.json?callback=?&from=" + from, function(data, textStatus, jqXHR) {
		loadStreaming(data);
		loadVodLiveConn(data);
		loadVodLiveBcnConn(data);
		loadVodLiveMadConn(data);
		setInterval(function(){ pollDataConn(); }, refreshtime);
	}).done(function(data, textStatus, jqXHR) {
		//console.log("second success");
	}).fail(function(jqXHR,  textStatus, errorThrow) {
		 var err = textStatus + ", " + errorThrow;
		 console.log( "Request Failed: " + err );
	}).always(function(data) {
		//console.log("complete");
	});
}*/

function loadDataConn() {
	
	$.getJSON(serverUrl + "infos.json?callback=?&from=" + from, function(data, textStatus, jqXHR) {
		loadGlobalConn(data);
		loadGlobalBcnConn(data);
		loadGlobalMadConn(data);
		
		loadStreaming(data);
		loadVodLiveConn(data);
		loadVodLiveBcnConn(data);
		loadVodLiveMadConn(data);
		
		//loadDataRangeConn();
		
		pollDataConnTimer = setInterval(function(){ pollDataConn(); }, refreshtime);
	}).done(function(data, textStatus, jqXHR) {
		//console.log("second success");
	}).fail(function(jqXHR,  textStatus, errorThrow) {
		 var err = textStatus + ", " + errorThrow;
		 console.log( "Request Failed: " + err );
	}).always(function(data) {
		//console.log("complete");
	});
	
}

$(function() {	
	
	Highcharts.setOptions({
		global: {
			useUTC: false
		}
	});
	
	pollDataSystems();
	setInterval(function(){ pollDataSystems(); }, rrefreshtime);
	
	pollDataEdges();
	setInterval(function(){ pollDataEdges(); }, refreshtime);
	
	loadDataConn();
	
	$( "#btn3h" ).click(function() {
		from = FROMC;
		tickInterval = 600; // 10 minutes
		rangeSelector = {
				buttons: [{
					count: 10,
					type: 'minute',
					text: '10M'
				}, {
					count: 30,
					type: 'minute',
					text: '30M'
				}, {
					count: 180,
					type: 'minute',
					text: '3H'
				}, {
					
					type: 'all',
					text: 'All'
				}],
				inputEnabled: false,
				selected: 0
			};
		
		clearInterval(pollDataConnTimer);
		loadDataConn();
		//alert( "Handler for .3h.click() called." );
	});

	$( "#btnDay" ).click(function() {
		from = 87;
		tickInterval = 1800; // 30 minutes
		rangeSelector = {
				buttons: [{
					count: 30,
					type: 'minute',
					text: '30M'
				}, {
					count: 180,
					type: 'minute',
					text: '3H'
				}, {
					count: 360,
					type: 'minute',
					text: '6H'
				}, {
					
					type: 'all',
					text: 'All'
				}],
				inputEnabled: false,
				selected: 0
			};
		clearInterval(pollDataConnTimer);
		loadDataConn();
		//alert( "Handler for .day.click() called." );
	});

	$( "#btnWeek" ).click(function() {
		from = 605;
		tickInterval = 21600; // 6 hours
		rangeSelector = {
				buttons: [{
					count: 360,
					type: 'minute',
					text: '6H'
				}, {
					count: 720,
					type: 'minute',
					text: '12H'
				}, {
					count: 1,
					type: 'day',
					text: '1D'
				}, {
					
					type: 'all',
					text: 'All'
				}],
				inputEnabled: false,
				selected: 0
			};
		clearInterval(pollDataConnTimer);
		loadDataConn();
		//alert( "Handler for .week.click() called." );
	});

	$( "#btnMonth" ).click(function() {
		from = 2592;
		tickInterval = 43200; // 12 hour
		rangeSelector = {
				buttons: [{
					count: 1,
					type: 'day',
					text: '1D'
				}, {
					count: 1,
					type: 'week',
					text: '1W'
				}, {
					count: 2,
					type: 'week',
					text: '2W'
				}, {
					
					type: 'all',
					text: 'All'
				}],
				inputEnabled: false,
				selected: 0
			};
		clearInterval(pollDataConnTimer);
		loadDataConn();
		//alert( "Handler for .month.click() called." );
	});
});

