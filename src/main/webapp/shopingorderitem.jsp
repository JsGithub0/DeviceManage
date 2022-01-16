<html>
<%@ page language="java" contentType="text/html;  charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>购物清单项列表</title>
    <%
        pageContext.setAttribute("APP_PATH",request.getContextPath());
    %>
    <script src="${APP_PATH}/jslib/js/jquery-1.12.4.min.js"></script>
    <script src="./jslib/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <link href="./jslib/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="modal fade" id="shopingOrderItemUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >购物清单项修改</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">购物清单编号</label>
                        <div class="col-sm-4">
                            <!-- 提交购物清单id即可 -->
                            <select class="form-control" name="ShopingorderID" id="shopingOrderSelect2">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">购买设备</label>
                        <div class="col-sm-4">
                            <!-- 提交设备id即可 -->
                            <select class="form-control" name="DeviceID" id="deviceSelect2">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">购买数量</label>
                        <div class="col-sm-10">
                            <input type="text" name="BuyNum" class="form-control" id="buyNum_update_input" placeholder="BuyNum">
                            <span class="help-block"></span>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="shopingCart_update_btn">更新</button>
            </div>
        </div>
    </div>
</div>

<!-- 购物清单项添加的模态框 -->
<div class="modal fade" id="shopingOrderItemAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">购物清单项修改</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">购物清单编号</label>
                        <div class="col-sm-4">
                            <!-- 提交购物清单id即可 -->
                            <select class="form-control" name="ShopingorderID" id="shopingOrderSelect">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">购买设备</label>
                        <div class="col-sm-4">
                            <!-- 提交设备id即可 -->
                            <select class="form-control" name="DeviceID" id="deviceSelect">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">购买数量</label>
                        <div class="col-sm-10">
                            <input type="text" name="BuyNum" class="form-control" id="buyNum_add_input" placeholder="BuyNum">
                            <span class="help-block"></span>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="shopingCart_save_btn">保存</button>
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
            <button class="btn btn-primary" id="shopingOrderItem_add_modal_btn">新增</button>
            <button class="btn btn-danger" id="shopingOrderItem_delete_all_btn">删除</button>
        </div>
    </div>
    <!--显示表格数据-->
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover" id="shopingorderitems_table">
                <thead>
                <tr>
                    <th>
                        <input type="checkbox" id="check_all">
                    </th>
                    <th>购物清单项编号</th>
                    <th>购物清单编号</th>
                    <th>设备编号</th>
                    <th>购买数量</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                注意：表格数据内容为空，需要动态生成
                </tbody>
            </table>
        </div>
    </div>
    <!--显示分页信息-->
    <div class="row">
        <!--分页文字信息-->
        <div class="col-md-6" id="page_info_area">
            注意：分页文字内容为空，需要动态生成
        </div>
        <!--分页条信息-->
        <div>
            注意：分页条内容为空，需要动态生成
        </div>
    </div>
</div>
<script type="text/javascript">
    //1、页面加载完成以后，直接去发送ajax请求，要到分页数据
    $(function () {
        //去首页
        findAllShopingorderitem();
    });

    function findAllShopingorderitem() {
        $.ajax({
            url:"${APP_PATH}/findAllShopingorderitem",
            type:"GET",
            success:function (result) {
                console.log(result);
                var jsjson=eval("("+result+")");
                console.log(jsjson);
                build_shopingorderitem_table(jsjson);
            }
        });
    }

    function build_shopingorderitem_table(jsjson){
        //清空table表格
        $("#shopingorderitems_table tbody").empty();
        var shopingorderitems=jsjson.result;
        $.each(shopingorderitems,function (index,item) {
            var checkBoxTd=$("<td><input type='checkbox' class='check_item'/></td>");
            var ShopingOrderItemIDTd=$("<td></td>").append(item.ShopingOrderItemID);
            var ShopingOrderIDTd=$("<td></td>").append(item.ShopingOrderID);
            var DeviceIDTd=$("<td></td>").append(item.Device.DeviceID);
            var BuyNumTd=$("<td></td>").append(item.BuyNum);
            var editBtn=$("<button></button>").addClass("btn btn-primary btn-sm edit_btn").append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
            //为编辑按钮添加一个自定义的属性，来表示当前设备id
            editBtn.attr("edit-id",item.ShopingOrderItemID);
            var delBtn=$("<button></button>").addClass("btn btn-danger btn-sm delete_btn").append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("删除");
            //为删除按钮添加一个自定义的属性来表示当前删除的设备id
            delBtn.attr("del-id",item.ShopingOrderItemID);
            var btnTd=$("<td></td>").append(editBtn).append(" ").append(delBtn);
            //append方法执行完后还是返回原对象，可以连续append，最后添加到tbody中
            $("<tr></tr>").append(checkBoxTd).append(ShopingOrderItemIDTd).append(ShopingOrderIDTd).append(DeviceIDTd).append(BuyNumTd).append(btnTd).appendTo("#shopingorderitems_table tbody");
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
    $("#shopingOrderItem_add_modal_btn").click(function(){
        //清除表单数据（表单完整重置（表单的数据，表单的样式））
        reset_form("#shopingOrderItemAddModal form");
        //发送ajax请求，查出部门信息，显示在下拉列表中
        getDs("#deviceSelect");
        getSOs("#shopingOrderSelect");
        //弹出模态框
        $("#shopingOrderItemAddModal").modal({
            backdrop:"static"
        });
    });

    //查出所有的设备信息并显示在下拉列表中
    function getDs(ele){
        //清空之前下拉列表的值
        $(ele).empty();
        $.ajax({
            url:"${APP_PATH}/findAllDevice",
            type:"GET",
            success:function (result) {
                var jsjson=eval("("+result+")");
                var devices=jsjson.result;
                $.each(devices,function (index,item) {
                    var DeviceOption=$("<option></potion>").append(item.DeviceName).appendTo(ele);
                    DeviceOption.attr("dId",item.DeviceID);
                });
            }
        });
    }

    //查出所有的购物清单信息并显示在下拉列表中
    function getSOs(ele){
        //清空之前下拉列表的值
        $(ele).empty();
        $.ajax({
            url:"${APP_PATH}/findAllShopingorder",
            type:"GET",
            success:function (result) {
                var jsjson=eval("("+result+")");
                var shopingOrders=jsjson.result;
                $.each(shopingOrders,function (index,item) {
                    var ShopingorderOption=$("<option></potion>").append(item.ShopingOrderID).appendTo(ele);
                    ShopingorderOption.attr("soId",item.ShopingOrderID);
                });
            }
        });
    }

    //单个删除
    $(document).on("click",".delete_btn",function(){
        //1、弹出是否确认删除对话框
        var shopingOrderItemName = $(this).parents("tr").find("td:eq(2)").text()+"号购物清单的"+$(this).parents("tr").find("td:eq(1)").text()+"号购物清单项";
        var shopingOrderItemId= $(this).attr("del-id");
        //alert($(this).parents("tr").find("td:eq(1)").text());
        if(confirm("确认删除【"+shopingOrderItemName+"】吗？")){
            //确认，发送ajax请求删除即可
            $.ajax({
                url:"${APP_PATH}/"+shopingOrderItemId,
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
        getDs("#deviceSelect2");
        getSOs("#shopingOrderSelect2");
        $("#shopingOrderItemUpdateModal").modal({
            backdrop:"static"
        });
    });

    //点击全部删除，就批量删除
    $("#shopingOrderItem_delete_all_btn").click(function(){
        var shopingOrderItemNames = "";
        var del_idstr = "";
        $.each($(".check_item:checked"),function(){
            //this
            shopingOrderItemNames += $(this).parents("tr").find("td:eq(1)").text()+"号购物清单的"+$(this).parents("tr").find("td:eq(1)").text()+"号购物清单项"+",";
            //组装员工id字符串
            del_idstr += $(this).parents("tr").find("td:eq(1)").text()+"-";
        });
        //去除empNames多余的,
        shopingOrderItemNames = shopingOrderItemNames.substring(0, shopingOrderItemNames.length-1);
        //去除删除的id多余的-
        del_idstr = del_idstr.substring(0, del_idstr.length-1);
        if(confirm("确认删除【"+shopingOrderItemNames+"】吗？")){
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
