<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${list_seo_title?if_exists}</title>
<meta name="keywords" content="${list_seo_keyword?if_exists}" />
<meta name="description" content="${list_seo_desc?if_exists}" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<link href="/templets/${templateStyle?if_exists}/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/${templateStyle?if_exists}/css/position_list.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/templets/${templateStyle?if_exists}/js/productattr.js"></script>
<#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/jsinclude.html">
<script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script> 
</head>
<body>
<@s.form action="/portal/resume!list.action" method="post" name="searchForm">
<#include "/${templateRoute?if_exists}/top.html">
<#include "/WEB-INF/template/portal/default/position.ftl" /> 
<div class="w960">
  <div  id="renposition" class="left_main f_left">   
  <#include "/WEB-INF/template/portal/default/cateList.ftl" />    
    <div class="dldetail_title" id="dldetail_title"><ul>
      <li class="dldetail_title_h"><a href="/portal/resume!list.action">人才搜索</a></li>
      <li ><a  href="/portal/job!list.action">职位搜索</a></li></ul>
    </div>
<div class="dldetail_main">
      <div class="f_left dldetail_n"><p class="f_left">工作性质</p>
        <@s.select name="worktype" list=r"#{'':'--请选择--','不限':'不限','全职':'全职','兼职':'兼职','实习':'实习'}" id="id_worktype"/>
      </div>      
      <div class="f_left dldetail_n"><p class="f_left">性别要求</p>
      <@s.select name="gender" list=r"#{'':'--请选择--','男':'男士','女':'女士'}" id="id_gender"/>
      </div>
      <div class="f_left dldetail_n"><p class="f_left">婚姻状况</p>
	      <@s.select name="marriage" list=r"#{'':'--请选择--','未婚':'未婚','已婚':'已婚'}" id="id_marriage"/>
      </div>
      <div class="f_left dldetail_n"><p class="f_left">学历要求</p>
          <@s.select name="education" list=r"#{'':'--请选择--','不限':'不限','初中':'初中','高中':'高中','中专':'中专','大专':'大专','本科':'本科','硕士':'硕士','博士':'博士'}" id="id_education"/> 
      </div>
      <div class="f_left dldetail_n"><p class="f_left">工作经验</p>
      	<@s.select name="experience" list=r"#{'':'--请选择--','1':'1年以上','2':'2年以上','3':'3年以上','5':'5年以上','8':'8年以上','10':'10年以上'}" id="id_experience"/>
	  </div>
	  <div class="f_left dldetail_n"><p class="f_left">待遇水平</p>
          <@s.select name="salar" list="salarList" listValue="para_key" listKey="para_key" headerKey="" headerValue="--请选择--" id="id_salar"/>
      </div>	  
      <div class="f_left dldetail_n"><p class="f_left">发布日期</p> 
      <@s.select name="sup_time" list=r"#{'':'--请选择--','-1':'近一天','-2':'近二天','-3':'近三天','-7':'近一周','-14':'近两周','-30':'近一月','-60':'近两月'}" id="id_sup_time"/>
     </div>      
      <div class="f_left dldetail_s"><p class="f_left">关 键 词</p>
         <@s.textfield name="searchText"  cssClass="dldetail_main_srk f_left" size="30" maxlength="100" />
         <@s.submit cssClass="f_left search_img"  value="  搜索  "/>
      </div>    
      <div class="clear"></div>
</div>
 <#include "/WEB-INF/template/portal/default/attrList.ftl" />    
 <#if positionList?if_exists && (positionList?size > 0)>
    <div class="list_title"><h2 class="title_text1">简历名称<span class="list_title_edu">姓名</span>
    <span class="list_title_work" style="margin-left:25px;">专业</span><span style="margin-left:20px;">发布日期</span></h2></div>
    <div class="list_main">
    <ul>
     <#list positionList as post>
        <#assign rbttime='${(post.in_date)?if_exists}'/>  
        <li <#if post_index%2!=0> class="list_main_n2"</#if>>
	        <span class="list_main_n1_text">        
			    <a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('resume','${post.resume_id?
					if_exists}','${rbttime?if_exists}')")}" target="_blank">
			    <#if post.resume_name?length lt 11>${post.resume_name?if_exists}<#else> ${post.resume_name[0..10]}...</#if></a>
	        </span>
	        <span class="list_main_major">
	        	${post.real_name?if_exists}
	        </span>
	        <span class="list_main_work">
	        	<#if post.technical?length lt 9>${post.technical?if_exists}<#else> ${post.technical[0..10]}...</#if>
	        </span>
	       <span class="list_main_date" >${post.in_date[0..10]?if_exists}</span>
        
        </li>
     </#list>
      </ul>
      <div class="page_main">
          <div style="text-align:center;">
             ${pageBar?if_exists}
          </div>
      </div>
      <div class="clear"></div>      
      <div class="right_bottom">
        <p class="f_right"><a href="###;"><img src="/templets/${templateStyle?if_exists}/images/xtb_007.gif" />返回顶部</a></p>
      </div>
    </div>
    <#else>
       <div class="search_no_info">对不起！没有找到符合你搜索条件的信息!</div>
    </#if>
    <div class="clear"></div>
  </div>
   <div class="right_main f_right">
      <div class="area_title"><h2 class="title_text">热搜人才</h2></div>
       <ul class="ph_main">
        <#list hotsearchList as hot>
         <#assign rbttime='${(hot.in_date)?if_exists}'/>  
         <li><a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('resume','${hot.resume_id?
if_exists}','${rbttime?if_exists}')")}" target="_blank"><span class="area_main_major">
         <#if hot.resume_name?length lt 18>${hot.resume_name?if_exists}<#else> ${hot.resume_name[0..17]}...</#if>
         </span></a></li>        
        </#list>
   </ul>
   <div class="ad">
      <script src="/include/advshow.jsp?pos_id=61"></script>
   </div>
    <div class="area_title"><h2 class="title_text">推荐人才</h2></div>
      <ul class="area_main">
        <#list recomsearchList as recom>
       <#assign rbttime='${(recom.in_date)?if_exists}'/>  
           <li><a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('resume','${recom.resume_id?
if_exists}','${rbttime?if_exists}')")}" target="_blank"><span class="area_main_major">
           <#if recom.resume_name?length lt 18>${recom.resume_name?if_exists}<#else> ${recom.resume_name[0..17]}...</#if></span></a></li>       
        </#list>
   </ul>
   <div class="ad">
     <script src="/include/advshow.jsp?pos_id=62"></script>
   </div>
   <div>
     <script src="/include/advshow.jsp?pos_id=63"></script>
   </div>
</div>
</div>
<div class="clear"></div>
<@s.hidden name="cat_id" id="cat_id_para"/>
<@s.hidden name="area_id" id="area_value_para"/>
<@s.hidden id="searchtype" name="searchtype"/>
<#include "/${templateRoute?if_exists}/bottom.html">
</@s.form>
<script type="text/javascript">
$(document).ready(function(){
    //select改变表单提交事件
	$("#id_worktype").change(function(){//事件触发  
		document.searchForm.submit();//提交表单
    });  
       //select改变表单提交事件
	$("#id_gender").change(function(){//事件触发  
		document.searchForm.submit();//提交表单
    });  
       //select改变表单提交事件
	$("#id_marriage").change(function(){//事件触发  
		document.searchForm.submit();//提交表单
    });  
       //select改变表单提交事件
	$("#id_education").change(function(){//事件触发  
		document.searchForm.submit();//提交表单
    });
        //select改变表单提交事件
	$("#id_experience").change(function(){//事件触发  
		document.searchForm.submit();//提交表单
    });  
       //select改变表单提交事件
	$("#id_salar").change(function(){//事件触发  
		document.searchForm.submit();//提交表单
    });  
       //select改变表单提交事件
	$("#id_sup_time").change(function(){//事件触发  
		document.searchForm.submit();//提交表单
    });  
});
   //选中checkbox框中的值
   function setHiddenValbyCheck(para_name,obj_id){
        if($(obj_id).attr("checked")==true){
          	   document.getElementById(para_name).value = "1";
        }else{
               document.getElementById(para_name).value = "";
        }
	}
	function setHiddenVal(para_name,para_value){
        document.getElementById(para_name).value = para_value;
       	document.searchForm.submit();
	}
</script>
</body>
</html>
