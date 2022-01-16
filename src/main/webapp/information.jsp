<html>
<%@ page language="java" contentType="text/html;  charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>资讯列表</title>
    <%
        pageContext.setAttribute("APP_PATH",request.getContextPath());
    %>
    <script src="${APP_PATH}/jslib/js/jquery-1.12.4.min.js"></script>
    <script src="./jslib/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <link href="./jslib/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
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
            <button class="btn btn-primary" id="information_add_modal_btn">新增</button>
            <button class="btn btn-danger" id="information_delete_all_btn">删除</button>
        </div>
    </div>
    <!--显示表格数据-->
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover" id="informations_table">
                <thead>
                <tr>
                    <th>
                        <input type="checkbox" id="check_all">
                    </th>
                    <th>资讯编号</th>
                    <th>资讯内容</th>
                    <th>资讯图片</th>
                    <th>资讯创建时间</th>
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
        findAllInformation();
    });

    function findAllInformation() {
        $.ajax({
            url:"${APP_PATH}/findAllInformation",
            type:"GET",
            success:function (result) {
                console.log(result);
                var jsjson=eval("("+result+")");
                console.log(jsjson);
                build_information_table(jsjson);
            }
        });
    }

    function build_information_table(jsjson){
        //清空table表格
        $("#informations_table tbody").empty();
        var informations=jsjson.result;
        $.each(informations,function (index,item) {
            var checkBoxTd=$("<td><input type='checkbox' class='check_item'/></td>");
            var InformationIDTd=$("<td></td>").append(item.InformationID);
            var InformationContentTd=$("<td></td>").append(item.InformationContent);
            var InformationImage=$("<td></td>").append(item.InformationImage);
            var InformationCreateTime=$("<td></td>").append(item.InformationCreateTime);
            var editBtn=$("<button></button>").addClass("btn btn-primary btn-sm edit_btn").append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
            //为编辑按钮添加一个自定义的属性，来表示当前设备id
            editBtn.attr("edit-id",item.InformationID);
            var delBtn=$("<button></button>").addClass("btn btn-danger btn-sm delete_btn").append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("删除");
            //为删除按钮添加一个自定义的属性来表示当前删除的设备id
            delBtn.attr("del-id",item.InformationID);
            var btnTd=$("<td></td>").append(editBtn).append(" ").append(delBtn);
            //append方法执行完后还是返回原对象，可以连续append，最后添加到tbody中
            $("<tr></tr>").append(checkBoxTd).append(InformationIDTd).append(InformationContentTd).append(InformationImage).append(InformationCreateTime).append(btnTd).appendTo("#informations_table tbody");
        });
    }
</script>
</body>
</html>
