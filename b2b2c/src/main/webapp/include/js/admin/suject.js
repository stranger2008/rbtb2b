 /*
*功能：系统专题管理
*主要有：弹出专题文字新闻的子窗口、弹出专题图片新闻选中的子窗口、绑定选中新闻的DIV
*专题图片新闻的子窗口接收子窗口的ID值和标题值、专题文字新闻的子窗口接收子窗口的ID值和标题值
*删除标题、加载选中的资讯标题、关联资讯分类的树的选择
*/

//弹出专题文字新闻的子窗口
function selectnews()
{
   var title=document.getElementById('titles').value;
   if(title=="")
   {
      alertShow('请输入专题文字新闻关键字！','warning');
   }
   else
   {
   	 var searchValue=encodeURIComponent(encodeURIComponent(title));        
     window.open("/admin_Subject_selectnewslist.action?selecttype=1&title_s="+searchValue+"&ajaxRequestRandom="+Math.random());
   }
}
 
//弹出专题图片新闻选中的子窗口
function selectnewspic()
  {
     var title=document.getElementById('selectnewspics').value;
      if(title=="")
     {
       alertShow('请输入专题图片新闻搜索标题关键字！','warning');
     }
     else
     {
      window.open("/admin_Subject_selectnewslist.action?selecttype=2&litpic_s=img&title_s="+title+"&ajaxRequestRandom="+Math.random());
     }
}

//绑定选中新闻的DIV 其中id:为选中的id串，name：为选中的标题串，newsid：为已经存在的ID串，newdiv：要绑定的DIV ,typeselect:操作类型：1：表示请求的专题文字新闻，2：表示的是请求专题图片新闻
function bangdnewsdata(id,name,newsid,newstitle,newdiv,typeselect)
{
      var strnewsid=$(newsid).val();
      var strnewstitles=$(newstitle).val();
      var strnewsid1="";
      var strnewstitles1="";
      var snid="";
      var sntitle="";
      if(strnewsid!="")
      {
        var strid=id.split(",");
        var strtitle=name.split("#####");
        var stroldid=strnewsid.split(",");
        for(var i=0;i<strid.length;i++)
        {
          	var isflages="0";//用于标记是否出现重复的选项
          	for(var j=0;j<stroldid.length;j++)
          	{
            	if(strid[i].toString()==stroldid[j].toString())
            	{
              		isflages="1";
              		break; 
            	}
          	}
           if(isflages=="0")
           	{
             	snid+=strid[i].toString()+",";
             	sntitle+=strtitle[i].toString()+"#####";
           	}
           	else
           	{
            	 isflages="0";
           	}
       	 }
        strnewsid1=snid+strnewsid;
        strnewstitles1=sntitle+strnewstitles;
        $(newsid).val(strnewsid1);
        $(newstitle).val(strnewstitles1);
        addnewhtml(strnewsid1,strnewstitles1,newdiv,typeselect);
      }
      else
      {
       $(newsid).val(id);
       $(newstitle).val(name);
       addnewhtml(id,name,newdiv,typeselect);
      }
}
 //专题图片新闻的子窗口接收子窗口的ID值和标题值
 function setNewsData (id, name) {
      bangdnewsdata(id,name,"#newsids","#newstitles","#newstitles1","1");
 }
 //专题文字新闻的子窗口接收子窗口的ID值和标题值
function setNewsPicData (id, name) {
      bangdnewsdata(id,name,"#newspicids","#newspictitles","#newstitlespic1","2");
}
 //删除标题的delid：要删除的ID，addids：对应的操作模块DIV的ID,selecttype:操作类型：1：表示请求的专题文字新闻，2：表示的是请求专题图片新闻
function deletenewhtml(delid,addids,selecttype)
{   

      var did1="";
      var dtitle="";
      if(selecttype=="1")
      {
         did1=$("#newsids").val();
         dtitle=$("#newstitles").val();
      }
      else
      {
         did1=$("#newspicids").val();
         dtitle=$("#newspictitles").val();
      }
      var strdidss1=did1.split(",");
      var strtitless=dtitle.split("#####");
      var delnewsid="";
      var delnewstitle="";
      for(var k=0;k<strdidss1.length;k++)
      {
         if(strdidss1[k].toString()!=delid)
         {
         delnewsid+=strdidss1[k].toString()+",";
         delnewstitle+=strtitless[k].toString()+"#####";
         }
      }
      var delnewsidlength=delnewsid.length;
      var delnewstitlelength=delnewstitle.length;
      delnewsid=delnewsid.substring(0,delnewsidlength-1);
      delnewstitle=delnewstitle.substring(0,delnewstitlelength-5);
      if(selecttype=="1")
      {
         $("#newsids").val(delnewsid);
         $("#newstitles").val(delnewstitle);
      }
      else
      {
         $("#newspicids").val(delnewsid);
         $("#newspictitles").val(delnewstitle);
      }
         addnewhtml(delnewsid,delnewstitle,addids,selecttype);//重新绑定数据
}
//加载选中的资讯标题 id：选中的ID串，name：选中的标题串，addids：要添加的DIV块；selecttype:操作类型：1：表示请求的专题文字新闻，2：表示的是请求专题图片新闻
 function addnewhtml(id,name,addids,selecttype)
 {
   var addhtml1="";
   if(id!="")
   { 
     addhtml1="<ul >";
     if(!id.indexOf(",")>-1)
     {
  		var ids=id.split(",");
  		var names=name.split("#####");      		
  		for(var s=0;s<ids.length;s++)
  		{
    			addhtml1+="<li><span style='font-weight:bold;color:#483D8B;'>"+(s+1)+".</span><span style='color:cecece;padding-left:5px;'>"+names[s].toString()+"</span>"
    			+"<span style='color:brown;padding-left:10px;text-align:right;'><a style='color:brown;' href='#' onClick=\"deletenewhtml(\'"+ids[s].toString()+"\',\'"+addids+"\',\'"+selecttype+"\')\"> 删除</a></span></li>";
  		}
  	}
  	else 
  	{
  	    addhtml1+="<li><span style='font-weight:bold;color:#483D8B;'>1.</span><span style='color:cecece;padding-left:5px;'>"+name+"</span>"
    		+"<span style='color:brown;padding-left:10px;text-align:right;'><a style='color:brown;' href='#' onClick=\"deletenewhtml(\'"+id+"\',\'"+addids+"\',\'"+selecttype+"\')\"> 删除</a></span></li>";
  	}
  	addhtml1+="</ul>";
   }
   $(addids).html(addhtml1);
 }
        
////////////////////关联资讯分类的树的选择/////////////////////////////////////
//获取选中的分类
function updateAreaSortNo(actionName){
      var admin_area_id='';
      var chks = document.getElementsByTagName('input');//得到所有input
        for(var i=0;i <chks.length;i++)
        { 
          if(chks[i].type.toLowerCase() == 'checkbox')
           {
              if(chks[i].checked)
              {
             	 //得到选中为checkbox的input
              	 admin_area_id+=chks[i].value+',';
              }             
            }
         }
         var len=admin_area_id.length;
         var sort_no_id=admin_area_id.substring(0,len-1);
         document.getElementById('linkcats').value = sort_no_id;//用于隐藏所有的ID
         
         //document.forms[0].action=actionName;
	     //document.forms[0].submit();
	      
}

//获取选中的分类
function updateAreaSortNo(actionName){
      var admin_area_id='';
      var chks = document.getElementsByTagName('input');//得到所有input
        for(var i=0;i <chks.length;i++)
        { 
          if(chks[i].type.toLowerCase() == 'checkbox')
           {
              if(chks[i].checked)
              {
             	 //得到选中为checkbox的input
              	 admin_area_id+=chks[i].value+',';
              }             
            }
         }
         var len=admin_area_id.length;
         var sort_no_id=admin_area_id.substring(0,len-1);
         document.getElementById('linkcats').value = sort_no_id;//用于隐藏所有的ID         
         document.forms[0].action=actionName;
	     document.forms[0].submit();	      
}


//获取选中的分类
function getcheckCat(){
      var admin_area_id='';
      var chks = document.getElementsByTagName('input');//得到所有input
        for(var i=0;i <chks.length;i++)
        { 
          if(chks[i].type.toLowerCase() == 'checkbox')
           {
              if(chks[i].checked)
              {
             	 //得到选中为checkbox的input
              	 admin_area_id+=chks[i].value+',';
              }             
            }
         }
         var len=admin_area_id.length;
         var sort_no_id=admin_area_id.substring(0,len-1);
         document.getElementById('linkcats').value = sort_no_id;//用于隐藏所有的ID
}


//实现树的全选功能
function checkStatus(no,chkBox)
     {
      var chks = document.getElementsByTagName('input');//得到所有input
      for(var i=0;i <chks.length;i++)
      { 
          
          if(chks[i].type.toLowerCase() == 'checkbox')
          {
              //得到所名为checkbox的input
              if(chks[i].className == no)
              {
                  //ID等于传进父目录ID时
                  chks[i].checked = chkBox.checked;//保持选中状态和父目录一致
                  this.checkStatus(chks[i].value,chks[i]);//递归保持所有的子目录选中
              }
          }
      }
  }
  
/////////////////////////////////关联资讯分类的树的选择/////////////////////////////////////////////////////
$(document).ready(function(){ 	
    ///////////////////////绑定关联资讯//////////////////////
	   var newsid1=$("#newsids").val();
	   var newspicdi1=$("#newspicids").val();
	   $.ajax({  	 
           type: "post",    	     
           url: "/admin_Subject_getnewstitle.action?newsid="+newsid1+"&newspicid="+newspicdi1+"&ajaxRequestRandom="+Math.random(),       
           datatype:"json",
           async:false,
           success: function(data){
              var strtitle=data.split("&&&&&");
              if(newsid1!=""&&strtitle[0]!="")
              {
                var strnewstitle=strtitle[0];
                $("#newstitles").val(strnewstitle);
                addnewhtml(newsid1,strnewstitle,"#newstitles1","1");
              }
              if(newspicdi1!=""&&strtitle[1]!="")
              {
                var strnewpictitle=strtitle[1];
                $("#newspictitles").val(strnewpictitle);
                addnewhtml(newspicdi1,strnewpictitle,"#newstitlespic1","2");
              } 
  	         }  
          });
          ///////////////////////绑定关联结束///////////////////////////// 	   
          ////////////////////分类树的回选////////////////////////////////////
	     var opl=$("#linkcats").val();		    
	     var orr=opl.split(',');     	     
	     for(var i = 0; i < orr.length; i++){			         
	             $("input:checkbox").each(function(){	 
	                 if(orr[i]==$(this).val())
	                 {
	                   this.checked=true;  
	                 }		                 
	             });
            }	 
         ////////////////////分类树的回选END////////////////////////////////////           

});  


