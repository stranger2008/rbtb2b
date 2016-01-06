/**
 * 文字滚动
 * lee
 */
(function($){
    $.fn.extend({
        Scroll:function(opt,callback){
            if(!opt) var opt={};
            var _this=this.eq(0).find("ul:first");
            var lineH=_this.find("li:first").height(),
            line=opt.line?parseInt(opt.line,10):parseInt(this.height()/lineH,10),
            speed=opt.speed?parseInt(opt.speed,10):500,
            timer=opt.timer?parseInt(opt.timer,10):3000;
            if(line==0) line=1;
            var upHeight=0-line*lineH;
            scrollUp=function(){
                _this.animate({
                    marginTop:upHeight
                },speed,function(){
                    for(i=1;i<=line;i++){
                        _this.find("li:first").appendTo(_this);
                    }
                    _this.css({marginTop:0});
                });
            }
            _this.hover(function(){
                clearInterval(timerID);
            },function(){
                timerID=setInterval("scrollUp()",timer);
            }).mouseout();
        }
    })
})(jQuery);

function scroll_supply_left(){
   $(function(){
   $('#div_left li').eq(0).fadeOut('slow',function(){
	  //alert($(this).clone().html());
	  //克隆:不用克隆的话，remove()就没了。
      $(this).clone().appendTo($(this).parent()).fadeIn('slow');
         $(this).remove();
      });
   });
}
function scroll_supply_right(){
   $(function(){
   $('#div_right li').eq(0).fadeOut('slow',function(){
	  //alert($(this).clone().html());
	  //克隆:不用克隆的话，remove()就没了。
      $(this).clone().appendTo($(this).parent()).fadeIn('slow');
         $(this).remove();
      });
   });
}
//开始移动
setInterval('scroll_supply_left()',2000);
setInterval('scroll_supply_right()',2000);






