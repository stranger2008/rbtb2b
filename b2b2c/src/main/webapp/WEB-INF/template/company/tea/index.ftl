<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>店铺首页—${memberconfig.web_title?if_exists}</title>
<meta name ="keywords" content="${memberconfig.keywords?if_exists}">
<meta name="description" content="${memberconfig.site_desc?if_exists}">  
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/public.css" rel="stylesheet" type="text/css">
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/index.css" rel="stylesheet" type="text/css">
<script src="/templets/company/tea/js/googlemap.js" type="text/javascript"></script>

<LINK rel=stylesheet type=text/css href="/templets/company/kefu/css/kefu.css">
<SCRIPT type=text/javascript src="/templets/company/kefu/js/jquery.js"></SCRIPT>
<SCRIPT type=text/javascript src="/templets/company/kefu/js/kefu.js"></SCRIPT>

</head>
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/top.ftl">

<div class="w960">
   <div class="postion"><span class="hpos">您当前所在的位置：</span><a>店铺首页</a><span>></span><a>欢迎光临${memberconfig.web_title?if_exists}</a></div>
   
   <div class="qycont">
     <div class="lw700">
     
        <!--介绍-->
        <div class="introback">
          <div class="intcont">
            <div class="intpic">
             <#if (memberconfig.header_img)?if_exists!=''>
   		     <img src="${(memberconfig.header_img)?if_exists}" width="680px" height="250px"/>
             <#else>
   		     <img src="/templets/company/${memberconfig.temp_code?if_exists}/images/com_pic.gif" width="680px" height="250px"/>
             </#if>
            </div>
           <!-- JiaThis Button BEGIN -->
           
			<div id="ckepop">
			 <p>
				<span class="jiathis_txt">分享到：</span>
				<a class="jiathis_button_tools_1"></a>
				<a class="jiathis_button_tools_2"></a>
				<a class="jiathis_button_tools_3"></a>
				<a class="jiathis_button_tools_4"></a>
				<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jiathis_separator jtico jtico_jiathis" target="_blank">更多</a>
				<a class="jiathis_counter_style"></a>
				</p>
			</div>
			<script type="text/javascript" src="http://v2.jiathis.com/code/jia.js" charset="utf-8"></script>
          <!-- JiaThis Button END -->
          </div>
        </div>
        
        <!--图片展示-->
        <div class="picture">
           <h2 class="pictitle"><span>图片展示</span><a href="/showroom/${user_name?if_exists}/gallery.html" class="more">更多>></a></h2>
           <div class="picbcont">
             <div class="piccont">
                <ul>
                  <#list gallerycustList as gallerycust>
                  <li>
                         <div>
                          <a href="/showroom/${user_name?if_exists}/gallerypic/detail_${gallerycust.gal_id?if_exists}.html">
                            <#if (gallerycust.img_path)?if_exists != ''>
	              				<img src="${(gallerycust.img_path)?if_exists}" width="150px;" height="110px"/>
	              			<#else>
	              				<img src="${(cfg_nopic)?if_exists}" width="150px;" height="110px"/>
	              			</#if>
	              			</a>
                         </div>
                         <p>	
                         <a href="/showroom/${user_name?if_exists}/gallerypic/detail_${gallerycust.gal_id?if_exists}.html">
                            <#if (gallerycust.title)?length lt 8>
			              		${(gallerycust.title)?if_exists}
			              	<#else>
			              		${gallerycust.title[0..7]}...
			              	</#if>
			              </a>
			             </p>
                     
                  </li>
                  </#list>
                </ul>
                <div class="clear"></div>
             </div>
           </div>
        </div>
        
        <!--留言-->
        <@s.form action="/companyleave.action?user_name=${user_name?if_exists}" method="post" validate="true" onsubmit="return doCheckSubmit();">
        <div class=" message">
          <h2 class="magtitle"><span class="magpic">留言</span></h2>
          <div class="magbcont">
            <div class="magcont">
              <table>
              	<tr height="30px;"><td width="10%"><span class="xzspan">标&nbsp;&nbsp;题<font color='red'>*</font></span></td>
              		<td><@s.textfield name="title" cssClass="magttext" maxLength="100" value=""/></td>
              		<td ><span class="xzts"><font color='red'><@s.fielderror><@s.param>title</@s.param></@s.fielderror></font></span></td>
              	</tr>
              	<tr height="30px;"><td><span class="xzspan f_left">内&nbsp;&nbsp;容<font color='red'>*</font></span></td>
              		<td><@s.textarea name="content" cssClass="tarea" value="" /></td>
              	<td ><font color='red'><@s.fielderror><@s.param>content</@s.param></@s.fielderror></font></td>
              	</tr>
              	<tr height="30px;"><td><span class="xzspan f_left">验证码<font color='red'>*</font></span></td>
              		<td><input type="text" maxlength="4" name="commentrand" id="rands" style="height:20px;width:50px;border: 1px solid #DDDDDD;vertical-align:middle;"/>
              		<img src="/imgrand.action" style="vertical-align:middle;" onclick="changeValidateCode(this)"/> </td>
              		<td><font color="red"><@s.fielderror><@s.param>commentrand</@s.param></@s.fielderror></font></td>
              	</tr>
              	<tr><td colspan="3"><p class="magp"><input type="submit" class="intbut" value="提 交"><font color='red'><@s.fielderror><@s.param>submit</@s.param></@s.fielderror></font></p></td></tr>
              </table>
            </div>
          </div>
        </div>
        </@s.form>   
     </div>
      <#include "/WEB-INF/template/company/${temp_loc?if_exists}/right.ftl">
</div>
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
