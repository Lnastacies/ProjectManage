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
<!-- 日报添加的模态框 -->
<div class="modal fade" id="proAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">日报添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">日报日期</label>
                        <div class="col-sm-4">
                            <input type="text" name="dailyDateStr" class="form-control" placeholder="请选择日期" id="dailyDateStr">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">项目组</label>
                        <div class="col-sm-4">
                            <!-- 部门提交部门id即可 -->
                            <select class="form-control" name="projectName">
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="daily_save_btn">保存</button>
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
<!-- 搭建显示页面 -->
<div style="width: 1100px;">
    <!-- 按钮 -->
    <div class="row">
        <div class="form-group">
                <div class="col-md-1">
                    <button class="btn btn-primary" id="add_daily_btn">新增</button>
                </div>
                <div class="col-md-1">
                    <button class="btn btn-primary" id="overWorkExport_btn">导出加班补贴表</button>
                </div>
            <form id="weeklyExport" method="get" enctype="multipart/form-data" action="${APP_PATH}/daily/weeklyExport">
                <div class="col-md-1" style="width: 130px; margin: 7px -42px 21px 58px">
                    <label class="control-label">日报日期：</label>
                </div>
                <div class="col-md-2">
                    <input type="text" name="dailyStartDateStr" class="form-control" placeholder="日报开始日期" id="dailyStartDateStr">
                </div>
                <div class="col-md-2" style="margin: 9px -166px -28px -12px">
                    <span>-</span>
                </div>
                <div class="col-md-2">
                    <input type="text" name="dailyEndDateStr" class="form-control" placeholder="日报结束日期" id="dailyEndDateStr">
                </div>
                <div class="col-md-1">
                    <button class="btn btn-warning" id="weeklyExport_btn">导出周报</button>
                </div>
            </form>
        </div>
    </div>
    <!-- 显示表格数据 -->
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover" id="pros_table">
                <thead>
                <tr>
                    <th>日报日期</th>
                    <th>星期</th>
                    <th>项目组</th>
                    <th>创建日期</th>
                    <th>下班时间</th>
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
    layui.use('laydate', function(){
        var laydate = layui.laydate;
        //常规用法
        laydate.render({
            elem: '#dailyDateStr'
        });
        laydate.render({
            elem: '#dailyStartDateStr'
        });
        laydate.render({
            elem: '#dailyEndDateStr'
        });
        laydate.render({
            elem: '#monthStr',
            type: 'month'
        });
    });
    layer = layui.layer //弹层
    var userId = "${user.userId}";
    var totalRecord, currentPage;

    //1、页面加载完成以后，直接去发送ajax请求,要到分页数据
    $(function () {
        //去首页
        to_page(1);
    });

    function to_page(pn) {
        $.ajax({
            url: "${APP_PATH}/getDailyList",
            data: {"userId":userId, "pn":pn},
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
            var dailyDate = $("<td></td>").append(item.dailyDate == null ? "" : new Date(item.dailyDate).Format("yyyy-MM-dd"));
            var week = $("<td></td>").append(item.week);
            var projectName = $("<td></td>").append(item.projectName);
            var createTime = $("<td></td>").append(item.createTime == null ? "" : new Date(item.createTime).Format("yyyy-MM-dd"));
            var offDutyTime = $("<td></td>").append(item.offDutyTime == null ? "" : new Date(item.offDutyTime).Format("hh:mm:ss"));
            var detailBtn = $("<button onclick=\"goDailyDetail('"+item.dailyId+"')\"></button>").addClass("btn btn-primary btn-sm edit_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("详情列表");
            //为详情按钮添加一个自定义的属性，来表示当前项目dailyId
            detailBtn.attr("detail-id", item.dailyId);

            var btnTd = $("<td></td>").append(detailBtn);
            //append方法执行完成以后还是返回原来的元素
            $("<tr class='layui-colla-item'></tr>")
                .append(dailyDate)
                .append(week)
                .append(projectName)
                .append(createTime)
                .append(createTime)
                .append(offDutyTime)
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

    //点击详情跳转到日报详情页面
    function goDailyDetail(dailyId) {
        window.location.href="/daily/dailyAdd.jsp?dailyId=" + dailyId;
    }

    //点击新增按钮弹出模态框。
    $("#add_daily_btn").click(function(){
        reset_form("#proAddModal form");
        //发送ajax请求，查出部门信息，显示在下拉列表中
        getProjectNameList("#proAddModal select");
        $("#proAddModal").modal({
            backdrop:"static"
        });
    });

    //查询所有的项目信息并显示在下拉列表中
    function getProjectNameList(ele) {
        //清空之前下拉列表的值
        $(ele).empty();
        $.ajax({
            url:"${APP_PATH}/TProject/getProjectNameList",
            type:"GET",
            success:function (result) {
                $.each(result.extend.pros, function () {
                    var optionEle = $("<option></option>").append(this.projectName).attr("value", this.id);
                    optionEle.appendTo(ele);
                });
            }
        });
    }

    //点击保存，添加日报列表信息
    $("#daily_save_btn").click(function () {
        $.ajax({
            url:"${APP_PATH}/daily/saveDaily/"+userId,
            type:"POST",
            data:$("#proAddModal form").serialize(),
            success:function (result) {
                if (result.code == 100) {
                    $("#proAddModal").modal('hide');
                    to_page(totalRecord);
                } else if(result.code == 110){
                    layer.open({
                        titel:'确信息',
                        content:'当天日报已存在'
                    });
                }
            }
        });
    });

    //周报导出
    $("#weeklyExport_btn").click(function(){
        if($("#dailyStartDateStr").val()==""||$("#dailyEndDateStr").val()==""){
            layer.alert('请选择日报日期！');
            return false;
        }
        $('#weeklyExport').submit();
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
        $('#overWorkExport').submit();
        reset_form("#overWorkAddModal form");
        $("#overWorkAddModal").modal('hide');
    });
</script>
</body>
</html>
