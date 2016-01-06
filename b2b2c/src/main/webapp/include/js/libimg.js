function $f(Id){if(typeof Id=="string")return document.getElementById(Id);return Id;}
function $s(Id,tag){return $f(Id).getElementsByTagName(tag);}
function $abs(o){var p=new Object();p.x=o.offsetLeft;p.y=o.offsetTop;while(o=o.offsetParent){p.x+=o.offsetLeft;p.y+=o.offsetTop;}return p;}
/*
***缩略图片尺寸已经设好，为120x90，无法修改，并且注意大图和小图的名称就差“_s”，小图多“_s”。如大图为“12345.jpg”，则小图格式为“12345_s.jpg”
***一些要显示的信息，需要在小图片生成时自定义，如图片标题，图片上传时间等
参数介绍
     parentId：图片容器对象的ID，高度已经设置好，120px
     imgParentId：滚动图片容器对象的ID
     cfgDisplay：显示配置  {margin：图片间距,opacity：未获得焦点的图片透明度，为0-1间的数字,dpNum：每次显示的图片数量,totalID：显示总图片数量的容器ID}
     cfgArrowCopy：上下移动小图片的对象和拷贝对象的id，绑定事件用{pre:'',next:'',copy:''}
     cfgUpdate：为josn对象，为显示大图的属性和需要从小图片的自定义属性中更新显示内容的数组对象，其中数组对象内容为json对象，id对象为要更新的对象id，是设置innerHTML属性的，如下
       {bigImgId:'显示大图img对象的id',nowUrl:'显示当前大图片路径的a对象Id',dps:[{id:'显示对象的id',attr:'小图自定义属性的名称'},{id:'显示对象的id',attr:'小图自定义属性的名称',isPos:'布尔变量值，指示是否为图片位置'}...]}
     cfgImg：传递上下2张图片路径和鼠标在大图上移动时显示的光标对象路径{pre:'图片左边',next:'图片右边',cnext:'光标下',cpre:'光标上',cffnext:'光标Firefox下',cffpre:'光标火狐上',wait:'等待图片地址'}
     cfgLans：提示信息配置 {head:'滚动到头的提示信息',end:'滚动到尾的提示信息',first:'已经是第一张图片的提示',last:'已经是最后一张图片的提示',copy:['IE浏览器提示信息','非IE浏览器提示信息'],next:'下一张提示信息',pre:'上一张提示信息'}
*/
var ShowboImgLib=function(parentId,imgParentId,cfgDisplay,cfgArrowCopy,cfgUpdate,cfgImg,cfgLans){
  var me=this;//闭包对象
  //输出样式
  document.write('<style type="text/css">\n'
    +'#'+parentId+'{height:145px;overflow:hidden;}\n'
    +'#'+parentId+' div{float:left;width:1000px;}\n'
    +'#'+parentId+' div.left,#'+parentId+' div.right{height:110px;width:20px;background:url('+cfgImg.pre+') no-repeat left center;cursor:pointer;}\n'
    +'#'+parentId+' div.right{background-image:url('+cfgImg.next+');height:110px;width:20px;background-position:right center;}\n'
    +'#'+parentId+' #'+imgParentId+'{overflow:hidden;\height:120px;}\n'
    +'#'+parentId+' #'+imgParentId+' img{width:100px;height:95px;border:none;margin:4px '+cfgDisplay.margin+'px;cursor:pointer;filter:alpha(opacity='+(cfgDisplay.opacity*100)+');-moz-opacity:'+cfgDisplay.opacity+';opacity:'+cfgDisplay.opacity+';}\n'
    +'#'+parentId+' #'+imgParentId+' img.focus{filter:alpha(opacity=100);-moz-opacity:1;opacity:1;}\n'
    +'#'+parentId+' .summery{clear:both;text-align:right;line-height:20px;height:20px;width:100%;font-size:12px;}\n'
    +'#'+parentId+' .summery a{color:#cc6600;text-decoration:underline;display:inline;}\n'
    +'#'+parentId+' .summery a:hover{color:#600;text-decoration:none;}\n'
    +'#'+parentId+' .summery b{color:Red;}\n'
    +'.mnext{cursor:url('+cfgImg.cffnext+'),url('+cfgImg.cnext+'),auto;}\n'
    +'.mpre{cursor:url('+cfgImg.cffpre+'),url('+cfgImg.cpre+'),auto;}\n'
    +'#ShowboImgWait{display:none;position:absolute;z-index:100;left:0px;top:0px;}\n'
    +'</style>');
  this.animate=function(){
    this.scrollLeft=(this.scrollLeft===false?this.nowScrollLeft:this.scrollLeft)+this.step;
    if((this.step<0&&this.scrollLeft>this.toScrollLeft)||(this.step>0&&this.scrollLeft<this.toScrollLeft))this.animateTimer=setTimeout(function(){me.animate();},this.delay);
    else {this.nowScrollLeft=this.toScrollLeft;this.scrollLeft=false;}
    this.imgParent.scrollLeft=this.scrollLeft===false?this.nowScrollLeft:this.scrollLeft;
  }
  //方法
  this.move=function(isLeft){
    if(isLeft){
      if(this.nowScrollLeft==0){alert(cfgLans.head);return false;}
      else{this.toScrollLeft=this.nowScrollLeft-this.itemWidth;this.step=-Math.abs(this.step);}
    }
    else{
      if(this.nowScrollLeft>=this.demoWidth-this.itemWidth*cfgDisplay.dpNum){alert(cfgLans.end);return false;}
      else{this.toScrollLeft=this.nowScrollLeft+this.itemWidth;this.step=Math.abs(this.step);}
    }
    this.animate();
  }
  this.Copy=function(){if(window.clipboardData){window.clipboardData.setData('text',me.img.src);alert(cfgLans.copy[0]);}else alert(cfgLans.copy[1]);;return false;}
  this.CheckPos=function(e,o){
     var x=e.offsetX||e.layerX;
  	 if(x>=(o.offsetWidth/2)){
    if(!o.className!='mnext')o.className="mnext";
   	  else o.style.cursor="n-resize";
   	  if(o.title!=cfgLans.next)o.title=cfgLans.next;
 	}
 	else{
 	  if(!o.className!='mpre')o.className="mpre";
 	  else o.style.cursor="pointer";
 	  if(o.title!=cfgLans.pre)o.title=cfgLans.pre;
 	}
  }
  this.wait=function(isHide){
    if(isHide)this.waitImg.style.display='none';
    else{
      var p=$abs(this.img);
      this.waitImg.style.display='block';
      this.waitImg.style.left=(this.img.offsetWidth-this.waitImg.offsetWidth)/2+p.x+'px';
      this.waitImg.style.top=(this.img.offsetHeight-this.waitImg.offsetHeight)/2+p.y+'px';
    }
  }
  this.update=function(small){
    this.imgs[this.focusId].className='';//去掉原焦点图片样式
    this.wait();//显示等待加载图片图像
    var asyImg=new Image();//一步加载图片
    asyImg.onload=function(){me.img.src=this.src;me.wait(true);}
    asyImg.src=this.nowA.href=small.src.toLowerCase().replace('_s.','.');//更新的大图地址和大图链接对象
    var pos=parseInt(small.getAttribute('pos'));
    this.img.setAttribute('pos',pos);
    this.focusId=pos;
    small.className='focus';
    if(cfgUpdate.dps)for(var i=0;i<cfgUpdate.dps.length;i++)
      //注意这里设置显示对象时需要判断下是否为图片位置，如果是要+1，因为pos属性从0开始
      $f(cfgUpdate.dps[i].id).innerHTML=cfgUpdate.dps[i].isPos?parseInt(small.getAttribute(cfgUpdate.dps[i].attr))+1:small.getAttribute(cfgUpdate.dps[i].attr);//跟新显示对象内容
    return pos;
  }
  this.setCT=function(o,isNext){
     var pos=-1,animate=false;
     if(typeof isNext=='boolean'){
       pos=parseInt(o.getAttribute('pos'));
       if(isNext){
         if(pos+1>=this.itemNum){alert(cfgLans.last);return false;}
         else o=this.imgs[pos+1];
       }
       else{
         if(pos<1){alert(cfgLans.first);return false;}
         else o=this.imgs[pos-1];
       }
     }
     pos=this.update(o);
     pos++;
     var half=Math.ceil(cfgDisplay.dpNum/2);     
     if(pos<=half)this.toScrollLeft=0;
     else if(pos>this.itemNum-half)this.toScrollLeft=(this.itemNum-cfgDisplay.dpNum)*this.itemWidth;
     else this.toScrollLeft=(pos-half)*this.itemWidth;
     if(this.toScrollLeft!=this.nowScrollLeft){this.step=(this.toScrollLeft<this.nowScrollLeft?-1:1)*Math.abs(this.step);animate=true;}
     if(this.scrollLeft!==false){this.step=(this.toScrollLeft<this.scrollLeft?-1:1)*Math.abs(this.step);animate=true;}
     
     if(animate){clearTimeout(this.animateTimer);this.animate();}
     else this.imgParent.scrollLeft=this.nowScrollLeft;
  }
  this.init=function(delay,step){//初始化对象，注意要放到图片父亲容器的下面调用此函数，否则html dom对象未生成导致出错。或者放到window.onload事件中
    document.write('<img src="'+cfgImg.wait+'" id="ShowboImgWait"/>');
    if(typeof delay=='undefined'||!/^\d+$/.test(delay.toString()))this.delay=10;//默认动画延时10ms
    else this.delay=delay;

    if(typeof step=='undefined'||!/^\d+$/.test(step.toString()))this.step=10;//默认每次移动10px
    else this.step=step;
    this.parent=$f(parentId);
    this.imgParent=$f(imgParentId);
    this.img=$f(cfgUpdate.bigImgId);
    this.total=$f(cfgDisplay.totalID);
    this.nowA=$f(cfgUpdate.nowUrl);
    this.waitImg=$f('ShowboImgWait');
    
    this.img.setAttribute('pos','0');//大图片对应小图的位置，默认为0
    this.img.onmousemove=function(e){me.CheckPos(e||window.event,this);}//鼠标在大图上移动根据相对位置设置大图title及光标形状
    this.img.onclick=function(){me.setCT(this,this.className=='mnext');}//对大图片点击是判断是下一张还是下一张
    this.img.style.position='relative';//注意大图像的位置要设置为relative，这样在Firefox下才能使用offsetX获取相对位置
    this.itemWidth=145+cfgDisplay.margin*2;//要注意乘以2，因为有两边
    this.imgs=$s(this.imgParent,'img');
    this.itemNum=this.imgs.length;//存储多少张图片
    this.total.innerHTML=this.itemNum;//获取图片总数
    this.demoWidth=this.itemNum*this.itemWidth;//滚动总长度
    this.focusId=0;//默认第一张获取焦点
    this.nowScrollLeft=0;//默认滚动在左边
    this.toScrollLeft=0;//要转换到的scrollLeft值
    this.scrollLeft=false;//动画中的临时变量
    this.animateTimer=false;//动画计时器
    this.imgParent.firstChild.style.width=this.demoWidth+'px';//设置滚动子容器的长度
    this.imgParent.style.width=this.itemWidth*cfgDisplay.dpNum+'px';//设置滚动容器的宽，以便实现滚动
    this.parent.style.width=this.itemWidth*cfgDisplay.dpNum+40+'px';//设置滚动父容器的宽，加上左右两边箭头的宽40
    for(var i=0;i<this.imgs.length;i++){this.imgs[i].onclick=function(){me.setCT(this);};this.imgs[i].setAttribute('pos',i);}//小图片点击事件处理
    //上下及拷贝地址对象事件绑定
    $f(cfgArrowCopy.copy).onclick=function(){return me.Copy();}
    $f(cfgArrowCopy.pre).onclick=function(){me.move(true);}
    $f(cfgArrowCopy.next).onclick=function(){me.move();}
    var nowPicsrc=/\/([\d]+)\.(gif|bmp|jpg|png)/i.exec(this.img.src);//获取大图的文件名称，然后移动小图位置
    if(nowPicsrc){
      nowPicsrc=nowPicsrc[0].replace('.','_s.').substring(1);
      for(var i=0;i<this.itemNum;i++)if(this.imgs[i].src.indexOf(nowPicsrc)!=-1){
        this.setCT(this.imgs[i]);break;
      }
    }
  }
}