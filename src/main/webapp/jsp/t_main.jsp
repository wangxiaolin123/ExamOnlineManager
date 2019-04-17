<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 22719
  Date: 2019/4/16
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>main</title>
    <!-- Bootstrap -->
    <link href="<%=basePath %>/css/bootstrap.min.css" rel="stylesheet">
    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>
      <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
      <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

  <!--头部-->
  <c:import url="header.jsp" />
  <!-- end 头部 -->

  <div class="container">
	  <div class="container">
		  <div class="row clearfix">
			  <div class="col-md-3 column">
				  <div class="list-group">
					  <a href="#" class="list-group-item active">考试管理</a>
					  <div class="list-group-item">
						  <button type="button" class="btn btn-default">新建考试</button>
						  <button type="button" class="btn btn-default">查看考试情况</button>
						  <button type="button" class="btn btn-default">编辑考试</button>
						  <button type="button" class="btn btn-default">查看考试情况</button>
						  <button type="button" class="btn btn-default">学生信息管理</button>
						  <button type="button" class="btn btn-default">解除学生登陆锁定</button>
						  <button type="button" class="btn btn-default">通知管理</button>
						  <button type="button" class="btn btn-default">考试结束</button>
						  <button type="button" class="btn btn-default">答卷下载</button>
						  <button type="button" class="btn btn-default">导出提交信息</button>
						  <button type="button" class="btn btn-default">考试清理和删除</button>
					  </div>
				  </div>
			  </div>
			  <div class="col-md-9 column">
			  </div>
		  </div>
	  </div>
  </div>


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="<%=basePath%>/js/jq1.12.4/jquery.min.js"></script>
		<script>
			 $(function(){
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
