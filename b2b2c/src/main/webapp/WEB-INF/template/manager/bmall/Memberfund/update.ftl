<html>
<head>
<title>查看资金</title>
</head>
<body>
	<div class="postion">
  		 <a href="#">账号管理</a><span>></span><a href="#">账号管理</a><span>></span><a href="#">查看资金</a><span>
    </div>
<@s.form action="/bmall_Memberfund_update.action" method="post" validate="true">
<div class="rightside f_right">
     <div class="rpostion"><h2>查看资金</h2></div>
     <div class="base_infor">
       <div class="table_infor f_left">
          <table style="width:750px;" >
          
            <tr><td  class="firsttd">总金额：</td><td>
        	<@s.label name="memberfund.fund_num" cssClass="txtinput" maxLength="9"/>
            </td></tr>
            
            <tr>
             <td  class="firsttd">可用金额：</td>
             <td><@s.label name="memberfund.use_num" cssClass="txtinput" maxLength="10"/></td>
            </tr>  
            
            <tr>
             <td  class="firsttd">冻结金额：</td>
             <td><@s.label name="memberfund.freeze_num" cssClass="txtinput" maxLength="10"/></td>
            </tr>  
 
          </table>
       </div>  
     </div> 
</div>
 </@s.form>
</body>
</html>

