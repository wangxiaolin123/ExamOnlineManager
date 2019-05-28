<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  Created by IntelliJ IDEA.
  User: 22719
  Date: 2019/4/16
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>

<title>main</title>
<!-- 导入js css等 -->
<c:import url="../import/style.jsp"></c:import>
</head>

<body>

	<!--teacher头部-->
	<c:import url="../import/t_header.jsp">
		<c:param name="data">main</c:param>
	</c:import>
	<!--end 头部-->

	<div class="container">
		<div class="container">
			<div class="row clearfix">
				<div class="col-md-3 column">
					<div class="list-group">
						<a href="#" class="list-group-item active">考试管理</a>
						<div class="list-group-item">
							<a
								href="<%=basePath%>/teacher/exammanager.do?teaNumber=${sessionScope.user.number}"
								class="btn btn-default">考试管理</a>
							<a
									href="<%=basePath%>/teacher/studentmanager.do?teaNumber=${sessionScope.user.number}"
									class="btn btn-default">学生信息管理</a>
							<a
									href=""
									class="btn btn-default">查看正在进行的考试</a>

						</div>
					</div>
				</div>
				<div class="col-md-9 column"></div>
			</div>
		</div>
	</div>


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
	<script src="<%=basePath%>/js/jq1.12.4/jquery.min.js"></script>
	<script>
		$(function() {
			/*公共部分
			 * 导航栏
			 * footer CopyRight
			 */
			$(".headerpage").load("../public/header.jsp");

		});
	</script>
	<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
	<script src="<%=basePath%>/js/bootstrap.min.js"></script>


</body>
</html>
