<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>日报列表</title>
    <%
        pageContext.setAttribute("APP_PATH", request.getContextPath());
    %>
    <!-- web路径：
    不以/开始的相对路径，找资源，以当前资源的路径为基准，经常容易出问题。
    以/开始的相对路径，找资源，以服务器的路径为标准(http://localhost:3306)；需要加上项目名
            http://localhost:3306/crud
     -->
    <script type="text/javascript" src="${APP_PATH}/static/js/jquery-1.12.4.min.js"></script>
    <link href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="${APP_PATH}/static/js/common.js"></script>
    <script src="${APP_PATH}/static/layui/layui.all.js"></script>
</head>
<body>
<!-- 搭建显示页面 -->
<div style="width: 1000px;">
    <!-- 日报添加的模态框 -->
    <div class="modal fade" id="proAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content" style="z-index: 19896; width: 800px; height: 395px; top: 10px; left: -107px;">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">日报添加</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">工作类型</label>
                            <div class="col-sm-5">
                                <select name="workType" class="form-control">
                                    <option value="">请选择工作类型</option>
                                    <option value="01" selected="">需求分析</option>
                                    <option value="02">设计</option>
                                    <option value="03">开发</option>
                                    <option value="04">测试</option>
                                    <option value="05">版本发布</option>
                                    <option value="06">运维支持</option>
                                    <option value="07">会议</option>
                                    <option value="08">其他</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">日报内容</label>
                            <div class="col-sm-10">
                                <input type="text" name="dailyContent" class="form-control" id="dailyContent_add_input">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">完成百分比</label>
                            <div class="col-sm-2">
                                <input type="text" name="dailyPercent" class="form-control" id="dailyPercent_add_input" min="0" max="100" maxlength="3">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">计划完成时间</label>
                            <div class="col-sm-2">
                                <input type="text" name="doneDateStr" class="form-control" placeholder="yyyy-MM-dd" id="doneDate">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">工作存在问题</label>
                            <div class="col-sm-10">
                                <input type="text" name="problem" class="form-control" id="problem_add_input">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">下周工作计划</label>
                            <div class="col-sm-10">
                                <input type="text" name="nextWorkPlan" class="form-control" id="nextWorkPlan_add_input">
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="pro_save_btn">保存</button>
                    <button type="button" class="btn btn-default" id="test_btn">测试</button>
                </div>
            </div>
        </div>
    </div>

    <!-- 按钮 -->
    <div class="row">
        <div class="col-md-12">
            <button class="btn btn-primary" onclick="history.go(-1)">返回</button>
            <button class="btn btn-primary" id="pro_add_modal_btn">新增</button>
        </div>
    </div>

    <!-- 显示表格数据 -->
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover" id="pros_table">
                <thead>
                <tr>
                    <th>工作类型</th>
                    <th>日报内容</th>
                    <th>完成百分比</th>
                    <th>计划完成时间</th>
                    <th>工作存在问题</th>
                    <th>下周工作计划</th>
                    <th>创建日期</th>
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
    var dailyId = (window.location.search).split("=")[1];
    layer = layui.layer //弹层
    var totalRecord, currentPage;
    //1、页面加载完成以后，直接去发送ajax请求,要到分页数据
    $(function () {
        //去首页
        to_page(1);
    });

    function to_page(pn) {
        params = "dailyId=" + dailyId + "&pn=" + pn;
        $.ajax({
            url: "${APP_PATH}/dailyDetail/getDailyDetailList",
            data: params,
            type: "POST",
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
            var workType = $("<td></td>").append(item.workType);
            var dailyContent = $("<td></td>").append(item.dailyContent);
            var dailyPercent = $("<td></td>").append(item.dailyPercent);
            var doneDate = $("<td></td>").append(item.doneDate == null ? "" : new Date(item.doneDate).Format("yyyy-MM-dd"));
            var problem = $("<td></td>").append(item.problem);
            var nextWorkPlan = $("<td></td>").append(item.nextWorkPlan);
            var createTime = $("<td></td>").append(item.createTime == null ? "" : new Date(item.createTime).Format("yyyy-MM-dd"));

            var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
            //为编辑按钮添加一个自定义的属性，来表示当前项目dailyId
            editBtn.attr("edit-id", item.dailyId);
            var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
            //为删除按钮添加一个自定义的属性来表示当前删除的项目dailyId
            delBtn.attr("del-id", item.dailyId);
            var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
            //append方法执行完成以后还是返回原来的元素
            $("<tr class='layui-colla-item'></tr>")
                .append(workType)
                .append(dailyContent)
                .append(dailyPercent)
                .append(doneDate)
                .append(problem)
                .append(nextWorkPlan)
                .append(createTime)
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
    $("#pro_add_modal_btn").click(function(){
        //清除表单数据（表单完整重置（表单的数据，表单的样式））
        reset_form("#proAddModal form");
        //s$("")[0].reset();
        //发送ajax请求，查出部门信息，显示在下拉列表中
        //getDepts("#proAddModal select");
        //弹出模态框
        $("#proAddModal").modal({
            backdrop:"static"
        });
    });

    layui.use('laydate', function(){
        var laydate = layui.laydate;
        //常规用法
        laydate.render({
            elem: '#doneDate'
        });
    });

    //点击添加保存
    $("#pro_save_btn").click(function () {
        var params = $("#proAddModal form").serialize() + "&dailyId=" + dailyId
        $.ajax({
            url:"${APP_PATH}/dailyDetail/saveDailyDateil",
            type:"POST",
            data:params,
            success:function(result){
                if(result.code == 100){
                    $("#proAddModal").modal('hide');
                    to_page(totalRecord);
                }else{

                }
            }
        });
    });

    //点击按钮测试
    $("#test_btn").click(function () {
        alert($("#proAddModal form").serialize());
    });

    //单个删除
    $(document).on("click", ".delete_btn", function () {
        //1、弹出是否确认删除对话框
        var dailyContent = $(this).parents("tr").find("td:eq(2)").text();
        var proId = $(this).attr("del-id");
        layer.confirm('确定删除【' + dailyContent + '】吗？', {icon: 3, title: '确认信息'}, function (index) {
            //确认，发送ajax请求删除即可
            $.ajax({
                url: "${APP_PATH}/dailyDetail/deleteDailyDetail" + proId,
                type: "DELETE",
                success: function (result) {
                    layer.msg(result.msg);
                    //回到本页
                    to_page(currentPage);
                }
            });
        });
    });

</script>
</body>
</html>