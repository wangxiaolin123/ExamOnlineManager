<%--
  Created by IntelliJ IDEA.
  User: 22719
  Date: 2019/4/16
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>管理员</title>
<!-- 导入js css等 -->
<c:import url="../import/style.jsp"></c:import>
</head>

<body>

	<!--admin头部-->
	<c:import url="../import/a_header.jsp">
		<c:param name="data">main</c:param>
	</c:import>
	<!--end admin头部-->
	
	<div class="container">
		<div class="container">
			<div class="row clearfix">
				<div class="col-md-6 column">
					<a href="${basePath}/admin/t_manager.do">教师管理</a>
					<a href="${basePath}/admin/examclean.do">考试清理</a>
					<a href="${basePath}/admin/classmanager.do">班级管理</a>

					<button type="button" class="btn btn-default">系统配置</button>
					<button type="button" class="btn btn-default">考试时间偏差设计</button>
				</div>
				<div class="col-md-6 column"></div>
			</div>
		</div>
	</div>

</body>
</html>
