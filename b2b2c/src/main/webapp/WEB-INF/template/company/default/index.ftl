<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>公司首页—${memberconfig.web_title?if_exists}</title>
<meta name ="keywords" content="${memberconfig.keywords?if_exists}">
<meta name="description" content="${memberconfig.site_desc?if_exists}">  
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/public.css" rel="stylesheet" type="text/css">
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/index.css" rel="stylesheet" type="text/css">

<LINK rel=stylesheet type=text/css href="/templets/company/kefu/css/kefu.css">
<SCRIPT type=text/javascript src="/templets/company/kefu/js/jquery.js"></SCRIPT>
<SCRIPT type=text/javascript src="/templets/company/kefu/js/kefu.js"></SCRIPT>

</head>

<#include "/WEB-INF/template/company/${temp_loc?if_exists}/top.ftl">

<div class="position"><span class="f_left wz">当前位置: <a href="###;"style="font-size:14px;">首页</a> >> 欢迎光临</span> <span class="data f_right">今天是 ${systime?if_exists}</span></span></div>

<div class="w960">

 <#include "/WEB-INF/template/company/${temp_loc?if_exists}/left.ftl">
  
  <div class="r_body f_right">
  <!--首页主栏绑定-->
      <#list mainbarList as mainbar>
      <#if mainbar_index == 0>
       <div class="right_main">
       <#else>
       <div class="right_main padding_10">
       </#if>
        <div class="r_title">
          <h2><span class="title_font f_left">${mainbar.ch_name?if_exists}</span>
          <!--绑定更多超链接-->
          <#if mainbar.ch_code?if_exists=='cptj'>
          <a href="/showroom/${user_name?if_exists}/product.html" class="more f_right">更多>></a></h2>
          </#if>
          <#if mainbar.ch_code?if_exists=='zgsjj'>
          <a href="/showroom/${user_name?if_exists}/profile.html" class="more f_right">更多>></a></h2>
          </#if>
          <#if mainbar.ch_code?if_exists=='zxgy'>
          <a href="/showroom/${user_name?if_exists}/supply.html" class="more f_right">更多>></a></h2>
          </#if>
          <#if mainbar.ch_code?if_exists=='zgsxc'>
          <a href="/showroom/${user_name?if_exists}/gallery.html" class="more f_right">更多>></a></h2>
          </#if>
        </div> 
        <!--绑定产品推荐-->
        <#if mainbar.ch_code?if_exists=='cptj'>
        <div class="Recommend_c padding_10">
           <ul class="pic">
              <#list recommendProductList as recomproduct>
              <li>
              
              		<#assign pro_img_path = (recomproduct.img_path)?if_exists>
           			<#if pro_img_path == ''>
					   <#assign pro_img_path = cfg_nopic>
					</#if>
              
	              <div  class="divMiddle" style="width:156px;height:126px;line-height:126px;">
		              <a href="/showroom/${user_name?if_exists}/product/detail_${recomproduct.product_id?if_exists}.html"  class="AMiddle"  style="width:156px;">
		              <img src="${pro_img_path}" width="146px" height="116px" ${stack.findValue("@com.rbt.function.ChannelFuc@getSetImgSize('${pro_img_path}','146','116')")} class="ImgMiddle"/>
		              </a>
	              </div>              
	              <p style="margin-top:6px;">
	              	<a href="/showroom/${user_name?if_exists}/product/detail_${recomproduct.product_id?if_exists}.html">
		              <#if recomproduct.title?length lt 8>
		              		${recomproduct.title?if_exists}
		              <#else>
		              		${recomproduct.title[0..7]}...
		              </#if>
	              	</a>
	              </p>
              </li>
              </#list>
              <#if recommendProductList.size()==0><li>暂无数据</li></#if>
           </ul>
           <div class="clear"></div>
       </div>
       </#if>
        <!--绑定公司简介-->
       <#if mainbar.ch_code?if_exists=='zgsjj'>
           <div class="gsjj">
           <style>
           		p{padding-left:10px;}
           	</style>
            <p class="jj_p">
	            <#if chinaDesc?if_exists != ''>
		            <#if chinaDesc?length lt 301>
		            ${chinaDesc?if_exists}
		            <#else>
		            ${chinaDesc[0..300]}...
		            </#if>
	            </#if>
	            <#if chinaDesc?if_exists==''>暂无数据<#else>
	            	<a href="/showroom/${user_name?if_exists}/profile.html">[更多]</a>
	            </#if>
            </p>
        </div>
       </#if>
        <!--绑定最新供应-->
       <#if mainbar.ch_code?if_exists=='zxgy'>
           <div class="supply padding_10">
           <ul class="pic">
              <#list supplyList as supply>
              	<li>
              	<div  class="divMiddle" style="width:156px;height:126px;line-height:126px;">
              		<a href="/showroom/${user_name?if_exists}/supply/detail_${supply.supply_id?if_exists}.html"  class="AMiddle"  style="width:156px;">
              			<#if (supply.img_path)?if_exists != ''>
              				<img src="${(supply.img_path)?if_exists}" 
              				     ${stack.findValue("@com.rbt.function.ChannelFuc@getSetImgSize('${supply.img_path?if_exists}','146','116')")} class="ImgMiddle"/>
              			<#else>
              				<img src="${(cfg_nopic)?if_exists}" 
                                ${stack.findValue("@com.rbt.function.ChannelFuc@getSetImgSize('${(cfg_nopic)?if_exists}','146','116')")} class="ImgMiddle"/>
              			</#if>
              		</a>
              	 </div>	
              		<a href="/showroom/${user_name?if_exists}/supply/detail_${supply.supply_id?if_exists}.html">
		              <#if supply.title?length lt 8>
		              ${supply.title?if_exists}
		              <#else>
		              ${supply.title[0..7]}...
		              </#if>
		            </a>
		        </li>
              </#list>
              <#if supplyList.size()==0><li>暂无数据</li></#if>
           </ul>
        </div>
       </#if>
        <!--绑定公司相册-->
       <#if mainbar.ch_code?if_exists=='zgsxc'>
          <div class="Photo padding_10">
           <ul class="pic">
              <#list gallerycustList as gallerycust>
	              <li>
	              <div  class="divMiddle" style="width:156px;height:126px;line-height:126px;">
	              		<a href="/showroom/${user_name?if_exists}/gallerypic/detail_${gallerycust.gal_id?if_exists}.html"  class="AMiddle"  style="width:156px;">
	              			<#if (gallerycust.img_path)?if_exists != ''>
	              				<img src="${(gallerycust.img_path)?if_exists}" 
                                   ${stack.findValue("@com.rbt.function.ChannelFuc@getSetImgSize('${gallerycust.img_path?if_exists}','146','116')")} class="ImgMiddle"/>
	              			<#else>
	              				<img src="${(cfg_nopic)?if_exists}"  
                                    ${stack.findValue("@com.rbt.function.ChannelFuc@getSetImgSize('${(cfg_nopic)?if_exists}','146','116')")} class="ImgMiddle"/>
	              			</#if>
	              		</a>
	              	</div>
	              		<a href="/showroom/${user_name?if_exists}/gallerypic/detail_${gallerycust.gal_id?if_exists}.html">
			              	<#if (gallerycust.title)?length lt 8>
			              		${(gallerycust.title)?if_exists}
			              	<#else>
			              		${gallerycust.title[0..7]}...
			              	</#if>
				        </a>
				   </li>
              </#list>
              <#if gallerycustList.size()==0><li>暂无数据</li></#if>
            </ul>
        </div>
       </#if>
       </div>
      </#list>
     <!--推荐产品结束-->
   
  <!--右部结束--> 
  </div>
 </div> 
		<!-- Baidu Button BEGIN -->
<script type="text/javascript" id="bdshare_js" data="type=slide&amp;mini=1&amp;img=2" ></script>
<script type="text/javascript" id="bdshell_js"></script>
<script type="text/javascript">
	document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + new Date().getHours();
</script>
<!-- Baidu Button END -->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/bottom.ftl">

<#if  (qqList.size()> 0) || (aliimList.size()> 0) || (msn?if_exists) || (skype?if_exists)>
	<DIV id=floatTools class=float0831>
  <DIV class=floatL><A style="DISPLAY: none" id=aFloatTools_Show class=btnOpen 
title=查看在线客服 
onclick="javascript:$('#divFloatToolsView').animate({width: 'show', opacity: 'show'}, 'normal',function(){ $('#divFloatToolsView').show();kf_setCookie('RightFloatShown', 0, '', '/', 'www.istudy.com.cn'); });$('#aFloatTools_Show').attr('style','display:none');$('#aFloatTools_Hide').attr('style','display:block');" 
href="###;">展开</A> <A id=aFloatTools_Hide class=btnCtn 
title=关闭在线客服 
onclick="javascript:$('#divFloatToolsView').animate({width: 'hide', opacity: 'hide'}, 'normal',function(){ $('#divFloatToolsView').hide();kf_setCookie('RightFloatShown', 1, '', '/', 'www.istudy.com.cn'); });$('#aFloatTools_Show').attr('style','display:block');$('#aFloatTools_Hide').attr('style','display:none');" 
href="###;">收缩</A> </DIV>
  <DIV id=divFloatToolsView class=floatR>
    <DIV class=tp></DIV>
    <DIV class=cn>
      <UL>
  
        <LI><SPAN class=icoZx>在线咨询</SPAN> </LI>
        
       <#if (qqList.size()> 0)>
       		<#list qqList as qq>
        		<LI><a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${qq?if_exists}&site=qq&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:${qq?if_exists}:41" alt="点击咨询" title="点击咨询" style="padding-top:10px;"></a></LI>        
       		</#list>
       </#if>
       <#if (aliimList.size()> 0)>
        	<#list aliimList as aliim>
        		<LI><a target="_blank" href="http://www.taobao.com/webww/ww.php?ver=3&touid=${aliim?if_exists}&siteid=cntaobao&status=1&charset=utf-8"><img border="0" src="http://amos.alicdn.com/realonline.aw?v=2&uid=${aliim?if_exists}&site=cntaobao&s=1&charset=utf-8" alt="点击这里给我发消息" style="padding-top:10px;"/></a></LI>
        	</#list>
       </#if>
        <#if msn?if_exists> 
        	<object id="MsgrObj" classid="clsid:B69003B3-C55E-4B48-836C-BC5946FC3B28" codetype="application/x-oleobject" width="0" height="0"></object>        
        	<LI>       	
        	<a href="msnim:chat?contact=${msn?if_exists}"><img src="/templets/company/kefu/images/msns.gif"  alt="在线MSN交流" border="0" style="padding-top:5px;" ></a>      	
        	</LI>
        </#if>
        <#if skype?if_exists>      
        	<LI class=bot><a href="callto://${skype?if_exists}"><img src="/templets/company/kefu/images/skypeme_btn_small_blue.gif" border="0"></a></LI>
        </#if>
      </UL>
      
    </DIV>
  </DIV>
</DIV>
</#if>


</body>
</html>
