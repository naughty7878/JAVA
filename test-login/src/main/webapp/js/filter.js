/* 
 * 说明：此处是调用ajax方法时，判断session是否过期 
 *  
 * 注：如果不想让ajax方法受这个影响，可以在ajax方法中写： global:false 
 * 如下： 
 * $.ajax({ 
 *    url:"test.html", 
 *    global:false    //不触发全局ajax事件 
 * }) 
 *  
 * **/  
  
$(document).ajaxComplete(function(event, xhr, settings) {   
    if(xhr.getResponseHeader("sessionstatus")=="timeOut"){   
        if(xhr.getResponseHeader("loginPath")){  
            alert("登录过期，请重新登录...");  
            window.location.replace(xhr.getResponseHeader("loginPath"));
        }else{   
            //alert("请求超时请重新登陆 !");   
        }   
    }   
}); 