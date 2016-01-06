function addvotes()
{
  var checkvalue="",radvalue="";
  var ismultis=$("#ismultis").val();
  var flage;
  if(ismultis=="checkbox")//多选
  {
    flage=ifcheckvalues();
    if(flage==false)
    {
     alert("请至少选择一项投票选项！");
    }
    else
    { alert("投票成功");
      document.forms[0].submit();
     
    }
  }
  else if(ismultis=="radio")//单选
  {
    flage=ifcheckvalues();
    if(flage==false)
    {
     alert("请选择其中一项投票选项！");
    }
    else
    { alert("投票成功");
      document.forms[0].submit();
     
    }
  }
  
}
 function ifcheckvalues()
 {
   var flag = false;
   var checks = document.getElementsByTagName('input');//得到所有input
     for(var i=0;i<checks.length;i++){
		  if(checks[i].checked){
		     flag=true;
		      break;
		    }
	    }
  return flag;
}