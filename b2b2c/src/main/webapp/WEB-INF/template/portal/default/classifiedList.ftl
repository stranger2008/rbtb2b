<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${list_seo_title?if_exists}</title>
<meta name="keywords" content="${list_seo_keyword?if_exists}" />
<meta name="description" content="${list_seo_desc?if_exists}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<link href="/templets/${templateStyle?if_exists}/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/${templateStyle?if_exists}/css/supply_lis.css" rel="stylesheet" type="text/css" />
<link href="/templets/${templateStyle?if_exists}/css/classification.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/templets/${templateStyle?if_exists}/js/productattr.js"></script>
<#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/jsinclude.html">
<style type="text/css">
	.searchspan{padding:3px 6px 3px 6px;}
	.searchattr{margin-top:-2px;}
</style>
</head>

<body>
<@s.form action="/portal/classified!list.action" method="post" name="searchForm">
<#include "/${templateRoute?if_exists}/classifiedtop.html">
<!--头部logo——search结束-->
<div class="position_1"><P><span class="f_left">当前位置:
     <#if isviewarea?if_exists=='0'><a href="/areas-${en_name?if_exists}.html">${area_name?if_exists}</a> > </#if>
     <#if cateupname?if_exists!=''>
      <a href="/classified/cat-${catupid?if_exists}.html"><#if isviewarea?if_exists=='0'>${area_name?if_exists}</#if>${cateupname?if_exists}</a> >
     </#if>
     <#if isviewarea?if_exists=='0'>${area_name?if_exists}</#if>${categoryname?if_exists}<#if categoryname?if_exists==''>搜索</#if></span></p></div>

<div class="w960">
  <div class="main_left f_left">
    <div class="condition">
    <#include "/WEB-INF/template/portal/default/cateList.ftl" />    
  <ul>
	  <#include "/WEB-INF/template/portal/default/attrList.ftl" />   
	  <!--搜索-->
      <li style="margin-left:22px;">
	      <@s.textfield id="search" name="search" type="text" cssClass="ss_text"/>
	      <input type="button" value="" onclick="searchcat()" class="bz"/>
	      <input type="button" value="" onclick="searchall()" class="qz"/>
      </li>
      <br class="clear"/>
  </ul>
</div>

<#if attrList?if_exists!=''>
<div class="jxtj">
  <span class="f_left">筛选条件:</span><span id="condi" class="jxtj_span"></span> 
</div>
</#if>
 <ul class="all_nav">
   <li <#if mem_type?if_exists!='1' && mem_type?if_exists!='0'>class="all_hover"</#if>><a href="###;" onclick="rechangemem_type(2)">全部</a></li>
   <li <#if mem_type?if_exists=='1'>class="all_hover"</#if>><a href="###;" onclick="rechangemem_type(1)">个人</a></li>
   <li <#if mem_type?if_exists=='0'>class="all_hover"</#if>><a href="###;" onclick="rechangemem_type(0)">企业</a></li>
   <li class="fbxx"><span><a href="/member_Classified_add.action">免费发布<#if isviewarea?if_exists=='0'>${charea_name?if_exists}</#if>${categoryname?if_exists}信息</a></span></li>
</ul>

<div class="smzf">
  <P class="sm_zf"><span class="zf_dq"><#if isviewarea?if_exists=='0'>${area_name?if_exists}</#if>${categoryname?if_exists}</span> <input name="" id="checkimg" type="checkbox" value="" />只看有图 
   <#if styletype?if_exists=='2'>
   <a href="###;" onclick="rechangeword(1)" class="qhtw">切换到图文</a>
   <#else>
   <a href="###;" onclick="rechangeword(2)" class="qhtw">切换到文本</a>
   </#if>
  </P>
  <p class="zd"><a href="/aboutus_5.html"><span>置顶</span>，让信息效果更好</a></p>
</div>






<div class="list_main">
 <#if classifiedList.size() gt 0>
 <#list classifiedList as classified>   
  <div class="list_cont"> 
     <#if styletype?if_exists=='1'>
        <a href="/classified/detail_${classified.info_id?if_exists}.html"><img src="${classified.img_path?if_exists}" class="list_tw_img" /></a>
       <ul class="fwxx_tw fwxx">
           <li><a href="/classified/detail_${classified.info_id?if_exists}.html"><#if classified.title?length lt 26>
           ${classified.title?if_exists}
           <#else>
           ${classified.title[0..25]}
           </#if></a>
           <font color='red'>${classified.area_attr?if_exists}</font>
		   <#if classified.mem_type=='0'><font color='red'>(企业)</font><#else><font color='red'>(个人)</font></#if>
		   <#if classified.is_recom?if_exists=='1'><span class="ico_tui" title="推广信息"></span></#if></span> <span>${classified.in_dates?if_exists}</span></li>
           <li><#if classified.info_desc?length lt 140>
           ${classified.info_desc?if_exists}
           <#else>
           ${classified.info_desc[0..139]}...
           </#if>
           </li>
       </ul>
       <div class="clear"></div>
       </#if>
       <#if styletype?if_exists=='2'>
       <span class="fwxx"><a href="/classified/detail_${classified.info_id?if_exists}.html"><#if classified.title?length lt 26>
           ${classified.title?if_exists}
           <#else>
           ${classified.title[0..25]}
           </#if></a><a href="#" class="fujin"><a href="###;" onclick="searcharea('${classified.area_id?if_exists}');" class="fujin"><font color='red'>${classified.area_attr?if_exists}</font></a> <#if classified.img_path?if_exists!=''><span class="ico_tu"></span></#if>
           <#if classified.mem_type=='0'><font color='red'>(企业)</font><#else><font color='red'>(个人)</font></#if> 
           <#if classified.is_recom?if_exists=='1'><span class="ico_tui" title="推广信息"></span></#if>
           <span >${classified.in_dates?if_exists}</span>
		   </span>
           
         <br class="clear"/>
       </#if>
    </div>
    </#list>
    <span class="page_main">
          <div style="text-align:center;">
             ${pageBar?if_exists}
          </div>
    </span>
    <#else>
    <#if search?if_exists!=''>
       <div id="searchTip" class="searchTip" >
	          <div >
		            <img class="img_no_result" src="/include/images/no_result_bg.png"/>很抱歉， 没有找到与 “<font class="no_search_result">${search?if_exists}</font>”
		              相关的信息	              
	          </div>
	          <div>
	            建议您：
	          </div>
	          <style  type="text/css">
	          	 .searchTip{padding:10px;font-size:14px;background-color:#FFFEF8;border-bottom:1px solid #e5e5e5;margin-bottom:60px;}
	          	 .searchTip div{line-height:23px;}
	          	 .img_no_result{padding:0px 8px 0px 0px;}
	          	 .no_search_result{font-weight:600;font-size:16px;color:red;}
	          	 .re_no_Search{line-height:26px;}
	          	 .nr a{color:blue;} 	 
	          	 .nr a:hover{color:red;}         
	          </style>
	          <ol class="nr">
	              <li class="reSearch"><span>去掉不必要的字句，扩大搜索范围，如“的”、“什么”等。&nbsp;</span> <a href="###;" onclick="flush()">重新搜索</a></li>
	              <li>马上发布一条与 “${search?if_exists}” 相关的信息
	               <a href="/member_Classified_cate.action?cat_attr=${cat_attr?if_exists}&hidden_area_value=${hidden_area_value?if_exists}&classified.title=${search?if_exists}" target="_blank">马上发布一条&raquo;</a></li>
	          </ol>
	 </div>
	 <div class="pager"></div>
    <#else>
   <div style="padding-top:10px"> 暂无数据 </div>
    </#if>
    </#if>
</div>
   
   </div>
   <div class="sidebar">
        <#if isviewarea?if_exists=='0'>
       <div class="hy">
           <h2>欢迎访问<span>${area_name?if_exists}&nbsp;分站</span></h2>  
      </div>
        
        <ul class="contact">
            <li class="tel">电话：${(organize.phone)?if_exists}</li></br>
            <li class="qq">QQ：${(organize.qq)?if_exists}</li></br>
            <li class="msn">MSN：${(organize.msn)?if_exists} </li></br>
            <li class="skype">Skype：${(organize.skype)?if_exists}</li></br>
            <li class="e_mail">邮箱：${(organize.email)?if_exists} </li></br>
            <li class="cz">传真：${(organize.fax)?if_exists}</li></br>
            <li class="report"><a href="/member_Memberreport_cate.action">虚假信息举报</a> <a href="/member_Complaint_add.action">投诉</a></li>
        </ul>
      </#if>
      <div class="link">
          <p class="links"><span class="right_title_main">赞助商链接</span><span class="right_title_more"><a href="http://www.ruibaotong.net" target="_blank"/>我要加入</a></span></P>
          <div class="links_main">
             <script src="/include/advshow.jsp?pos_id=124&area_id=${hidden_area_id?if_exists}"></script>
             <script src="/include/advshow.jsp?pos_id=125&area_id=${hidden_area_id?if_exists}"></script>
          </div>
        </div>    
   </div>
</div>
</div>


<div class="clear"></div>
<@s.hidden name="cat_id" id="cat_id_para"/>
<@s.hidden name="hidden_area_id" id="hidden_area_id"/>
<@s.hidden name="hidden_cat_id" id="hidden_cat_id"/>
<@s.hidden name="area_id" id="area_value_para"/>
<@s.hidden name="img_state" id="img_state"/>
<@s.hidden name="styletype" id="styletype" value="${styletype?if_exists}"/>
<@s.hidden name="mem_type" id="mem_type" />
<@s.hidden name="title" id="title" />
<@s.hidden name="intrkey" id="intrkey" />
<@s.hidden name="all" id="all" />
<#include "/${templateRoute?if_exists}/bottom.html">
<script type="text/javascript">
	function setHiddenVal(para_name,para_value){
        document.getElementById(para_name).value = para_value;
       	document.searchForm.submit();
	}

	//判断checkbox是否被选中
	$(document).ready(function() {
	        if($("#styletype").val()==''){
	           $("#styletype").val("1");
	        }
	        if($("#img_state").val()=="1"){
	        $("#checkimg").attr("checked",true);
	        }else{
	         $("#checkimg").attr("checked",false);
	        }
	
            $("#checkimg").click(function() {
                if ($("#checkimg").attr("checked")) {
                   $("#img_state").val("1");
                    document.searchForm.submit();   
                }else{
                    $("#img_state").val("0");
                    document.searchForm.submit(); 
                    
                }
               
            }); 
            
          //获取条件串
          var condition='';
          var atrrString=$("#product_attr").val();
          var attr = atrrString.split(",");
          for(var i in attr){
            if(attr[i]!='' && attr[i].indexOf("none")==-1 && attr[i].indexOf("|") != -1){
               var value = attr[i].substring(attr[i].lastIndexOf("|"));
               if (value != '') {
					value = value.substring(1, value.length);
					// 获取条件ID号
					var value1 = attr[i].substring(0, attr[i].indexOf("|"));
					var value2 = attr[i].substring(attr[i].indexOf("|") + 1,attr[i].lastIndexOf("|"));
					condition+="<span class='jxtj_span'><a href='###;' onclick='setAttrForm("
											+ value1
											+ ",this,\""
											+ value
											+ "\",2);'>"
											+ value2
											+ "</a></span>" 
			   }
             
            }
          }
          $("#condi").html(condition);   
   });
        //图文切换
        function rechangeword(styletype){
          $("#styletype").val(styletype);
          document.searchForm.submit(); 
        }
        //信息类型切换
        function rechangemem_type(mem_type){
          $("#mem_type").val(mem_type);
          document.searchForm.submit(); 
        }
        //全站搜索
        function searchall(){
        if($("#search").val()==''){
         alert("请输入搜索条件");
         return;
         }
         $("#title").val($("#search").val());
         $("#intrkey").val("");
         $("#all").val("1");
         document.searchForm.submit(); 
        }
        
        //本类搜索
        function searchcat(){
         if($("#search").val()==''){
         alert("请输入搜索条件");
         return;
         }
         $("#intrkey").val($("#search").val());
         $("#title").val("");
         $("#all").val("2");
         document.searchForm.submit(); 
        }        
        //区域搜索
        function searcharea(area_id){
          $("#area_id").val(area_id);
          document.searchForm.submit(); 
        }
         //清空搜索框
        function flush(){
          $("#search").val("");   
        }
</script>
</@s.form>
</body>
</html>
