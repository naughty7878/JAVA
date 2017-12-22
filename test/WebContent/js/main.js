$(function() {

//导航
$(".nav").slide({
	type:"menu", //效果类型
	titCell:".m", // 鼠标触发对象
	targetCell:".sub", // 效果对象，必须被titCell包含
	effect:"slideDown",//下拉效果
	delayTime:0, // 效果时间
	triggerTime:0, //鼠标延迟触发时间
	returnDefault:true  //返回默认状态
});

	$(".diagram").each(function(){
		var t=$(this);
		var color = t.parent().find('.color').val(),
		 value = t.parent().find('.percent').val(),
		 text = t.parent().find('.text').text();
		
		var r = Raphael($(this).attr("id"), 70, 70),
			rad = 28,//圆圈大小
			defaultText = t.parent().find(".percent").val()+"%",
			speed = 0;
		var title = r.text(35, 35, defaultText).attr({font: '15px Arial',fill: '#00aeff'}).toFront();
		
		r.customAttributes.arc = function(value, color, rad){
			var v = 3.6*value,
				alpha = v == 360 ? 359.99 : v,
				random = Math.floor((Math.random()*(90-90+1))+90), //角度
				a = (random-alpha) * Math.PI/180,
				b = random * Math.PI/180,
				sx = 35 + rad * Math.cos(b),
				sy = 35 - rad * Math.sin(b),
				x = 35 + rad * Math.cos(a),
				y = 35 - rad * Math.sin(a),
				path = [['M', sx, sy], ['A', rad, rad, 0, +(alpha > 180), 1, x, y]];
			return { path: path, stroke: color }
		}
		
		var x = r.path().attr({ arc: ["100", "#ccc", rad], 'stroke-width': 4 });//圆圈线条粗细
		var z = r.path().attr({ arc: [value, color, rad], 'stroke-width': 4 });
		
//		setTimeout(function() {
					z.animate({
						stroke: "#00aeff",
						arc: [value, color, rad]
					}, 300, ">")
//				}, 1000)
		t.parent().find("text").text(value + '%')
	});	
});


	
//回到顶部
function gotoTop(acceleration,stime) {
   acceleration = acceleration || 0.1;
   stime = stime || 10;
   var x1 = 0,y1 = 0,x2 = 0,y2 = 0,x3 = 0,y3 = 0; 
   if (document.documentElement) {
       x1 = document.documentElement.scrollLeft || 0;
       y1 = document.documentElement.scrollTop || 0;
   }
   if (document.body) {
       x2 = document.body.scrollLeft || 0;
       y2 = document.body.scrollTop || 0;
   }
   var x3 = window.scrollX || 0;
   var y3 = window.scrollY || 0;
 
   var x = Math.max(x1, Math.max(x2, x3));
   var y = Math.max(y1, Math.max(y2, y3));
 
   var speeding = 1 + acceleration;
   window.scrollTo(Math.floor(x / speeding), Math.floor(y / speeding));
 
   if(x > 0 || y > 0) {
       var run = "gotoTop(" + acceleration + ", " + stime + ")";
       window.setTimeout(run, stime);
   }
}