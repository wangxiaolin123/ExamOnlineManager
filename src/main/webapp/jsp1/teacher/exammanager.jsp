<%--
  Created by IntelliJ IDEA.
  User: 22719
  Date: 2019/4/22
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<html>
<head>
    <title>考试管理</title>
    <link href="<%=basePath %>/assert/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="<%=basePath%>/js/jq1.12.4/jquery.min.js"></script>
    <script src="<%=basePath%>/js/jquery-3.3.1.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="<%=basePath%>/assert/bootstrap/js/bootstrap.min.js"></script>
    <!-- 时间选择组件 -->
    <link href="<%=basePath %>/assert/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <script src="<%=basePath %>/assert/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
    <script src="<%=basePath %>/assert/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"></script>

</head>
<body>

	<!--teacher头部-->
	<c:import url="../import/t_header.jsp">
		<c:param name="data">exammanager</c:param>
	</c:import>
	<!--end 头部-->

<div class="container">
    <div class="row clearfix">
        <div class="col-md-2 column">
            <button type="button" id="btn_add" class="btn btn-default">添加考试</button>
            <button type="button" class="btn btn-default">其他</button>
        </div>
        <div class="col-md-10 column">
            <table id="examList" class="table table-condensed table-hover">
                <thead>
                <tr>
                    <th>考试编号</th>
                    <th>考试名称</th>
                    <th>考试时间</th>
                    <th>是否自动开始</th>
                    <th>当前考试状态</th>
                    <th>考生班级</th>
                    <th>可用操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${sessionScope.examList}" var="item">
                    <tr>
                        <td>${item.examID}</td>
                        <td><a href="<%=basePath%>/teacher/examming.do?examID=${item.examID}">${item.examName}</a></td>
                        <td>
                            <en><fmt:formatDate value="${item.startTime}" pattern="yyyy-MM-dd HH:mm"/></en>
                            至 <em><fmt:formatDate value="${item.endTime}" pattern="yyyy-MM-dd HH:mm"/></em>
                        </td>
                        <td>${item.isAuto}</td>
                        <td>${item.state}</td>
                        <td>${item.className}</td>
                        <td class="list-inline">
                            <li><a href="javascript:void(0)" onclick="upPaper(this)">上传试卷</a></li>
                            <li><a href="javascript:void(0)" onclick="editExam(this)">修改</a></li>
                            <li><a href="javascript:void(0)" onclick="del(this)">删除</a></li>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>


<!--新增-->
<div class="modal fade" data-keyboard="false" id="addExamModel"
     data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">×
                </button>
                <h4 class="modal-title">
                    创建考试
                </h4>
            </div>
            <div class="modal-body">
                <form id="addExamForm" action="#"
                      class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">考试名称</label>
                        <div class="col-sm-10">
                            <input name="examName" type="text"
                                   class="form-control" placeholder="请输入考试名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">开始时间</label>
                        <div class="col-sm-10">
                            <input name="startTime" type="text"
                                   readonly class="form-control form_datetime">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">结束时间</label>
                        <div class="col-sm-10">
                            <input name="endTime" type="text"
                                   readonly class="form-control form_datetime">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">选择班级</label>
                        <div class="col-sm-10">
                            <select id="addClass" name="classID" class="selector form-control">
                            </select>
                        </div>
                    </div>
                    <div class="checkbox">
                        <label><input name="isAuto" type="checkbox"/>自动开始</label>
                    </div>
                    <div class="hidden">
                        <input name="teaNumber" type="text" class="form-control" value="${sessionScope.user.number}"/>
                        <input name="state" type="text" value="before"/>
                        <input name="examPath" type="text" value="/${sessionScope.user.number}"/>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">
                    取消
                </button>
                <button id="addExam" type="button"
                        class="btn btn-primary">
                    创建
                </button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<!--修改-->
<div class="modal fade" data-keyboard="false" id="updateExamModel"
     data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">×
                </button>
                <h4 class="modal-title">
                    修改考试信息
                </h4>
            </div>
            <div class="modal-body">
                <form id="updateExamForm" action="#"
                      class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">考试名称</label>
                        <div class="col-sm-10">
                            <input name="examName" type="text"
                                   class="form-control" placeholder="请输入考试名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">开始时间</label>
                        <div class="col-sm-10">
                            <input name="startTime" type="text"
                                   readonly class="form-control form_datetime">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">结束时间</label>
                        <div class="col-sm-10">
                            <input name="endTime" type="text"
                                   readonly class="form-control form_datetime">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">选择班级</label>
                        <div class="col-sm-10">
                            <select id="updateClass" name="classID" class="selector form-control">

                            </select>
                        </div>
                    </div>
                    <div class="checkbox">
                        <label><input name="isAuto" type="checkbox"/>自动开始</label>
                    </div>
                    <div class="hidden">
                        <input name="examID" type="text" class="form-control"/>
                        <input name="teaNumber" type="text" class="form-control" value="${sessionScope.user.number}"/>
                        <input name="state" type="text" value="before"/>
                        <input name="examPath" type="text" value="/${sessionScope.user.number}"/>
                    </div>
                </form>

            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">
                    取消
                </button>
                <button id="updateExam" type="button"
                        class="btn btn-primary">
                    提交更改
                </button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
</div>

<!--试卷提交-->
<div class="modal fade" data-keyboard="false" id="upPaperModel"
     data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">×
                </button>
                <h4 class="modal-title">
                    上传试卷
                </h4>
            </div>
            <div class="modal-body">
                <form id="upPaperForm"
                      class="form-horizontal" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">试卷文件：</label>
                        <div class="col-sm-10">
                            <input name="upFile" id="upFile" type="file" class="form-control"/>
                        </div>
                    </div>
                    <div class="hidden">
                        <input name="examID" id="examID" type="text" class="form-control"/>
                       </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">
                    取消
                </button>
                <button id="upPaperBtn" type="button"
                        class="btn btn-primary">
                    上传
                </button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->


<script type="text/javascript">

    $(function () {
        addClassSelects();
    })


    //时间控件初始化
    $(".form_datetime").datetimepicker({
        format: "yyyy-mm-dd hh:ii",
        autoclose: true,
        todayBtn: true,
        minuteStep: 1,
        startDate: new Date()
    });

    //考试结束时间 >= 考试开始时间
    function checkDate() {
        var cDate = $("#addExamForm input[name='startTime']").val();
        $("#addExamForm :input[name='endTime']").datetimepicker('setStartDate', cDate);
        $("#addExamForm :input[name='endTime']").val(cDate);

        var uDate = $("#updateExamForm input[name='startTime']").val();
        $("#updateExamForm :input[name='endTime']").datetimepicker('setStartDate', uDate);
        $("#updateExamForm :input[name='endTime']").val(uDate);
    }

    $("input[name='startTime']").change(function () {
        checkDate();
    })

    //新增考试
    $("#btn_add").click(function () {
        //开启模态框
        $('#addExamModel').modal('toggle');

    })
    //新增考试提交表单
    $("#addExam").click(function () {
        //获取数据
        var params = $("#addExamForm").serialize();
        //post请求添加数据
        var url = "<%=basePath%>/teacher/addExam.do";
        $.post(url, params, function (data) {
            if (data.status == 200) {
                alert('考试添加成功');
                // 提交成功，列表项添加该数据
                //addRow(data.data);
                window.location.reload();
            } else {
                //alert重新输入
                alert(data.msg);
            }
        })
        $('#addExamModel').modal('toggle');
    })


    //删除考试
    function del(obj) {
        var msg = "您真的确定要删除吗？\n\n请确认！";
        if (confirm(msg) == true) {
            deleteExam(obj);
            return true;
        } else {
            return false;
        }
    }

    function deleteExam(obj) {
        //获取要删除的考试id
        var tr = $(obj).parent().parent().parent();
        var examID = tr.find("td:eq(0)").text();
        // 请求服务器删除数据
        var url = "<%=basePath%>/teacher/deleteExam.do/" + examID;
        $.get(url, function (data) {
            if (data.status == 200) {
                alert("删除考试成功");
                window.location.reload();
            } else {
                alert(data.msg);
            }
        })
    }

    //修改考试
    function editExam(obj) {

        var tr = $(obj).parent().parent().parent();
        //将获取的该行的id值填充到模态框的文框中，文本框的ID为itemmodalid，其他的数据也是如此处理}


        var className=tr.find("td:eq(5)").text();
        $("#updateClass option[text='"+className+"']").attr("selected", true);
        $("#updateExamForm :input[name='examID']").val(tr.find("td:eq(0)").text());
        $("#updateExamForm :input[name='examName']").val(tr.find("td:eq(1)").find("a:eq(0)").text());
        //$("#updateExamForm :input[name='startTime']").val(tr.find("td:eq(4)").find("en:eq(0)").text());
        //$("#updateExamForm :input[name='endTime']").val(tr.find("td:eq(4)").find("en:eq(1)").text());
        if (tr.find("td:eq(3)").text() == 'true') {
            $("#updateExamForm :input[name='isAdmin']").prop("checked", true);
        } else {
            $("#updateExamForm :input[name='isAdmin']").removeAttr("checked");
        }
        $("#updateExamForm :input[name='state']").val(tr.find("td:eq(4)").text());
        //开启模态框
        $('#updateExamModel').modal('toggle');

    }

    //修改考试提交表单
    $("#updateExam").click(function () {
        //获取数据
        var params = $("#updateExamForm").serialize();
        //var t = $("#updateExamForm").serializeArray();
        //post请求添加数据
        var url = "<%=basePath%>/teacher/updateExam.do";
        $.post(url, params, function (data) {
            if (data.status == 200) {
                //提交成功，修改该数据
                window.location.reload();
                $('#updateExamModel').modal('toggle');
                //数据清空
                clearForm();

            } else {
                alert(data.msg);
            }
        })
    })



    //上传试卷
    function upPaper(obj) {

        var tr = $(obj).parent().parent().parent();
        //将获取的该行的id值填充到模态框的文框中，文本框的ID为itemmodalid，其他的数据也是如此处理}
        $("#upPaperForm :input[name='examID']").val(tr.find("td:eq(0)").text());
        //开启模态框
        $('#upPaperModel').modal('toggle');

    }

    //上传试卷提交表单
    $("#upPaperBtn").click(function () {

        var form = new FormData($("#upPaperForm")[0]);
        console.log(form);
        $.ajax({
            type: "POST",
            url: "<%=basePath%>/teacher/upPaper.do",
            data:form,
            processData:false,
            contentType:false,
            success: function (data) {
                alert("试卷上传完成");
                $('#upPaperModel').modal('toggle');
                clearForm();
            },
            error: function(data) {
                alert("error");
            }
        });
    })

    //清空表单
    function  clearForm() {
        $("form input[name!='isAdmin']").val("");
        $("form input[name='isAdmin']").removeAttr("checked");
    }

    //获取班级对应的名称
    function addClassSelects() {

        var url="<%=basePath%>/teacher/getClassSelects.do";
        $.get(url,function(res) {

            var list=res.data;
            var selectOption= "";

            $.each(list, function (index, item) {
                selectOption += "<option value=\"" + item.classID + "\" text=\""+item.className +"\">" + item.className + "</option>";
            })
           $('.selector').html(selectOption);
        })
    }


</script>
</body>
</html>
