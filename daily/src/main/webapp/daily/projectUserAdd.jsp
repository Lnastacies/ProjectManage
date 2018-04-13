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

    </div>
    <!-- 按钮 -->
    <div class="row" onkeyup="keyOnClick()">
        <div class="col-md-10">
            <button class="btn btn-primary" onclick="history.go(-1)">返回</button>
            &nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="user-name">
            <button class="btn btn-warning" id="user_search_btn" >搜索</button>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <button class="btn btn-primary" id="user_save_btn" >保存</button>
        </div>
    </div>
    <!-- 显示表格数据 -->
    <div class="row">
        <div class="col-md-10">
            <table class="table table-hover" id="users_table">
                <thead>
                <tr>
                    <th>选择</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>邮箱</th>
                    <th>手机号</th>
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
    layer = layui.layer //弹层
    var totalRecord, currentPage;

    //点击搜索，查询用户列表
    $(document).on("click", "#user_search_btn", function () {
        var userName = $("#user-name").val();
        to_page(1,userName);
    });

    function to_page(pn,userName) {
        params = "userName=" + userName + "&pn=" + pn;
        $.ajax({
            url: "${APP_PATH}/user/getUserInfoByUserName",
            data: params,
            type: "POST",
            success: function (result) {
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
            var checkBoxTd = $("<td><input type='checkbox' class='check_item'/></td>");
            var userName = $("<td></td>").append(item.userName);
            var gender = $("<td></td>").append(item.gender=='1'?"男":"女");
            var email = $("<td></td>").append(item.email);
            var mobile = $("<td></td>").append(item.mobile);
            var userId = $("<td></td>").append(item.userId).hide();

            //var delBtn =
            //append方法执行完成以后还是返回原来的元素
            $("<tr></tr>")
                .append(checkBoxTd)
                .append(userName)
                .append(gender)
                .append(email)
                .append(mobile)
                .append(userId)
                .appendTo("#users_table tbody");
        });
    }

    //解析显示分页信息
    function build_page_info(result){
        $("#page_info_area").empty();
        $("#page_info_area").append("当前"+result.extend.pageInfo.pageNum+"页,总"+
            result.extend.pageInfo.pages+"页,总"+
            result.extend.pageInfo.total+"条记录");
        totalRecord = result.extend.pageInfo.total;
        currentPage = result.extend.pageInfo.pageNum;
    }
    //解析显示分页条，点击分页要能去下一页....
    function build_page_nav(result){
        //page_nav_area
        $("#page_nav_area").empty();
        var ul = $("<ul></ul>").addClass("pagination");

        //构建元素
        var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
        var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
        if(result.extend.pageInfo.hasPreviousPage == false){
            firstPageLi.addClass("disabled");
            prePageLi.addClass("disabled");
        }else{
            //为元素添加点击翻页的事件
            firstPageLi.click(function(){
                console.info(result.extend.pageInfo.list[0].userName);
                to_page(1,result.extend.pageInfo.list[0].userName);
            });
            prePageLi.click(function(){
                console.info(result.extend.pageInfo.list[0].userName);
                to_page(result.extend.pageInfo.pageNum -1,result.extend.pageInfo.list[0].userName);
            });
        }



        var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
        var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href","#"));
        if(result.extend.pageInfo.hasNextPage == false){
            nextPageLi.addClass("disabled");
            lastPageLi.addClass("disabled");
        }else{
            nextPageLi.click(function(){
                to_page(result.extend.pageInfo.pageNum +1,result.extend.pageInfo.list[0].userName);
            });
            lastPageLi.click(function(){
                to_page(result.extend.pageInfo.pages,result.extend.pageInfo.list[0].userName);
            });
        }



        //添加首页和前一页 的提示
        ul.append(firstPageLi).append(prePageLi);
        //1,2，3遍历给ul中添加页码提示
        $.each(result.extend.pageInfo.navigatepageNums,function(index,item){

            var numLi = $("<li></li>").append($("<a></a>").append(item));
            if(result.extend.pageInfo.pageNum == item){
                numLi.addClass("active");
            }
            numLi.click(function(){
                to_page(item,result.extend.pageInfo.list[0].userName);
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

    //保存
    $(document).on("click", "#user_save_btn", function () {
        //1、弹出是否确认保存对话框
        var userNames = "";
        var save_idstr = "";
        $.each($(".check_item:checked"),function(){
            //this
            userNames += $(this).parents("tr").find("td:eq(1)").text()+",";
            //组装员工id字符串
            save_idstr += $(this).parents("tr").find("td:eq(5)").text()+"-";
        });
        //去除userNames多余的,
        userNames = userNames.substring(0, userNames.length-1);
        //去除save_idstr多余的-
        save_idstr = save_idstr.substring(0, save_idstr.length-1);
        if(userNames.length > 0){
        layer.confirm('确定添加吗？', {icon: 3, title: '确认信息'}, function (index) {
            //确认，发送ajax请求删除即可
            $.ajax({
                url: "${APP_PATH}/project/projectUserSave",
                type: "GET",
                data:"ids="+save_idstr+"&projectId="+projectId,
                success: function (result) {
                    if(result.length > 0){
                        layer.msg(result[0].userName+" 邮箱:"+result[0].email+"已经在项目中！");
                    }else{
                        //回到项目详情页
                        window.history.go(-1);
                    }

                }
            });
        });
        }else{
            layer.msg("请选择用户！")
        }
    });

    //回车键触发搜索
    function keyOnClick(e){
        //考虑浏览器兼容性
        var theEvent = window.event || e;
        var code = theEvent.keyCode || theEvent.which;
        if (code==13) {  //回车键的键值为13
            var userName = $("#user-name").val();
            to_page(1,userName);  //调用搜索方法
        }
    }

</script>
</body>
</html>