<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传 原生ajax上传</title>
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
		 上传文件1: <input type="file" name="file1" id="file"><br /> 
		 <div class='container'>
		 	<span id="progress"></span>
		 </div>
	</form>
	<br>
	<button onclick="fileSelected()">文件信息</button><button onclick="uploadFile()">确认上传</button>
	<div id="info">
		<div id="fileName"></div>
		<div id="fileSize"></div>
		<div id="fileType"></div>
	</div>
	<div id="result"></div>
	<script>
		function fileSelected() {
            var file = document.getElementById('file').files[0];
            if (file) {
                var fileSize = 0;
                if (file.size > 1024 * 1024)
                    fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
                else
                    fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';
                document.getElementById('fileName').innerHTML = 'Name: ' + file.name;
                document.getElementById('fileSize').innerHTML = 'Size: ' + fileSize;
                document.getElementById('fileType').innerHTML = 'Type: ' + file.type;
            }
        }
        function uploadFile() {
            var fd = new FormData();
            fd.append("file", document.getElementById('file').files[0]);
            var xhr = new XMLHttpRequest();
            xhr.upload.addEventListener("progress", uploadProgress, false);
            xhr.addEventListener("load", uploadComplete, false);
            xhr.addEventListener("error", uploadFailed, false);
            xhr.addEventListener("abort", uploadCanceled, false);
            xhr.open("POST", "${pageContext.request.contextPath }/upload");//修改成自己的接口
            xhr.send(fd);
        }

        function uploadProgress(evt) {
            if (evt.lengthComputable) {
                var percent = Math.round(evt.loaded * 100 / evt.total);
                
                document.getElementById('progress').innerHTML = percent.toFixed(2) + '%';
                document.getElementById('progress').style.width = percent.toFixed(2) + '%';
            }
            else {
                document.getElementById('progress').innerHTML = 'unable to compute';
            }
        }
        function uploadComplete(evt) {
            /* 服务器端返回响应时候触发event事件*/
            document.getElementById('result').innerHTML = evt.target.responseText;
        }
        function uploadFailed(evt) {
            alert("There was an error attempting to upload the file.");
        }
        function uploadCanceled(evt) {
            alert("The upload has been canceled by the user or the browser dropped the connection.");
        }
    </script>
	
</body>
</html>