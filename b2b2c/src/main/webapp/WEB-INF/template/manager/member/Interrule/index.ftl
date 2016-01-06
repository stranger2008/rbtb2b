<html>
<head>
    <title>积分规则管理</title>   
</head>
<body>
<@s.form action="/member_Interrule_list.action" method="post">
  <div class="cont_main">
   <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:交易管理>我的资金>积分规则</td>
 	</tr>
	</table>
    <div class="finder_bar_bg">
    </div>
    <table width="100%" cellspacing="0" >
      <tr style="background:url(images/top_tr.gif) repeat-x;">
         <td width="50%" align="center" class="top_td">规则名称</td>
         <td width="50%"  align="center" class="top_td">操作积分数</td>
      </tr>
      <#list interruleList as rule>
        <tr bgcolor="#FFFFFF"> 
	          <td align="center" width="50%">${rule.rule_name?if_exists}</td>
	          <td align="center" width="50%">${rule.internum?if_exists}</td>
        </tr>
      </#list>
    </table>
   <div class="listbottom">
   ${pageBar?if_exists}
 </div>
 <div class="clear"></div>
 </div>
</div>
</div>
</@s.form>
</body>
</html>
