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
<title>实例</title>
<!-- signin -->
<link href="<%=path%>/css/login/signin.css" rel="stylesheet">
</head>
<body>
	<div class="container">

			<form class="form-signin" action="<%=path%>/login/login.do" method="post">
				<h2 class="form-signin-heading">在线考试系统</h2>
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