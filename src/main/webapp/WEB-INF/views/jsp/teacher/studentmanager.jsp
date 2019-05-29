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
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>

<html>
<head>
    <title>学生管理</title>

    <c:import url="../import/style.jsp"></c:import>
    <!-- 引入bootstrap-table样式 -->
    <!-- 引入bootstrap-table样式 -->
    <link href="${basePath}/css/bootstrap-table.min.css" rel="stylesheet">

    <script src="${basePath}/js/tableExport.min.js"></script>
    <script src="${basePath}/js/bootstrap-table.min.js"></script>
    <script src="${basePath}/js/bootstrap-table-locale-all.min.js"></script>
    <script src="${basePath}/js/bootstrap-table-export.min.js"></script>
</head>
<body>
<style>
    .select,
    #locale {
        width: 100%;
    }
    .like {
        margin-right: 10px;
    }
</style>
<!--teacher头部-->
<c:import url="../import/t_header.jsp">
    <c:param name="data">exammanager</c:param>
</c:import>
<!--end 头部-->

<div class="container">
    <div class="row clearfix">

        <!--工具栏-->
        <div id="toolbar" class="btn-group">
            <button id="btn_import" type="button" class="btn btn-info">
                <i class="glyphicon glyphicon-import"></i>单个导入
            </button>
            <button id="btn_importAll" type="button" class="btn btn-success">
                <i class="glyphicon glyphicon-ok"></i>批量导入
            </button>
            <button id="remove" class="btn btn-danger" disabled>
                <i class="glyphicon glyphicon-remove"></i> 批量删除
            </button>

        </div>
    </div>
</div>

<table
        id="table"
        data-toolbar="#toolbar"
        data-search="true"
        data-show-refresh="true"
        data-show-toggle="true"
        data-show-fullscreen="true"
        data-show-columns="true"
        data-detail-view="true"
        data-show-export="true"
        data-detail-formatter="detailFormatter"
        data-minimum-count-columns="2"
        data-show-pagination-switch="true"
        data-pagination="true"
        data-id-field="id"
        data-page-list="[10, 25, 50, 100, ALL]"
        data-show-footer="true"
        data-side-pagination="server"
        data-url="${basePath}/teacher/getStudents.do"
        data-response-handler="responseHandler">
</table>

<!--单个导入-->
<div class="modal fade" data-keyboard="false" id="importStudentModel"
     data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">×
                </button>
                <h4 class="modal-title">
                    导入学生
                </h4>
            </div>
            <div class="modal-body">
                <form id="importStudentForm" action="#"
                      class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">学号</label>
                        <div class="col-sm-10">
                            <input name="stuNumber" type="text"
                                   class="form-control" placeholder="请输入学号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-10">
                            <input name="stuName" type="text" class="form-control" placeholder="请输入姓名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">选择班级</label>
                        <div class="col-sm-10">
                            <select name="classID" class="form-control">
                                <option value=1>1</option>
                                <option value=2>2</option>
                                <option value=3>3</option>
                                <option value=4>4</option>
                                <option value=5>5</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">
                    取消
                </button>
                <button id="importStudent" type="button"
                        class="btn btn-primary">
                    添加
                </button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<!--批量导入-->
<div class="modal fade" data-keyboard="false" id="importAllStudentModel"
     data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">×
                </button>
                <h4 class="modal-title">
                    批量导入学生信息
                </h4>
            </div>
            <div class="modal-body">
                <form id="importAllStudentForm" action="#" enctype="multipart/form-data"
                      class="form-horizontal" role="form">
                    <div class="text-center badge" style="background: darkgreen">
                        文件格式：第一行必须是列名，且列名必须包含"学号","姓名","班级"三列(可乱序)
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Excel文件</label>
                        <div class="col-sm-10">
                            <input name="file" type="file">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">
                    取消
                </button>
                <button id="importAllStudent" type="button"
                        class="btn btn-primary">
                    提交
                </button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<!--修改-->
<div class="modal fade" data-keyboard="false" id="updateStudentModel"
     data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">×
                </button>
                <h4 class="modal-title">
                    修改学生信息
                </h4>
            </div>
            <div class="modal-body">
                <form id="updateStudentForm" action="#"
                      class="form-horizontal" role="form">

                    <div class="form-group" >
                        <label class="col-sm-2 control-label">学号</label>
                        <div class="col-sm-10">
                            <input name="stuNumber" type="text"
                                   class="form-control" placeholder="请输入学号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-10">
                            <input name="stuName" type="text"
                                   class="form-control" placeholder="请输入姓名">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">选择班级</label>
                        <div class="col-sm-10">
                            <select name="classID" class="form-control">
                                <option value=1>1</option>
                                <option value=2>2</option>
                                <option value=3>3</option>
                                <option value=4>4</option>
                                <option value=5>5</option>
                            </select>
                        </div>
                    </div>

                    <div style="display: none;">
                            <input name="stuID" type="text">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">
                    取消
                </button>
                <button id="updateStudent" type="button"
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

<script type="text/javascript">

    var $table = $('#table')
    var $remove = $('#remove')
    var selections = []

    function getStuNumberSelections() {
        return $.map($table.bootstrapTable('getSelections'), function (row) {
            return row.stuNumber
        })
    }

    function responseHandler(res) {
        $.each(res.rows, function (i, row) {
            row.state = $.inArray(row.id, selections) !== -1
        })
        return res
    }

    function detailFormatter(index, row) {
        var html = []
        $.each(row, function (key, value) {
            html.push('<p><b>' + key + ':</b> ' + value + '</p>')
        })
        return html.join('')
    }


    function operateFormatter(value, row, index) {
       return [
            '<a class="like" href="javascript:void(0)" title="修改学生信息">',
            '<i class="glyphicon glyphicon-pencil"></i>',
            '</a>  ',
            '<a class="remove" href="javascript:void(0)" title="删除学生信息">',
            '<i class="glyphicon glyphicon-trash"></i>',
            '</a>'
        ].join('')
    }

    window.operateEvents = {

        'click .like': function (e, value, row, index) {
           console.info(row);
           //后台修改
            updateStudent(row);
            //刷新

        },
        'click .remove': function (e, value, row, index) {
            del(row.stuNumber);
            $table.bootstrapTable('remove', {
                field: 'stuNumber',
                values: [row.stuNumber]
            })
        }
    }


    //1.初始化Table
    $(function () {
        $table.bootstrapTable('destroy').bootstrapTable({
            height: 550,
            locale: 'zh-CN',
            columns:  [
                [{
                    field: 'state',
                    checkbox: true,
                    rowspan: 2,
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '学号',
                    field: 'stuNumber',
                    rowspan: 2,
                    align: 'center',
                    valign: 'middle',
                    //sortable: true,
                    //footerFormatter: totalTextFormatter
                }, {
                    title: '学生具体信息',
                    colspan: 3,
                    align: 'center'
                }],
                [{
                    field: 'stuName',
                    title: '姓名',
                    //sortable: true,
                    //footerFormatter: totalNameFormatter,
                    align: 'center'
                }, {
                    field: 'classID',
                    title: '班级',
                    //sortable: true,
                    align: 'center',
                    //footerFormatter: totalPriceFormatter
                }, {
                    field: 'operate',
                    title: '可用操作',
                    align: 'center',
                    events: window.operateEvents,
                    formatter: operateFormatter
                }]
            ]
        })


        $table.on('check.bs.table uncheck.bs.table ' +
            'check-all.bs.table uncheck-all.bs.table',
            function () {
                $remove.prop('disabled', !$table.bootstrapTable('getSelections').length)
                // save your data, here just save the current page
                selections = getStuNumberSelections()
                // push or splice the selections if you want to save all data selections
            })
        $table.on('all.bs.table', function (e, name, args) {
            //console.log(name, args)
        })

        $remove.click(function () {
            var stuNumbers = getStuNumberSelections()

            delStudents(stuNumbers);

            $remove.prop('disabled', true)
        })
    })


    //批量导入
    $("#btn_importAll").click(function () {
        //开启模态框
        $('#importAllStudentModel').modal('toggle');
    })

    $("#importAllStudent").click(function () {
        //获取数据
        var params = new FormData($("#importAllStudentForm")[0]);
        //params.append("examId", examId);
        //post请求添加数据
        var url = "${basePath}/teacher/importAllStudent.do";
        $.ajax({
            url: url,
            type: "POST",
            data: params,
            processData: false,       //必不可缺
            contentType: false,       //必不可缺
            success: function (e) {
                if (e.status == 200) {
                    alert(e.msg);
                    $('#importAllStudentModel').modal('toggle');
                    window.location.reload();
                } else {
                    alert(e.msg);
                }
            }
        })
    })

    //单个导入
    $("#btn_import").click(function () {
        //开启模态框
        $('#importStudentModel').modal('toggle');
    })
    $("#importStudent").click(function () {
        //获取数据
        var params = $("#importStudentForm").serialize();
        //var params2 = new FormData($("#importStudentForm")[0])
        //post请求添加数据
        var url = "${basePath}/teacher/importStudent.do";
        $.post(url, params, function (data) {
            if (data.status == 200) {
                alert("成功导入");
                window.location.reload();
            } else {
                alert(data.msg);
            }
        })
        //提交成功，列表项添加该数据
        $('#importStudentModel').modal('toggle');
        //数据清空
        clearForm();
    })

    //清空表单
    function clearForm() {
        $("form input").val("");
    }

    //更新信息
    function updateStudent(row) {

        $("#updateStudentForm :input[name='stuID']").val(row.stuID);
        $("#updateStudentForm :input[name='stuNumber']").val(row.stuNumber);
        $("#updateStudentForm :input[name='stuName']").val(row.stuName);
        //班级暂时不修改
        //开启模态框
        $('#updateStudentModel').modal('toggle');
    }

    $("#updateStudent").click(function() {
        //获取数据
        var params = $("#updateStudentForm").serialize();
        //post请求添加数据
        var url = "${basePath}/teacher/updateStudent.do";
        $.post(url, params, function (data) {
            if (data.status == 200) {
                //提交成功，修改该数据
                alert(data.msg);
                 $('#updateStudentModel').modal('toggle');
                clearForm();
                window.location.reload();
            } else {
                alert(data.msg);
            }
        })

    })

    //删除学生信息
    function del(stuNumber) {
        var msg = "您真的确定要删除 "+stuNumber+" 吗？\n\n请确认！";
        if (confirm(msg) == true) {
           deleteStudent(stuNumber);
            return true;
        } else {
            return false;
        }
    }
    function deleteStudent(stuNumber) {
        // 请求服务器删除数据
        var url = "${basePath}/teacher/deleteStudent.do?stuNumber="+stuNumber;
        $.get(url, function (data) {
            if (data.status == 200) {
                alert("删除学生信息成功");
                //window.location.reload();
            } else {
                alert(data.msg);
            }
        })
    }

    //批量删除
    function delStudents(stuNumbers) {

        console.info(stuNumbers);
        var params = {"stuNumbers":JSON.stringify(stuNumbers)};
        //var params2 = new FormData($("#importStudentForm")[0])
        //post请求添加数据
        var url = "${basePath}/teacher/deleteStudents.do";
        $.post(url, params, function (data) {
            if (data.status == 200) {
                alert("删除成功");
                $table.bootstrapTable('remove', {
                    field: 'stuNumber',
                    values: stuNumbers
                })
            } else {
                alert(data.msg);
                window.location.reload();
            }
        })

    }

</script>
</body>
</html>
