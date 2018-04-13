<%--
  Created by IntelliJ IDEA.
  User: 11111
  Date: 2018/3/21
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>日报管理系统登录</title>
    <%
        pageContext.setAttribute("APP_PATH", request.getContextPath());
    %>
    <script type="text/javascript"
            src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>
    <script src="${APP_PATH}/static/js/common.js"></script>
    <script src="${APP_PATH}/static/layui/layui.all.js"></script>
    <link rel="stylesheet" href="${APP_PATH}/static/layui/css/layui.css">
    <script src="${APP_PATH}/static/js/inputSuggest.js" type="text/javascript"></script>
    <style type="text/css">
        .suggest-container{border:1px solid #C1C1C1; font-size:12px; color:#333; background:#FFFFFF; visibility:hidden;}
        .suggest-item{padding:3px 5px;}
        .suggest-active {background:#33CCFF; font-size:16px; color:#050406; padding:3px 5px;}
    </style>
</head>
<body bgcolor="PINK" onkeyup="keyOnClick(event)">
<div class="center-in-center">
<p class="layui-input-block"  style="width: 600px">
    <font color="white"/>邮&nbsp;&nbsp;&nbsp; 箱：
    <input id="email" type="text" lay-verify="" required placeholder="请输入用户名"
            style="height: 38px;width: 400px;padding-left: 10px;border-width: 1px;border-style: solid;border-radius:5px;">
</p>
<br/>
<p class="layui-input-block" style="width: 600px">
    <font color="white"/>密&nbsp;&nbsp;&nbsp; 码：
    <input id="password" type="password" lay-verify="" required placeholder="请输入密码"
           style="height: 38px;width: 400px;padding-left: 10px;border-width: 1px;border-style: solid;border-radius:5px;">
</p>
<br/>
<br/>
<p style="width: 400px; padding-left: 300px">
    <label style="width: 260px"></label>
    <button id="login" class="layui-btn layui-btn-normal ">登录</button>
</p>
</div>
</body>
<style type="text/css">
    .center-in-center{
        width: 400px;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-90%, -50%);
    }
</style>
<script type="text/javascript">
    $(function() {
        var sinaSuggest = new InputSuggest({
            opacity: 1,
            input: document.getElementById('email'),
            data: ['adtec.com.cn']
        });
    })
    $(document).on("click","#login",function () {
        login();
            }
        )

    //回车键触发登录
    function keyOnClick(e){
        //考虑浏览器兼容性
        var theEvent = window.event || e;
        var code = theEvent.keyCode || theEvent.which;
        var email = $("#email").val();
        var password = $("#password").val();
        if (code==13 && email!='' && password!='') {  //回车键的键值为13
            login();  //调用登录方法
        }
    }

    //用户登录
    function login() {
        var email = $("#email").val();
        var password = $("#password").val();
        if (email) {
            $.ajax({
                url: "${APP_PATH}/userLogin",
                data: {"email": email, "password": password},
                type: "POST",
                dataType:"json",
                success: function (result) {
                    if (result.code == 100) {
                        window.location.href="/index.jsp";
                    } else {
                        $("#error").remove();
                        $("#password").after("<p id='error' style='color: red;margin-left:30%;margin-top: 5px'>用户名或密码错误</p>");
                    }
                }
            });
        }
    }

</script>
</html>
