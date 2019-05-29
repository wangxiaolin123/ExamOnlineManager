<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<title>exam-登陆</title>

	<!-- signin -->

	<link href="<%=path%>/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
	<link href="<%=path%>/css/login/signin.css" rel="stylesheet">
	<%--<script src="https://cdn.bootcss.com/jquery/2.2.3/jquery.min.js"></script>--%>
	<link href="<%=path%>/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">



	<script src="<%=path%>/js/jquery-3.3.1.min.js"></script>

	<script src="<%=path%>/bootstrap-3.3.7-dist/js/bootstrap.js"></script>

	<script src="<%=path%>/js/sco.countdown.js"></script>

	<script src="<%=path%>/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script src="<%=path%>/js/bootstrap-datetimepicker.min.js"></script>
</head>
<body>

<!--头部-->
<c:import url="../import/l_header.jsp">
	<c:param name="data">login</c:param>
</c:import>

</body>

</html>