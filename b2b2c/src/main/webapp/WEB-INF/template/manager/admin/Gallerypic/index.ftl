<html>
  <head>
    <title>查看图片</title>
  </head>
  <body>
 <@s.form action="/admin_Gallerypic_list.action" method="post">
 <@s.hidden name="gallerypic.gal_id"/>
  <@s.hidden name="gal_desc" id="gal_desc"/>
 <div class="main_index f_left">
 
  <div class="pageLocation">当前位置:功能模块 > 图库管理 > 查看图片</div>
  
  <div class="main_top">
    <ul class="main_top_ul">
      <li class="tj"><a onclick="linkToInfo('/admin_Gallerypic_add.action','gallerypic.gal_id=${gallerypic.gal_id?if_exists}');">添加图片</a></li>
      <li class="sc"><a onclick="checkInfo('gallerypic.pic_id','/admin_Gallerypic_updateDesc.action')">批量保存</a></li>
      <li class="sc"><a onclick="delInfo('gallerypic.pic_id','/admin_Gallerypic_delete.action?gallerypic.gal_id=${gallerypic.gal_id?if_exists}')">删除</a></li>
      <li class="sx"><a onclick="linkToInfo('/admin_Gallery_list.action','');">返回列表</a></li>
   </ul>
   <div class="clear"></div> 
  </div>
<div class="main_cont">
    <div>
	    <table border='0'><tr><td><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('gallerypic.pic_id')"/></td></tr>    
        <tr><td><ul style="height:auto;" id="checkid" >
          <#list gallerypicList as gallerypic>
			   <#list gallerypic as gallerypic>      
			           <li style="float:left;margin:10px;margin-bottom:16px;margin-left:25px; width:200px;">
			           	    <input type="checkbox"   name="gallerypic.pic_id" value="${gallerypic.pic_id?if_exists}" style="float:left;margin-top:75px;"/>
					    	<a href="${gallerypic.img_path?if_exists}" target="_blank">
					    		<img src="${gallerypic.img_path?if_exists}" width="170" height="120" style="float:left;border:1px solid #e1e2e3;" alt="${gallerypic.pic_desc?if_exists}"/>
					    	</a>
					    	<div><@s.textarea name="gallerypic.pic_desc" value="${gallerypic.pic_desc?if_exists}" id="${gallerypic.pic_id?if_exists}"  cssStyle="width:172px;height:70px;margin-left:20px;margin-top:5px;font-size:15px;" maxLength="500" /></div>
			           </li>    
			   </#list>     
   		  </#list>     
        </ul></td></tr>
        </table> 
     </div>
</div>  
  <div class="listbottom">
   ${pageBar?if_exists}
 </div>
  
 <div class="clear"></div> 
 
</div>

<div class="clear"> </div>
<@s.hidden name="gallery.gal_id"/>
</@s.form>
<script type="text/javascript">

//列表批量保存图片描述操作
function checkInfo(field_name,actionName){
	//值拼串
	var filedVal = '';	
	var checks = document.getElementsByName(field_name);
	var count=0;
	var flag=true;
	for(var i=0;i<checks.length;i++){
		if(checks[i].checked){
			count++;
		}
	}	

	if(count==0){
		alertShow('请至少选择一条记录!','warning');
		runCount(3);
		return false;
	}else{	
		$("input[name='gallerypic.pic_id']:checkbox").each(function(){ 
	    if($(this).attr("checked")){
	     var ckid= $(this).val();
	     if($("#"+ckid).val()==""){
	     	alertShow('请填写完整图片描述!','warning');
			runCount(3);
			flag=false;
			return false;
	     }
	     else{
	     	flag=true;
	     }
	        
	    }
	})		
		if(flag){
			art.dialog({
			title: '系统信息提示',
		    content: '<div class="decorate">您确定保存吗？</div>',
		    width: '15%',
		    icon: 'question',
		    yesFn: function () {
		        getgallerydesc();
				document.forms[0].action=actionName;
				document.forms[0].submit();
			    return false;
		    },
		    noText: '关闭',
		    noFn: true //为true等价于function(){}
		    })
	    }
	}
}

 function getgallerydesc()
 {
    var str="";
	$("input[name='gallerypic.pic_id']:checkbox").each(function(){ 
	    if($(this).attr("checked")){
	     var ckid= $(this).val();
	     str+=$("#"+ckid).val()+",";	        
	    }
	})
	str=str.substring(0,str.length-1);
    $("#gal_desc").val(str);
 }

</script>
</body>
</html>











