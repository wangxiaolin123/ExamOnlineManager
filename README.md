# exam

## 介绍
上机考试系统开发组的托管仓库

## 前端开发说明
每个页面的demo。
以下是通用，必须引入


```
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<title>登陆</title>
<!-- 导入js css等 -->
<c:import url="../import/style.jsp"></c:import>
</head>
<body>
	<!--头部-->
	<c:import url="../import/header.jsp">
		<c:param name="data">login</c:param>
	</c:import>
</body>
```

- /webapp/jsp/import文件夹存放c:import标签导入的jsp文件
- 其中header.jsp是导航栏，style.jsp是css，js，meta等内容
- header.jsp导入需要传递参数，其目的是设置active属性


