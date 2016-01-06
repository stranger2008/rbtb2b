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
<#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/jsinclude.html">
</head>
<body>
<@s.form action="/portal/company!list.action" method="post" name="searchForm">
<#include "/${templateRoute?if_exists}/top.html">
<#include "/WEB-INF/template/portal/default/position.ftl" />  
<div class="w960">
   <div class="left_main f_left">
   		 <#include "/WEB-INF/template/portal/default/cateList.ftl" />    
	     <div class="industry_main">
		     <div class="industry_main_search">
			     <table width="100%">
				     <tr>
				         <td><@s.select name="client_status" list="clientList" listKey='para_key'  listValue='para_key'  headerKey='' headerValue="所有经营模式"  id="id_client_status" /></td>
					     <td><@s.select name="sortby" list=r"#{'1':'注册时间降序','2':'注册时间升序','3':'信用指数降序','4':'信用指数升序'}"  headerKey='' headerValue="默认排序"  id="id_sortby"  /></td>
					     <td><@s.select name="date_in_date" list=r"#{'5':'超过5年','3':'超过3年','1':'超过1年','0':'不满一年'}"  headerKey='' headerValue="所有注册年份"   id="id_date_in_date"    /></td>
				         <td><@s.select name="level_code" list="levelList" listKey='level_id'  listValue='level_name' headerKey='' headerValue="所有会员"   id="id_level_code"   /></td>
				         <td width="166"></td>
				     </tr>
			     </table>
		     </div> 
		  </div>   
      <div class="flll_main">
        <#if companyList?if_exists && (companyList?size > 0)>
        <div class="flll_main_top" style="margin-bottom:23px;">
            <input class=" flll_main_top_jg f_left" id="all_cb_box" name="all_cb_box" type="checkbox" value="" />
            <a class="flll_main_top_jg f_left" href="###;" onclick="send_msg();"><img src="/templets/${templateStyle?if_exists}/images/xtb_msg.gif" /></a>
        </div>
        <div class="clear"></div>
        <#list companyList as company>               
           <div class="flll_main_mid" style="padding-bottom:10px;">
            <table width="100%" height="100%" style="height:130px;">
               <tr>
					<td rowspan="4" class="mod_td1">
					  <input class="cb_box" name="cb_box" type="checkbox" value="${company.company_id?if_exists}" />	
					  <input type="hidden" class="hidden_cust_name" value="${company.cust_name?if_exists}"/>	
	                </td>
	                <td rowspan="4" class="mod_td2">
		               <div class="divMiddle" style="width:118px;height:112px;line-height:112px;">
				           <a  href="/showroom/${company.user_name?if_exists}"
				            target="_blank"  class="AMiddle"  style="width:118px;">
			               <#if (company.logo_path)?if_exists != ''>
			                 <img  src="${company.logo_path?if_exists}" 
	                        ${stack.findValue("@com.rbt.function.ChannelFuc@getSetImgSize('${company.logo_path?if_exists}','108','102')")} class="ImgMiddle"/>
			               <#else>
			                 <img  src="${(cfg_nopic)?if_exists}" 
	                        ${stack.findValue("@com.rbt.function.ChannelFuc@getSetImgSize('${(cfg_nopic)?if_exists}','108','102')")} class="ImgMiddle"/>
			               </#if>
			               </a>
			           </div>
	                 </td>
	                 <td  class="mod_td3" colspan="2">
	                     <div style="float:left;">
							  <a href="/showroom/${company.user_name?if_exists}" target="_blank"><h2 class="flll_title1">
				                 <#if company.cust_name?length lt 23>${company.cust_name?if_exists}
								 <#else>${company.cust_name[0..22]}...</#if> 
						  </div>
						  <#if company.c_num?if_exists!=''>
							 <span class="td_span">							  
								  <span class="td_span_1">信用指数:</span>
		                          <span class="td_span_2">${company.c_num?if_exists}</span>
		                     </span>
		                  </#if>	                                               
	                 </td>	                
	                 <td rowspan="4" class="mod_td5" style="width:70px;">
			              <ul>				              
				               <li>
				               	  <#if company.area_name!=''>
				                     <font color="#333333">[${company.area_name?if_exists}]</font>
				                   </#if>
				               </li>
				               <li style="margin-top:10px;">				               
				               	   <#if company.contact_qq?if_exists!="">
				                      <a target="_blank" href=" http://wpa.qq.com/msgrd?v=3&uin=${company.contact_qq?if_exists}&site=qq&menu=yes">
				                      <img border="0" src="http://wpa.qq.com/pa?p=1:${company.contact_qq?if_exists}:16"></a>
				             	   </#if>   
				               </li>
				          </ul>     
	                 </td>
               </tr>
               <tr>
	               <td class="mod_td4" colspan="2" >
	                 <p> 
	                    <#if company.status_name?if_exists!=''>	
	               		   经营模式:${(company.status_name)?if_exists}<br/>
	               		</#if> 
		                <#if company.main_product?if_exists!=''>			                
			                   主营产品:
			                 <#if (company.main_product)?if_exists?length lt 62>
								  ${(company.main_product)?if_exists}
								 <#else>
								  ${company.main_product[0..61]}...
							</#if>
			                
			             </#if>
		             </p>
	               </td>
               </tr>
               <tr>
                  <td >
                  <span class="area_name">
                  <#if company.contact_cellphone!=''>
                        联系电话:${(company.contact_cellphone)?if_exists}
                  </#if>
                  </span>
                  <span style="float:right;">
                       <#if company.level_code!='4'>
	                    	<#if (company.level_name)?if_exists>
                       			 <span class="flll_main_mid_text" >[${(company.level_name)?if_exists}]</span>
                       		 </#if>
	                    <#else>
	                    	<span style="color:red; ">[${(company.level_name)?if_exists}]</span>
	                    </#if>
                  <span>
                 </td>
               </tr>
               <tr>
               <td >
                  <span class="area_name">                                          
                     <#if company.address?if_exists!="">
                         企业地址:
	                     <#if (company.address)?if_exists?length lt 26>
						    ${(company.address)?if_exists}
						 <#else>
						    ${company.address[0..25]}...    
						 </#if>          
					 </#if>	 
	              </span>  
	              <span style="float:right;">	   
	                 <#if company.in_date?if_exists!=''>        
                          注册时间:${company.in_date[0..9]?if_exists}	
                     </#if>	  
             	  </span>    
               </td>         
               </tr>
            </table>
        </div>                
        <div class="clear"></div>
        </#list>
      <div class="right_bottom">
         <input class=" flll_main_top_jg f_left" id="all_cb_box_bt" name="all_cb_box" type="checkbox" value="" />
         <a class="f_left" href="###;" onclick="send_msg();"><img src="/templets/${templateStyle?if_exists}/images/xtb_msg.gif" /></a>
         <p class="f_right"><a href="#"><img src="/templets/${templateStyle?if_exists}/images/xtb_007.gif" />返回顶部</a></p>
      </div>
       <div class="page_main">
            <div style="text-align:center;">
                ${pageBar?if_exists}<br/>     
            </div>
        </div>
            <#else>
           <div class="search_no_info">对不起！没有找到符合你搜索条件的信息!</div>
        </#if>
      <div class="clear"></div>
      </div>
   </div>
   <div class="right_main f_right">
     
      <div class="area_title"><h2 class="title_text">推荐企业</h2></div>
      <ul class="ph_main">
        <#list topList as top>
         <li><span class="n1">1</span><a href="/showroom/${top.user_name?if_exists}" target="_blank">
         <#if top.cust_name?length lt 16>${top.cust_name?if_exists}
		<#else>${top.cust_name[0..15]}... </#if></a></a></li>
        </#list>   
   	  </ul>  
   	  
   	  <div class="ad">
    <script src="/include/advshow.jsp?pos_id=57"></script>
   </div>
   <div class="ad">
    <script src="/include/advshow.jsp?pos_id=58"></script>
   </div>
    </div>
   
</div>
<div class="clear"></div>
<@s.hidden name="searchText"/>	
<@s.hidden name="cat_id" id="cat_id_para"/>
<@s.hidden name="area_id" id="area_value_para"/>
<#include "/${templateRoute?if_exists}/bottom.html">
</@s.form>
<script>
function send_msg(){
    var askcount=0;
    var ask_cust_name="";
    $("input:checkbox[name=cb_box]").each(function(){	  
           if(this.checked){
                askcount++;
                var cust_name=$(this).parent("td").find(".hidden_cust_name").val();
                if(ask_cust_name.indexOf(cust_name)==-1){
	                ask_cust_name+=cust_name;
	                ask_cust_name+=",";
                } 
           }
       })
        var size = ask_cust_name.lastIndexOf(",");
        if(size>0){
            ask_cust_name=ask_cust_name.substring(0,size);
        }
        $("#send_name").val();//清空数据
        $("#send_name").val(ask_cust_name);
	    if(askcount>0){
	        document.addMemberinboxFormds.submit();
	    }else{
	        alert("请至少选择一位商家留言!");
	    }
}

</script>
<form action="/member_Memberinbox_add.action" method="post" name="addMemberinboxFormds">
   <input type="hidden" id="send_name" name="send_name" value=""/>
   <input type="hidden" name="memberinbox.mess_type" value="4"/>
   <input type="hidden" name="loc" value="" /> 
</form>		    
<script>
	document.addMemberinboxFormds.loc.value = document.location.href;
</script>
<script type="text/javascript">
function setHiddenVal(para_name,para_value){
        document.getElementById(para_name).value = para_value;
       	document.searchForm.submit();
}
$(document).ready(function(){
     /*供应排行*/
	 $(".ph_main .n1:lt(3)").addClass("n1");
	 $(".ph_main .n1:gt(2)").addClass("n2");
     var value_array = [1,2,3,4,5,6,7,8,9,10]; 
	 $(".ph_main .n1").each(function(i){  
		 $(this).text(value_array[i]);
     });   
     //全选与反选
    $("#all_cb_box,#all_cb_box_bt").click(function(){
	      if(this.checked){
	         $("input:checkbox[name=cb_box]").each(function(){	  
	            this.checked=true;
	         })
	      }else{
	         $("input:checkbox[name=cb_box]").each(function(){	  
	            this.checked=false;
	         })
	      }
    });
    //select改变表单提交事件
	$("#id_client_status").change(function(){//事件触发  
		document.searchForm.submit();//提交表单
    });  
       //select改变表单提交事件
	$("#id_sortby").change(function(){//事件触发  
		document.searchForm.submit();//提交表单
    });  
       //select改变表单提交事件
	$("#id_date_in_date").change(function(){//事件触发  
		document.searchForm.submit();//提交表单
    });  
       //select改变表单提交事件
	$("#id_level_code").change(function(){//事件触发  
		document.searchForm.submit();//提交表单
    });  
});
</script>
</body>
</html>
