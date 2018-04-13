<%--
  Created by IntelliJ IDEA.
  User: 11111
  Date: 2018/3/26
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>日报管理系统</title>
    <script type="text/javascript"
            src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>
    <link rel="stylesheet" href="static/layui/css/layui.css">
    <script type="text/javascript"
            src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">EAI-日报管理系统</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-right" lay-filter="menu">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    ${user.userName}
                </a>
                <dl class="layui-nav-child">
                    <dd id="S00106" name="daily/loginUser.jsp"><a href="javascript:void(0);">基本资料</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item" id="exit"><a href="">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree " lay-filter="menu">
                <!--<li class="layui-nav-item layui-nav-itemed">-->
                <li id="S000" name="daily/home.jsp" class="layui-nav-item">
                    <a  class="" href="javascript:;" >首页</a>
                </li>
                <li class="layui-nav-item" id="userMana">
                    <a id="S001" class="" href="javascript:;" >用户管理</a>
                    <dl class="layui-nav-child">
                        <dd id="S00101" name="daily/user.jsp" ><a href="javascript:;">用户信息管理维护</a></dd>
                        <dd id="S00102" name="daily/role.jsp" ><a href="javascript:;">角色信息管理维护</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item " id="projectMana">
                    <a id="S002" href="javascript:;" >项目管理</a>
                    <dl class="layui-nav-child">
                        <dd id="S00201" name="daily/project.jsp" ><a href="javascript:;">项目信息管理维护</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item" id="dailyMana">
                    <a id="S003" href="javascript:;" >日报管理</a>
                    <d1 class="layui-nav-child">
                        <dd id="S00301" name="daily/daily.jsp" ><a href="javascript:;">日报信息管理维护</a>
                        </dd>
                    </d1>
                </li>

                <li id="S999" name="daily/aboutWe.jsp" class="layui-nav-item">
                    <a  class="" href="javascript:;" style="width:160px;height:45px;position:fixed;top:93%;overflow:hidden;">关于我们</a>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body" style="bottom: 0px;">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <div class="layui-tab" lay-allowClose="true" lay-filter="tab-switch">
                <ul class="layui-tab-title">
                </ul>
                <div class="layui-tab-content">
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!--<div class="layui-footer">-->
    <!--&lt;!&ndash; 底部固定区域 &ndash;&gt;-->
    <!--© layui.com - 底部固定区域-->
    <!--</div>-->

</div>

<script src="static/layui/layui.all.js"></script>
<script type="text/javascript">
    var element;
    var $;
    //获取当前用户的权限列表
    var privilegeList = ${user.privilegeList};
    privilegeJudge(privilegeList);

    layui.use(['element', 'jquery'], function () {
        element = layui.element;
        $ = layui.jquery;

         /*
        也可以通过class属性达到隐藏元素的目的
        class="layui-hide"

         */

        //进来之后默认跳转到首页
        element.tabAdd('tab-switch', {
            title: "首页"
            ,
            content: '<iframe src=' + "daily/home.jsp" + ' width="100%" style="min-height: 500px;" frameborder="0" scrolling="auto" onload="setIframeHeight(this)"></iframe>' // 选项卡内容，支持传入html
            ,
            id: "S000" //选项卡标题的lay-id属性值
        });
        element.tabChange('tab-switch', "S000"); //切换到新增的tab上

        //监听左侧菜单点击
        element.on('nav(menu)', function (elem) {
            if(elem[0].id!="exit") {
                addTab(elem[0].innerText, elem[0].attributes[1].nodeValue, elem[0].id);
            }
        });
        //监听tab选项卡切换
        element.on('tab(tab-switch)', function (data) {
            if (data.elem.context.attributes != undefined) {
                var id = data.elem.context.attributes[0].nodeValue;
                $("#S999").removeClass( "layui-this");
                $("#S000").removeClass( "layui-this");
                layui.each($(".layui-nav-child"), function () {
                    $(this).find("dd").removeClass("layui-this");
                });
                $("#" + id).addClass( "layui-this");
            }
        });
        //注销返回登录界面
        $(document).on("click","#exit",function () {
                $.ajax({
                    url: "${APP_PATH}/userExit",
                    type: "POST",
                    dataType:"json",
                    success: function (result) {
                        if (result.code == 100) {
                            window.location.href="/login.jsp";
                        } else {
                            alert("注销失败");
                            window.location.href="/login.jsp";
                        }
                    }
                });
        })
    });

    //根据每个菜单和按钮的privilege-id来进行显示和隐藏，控制每个角色的权限
    function privilegeJudge(privilegeList){
        if(privilegeList.indexOf(24) == -1){
            $("#userMana").hide();
        };
        if(privilegeList.indexOf(25) == -1){
            $("#projectMana").hide();
        }
        if(privilegeList.indexOf(26) == -1){
            $("#dailyMana").hide();
        }
    }

    /**
     * 新增tab选项卡，如果已经存在则打开已经存在的，不存在则新增
     * @param tabTitle 选项卡标题名称
     * @param tabUrl 选项卡链接的页面URL
     * @param tabId 选项卡id
     */
    function addTab(tabTitle, tabUrl, tabId) {
        if ($(".layui-tab-title li[lay-id=" + tabId + "]").length > 0) {
            element.tabChange('tab-switch', tabId);
        } else {
            element.tabAdd('tab-switch', {
                title: tabTitle
                ,
                content: '<iframe src=' + tabUrl + ' width="100%" style="min-height: 500px;" frameborder="0" scrolling="auto" onload="setIframeHeight(this)"></iframe>' // 选项卡内容，支持传入html
                ,
                id: tabId //选项卡标题的lay-id属性值
            });
            element.tabChange('tab-switch', tabId); //切换到新增的tab上
        }
    }

    /**
     * ifrme自适应页面高度，需要设定min-height
     * @param iframe
     */
    function setIframeHeight(iframe) {
        if (iframe) {
            var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
            if (iframeWin.document.body) {
                iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
            }
        }
    };
</script>
</body>
</html>
