$(document).ready(function() {

	var uuid = $("#text-uuid").html();

$(".btn-showlog").click(function () {
		
		var url = $(this).attr("data-wsurl");
		var websocket = new WebSocket(url);
		websocket.onmessage = function(event) {
			var msg = event.data;
			$("#layer-modal .modal-content>div").append(msg);
			$("#layer-modal .modal-content").scrollTop($("#layer-modal .modal-content>div").height() - $("#layer-modal .modal-content").height());
		};

		$("#layer-modal .modal-content").html("<div></div>");
		$("#layer-modal").openModal({
			dismissible: false,
			complete: function () {
				websocket.close();
			}
		});

	});
	/**
	 * 用于代替alert
	 * @param text
	 */
	function layerAlert(text) {
		$("#alert-modal .text-alert").html(text);
		$("#alert-modal").openModal({dismissible: false});
	}
	
});