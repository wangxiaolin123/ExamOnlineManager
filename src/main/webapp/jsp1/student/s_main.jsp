<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 22719
  Date: 2019/4/16
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>main</title>
    <!-- Bootstrap -->
    <link href="<%=basePath %>/css/bootstrap.min.css" rel="stylesheet">
    <script src="<%=path%>/js/jquery-3.3.1.min.js"></script>
    <script src="<%=path%>/js/sco.countdown.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="<%=basePath%>/js/bootstrap.min.js"></script>
    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<!--teacher头部-->
	<c:import url="../import/s_header.jsp">
		<c:param name="data">main</c:param>
	</c:import>
	<!--end 头部-->

<div class="container">
    <div class="row clearfix">
        <div class="alert alert-dismissable alert-warning">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
            <h4 id="noticeTitle"></h4>
            <div id="noticeContent">

            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="row clearfix">

                <div class="col-md-2 column">
                    <button type="button" class="btn btn-default">试卷下载</button>
                    <button type="button" class="btn btn-default">答案上传</button>
                    <div>
                        <div id="examTimeTitle">距离考试开始还有:</div>
                        <div id="examTimeCount"></div>
                    </div>
                </div>
                <div class="col-md-2 column">
                </div>
                <div class="col-md-10 column">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>考试编号</th>
                                    <th>考试名称</th>
                                    <th>考试时间</th>
                                    <th>考试状态</th>
                                    <th>考生操作</th>
                                </tr>
                                </thead>
                                <tbody id="examList">

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!--答卷提交-->
<div class="modal fade" data-keyboard="false" id="upAnswerModel"
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
                <form id="upAnswerForm"
                      class="form-horizontal" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">试卷文件：</label>
                        <div class="col-sm-10">
                            <input name="upFile" id="upFile" type="file" class="form-control"/>
                        </div>
                    </div>
                    <div class="hidden">
                        <input name="examID" id="examID" type="text" class="form-control"/>
                        <input name="stuNumber" id="stuNumber" type="text" class="form-control"/>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">
                    取消
                </button>
                <button id="upAnswerBtn" type="button"
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


<script>

    var HostIpPort="${sessionScope.HostIpPort}";
    //判断当前浏览器是否支持WebSocket
    var webSocket = null;
    if ('WebSocket' in window) {
        webSocket = new WebSocket("ws://"+HostIpPort+"/exam/socket/" +${sessionScope.user.number});
    } else if ('MozWebSocket' in window) {
        webSocket = new MozWebSocket("ws://"+HostIpPort+"/exam/socket/" + ${sessionScope.user.number});
    } else {
        alert('Not support webSocket');
    }

    //打开socket,握手
    webSocket.onopen = function (event) {
        //alert("websocket已经连接");
    }
    //接收推送的消息
    webSocket.onmessage = function (event) {

        var res=JSON.parse(event.data);

        var notice=res.data;
        console.info(notice);
        if(notice.signal=="start"){
            alert(notice.noticeTitle);
            window.location.reload();
        }if(notice.signal=="end"){
            alert(notice.noticeTitle);
            window.location.reload();
        }if(notice.signal=="notice"){

            $("#noticeTitle").html(notice.noticeTitle);
            $("#noticeContent").html(notice.noticeContent);

        }
    }
    //错误时
    webSocket.onerror = function (event) {
        console.info("发生错误");
        alert("websocket发生错误" + event);
    }

    //关闭连接
    webSocket.onclose = function () {
        console.info("关闭连接");
    }

    //监听窗口关闭
    window.onbeforeunload = function (event) {
        webSocket.close();
    }



    $(function () {

        var url = "<%=basePath%>/student/examList.do?stuNumber=${sessionScope.user.number}";
        $.post(url, function (data) {
            if (data.status == 200) {
                //提交成功，修改该数据
                console.info(data);
                var html = "";
                var item = data.data;
                for (var i = 0; i < item.length; i++) {
                    var startTime = timeStamp2String(item[i].startTime);
                    var endTime = timeStamp2String(item[i].endTime);
                    html += "<tr id='"+item[i].examID+"'>";
                    html += "<td>" + item[i].examID + "</td>";
                    html += "<td>" + item[i].examName + "</td>";
                    html += "<td>" + startTime + " 至 " + endTime + "</td>";
                    html += "<td>" + item[i].state + "</td>";
                    if("underway"== item[i].state){

                        $("#examTimeTitle").html("距离考试结束还有：");
                        $("#examTimeCount").scojs_countdown({until: item[i].endTime/1000});
                    html += "<td class='list-inline'><li>"
                        + "<form action='<%=basePath%>/student/downloadPaper.do' method='post'>"
                        + "<div class='hidden'><input type='text' name='examName' value='"+item[i].examName+"'>"
                        + "<input type='text' name='examPath' value='"+item[i].examPath+"'> </div>"
                        + "<input type='submit' value='试卷下载'>"
                        + "</form></li>";
                    html += "<li><a href='javascript:void(0)' onclick='upAnswer(" + item[i].examID + ",${sessionScope.user.number})'>上传答案</a></li></td>";
                    }else {
                        html += "<td class='list-inline'>未在考试时间段内</td>";
                    }
                    html += "</tr>";
                }
                $("#examList").html(html);

            } else {
                alert(data.msg);
            }
        })

    });

    //在Jquery里格式化Date日期时间数据
    function timeStamp2String(time) {
        var datetime = new Date();
        datetime.setTime(time);
        var year = datetime.getFullYear();
        var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
        var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
        var hour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime.getHours();
        var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
        //var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
        return year + "-" + month + "-" + date + " " + hour + ":" + minute;//+":"+second;
    }

    //下载试题
    function downPaper(examID) {
        alert("考试场次" + examID);

        var data = {
            examID: examID,
            //stuNumber:stuNumber,
        }
        var url = "<%=basePath%>/student/downPager.do";
        $.post(url, data, function (data) {
            if (data.status == 200) {
                alert("考试试卷下载成功");
            } else {
                alert(data.msg);
            }

        });

    }

    //上传答案
    function upAnswer(examID, stuNumber) {
        //将获取的该行的id值填充到模态框的文框中，文本框的ID为itemmodalid，其他的数据也是如此处理}
        $("#upAnswerForm :input[name='examID']").val(examID);
        $("#upAnswerForm :input[name='stuNumber']").val(stuNumber);
        //开启模态框
        $('#upAnswerModel').modal('toggle');

    }

    //上传试卷提交表单
    $("#upAnswerBtn").click(function () {
        var form = new FormData($("#upAnswerForm")[0]);
        $.ajax({
            type: "POST",
            url: "<%=basePath%>/student/upAnswer.do",
            data: form,
            processData: false,
            contentType: false,
            success: function (data) {
                alert("答卷上传完成");
                $('#upAnswerModel').modal('toggle');
                window.location.reload();
                //clearForm();
            },
            error: function (data) {
                alert("error");
            }
        });
    })

    //清空表单
    function clearForm() {
        $("form input[name!='isAdmin']").val("");
        $("form input[name='isAdmin']").removeAttr("checked");
    }

</script>


</body>
</html>
