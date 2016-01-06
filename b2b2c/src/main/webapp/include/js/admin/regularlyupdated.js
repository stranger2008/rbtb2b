/*
*功能：系统定时更新修改XML文件管理
*主要有：初始化加载XML数据绑定到页面上、初始化选中下拉框、绑定下拉的时间和日期、执行更新XML的值的方法
*/
 $(document).ready(function(){
      upfirstdata();
 });
//初始化加载XML数据绑定到页面上
function upfirstdata()
{
  var rdxmltext=$("#rdxmls").val();
  var strrdxml=rdxmltext.split("##&&");
  var len=strrdxml.length;
  var straddhtmltitle='<tr style="background:url(images/top_tr.gif) repeat-x;">'
    					+'<td width="20%" align="center" class="top_td show_top" style="font-weight:bold;">任务名称</td>'
    					+'<td width="40%" align="center" class="top_td show_top" style="font-weight:bold;">更新频率</td>'
    					+'<td width="10%" align="center" class="top_td show_top" style="font-weight:bold;">状态</td>'
    					+'<td width="10%" align="center" class="top_td show_top" style="font-weight:bold;">操作</td></tr>';
  var straddhtmltitetwo="";
  var straddhiddenhtml="";
  var alladdhtmltext="";
  for(var i=0;i<strrdxml.length;i=i+2)
   {
       var sdate=strrdxml[i+1].toString();
        straddhtmltitetwo+='<tr bgcolor="#FFFFFF"><td align="center"><p id="titles_'+i+'">'+strrdxml[i]+'</p></td><td align="center">'
        +'每隔<select id="datess_'+i+'" ></select>天,每天<select id="hourss_'+i+'" ></select>时</td>'
        +'<td align="center" style="color:green;">正常</td><td align="center"><a href="#" style="color:blue;">[关闭]</a></td></tr>';
   }
   alladdhtmltext=straddhtmltitle+straddhtmltitetwo;
   $("#alladdhtmltext").html(alladdhtmltext);
   //添加天数和小时下拉框选项，小时为：1-24，天数为1-31hu35272
   for(var i=0;i<strrdxml.length;i=i+2)
   {
     var strdates="#datess_"+i;
     var strhours="#hourss_"+i;
     addselects(strhours,strdates);//日期和时间下拉选择值
   }
   //获取xml文件取得的表达式，读取天数和小时
   for(var i=0;i<strrdxml.length;i=i+2)
   {
     var num=i+1;
     var strtexts=strrdxml[i+1].toString();
     //0 0 0 1/5 * *
     //获取小时
     var strlen=3;
     var strname1=strtexts.substring(strlen+1,strtexts.length-1);
     var strlen1=strname1.indexOf(" ");
     var strname2=strname1.substring(0,strlen1);
     //获取天数
     var strlastlen=strtexts.lastIndexOf("/");
     var strname3=strtexts.substring(strlastlen,strtexts.length);
     var strlen3=strname3.indexOf(" ");
     var strname4=strname3.substring(1,strlen3);
     var strdates="datess_"+i;
     var strhours="hourss_"+i;
     var obj1 = document.getElementById(strdates);
     var obj2 = document.getElementById(strhours);
    jsSelectItemByValue(obj1,strname4);//初始化根据XML存储的日期，选中下拉框的值
    jsSelectItemByValue(obj2,strname2);//初始化根据XML存储的时间，选中下拉框的值
         
   }
 }
 //初始化选中下拉框
function jsSelectItemByValue(objSelect,objItemText)
{      
    for (var i = 0; i < objSelect.options.length; i++) {        
        if (objSelect.options[i].text == objItemText) {        
            objSelect.options[i].selected = true;        
            isExit = true;        
            break;        
        }        
    }              
          
}
//绑定下拉的时间和日期
function addselects(name1,name2)
{
 var strhours="";
 for(var i=0;i<24;i++)
 {
   var num=i;
   strhours+="<option value="+num+" >"+num+"</option>";
 }
var strdates="";
for(var j=0;j<30;j++)
 {
  var nums=j+1;
   strdates+="<option value="+nums+">"+nums+"</option>";
 }
 $(name1).html(strhours);
 $(name2).html(strdates);
}
//执行更新XML的值的方法
  function uxmldata()
  {
     //* * 0/23 0/31 * ?
      var rdxmltext=$("#rdxmls").val();
      var strrdxml=rdxmltext.split("##&&");
      var strdate="";
      var strtime="";
      var opdate="";
     for(var i=0;i<strrdxml.length;i=i+2)
     {
       var strdates="datess_"+i;
       var strhours="hourss_"+i;
       var obj1 = document.getElementById(strdates);
       var obj2 = document.getElementById(strhours);
       strdate=obj1.options[obj1.selectedIndex].value;
       strtime=obj2.options[obj2.selectedIndex].value;
       var dates="";
       var times="";
       if(strdate=="0")
       {
        dates="*";
       }
       else
       {
        dates="1/"+strdate;
       }
       if(strtime=="0")
       {
        times="*";
       }
       else
       {
        times="0/"+strtime;
       }
        opdate+="* * "+times+" "+dates+" "+"* ?"+"@@";
     }
       var oplen=opdate.length;
       opdate=opdate.substring(0,oplen-2);
       //将页面取得值，传到action处理数据
        $.ajax({  	 
               type: "post",    	     
               url: "/regularly-updated!updatedRegularly.action?opdate="+opdate+"&ajaxRequestRandom="+Math.random(),       
               datatype:"json",
               async:false,
               success: function(data)
               {
                 if(data=="1")
                  {
                    alert("修改定时更新成功！");
                    location.reload();
                  }
                 if(data=="2")
                 {
                  alert("修改定时更新失败！");
                 }
   	            } 
            });
  }