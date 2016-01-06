<html>
  <head>
    <title>更新栏目页</title>
	<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
	 <script type="text/javascript" src="/include/js/admin/channel.js"></script>
	  <link rel="StyleSheet" href="/include/components/dtree/dtree.css" type="text/css"  />
	<script type="text/javascript" src="/include/components/dtree/dtree.js" ></script>
  </head>
<body >
<div class="tj_main f_left">
   <div class="pageLocation">
 	   当前位置:网站管理 > 网站更新 > 更新栏目页
   </div>
   <div class="tj_main_cont">
   	<@s.form  method="post" validate="true">
       <table class="wwtable" cellspacing="1" cellpadding="8" >
          <tr >
          
          <td height="65px" width="10%"> 
          <input  value="更新全部栏目页" type="button" onclick="doHtmlPage('all','');">
          
          </td>
          <td align="left" >
          <div id="loading" style="display:none;">
            
           <img src=/include/images/admin/upLoading.gif style="width:30px; height:30px;">&nbsp;更新中...
            
          </div>
           
          <font color="red"><label id="msg" ></label></font>
          </td>
          
          </tr>
             <tr>
	             <td  colspan="2" >
		            <script type="text/javascript">
				       d = new dTree('d');
				       d.add(0,-1,'更新网站栏目&nbsp;');	
				       <#list channelList as syschannel>
				       d.add(${syschannel.ch_id?if_exists},${syschannel.up_ch_id?if_exists},'${syschannel.ch_name?if_exists} &nbsp;<a style="cursor:pointer;" onclick="doHtmlPage(\'one\',\'${syschannel.ch_id?if_exists}\');">仅更新所选栏目</a>&nbsp;<a style="cursor:pointer;"  onclick="doHtmlPage(\'many\',\'${syschannel.ch_id?if_exists}\');">更新子级栏目</a>','','','','');
				       </#list>
				       document.write(d);
			        </script>
	             </td>
             </tr>
         </table>
	  </@s.form>  
   </div>
</div>
</div>
<div class="clear"></div>
      
       
  </body>
</html>