<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 22719
  Date: 2019/4/16
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>

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

            <ul class="breadcrumb">
                <li><a href="${basePath}/teacher/mainPage.do" class="active">首页</a></li>
            </ul>

            <div class="col-md-3 column">
                <div class="list-group">
                    <a href="#" class="list-group-item active">考试管理</a>
                    <div class="list-group-item">
                        <a
                                href="${basePath}/teacher/exammanager.do?teaNumber=${sessionScope.user.number}"
                                class="btn btn-default">考试管理</a>
                        <a
                                href="${basePath}/teacher/studentmanager.do?teaNumber=${sessionScope.user.number}"
                                class="btn btn-default">学生信息管理</a>


                    </div>
                </div>
            </div>
            <div class="col-md-9 column"></div>
        </div>
    </div>
</div>

</body>
</html>
