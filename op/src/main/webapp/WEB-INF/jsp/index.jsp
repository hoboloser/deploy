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
<script src="${pageContext.request.contextPath}/resources/js/index.js"></script>
</head>
<body style="font-family: 'Roboto', 'Droid Sans Fallback', '微软雅黑'; min-height: 100vh;display: flex;flex-direction: column;">

	<nav>
		<div class="nav-wrapper">
			<a href="${pageContext.request.contextPath}/opc/index" class="brand-logo center">Auto Deploy</a>
		</div>
	</nav>

	<div class="container" style="padding-top: 20px; width: 90%;flex: 1 0 auto;">
				<div id="java-deploy">
					<div class="row">
						<div class="input-field col s12 m6 offset-m2">
							<nav>
								<div class="nav-wrapper">
										<div class="input-field" style="height: 100%">
											<input id="java-deploy-search" type="search">
											<label for="java-deploy-search">
												<i class="material-icons" style="line-height: inherit;">search</i>
											</label>
										</div>
								</div>
							</nav>
						</div>
						<div class="input-field col s12 m4">
							<a class="waves-effect waves-light btn red lighten-2" href="${pageContext.request.contextPath}/opc/new" style="line-height: 64px; height: 64px;">创建</a>
						</div>
					</div>
					<table class="hoverable">
						<thead>
							<tr>
								<td>项目名称</td>
								<td>详情</td>
								<td>删除</td>
								<td>更新</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${deployList}">
								<tr>
									<td>${item.name}</td>
									<td><a href="${pageContext.request.contextPath}/opc/detail/${item.uuid}" class="btn waves-effect waves-light red lighten-2">详情</a></td>
									<td><a href="${pageContext.request.contextPath}/opc/delete/${item.uuid}" class="btn waves-effect waves-light red lighten-2">删除</a></td>
									<td><a href="${pageContext.request.contextPath}/opc/update/${item.uuid}" class="btn waves-effect waves-light red lighten-2">更新</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
			</div>
	</div>
	
	<footer class="page-footer" style="padding-top: 0; margin-top: 40px;">
      <div class="footer-copyright">
        <div class="container">
        	<a href="https://github.com/hoboloser/deploy">GitHub</a>
        </div>
      </div>
    </footer>
	
</body>
</html>