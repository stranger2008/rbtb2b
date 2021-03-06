<html>
  <head>
    <title>举报结果</title>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
    <#include "/include/uploadInc.html">
	<script type="text/javascript" src="/js/jquery.alert.js"></script>
	<script type="text/javascript" src="/js/jNotify.jquery.js"></script>
	<script type="text/javascript" src="/js/category.js"></script>  
	<script type="text/javascript">
	 $(document).ready(function(){ 
	 var item = $("#sel").find("option:selected").text(); 
	    $("#jblx").html(item);
	 });
	
		function ImgShow(evt){
			var imgTag=(window.event)?event.srcElement:evt.target;
			var imgPath=imgTag.src.replace(".jpg",".jpg");//取得弹出的大图url
			var tagTop=Math.max(document.documentElement.scrollTop,document.body.scrollTop);
			var tag=document.createElement("div");
				tag.style.cssText="width:100%;height:"+Math.max(document.body.clientHeight,document.body.offsetHeight)+"px;position:absolute;background:white;top:0;filter: Alpha(Opacity=80);Opacity:0.8;";
				tag.ondblclick=function(){var clsOK=confirm("确定要取消图片显示吗?"); if(clsOK){closes();}};
			var tagImg=document.createElement("div");
				tagImg.style.cssText="font:12px /18px verdana;overflow:auto;text-align:center;position:absolute;width:200px;border:5px solid gray;background:gray;color:white;left:"+(parseInt(document.body.offsetWidth)/2-100)+"px;top:"+(document.documentElement.clientHeight/3+tagTop)+"px;"
				tagImg.innerHTML="<div style='padding:10px;background:#cccccc;border:1px solid white'><br /><br /><b style='color:#999999;font-weight:normal'>Image loading...</b><br /></div>";
			var closeTag=document.createElement("div");
				closeTag.style.cssText="display:none;position:absolute;left:10px;top:10px;background:red;border:1px solid white;yellow:white;filter:Alpha(Opacity=50);Opacity:0.5;cursor:pointer;";
				closeTag.innerHTML="<b>&nbsp;关闭&nbsp;</b>";
				closeTag.onclick=closes;
			document.body.appendChild(tag);
			document.body.appendChild(tagImg);
			var img=new Image();
				img.src=imgPath;
				img.style.cssText="border:1px solid #cccccc;filter: Alpha(Opacity=0);Opacity:0;";
				tagImg.oncontextmenu=function(){var clsOK=confirm("确定要取消图片显示吗?"); if(clsOK){closes();};return false};
			var barShow,imgTime;
			img.complete?ImgOK():img.onload=ImgOK;
			function ImgOK(){
				var Stop1=false,Stop2=false,temp=0;
				var tx=tagImg.offsetWidth,ty=tagImg.offsetHeight;
				var ix=img.width,iy=img.height;
				var sx=document.documentElement.clientWidth,sy=Math.min(document.documentElement.clientHeight,document.body.clientHeight/*opera*/);
				var xx=ix>sx?sx-50:ix+4,yy=iy>sy?sy-50:iy+3;
				var maxTime=setInterval(function(){
					temp+=35;
					if((tx+temp)<xx){
						tagImg.style.width=(tx+temp)+"px";
						tagImg.style.left=(sx-(tx+temp))/2+"px";
					}else{
						Stop1=true;
						tagImg.style.width=xx+"px";
						tagImg.style.left=(sx-xx)/2+"px";
					}
					if((ty+temp)<yy){
						tagImg.style.height=(ty+temp)+"px";
						tagImg.style.top=(tagTop+((sy-(ty+temp))/2))+"px";
					}else{
						Stop2=true;
						tagImg.style.height=yy+"px";
						tagImg.style.top=(tagTop+((sy-yy)/2))+"px";
					}
					if(Stop1&&Stop2){
						clearInterval(maxTime);
						tagImg.appendChild(img);
						temp=0;
						imgOPacity();
					}
				},1);
				function imgOPacity(){
					temp+=10;
					img.style.filter="alpha(opacity="+temp+")";
					img.style.opacity=temp/100;
					imgTime=setTimeout(imgOPacity,1)
					if(temp>100) clearTimeout(imgTime);
				}
				tagImg.innerHTML="";
				tagImg.appendChild(closeTag);
				if(ix>xx||iy>yy){
					img.alt="左键拖动,双击放大缩小";
					img.ondblclick=function (){
						if(tagImg.offsetWidth<img.offsetWidth||tagImg.offsetHeight<img.offsetHeight){
							img.style.width="auto";
							img.style.height="100%";
							img.alt="双击可以放大";
							img.onmousedown=null;
							closeTag.style.top="10px";
							closeTag.style.left="10px";
							tagImg.style.overflow="visible";
							tagImg.style.width=img.offsetWidth+"px";
							tagImg.style.left=((sx-img.offsetWidth)/2)+"px";
						}else{
							img.style.width=ix+"px";
							img.style.height=iy+"px";
							img.alt="双击可以缩小";
							img.onmousedown=dragDown;
							tagImg.style.overflow="auto";
							tagImg.style.width=xx+"px";
							tagImg.style.left=((sx-xx)/2)+"px";
						}
					}
					img.onmousedown=dragDown;
					tagImg.onmousemove=barHidden;
					tagImg.onmouseout=moveStop;
					document.onmouseup=moveStop;
				}else{
					tagImg.style.overflow="visible";
					tagImg.onmousemove=barHidden;
				}
			}
			function dragDown(a){
				img.style.cursor="pointer";
				var evts=a||window.event;
				var onx=evts.clientX,ony=evts.clientY;
				var sox=tagImg.scrollLeft,soy=tagImg.scrollTop;
				var sow=img.width+2-tagImg.clientWidth,soh=img.height+2-tagImg.clientHeight;
				var xxleft,yytop;
				document.onmousemove=function(e){
					var evt=e||window.event;
					xxleft=sox-(evt.clientX-onx)<0?"0":sox-(evt.clientX-onx)>sow?sow:sox-(evt.clientX-onx);
					yytop=soy-(evt.clientY-ony)<0?"0":soy-(evt.clientY-ony)>soh?soh:soy-(evt.clientY-ony);
					tagImg.scrollTop=yytop;
					tagImg.scrollLeft=xxleft;
					closeTag.style.top=(yytop+10)+"px";
					closeTag.style.left=(xxleft+10)+"px";
					return false;
				}
				return false;
			}
			function barHidden(){
				clearTimeout(barShow);
				if(closeTag.style.display=="none")closeTag.style.display="block";
				barShow=setTimeout(function(){closeTag.style.display="none";},2000);
			}
			function closes(){
				document.body.removeChild(tag);
				document.body.removeChild(tagImg);
				clearTimeout(barShow);
				clearTimeout(imgTime);
				document.onmouseup=null;
				tag=img=tagImg=closeTag=null;
			}
			function moveStop(){
				document.onmousemove=null;
				tagImg.onmousemove=barHidden;
				img.style.cursor="default";
			}
		}
	</script>
  </head>
  <body>
<div class="cont_main">
   	<@s.form action="" method="post" validate="true">
   	   <table width="100%" class="cont_title">
      <tr>
       <td>当前位置:会员信息>投诉举报>举报结果</td>
      </tr>
    </table>
        <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
           <tr>
             <td width="17%" valign="middle" class="left_td">举报人:</td>
              <td width="83%">
             	${memberuser.user_name?if_exists}
             </td>
           </tr>
           
		  <tr>
		    <td valign="middle" class="left_td">举报地址:</td>
		    <td>
		     <a href="${memberreport.link_url?if_exists}" target="_blank">${memberreport.link_url?if_exists}</a>
		    </td>
		  </tr>
		  
		    <tr>
             <td valign="middle" class="left_td">举报类型:</td>
             <td>
             	<span id="jblx"></span>
             </td>
           </tr>
           
           <tr class="table_name" style="display:none;">
		    <td valign="middle" class="left_td">举报类型<span class="mustfield">*</span></td>
		    <td>
		     <@s.select name="memberreport.report_type" id="sel" list="CommparaList" listValue="para_key" listKey="para_value" />
		     <@s.fielderror><@s.param>memberreport.report_type</@s.param></@s.fielderror>
		    </td>
		  </tr>
		  
		   <tr>
             <td valign="middle" class="left_td">图片证据:</td>
             <td>
                <div id="tpzj">
                  <ul>
                    ${img_path?if_exists}
                  </ul>
                </div>
             </td>
           </tr>
           
           <script type="text/javascript">//实例化
		var imgList1=document.getElementById("tpzj").getElementsByTagName("img");
		for(var i in imgList1){
			imgList1[i].onclick=ImgShow;
		}
	     </script> 
	     
	     <tr>
             <td valign="middle" class="left_td">举报内容:</td>
             <td>
				${memberreport.re_desc?if_exists}
             </td>
           </tr>
           
           <tr>
             <td valign="middle" class="left_td">处理结果:</td>
             <td>
				${memberreport.doresult?if_exists}
             </td>
           </tr>

           <tr>
           <td colspan="4" class="subbut">
           <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <input type="button" name="returnList" value="返回列表" class="sub" onclick="document.forms[0].action='/member_Memberreport_list.action';document.forms[0].submit();"/>
           </td>
           </tr>
         </table>
	  </@s.form>
</div>

</div>
<div class="clear"></div>
  
  </body>
</html>