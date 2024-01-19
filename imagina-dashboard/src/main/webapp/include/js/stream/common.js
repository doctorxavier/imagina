var url = window.location.protocol+'//'+window.location.hostname+(window.location.port ? ':'+window.location.port: '');
var serverUrl = url + "/imagina-dashboard/server/";
var srefreshtime = 5 * 1000;

function addStream(name, url) {
	var stream = 	"<div id='c" + name + "' style='float: left;margin:3px;'>" +
						"<div id='" + name + "' style='float: left;'></div>" +
						"<div>" + name + "</div>" +
					"</div>";
	
	$('#container_streams').append(stream);
	
	jwplayer(name)
	.setup(
	{
		height : 240,
		width : 426,
		autostart : 'true',
		flashplayer : "include/swf/jwplayer.swf",
		file : name,
		streamer : "rtmp://" + url + "/niceStreamingServer/",
		provider : "rtmp"
	});	
}

function pollStreams() {
	$.getJSON(serverUrl + "streams.json?callback=?", function(data, textStatus, jqXHR) {
		var streams = data.streams;
		
		$('#container_streams').children().each(function () {
			var found = false;
		    for(var i in streams) {
		    	if($(this).attr('id') == 'c' + streams[i].name) {
		    		found = true;
		    		break;
		    	}
		    }
		    if(!found) {
		    	$(this).remove();
		    }
		});
		
		for(var i in streams) {
			var found = false;
			$('#container_streams').children().each(function () {
				if('c' + streams[i].name == $(this).attr('id')) {
					found = true;
					return;
				}
			});
			
			if(!found) {
				addStream(streams[i].name, streams[i].url);
			}
		}
		
	}).done(function(data, textStatus, jqXHR) {
		//console.log("second success");
	}).fail(function(jqXHR,  textStatus, errorThrow) {
		 var err = textStatus + ", " + errorThrow;
		 console.log( "Request Failed: " + err );
	}).always(function(data) {
		//console.log("complete");
	});
}

function loadStreams() {

	$.getJSON(serverUrl + "streams.json?callback=?", function(data, textStatus, jqXHR) {
		var streams = data.streams;
		
		for(var i in streams) {
			addStream(streams[i].name, streams[i].url);
		}
		setInterval(function(){ pollStreams(); }, srefreshtime);
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
	loadStreams();
});