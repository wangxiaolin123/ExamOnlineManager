<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path;
%>
<html>
    <!-- 导入js css等 -->
    <c:import url="../import/style.jsp"></c:import>

    <link href="<%=path%>/assert/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- 引入bootstrap-table样式 -->
    <link href="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.css" rel="stylesheet">

    <script src="https://unpkg.com/tableexport.jquery.plugin/tableExport.min.js"></script>
    <script src="https://unpkg.com/bootstrap-table@1.14.2/dist/bootstrap-table.min.js"></script>
    <script src="https://unpkg.com/bootstrap-table@1.14.2/dist/bootstrap-table-locale-all.min.js"></script>
    <script src="https://unpkg.com/bootstrap-table@1.14.2/dist/extensions/export/bootstrap-table-export.min.js"></script>
<body>
<!--admin头部-->
<c:import url="../import/a_header.jsp">
    <c:param name="data">main</c:param>
</c:import>
<!--end admin头部-->

<!--工具栏-->
<div id="toolbar" class="btn-group">
  <%--  <button id="remove" class="btn btn-danger" disabled>
        <i class="glyphicon glyphicon-remove"></i> 批量清除
    </button>--%>
      <button id="btn_add" type="button" class="btn btn-info">
          <i class="glyphicon glyphicon-import"></i>添加班级
      </button>


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
        data-url="<%=basePath%>/admin/getClassInfos.do"
        data-response-handler="responseHandler">
</table>

<!--添加-->
<div class="modal fade" data-keyboard="false" id="addClassModel"
     data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">×
                </button>
                <h4 class="modal-title">
                    修改班级信息
                </h4>
            </div>
            <div class="modal-body">
                <form id="addClassForm" action="#"
                      class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">班级名称</label>
                        <div class="col-sm-10">
                            <input name="className" type="text"
                                   class="form-control" placeholder="请输入创建的班级名称">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">
                    取消
                </button>
                <button id="addClass" type="button"
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

<!--修改-->
<div class="modal fade" data-keyboard="false" id="updateClassModel"
     data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">×
                </button>
                <h4 class="modal-title">
                    修改班级信息
                </h4>
            </div>
            <div class="modal-body">
                <form id="updateClassForm" action="#"
                      class="form-horizontal" role="form">

                    <div class="form-group hidden">
                        <input name="classID" type="text"
                               class="form-control">
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">班级名称</label>
                        <div class="col-sm-10">
                            <input name="className" type="text"
                                   class="form-control">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">
                    取消
                </button>
                <button id="updateClass" type="button"
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
            return row.classID
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
            '<a class="like" href="javascript:void(0)" title="修改班级">',
            '<i class="glyphicon glyphicon-pencil"></i>',
            '</a>  ',
            '<a class="remove" href="javascript:void(0)" title="删除班级">',
            '<i class="glyphicon glyphicon-trash"></i>',
            '</a>'
        ].join('')
    }

    window.operateEvents = {

        'click .like': function (e, value, row, index) {
            console.info(row);
            //后台修改
            updateClass(row);
            //刷新

        },
        'click .remove': function (e, value, row, index) {
            del(row);
            $table.bootstrapTable('remove', {
                field: 'classID',
                values: [row.clasID]
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
                    title: '班级ID',
                    field: 'classID',
                    rowspan: 2,
                    align: 'center',
                    valign: 'middle',
                    //sortable: true,
                    //footerFormatter: totalTextFormatter
                }, {
                    title: '班级具体信息',
                    colspan: 3,
                    align: 'center'
                }],
                [{
                    field: 'className',
                    title: '班级名称',
                    //sortable: true,
                    //footerFormatter: totalNameFormatter,
                    align: 'center'
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

    })

    //更新信息
    function updateClass(row) {

        $("#updateClassForm :input[name='classID']").val(row.classID);
        $("#updateClassForm :input[name='className']").val(row.className);
        //开启模态框
        $('#updateClassModel').modal('toggle');
    }

    $("#updateClass").click(function() {
        //获取数据
        var params = $("#updateClassForm").serialize();
        //post请求添加数据
        var url = "<%=basePath%>/admin/updateClass.do";
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

    //删除班级信息
    function del(row) {
        var msg = "您真的确定要删除 "+row.className+" 吗？\n\n请确认！";
        if (confirm(msg) == true) {
            deleteClass(row.classID);
            return true;
        } else {
            return false;
        }
    }

    function deleteClass(classID) {
        // 请求服务器删除数据
        var url = "<%=basePath%>/admin/deleteClass.do?classID="+classID;
        $.get(url, function (data) {
            if (data.status == 200) {
                alert("删除班级信息成功");
                //window.location.reload();
            } else {
                alert(data.msg);
            }
        })
    }
    //清空表单
    function clearForm() {
        $("form input").val("");
    }

    //单个添加
    $("#btn_add").click(function () {
        //开启模态框
        $('#addClassModel').modal('toggle');
    })
    $("#addClass").click(function () {
        //获取数据
        var params = $("#addClassForm").serialize();
        var url = "<%=basePath%>/admin/addClass.do";
        $.post(url, params, function (data) {
            if (data.status == 200) {
                alert("成功添加班级");
                window.location.reload();
            } else {
                alert(data.msg);
            }
        })
        //提交成功，列表项添加该数据
        $('#addClassModel').modal('toggle');
        //数据清空
        clearForm();
    })

</script>

</body>
</html>
