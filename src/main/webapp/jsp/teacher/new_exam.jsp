<%--
  Created by IntelliJ IDEA.
  User: 22719
  Date: 2019/4/21
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<html>
<head>
    <title>新建考试</title>
    <link href="<%=basePath %>/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap core JavaScript
 ================================================== -->
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="<%=basePath%>/js/jq1.12.4/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="<%=basePath%>/js/bootstrap.min.js"></script>
</head>
<body>
<!--头部-->
<c:import url="../header.jsp"/>
<!-- end 头部 -->
<div class="container">
    <div class="row clearfix">
        <div class="col-md-2 column">
        </div>
        <div class="col-md-6 column">
            <form role="form">
                <div class="form-group">
                    <label>考试名称</label>
                    <input type="text" class="form-control" id="exampleInputEmail1"/>
                </div>
                <div class="form-group">
                    <label>开始时间</label>
                    <input type="text" class="form-control form_datetime"/>
                </div>
                <div class="form-group">
                    <label>结束时间</label>
                    <input type="text" class="form-control form_datetime"/>
                </div>
                <div class="form-group">
                    <label>选择考试班级</label>

                </div>
                <div class="form-group">
                    <label>考试创始人</label>
                    <input type="text" class="form-control" value="${sessionScope.user.number}"/>
                </div>
                <div class="checkbox">
                    <label><input type="checkbox"/>自动开始</label>
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
        </div>
        <div class="col-md-4 column">
        </div>
    </div>
</div>

<script type="text/javascript">
    //时间控件初始化
    $(".form_datetime").datetimepicker({
        format: "yyyy-mm-dd hh:ii",
        autoclose: true,
        todayBtn: true,
        minuteStep: 1,
        startDate: new Date()
    });

</script>

</body>
</html>
