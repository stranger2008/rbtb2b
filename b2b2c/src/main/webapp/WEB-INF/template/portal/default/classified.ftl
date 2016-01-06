<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>国家地区切换</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/jsinclude.html">
<link href="/templets/${templateStyle?if_exists}/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/${templateStyle?if_exists}/css/classification.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
 $(document).ready(function(){ 
   var type=$("#type").val();
   if(type=='country'){
     showdiv(2,2,'dq_hover','cont_main','dq_hover','');
   }else{
     showdiv(1,2,'dq_hover','cont_main','dq_hover','');
   }
   
 }); 
	/*
		val:当前数字
		num:tab页总数
		btnName:选项卡id名称前缀（去掉数字的名称）
		divName:显示隐藏的下部div名称前缀
		btncss1:选项卡当前的样式名称
		btncss2:选项卡其他的样式名称
	*/
	function showdiv(val,num,btnName,divName,btncss1,btncss2){
		for(var i=1;i<=num;i++){
			if(val==i){
				document.getElementById(divName+i).style.display = 'block';
				document.getElementById(btnName+i).className = btncss1;
			}else{
				document.getElementById(divName+i).style.display = 'none';
				document.getElementById(btnName+i).className = btncss2;
			}
		}
	}
	
	 //输入查找地区
	  function inputarea(){
	     var area=$("#in_area").val();
	     var varea=encodeURIComponent(encodeURIComponent(area));
	        if(area!=""){
	      	$.ajax({  	 
            type: "post",    	     
            url: "/portal/classified!inputarea.action?areaName="+varea,
            datatype:"json",
            async:false,
            success: function(data){   
   	          if(data!=0){
   	           window.location.href='/classified/areas-'+ data +'.html';
   	          }else{
   	           alert("找不到城市！");
   	          }
   	        }
            });
            }else{
              alert("请输入城市的名称！");
            }
	  }
</script>
</head>

<body>

<#include "/${templateRoute?if_exists}/classifiedtop.html">
<@s.hidden name="type" id="type"/>
<div class="w960 cmin">
   <P class="comein"><a href="/areas-${en_name?if_exists}.html">&nbsp;<font color='red'>直接进入</font>${country?if_exists}<#if country?if_exists!=area_name?if_exists>${area_name?if_exists}</#if></a><span class="sc_zj">或直接输入</span><input id="in_area" type="text" /><input type="button" value="确 定" onclick="inputarea()"/></P>
</div>


<div class="dq w960">
         <ul>
            <li class="dq_hover" id="dq_hover1" onmouseover="showdiv(1,2,'dq_hover','cont_main','dq_hover','');">${country?if_exists}	</li>
            <li id="dq_hover2" onmouseover="showdiv(2,2,'dq_hover','cont_main','dq_hover','');">国家筛选</li><br class="clear" />
         </ul> 
      </div>
<div class="cont_main" id="cont_main1">
</br>
<#assign x=1>
<#if (areacharList.size() < 1) > <P class="cont_area">暂无地区</P> </#if>
<#list areacharList as areachar>
    <P class="cont_area">
    <span class="letter">
    ${areachar.en_name?capitalize}.
    </span>
    <span class="area1">
    <#list areaList as area>
      <#if area.en_name[0..0] == areachar.en_name?if_exists>
      <a href="/areas-${area.en_name?if_exists}.html">${area.area_name?if_exists}</a>
      </#if>
    </#list>
     </span></P>
     <br class="clear"/>
</#list>
</div>
<div class="cont_main" id="cont_main2">
</br>
<#list countrycharList as countrychar>
     <P class="cont_area">
     <span class="letter">
     ${countrychar.en_name?capitalize}.
     </span>
     <span class="area1">
    <#list areacountryList as areacountry>
      <#if areacountry.en_name[0..0] == countrychar.en_name?if_exists>
      <a href="/classified/${areacountry.en_name?if_exists}/area.html">${areacountry.area_name?if_exists}</a>
      </#if>
    </#list>
     </span></P>
     <br class="clear"/>
</#list>
</div>
<#include "/${templateRoute?if_exists}/bottom.html">
</body>
</html>
