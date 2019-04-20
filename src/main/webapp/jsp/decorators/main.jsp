<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="sitemesh"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	System.out.println("path:" + path);
	System.out.println("basePath:" + basePath);
	//设置缓存为空
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>

<!DOCTYPE HTML>
<html>
<head>
<title>EXAM-<sitemesh:title /></title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">
<meta name="keywords" content="SSM,sample">
<meta name="description" content="sample">

<!-- bootstrap3.3.7 -->
<link href="<%=path%>/assert/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/js/jq/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=path%>/assert/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

<sitemesh:head />
<script>
	var root = "<%=path%>/";
	if (root == "/") {root="";}
	var abroot = "<%=basePath%>";
</script>
</head>
<body>
	<sitemesh:body />
</body>
</html>
