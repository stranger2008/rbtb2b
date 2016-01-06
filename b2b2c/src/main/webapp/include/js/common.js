

/*
*功能：系统公共脚本，包括，验证数字输入、列表批量删除操作、删除单个操作、列表批量选择操作
*列表筛选弹出层操作、批量推荐与不推荐、控制文本域输入字数
*/
//验证只能输入正整数 >=0
function checkNum(obj)
{
    var num_value=$(obj).val();
    var re =/^(0|([1-9]\d*))$/;
	if (!re.test(obj.value))
	  {
	     if(isNaN(obj.value)){
	        obj.value="";
	        obj.focus();
	        jNotify("该文本框只能输入数字,请正确输入!"); 
	        return false;
	     }
	  }
}

//验证只能输入整数 -1，0，1
 function check_zen_num(obj)
 {
    var re = /^-?[1-9]*(\.\d*)?$|^-?d^(\.\d*)?$/;
	if (!re.test(obj.value))
	  {
	    if(isNaN(obj.value)){
		   obj.value="0";
	       obj.focus();
	       jNotify("该文本框只能输入整数,请正确输入!"); 
	       return false;
	    }
	 }
 }  
 
//列表批量删除操作
function delInfo(field_name,actionName){
	//值拼串
	var filedVal = '';	
	var checks = document.getElementsByName(field_name);
	for(var i=0;i<checks.length;i++){
		if(checks[i].checked){
			filedVal += checks[i].value+',';
		}
	}	
	if(filedVal.indexOf(',') > -1){
		filedVal = filedVal.substring(0,filedVal.length-1);
	}	
	if(filedVal==''){
		alertShow('请至少选择一条记录!','warning');
		return false;
	}else{
		art.dialog({
		title: '系统信息提示',
	    content: '<div class="decorate">您确定删除吗？</div>',
	    width: '15%',
	    icon: 'question',
	    yesFn: function () {
	    	document.getElementById('commonForm').action=actionName;
	    	document.getElementById('commonText').name = field_name;
	    	document.getElementById('commonText').value = filedVal;
	    	document.getElementById('commonForm').submit();	      
	        return false;
	    },
	    noText: '关闭',
	    noFn: true //为true等价于function(){}
	    })
	}
}

//删除单个操作
function delOneInfo(field_name,actionName,infoid){
	art.dialog({
		title: '系统信息提示',
	    content: '<div class="decorate">您确定删除吗？</div>',
	    width: '15%',
	    icon: 'question',
	    yesFn: function () {
	    	document.getElementById('commonForm').action=actionName;
	    	document.getElementById('commonText').name = field_name;
	    	document.getElementById('commonText').value = infoid;
	    	document.getElementById('commonForm').submit();	        
	        return false;
	    },
	    noText: '关闭',
	    noFn: true //为true等价于function(){}
    })
}


//进入修改页面
function linkToInfo(actionName,paraStr){

	var ppstr = paraStr.split("&");
	if(ppstr.length > 0){
		for(var i=0;i<ppstr.length;i++){
			var para = ppstr[i];
			if(para.indexOf("=") > -1){
				var paraSon = para.split("=");
				if(paraSon.length > 1){
					var key = paraSon[0];
					var value = paraSon[1];
					var isExist = isExistsField(key);
					if( isExist != "a" ){
						document.getElementById("rsrvId"+isExist).value = value;
					}else{
						document.getElementById("rsrv"+(i*1+1)).name = key;
	   					document.getElementById("rsrv"+(i*1+1)).value = value;
	   				}
				}
			}
		}
		document.getElementById("commonForm").action=actionName;
	   	document.getElementById("commonForm").submit();
	}
}

function isExistsField(fname){
	var ret = "a";
	var rsrvNum = document.getElementById("rsrvNum").value;
	for(var i=0;i<rsrvNum;i++){
		if( document.getElementById("rsrvId"+i).name == fname){
			ret = i;
			break;
		}
	}
	return ret;
}

//控制textarea框的长度
function set_textarea_length(obj,num){
    var obj_value=$(obj).val();
    if(obj_value.length>num){    
       var get_value=obj_value.substring(0,num);
       $(obj).val(get_value);
       jNotify("该输入框字符数不能超过"+num+"字!"); 
    }
}

//列表批量选择操作
function selectAll(field_name){
	var checkall = document.getElementById('checkall');
	var checks = document.getElementsByName(field_name);
	for(var i=0;i<checks.length;i++){
		if(checkall.checked){
			checks[i].checked = true;
		}else{
			checks[i].checked = false;
		}
	}
}

//列表筛选弹出层操作-开始
function divfixed2(r,name){ 
   var sug=document.getElementById(name);
   sug.style.left=getPosition(r).x+"px"; 
   sug.style.top=getPosition(r).y+r.offsetHeight+"px"; 
   sug.style.position="absolute"; 
   sug.style.display="block"; 
}
//显示搜索框
function showSearch(obj,val){
	var objdiv = document.getElementById(val);
	if(objdiv.style.display=="none"){
		divfixed2(obj,val);
	}else{
		objdiv.style.display="none"; 
	}
}
//关闭搜索框
function closeSearch(val){
	document.getElementById(val).style.display="none"; 
}

function getPosition(el) 
{ 
	for (var lx=0,ly=0;el!=null;lx+=el.offsetLeft,ly+=el.offsetTop,el=el.offsetParent); 
	return {x:lx,y:ly} 
}
//列表筛选弹出层操作-结束

//批量推荐与不推荐开始
function updateRecomState(state,field_name,actionName,recom_id){
        var flag = false;
		var checks = document.getElementsByName(field_name);
		for(var i=0;i<checks.length;i++){
			if(checks[i].checked){
				flag=true;
				break;
			}
		}
		if(flag==false){
			alertShow('请至少选择一条记录!','warning');
	        runCount(3);
			return false;
		}
		if(flag==true){
		    var tip = '';
			if(state=='0'){
				tip = '确认取消推荐吗?';
			}else if(state=='1'){
				tip = '确认推荐吗?';
			}
			art.dialog({
			title: '系统信息提示',
		    content: '<div class="decorate">'+tip+'</div>',
		    width: '15%',
		    icon: 'question',
		    yesFn: function () {
		       document.getElementById(recom_id).value = state;
			   document.forms[0].action=actionName;
			   document.forms[0].submit();
		       return false;
		    },
		    noText: '关闭',
		    noFn: true //为true等价于function(){}
		    });			
		}
}
//批量推荐与不推荐结束
//批量审核通过与未通过开始
//state:审核的状态
//field_name：选择框的名称
//actionName：审核操作的action
//audit_id:隐藏域的审核状态值
function auditInfoState(state,field_name,actionName,audit_id){
        var flag = false;
		var checks = document.getElementsByName(field_name);
		for(var i=0;i<checks.length;i++){
			if(checks[i].checked){
				flag=true;
				break;
			}
		}
		if(flag==false){
			alertShow('请至少选择一条记录!','warning');
	        runCount(3);
			return false;
		}
		if(flag==true){
		    var tip = '';
			if(state=='2'){
				tip = '确认审核不通过吗?';
			}else if(state=='1'){
				tip = '确认审核通过吗?';
			}
			art.dialog({
			title: '系统信息提示',
		    content: '<div class="decorate">'+tip+'</div>',
		    width: '15%',
		    icon: 'question',
		    yesFn: function () {
		       document.getElementById(audit_id).value = state;
			   document.forms[0].action=actionName;
			   document.forms[0].submit();
		       return false;
		    },
		    noText: '关闭',
		    noFn: true //为true等价于function(){}
		    });			
		}
}
//批量审核通过与未通过结束

//审核信息的时候，选择审核未通过的时候，判断是否有输入审核未通过的理由开始
//第一个参数radio框的的name名称,第二个显示拒绝理由框ID，第三个参数理由文本框的ID,第四个是要比较的状态
 function getRadioValue(name,reasonid,noreasonvalue,state)
     {
       var radioes = document.getElementsByName(name);
	   for (var i = 0; i < radioes.length; i++) {
			if (radioes[i].checked) {
				if (radioes[i].value == state) {
					  document.getElementById(reasonid).style.display="";
					  document.getElementById(noreasonvalue).value="";
				}
				else
				  {
				  document.getElementById(reasonid).style.display="none";				
				}
		     }
	     }
     }    
//第一个显示拒绝理由框ID，第二个参数理由文本框的ID,第三个是要比较的状态
function noreasron(name,noreasonvalue,state) {
	var radioes = document.getElementsByName(name);
	var auditcount=0;
	for (var i = 0; i < radioes.length; i++) {
		if (radioes[i].checked) {
		  	if (radioes[i].value == state) {
				var novalue = document.getElementById(noreasonvalue).value;
				if (novalue == "") {
					alertShow('拒绝理由不能为空！','warning');
					return false;
				} 
			}
			auditcount=auditcount+1;
		}
	}	
	if(auditcount<1){
	   		alertShow('请选择审核状态！','warning');
		    return false;
	}
}
//审核信息的时候，选择审核未通过的时候，判断是否有输入审核未通过的理由结束



//将把有控件设置成fasle不可用开始
function disabledCss(){
     var isdisable="must";//定义常量   
     $("select").each(function(){
          $(this).attr("disabled","true")
          $(this).css("color","black");
     });
     $("input:checkbox").each(function(){
          $(this).attr("disabled","true")
          $(this).css("color","black");
     });
    $("textarea[id^="+isdisable+"]").each(function(){
          $(this).attr("disabled","true")
          $(this).css("color","black");              
     });
     $("input:text[id^="+isdisable+"]").each(function(){
          $(this).attr("disabled","true")
          $(this).css("color","black");
     });
}
//将把有控件设置成fasle不可用结束        

//审核页面图片的读取开始,第一个参数为要开到哪个DIV，第二个为获取图片隐藏域中的值
function displayImages(showImage,imageValue){
    var imageVal=$("#"+imageValue).val();
    var image=imageVal.split(",");
    for(var i=0;i<image.length;i++){
       if(image[i]!=""){
          $("#"+showImage).append("<img src="+image[i]+">");
          $("#"+showImage+" img").css({
               "margin-right": "10px", 
               "float":"left",
               "width":"150px",
               "height":"150px",
               "border":"1px dotted #E1E2E3"
          }); 
       }
    }
}
//审核页面图片的读取结束

//获取URL路径传过来的值
function queryString(key)
{
  var regex_str="^.+\\?.*?\\b"+ key +"=(.*?)(?:(?=&)|$|#)"
  var regex=new RegExp(regex_str,"i");
  var url=window.location.toString();
  if(regex.test(url)) return RegExp.$1;
  return undefined;
}


//对查找出的select 进行回选
function selectCheckObj(objid,thisvalue){
	var levelone = document.getElementById(objid);
	if(levelone!=null){
		for (var j = 0; j < levelone.options.length; j++) {
	        if (levelone.options[j].value == thisvalue) {
	            levelone.options[j].selected = true;
	        }
        }
	}
}

//验证RMB类型
function checkThisFloat(f_val){
	f_val=$(this).val();
	//验证是否为数字正则表达式
	var reg = /^(\d){1,8}(\.)?(\d(\d)?)?$/;
	if(!reg.test(f_val)){
		jNotify("请正确输入货币类型!");
		return false;
	}else{
		return true;
	}
}

