<%--
  Created by IntelliJ IDEA.
  User: 22719
  Date: 2019/4/18
  Time: 18:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<title>教师管理</title>
<c:import url="../import/style.jsp"/>
</head>
<body>

	<!--admin头部-->
	<c:import url="../import/a_header.jsp">
		<c:param name="data">t_manager</c:param>
	</c:import>
	<!--end admin头部-->


		<div class="container">

			<ul class="breadcrumb">
				<li><a href="${basePath}/admin/mainPage.do">首页</a></li>
				<li><a href="${basePath}/admin/t_manager.do" class="active">教师管理</a></li>
			</ul>

			<div style="margin: 30px;padding:30px;" class="row clearfix panel panel-default">
				<div class="col-md-1 col-sm-12 column"></div>
				<div class="col-md-10 column">
                    <button type="button" class="btn btn-default pull-right">更多...</button>
					<button type="button" id="btn_ref" class="btn btn-default pull-right">刷新</button>
	                <button type="button" id="btn_add" class="btn btn-default pull-right">添加教师</button>

	            </div>
	            <div class="col-md-1 col-sm-12 column"></div>
			</div>
			<div class="row clearfix">
				<div class="col-md-1 col-sm-12 column"></div>
				<div class="col-md-10 col-sm-12 column">
					<table id="teacherList" class="table table-striped">
						<thead>
							<tr>
								<th>序号</th>
								<th>工号</th>
								<th>姓名</th>
								<th>是否为管理员</th>
								<th>可用操作</th>
							</tr>
						</thead>
						<tbody id="tablebody">
							<c:forEach items="${requestScope.teachers}" var="item" varStatus="xh">
								<tr id="${item.teaID}">
									<td align="center">${xh.index+1 }</td>
									<td>${item.teaNumber}</td>
									<td>${item.teaName}</td>
									<td>
										<c:choose>
											<c:when test="${not empty item.isadmin && item.isadmin == 'true'}">
												<span>是</span>
											</c:when>
											<c:when test="${not empty item.isadmin && item.isadmin == 'false'}">
												<span>否</span>
											</c:when>
											<c:otherwise>
												<span style="color: red;">未知错误！！</span>
											</c:otherwise>
										</c:choose>
									</td>
									<td class="list-inline">
										<li><a href="javascript:void(0)"
											onclick="edtiTeacher(this)">修改</a></li>
										<li><a href="javascript:void(0)" onclick="del(this)">删除</a></li>
									</td>
									<td hidden>${item.teaID}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<div class="col-md-1 col-sm-12 column"></div>
			</div>
		</div>


	<!--新增-->
	<div class="modal fade" data-keyboard="false" id="addTeacherModel"
		data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">添加教师</h4>
				</div>
				<div class="modal-body">
					<form id="addTeacherForm" action="#" class="form-horizontal"
						role="form">
						<div class="form-group">
							<label class="col-sm-2 control-label">工号</label>
							<div class="col-sm-10">
								<input name="teaNumber" type="text" class="form-control"
									placeholder="请输入教师工号">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">姓名</label>
							<div class="col-sm-10">
								<input name="teaName" type="text" class="form-control"
									placeholder="请输入姓名">
							</div>
						</div>
						<div class="form-group text-center" style="color: red">
							*初始密码与工号一致</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<div class="checkbox">
									<label> <input name="isadmin" type="checkbox" value="1">设为管理员
									</label>
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						取消</button>
					<button id="addTeacher" type="button" class="btn btn-primary">
						保存</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<!--修改-->
	<div class="modal fade" data-keyboard="false" id="updateTeacherModel"
		data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">修改教师信息</h4>
				</div>
				<div class="modal-body">
					<form id="updateTeacherForm" action="#" class="form-horizontal"
						role="form">
						<div class="form-group">
							<label class="col-sm-2 control-label">工号</label>
							<div class="col-sm-10">
								<input name="teaID" type="hidden"> <input
									name="teaNumber" type="text" class="form-control"
									placeholder="请输入工号">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">姓名</label>
							<div class="col-sm-10">
								<input name="teaName" type="text" class="form-control"
									placeholder="请输入姓名">
							</div>
						</div>
						<div class="form-group text-center" style="color: red">
							<label class="col-sm-2 control-label">密码</label>
							<div class="col-sm-10">
								<input name="teaPassword" type="password" class="form-control"
									placeholder="请输入要重置的密码">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<div class="checkbox">
									<label> <input name="isadmin" type="checkbox" value="1">设为管理员
									</label>
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						取消</button>
					<button id="updateTeacher" type="button" class="btn btn-primary">
						提交更改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<script type="text/javascript">

    //新增教师
    $("#btn_add").click(function () {
        //开启模态框
        $('#addTeacherModel').modal('toggle');

    })
    //新增教师提交表单
    $("#addTeacher").click(function () {
        //获取数据
        var params = $("#addTeacherForm").serialize();
        //var t = $("#addTeacherForm").serializeArray(); 测试静态数据时使用
        //post请求添加数据
        var url = "${basePath}/admin/t_addteacher.do";
        $.post(url, params, function (data) {
            if (data.status == 200) {
                alert('教师添加成功');
                // 提交成功，列表项添加该数据
                addRow(data.data);
                window.location.reload();
            } else {
                //alert重新输入
                alert(data.msg);
            }
        })
        $('#addTeacherModel').modal('toggle');
    })

    function addRow(data) {

        var table = document.getElementById("teacherList");
        var row = table.insertRow(1);
        var str = "<tr id='"+data.teaID+"'><td>" + data.teaNumber + "</td><td>"
            + data.teaName + "</td><td>" + data.isadmin + "</td><td class='list-inline'>" +
            "<li><a href='javascript:void(0)' onclick='edtiTeacher(this)'>修改</a></li>" +
            "<li><a href='javascript:void(0)' onclick='delRow()'>删除</a></li>" +
            " </td><td>"+data.teaID+"</td></tr>";
        row.innerHTML = str;

    }


    function del(obj) {
        var msg = "您真的确定要删除吗？\n\n请确认！";
        if (confirm(msg)==true){
            deleteTeacher(obj);
            return true;
        }else{
            return false;
        }
    }

    function deleteTeacher(obj) {
        //获取要删除的教师id
        var tr = $(obj).parent().parent().parent();
        var teaNumber=tr.find("td:eq(0)").text();
        var trid=tr.find("td:eq(4)").text();
        // 请求服务器删除数据
        var url = "${basePath}/admin/deleteTeacher.do/" + teaNumber;
        $.get(url, function(data) {
            if (data.status == 200) {
                //删除本地表格对应的行
               delRow(trid);
            }else {
                alert(data.msg);
            }
        })
    }
    // 创建删除函数
    function delRow(trid) {
        $("#" + trid).remove();
    }

    //创建更新函数
    function updateRow(data) {
        delRow(data.teaID);
        addRow(data);

    }


    //修改教师
    function edtiTeacher(obj) {

        var tr = $(obj).parent().parent().parent();

        //将获取的该行的id值填充到模态框的文框中，文本框的ID为itemmodalid，其他的数据也是如此处理}
        $("#updateTeacherForm :input[name='teaNumber']").val(tr.find("td:eq(1)").text());
        $("#updateTeacherForm :input[name='teaName']").val(tr.find("td:eq(2)").text());
        if (tr.find("td:eq(3)").find("span:eq(0)").text() == '是') {
            $("#updateTeacherForm :input[name='isadmin']").prop("checked", true);
        } else {
            $("#updateTeacherForm :input[name='isadmin']").prop("checked",false);
        }
        $("#updateTeacherForm :input[name='teaID']").val(tr.find("td:eq(5)").text());
        //开启模态框
        $('#updateTeacherModel').modal('toggle');

    }

    //修改教师提交表单
    $("#updateTeacher").click(function () {
        //获取数据
        var params = $("#updateTeacherForm").serialize();
        //var t = $("#updateTeacherForm").serializeArray();
        //post请求添加数据
        var url = "${basePath}/admin/updateTeacher.do";
			$.post(url, params, function(data) {
				if (data.status == 200) {
					//提交成功，修改该数据
                    alert("修改教师信息成功");
					$('#updateTeacherModel').modal('toggle');
					//数据清空
                    window.location.reload();

				} else {
					alert(data.msg);
                    window.location.reload();
				}
			})
		})
	$("#btn_ref").click(function () {
		window.location.reload();
	})
	</script>
</body>
</html>
