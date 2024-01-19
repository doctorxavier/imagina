var url = window.location.protocol+'//'+window.location.hostname+(window.location.port ? ':'+window.location.port: '');
//console.debug(url);

var FROMC = 15;
var from = FROMC;
var serverUrl = url + "/imagina-dashboard/server/";
var refreshtime = from * 1000;
var rrefreshtime = 5 * 1000;

document.write('<script type="text/javascript" src="include/js/charts/common.js"></script>');

function relocates(data) {
	var rows = data.widgets;
	for ( var i in rows) {
		var cols = rows[i];
		for(var j in cols) {
			var widgets = cols[j];
			for(var z in widgets) {
				var widget = widgets[z];
				
				var widget = $('#' + widget.id);
				widget.remove();
				
				relocate(widget, i, j);	
				
			}
		}
	}
}

function relocate(widget, row, col) {
	var rows = $('.container-fluid').find('.row');
	rows.each(function(indexi) {
		var cols = $(this).find('.ui-sortable');
		cols.each(function(indexj) {							
			if(row == indexi && col == indexj) {
				//console.debug(widget);
				$(this).append(widget);
			}
		});
	});
}

function convertToText(obj) {

    var string = [];

    if (typeof(obj) == "object" && (obj.join == undefined)) {
        string.push("{");
        for (prop in obj) {
            string.push(prop, ": ", convertToText(obj[prop]), ",");
        };
        string.push("}");

    } else if (typeof(obj) == "object" && !(obj.join == undefined)) {
        string.push("[")
        for(prop in obj) {
            string.push(convertToText(obj[prop]), ",");
        }
        string.push("]")

    } else if (typeof(obj) == "function") {
        string.push(obj.toString())

    } else {
        string.push(JSON.stringify(obj))
    }

    return string.join("")
}
function stateJSON() {
	
	var json = {};
	
	var rowsJSON = new Array();
	var rows = $('.container-fluid').find('.row');
	rows.each(function(indexi) {
		
		var colsJSON = new Array();
		var cols = $(this).find('.ui-sortable');
		
		
		cols.each(function(indexj) {
			var widgetsJSON = new Array();
			var widgets = $(this)
					.find('.dashboard-box');
			
			widgets.each(function(indexz) {
				var widgetJSON = {};
				widgetJSON.id = $(this).attr('id');
				widgetsJSON[indexz] = widgetJSON;
				
			});
			
			colsJSON[indexj] = widgetsJSON;
		});
		
		
		rowsJSON[indexi] = colsJSON;
	});
	
	json.widgets = rowsJSON;
	
	return convertToText(json);
}

$('.widget-container-span').on("sortstop",
		function(event, ui) {
			$(window).trigger('resize');
			setState(stateJSON());
		});

function getState() {
	$.getJSON(serverUrl + "state.json?callback=?", function(data, textStatus, jqXHR) {
		relocates(data);
	}).done(function(data, textStatus, jqXHR) {
		console.log("second success");
	}).fail(function(jqXHR,  textStatus, errorThrow) {
		 var err = textStatus + ", " + errorThrow;
		 console.log( "Request Failed: " + err );
	}).always(function(data) {
		console.log("complete");
	});
}
		
function setState(state) {
	$.getJSON(serverUrl + "setstate?callback=?&state=" + state, function(data, textStatus, jqXHR) {
	}).done(function(data, textStatus, jqXHR) {
		//console.log("second success");
	}).fail(function(jqXHR,  textStatus, errorThrow) {
		 var err = textStatus + ", " + errorThrow;
		 console.log( "Request Failed: " + err );
	}).always(function(data) {
		//console.log("complete");
	});
}

getState();