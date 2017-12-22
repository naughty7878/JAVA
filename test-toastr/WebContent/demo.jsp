<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/toastr/toastr.css" />

<script src="js/jquery-2.1.1.min.js"></script>

<script src="js/toastr/toastr.min.js"></script>


</head>
<body>
	<button id="showtoast">show info toast</button>
	<br>
	<button id="showtoastsuccess">show success toast</button>
	<br>
	<button id="showtoasterror">show error toast</button>
	<br>
	<button id="showtoastwarning">show warning toast</button>
	<br>
	<button id="cleartoasts">clear toast</button>
	<br>
	<button id="removetoasts">remove toast</button>
	<br>


</body>

<script type="text/javascript">
	$(function() {

		//设置显示配置
		var messageOpts = {
			"closeButton" : true,
			"debug" : false,
			"positionClass" : "toast-bottom-right",
			"onclick" : null,
			"showDuration" : "300",
			"hideDuration" : "1000",
			"timeOut" : "5000",
			"extendedTimeOut" : "1000",
			"showEasing" : "swing",
			"hideEasing" : "linear",
			"showMethod" : "fadeIn",
			"hideMethod" : "fadeOut"
		};
		toastr.options = messageOpts;

		$('#showtoast').click(function() {

			//调用方法1
			toastr.info('内容1');

			//调用方法2
			toastr.info('内容2', '标题2');

			//调用方法3
			//toastr['info']('内容3', '标题3');

			//调用方法4
			//toastr.info('内容4', '标题4',messageOpts);

		});

		$('#showtoastsuccess').click(function() {

			toastr.info('success内容', 'success标题');

		});

		$('#showtoasterror').click(function() {

			toastr.info('内容error', '标题error');

		});

		$('#showtoastwarning').click(function() {

			toastr.info('内容warning', '标题warning');
		});

		$('#cleartoasts').click(function() {
			toastr.clear();
		});

		$('#removetoasts').click(function() {
			toastr.remove();
		});

	})
</script>
</html>