<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <base href="<%=basePath%>">
</head>

<body>

<!-- Static navbar -->
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">上机考试管理系统</a>
    </div>
    <div id="navbar" class="navbar-collapse collapse">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">主页</a></li>
        <li><a href="#">菜单</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">下拉页面 <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">字标签</a></li>
            <li><a href="#">字标签</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">看我上边的分割线</a></li>
          </ul>
        </li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <c:choose>
          <c:when test="${empty sessionScope.user}">
            <li><a href="javascript:void(0)">未登陆</a></li>
            <li><a href="javascript:void(0)">登陆</a></li>
          </c:when>
          <c:otherwise>
            <li><a href="javascript:void(0)">"${sessionScope.user.username}</a></li>
            <li><a href="logout.do">退出</a></li>
          </c:otherwise>
        </c:choose>
      </ul>
    </div><!--/.nav-collapse -->
  </div><!--/.container-fluid -->
</nav>
<!--end 头部-->

</body>
</html>
