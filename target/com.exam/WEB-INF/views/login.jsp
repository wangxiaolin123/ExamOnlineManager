<%--
  Created by IntelliJ IDEA.
  User: 22719
  Date: 2019/4/16
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<title>login</title>

		<!-- Bootstrap -->
		<link href="<%=basePath%>/css/bootstrap.css" rel="stylesheet">
		<!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
		<!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
		<!--[if lt IE 9]>
		  <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
		  <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
		<![endif]-->
		<link href="<%=basePath%>/css/login/signin.css" rel="stylesheet" />
	</head>
	<body>
		<div class="container">

			<form class="form-signin" action="user/Login.do">
				<%--@declare id="inputtype"--%><h2 class="form-signin-heading">在线考试系统</h2>
				<label for="inputUser" class="sr-only">账户</label>
				<input type="text" name="inputUser" id="inputUser" class="form-control" placeholder="账户名" required autofocus>
				<label for="inputPassword" class="sr-only">密码</label>
				<input type="password" name="inputPassword" id="inputPassword" class="form-control" placeholder="密码" required>
				<label for="inputType" class="sr-only">账号类型</label>
				<select name="inputSelect" name="inputSelect" id="inputSelect" class="form-control">
					<option>管理员</option>
					<option>教师</option>
					<option>学生</option>
				</select>
				<div class="checkbox">
					<label>
						<input type="checkbox" value="remember-me">记住我
					</label>
				</div>
				<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
			</form>

		</div> <!-- /container -->
		<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
		<script src=<%=basePath%>/js/jq1.12.4/jquery.min.js"></script>
		<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
		<script src="<%=basePath%>/js/bootstrap.min.js"></script>
	</body>
</html>
