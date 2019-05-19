<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
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
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path;
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>

    <title>main</title>
    <!-- 导入js css等 -->
    <!-- Bootstrap core JavaScript
================================================== -->
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="<%=basePath%>/js/jq1.12.4/jquery.min.js"></script>

    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="<%=basePath%>/js/bootstrap.min.js"></script>
    <c:import url="../import/style.jsp"></c:import>
</head>

<body>

<!--teacher头部-->
<c:import url="../import/t_header.jsp">
    <c:param name="data">main</c:param>
</c:import>
<!--end 头部-->

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <button class="btn-default" id="addNotice">发布通知</button>

            <!--通知-->
            <div class="modal fade" data-keyboard="false" id="noticeModel"
                 data-backdrop="static" tabindex="-1" role="dialog"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close"
                                    data-dismiss="modal" aria-hidden="true">×
                            </button>
                            <h4 class="modal-title">
                                发布通知
                            </h4>
                        </div>
                        <div class="modal-body">
                            <form id="noticeForm" action="#"
                                  class="form-horizontal" role="form">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">标题</label>
                                    <div class="col-sm-10">
                                        <input name="noticeTitle" id="noticeTitle" type="text"
                                               class="form-control" placeholder="请输入通知标题">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">内容</label>
                                    <div class="col-sm-10">
                                        <input name="noticeContent" id="noticeContent" type="text"
                                               class="form-control" placeholder="请输入通知内容">
                                    </div>
                                </div>

                            </form>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default"
                                    data-dismiss="modal">
                                取消
                            </button>
                            <button id="noticeSend" type="button"
                                    class="btn btn-primary">
                                发布
                            </button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            <!-- /.modal -->

        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-4 column">
            <dl>
                <dt>考试名称</dt>
                <dd>${sessionScope.examInfo.examName}</dd>
                <dt>考试时间</dt>
                <dd>开始时间:<fmt:formatDate value="${sessionScope.examInfo.startTime}" pattern="yyyy-MM-dd HH:mm"/></dd>
                <dd>结束时间:<fmt:formatDate value="${sessionScope.examInfo.endTime}" pattern="yyyy-MM-dd HH:mm"/></dd>
                <dt>当前考试状态</dt>
                <dd><c:if test="${sessionScope.examInfo.isAuto == true }">自动开始<br></c:if>
                    当前考试状态:${sessionScope.examInfo.state}
                </dd>
                <dt>可用操作</dt>
                <c:choose>
                    <c:when test="${sessionScope.examInfo.state == 'before'}">
                        <dd>
                        <button class="btn-default" id="forceBeginExam">强制开启考考试</button>
                    </dd>
                        <dd>
                            <button class="btn-default">添加额外考生</button>
                        </dd>
                    </c:when>
                    <c:when test="${sessionScope.examInfo.state == 'underway'}">
                        <dd>
                            <button class="btn-default" id="unBindIp">解绑指定学生ip</button>
                        </dd>
                    </c:when>

                    <c:when test="${sessionScope.examInfo.state == 'end'}">
                        <dd>
                            <form action='<%=basePath%>/teacher/downloadZip.do' method='post'>
                                <div class='hidden'>
                                    <input type='text' name='examName' value='${sessionScope.examInfo.examName}'>
                                    <input type='text' name='examID' value='${sessionScope.examInfo.examID}'>
                                </div>
                                <input type='submit' value='打包下载'>
                            </form>
                        </dd>
                        <dd>
                            <form action='<%=basePath%>/teacher/downloadUpInfo.do' method='post'>
                                <div class='hidden'>
                                    <input type='text' name='examName' value='${sessionScope.examInfo.examName}'>
                                    <input type='text' name='examID' value='${sessionScope.examInfo.examID}'>
                                </div>
                                <input type='submit' value='考生提交信息导出'>
                            </form>
                        </dd>
                    </c:when>

                </c:choose>
                <dt>考生班级</dt>
                <dd>${sessionScope.examInfo.classID}</dd>
            </dl>
        </div>
        <div class="col-md-4 column">

            <div class="col-md-12">
                <a href="#" class="list-group-item active">
                    <h4 class="list-group-item-heading">
                        <span class="label label-info" id="submitInfo">
                            考试提交情况:<br>
                            0 人已提交 所有人未提交
                        </span>
                    </h4>
                </a>
                <ul class="list-group">

                    <div id="alreadyUpShow">

                    </div>
                    <div id="notUpShow">

                    </div>
                </ul>
            </div>

        </div>
        <div class="col-md-4 column" id="onlineCount">
            <div class="col-md-12">
                <a href="#" class="list-group-item active">
                    <h4 class="list-group-item-heading">
                        <span class="label label-info" id="onLineInfo">
                            在线情况:<br>
                            0 人在线 所有人离线
                        </span>
                    </h4>
                </a>
                <ul class="list-group">
                    <div id="onLineShow">

                    </div>
                    <div id="offLineShow">

                    </div>
                </ul>
            </div>

        </div>
    </div>
</div>


<!--通知-->
<div class="modal fade" data-keyboard="false" id="unIpModel"
     data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">×
                </button>
                <h4 class="modal-title">
                    解绑指定学生Ip
                </h4>
            </div>
            <div class="modal-body">
                <form id="unIpForm" action="#"
                      class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">学号</label>
                        <div class="col-sm-10">
                            <input name="stuNumber" type="text"
                                   class="form-control" placeholder="请输入学生学号">
                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">
                    取消
                </button>
                <button id="unIpSend" type="button"
                        class="btn btn-primary">
                    解绑
                </button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script type="text/javascript">

    var HostIp="localhost"
    //判断当前浏览器是否支持WebSocket
    var webSocket = null;
    if ('WebSocket' in window) {
        webSocket = new WebSocket("ws://"+HostIp+":8080/exam/socket/" + ${sessionScope.user.number});
    } else if ('MozWebSocket' in window) {
        webSocket = new MozWebSocket("ws://"+HostIp+":8080/exam/socket/" +${sessionScope.user.number});
    } else {
        alert('Not support webSocket');
    }

    //打开socket,握手
    webSocket.onopen = function (event) {
        alert("websocket已经连接");
    }
    //接收推送的消息
    webSocket.onmessage = function (event) {

        var res = JSON.parse(event.data);

        if (res.msg == "onLine") {
            var list = res.data;

            var onLineCount=0;
            var subCount=0;
            var count=0;
            var onLineHtml = "";
            var offLineHtml="";
            var submitHtml="";
            var notSubHtml="";

            $.each(list, function (index, item) {

                count++;
                console.info(item);
                if(item.onLine == true){

                    onLineCount++;
                    onLineHtml += "<li class='list-group-item'>" + item.stuNumber + '(' + item.stuName + ')' + "在线</li>";
                }else {
                    offLineHtml += "<li class='list-group-item'>" + item.stuNumber + '(' + item.stuName + ')' + "离线</li>";
                }

                if(item.submit == true){
                    subCount++;
                    submitHtml += "<li class='list-group-item'>" + item.stuNumber + '(' + item.stuName + ')' + "</li>";
                }else {
                    notSubHtml += "<li class='list-group-item'>" + item.stuNumber + '(' + item.stuName + ')' + "</li>";
                }
              })


            alert(count);
            $("#onLineInfo").html("在线情况:<br>"+onLineCount+" 人在线 "+(count-onLineCount)+" 人离线");
            $("#submitInfo").html("考试提交情况:<br>"+subCount+" 人已提交 "+(count-subCount)+" 人未提交");

            $("#onLineShow").html(onLineHtml);
            $("#offLineShow").html(offLineHtml);


            $("#alreadyUpShow").html(submitHtml);
            $("#notUpShow").html(notSubHtml);


        } else if(res.msg == "signal"){
            var notice=rs.data;
            alert(notice.NoticeTitle);
            window.location.reload();
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

    //单发
    function sendMessageToUser() {
        if (webSocket.readyState == webSocket.OPEN) {

            var notice = {

                noticeID: 1,
                noticeTitle: "标题",
                signal: "测试",
                noticeTime: new Date(),
                noticeContent: "内容",
                examID: 1,
                teaNumber: "1010120001"

            }

            var massage = JSON.stringify(notice);
            webSocket.send(massage); //发送json数据
            alert("发送成功!");
        } else {
            alert("连接失败!");
        }
    }

    //群发
    function sendMessageToAll(notice) {
        if (webSocket.readyState == webSocket.OPEN) {

            var massage = JSON.stringify(notice);

            webSocket.send(massage); //发送json数据
            alert("通知发布成功!");
        } else {
            alert("通知发布失败!");
        }
    }

    //新建通知
    $("#addNotice").click(function () {
        //开启模态框
        $('#noticeModel').modal('toggle');
    })

    $("#noticeSend").click(function () {

        var notice = {

            //noticeID: 1,
            noticeTitle: $("#noticeTitle").val(),
            signal: "notice",
            noticeTime: new Date(),
            noticeContent: $("#noticeContent").val(),
            examID: 1,
            teaNumber: "${sessionScope.user.number}"
        }
        sendMessageToAll(notice);
        //清空表单
        $("form input[name!='isAdmin']").val("");

        $('#noticeModel').modal('toggle');
    })

    //解绑ip
    $("#unBindIp").click(function () {
        //开启模态框
        $('#unIpModel').modal('toggle');
    })

    $("#unIpSend").click(function () {


        var StringStudent=$("#unIpForm :input[name='stuNumber']").val();

        var params={
            "stuNumber":StringStudent,
            "examID":${sessionScope.examInfo.examID}
        };
        var url = "<%=basePath%>/teacher/unBindIp.do";

        $.post(url,params, function (data) {
            if (data.status == 200) {
                alert('学生Ip信息解除成功！');
            } else {
                //alert重新输入
                alert(data.msg);
            }
        })

        $("form input[name!='isAdmin']").val("");
        $('#unIpModel').modal('toggle');
    })


    //强制考试开始
    $("#forceBeginExam").click(function () {

        var url = "<%=basePath%>/teacher/forceBegin.do?examID=${sessionScope.examInfo.examID}";

        $.post(url, function (data) {
            if (data.status == 200) {
                alert('强制考试开始成功，考试开始！');
                window.location.reload();
            } else {
                //alert重新输入
                alert(data.msg);
            }
        })
    })
</script>

</body>
</html>
