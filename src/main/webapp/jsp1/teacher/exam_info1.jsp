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

    <c:import url="../import/style.jsp"></c:import>
    <script src="<%=path%>/js/sco.countdown.js"></script>

<body>

<!--teacher头部-->
<c:import url="../import/t_header.jsp">
    <c:param name="data">main</c:param>
</c:import>
<!--end 头部-->
距离考试开始还有: <div id="endTimeCount"></div>
<script>
    $("#endTimeCount").scojs_countdown({until: 1558850400});
</script>

<div class="container">

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
                            距离考试开始还有: <div id="startTimeCount"></div>
                            <script>
                                $("#startTimeCount").scojs_countdown({unitl: 1558850400})
                            </script>
                        </dd>
                        <dd>
                            <button class="btn-default" id="forceBeginExam">强制开启考考试</button>
                        </dd>
                        <dd>
                            <button class="btn-default" id="btn_import">添加额外考生</button>
                        </dd>
                    </c:when>
                    <c:when test="${sessionScope.examInfo.state == 'underway'}">
                        <dd>

                        </dd>
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
                <dd>${sessionScope.examInfo.className}</dd>
            </dl>
        </div>
    </div>
</div>
<script type="text/javascript">

    var HostIpPort = "${sessionScope.HostIpPort}";
    //判断当前浏览器是否支持WebSocket
    var webSocket = null;
    if ('WebSocket' in window) {
        webSocket = new WebSocket("ws://" + HostIpPort + "/exam/socket/${sessionScope.user.number}");
    } else if ('MozWebSocket' in window) {
        webSocket = new MozWebSocket("ws://" + HostIpPort + "/exam/socket/${sessionScope.user.number}");
    } else {
        alert('Not support webSocket');
    }

    //打开socket,握手
    webSocket.onopen = function (event) {
        //alert("websocket已经连接");
    }
    //接收推送的消息
    webSocket.onmessage = function (event) {

        var res = JSON.parse(event.data);

        if (res.msg == "onLine") {
            var list = res.data;

            var onLineCount = 0;
            var subCount = 0;
            var count = 0;
            var onLineHtml = "";
            var offLineHtml = "";
            var submitHtml = "";
            var notSubHtml = "";

            $.each(list, function (index, item) {

                count++;
                console.info(item);
                if (item.onLine == true) {

                    onLineCount++;
                    onLineHtml += "<li class='list-group-item'>" + item.stuNumber + '(' + item.stuName + ')' + "在线</li>";
                } else {
                    offLineHtml += "<li class='list-group-item'>" + item.stuNumber + '(' + item.stuName + ')' + "离线</li>";
                }

                if (item.submit == true) {
                    subCount++;
                    submitHtml += "<li class='list-group-item'>" + item.stuNumber + '(' + item.stuName + ')' + "</li>";
                } else {
                    notSubHtml += "<li class='list-group-item'>" + item.stuNumber + '(' + item.stuName + ')' + "</li>";
                }
            })


            alert(count);
            $("#onLineInfo").html("在线情况:<br>" + onLineCount + " 人在线 " + (count - onLineCount) + " 人离线");
            $("#submitInfo").html("考试提交情况:<br>" + subCount + " 人已提交 " + (count - subCount) + " 人未提交");

            $("#onLineShow").html(onLineHtml);
            $("#offLineShow").html(offLineHtml);


            $("#alreadyUpShow").html(submitHtml);
            $("#notUpShow").html(notSubHtml);


        } else if (res.msg == "signal") {
            var notice = rs.data;
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


        var StringStudent = $("#unIpForm :input[name='stuNumber']").val();

        var params = {
            "stuNumber": StringStudent,
            "examID":${sessionScope.examInfo.examID}
        };
        var url = "<%=basePath%>/teacher/unBindIp.do";

        $.post(url, params, function (data) {
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
        var url = "<%=basePath%>/teacher/importAdditionStudent.do";
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


</script>
</body>
</html>
