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
<script src="http://cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/materialize/0.97.0/js/materialize.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/javadeploy/new.js"></script>
</head>
<body style="font-family: 'Roboto', 'Droid Sans Fallback', '微软雅黑'; min-height: 100vh;display: flex;flex-direction: column;">

	<nav>
		<div class="nav-wrapper">
			<a href="${pageContext.request.contextPath}/opc/index" class="brand-logo center">Auto Deploy</a>
		</div>
	</nav>

	<form id="form-new" method="post" action="${pageContext.request.contextPath}/opc/update/config" style="flex: 1 0 auto;">
		<div class="container" style="padding-top: 20px;">

			<div class="row">
				<div class="col s12">
					<div class="card">
						<div class="card-content">
							<span class="card-title black-text">新建项目</span>
								<input type="hidden" name="uuid" value="${detail.uuid }">
								<div class="row">
									<div class="input-field col s12">
										<input type="text" id="input-name" name="name" value="${detail.name }">
										<label for="input-name">项目名称</label>
									</div>
									<div class="input-field col s6">
										<input type="text" id="input-tname" name="tname" value="${detail.tname }">
										<label for="input-tname">tomcat名字</label>
									</div>
									<div class="input-field col s6">
										<input type="text" id="input-wname" name="wname" value="${detail.wname }">
										<label for="input-wname">war包名字</label>
									</div>
									<div class="input-field col s6">
										<input type="text" id="input-linux-ip" name="ip" value="${detail.ip }">
										<label for="input-linux-ip">linux服务器ip</label>
									</div>
									<div class="input-field col s6">
										<input type="text" id="input-linux-account" name="lname" value="${detail.lname }">
										<label for="input-linux-account">linux登录账户</label>
									</div>
									<div class="input-field col s6">
										<input type="text" id="input-password" name="password" value="${detail.password }">
										<label for="input-password">password</label>
									</div>
									<div class="input-field col s6">
										<input type="text" id="input-shell" name="sdir" value="${detail.sdir }">
										<label for="input-shell">shell脚本目录</label>
									</div>
									<div class="input-field col s6">
										<input type="text" id="input-tomcat" name="tdir" value="${detail.tdir }">
										<label for="input-tomcat">tomcat根目录</label>
									</div>
									<div class="input-field col s6">
										<input type="text" id="input-bdir" name="bdir" value="${detail.bdir }">
										<label for="input-bdir">备份目录</label>
									</div>
									<div class="input-field col s6">
										<input type="text" id="input-war" name="wdir" value="${detail.wdir }">
										<label for="input-war">上传war包目录</label>
									</div>
									<div class="input-field col s6">
										<input type="text" id="input-log" name="logpath" value="${detail.logpath }">
										<label for="input-log">日志目录</label>
									</div>
									<div class="input-field col s6">
										<input type="text" id="input-java_home" name="javaHomePath" value="${detail.javaHomePath }">
										<label for="input-java_home">JAVA_HOME目录</label>
									</div>
									<div class="input-field col s6">
										<input type="text" id="input-zhicall_config" name="zhicallConfig" value="${detail.zhicallConfig }">
										<label for="input-zhicall_config">ZHICALL_CONFIG目录</label>
									</div>
									<div class="input-field col s6">
										<input type="text" id="input-type" name="type" value="${detail.type }">
										<label for="input-type">服务器类型</label>
									</div>
								</div>

						</div>
						<div class="card-action">
							<p>
								<button type="submit" class="btn waves-light waves-effect white-text">提交</button>
							</p>
						</div>
					</div>
				</div>
			</div>

		</div>
	</form>
	
	<footer class="page-footer" style="padding-top: 0; margin-top: 40px;">
      <div class="footer-copyright">
        <div class="container">
       <a href="https://github.com/hoboloser/deploy">GitHub</a>
        </div>
      </div>
    </footer>

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