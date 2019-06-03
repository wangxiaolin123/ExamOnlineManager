<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>

<html>
    <!-- 导入js css等 -->
    <c:import url="../import/style.jsp"></c:import>

    <!-- 引入bootstrap-table样式 -->
    <link href="${basePath}/css/bootstrap-table.min.css" rel="stylesheet">

    <script src="${basePath}/js/tableExport.min.js"></script>
    <script src="${basePath}/js/bootstrap-table.min.js"></script>
    <script src="${basePath}/js/bootstrap-table-locale-all.min.js"></script>
    <script src="${basePath}/js/bootstrap-table-export.min.js"></script>
<body>
<!--admin头部-->
<c:import url="../import/a_header.jsp">
    <c:param name="data">main</c:param>
</c:import>
<!--end admin头部-->


<ul class="breadcrumb">
    <li><a href="${basePath}/admin/mainPage.do">首页</a></li>
    <li><a href="${basePath}/admin/examclean.do" class="active">考试清理</a></li>
</ul>
<!--工具栏-->
<div id="toolbar" class="btn-group">
  <%--  <button id="remove" class="btn btn-danger" disabled>
        <i class="glyphicon glyphicon-remove"></i> 批量清除
    </button>--%>

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
        data-url="${basePath}/admin/getExamInfos.do?state=end"
        data-response-handler="responseHandler">
</table>


<script type="text/javascript">

    var $table = $('#table')
    var $remove = $('#remove')
    var selections = []


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
            '<a class="remove" href="javascript:void(0)" title="清理考试">',
            '<i class="glyphicon glyphicon-trash"></i>',
            '</a>'
        ].join('')
    }

    window.operateEvents = {

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
                    title: '考试编号',
                    field: 'examID',
                    rowspan: 2,
                    align: 'center',
                    valign: 'middle',
                    //sortable: true,
                    //footerFormatter: totalTextFormatter
                }, {
                    title: '考试具体信息',
                    colspan: 3,
                    align: 'center'
                }],
                [{
                    field: 'examName',
                    title: '考试名称',
                    //sortable: true,
                    //footerFormatter: totalNameFormatter,
                    align: 'center'
                },
                    {
                        field: 'className',
                        title: '考试班级',
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
                // push or splice the selections if you want to save all data selections
            })
        $table.on('all.bs.table', function (e, name, args) {
            //console.log(name, args)
        })

    })

    //删除考试信息
    function del(row) {
        var msg = "您真的确定要删除 "+row.examName+" 吗？\n\n请确认！";
        if (confirm(msg) == true) {
            deleteExam(row.examID);
            return true;
        } else {
            return false;
        }
    }

    function deleteExam(examID) {
        // 请求服务器删除数据
        var url = "${basePath}/teacher/deleteExam.do/" + examID;
        $.get(url, function (data) {
            if (data.status == 200) {
                alert("删除考试信息成功");
                //window.location.reload();
            } else {
                alert(data.msg);
            }
        })
    }

</script>

</body>
</html>
