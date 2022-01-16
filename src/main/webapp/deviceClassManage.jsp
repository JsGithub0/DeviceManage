<html>
<%@ page language="java" contentType="text/html;  charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>设备分类列表</title>
    <%
        pageContext.setAttribute("APP_PATH",request.getContextPath());
    %>
    <script src="${APP_PATH}/jslib/js/jquery-1.12.4.min.js"></script>
    <script src="./jslib/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <link href="./jslib/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<!-- 修改的模态框 -->
<div class="modal fade" id="deviceClassUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">设备类修改</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">设备类名称</label>
                        <div class="col-sm-10">
                            <input type="text" name="DeviceClassName" class="form-control" id="deviceClassName_update_input" placeholder="DeviceClassName">
                            <span class="help-block"></span>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="deviceClass_update_btn">更新</button>
            </div>
        </div>
    </div>
</div>

<!-- 设备类添加的模态框 -->
<div class="modal fade" id="deviceClassAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">设备类添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">设备类名称</label>
                        <div class="col-sm-10">
                            <input type="text" name="DeviceClassName" class="form-control" id="deviceClassName_add_input" placeholder="DeviceClassName">
                            <span class="help-block"></span>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="deviceClass_save_btn">保存</button>
            </div>
        </div>
    </div>
</div>

<!--搭建显示页面-->
<div class="container">
    <!--标题-->
    <div class="row">
        <div class="col-md-12">
            <h1>列表</h1>
        </div>
    </div>
    <!--按钮-->
    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <button class="btn btn-primary" id="deviceClass_add_modal_btn">新增</button>
            <button class="btn btn-danger" id="deviceClass_delete_all_btn">删除</button>
        </div>
    </div>
    <!--显示表格数据-->
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover" id="deviceclasses_table">
                <thead>
                <tr>
                    <th>
                        <input type="checkbox" id="check_all"/>
                    </th>
                    <th>设备分类编号</th>
                    <th>设备分类名称</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                注意：表格数据内容为空，需要动态生成
                </tbody>
            </table>
        </div>
    </div>
</div>
<script type="text/javascript">
    //1、页面加载完成以后，直接去发送ajax请求，要到分页数据
    $(function () {
        //去首页
        findAllDeviceClass();
    });

    function findAllDeviceClass() {
        $.ajax({
            url:"${APP_PATH}/findAllDeviceClass",
            type:"GET",
            success:function (result) {
                console.log(result);
                //1.解析并显示设备分类数据
                //build_deviceclass_table(result);
                var jsjson=eval("("+result+")");
                console.log(jsjson);
                build_deviceclass_table(jsjson);
            }
        });
    }

    function build_deviceclass_table(jsjson){
        //清空table表格
        $("#deviceclasses_table tbody").empty();
        var deviceclasses=jsjson.result;
        $.each(deviceclasses,function (index,item) {
            var checkBoxTd=$("<td><input type='checkbox' class='check_item'/></td>");
            var DeviceClassIDTd=$("<td></td>").append(item.DeviceClassID);
            var DeviceClassNameTd=$("<td></td>").append(item.DeviceClassName);
            var editBtn=$("<button></button>").addClass("btn btn-primary btn-sm edit_btn").append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
            //为编辑按钮添加一个自定义的属性，来表示当前员工id
            editBtn.attr("edit-id",item.DeviceClassID);
            var delBtn=$("<button></button>").addClass("btn btn-danger btn-sm delete_btn").append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("删除");
            //为删除按钮添加一个自定义的属性来表示当前删除的员工id
            delBtn.attr("del-id",item.DeviceClassID);
            var btnTd=$("<td></td>").append(editBtn).append(" ").append(delBtn);
            //append方法执行完后还是返回原对象，可以连续append，最后添加到tbody中
            $("<tr></tr>").append(checkBoxTd).append(DeviceClassIDTd).append(DeviceClassNameTd).append(btnTd).appendTo("#deviceclasses_table tbody");
        });
    }

    //清空表单样式及内容
    function reset_form(ele){
        $(ele)[0].reset();
        //清空表单样式
        $(ele).find("*").removeClass("has-error has-success");
        $(ele).find(".help-block").text("");
    }

    //点击新增按钮弹出模态框。
    $("#deviceClass_add_modal_btn").click(function(){
        //清除表单数据（表单完整重置（表单的数据，表单的样式））
        reset_form("#deviceClassAddModal form");
        //弹出模态框
        $("#deviceClassAddModal").modal({
            backdrop:"static"
        });
    });


    //单个删除
    $(document).on("click",".delete_btn",function(){
        //1、弹出是否确认删除对话框
        var deviceClassName = $(this).parents("tr").find("td:eq(2)").text();
        var deviceClassId = $(this).attr("del-id");
        //alert($(this).parents("tr").find("td:eq(1)").text());
        if(confirm("确认删除【"+deviceClassName+"】吗？")){
            //确认，发送ajax请求删除即可
            $.ajax({
                url:"${APP_PATH}/"+deviceClassId,
                type:"DELETE",
                success:function(result){
                    alert(result.msg);
                }
            });
        }
    });

    //完成全选/全不选功能
    $("#check_all").click(function(){
        //attr获取checked是undefined;
        //我们这些dom原生的属性；attr获取自定义属性的值；
        //prop修改和读取dom原生属性的值
        $(".check_item").prop("checked",$(this).prop("checked"));
    });

    //check_item
    $(document).on("click",".check_item",function(){
        //判断当前选择中的元素是否5个
        var flag = $(".check_item:checked").length==$(".check_item").length;
        $("#check_all").prop("checked",flag);
    });

    $(document).on("click",".edit_btn",function(){
        $("#deviceClassUpdateModal").modal({
            backdrop:"static"
        });
    });


    //点击全部删除，就批量删除
    $("#deviceClass_delete_all_btn").click(function(){
        var deviceClassNames = "";
        var del_idstr = "";
        $.each($(".check_item:checked"),function(){
            //this
            deviceClassNames += $(this).parents("tr").find("td:eq(2)").text()+",";
            //组装员工id字符串
            del_idstr += $(this).parents("tr").find("td:eq(1)").text()+"-";
        });
        //去除empNames多余的,
        deviceClassNames = deviceClassNames.substring(0, deviceClassNames.length-1);
        //去除删除的id多余的-
        del_idstr = del_idstr.substring(0, del_idstr.length-1);
        if(confirm("确认删除【"+deviceClassNames+"】吗？")){
            //发送ajax请求删除
            $.ajax({
                url:"${APP_PATH}/emp/"+del_idstr,
                type:"DELETE",
                success:function(result){
                    alert(result.msg);
                }
            });
        }
    });
</script>
</body>
</html>
