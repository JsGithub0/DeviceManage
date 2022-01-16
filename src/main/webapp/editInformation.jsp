<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>咨询</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <script type="text/javascript" charset="utf-8" src="./ueditor1.4.3.3/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="./ueditor1.4.3.3/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="./ueditor1.4.3.3/lang/zh-cn/zh-cn.js"></script>
	<link href="./jules/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
	<link href="./jules/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
	<link href="./jules/css/animate.min.css" rel="stylesheet">
	<link href="./jules/css/style.min862f.css?v=4.1.0" rel="stylesheet">
	<link href="./jules/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
	<link href="./jules/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
	<link href="./css/bootstrap-datetimepicker.css" rel="stylesheet">
	<link href="./jules/css/animate.min.css" rel="stylesheet">
	<link href="./jules/css/plugins/chosen/chosen.css" rel="stylesheet">
	<link href="./jules/css/style.min862f.css?v=4.1.0" rel="stylesheet">
	<link href="./jules/css/plugins/iCheck/custom.css" rel="stylesheet">
	<link href="./jules/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
	<link href="./matrixadmin/css/select2.css" rel="stylesheet">
	<link rel="stylesheet" href="./css/select2.css" />
	<style>
		th {
			background-color: #F5F5F6;
		}
	</style>
    <style type="text/css">
        div{
            width:100%;
        }
        label{
            position: relative;
        }
        #fileinp{
            position: absolute;
            left: 0;
            top: 0;
            opacity: 0;
        }
        #btn{
            margin-right: 5px;
        }
        #text{
            color: black;
        }
    </style>
    
</head>
<body>
<div>
	<div class="wrapper wrapper-content  animated fadeInRight article">
			<div class="row">
				<div class="col-lg-10 col-lg-offset-1">
					<div class="ibox">
						<div class="ibox-content">
							<div class="ibox float-e-margins">
								<div class="text-center">
									<h3 id="text">编辑咨询（图文混排）</h3>
								</div>
								<div class="ibox-content">
									<form method="post" action="editInformation" enctype="multipart/form-data">
										<table class="table table-bordered" id="table" style="height: auto;">
										 <tbody>
											<tr>
												<th>标题：</th>
												<td>
													<textarea  style="display:none" id="informationTitle">${informationTitle}</textarea>
													<script id="editorTitle" type="text/plain" name="informationTitle" style="width:100%;height:100px;"></script>
												</td>
											</tr>
											<tr>
												<th>标题图片：</th>
												<td class="uploader white">
													<label for="fileinp">
												        <input type="button" id="btn" value="点我上传"><span id="text">${newFileName1}</span>
												        <input id="fileinp" type="file" name="informationImage" accept="image/*" size="60">
													</label>
												</td>
											</tr>
												<th>简介：</th>
												<td colspan="3">
													<script id="editorBrief" type="text/plain" name="informationBrief" style="width:100%;height:200px;"></script>
													<textarea id="informationBrief" style="display:none">${informationBrief}</textarea>
												</td>
											</tr>
											<tr>
												<th>正文：</th>
												<td colspan="3">
													<script id="editor" type="text/plain" name="informationMainBody" style="width:100%;height:500px;"></script>
												</td>
											</tr>
											<tr>
												<th>提交：</th>
												<td>
													<input type="submit" class="btn btn-primary" value="提交" id='upload'>
													
													<input class="btn btn-primary" onclick="javascript:history.go(-1);" type="reset" value="返回">
												</td>
											</tr>
											<tr>
										</tbody>
									</table>
									<input type="hidden" name="information" id="information" value="${informationId}">
								</form>
								<textarea  style="display:none" id="context">${informationMainBody}</textarea>
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="./jules/js/jquery.min.js?v=2.1.4 "></script>
<!--<script src="js/jquery-ui.js "></script>-->
<script src="./jules/js/bootstrap.min.js?v=3.3.6 "></script>
<script src="./js/bootstrap-datetimepicker.js "></script>
<!--<script src="js/bootstrap-datetimepicker.zh-CN.js "></script>-->
<script src="./jules/js/content.min.js?v=1.0.0 "></script>
<script src="./jules/js/layer/laydate/laydate.js "></script>
<script src="./jules/js/plugins/chosen/chosen.jquery.js "></script>
<script src="./jules/js/plugins/cropper/cropper.min.js "></script>
<script src="./jules/js/demo/form-advanced-demo.min.js "></script>
<script src="./jules/js/plugins/iCheck/icheck.min.js "></script>
<script type="text/javascript" src="./js/jquery-1.7.2.js"></script>
<script src="./matrixadmin/js/select2.min.js"></script>
<script type="text/javascript">
	$(function(){
		//实例化编辑器
	    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	    var ue = UE.getEditor('editor');    
	    var editorTitle = UE.getEditor('editorTitle',{
	    	toolbars : [
		        ['fontfamily', //字体
		         'fontsize', //字号
		         'source', //源代码
		         'undo', //撤销
		         'redo', //重做
		         'italic', //斜体
		         'underline', //下划线
		         'bold', //加粗
		         'forecolor', //字体颜色
		         'backcolor' //背景色
		        ]
	        ],
	    });
	    var editorBrief = UE.getEditor('editorBrief',{
	    	toolbars : [
   		        ['fontfamily', //字体
   		         'fontsize', //字号
   		         'source', //源代码
   		         'undo', //撤销
   		         'redo', //重做
   		         'italic', //斜体
   		         'underline', //下划线
   		         'bold', //加粗
   		         'forecolor', //字体颜色
   		         'backcolor' //背景色
   		        ]
   	        ],
   	    });
		var context = $("#context").val();
		
	    ue.ready(function() {
		    ue.setContent(context);
	    });
	    editorTitle.ready(function() {
	    	
	    	editorTitle.setContent($("#informationTitle").val());
	    });
	    editorBrief.ready(function() {
	    	editorBrief.setContent($("#informationBrief").val());
	    });
	    
	    var information = $("#information").val();
	    if(information == 1){
	    	$.each($("#table tr"), function(i){ 
		    	if(i <2){ 
		    	this.style.display = 'none'; 
		    	} 
	    	});
		    $("#text").html("编辑尚德书院简介");
	    }
	});
    
</script>
<script type="text/javascript">
	$("#fileinp").change(function () {
		var s = $("#fileinp").val();
		$("#text").html(s.substring(s.lastIndexOf("\\")+1));
	});
	function getPhotoSize(obj){
		photoExt=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();
		if(photoExt!='.jpg'&&photoExt!='.png'&&photoExt!='.jpeg'){
			alert("文件格式错误！");
			var nf = obj.cloneNode(true); 
			nf.value=''; 
			obj.parentNode.replaceChild(nf, obj);
			return false;
	   }
	   var fileSize = 0;
	   var isIE = /msie/i.test(navigator.userAgent) && !window.opera; 
	   if (isIE && !obj.files) { 
		   var filePath = obj.value; 
		   var fileSystem = new ActiveXObject("Scripting.FileSystemObject"); 
		   var file = fileSystem.GetFile (filePath); 
		   fileSize = file.Size; 
	   }else { 
		   fileSize = obj.files[0].size; 
	   }
	   fileSize=Math.round(fileSize/1024*100)/100; //取得图片文件的大小 
	   if(fileSize>2048){
		   alert("不接受超过2m的图片！");
		   return false;
		}
	}
</script>
</body>
</html>