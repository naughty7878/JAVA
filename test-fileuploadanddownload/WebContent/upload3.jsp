<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传 jquery上传</title>
<script type="text/javascript" src="js/jquery.min.js"></script>
<style type="text/css">
.container{
	width: 200px;
	height: 20px;
	background-color: gray;
}
#progress{
	height: 20px;
	background-color: orange;
	display: inline-block;
}
	
</style>
</head>
<body>
	<form action="${pageContext.request.contextPath }/upload"
		enctype="multipart/form-data" method="post">
		 上传文件1: <input type="file" name="file1"><br /> 
		 <div class='container'>
		 	<span id="progress"></span>
		 </div>
	</form>
	<br>
	<button onclick="upload()">确认上传</button>
	<div id="info"></div>
	<div id="result"></div>
	<script>  
	    var totalSize = 0;  
	      
	    //绑定所有type=file的元素的onchange事件的处理函数  
	    $(':file').change(function() {  
	        var file = this.files[0]; //假设file标签没打开multiple属性，那么只取第一个文件就行了  
	        name = file.name;  
	        size = file.size;  
	        type = file.type;  
	        url = window.URL.createObjectURL(file); //获取本地文件的url，如果是图片文件，可用于预览图片  
	          
	        totalSize += size;  
	        $("#info").html("文件名：" + name + "<br>文件类型：" + type + "<br>文件大小：" + size + "<br>url: " + url);  
	          
	    });  
	  
	    function upload() {  
	        //创建FormData对象，初始化为form表单中的数据。需要添加其他数据可使用formData.append("property", "value");  
	        var formData = new FormData($('form')[0]);  
	          
	        //ajax异步上传  
	        $.ajax({  
	            url: "${pageContext.request.contextPath }/upload",  
	            type: "POST",  
	            data: formData,  
	            xhr: function(){ //获取ajaxSettings中的xhr对象，为它的upload属性绑定progress事件的处理函数  
	              
	                myXhr = $.ajaxSettings.xhr();  
	                if(myXhr.upload){ //检查upload属性是否存在  
	                    //绑定progress事件的回调函数  
	                    myXhr.upload.addEventListener('progress',progressHandlingFunction, false);   
	                }  
	                return myXhr; //xhr对象返回给jQuery使用  
	            },  
	            success: function(result){  
	                $("#result").html(result);  
	            },  
	            contentType: false, //必须false才会自动加上正确的Content-Type  
	            processData: false  //必须false才会避开jQuery对 formdata 的默认处理  
	        });  
	    }         
	  
	    //上传进度回调函数：  
	    function progressHandlingFunction(e) {  
	        if (e.lengthComputable) {  
	            $('#progress').attr({value : e.loaded, max : e.total}); //更新数据到进度条  
	            var percent = e.loaded/e.total*100;  
	            $('#progress').html(e.loaded + "/" + e.total+" bytes. " + percent.toFixed(2) + "%");  
	            $('#progress').css('width', percent.toFixed(2) + "%");
	        }  
	    }  
	</script>
</body>
</html>