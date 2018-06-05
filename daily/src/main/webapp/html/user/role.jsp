<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户列表</title>
    <jsp:include page="/html/default/pub.jsp"/>
    <link href="/js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="/js/bootstrap/js/bootstrap.min.js"></script>
    <link href="/js/jquery/tree/jquery.treeview.css" rel="stylesheet">
    <script src="/js/jquery/tree/jquery.treeview.js"></script>
</head>
<body>
<!-- 角色修改的模态框 -->
<div class="modal fade" id="roleUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">用户修改</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">角色类型</label>
                        <div class="col-sm-10">
                            <select id="roleTypeSelectUpdate" class="form-control" name="roleType">
                                <option value="00">项目角色</option>
                                <option value="01">部门角色</option>
                            </select>
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">角色名称</label>
                        <div class="col-sm-10">
                            <input type="text" name="roleName" class="form-control" id="roleName_update_input">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">角色描述</label>
                        <div class="col-sm-10">
                            <input type="text" name="description" class="form-control" id="description_update_input">
                            <span class="help-block"></span>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="role_update_btn">更新</button>
            </div>
        </div>
    </div>
</div>


<!-- 角色添加的模态框 -->
<div class="modal fade" id="roleAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">角色添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">角色类型</label>
                        <div class="col-sm-10">
                            <select id="roleTypeSelectAdd" class="form-control" name="roleType">
                                <option value="00">项目角色</option>
                                <option value="01">部门角色</option>
                            </select>
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">角色名称</label>
                        <div class="col-sm-10">
                            <input type="text" name="roleName" class="form-control" id="roleName_add_input">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">角色描述</label>
                        <div class="col-sm-10">
                            <input type="text" name="description" class="form-control" id="description_add_input">
                            <span class="help-block"></span>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="role_save_btn">保存</button>
            </div>
        </div>
    </div>
</div>

<!-- 权限列表的模态框 -->
<div class="modal fade" id="privilegeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">设置权限</h4>
            </div>
            <div class="modal-body">
                <div class="tree">
                    <ui class="navigation">

                    </ui>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="privilege_save_btn">保存</button>
            </div>
        </div>
    </div>
</div>


<!-- 搭建显示页面 -->
<div style="width: 1100px;">
    <!-- 标题 -->
    <div class="row">
        <div class="col-md-12">
        </div>
    </div>
    <!-- 按钮 -->
    <div class="row">
        <div class="col-md-1">
            <button class="btn btn-primary" id="role_add_modal_btn" privilege-id="6">新增</button>
        </div>
    </div>
    <!-- 显示表格数据 -->
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover" id="roles_table">
                <thead>
                <tr>
                    <th>角色名称</th>
                    <th>角色描述</th>
                    <th>角色类型</th>
                    <th>创建日期</th>
                    <th>修改日期</th>
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
    //获取当前用户的权限列表
    var privilegeList = ${user.privilegeList};
    //根据权限列表判断是否显示
    if (privilegeList.indexOf(6) == -1) {
        $("#role_add_modal_btn").hide();
    }
    //1、页面加载完成以后，直接去发送ajax请求,要到分页数据
    $(function () {
        //去首页
        to_page(1);
    });

    function to_page(pn) {
        var index = layer.msg('拼命加载中', {
            icon: 16
            ,shade: 0.01
        });
        $.ajax({
            url: "/role/getRoles",
            data: "pn=" + pn,
            type: "GET",
            success: function (result) {
                //1、解析并显示角色数据
                build_roles_table(result);
                //2、解析并显示分页信息
                build_page_info(result);
                //3、解析显示分页条数据
                build_page_nav(result);
                layer.close(index);
            }
        });
    }

    function build_roles_table(result) {
        //清空table表格
        $("#roles_table tbody").empty();
        var roles = result.extend.pageInfo.list;
        $.each(roles, function (index, item) {
            var roleName = $("<td></td>").append(item.roleName);
            var description = $("<td></td>").append(item.description);
            var roleType = $("<td></td>").append(item.roleType == '00'? "项目角色":"部门角色");
            var createTime = $("<td></td>").append(item.createTime == null ? "" : new Date(item.createTime).Format("yyyy-MM-dd"));
            var updateTime = $("<td></td>").append(item.updateTime == null ? "" : new Date(item.updateTime).Format("yyyy-MM-dd"));
            var roleId = $("<td></td>").append(item.roleId).hide();
            /**
             <button class="">
             <span class="" aria-hidden="true"></span>
             编辑
             </button>
             */
            var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
            //为编辑按钮添加一个自定义的属性，来表示当前角色id
            editBtn.attr("edit-id", item.roleId);
            //添加属性权限ID
            editBtn.attr("privilege-id",7);
            //根据权限列表判断是否显示
            if (privilegeList.indexOf(7) == -1) {
                editBtn.hide();
            }
            var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
            //为删除按钮添加一个自定义的属性来表示当前删除的角色id
            delBtn.attr("del-id", item.roleId);
            //添加属性权限ID
            delBtn.attr("privilege-id",8);
            //根据权限列表判断是否显示
            if (privilegeList.indexOf(8) == -1) {
                delBtn.hide();
            }
            var priviBtn = $("<button></button>").addClass("btn btn-primary btn-sm privi_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("设置权限");
            //为权限按钮添加一个自定义的属性来表示当前设置权限的角色id
            priviBtn.attr("privi-id", item.roleId);
            //添加属性权限ID
            priviBtn.attr("privilege-id",9);
            //根据权限列表判断是否显示
            if (privilegeList.indexOf(9) == -1) {
                priviBtn.hide();
            }
            var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn).append(" ").append(priviBtn);
            //append方法执行完成以后还是返回原来的元素
            $("<tr></tr>")
                .append(roleName)
                .append(description)
                .append(roleType)
                .append(createTime)
                .append(updateTime)
                .append(updateTime)
                .append(roleId)
                .append(btnTd)
                .appendTo("#roles_table tbody");
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
    $("#role_add_modal_btn").click(function () {
        //清除表单数据（表单完整重置（表单的数据，表单的样式））
        reset_form("#roleAddModal form");
        //弹出模态框
        $("#roleAddModal").modal({
            backdrop: "static"
        });
    });

    //校验表单数据
    function validate_add_form() {
        //1、拿到要校验的数据，使用正则表达式
        var roleName = $("#roleName_add_input").val();
        var regName = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,10})/;
        if (!regName.test(roleName)) {
            // alert("角色名称可以是2-10位中文或者6-16位英文和数字的组合");
            show_validate_msg("#roleName_add_input", "error", "角色名可以是2-10位中文或者6-16位英文和数字的组合");
            return false;
        } else {
            show_validate_msg("#roleName_add_input", "success", "");
        }
        return true;
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

    //校验角色名是否可用
    $("#roleName_add_input").change(function () {
        //发送ajax请求校验角色名是否可用
        var roleName = this.value;
        $.ajax({
            url: "/role/roleCheck",
            data: "roleName=" + roleName,
            type: "POST",
            success: function (result) {
                if (result.code == "100") {
                    show_validate_msg("#roleName_add_input", "success", "角色名可用");
                    $("#role_save_btn").attr("ajax-va", "success");
                } else {
                    show_validate_msg("#roleName_add_input", "error", result.extend.va_msg);
                    $("#role_save_btn").attr("ajax-va", "error");
                }
            }
        });
    });

    //校验修改角色名是否可用
    $("#roleName_update_input").change(function () {
        //发送ajax请求校验角色名是否可用
        var roleName = this.value;
        var hideName = $("#hideName").html();
        $.ajax({
            url: "/role/roleCheck",
            data: "roleName=" + roleName,
            type: "POST",
            async: false,
            success: function (result) {
                if (result.code == "100") {
                    show_validate_msg("#roleName_update_input", "success", "角色名可用");
                    $("#role_update_btn").attr("ajax-va", "success");
                } else {
                    if(hideName == roleName){
                        show_validate_msg("#roleName_update_input", "success", "角色名可用");
                        $("#role_update_btn").attr("ajax-va", "success");
                    }else {
                        show_validate_msg("#roleName_update_input", "error", result.extend.va_msg);
                        $("#role_update_btn").attr("ajax-va", "error");
                    }
                }
            }
        });
    });

    //点击保存，保存角色。
    $("#role_save_btn").click(function () {
        // 1、先对要提交给服务器的数据进行校验
        if (!validate_add_form()) {
            return false;
        }
        //1、判断之前的ajax用户名校验是否成功。如果成功。
        if ($(this).attr("ajax-va") == "error") {
            show_validate_msg("#roleName_add_input", "error", "角色名不可用");
            $("#role_save_btn").attr("ajax-va", "error");
            return false;
        }

        //2、发送ajax请求保存员工
        $.ajax({
            url: "/role/save",
            type: "POST",
            data: $("#roleAddModal form").serialize(),
            success: function (result) {
                //alert(result.msg);
                if (result.code == "100") {
                    //用户保存成功；
                    //1、关闭模态框
                    $("#roleAddModal").modal('hide');

                    //2、来到最后一页，显示刚才保存的数据
                    //发送ajax请求显示最后一页数据即可
                    to_page(totalRecord);
                } else {
                    //显示失败信息
                    //console.log(result);
                    //有哪个字段的错误信息就显示哪个字段的；
                    if (undefined != result.extend.errorFields.empName) {
                        //显示角色名字的错误信息
                        show_validate_msg("#roleName_add_input", "error", result.extend.errorFields.roleName);
                    }
                }
            }
        });
    });


    $(document).on("click", ".edit_btn", function () {
        //清空表单样式
        $('#roleUpdateModal form').find("*").removeClass("has-error has-success");
        $('#roleUpdateModal form').find(".help-block").text("");
        //1、查出用户信息，显示用户信息
        getRole($(this).attr("edit-id"));

        //2、把角色的id传递给模态框的更新按钮
        $("#role_update_btn").attr("edit-id", $(this).attr("edit-id"));
        $("#roleUpdateModal").modal({
            backdrop: "static"
        });
    });

    function getRole(id) {
        $.ajax({
            url: "/role/getRoleById/" + id,
            type: "GET",
            success: function (result) {
                //console.log(result);
                var roleData = result.extend.role;
                $("#roleName_update_input").val(roleData.roleName);
                $("#description_update_input").val([roleData.description]);
                $("#roleTypeSelectUpdate").val(roleData.roleType);
                $("#hideName").remove();
                $("#roleTypeSelectUpdate").after($("<span></span>").attr("id","hideName").html(roleData.roleName));
                $("#hideName").hide();
            }
        });
    }

    //点击更新，更新角色信息
    $("#role_update_btn").click(function () {
        //1、拿到要校验的数据，使用正则表达式
        var roleName = $("#roleName_update_input").val();
        var regName = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,10})/;
        if (!regName.test(roleName)) {
            // alert("角色名称可以是2-10位中文或者6-16位英文和数字的组合");
            show_validate_msg("#roleName_add_input", "error", "角色名可以是2-10位中文或者6-16位英文和数字的组合");
            return false;
        } else {
            show_validate_msg("#roleName_add_input", "success", "");
        }

        //1、判断之前的ajax用户名校验是否成功。如果成功。
        if ($(this).attr("ajax-va") == "error") {
            return false;
        }

        //2、发送ajax请求保存更新的角色数据
        $.ajax({
            url: "/role/update/" + $(this).attr("edit-id"),
            type: "PUT",
            data: $("#roleUpdateModal form").serialize(),
            success: function (result) {
                //1、关闭对话框
                $("#roleUpdateModal").modal("hide");
                layer.msg("更新成功!");
                //2、回到本页面
                to_page(currentPage);
            }
        });
    });

    //单个删除
    $(document).on("click", ".delete_btn", function () {
        //1、弹出是否确认删除对话框
        var roleName = $(this).parents("tr").find("td:eq(0)").text();
        var roleId = $(this).attr("del-id");
        layer.confirm("确认删除【" + roleName + "】吗？", {icon: 3, title: '确认信息'}, function (index) {
            //确认，发送ajax请求删除即可
            $.ajax({
                url: "/role/delete/" + roleId,
                type: "DELETE",
                success: function (result) {
                    layer.msg(result.msg);
                    //回到本页
                    to_page(currentPage);
                }
            });
        })
    });

    //设置权限按钮绑定点击事件
    $(document).on("click", ".privi_btn", function () {
        //清空内容
        $(".tree .navigation").empty();
        //1、查出权限信息，显示权限信息
        getPrivilege($(this).attr("privi-id"));

        //2、把角色的id传递给模态框的保存按钮
        $("#privilege_save_btn").attr("role-id", $(this).attr("privi-id"));
        $("#privilegeModal").modal({
            backdrop: "static"
        });
    });

    //查出所有的部门信息并显示在下拉列表中
    function getPrivilege(roleId) {
        $.ajax({
            url: "/privilege/getPrivileges",
            type: "GET",
            success: function (result) {
                var privileges = result.extend.resultList;
                init_node(privileges, roleId);
            }
        });
    }

    //查询角色的权限信息
    function getPrivilegeByRoleId(roleId) {
        $.ajax({
            url: "/rolePrivilege/getPrivileges",
            type: "GET",
            data: {roleId: roleId},
            success: function (data) {
                var privileges = data.extend.resultList;
                for (var i = 0; i < privileges.length; i++) {
                    $(".tree .navigation input[data-auth='" + privileges[i].privilegeId + "']").prop("checked", "true");
                }
            }
        })
    }

    //一级节点
    function init_node(privileges, roleId) {
        if (privileges != null) {
            for (var i = 0; i < privileges.length; i++) {
                if (privileges[i].parentId == null) {
                    var _html = '<li treeId="' + privileges[i].privilegeId + '" treeName="' + privileges[i].name + '" >&nbsp;&nbsp;<input  type="checkbox" data-auth="' + privileges[i].privilegeId + '" /><a href="javascript:void(0);" > ' + privileges[i].name + '</a></li>';
                    ;
                    $(".tree .navigation").append(_html);
                    init_subNode(privileges[i].privilegeId, privileges);
                }
            }
            //查询角色的权限信息，置为选中状态
            getPrivilegeByRoleId(roleId);
            init_tree();
        }
    }

    //子级节点
    function init_subNode(_id, privileges) {
        var _flag = true;
        var _parentObj = $('li[treeId="' + _id + '"]');
        for (var i = 0; i < privileges.length; i++) {
            if (privileges[i].parentId == _id) {
                if (_flag == true) {
                    $(_parentObj).append("<ul></ul>");
                    _flag = false;
                }
                var _html = '<li treeId="' + privileges[i].privilegeId + '" treeName="' + privileges[i].name + '" >&nbsp;&nbsp;<input  type="checkbox" data-auth="' + privileges[i].privilegeId + '" /><a href="javascript:void(0);" > ' + privileges[i].name + '</a></li>';
                ;
                $("ul:eq(0)", _parentObj).append(_html);
                init_subNode(privileges[i].privilegeId, privileges);
            }
        }
    }

    //树形初始化
    function init_tree() {
        $(".tree .navigation").treeview({
            persist: "location",
            collapsed: true,
            unique: true
        });
        initTreeChecked();
    }

    function initTreeChecked() {
        $(".tree .navigation input[type='checkbox']").click(function () {
            var pChk = this.checked;
            //当选中或取消一个权限时，也同时选中或取消所有的下级权限
            $(this).siblings("ul").find("input").each(function (index, ele) {
                ele.checked = pChk
            });

            //当选中一个权限时，也要选中所有的直接上级权限
            if (this.checked == true) {
                $(this).parents("li").children("input").each(function (index, ele) {
                    ele.checked = true;
                })
            }
        });
    }

    //点击保存，保存权限设置
    $("#privilege_save_btn").click(function () {
        var privileges = [];
        $(".tree .navigation input[type='checkbox']:checked").each(function (index, ele) {
            privileges.push($(ele).attr("data-auth"));
        });
        console.info(privileges);
        //2、发送ajax请求保存权限数据
        $.ajax({
            url: "/rolePrivilege/savePrivilege",
            type: "POST",
            data: {roleId: $("#privilege_save_btn").attr("role-id"), privilegeList: privileges, aa: 'eeee'},
            traditional: true,
            success: function (result) {
                layer.msg(result.msg);
                // 1、关闭对话框
                $("#privilegeModal").modal("hide");
                //2、回到本页面
                to_page(currentPage);
            }
        });
    });

</script>
</body>
</html>