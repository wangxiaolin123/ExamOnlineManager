<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
</head>
<body style="margin: 0px;padding: 0px;">
	<div class="container">
		<!-- Static navbar -->
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="${basePath}/adminT/mainPage.do">上机考试管理系统</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						
						<li
							<c:choose>
								<c:when test="${not empty param.data && param.data == 't_manager'}">
									 class="active" 
								</c:when>
							</c:choose>
						><a href="${basePath}/adminT/t_manager.do">教师管理</a></li>

						<c:if test="${sessionScope.user.number != 'admin'}">
							<li>
								<a href="${basePath}/teacher/mainPage.do">教学管理</a>
							</li>
						</c:if>

					</ul>
					<ul class="nav navbar-nav navbar-right">
						<c:choose>
							<c:when test="${empty sessionScope.user}">
								<li><a style="color: red;" href="javascript:void(0)">当前状态：未登陆</a></li>
								<li
									<c:choose>
										<c:when test="${not empty param.data && param.data == 'login'}">
											 class="active" 
										</c:when>
									</c:choose>
								><a href="${basePath}/login/loginPage.do">点我登陆</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="javascript:void(0)">管理员用户：${sessionScope.user.name}</a></li>
								<li><a class="red" href="${basePath}/login/logout.do">点我退出</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
			<!--/.container-fluid -->
		</nav>
		<!--end 头部-->
	</div>
</body>
</html>