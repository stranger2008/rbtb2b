<html>
  <head>
    <title>修改会员</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <#include "/include/uploadInc.html">
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
    <script type="text/javascript" src="/include/js/jquery.alert.js"></script>
    <script type="text/javascript" src="/include/js/admin/member.js"></script>		
	<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
	<script type="text/javascript">
	  $(document).ready(function(){
	     //所属分类的回选
         cate_select(${cfg_topcatid?if_exists},1,"company");           
         //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");
         $("#com_insert").submit(function(){
              var cat_attr_str="";
              $("input:hidden[name='all_area_id_str']").each(function(){
                  cat_attr_str+=$(this).val()+"|";
              }) 
              $("#cat_attr_str").val(cat_attr_str);        
              return true;
         }); 
         //注册币种回显
         var typevalue = $("#moneytype option:selected").val();
         if(typevalue == "othertype"){
             $("#othertype").css("display","");
         }
             
	  });
	  
	 //当选择币种为其他时显示输入框
	 function addMoneytype(){
	    $("select[id^=moneytype]").each(function(){	
             if($(this).val()=="othertype"){ 
                 $("#othertype").css("display","");
			}else{
			     $("#othertype").css("display","none");
			}
        });	  
	  } 	
	  
	</script> 
  </head>
  <body>
<div class="tj_main f_left">
 <div class="pageLocation">
 	当前位置:会员管理 > 会员管理 > 会员列表 > 修改会员
 </div>
   <div class="tj_main_cont">
   	<@s.form   id="com_insert"  action="/admin_Member_update.action" method="post" validate="true">
      
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td colspan="4" align="left" style="font-weight:bold;font-size:14px;padding-left:120px;">会员基本信息</td>
           </tr>  
           <tr>
             <td class="table_name">会员名称<font color='red'> *</font></td>
             <td colspan="3">
             	<@s.textfield name="member.cust_name" cssClass="txtinput" cssStyle="width:400px;" maxLength="100"/>
             	<@s.fielderror><@s.param>member.cust_name</@s.param></@s.fielderror>
             	<@s.hidden  name="oldinfotitle" value="${member.cust_name?if_exists}"/>
             </td>
           </tr>
           <tr>
             <td class="table_name">企业LOGO:</td>
             <td colspan="3">
             
              <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			  <@s.textfield name="member.logo_path" id="uploadresult" cssStyle="width:300px;" value="${(member.logo_path)?if_exists}"/>
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
	              			<script>uploadOneImg();</script>
             			</td>
             			<td>
             			   <@s.fielderror><@s.param>member.logo_path</@s.param></@s.fielderror>
             			</td>
             		</tr>
             	</table>
             </td>
           </tr>
            <tr>
             <td class="table_name">会员状态:</font></td>
             <td colspan="3">
             	<@s.radio name="member.info_state" list=r"#{'0':'未审核','1':'审核通过','2':'审核未通过','3':'禁用'}" />
             	<@s.fielderror><@s.param>member.info_state</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
              <td class="table_name" >联系人姓名<font color='red'> *</font></td>
             <td>
             	<@s.textfield name="member.contact_name" cssClass="txtinput"  maxLength="50" cssStyle="width:200px;"/>
             	<@s.fielderror><@s.param>member.contact_name</@s.param></@s.fielderror>
             	<@s.radio name="member.contact_sex" list=r"#{'先生':'先生','女士':'女士'}" />
             </td>
             <td class="table_name">电子邮箱<font color='red'> *</font></td>
             <td >
             	<@s.textfield name="member.email" cssClass="txtinput" maxLength="100"  cssStyle="width:300px;"/>
             	<@s.fielderror><@s.param>member.email</@s.param></@s.fielderror>
             	<@s.hidden  name="oldemail" value="${member.email?if_exists}"/>
             </td>
             
           </tr>
            <tr>
             <td class="table_name">联系手机:</td>
             <td>
             	<@s.textfield name="member.contact_cellphone" cssClass="txtinput" maxLength="50"  cssStyle="width:300px;"/>
             	<@s.fielderror><@s.param>member.contact_cellphone</@s.param></@s.fielderror>
             </td>
              <td class="table_name">联系人MSN:</td>
             <td>
             	<@s.textfield name="member.contact_msn" cssClass="txtinput" maxLength="100" cssStyle="width:300px;"/>
             	<@s.fielderror><@s.param>member.contact_msn</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">公司电话:</td>
             <td>
             	<@s.textfield name="member.phone" cssClass="txtinput" maxLength="50" cssStyle="width:200px;" />
             	<@s.fielderror><@s.param>member.phone</@s.param></@s.fielderror> 
             </td> 
              <td class="table_name">公司传真:</td>
             <td>
             	<@s.textfield name="member.fax" cssClass="txtinput" maxLength="50" cssStyle="width:200px;"/>
             	<@s.fielderror><@s.param>member.fax</@s.param></@s.fielderror>
             </td> 
           </tr> 
           <tr>
             <td class="table_name">联系人QQ:</td>
             <td>
             	<@s.textfield name="member.contact_qq" cssClass="txtinput"  maxLength="100"  cssStyle="width:300px;"/>
             	<@s.fielderror><@s.param>member.contact_qq</@s.param></@s.fielderror>
             </td>
             <td class="table_name">联系人职位:</td>
             <td>
             	<@s.textfield name="member.contact_depart" cssClass="txtinput" maxLength="50"  cssStyle="width:300px;"/>
             	<@s.fielderror><@s.param>member.contact_depart</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">邮政编码:</td>
             <td>
             	<@s.textfield name="member.post_code" cssClass="txtinput"  maxLength="50"  cssStyle="width:300px;"/>
             	<@s.fielderror><@s.param>member.post_code</@s.param></@s.fielderror>
             </td>
              <td class="table_name">联系人部门:</td>
             <td>
               <@s.textfield name="member.contact_job" cssClass="txtinput"  maxLength="50"  cssStyle="width:300px;"/>
             	<@s.fielderror><@s.param>member.contact_job</@s.param></@s.fielderror>
             </td>
           </tr> 
            <tr>
             <td class="table_name">所在地区<font color='red'> *</font></td>
             <td colspan="3">
             	<div id="arealist"></div>
                 <@s.fielderror><@s.param>area_attr</@s.param></@s.fielderror>    
             </td>
           </tr>
           <tr>
             <td colspan="4" align="left" style="font-weight:bold;font-size:14px;padding-left:120px;">企业基本信息</td>
           </tr>   
           
           <tr>
             <td class="table_name">行业分类:<span id="classattr" style="display:<#if custtypeTip=='0'><#else>none</#if>"><font color="red"> *</font></span></td>
             <td colspan="3">
              <input type="hidden" id="cat_attr_str" name="cat_attr_str" value="${cat_attr_str?if_exists}">
             	<div >
             		<div id="show_add_cat">
	                     <#list cat_attr_list as cat>
	                          <div style='line-height:20px;'>
		                          <input type='hidden' name='all_area_id_str' value="${cat.id?if_exists} "/>${cat.val?if_exists} 
		                          <a class='oper' href='###' onclick='del_add_area(this)'>[删除]</a>
	                          </div>
	                     </#list>
	                 </div>
	                 <table>
	                 	<tr><td  colspan="2" class="tdbottom"><div id="divlist"></div></td>
	                 	<td class="tdbottom"><a class="oper_add" href="###" onclick="com_add_cat()"><input type='button' value="添加分类"/></a><@s.fielderror><@s.param>cate_attr</@s.param></@s.fielderror>   </td></tr>
	                 </table>	                 
		         </div>
             </td>
           </tr>   
           
           <tr>
             <td class="table_name">企业类型:<span id="custtype" style="display:<#if custtypeTip=='0'><#else>none</#if>"><font color="red"> *</font></span></td>
             <td colspan="3">
             	<@s.select name="member.cust_type" list="commparaList" listValue="para_key" listKey="para_value" headerKey="0" headerValue="请选择" />
             	<@s.fielderror><@s.param>member.cust_type</@s.param></@s.fielderror>
             </td>  
           </tr>
           
           
           <tr>
              <td valign="middle" class="table_name">公司介绍<font color='red'> *</font></td>
             <td colspan="3">
                <@s.textarea name="member.cust_desc" id="cust_desc" cssClass="txtinput" cssStyle="width:600px;height:250px;" maxLength="2000" />
				<@s.fielderror><@s.param>member.cust_desc</@s.param></@s.fielderror>
				<div style="line-height:20px;">	-我们建议您填写详细的公司介绍，例如历史、业绩、经营范围、发展前景等。</br>
						-不支持html语言。</br>
						-内容控制在50-2000字符内。
				</div>
             </td>
          </tr> 
           
           
           <tr>
             <td class="table_name">企业类别:</td>
             <td colspan="3">
                <@s.radio name="member.cust_rage" list=r"#{'0':'供应商','1':'采购商','2':'二者皆有'}" /> 
                <@s.fielderror><@s.param>member.cust_rage</@s.param></@s.fielderror>
             </td>
           </tr>
           
            <tr>
             <td class="table_name">供应产品（服务）:</td>
             <td colspan="3">
             	<@s.textfield name="member.cust_supply" cssClass="txtinput"  cssStyle="width:600px;" />
             	<@s.fielderror><@s.param>member.cust_supply</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">采购产品（服务）:</td>
             <td colspan="3">
             	<@s.textfield name="member.cust_stock" cssClass="txtinput" cssStyle="width:600px;" />
             	<@s.fielderror><@s.param>member.cust_stock</@s.param></@s.fielderror>
             </td>
           </tr>
           
            <tr>
             <td class="table_name">经营模式:<font color="red"> *</font></td>
             <td colspan="3">
                <@s.checkboxlist id="status" name="member.client_status" list="clientStrList" listKey="para_value" headerKey="" listValue="para_key" value="#request.clientList">
                </@s.checkboxlist>
                <@s.fielderror><@s.param>member.client_status</@s.param></@s.fielderror> 
             </td>
           </tr>
           <tr>
             <td class="table_name">公司关健词:</td>
             <td colspan="3">
             	<@s.textfield name="member.cust_key" cssClass="txtinput" cssStyle="width:600px;"/>
             	<@s.fielderror><@s.param>member.cust_key</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">公司经营范围:</td>
             <td colspan="3">
             	<@s.textarea name="member.main_product" cssClass="txtinput" rows="3" cssStyle="width:600px;" onkeyup="set_textarea_length(this,200);"/>
             	<@s.fielderror><@s.param>member.main_product</@s.param></@s.fielderror>
             </td>  
           </tr>
                
            <tr>
             <td class="table_name">经营地址:</td>
             <td colspan="3">
             	<@s.textfield name="member.address" cssClass="txtinput"  maxLength="200" rows="3" cssStyle="width:600px;"/>
             	<@s.fielderror><@s.param>member.address</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
              <td class="table_name">公司网址:</td>
             <td colspan="3">
             	<@s.textfield name="member.website" cssClass="txtinput" maxLength="100" cssStyle="width:400px;"/>
             	<@s.fielderror><@s.param>member.website</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
              <td class="table_name">推荐:</td>
             <td colspan="3">
             	<@s.radio name="member.recommend" list=r"#{'0':'非推荐','1':'推荐'}" />
             	<@s.fielderror><@s.param>member.recommend</@s.param></@s.fielderror>
             </td>
          </tr>
          <tr>
             <td colspan="4" align="left" style="font-weight:bold;font-size:14px;padding-left:120px;">企业详细信息</td>
           </tr>
           <tr>
             <td class="table_name">公司法人代表:</td>
             <td colspan="3">
               <@s.textfield name="member.corporate" cssClass="txtinput" maxLength="100" cssStyle="width:200px;"/>
               <@s.fielderror><@s.param>member.corporate</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">公司规模:</td>
             <td colspan="3">
                <@s.select name="member.staff_num" list="staffList" listValue="para_key" listKey="para_value" headerKey="保密" headerValue="保密" maxLength="10"/>
             	<@s.fielderror><@s.param>member.staff_num</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">注册资金:</td>
             <td colspan="3">
                <@s.textfield name="member.reg_money" cssClass="txtinput"  maxLength="100"/>
                <@s.fielderror><@s.param>member.reg_money</@s.param></@s.fielderror>
                <@s.select id="moneytype" name="member.reg_money_type" list="moneyList" listValue="para_key" listKey="para_value" headerKey="0" headerValue="请选择" maxLength="100" onchange="addMoneytype();"/>
                
                <span id="othertype" style="display:none;"><@s.textfield  name="other_money_type" cssClass="zitxt"  maxlength="50"/></span>
             </td>
           </tr>
           <tr>
             <td class="table_name">年销售额:</td>
             <td colspan="3">
                <@s.select name="member.year_sum" list="sumList" listValue="para_key" listKey="para_value" headerKey="保密" headerValue="保密" maxLength="200"/>百万人民币
             	<@s.fielderror><@s.param>member.year_sum</@s.param></@s.fielderror>
             </td>  
           </tr>
           <tr>
             <td class="table_name">公司成立年份:</td>
             <td colspan="3"> 
               <@s.textfield name="member.reg_date" cssClass="txtinput" maxLength="100" cssStyle="width:200px;"/>
               <@s.fielderror><@s.param>member.reg_date</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
              <td class="table_name">商标:</td>
             <td colspan="3">
             	<@s.textfield name="member.brand_name" cssClass="txtinput" maxLength="200"  cssStyle="width:200px;"/>
             	<@s.fielderror><@s.param>member.brand_name</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">营业执照号:</td>
             <td colspan="3">
             	<@s.textfield name="member.reg_no" cssClass="txtinput" maxLength="200"  cssStyle="width:300px;"/>
             	<@s.fielderror><@s.param>member.reg_no</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">是否OEM代加工:</td>
             <td colspan="3">
                <@s.radio name="member.isoem" list=r"#{'是':'是','否':'否'}"/>
             	<@s.fielderror><@s.param>member.isoem</@s.param></@s.fielderror>
             </td>
           </tr>   
          <tr>
             <td class="table_name">营业执照复印件:</td>
             <td colspan="3">
             <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue1"></div>
	              			   <@s.textfield name="member.reg_no_path" id="uploadresult1" cssStyle="width:300px;"  value="${(member.reg_no_path)?if_exists}"/>
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile1" id="uploadifyfile1"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult1');"/>
	              			<script>uploadImg("uploadifyfile1","uploadresult1","fileQueue1");</script>
             			</td>
             			<td>
             			   <@s.fielderror><@s.param>member.reg_no_path</@s.param></@s.fielderror>
             			</td>
             		</tr>
             	</table>       
             </td>
           </tr>                            
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="member.cust_id"/>
	       <@s.hidden name="member.mem_type" value="0"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <!--所属分类插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
		   <!--所属分类插件隐藏字段结束区域-->
		   <!--所属地区插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
		   <!--所属地区插件隐藏字段结束区域-->
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Member_list.action','');"/>
	     </div>	 
	         
	  </@s.form>
	  
   </div>
</div>
</div>
<div class="clear"></div>
<!--展示预览图片-->
<div class="wrap" id="displaypicture" style="display:none;">
	    <div  align="right"> <a onclick="closeshow();"  href="###" ><img src="/include/components/upload/close.png" /></a></div>
		<a onclick="scrollHori.start(this);" class="rollMenu" id="rollLeft" href="javascript:;">&#8249;</a>
		<a onclick="scrollHori.start(this);" class="rollMenu" id="rollRight" href="javascript:;">&#8250;</a>
		<div id="rollBox">
			<ul id="rollList">
			</ul>
		</div>	
</div>
<!--展示预览图片end-->
<style type="text/css">
     .zitd{width:100px;text-align:right;}
     .zitxt{width:80px;}
     .datenum{width:20px;}
     .attr{border:1px solid #E3E3E3;}
	 .oper_add{color:#990000;}
	 .oper{margin-left:20px;color:#990000;}
	 .cert_td1{width:160px;padding-right:20px;text-align:right;}
</style>
  </body>
</html>