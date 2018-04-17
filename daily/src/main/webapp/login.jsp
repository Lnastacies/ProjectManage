<%--
  Created by IntelliJ IDEA.
  User: 11111
  Date: 2018/3/21
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html >
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>日报管理系统登录</title>
    <jsp:include page="/html/default/pub.jsp" />
    <script type="text/javascript" src="/js/common/inputSuggest.js" charset="utf-8"></script>
</head>
<body style="background: url(/static/image/bg.webp) no-repeat 57% 5%;" onkeyup="keyOnClick(event)">
<div class="center-in-center" style="margin-top:-17%;margin-left:-5%">
    <p class="layui-input-block"  style="width: 400px;margin: 604px 5px 10px 250px">
        <font color="white"/>
        <input id="email" type="text" lay-verify="" required placeholder="请输入邮箱"
                style="height: 40px;width: 280px;padding-left: 10px;border-width: 1px;border-style: solid;border-radius:5px;color: #0C0C0C;border-color:#777777">
    </p>
    <br/>
    <p class="layui-input-block" style="width: 400px;margin: -6px 10px 10px 250px">
        <font color="white"/>
        <input id="password" type="password" lay-verify="" required placeholder="请输入密码"
               style="height: 38px;width: 280px;padding-left: 10px;border-width: 1px;border-style: solid;border-radius:5px;color: #0C0C0C;border-color:#777777">
    </p>
    <br/>
    <br/>
    <p style="width: 400px; margin: -48px 12px 18px 250px">
        <label style="width: 260px"></label>
        <button id="login" class="layui-btn layui-btn-normal " style="font-size: 18px;width: 280px;background-color: #262626">登录</button>
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
    });
    $(document).on("click","#login",function () {
        login();
            }
        );

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
    };

    //用户登录
    function login() {
        var email = $("#email").val();
        var password = $("#password").val();
        if (email) {
            $.ajax({
                url: "/user/userLogin",
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
    };

</script>
</html>
