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
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">
<meta name="keywords" content="exam">
<meta name="description" content="exam">

<title>实例</title>

<!-- bootstrap3.3.7 -->
<link href="<%=path%>/assert/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/js/jq/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=path%>/assert/bootstrap/js/bootstrap.min.js"></script>
	<script src="js/MD5.js" type="text/javascript" charset="utf-8"></script>

<!-- signin -->
<link href="<%=path%>/css/login/signin.css" rel="stylesheet">
</head>
<body>
<!--头部-->
	<c:import url="../header.jsp"/>

	<div class="container">

			<form class="form-signin" action="<%=path%>/login/login.do" method="post">
				<%--@declare id="inputtype"--%><h2 class="form-signin-heading">在线考试系统</h2>
					<font color="red">${ requestScope.tips}</font>
				<label for="inputUser" class="sr-only">账户</label>
				<input type="text" id="inputUser" name="inputUser" class="form-control" placeholder="账户名" required autofocus>
				<label for="inputPassword" class="sr-only">密码</label>
				<input type="password" id="inputPassword" name="inputPassword" class="form-control" placeholder="密码" required>
				<label for="inputType" class="sr-only">账号类型</label>
				<select name="inputSelect" id="inputSelect" class="form-control">
					<option value="1">管理员</option>
					<option value="2">教师</option>
					<option value="3">学生</option>
				</select>
				
				<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
			</form>

		</div> <!-- /container -->
</body>
</html>