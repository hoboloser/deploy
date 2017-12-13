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

	<%-- <c:forEach var="item" items="${jobList}"> --%>
	<div class="container" style="padding-top: 20px; width: 90%;flex: 1 0 auto;">
		<span class="card-title black-text">已有定时任务</span>
		<div id="java-deploy">
			<table class="hoverable">
				<thead>
					<tr>
						<td>job时间</td>
						<td>执行结果</td>
						<td>邮件接收者</td>
						<td>短信接收者</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${jobList}">
						<tr>
							<td>${item.jobTime}</td>
							<td>${item.result}</td>
							<td>${item.email}</td>
							<td>${item.mobile}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<%-- </c:forEach> --%>
		</div>
	
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