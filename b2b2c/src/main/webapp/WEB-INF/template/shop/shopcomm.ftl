<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>${(shopconfig.shop_name)?if_exists}-${cfg_mallwebname?if_exists}</title>
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<link href="/templets/shop/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/shop/css/message.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/templets/bmall/js/collect.js"></script>
<script type="text/javascript" src="/templets/shop/js/shopcomm.js"></script>
<#include "/WEB-INF/template/shop/jsinclude.ftl">
</head>

<body>
<#include "/WEB-INF/template/shop/top.ftl">
<@s.form action="/shop/${user_name?if_exists}/shopcomm.html" method="post">
<div class="w980">
   <#if busimesList?if_exists && (busimesList?size > 0)>
   <#list busimesList as blist>
    <div class="reply<#if blist_index%2==1>1</#if>">
      <p class="f_right">${(blist.user_name)?if_exists}<span>${(blist.msg_date)?if_exists}</span></p>
      <div class="clear"></div>
      <p class="qtion">咨询内容：${(blist.msg_content)?if_exists}</p>
      <p class="answer">咨询回复：
      <#if blist.re_content!=null && blist.re_content!="">
     	 ${(blist.re_content)?if_exists}
      <#else>
         暂无回复
      </#if>
      </p>
    </div>
    </#list>
     <div class="page_main">
		${pageBar?if_exists} 
	 </div>
    <#else>
    </#if>
    
    <div class="message">
      <h2 class="mesh2">发表咨询</h2>
      <div class="mescont">
        <p>声明：您可在购买前对产品包装、颜色、运输、库存等方面进行咨询，我们有专人进行回复！因厂家随时会更改一些产品的包装、颜色、产地等参数，所以该回复仅在当时对提问者有效，其他网友仅供参考！咨询回复的工作时间为：周一至周五，9:00至18:00，请耐心等待工作人员回复。</p>
        <p><b>咨询内容：</b></p>
        <p><textarea class="mestarea" id="mescontentid" style="width:400px;height:100px;" ></textarea></p>
        <p><a href="###;" onclick="addMessage();"><img src="/templets/shop/images/tij.gif"></a></p>
      </div>
    </div>
</div>
  
<div class="clear"></div>
<input type="hidden" value="${shop_cust_id}" id="shopid" />
</div>
<#include "/WEB-INF/template/bmall/default/bottom.ftl">
</@s.form>
</body>
</html>
