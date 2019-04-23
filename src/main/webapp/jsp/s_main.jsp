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
	  <script src="<%=basePath%>/js/jq1.12.4/jquery.min.js"></script>
	  <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
	  <script src="<%=basePath%>/js/bootstrap.min.js"></script>
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
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="row clearfix">
						<div class="col-md-2 column">
							<button type="button" class="btn btn-default">试卷下载</button>
							<button type="button" class="btn btn-default">答案上传</button> 
							<button type="button" class="btn btn-default">查看已提交文件</button>
						</div>
						<div class="col-md-2 column">
						</div>
						<div class="col-md-10 column">
							<div class="row clearfix">
								<div class="col-md-12 column">
									<table class="table table-hover">
										<thead>
										<tr>
											<th>考试编号</th>
											<th>考试名称</th>
											<th>考试时间</th>
											<th>考试状态</th>
											<th>考生操作</th>
										</tr>
										</thead>
										<tbody id="examList">

										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>			
		   </div>



		<script>
			 $(function(){

				 var url = "<%=basePath%>/student/examList.do?stuNumber=${sessionScope.user.number}";
				 $.post(url, function (data) {
					 if (data.status == 200) {
						 //提交成功，修改该数据
						 var html = "";
						 var item=data.data;
						 for( var i = 0; i < item.length; i++ ) {
						 	var startTime=timeStamp2String(item[i].startTime);
							 var endTime=timeStamp2String(item[i].endTime);
						 	html += "<tr>";
						 	html += "<td>" + item[i].examID + "</td>";
						 	html += "<td>" + item[i].examName + "</td>";
						 	html += "<td>" + startTime+" 至 "+ endTime+ "</td>";
						 	html += "<td>" + item[i].state + "</td>";
							 html += "<td>暂无</td>";
						 	html += "</tr>";
						 }
						 $("#examList").html(html);

					 } else {
						 alert(data.msg);
					 }
				 })

		});

			 //在Jquery里格式化Date日期时间数据
			 function timeStamp2String(time){
				 var datetime = new Date();
				 datetime.setTime(time);
				 var year = datetime.getFullYear();
				 var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
				 var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
				 var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
				 var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
				 //var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
				 return year + "-" + month + "-" + date+" "+hour+":"+minute;//+":"+second;
			 }
		</script>



  </body>
</html>
