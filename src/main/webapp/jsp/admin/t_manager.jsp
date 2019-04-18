<%--
  Created by IntelliJ IDEA.
  User: 22719
  Date: 2019/4/18
  Time: 18:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<html>
<head>
    <title>教师管理</title>
    <link href="<%=basePath %>/css/bootstrap.min.css" rel="stylesheet">
    <script src="<%=basePath%>/js/bootstrap.min.js"></script>

</head>
<body>

<!--头部-->
<c:import url="../header.jsp"/>
<!-- end 头部 -->

<div class="container">
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-4 column">
                <button type="button" class="btn btn-default">添加教师</button>
                <button type="button" class="btn btn-default">指定管理员</button>
            </div>
            <div class="col-md-8 column">
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>工号</th>
                        <th>姓名</th>
                        <th>是否为管理员</th>
                        <th>可用操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.teachers}" var="item">
                        <tr>
                            <td>${item.teaNumber}</td>
                            <td>${item.teaName}</td>
                            <td>${item.isadmin}</td>
                            <td class="list-inline">
                                    <li><a href="javascript:void(0)">修改</a></li>
                                    <li><a href="javascript:void(0)">删除</a></li>
                                    <li><a href="javascript:void(0)">添加权限</a></li>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</div>


</body>
</html>
