<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>安全设置</title>
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
<div class="modal fade" id="userUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">用户修改</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-10">
                            <input type="text" name="userName" class="form-control" id="userName_update_input">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">性别</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender1_update_input" value="1" checked="checked"> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender2_update_input" value="0"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-10">
                            <input type="text" name="email" class="form-control" id="email_update_input" placeholder="email@adtect.com.cn">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">项目</label>
                        <div class="col-sm-6">
                            <select id="proSelectUpdate" class="form-control" name="projectId">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">部门</label>
                        <div class="col-sm-4">
                            <!-- 部门提交部门id即可 -->
                            <select id="deptSelectUpdate" class="form-control" name="deptId">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">角色</label>
                        <div class="col-sm-4">
                            <!-- 角色提交角色id即可 -->
                            <select id="roleSelectUpdate" class="form-control" name="roleId">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">手机号</label>
                        <div class="col-sm-10">
                            <input type="text" name="mobile" class="form-control" id="mobile_update_input">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-10">
                            <input type="text" name="password" class="form-control" id="password_update_input">
                            <span class="help-block"></span>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="user_update_btn">更新</button>
            </div>
        </div>
    </div>
</div>

<!-- 搭建显示页面 -->
<div class="container">
    <!-- 标题 -->
    <div class="row">
        <div class="col-md-12">
            <!--  登录员工修改页面-->
                <div class="modal-dialog" role="document">
                    <div >
                        <div >
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">姓名:</label>
                                    <div class="col-sm-10" style="padding-top: 7px">
                                        <span  name="userName"  id="userName_input" ></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">性别:</label>
                                    <div class="col-sm-10" style="padding-top: 7px">
                                        <span  name="gender"  id="gender_input" ></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">邮箱:</label>
                                    <div class="col-sm-10" style="padding-top: 7px">
                                        <span  name="email" id="email_input" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">角色:</label>
                                    <div class="col-sm-10" style="padding-top: 7px">
                                        <span name="role" id="role_input" ></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">手机号:</label>
                                    <div class="col-sm-10" style="padding-top: 7px">
                                        <span name="mobile"  id="mobile_input" ></span>
                                    </div>
                                </div>
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    <!-- 按钮 -->
</div>
<script type="text/javascript">
    console.info(${user.projectName});
    console.info(${user.projectId});
    $(function () {
        getUser("${user.userId}");
    })
    function getUser(id){
        $.ajax({
            url:"${APP_PATH}/user/"+id,
            type:"GET",
            success:function(result){
                var userData = result.extend.user;
                $("#userName_input").html(userData.userName);
                $("#email_input").text(userData.email);
                $("#gender_input").text([userData.gender=='1'?"男":"女"]);
                $("#mobile_input").html([userData.mobile]);
                $("#role_input").html([userData.roleName]);
            }
        });
    }
</script>
</body>
</html>