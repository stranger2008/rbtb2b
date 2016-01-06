<html>
  <head>
    <title>审核资讯</title>
	 <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
	  <script src="/include/js/admin/pitureshow.js" type="text/javascript"></script>	
	  <script type="text/javascript">
	  $(document).ready(function(){ 
	   
	   disabledCss();   
         //图片展示
         firstaddimges("mypicid","addimg","100","100");
  	  });
	</script> 	
  </head>
<body >
<div class="tj_main f_left">
 <div class="pageLocation">
 	当前位置:功能模块 > 资讯管理 > 资讯审核列表 > 审核资讯
   </div>
   <div class="tj_main_cont">
    <@s.form id="supplyadd" action="/admin_News_auditState.action" method="post" validate="true" onsubmit="return noreasron('news.info_state','noreasonvalue','2');">  
       <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td class="table_name" width="150px">资讯标题:</td>
             <td colspan="3" >
                <@s.label name="news.title" />
             </td>
            
           </tr>
          <tr>
             <td class="table_name">自定义属性:</td>
             <td >
                <@s.label name="auditattrString" />
             </td>
            <td class="table_name" width="150px">标题颜色</td>
             <td width="400px">
              ${news.title_color?if_exists}
            </td>
           </tr>
           <tr>
            <td class="table_name">所属分类:</td>
             <td >
               <@s.label name="news.cat_attr" />
             </td>
            <td class="table_name" style="width:150px;">投票:</td>
             <td  >
             <@s.label name="news.vote_id" />
             </td>
            
           </tr>
           <#if (sysmodule.is_catattr)?if_exists=="0">      
           		<#include "/WEB-INF/template/manager/auditattr.ftl" />   
           </#if>
           <tr>
           <td class="table_name">文章来源:</td>
             <td>
                <@s.label name="news.source" />
             </td>
          <td class="table_name">tag标签:</td>
              <td >
                 <@s.label name="news.tag" />
             </td>
           
           </tr>
            <tr>
               <td class="table_name">跳转网页:</td>
             <td >
                <@s.label name="news.out_link" />
             </td>
              <td class="table_name">作者:</td>
             <td>
                <@s.label name="news.author" />
             </td>
           </tr>
           <tr>
             
              <td class="table_name">关键字:</td>
             <td >
                <@s.label name="news.keyword" />
             </td>
               <td class="table_name">文章排序:</td>
             <td >
                 <#if news.sort_data_number?if_exists=='0'>
                   默认排序
                  <#elseif news.sort_data_number?if_exists=='7'>
                  置顶一周
                     <#elseif news.sort_data_number?if_exists=='30'>
                  置顶一个月
                     <#elseif news.sort_data_number?if_exists=='90'>
                  置顶三个月
                     <#elseif news.sort_data_number?if_exists=='180'>
                  置顶半年
                     <#elseif news.sort_data_number?if_exists=='365'>
                 置顶一年
                 </#if>
             </td>
           </tr>
           <tr>
             <td class="table_name">来源链接:</td>
              <td >
                <@s.label name="news.sourcelink" />
             </td>
              <td class="table_name">内容收费:</td>
             <td > 
             <@s.label name="news.fare" />( 默认"0"则免费!)
             </td>
             
           </tr>
           <tr>
             <td class="table_name">评论选项:</td>
             <td >
               <#if news.comment?if_exists='0'>支持评论<#elseif news.comment?if_exists='1'>禁止评论</#if>
             </td>
               <td class="table_name">点击量:</td>
             <td >
                <@s.label name="news.clicknum" />
             </td>
           </tr>
           
            <tr>
             <td class="table_name">所属地区:</td>
             <td colspan="3" >
             	<div id="arealist"></div>
                 <@s.fielderror><@s.param>area_attr</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
              <td class="table_name"  >缩略图:</td>
             <td colspan="3" >
              <@s.hidden name="news.litpic" id="mypicid"/>   
               <div id="addimg">
               </div>
             </td>
           </tr>
             <tr>
             <td class="table_name">内容摘要:</td>
             <td colspan="3">
                  <div style="padding:10px 200px 10px 0">
                ${news.description?if_exists}
               </div>
             </td>
           </tr>
             <tr>
             <td class="table_name">资讯内容:</td>
             <td colspan="3">
              <div style="padding:10px 200px 10px 0">
                ${news.content?if_exists}
               </div>
               
             </td>
           </tr>
           
            <#if if_opt_audit=="1">
             <tr>
             <td class="table_name">审核状态</td>
             <td colspan="3">
             	<@s.radio name="news.info_state" list=r"#{'1':'审核通过','2':'审核未通过'}" onclick="getRadioValue('news.info_state','reasonid','noreasonvalue','2');" />
             	<@s.fielderror><@s.param>news.info_state</@s.param></@s.fielderror>
             </td>
           </tr>
            <#if noReasonKey?if_exists=='2'>
             <tr  id="reasonid" >
             <td class="table_name">拒绝理由<font color='red'>*</font></td>
             <td colspan="3">
             	<@s.textfield name="news.no_reason" cssClass="txtinput" cssStyle="width:600px" id="noreasonvalue"  maxLength="100"/>
             	<@s.fielderror><@s.param>news.no_reason</@s.param></@s.fielderror>
             </td>
           </tr>
           <#else>
            <tr  id="reasonid" style="display:none;">
             <td class="table_name">拒绝理由<font color='red'>*</font></td>
             <td colspan="3">
             	<@s.textfield name="news.no_reason" cssClass="txtinput" cssStyle="width:600px" id="noreasonvalue"  maxLength="100"/>
             	<@s.fielderror><@s.param>news.no_reason</@s.param></@s.fielderror>
             </td>
           </tr>
           </#if>  
             </td>
           </tr>
           </#if>
         </table>
         <#if cfg_auditmodel=="0">
             <#include "/WEB-INF/template/manager/admin/Auditmodel/auditinfo.ftl" />   
          </#if>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="news.news_id"/>
	       <@s.hidden name="news.cust_id" />
	       ${listSearchHiddenField?if_exists}
	       <#if cfg_auditmodel=="0">
		        <#if if_opt_audit=="1">
		        <@s.submit value="审核"/>
		        </#if>
		   <#else>
		   		<@s.submit value="审核"/>
	       </#if>
	        <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_News_auditList.action','');"/>
	        <br/> <br/> <br/>
	     </div>
	     
	  </@s.form>  
   </div>
</div>

</div>
<div class="clear"></div>
  </body>
</html>