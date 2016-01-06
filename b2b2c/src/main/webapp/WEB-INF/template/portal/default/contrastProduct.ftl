<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<link href="/templets/${templateStyle?if_exists}/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/${templateStyle?if_exists}/css/supply_lis.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/jsinclude.html">
<script type="text/javascript">
    var count;	
$(document).ready(function(){
    var con_value=$("#contrasVlaue").val();  
    var value=con_value.split(",");
    count = value.length;
    for(var i=0;i<value.length;i++){
         $.ajax({  	 
           type: "post",    	     
           url: "/portal/supply!contrastList.action?id="+value[i]+"&ajaxRequestRandom="+Math.random(),       
           datatype:"json",
           success: function(data){   
           var td_data=data.split("<,>");            
           	   $("#main_table tr").each(function(i){
			      var trHtml = $(this).html();
				 trHtml += td_data[i];
				 $(this).html(trHtml);
			   });
		     }
		 });
    }
});
//移除对比
function removeTd(id){
  count--;
  if(count==0){
    window.close();
  }else{
    $(".td"+id).remove();
  }
  
}
</script>
<style type="text/css">
.child_main{float:left;border:1px solid blue;margin:20px 0px 0px 30px;width:200px;height:420px;}
.main_td{color:#FF6600;font-weight:bold;}
.conTab{
    border-top:1px solid #A0DDFF;border-right:0px;border-bottom:0px;border-left:0px;
	background:#cecece;
}
.conTab td{
    font-size:12px;width:12%;text-align:center;height:30px;
}
.conTab tr{
    background:white;
}
</style>
<title>产品对比--${cfg_webname?if_exists}</title>
</head>
<body>
<@s.form action="/portal/supply!list.action" method="post" name="searchForm">
<#include "/${templateRoute?if_exists}/top.html">
<!--导航结束-->
<div class="position"><P>当前位置: <a href="${web_basehost?if_exists}">首页</a> > <a href="/a/supply/index.html">供应</a> > <a href="###;">产品对比</a> </P></div>
<div class="w960" >
<div id="main" style="width:100%;height:auto;">
  <table id="main_table" width="100%" class="conTab" cellpadding="0" cellspacing="1">
    <tr style="background:#F6F5F5;"><td  class="main_td">标题</td></tr>
    <tr><td  class="main_td main_td_img">图片</td></tr>  
    <tr style="background:#F6F5F5;"><td  class="main_td">公司</td></tr>
    <tr><td  class="main_td">供应类型</td></tr>
    <tr style="background:#F6F5F5;"><td  class="main_td">所属分类</td></tr>
    <tr><td  class="main_td">所属地区</td></tr>
    <tr style="background:#F6F5F5;"><td  class="main_td">产品型号</td></tr>
    <tr ><td  class="main_td"">产品规格</td></tr>
    <tr style="background:#F6F5F5;"><td  class="main_td">品牌</td></tr>
    <tr ><td  class="main_td">计量单位</td></tr>
    <tr style="background:#F6F5F5;"><td  class="main_td">产品单价</td></tr> 
    <tr ><td  class="main_td">最小起订量</td></tr>
    <tr style="background:#F6F5F5;"><td  class="main_td">供应总量</td></tr>
    <tr ><td  class="main_td">过期时间</td></tr>
    <tr style="background:#F6F5F5;"><td  class="main_td">是否推荐</td></tr>
    <tr><td  class="main_td">相关操作</td></tr>
  </table>
</div>
</div>
<br class="clear"/>
<@s.hidden id="contrasVlaue" name="contrastValue"/>
<#include "/${templateRoute?if_exists}/bottom.html">
</@s.form>
</body>
</html>
