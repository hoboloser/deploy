$(document).ready(function() {

	var uuid = $("#text-uuid").html();

	// 初始化刷新运行状态
	refreshStatus();

	// 部署按钮
	$("#btn-deploy").click(function () {
		ajaxShell("../deploy/deploy", {uuid: uuid}, function() {
			refreshStatus();
		});
	});
	
	// 部署按钮btn-auto
	$("#btn-install").click(function () {
		ajaxShell("../deploy/install", {uuid: uuid}, function() {
			refreshStatus();
		});
	});
	$("#btn-system").click(function () {
		ajaxShell("../deploy/system/info", {uuid: uuid}, function() {
			refreshStatus();
		});
	});
	
	$("#btn-auto").click(function () {
		ajaxShell("../deploy/automation", {uuid: uuid}, function() {
			refreshStatus();
		});
	});

	// 重启按钮
	$("#btn-restart").click(function () {
		ajaxShell("../deploy/restart", {uuid: uuid}, function() {
			refreshStatus();
		});
	});
	
	// 重启按钮
	$("#btn-start").click(function () {
		ajaxShell("../deploy/start", {uuid: uuid}, function() {
			refreshStatus();
		});
	});

	// 停止按钮btn-showlogfile
	$("#btn-stop").click(function () {
		ajaxShell("../deploy/stop", {uuid: uuid}, function() {
			refreshStatus();
		});
	});
	
	$("#btn-showlogfile").click(function () {
		ajaxShell("../deploy/showlogfile", {uuid: uuid}, function() {
			refreshStatus();
		});
	});
	
	function path(url, postData) {
		var pwdpath = null;
		$.ajax({
			url: url,
			type: "POST",
			data: postData,
			cache: false,
			dataType: "text",
			success: function (data) {
				pwdpath = data;
				//$("#loader-modal").closeModal();
				//$("#excute-layer-modal .modal-content>div").append(data.replace(/\n/g,"<br>"));
				//$("#excute-layer-modal .modal-content").scrollTop($("#excute-layer-modal .modal-content>div").height() - $("#excute-layer-modal .modal-content").height());
			},
			error: function () {
				//$("#loader-modal").closeModal();
				layerAlert("发生异常，请重试！");
			}
		});
		return pwdpath;
	}

	// 查看日志
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
	
	// 停止按钮btn-showlogfile
	/*$("#shell-input").keypress(function (event) {
		if(event.which == 13) { 
			var cmd = $('#shell-input').val();
			ajaxShellE("../excute", {uuid: uuid,cmd:cmd}, function() {
				//refreshStatus();
			});
		}
	});*/
	
	/**
	 * ajax请求后台运行脚本
	 */
	function ajaxShellE(url, postData, successCallback) {
		//$("#loader-modal").openModal({dismissible: false});
		$.ajax({
			url: url,
			type: "POST",
			data: postData,
			cache: false,
			dataType: "text",
			success: function (data) {
				//$("#loader-modal").closeModal();
				//$("#excute-layer-modal .modal-content>div").append(data.replace(/\n/g,"<br>"));
				//$("#excute-layer-modal .modal-content").scrollTop($("#excute-layer-modal .modal-content>div").height() - $("#excute-layer-modal .modal-content").height());
			},
			error: function () {
				//$("#loader-modal").closeModal();
				layerAlert("发生异常，请重试！");
			}
		});
	}
	
	// 查看日志
	$("#btn-excute").click(function () {
		var url = $('#shell-ws-url').val();
		var cmd = $('#shell-input').val();
		url = url + "&cmd=1";
		var websocket = new WebSocket(url);
		
		$.ajax({
			url: "../deploy/pwd",
			type: "POST",
			data: {uuid: uuid},
			cache: false,
			dataType: "text",
			success: function (data) {
				var pwdPath = data;
				$("#excute-layer-modal .modal-content").html('<div><div id="shell-input-div"><table><tr><th><p>'+pwdPath+'</p></th><th><input id ="shell-input" type="text" /></th></tr></table></div></div>');
				$("#excute-layer-modal").openModal({
					dismissible: false,
					complete: function () {
						websocket.close();
					}
				});
				
				$('#shell-input').bind('keypress',function(event){
					if(event.which == 13) { 
						var cmd = $('#shell-input').val();
						ajaxShellE("../deploy/excute/cmd", {uuid: uuid,cmd:cmd}, function() {
						});
					}
				});
			}
		});
		
		websocket.onmessage = function(event) {
			var msg = event.data;
			
			$.ajax({
				url: "../deploy/pwd",
				type: "POST",
				data: {uuid: uuid},
				cache: false,
				dataType: "text",
				success: function (data) {
					var pwdPath = data;
					
					$("#excute-layer-modal .modal-content>div").append(msg);
					$("#excute-layer-modal .modal-content").scrollTop($("#excute-layer-modal .modal-content>div").height() - $("#excute-layer-modal .modal-content").height());
					
					$('#shell-input-div').remove();
					
					$("#excute-layer-modal .modal-content>div").append('<div id="shell-input-div"><table><tr><th><p>'+pwdPath+'</p></th><th><input id ="shell-input" type="text" /></th></tr></table></div>');
					
					$('#shell-input').bind('keypress',function(event){
						if(event.which == 13) { 
							var cmd = $('#shell-input').val();
							ajaxShellE("../deploy/excute/cmd", {uuid: uuid,cmd:cmd}, function() {
							});
						}
					});
				}
			});
			
		};

	});


	/**
	 * ajax请求后台运行脚本
	 */
	function ajaxShell(url, postData, successCallback) {
		$("#loader-modal").openModal({dismissible: false});
		$.ajax({
			url: url,
			type: "POST",
			data: postData,
			cache: false,
			dataType: "text",
			success: function (data) {
				$("#loader-modal").closeModal();
				$("#layer-modal .modal-content").html(data.replace(/\n/g,"<br>"));
				$("#layer-modal").openModal({dismissible: false});
				if(successCallback) {
					successCallback();
				}
			},
			error: function () {
				$("#loader-modal").closeModal();
				layerAlert("发生异常，请重试！");
			}
		});
	}

	/**
	 * 刷新服务器运行状态
	 */
	function refreshStatus() {
		$.ajax({
			url: "../deploy/status",
			type: "POST",
			data: {uuid: uuid},
			cache: false,
			dataType: "text",
			success: function (data) {
				$(".service-status").children("span").hide();
				if(data === "true") {
					$(".service-status").find(".green-text").show();
				} else {
					$(".service-status").find(".red-text").show();
				}
			},
			error: function () {
				layerAlert("发生异常，请重试！");
			}
		});
	}

	/**
	 * 用于代替alert
	 * @param text
	 */
	function layerAlert(text) {
		$("#alert-modal .text-alert").html(text);
		$("#alert-modal").openModal({dismissible: false});
	}
	
});