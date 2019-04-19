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
    <script src="<%=basePath%>/js/jq1.12.4/jquery.min.js"></script>
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
                <button type="button" id="btn_add" class="btn btn-default">添加教师</button>
                <button type="button" class="btn btn-default">指定管理员</button>
            </div>
            <div class="col-md-8 column">
                <table id="teacherList" class="table table-bordered table-hover">
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

<!--新增-->
<div class="modal fade" data-keyboard="false" id="addTeacherModel" data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                </button>
                <h4 class="modal-title" >
                    添加教师
                </h4>
            </div>
            <div class="modal-body">
                <form id="addTeacherForm" action="#" class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">工号</label>
                        <div class="col-sm-10">
                            <input name="teaNumber" type="text" class="form-control" placeholder="请输入教师工号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-10">
                            <input name="teaName" type="text" class="form-control"  placeholder="请输入姓名">
                        </div>
                    </div>
                    <div class="form-group text-center" style="color: red">
                        *初始密码与工号一致
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <div class="checkbox">
                                <label>
                                    <input name="isadmin" type="checkbox" value="1">设为管理员
                                </label>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    取消
                </button>
                <button id="addTeacher" type="button" class="btn btn-primary">
                    保存
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<script type="text/javascript">
    //新增教师
    $("#btn_add").click(function () {
        //开启模态框
        $('#addTeacherModel').modal('toggle');

    })
    //新增教师提交表单
    $("#addTeacher").click(function() {
        //获取数据
        var params = $("#addTeacherForm").serialize();
        //var t = $("#addTeacherForm").serializeArray(); 测试静态数据时使用
        //post请求添加数据
        var url = "<%=basePath%>/adminT/t_addteacher.do";
        $.post(url, params, function (data) {
            if (data.status == 200) {
                alert('数据保存成功');
                // 提交成功，列表项添加该数据
                addRow(data.data);
            } else {
                //alert重新输入
                alert(data.msg);
            }
        })
        $('#addTeacherModel').modal('toggle');
    })

    function addRow(data) {

        var table=document.getElementById("teacherList");
        var row=table.insertRow(1);
        row.innerHTML="<tr><td>"+data.teaNumber+"</td><td>" +data.teaName+"</td><td>"
            +data.isadmin+"</td><td class=\"list-inline\"><li><a href=\"javascript:void(0)\">修改</a></li><li><a href=\"javascript:void(0)\">删除</a></li>" +
            "<li><a href=\"javascript:void(0)\">添加权限</a></li></td></tr>";


    }

</script>
</body>
</html>
