<%@page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>


<!-- Mirrored from www.zi-han.net/theme/hplus/article.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:47 GMT -->
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<title>资讯列表</title>

<link href="./jules/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
<link href="./jules/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

<link href="./jules/css/animate.min.css" rel="stylesheet">
<link href="./jules/css/style.min862f.css?v=4.1.0" rel="stylesheet">
<link href="./jules/css/dataTables.bootstrap.css" rel="stylesheet">

<style type="text/css">
.selected {
	background-color: #a7aaab;
	cursor: pointer;
}
</style>
</head>

<body class="gray-bg">
	<div class="row">
		<div class="col-sm-12">
			<div class="tabs-container">
				<ul class="nav nav-tabs">
					<li class="active"><a data-toggle="tab" href="#tab-1"
						aria-expanded="true"> 资讯列表</a></li>
				</ul>
				<div class="tab-content">
					<div id="tab-1" class="tab-pane active">
						<div class="panel-body">
							<table class="table table-bordered" id="datatable1">
								
							</table>
						</div>
					</div>
						</div>
					</div>
				</div>
			</div>

	<script src="./jules/js/jquery.min.js?v=2.1.4"></script>
	<script src="./jules/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="./jules/js/content.min.js?v=1.0.0"></script>
	<script src="./jules/js/jquery.dataTables.js"></script>
	<script src="./jules/js/dataTables.bootstrap.js"></script>
	<!--<script src="js/dialog.js"></script>-->
	<script type="text/javascript">
	$(function(){
		$('#datatable1').DataTable(
				{
					destory : true,
					searching : true,
					bLengthChange : true,
					ordering : false,
					bScrollInfinite : true,
					bScrollCollapse : true,
					ajax : "findAllInfomation.action",
					aoColumns : [
					    {
					        "data" :  "rowNumber",
					        "title":  "序号",
						},
						{
							"data" : "title",
							"title": "标题",
						},
						{
							"data" : null,
							"title": "标题图片",  
							"render" : function(data,type,row){
								alert
			                	var html = "<img width='60' height='60' src='http://127.0.0.1"+data.informationImage+"'></img";
			                	return html;
			                }
			            },
						{
							"data" : "introduction",
							"title": "简介",
						}, 
						{
							"data" : "informationCreateTime",
							"title": "时间",
							"width" : "130px"
						},
						{
			                "data" : null,
			                "title" : "操作",
			                "render" : function(data,type,row){
			                	var html = "<div style='margin-top:5px;' ><a id='show"+data.informationId+"' href='http://127.0.0.1/DeviceManage/showInformationById?infoId="+data.informationId+"' class='btn btn-success btn-xs' ><i class='glyphicon glyphicon-search'></i>查看</a></div>";
			                	html += "<div style='margin-top:5px;' ><a id='edit"+data.informationId+"' href='editInformationById.action?informationId="+data.informationId+"' class='btn btn-success btn-xs' ><i class='fa fa-arrow-up'></i>编辑</a></div>";
			                	if(data.informationId != 1) {
			                		html += "<div style='margin-top:5px' ><a id='del"+data.informationId+"' onclick='Delete("+data.informationId+")' class='btn btn-success btn-xs' style='padding-top:5px'><i class='fa fa-close'></i>删除</a></div>";
			                		if(data.banner == 5){
			                			html += "<div style='margin-top:5px' ><a onclick='lunbo("+data.informationId+")' class='btn btn-success btn-xs' style='padding-top:5px'><i class='fa fa-plus'></i>轮播</a></div>";
			                		} else {
			                			html += "<div style='margin-top:5px' ><a onclick='cancelLunbo("+data.informationId+")' class='btn btn-success btn-xs' style='padding-top:5px'><i class='fa fa-minus'></i>取消</a></div>";
					                	
			                		}
			                	}
								return html;
			                }
			            }
					]
				}
			);
	});
	
	function Delete(informationId){
		var r=confirm("确认删除资讯?");
		if (r==true) {
			$.ajax({
			    url:"deleteInformation.action",
			    type:"GET",
			    async:true,
			    data:{
			    	"informationId":informationId
			    },
			    dataType:'json',
			    success:function(data){
			        if(data.data == 1){
			        	alert("删除成功");
			        } else {
			        	alert("删除失败");
			        }
			       	window.location = "allinformation.jsp";
			    },
			    error:function(xhr,textStatus){
			    }
			});
		}
	}
	function lunbo(informationId){
		var r=confirm("确认轮播资讯?");
		if (r==true) {
			$.ajax({
			    url:"lunboInformation.action",
			    type:"POST",
			    async:true,
			    data:{
			    	"informationId":informationId
			    },
			    dataType:'json',
			    success:function(data){
			        if(data.data == 1){
			        	alert("轮播成功");
			        } else {
			        	alert("轮播失败");
			        }
			       	window.location = "allinformation.jsp";
			    },
			    error:function(xhr,textStatus){
			    }
			});
		}
	}
	///取消轮播
	function cancelLunbo(informationId){
		var r=confirm("确认取消轮播资讯?");
		if (r==true) {
			$.ajax({
			    url:"cancelLunboInformation.action",
			    type:"POST",
			    async:true,
			    data:{
			    	"informationId":informationId
			    },
			    dataType:'json',
			    success:function(data){
			        if(data.data == 1){
			        	alert("取消成功");
			        } else {
			        	alert("取消失败");
			        }
			       	window.location = "allinformation.jsp";
			    },
			    error:function(xhr,textStatus){
			    }
			});
		}
	}
	</script>
</body>
