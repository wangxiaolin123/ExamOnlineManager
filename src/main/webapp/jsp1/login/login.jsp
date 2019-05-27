<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>exam-登陆</title>

    <!-- signin -->
    <link href="<%=path%>/css/login/signin.css" rel="stylesheet">
    <!-- 导入js css等 -->
    <c:import url="../import/style.jsp"></c:import>
    <script src="<%=path%>/js/MD5.js" type="text/javascript" charset="utf-8"></script>


</head>
<body>
<!--头部-->
<c:import url="../import/l_header.jsp">
    <c:param name="data">login</c:param>
</c:import>

<div class="container">

    <form class="form-signin" action="<%=path%>/login/login.do" method="post">
        <%--@declare id="inputtype"--%><h2 class="form-signin-heading">在线考试系统</h2>
        <font color="red">${ requestScope.tips}</font>

        <label for="inputUser" class="sr-only">账户</label>
        <input style="margin-bottom: 10px;" type="text" id="inputUser" name="inputUser" class="form-control"
               placeholder="账户名" required autofocus>

        <div style="margin: 1px;"></div>
        <label for="inputPassword" class="sr-only">密码</label>
        <input type="password" id="inputPassword" name="inputPassword" class="form-control" placeholder="密码" value="卢老师"
               required>

        <div style="margin: 1px;"></div>
        <label for="inputType" class="sr-only">账号类型</label>
        <select style="margin-bottom: 10px;" name="inputSelect" id="inputSelect" class="form-control">
            <option value="1">管理员</option>
            <option value="2">教师</option>
            <option value="3">学生</option>
        </select>

        <div style="margin: 5px;">
            <button style="width: 48%;margin:0 auto;" class="btn btn-md btn-primary" type="submit">登录</button>
            <button style="width: 48%;margin:0 auto;" class="btn btn-md btn-primary" type="reset">取消</button>
        </div>
    </form>

</div> <!-- /container -->

</body>

</html>