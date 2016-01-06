var un="#searchText";
var s_content="请输入搜索内容";  
$(document).ready(function(){   
         //根据模块值搜索
	     $("#searchButton").click(function(){	   
	          var wd=encodeURIComponent(encodeURIComponent($("#searchText").val()));
	          if($(un).val()!='' && $(un).val()!=s_content){//符合条件提交表单
	            searchClass("classified",wd);  
	          }      
	     });	
	     //根据模块的关键字值搜索
	     $("#hotsearch a").click(function(){
	          var searchValue=$(this).html();
   	          searchValue=encodeURIComponent(encodeURIComponent(searchValue));
	          searchClass("classified",searchValue);          
	     });    
	     //获得焦点事件
		 $(un).focus(function(){	
			 if($(un).val()=='' || $(un).val()==s_content){	     
			     $(un).val("");
			     $(un).removeClass("search_txt");
			  }
		 });	
		 //失去焦点事件
		 $(un).blur(function(){
		     if($(un).val()==''){
			    $(un).val(s_content);
			    $(un).addClass("search_txt");
			 }
		 });	
	     //判断是否要保留值
	     if($("#searchTextVal").val()!="${searchText?if_exists}"&&$("#searchTextVal").val()!=""){
	         $("#searchText").val($("#searchTextVal").val());
	         $(un).removeClass("search_txt");//去除样式
	     }   
	     //列表页初始化分类和地区的高度
	     inti_height("dis_more_1,dis_more_2",60);
});		
//搜索的ACTION类
function  searchClass(searchType,wd){
     location.href="/portal/"+searchType+"!list.action?wd="+wd;  
}
//获取URL中的模块值用于反选
function queryString(key)
{
  var regex_str="^.+\\?.*?\\b"+ key +"=(.*?)(?:(?=&)|$|#)"
  var regex=new RegExp(regex_str,"i");
  var url=window.location.toString();
  if(regex.test(url)) return RegExp.$1;
  return undefined;
}

//设置主页
function setMyHome() {
	if (document.all) {
		document.body.style.behavior = "url(#default#homepage)";
		document.body.setHomePage(location.href);
	} else {
		if (window.sidebar) {
			if (window.netscape) {
				try {
					netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
				}
				catch (e) {
					alert("该操作被浏览器拒绝，如果想启用该功能，请在地址栏内输入 about:config,然后将项 signed.applets.codebase_principal_support 值该为true");
				}
			}
			var prefs = Components.classes["@mozilla.org/preferences-service;1"].getService(Components.interfaces.nsIPrefBranch);
			prefs.setCharPref("browser.startup.homepage",location.href);
		}
	}
}
//加入收藏
function addFavorite() {   
   if (document.all) {   
      window.external.addFavorite(location.href,document.title);   
   } else if (window.sidebar) {   
     window.sidebar.addPanel(document.title,location.href, "");   
   }  
}

//验证只能输入正整数
 function checkNum(obj)
 {
    var re =/^(0|([1-9]\d*))$/;
	if (!re.test(obj.value))
	  {
	     if(isNaN(obj.value)){
		    obj.value="";
	        obj.focus();
	        return false;
	     }
	  }
  } 
 
 //初始化设定高度
 function inti_height(dis_more,set_height){
    var dis=dis_more.split(",");
    for(var i=1;i<dis.length+1;i++){
        var dis_class=".dis_more_"+i;
 		var height=$(dis_class).height();
		if(height>set_height){
	    	$(dis_class).css("height",set_height+"px");
	    }else{
	      $(dis_class).css("height",height+"px");   
	    }    
    }
 }  
 
 //列表显示更多
 //第一个参数class,第二个参数高度
 function displayMore(more_id,set_height,obj){
    var dis="."+more_id;
    var height=$(dis).css("height");   
    if(set_height==height){
    	$(dis).css("height","auto");
        $(obj).html("[精简显示]"); 
    }else{
      $(dis).css("height",set_height);   
      $(obj).html("[显示更多]"); 
    }    
 }
