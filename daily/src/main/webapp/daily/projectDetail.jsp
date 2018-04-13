<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>项目成员列表</title>
    <%
        pageContext.setAttribute("APP_PATH", request.getContextPath());
    %>
    <!-- web路径：
    不以/开始的相对路径，找资源，以当前资源的路径为基准，经常容易出问题。
    以/开始的相对路径，找资源，以服务器的路径为标准(http://localhost:3306)；需要加上项目名
            http://localhost:3306/crud
     -->
    <script type="text/javascript"
            src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>
    <link
            href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
            rel="stylesheet">
    <script
            src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

    <script src="${APP_PATH}/static/js/common.js"></script>
    <script src="${APP_PATH}/static/layui/layui.all.js"></script>
</head>
<body>
<!-- 搭建显示页面 -->
<div style="width: 1100px;">
    <!-- 标题 -->
    <div class="row">
        <div class="col-md-12">
            <p>项目编号：<span id="pro_code"></span>&nbsp;&nbsp;&nbsp;&nbsp;项目名称：<span id="pro_name"></span></p>
        </div>
    </div>
    <!-- 按钮 -->
    <div class="row">
        <div class="col-md-4">
            <button class="btn btn-primary" onclick="history.go(-1)">返回</button>
            <button class="btn btn-primary" id="proUser_add_btn" >新增</button>
        </div>
    </div>
    <!-- 显示表格数据 -->
    <div class="row">
        <div class="col-md-10">
            <table class="table table-hover" id="users_table">
                <thead>
                <tr>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>邮箱</th>
                    <th>角色</th>
                    <th>手机号</th>
                    <th>密码</th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
    </div>

    <!-- 显示分页信息 -->
    <div class="row">
        <!--分页文字信息  -->
        <div class="col-md-6" id="page_info_area"></div>
        <!-- 分页条信息 -->
        <div class="col-md-6" id="page_nav_area">

        </div>
    </div>

</div>
<script type="text/javascript">
    var projectId = (window.location.search).split("=")[1];
    $("#proUser_add_btn").attr("project-id", projectId);
    console.info(projectId);
    layer = layui.layer //弹层
    var totalRecord, currentPage;
    //1、页面加载完成以后，直接去发送ajax请求,要到分页数据
    $(function () {
        //去首页
        to_page(1);
    });

    function to_page(pn) {
        params = "projectId=" + projectId + "&pn=" + pn;
        $.ajax({
            url: "${APP_PATH}/project/getProjectUserByProjectId",
            data: params,
            type: "POST",
            success: function (result) {
                $("#pro_code").html(result.extend.pageInfo.list[0].projectCode);
                $("#pro_name").html(result.extend.pageInfo.list[0].projectName);
                //1、解析并显示项目成员数据
                build_users_table(result);
                //2、解析并显示项目信息
                build_page_info(result);
                //3、解析显示分页条数据
                build_page_nav(result);
            }
        });
    }

    function build_users_table(result){
        //清空table表格
        $("#users_table tbody").empty();
        var users = result.extend.pageInfo.list;
        $.each(users,function(index,item){
            var userName = $("<td></td>").append(item.userName);
            var gender = $("<td></td>").append(item.gender=='1'?"男":"女");
            var password = $("<td></td>").append(item.password);
            var email = $("<td></td>").append(item.email);
            var mobile = $("<td></td>").append(item.mobile);
            var roleName = $("<td></td>").append(item.roleName);
            var userId = $("<td></td>").append(item.userId).hide();

            var delBtn =  $("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
            //为删除按钮添加一个自定义的属性来表示当前删除的用户id
            delBtn.attr("user-id",item.userId);
            //添加属性权限ID
            // delBtn.attr("privilege-id",4);
            //根据权限判断是否显示
            // if(delBtn.attr("privilege-id") == '4'){
            //     delBtn.hide();
            // }
            //根据权限列表判断是否显示
            // if(privilegeList.indexOf(4)== -1){
            //     delBtn.hide();
            // }
            var btnTd = $("<td></td>").append(" ").append(delBtn);
            //var delBtn =
            //append方法执行完成以后还是返回原来的元素
            $("<tr></tr>")
                .append(userName)
                .append(gender)
                .append(email)
                .append(roleName)
                .append(mobile)
                .append(password)
                .append(userId)
                .append(btnTd)
                .appendTo("#users_table tbody");
        });
    }

    //解析显示分页信息
    function build_page_info(result) {
        $("#page_info_area").empty();
        $("#page_info_area").append("当前" + result.extend.pageInfo.pageNum + "页,总" +
            result.extend.pageInfo.pages + "页,总" +
            result.extend.pageInfo.total + "条记录");
        totalRecord = result.extend.pageInfo.total;
        currentPage = result.extend.pageInfo.pageNum;
    }

    //解析显示分页条，点击分页要能去下一页....
    function build_page_nav(result) {
        //page_nav_area
        $("#page_nav_area").empty();
        var ul = $("<ul></ul>").addClass("pagination");

        //构建元素
        var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href", "#"));
        var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
        if (result.extend.pageInfo.hasPreviousPage == false) {
            firstPageLi.addClass("disabled");
            prePageLi.addClass("disabled");
        } else {
            //为元素添加点击翻页的事件
            firstPageLi.click(function () {
                to_page(1);
            });
            prePageLi.click(function () {
                to_page(result.extend.pageInfo.pageNum - 1);
            });
        }

        var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
        var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href", "#"));
        if (result.extend.pageInfo.hasNextPage == false) {
            nextPageLi.addClass("disabled");
            lastPageLi.addClass("disabled");
        } else {
            nextPageLi.click(function () {
                to_page(result.extend.pageInfo.pageNum + 1);
            });
            lastPageLi.click(function () {
                to_page(result.extend.pageInfo.pages);
            });
        }

        //添加首页和前一页 的提示
        ul.append(firstPageLi).append(prePageLi);
        //1,2，3遍历给ul中添加页码提示
        $.each(result.extend.pageInfo.navigatepageNums, function (index, item) {
            var numLi = $("<li></li>").append($("<a></a>").append(item));
            if (result.extend.pageInfo.pageNum == item) {
                numLi.addClass("active");
            }
            numLi.click(function () {
                to_page(item);
            });
            ul.append(numLi);
        });
        //添加下一页和末页 的提示
        ul.append(nextPageLi).append(lastPageLi);

        //把ul加入到nav
        var navEle = $("<nav></nav>").append(ul);
        navEle.appendTo("#page_nav_area");
    }

    //清空表单样式及内容
    function reset_form(ele) {
        $(ele)[0].reset();
        //清空表单样式
        $(ele).find("*").removeClass("has-error has-success");
        $(ele).find(".help-block").text("");
    }

    //单个删除
    $(document).on("click", ".delete_btn", function () {
        //1、弹出是否确认删除对话框
        var userName = $(this).parents("tr").find("td:eq(0)").text();
        var userId = $(this).attr("user-id");
        layer.confirm('确定删除【' + userName + '】吗？', {icon: 3, title: '确认信息'}, function (index) {
            //确认，发送ajax请求删除即可
            $.ajax({
                url: "${APP_PATH}/projectUser/" + userId,
                type: "DELETE",
                success: function (result) {
                    layer.msg(result.msg);
                    //回到本页
                    to_page(currentPage);
                }
            });
        });
    });

    //点击新增，跳转到项目成员新增页面
    $(document).on("click", "#proUser_add_btn", function () {
        var id = this.getAttribute("project-id");
        window.location.href = "/daily/projectUserAdd.jsp?projectId=" + id;

    });
</script>
</body>
</html>