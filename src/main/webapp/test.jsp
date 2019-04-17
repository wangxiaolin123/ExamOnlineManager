<%--
  Created by IntelliJ IDEA.
  User: 22719
  Date: 2019/4/16
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>

<html>
<head>
    <title>Title</title>
    <link href="<%=basePath%>css/bootstrap.css" rel="stylesheet">

</head>
<body>
<button class="btn-danger" name="t" value="hh">test</button>
hhhhhhhhh
</body>
</html>
