<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<meta name ="keywords" content="${memberconfig.keywords?if_exists}">
<meta name="description" content="${memberconfig.site_desc?if_exists}">  
<title>联系我们—${memberconfig.web_title?if_exists}</title>
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/public.css" rel="stylesheet" type="text/css">
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/index.css" rel="stylesheet" type="text/css">
<STYLE TYPESTYLETYPE="text/css"> 
.gels-extad{
 height:10px;
}
</STYLE> 
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
   
   function doCheckSubmit(){
   var code=checkcode();
      if(code==1){
          return true;
       }else if(code==0){
         alert('验证码错误!');
         return false;
       }
       else if(code==2){ 
          alert('请输出验证码!');
          return false;
       }else{
          return false;
       }
   }
   
   function changeValidateCode(obj) {   
        //获取当前的时间作为参数，无具体意义   
        var timenow = new Date().getTime();   
        //每次请求需要一个不同的参数，否则可能会返回同样的验证码   
        //这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。   
        obj.src="/imgrand.action?d="+timenow;
    } 
    
    function checkcode(){
     var c_code=$("#rands").val();
     var state=0;
     $.ajax({  	 
          type: "post",    	     
          url: "/insertfrom.action?c_code="+c_code+"&ajaxRequestRandom="+Math.random(),       
          datatype:"json",
          async:false,
          success: function(data){           
             state=data;
          }            
       });   
      return state; 
   }
   
</script>
<script src="http://ditu.google.cn/maps?file=api&amp;v=2.x&amp;key=" type="text/javascript"></script>
<script type="text/javascript">
    var map = null;
    var geocoder = null;
    var mapReady = false;
    var mapResult = [];
    function initialize() {
        if (GBrowserIsCompatible()) {
    
        //搜索结果元素
        var resultElem = document.getElementById("googleResult");
        //地图容器元素
        var mapElem = document.getElementById("googleMap");
        
        // 指定实例化地图选项    
        var options = {
            //size : new GSize (338,353),
            googleBarOptions : {
                onSearchCompleteCallback : function(searcher) {
                    //设置结果
                    mapResult = searcher.results;
                    //统计结果数
                    //document.getElementById("sum").innerHTML = searcher.results.length;
                    //搜索完成时候自动居中第一个结果
                    setTimeout(function() {autoCenter(0);},1000);
                },
                resultList : resultElem ,
                maxCursorPages : 5 ,
                suppressZoomToBounds : true
            }
        };
          
        map = new GMap2(mapElem, options);
        
        // 平移及缩放控件（左上角）
        map.addControl(new GLargeMapControl());
        //比例尺控件（左下角）
        map.addControl(new GScaleControl());
        
        //创建缩略图控件（右下角）
        var overviewMap = new GOverviewMapControl();
        map.addControl(overviewMap);
        //最小化隐藏缩略图控件
        setTimeout(function (){overviewMap.hide();},1000);
        
        geocoder = new GClientGeocoder();
        
        //为地图启用集成搜索控件GoogleBar(此句顺序位置不可修改)
        map.enableGoogleBar();
        
        //自动居中结果点
        window.autoCenter = function (resultIndex){
            var result = mapResult[resultIndex];
            if( result ) {
                setTimeout(function() {
                        //构建以经度和纬度表示的地理坐标点
                        var point = new GLatLng(result.lat,result.lng);
                        //设置中心点
                        map.setCenter(point);//map.panTo(point);
                        //以动画方式平移指定的距离(左：40px,下：100px)
                        map.panBy(new GSize(-40,100));
                    },
                    500
                );
            }
        }
        
        //点击搜索结果事件
        resultElem.onclick = function (e){
            e = window.event || e;
            var target = e.target || e.srcElement;
            var trElem = target;
            //获取冒泡事件的TR层节点
            while(trElem && trElem.tagName!="TR")
            {
                if(trElem.tagName=="TABLE") break;
                trElem = trElem.parentNode;
            }
            if (trElem!=null)
            {
                //获取点击的结果索引
                var resultIndex = trElem.id.substring(trElem.id.length-1);
                //自动居中
                autoCenter(resultIndex);
            }
        }
    
        //初始化位置
        var address =  $("#company_address").val();
        if (geocoder) {
            geocoder.getLatLng(
              address,
              function(point) {
                if (point) {
                  map.setCenter(point, 16);
                  var marker = new GMarker(point);
                  map.addOverlay(marker);
                  marker.openInfoWindowHtml(address);
                  setTimeout(function (){map.panBy(new GSize(-10,120));},2500);
                }
              }
            );
        }
        
        //获取搜索控件
        var searchBar = mapElem.lastChild;
        //清空样式
        searchBar.style.cssText = "";
        //改变搜索控件元素位置
        document.getElementById("googleSearch").appendChild(searchBar);
        
      }
    }
    
    window.onload = function(){
        initialize();
        document.body.onunload = GUnload;
        mapReady = true;
    }
</script>
</head>

<input type="hidden" id="company_address" value="${(member.address)?if_exists}"/>
<!--头部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/top.ftl">
<!--头部结束-->
<div class="position"><P><span class="f_left wz">当前位置: <a href="/showroom/${user_name?if_exists}" style="font-size:14px;">首页</a> > 联系我们 </span> <span class="data f_right">今天是 ${systime?if_exists}</span></span></P></div>

<div class="w960">
  <!--左部开始-->
  <#include "/WEB-INF/template/company/${temp_loc?if_exists}/left.ftl">
  <!--左部结束-->
    <div class="r_body f_right">
      <div class="right_main">
            <div class="r_title"><h2><span class="title_font f_left">联系方式</span></h2></div> 
            <div class="other_main">
              <ul class="files">
                <li><strong>联 系 人：</strong>${(member.contact_depart)?if_exists} ${(member.contact_job)?if_exists} ${(member.contact_name)?if_exists}&nbsp;${(member.contact_sex)?if_exists}
              		<#if (member.contact_qq)?if_exists != ''>
              			<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${(member.contact_qq)?if_exists}&site=qq&menu=yes">
                        	<img border="0" src="/templets/${templateStyle?if_exists}/images/sm_qq.gif"/>
                    	</a>
                    </#if>
                    <#if (member.contact_msn)?if_exists != ''>
	                    <a href="msnim:add?contact=${(member.contact_msn)?if_exists}" target="_blank">
	                        <img src="/templets/${templateStyle?if_exists}/images/sm_msn.gif"/>
	                    </a>
                    </#if>
              	</li>
              	<#if member.phone?if_exists>
              	<li>             		
              		<span><strong>公司电话：</strong>${(member.phone)?if_exists}</span>             		
               	</li>
               	</#if>             		
              	<#if member.contact_cellphone?if_exists>                          		
              	<li>
              		<span><strong>手机号码：</strong>${(member.contact_cellphone)?if_exists}</span>
              	</li>
              	</#if>
				<#if member.email?if_exists>
	             <li>	              		
	              	<span><strong>电子邮件：</strong>${(member.email)?if_exists}</span>
	             </li>		
	            </#if>
	             <#if member.fax?if_exists>
	              	<li>
	              	<span><strong>公司传真：</strong>${(member.fax)?if_exists}</span>
	              	</li>
	             </#if>
              	<#if member.address?if_exists>
              	<li><strong>联系地址：</strong>${(member.address)?if_exists}</li>
              	</#if>
              	<#if member.website?if_exists>
              	<li><strong>公司网址：</strong><a href="${(member.website)?if_exists}" target="_blank">${(member.website)?if_exists}</a></li> 
              	</#if>
              </ul> 
            <#if (member.address)?if_exists?if_exists!=''>
            <div style="padding-left:20px;">
  			<div id="googleSearch"></div>
            <div id="googleResult" style="width:700px;"></div>
            <div id="googleMap" style="width:700px; height:400px; border:solid 1px #ccc">loading...</div>
            </div>
            </#if>
            </div>
           
       </div>
       <div class="clear"></div>
       <@s.form action="/companyleave.action?user_name=${user_name?if_exists}" method="post" validate="true" onsubmit="return doCheckSubmit();">
       <div class="right_main padding_10">
            <div class="r_title"><h2><span class="title_font f_left">给我留言</span></h2></div> 
            <div class="other_main">
              <ul class="files">
              	<li><b>留言标题<font color='red'>*</font></b>
              	<@s.textfield name="title" cssStyle="width:200px;margin-left:10px;" maxLength="100"/>
              	<font color='red'><@s.fielderror><@s.param>title</@s.param></@s.fielderror></font></li>
              	<li>               
	                <table>
	                	<tr>
	                		<td valign='top'><b style='margin-left:-3px;'>留言内容<font color='red'>*</font></b></td>
	                		<td style='padding-left:3px;'><@s.textarea name="content" cssStyle="width:400px;height:120px;margin-left:10px;"/>
	                			<font color='red'><@s.fielderror><@s.param>content</@s.param></@s.fielderror></font>
	                		</td>
	                	</tr>                
	                </table>
                </li>
                <li><b>验证码<font color='red'>*</font></b>
                <input type="text" maxlength="4" name="commentrand" id="rands" style="width:50px;margin-left:22px;"/> 
                <img src="/imgrand.action" style="vertical-align:middle;" onclick="changeValidateCode(this)"/>
                </li>
                <li><input type="submit" value="提交"/><font color='red'><@s.fielderror><@s.param>submit</@s.param></@s.fielderror></font></li>
              </ul>  
           </div>
       </div>
       </@s.form> 
     </div>
      <!--右部结束--> 
</div>
<!--底部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/bottom.ftl">
<!--底部结束-->
</body>
</html>
