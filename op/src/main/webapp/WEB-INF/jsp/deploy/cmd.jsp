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
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body>
<!--    <script type="text/javascript" src="com.js"></script>
   <script type="text/javascript" src="config.js"></script>
   <script type="text/javascript" src="jsterm.js"></script>
   <script type="text/javascript" src="analytics.js"></script> -->
   <script src="http://cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
   <script src="http://cdn.bootcss.com/materialize/0.97.0/js/materialize.min.js"></script>
   <script src="${pageContext.request.contextPath}/resources/js/cmd.js"></script>
   <script src="${pageContext.request.contextPath}/resources/js/config.js"></script>
   <%-- <script src="${pageContext.request.contextPath}/resources/js/jsterm.js"></script> --%>
   
   <input type="hidden" id="uuid-input" value="${detail.uuid}">
   
   
</body>
</html>