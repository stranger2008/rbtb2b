/*
*功能：系统产品管理页面
*主要有：选中审核未通过时候，显示可以输入未通过理由文本框、审核资讯的时候，选择审核未通过的时候，判断是否有输入审核未通过的理由
*更新资讯的时候，选择更新未通过的时候，判断是否有输入更新未通过的理由、更新信息页面的重置功能
*/
//选中审核未通过时候，显示可以输入未通过理由文本框
function getRadioValueAudit(name) {
	var radioes = document.getElementsByName(name);
	for (var i = 0; i < radioes.length; i++) {
		if (radioes[i].checked) {
			if (radioes[i].value == "2") {
				document.getElementById("mytd1").style.display = "";
				document.getElementById("mytd3").style.display = "";
				document.getElementById("mytd2").style.display = "";
				document.getElementById("mytd3").value = "";
			} else {
				document.getElementById("mytd1").style.display = "none";
				document.getElementById("mytd3").style.display = "none";
				document.getElementById("mytd2").style.display = "none";
			}
		}
	}
}
 //审核产品的时候，选择审核未通过的时候，判断是否有输入审核未通过的理由；
     function noreasronAudit()
     {  
        var radioes = document.getElementsByName("product.info_state"); 
        var flages="0";
        for(var i=0;i<radioes.length;i++)
        {   
            if(radioes[i].checked)
           	  {
           			 if(radioes[i].value=="2")
            			 {
            				  var novalue=document.getElementById("mytd3").value;
            				  if(novalue =='')
            					 {
             						alert("请输入审核未通过的理由！");
             						return;
             					 }
             					  else
           				    	 {
           				 			document.forms[0].action='/admin_Product_auditState.action';
           							 document.forms[0].submit();
           						 }
           				 }
           				 else
           				 {
           				 document.forms[0].action='/admin_Product_auditState.action';
           				 document.forms[0].submit();
           				 }
           				 flages="1";
        	 }
        }
        if(flages=="0")
        {
        alert("请选择审核状态！");
        }
     }
 
  //更新产品的时候，选择更新未通过的时候，判断是否有输入更新未通过的理由；
     function noreasronUpdateAudit()
     {  
        var radioes = document.getElementsByName("product.info_state"); 
        var flages="0";
        for(var i=0;i<radioes.length;i++)
        {   
            if(radioes[i].checked)
           	  {
           			 if(radioes[i].value=="2")
            			 {
            				  var novalue=document.getElementById("mytd3").value;
            				  if(novalue =='')
            					 {
             						alert("请输入审核未通过的理由！");
             						return;
             					 }
             					  else
           				    	 {
           				 			document.forms[0].action='/admin_Product_update.action';
           							 document.forms[0].submit();
           						 }
           				 }
           				 else
           				 {
           				 document.forms[0].action='/admin_Product_update.action';
           				 document.forms[0].submit();
           				 }
           				 flages="1";
        	 }
        }
        if(flages=="0")
        {
        alert("请选择审核状态！");
        }
     }
 //更新信息页面的重置功能
     function resetModify()
     {
       var id=document.getElementById("id").value;//获取ID
       var pages=document.getElementById("page").value;//获取页码
       var pagesize=document.getElementById("psize").value;//获取页数
       window.location.href="/admin_Product_view.action?product.product_id="+id+"&pages="+pages+"&pagesize="+pagesize;
     }

