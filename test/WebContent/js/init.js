var o = {
	init: function(){
		this.diagram();
	},
	random: function(l, u){
		return Math.floor((Math.random()*(u-l+1))+l);
	},
	diagram: function(){
		var r = Raphael('diagram', 70, 70),
			rad = 23,//大小
			defaultText = $('#diagram').parent().find(".percent").val()+"%",
			speed = 0;

		var title = r.text(35, 35, defaultText).attr({
			font: '15px Arial',
			fill: '#00aeff'
		}).toFront();
		
		r.customAttributes.arc = function(value, color, rad){
			var v = 3.6*value,
				alpha = v == 360 ? 359.99 : v,
				random = o.random(90, 90), //角度
				a = (random-alpha) * Math.PI/180,
				b = random * Math.PI/180,
				sx = 35 + rad * Math.cos(b),
				sy = 35 - rad * Math.sin(b),
				x = 35 + rad * Math.cos(a),
				y = 35 - rad * Math.sin(a),
				path = [['M', sx, sy], ['A', rad, rad, 0, +(alpha > 180), 1, x, y]];
			return { path: path, stroke: color }
		}
		
		$('#diagram').parent().parent().find('.arc').each(function(i){
			var t = $(this), 
				color = t.find('.color').val(),
				value = t.find('.percent').val(),
				text = t.find('.text').text();
			
			rad += 0;
			var x = r.path().attr({ arc: ["100", "#ccc", rad], 'stroke-width': 3 });
			var z = r.path().attr({ arc: [value, color, rad], 'stroke-width': 3 });
			t.find("text").text(value + '%')
		});
		
	}
}
$(function(){ o.init(); });
