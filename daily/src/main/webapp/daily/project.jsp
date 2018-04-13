<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>项目列表</title>
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
<!-- 员工修改的模态框 -->
<div class="modal fade" id="proUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">项目修改</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">项目编号</label>
                        <div class="col-sm-10">
                            <input type="text" name="projectCode" class="form-control" id="projectCode_update_input">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">项目名称</label>
                        <div class="col-sm-10">
                            <input type="text" name="projectName" class="form-control" id="projectName_update_input">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">项目经理</label>
                        <div class="col-sm-10">
                            <input type="text"  class="form-control"
                                   id="projectUserId_update_input" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">上班时间</label>
                        <div class="col-sm-10">
                            <input type="text" name="workStartTime" class="form-control"
                                   id="workStartTimeStr_update_input">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">下班时间</label>
                        <div class="col-sm-10">
                            <input type="text" name="workEndTime" class="form-control" id="workEndTimeStr_update_input">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">加班开始时间</label>
                        <div class="col-sm-10">
                            <input type="text" name="overworkStartTime" class="form-control"
                                   id="overWorkStartTimeStr_update_input">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="pro_update_btn">更新</button>
            </div>
        </div>
    </div>
</div>

<!-- 员工添加的模态框 -->
<div class="modal fade" id="proAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">项目添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">项目编号</label>
                        <div class="col-sm-10">
                            <input type="text" name="projectCode" class="form-control" id="projectCode_add_input">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">项目名称</label>
                        <div class="col-sm-10">
                            <input type="text" name="projectName" class="form-control" id="projectName_add_input">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">项目经理</label>
                        <div class="col-sm-10">
                            <input type="text" name="projectUserId" class="form-control" id="projectUserId_add_input">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">上班时间</label>
                        <div class="col-sm-10">
                            <input type="text" name="workStartTimeStr" class="form-control"
                                   id="workStartTimeStr_add_input">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">下班时间</label>
                        <div class="col-sm-10">
                            <input type="text" name="workEndTimeStr" class="form-control" id="workEndTimeStr_add_input">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">加班开始时间</label>
                        <div class="col-sm-10">
                            <input type="text" name="overworkStartTimeStr" class="form-control"
                                   id="overWorkStartTimeStr_add_input">
                            <span class="help-block"></span>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="pro_save_btn">保存</button>
            </div>
        </div>
    </div>
</div>

<%--导出加班补贴表--%>
<div class="modal fade" id="overWorkAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form class="form-horizontal" id="overWorkExport" method="get" enctype="multipart/form-data" action="${APP_PATH}/overWork/overWorkExport">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myOverWorkAddModal">导出加班补贴表</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">月份</label>
                        <div class="col-sm-4">
                            <input type="text" name="monthStr" class="form-control" placeholder="请选择月份" id="monthStr">
                            <span class="help-block"></span>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="overWork_save_btn">确定</button>
                </div>
            </form>
        </div>
    </div>
</div>

<%--导出项目周报表--%>
<div class="modal fade" id="projectWeekModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form class="form-horizontal" id="projectWeekExport" method="get" enctype="multipart/form-data" action="${APP_PATH}/daily/projectWeeklyExport">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myProjectWeekModal">导出项目周报表</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">日报日期</label>
                        <div class="col-md-2">
                            <input type="text" name="dailyStartDateStr" class="form-control" style="width: 120px" placeholder="日报开始日期"
                                   id="dailyStartDateStr">
                        </div>
                        <div class="col-md-2" style="margin: 9px 0 50px 50px; float: left">
                            <span>-</span>
                        </div>
                        <div class="col-md-2"style="margin-left:-70px;float: left">
                            <input type="text" name="dailyEndDateStr" class="form-control" style="width: 120px" placeholder="日报结束日期"
                                   id="dailyEndDateStr">
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="projectWeek_save_btn">确定</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- 搭建显示页面 -->
<div style="width: 1100px;">
    <!-- 按钮 -->
    <div class="row">
        <div class="col-md-1">
            <button class="btn btn-primary" id="pro_add_modal_btn">新增</button>
        </div>
        <div class="col-md-1">
            <button class="btn btn-warning" id="overWorkExport_btn">导出加班补贴表</button>
        </div>
        <div class="col-md-1" style="margin-left: 80px">
            <button class="btn btn-warning" id="projectWeekExport_btn">导出项目周报表</button>
        </div>
    </div>
    <!-- 显示表格数据 -->
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover" id="pros_table">
                <thead>
                <tr>
                    <th>项目编号</th>
                    <th>项目名称</th>
                    <th>项目经理</th>
                    <th>上班时间</th>
                    <th>下班时间</th>
                    <th>加班开始时间</th>
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
    layer = layui.layer //弹层
    var totalRecord, currentPage;
    //1、页面加载完成以后，直接去发送ajax请求,要到分页数据
    $(function () {
        //去首页
        to_page(1);
    });
    var userId = "${user.userId}";
    //执行一个laydate实例
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        //常规用法
        laydate.render({
            elem: '#workStartTimeStr_add_input',
            type: 'time'
        });
        laydate.render({
            elem: '#workEndTimeStr_add_input',
            type: 'time'
        });
        laydate.render({
            elem: '#overWorkStartTimeStr_add_input',
            type: 'time'
        });
        laydate.render({
            elem: '#workStartTimeStr_update_input',
            type: 'time'
        });
        laydate.render({
            elem: '#workEndTimeStr_update_input',
            type: 'time'
        });
        laydate.render({
            elem: '#overWorkStartTimeStr_update_input',
            type: 'time'
        });
        laydate.render({
            elem: '#monthStr',
            type: 'month'
        });
        laydate.render({
            elem: '#dailyStartDateStr'
        });
        laydate.render({
            elem: '#dailyEndDateStr'
        });
    });
    //分页查询处理
    function to_page(pn) {
        $.ajax({
            url: "${APP_PATH}/project/getProjectByUser",
            data: "pn=" + pn + "&userId=" + userId,
            type: "GET",
            success: function (result) {
                //1、解析并显示项目数据
                build_pros_table(result);
                //2、解析并显示项目信息
                build_page_info(result);
                //3、解析显示分页条数据
                build_page_nav(result);
            }
        });
    }

    function build_pros_table(result) {
        //清空table表格
        $("#pros_table tbody").empty();
        var pros = result.extend.pageInfo.list;
        $.each(pros, function (index, item) {
            var projectCode = $("<td></td>").append(item.projectCode);
            var projectName = $("<td></td>").append(item.projectName);
            var projectUserId = $("<td></td>").append(item.userName);
            var workStartTime = $("<td></td>").append(item.workStartTime == null ? "" : new Date(item.workStartTime).Format("hh:mm:ss"));
            var workEndTime = $("<td></td>").append(item.workEndTime == null ? "" : new Date(item.workEndTime).Format("hh:mm:ss"));
            var overworkStartTime = $("<td></td>").append(item.overworkStartTime == null ? "" : new Date(item.overworkStartTime).Format("hh:mm:ss"));
            var proId = $("<td></td>").append(item.id).hide();

            var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
            //为编辑按钮添加一个自定义的属性，来表示当前项目id
            editBtn.attr("edit-id", item.id);
            var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
            //为删除按钮添加一个自定义的属性来表示当前删除的项目id
            delBtn.attr("del-id", item.id);
            var detailBtn = $("<button></button>").addClass("btn btn-info btn-sm detail_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("项目详情");
            //为详情按钮添加一个自定义的属性来表示当前项目id
            detailBtn.attr("detail-id", item.id);
            var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn).append(" ").append(detailBtn);
            //append方法执行完成以后还是返回原来的元素
            $("<tr></tr>")
                .append(projectCode)
                .append(projectName)
                .append(projectUserId)
                .append(workStartTime)
                .append(workEndTime)
                .append(overworkStartTime)
                .append(proId)
                .append(btnTd)
                .appendTo("#pros_table tbody");
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

    //点击新增按钮弹出模态框。
    $("#pro_add_modal_btn").click(function () {
        //清除表单数据（表单完整重置（表单的数据，表单的样式））
        reset_form("#proAddModal form");
        //s$("")[0].reset();
        //发送ajax请求，查出部门信息，显示在下拉列表中
        //getDepts("#proAddModal select");
        //弹出模态框
        $("#proAddModal").modal({
            backdrop: "static"
        });
    });

    //显示校验结果的提示信息
    function show_validate_msg(ele, status, msg) {
        //清除当前元素的校验状态
        $(ele).parent().removeClass("has-success has-error");
        $(ele).next("span").text("");
        if ("success" == status) {
            $(ele).parent().addClass("has-success");
            $(ele).next("span").text(msg);
        } else if ("error" == status) {
            $(ele).parent().addClass("has-error");
            $(ele).next("span").text(msg);
        }
    }

    //校验用户名是否可用
    $("#projectCode_add_input").change(function () {
        //发送ajax请求校验用户名是否可用
        var projectCode = this.value;
        $.ajax({
            url: "${APP_PATH}/checkProject",
            data: "projectCode=" + projectCode,
            type: "POST",
            success: function (result) {
                if (result.code == 100) {
                    show_validate_msg("#projectCode_add_input", "success", "项目编号可用");
                    $("#pro_save_btn").attr("ajax-va", "success");
                } else {
                    show_validate_msg("#projectCode_add_input", "error", result.extend.va_msg);
                    $("#pro_save_btn").attr("ajax-va", "error");
                }
            }
        });
    });

    //点击保存，保存员工。
    $("#pro_save_btn").click(function () {
        //1、模态框中填写的表单数据提交给服务器进行保存
        //1、判断之前的ajax用户名校验是否成功。如果成功。
        if ($(this).attr("ajax-va") == "error") {
            return false;
        }
        //2、发送ajax请求保存员工
        $.ajax({
            url: "${APP_PATH}/addProject",
            type: "POST",
            data: $("#proAddModal form").serialize(),
            success: function (result) {
                //alert(result.msg);
                if (result.code == 100) {
                    //员工保存成功；
                    //1、关闭模态框
                    $("#proAddModal").modal('hide');
                    //2、来到最后一页，显示刚才保存的数据
                    //发送ajax请求显示最后一页数据即可
                    to_page(totalRecord);
                }
            }
        });
    });

    //1）、我们是按钮创建之前就绑定了click，所以绑定不上。
    //2）、可以在创建按钮的时候绑定。
    //3）、绑定点击.live()
    //jquery新版没有live，使用on进行替代
    $(document).on("click", ".edit_btn", function () {
        //2、查出项目信息，显示项目信息
        getProject($(this).attr("edit-id"));
        //3、把项目的id传递给模态框的更新按钮
        $("#pro_update_btn").attr("edit-id", $(this).attr("edit-id"));
        $("#proUpdateModal").modal({
            backdrop: "static"
        });
    });

    //获取项目信息
    function getProject(id) {
        $.ajax({
            url: "${APP_PATH}/pro/" + id,
            type: "GET",
            success: function (result) {
                var proData = result.extend.id;
                var proId = proData.id;
                $("#projectCode_update_input").val(proData.projectCode)
                $("#projectName_update_input").val(proData.projectName);
                $("#projectUserId_update_input").val(proData.userName);
                $("#workStartTimeStr_update_input").val(proData.workStartTime == null ? "" : new Date(proData.workStartTime).Format("hh:mm:ss"));
                $("#workEndTimeStr_update_input").val(proData.workEndTime == null ? "" : new Date(proData.workEndTime).Format("hh:mm:ss"));
                $("#overWorkStartTimeStr_update_input").val(proData.overworkStartTime == null ? "" : new Date(proData.overworkStartTime).Format("hh:mm:ss"));
            }
        });
    }

    //点击更新，更新员工信息
    $("#pro_update_btn").click(function () {
        //2、发送ajax请求保存更新的员工数据
        $.ajax({
            url: "${APP_PATH}/pro/" + $(this).attr("edit-id"),
            type: "PUT",
            data: $("#proUpdateModal form").serialize(),
            success: function (result) {
                //alert(result.msg);
                //1、关闭对话框
                $("#proUpdateModal").modal("hide");
                //2、回到本页面
                to_page(currentPage);
            }
        });
    });

    //单个删除
    $(document).on("click", ".delete_btn", function () {
        //1、弹出是否确认删除对话框
        var projectName = $(this).parents("tr").find("td:eq(1)").text();
        var proId = $(this).attr("del-id");
        layer.confirm('确定删除【' + projectName + '】吗？', {icon: 3, title: '确认信息'}, function (index) {
            //确认，发送ajax请求删除即可
            $.ajax({
                url: "${APP_PATH}/pro/" + proId,
                type: "DELETE",
                success: function (result) {
                    layer.msg(result.msg);
                    //回到本页
                    to_page(currentPage);
                }
            });
        });
    });

    //完成全选/全不选功能
    $("#check_all").click(function () {
        //attr获取checked是undefined;
        //我们这些dom原生的属性；attr获取自定义属性的值；
        //prop修改和读取dom原生属性的值
        $(".check_item").prop("checked", $(this).prop("checked"));
    });

    //check_item
    $(document).on("click", ".check_item", function () {
        //判断当前选择中的元素是否5个
        var flag = $(".check_item:checked").length == $(".check_item").length;
        $("#check_all").prop("checked", flag);
    });
    //加班补贴表
    $("#overWorkExport_btn").click(function(){
        reset_form("#overWorkAddModal form");
        $("#overWorkAddModal").modal({
            backdrop:"static"
        });
    });
    //点击保存，导出加班补贴表
    $("#overWork_save_btn").click(function () {
        if($("#monthStr").val()==""){
            layer.alert('请选择月份！');
            return false;
        }
        $('#overWorkExport').submit();
        reset_form("#overWorkAddModal form");
        $("#overWorkAddModal").modal('hide');
    });
    //项目周报表
    $("#projectWeekExport_btn").click(function(){
        reset_form("#projectWeekModal form");
        $("#projectWeekModal").modal({
            backdrop:"static"
        });
    });
    //点击保存，导出加班补贴表
    $("#projectWeek_save_btn").click(function () {
        if ($("#dailyStartDateStr").val() == "" || $("#dailyEndDateStr").val() == "") {
            layer.alert('请选择日报日期！');
            return false;
        }
        $('#projectWeekExport').submit();
        reset_form("#projectWeekModal form");
        $("#projectWeekModal").modal('hide');
    });

    //点击详情，跳转到项目详情页面
    $(document).on("click", ".detail_btn", function () {
        var id = this.getAttribute("detail-id");
        window.location.href = "/daily/projectDetail.jsp?projectId=" + id;

    });
</script>
</body>
</html>