<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<title>Auto Deploy Platform</title>
<link rel="stylesheet" href="http://cdn.bootcss.com/materialize/0.97.0/css/materialize.min.css">
<link href="${pageContext.request.contextPath}/resources/css/icon.css" rel="stylesheet">
<style type="text/css">
	.spinner {
		margin: 100px auto;
		width: 32px;
		height: 32px;
		position: relative;
	}

	.cube1, .cube2 {
		background-color: #67CF22;
		width: 30px;
		height: 30px;
		position: absolute;
		top: 0;
		left: 0;

		-webkit-animation: cubemove 1.8s infinite ease-in-out;
		animation: cubemove 1.8s infinite ease-in-out;
	}

	.cube2 {
		-webkit-animation-delay: -0.9s;
		animation-delay: -0.9s;
	}

	@-webkit-keyframes cubemove {
		25% { -webkit-transform: translateX(42px) rotate(-90deg) scale(0.5) }
		50% { -webkit-transform: translateX(42px) translateY(42px) rotate(-180deg) }
		75% { -webkit-transform: translateX(0px) translateY(42px) rotate(-270deg) scale(0.5) }
		100% { -webkit-transform: rotate(-360deg) }
	}

	@keyframes cubemove {
		25% {
			transform: translateX(42px) rotate(-90deg) scale(0.5);
			-webkit-transform: translateX(42px) rotate(-90deg) scale(0.5);
		}
		50% {
			transform: translateX(42px) translateY(42px) rotate(-179deg);
			-webkit-transform: translateX(42px) translateY(42px) rotate(-179deg);
		}
		50.1% {
			transform: translateX(42px) translateY(42px) rotate(-180deg);
			-webkit-transform: translateX(42px) translateY(42px) rotate(-180deg);
		}
		75% {
			transform: translateX(0px) translateY(42px) rotate(-270deg) scale(0.5);
			-webkit-transform: translateX(0px) translateY(42px) rotate(-270deg) scale(0.5);
		}
		100% {
			transform: rotate(-360deg);
			-webkit-transform: rotate(-360deg);
		}
	}
</style>
<script src="http://cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/materialize/0.97.0/js/materialize.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/detail.js"></script>
</head>
<body style="font-family: 'Roboto', 'Droid Sans Fallback', '微软雅黑';min-height: 100vh;display: flex;flex-direction: column;">

	<nav>
		<div class="nav-wrapper">
			<a href="${pageContext.request.contextPath}/opc/index" class="brand-logo center">Auto Deploy</a>
		</div>
	</nav>

	<div class="container" style="padding-top: 20px; width: 90%;flex: 1 0 auto;">
		
		<div class="row">
			<div class="col s12">
				<div class="card">
					<div class="card-content">
						<span class="card-title red-text">${detail.name}</span>
						<p>UUID：<span id="text-uuid">${detail.uuid}</span></p>
						<p>名称：${detail.name}</p>
						<p>IP：${detail.ip}</p>
						<p>linux登录账号：${detail.lname}</p>
						<p>tomcat名字：${detail.tname}</p>
						<p>shell脚本目录：${detail.sdir}</p>
						<p>war包名字：${detail.wname}</p>
						<p>war包上传路径：${detail.wdir}</p>
						<p>log目录：${detail.logpath}</p>
						<p>jdk目录：${detail.javaHomePath}</p>
						<p>zhicall_config目录：${detail.zhicallConfig}</p>
						<p>服务器类型：${detail.type}</p>
					</div>
					<div class="card-action">
						<p>
							<a class="btn waves-light waves-effect white-text" href="${pageContext.request.contextPath}/opc/cmd/${detail.uuid}">控制台</a>
							<a class="btn waves-light waves-effect white-text" id="btn-deploy">部署</a>
							<a class="btn waves-light waves-effect white-text" id="btn-restart">重启</a>
							<a class="btn waves-light waves-effect white-text" id="btn-start">启动</a>
							<a class="btn waves-light waves-effect white-text" id="btn-stop">停止</a>
							<a class="btn waves-light waves-effect white-text" id="btn-system">服务器信息</a>
							
							<a class="btn waves-light waves-effect white-text" href="${pageContext.request.contextPath}/opc/upload/${detail.uuid}">上传</a>
							<a class="btn waves-light waves-effect white-text" href="${pageContext.request.contextPath}/opc/deploy/show/file/${detail.uuid}">查看日志</a>
							<a class="btn waves-light waves-effect white-text" href="${pageContext.request.contextPath}/opc/newjob/${detail.uuid}">新增定时发布任务</a>
						</p>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
				<div class="col s12 m6 l4">
					<div class="card">
						<div class="card-content">
							<span class="card-title grey-text">运行状态</span>
							<p class="service-status">
								<span class="green-text" style="display: none;">正在运行</span>
								<span class="red-text" style="display: none;">已停止</span>
								<span class="grey-text">获取中...</span>
							</p>
						</div>
						<div class="card-action">
							<a href="#" class="btn-showlog" data-wsurl="ws://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/log?uuid=${detail.uuid}&type=tomcat">查看日志</a>
						</div>
					</div>
				</div>
		</div>

	</div>
	
		<footer class="page-footer" style="padding-top: 0; margin-top: 40px;">
      <div class="footer-copyright">
        <div class="container">
        <a href="https://github.com/hoboloser/deploy">GitHub</a>
        </div>
      </div>
    </footer>

	<div id="loader-modal" class="modal" style="background-color: transparent; box-shadow: none;">
		<div class="modal-content">
			<div class="spinner">
				<div class="cube1"></div>
				<div class="cube2"></div>
			</div>
		</div>
	</div>

	<div id="layer-modal" class="modal modal-fixed-footer">
		<div class="modal-content" style="background-color: black; color: #ccc; font-size: 0.9em;">
		</div>
		<div class="modal-footer">
			<a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">关闭</a>
		</div>
	</div>
	
	<div id="alert-modal" class="modal">
		<div class="modal-content">
			<p class="grey-text">提示</p>
			<p class="text-alert"></p>
		</div>
		<div class="modal-footer">
			<a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">关闭</a>
		</div>
	</div>

</body>
</html>