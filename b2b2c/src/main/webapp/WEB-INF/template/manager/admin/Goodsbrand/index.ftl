<html>
  <head>
    <title>商品品牌列表</title>   
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
     <script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	 <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
	 <script type="text/javascript">
     function updatesort_no(actionName)
      {  
           var admin_group_id='';
	       var chks =document.getElementsByTagName('input');//得到所有input
           for(var i=0;i <chks.length;i++)
          { 
            
           if(chks[i].type.toLowerCase() == 'checkbox'&&chks[i].value!='on')
           {
                //得到所名为checkbox的input
                admin_group_id+=chks[i].value+',';
               
             }
           }
            var len=admin_group_id.length;
            var group_id=admin_group_id.substring(0,len-1);
            document.getElementById('goodsbrand_sortno_id').value = group_id;//用于隐藏所有的ID
			document.forms[0].action=actionName;
			document.forms[0].submit();
      }

    </script>
  </head>
  <body>
<@s.form action="/admin_Goodsbrand_list.action" method="post">
<@s.hidden name="goodsbrand_sortno_id" id="goodsbrand_sortno_id"/>
<div class="main_index f_left">
   <div class="pageLocation">
 	当前位置:商城管理 > 商品管理 > 商品品牌列表
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Goodsbrand_add.action','');">添加品牌</a></li>
     <li class="sc"><a onclick="delInfo('goodsbrand.brand_id','/admin_Goodsbrand_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
       <li class="xg" ><a onclick="updatesort_no('/admin_Goodsbrand_updatesortno.action');">排序</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('goodsbrand.brand_id')"/></td>
    
     	 <td width="5%"  align="center" class="top_td">排序</td>
    
     	 <td width="15%"  align="center" class="top_td">品牌名称</td>
    
     	 <td width="15%"  align="center" class="top_td">品牌网址</td>
    
     	 <td width="10%"  align="center" class="top_td">状态</td>
     	 
         <td width="10%"  align="center" class="top_td">是否推荐</td>
    
    <td width="5%" align="center" class="top_td">修改</td>
  </tr>
  
  <#list goodsbrandList as goodsbrand>
  <tr>
    <td><input type="checkbox" name="goodsbrand.brand_id" value="${goodsbrand.brand_id?if_exists}"/></td>    
 		
 		<td align="center"><input name="isort_no" value="${goodsbrand.sort_no?if_exists}" style="width:50px" onkeyup="checkNum(this)" />
 		</td>
    
    	<td align="center">${goodsbrand.brand_name?if_exists}</td>
    
    	<td align="center">${goodsbrand.brand_site?if_exists}</td>
      
    	<td align="center">
    	<#if goodsbrand.info_state?if_exists?if_exists='0'>
	   	   <font class="greencolor">正常</font>
	    <#elseif goodsbrand.info_state?if_exists?if_exists='1'>	     
	   		<font class="redcolor">禁用</font>
    	</#if>
	    </td>
	    <td align="center">
    	<#if goodsbrand.is_recom?if_exists?if_exists='0'>
	   	   <font class="redcolor">未推荐</font>
	    <#elseif goodsbrand.is_recom?if_exists?if_exists='1'>	     
	   		<font class="greencolor">推荐</font>
    	</#if>
	    </td>
          
    <td align="center"><a onclick="linkToInfo('/admin_Goodsbrand_view.action','goodsbrand.brand_id=${goodsbrand.brand_id?if_exists}');"><img src="/include/images/bj.gif" /></a></td>
  </tr>
  </#list>
</table>
 </div>
 <div class="listbottom">
   ${pageBar?if_exists}
 </div>
 <div class="clear"></div>
</div>
</div>
<div class="clear"></div>


<!--搜索区域开始-->

<div class="divceng" style="display:none;" id="searchDiv">
	<table align="left">
		<tr>
		<td align="right">品牌名称:</td> 
		<td><@s.textfield name="title_s"  cssStyle="width:245px;"/></td>
	</tr>
		<tr>
		<td align="center" colspan="2" style="border:0px;">
			<input type="button" name="search" value="搜索" onclick="showSelectDiv('','','','');" />
			<input type="button" name="close" value="关闭" onclick="closeSearch('searchDiv')"/>
		</td>
	</tr>
	</table>
</div>

</@s.form>
</body>
</html>

