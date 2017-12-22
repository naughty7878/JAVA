<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="css/toastr/toastr.css" />
	
	<script src="js/toastr/toastr.js"></script>
	<script src="js/jquery-1.9.1.min.js"></script>
		<button  id="showtoast">show toast</button>
</head>
<body>
	<script type="text/javascript">
	$(function () {  
  
        $('#showtoast').click(function () {  
  
            toastr.options = {  
  
                closeButton: false,  
                debug: false,  
                progressBar: false,  
                positionClass: "toast-top-right",  
                onclick: null,  
                showDuration: "300",  
                hideDuration: "1000",  
                timeOut: "5000",  
                extendedTimeOut: "1000",  
                showEasing: "swing",  
                hideEasing: "linear",  
                showMethod: "fadeIn",  
                hideMethod: "fadeOut"  
            };  
  
            toastr.info('123', 'title');  
           // var $toast = toastr['error']('123', 'title');  
  
        });  
  
    })  
    </script>
</body>
</html>