var un="#searchText";
var s_content="请输入搜索内容";  
$(document).ready(function(){   
         //根据模块值搜索
	     $("#searchButton").click(function(){
	          var searchType=$("#modselect").val();
	          var wd=encodeURIComponent(encodeURIComponent($("#searchText").val()));
	          if($(un).val()!='' && $(un).val()!=s_content){//符合条件提交表单
	             searchClass(searchType,wd);  
	          }      
	     });	
	     //根据模块的关键字值搜索
	     $("#hotsearch a").click(function(){
	          var searchType=$("#modselect").val();
	          var searchValue=$(this).html();
   	          searchValue=encodeURIComponent(encodeURIComponent(searchValue));
	          searchClass(searchType,searchValue);          
	     });
	     //用于select的回选		 
	     backselect();
	     //根据不同的模块值显示出不同的模块的热门搜索
	     var mtype=$("#modselect").val();
	     $("#"+mtype,"div").css("display","block");
	     
	     $("#modselect").change(function(){
	          $("#hotsearch div").css("display","none");
	          var mtype=$("#modselect").val();
	          $("#"+mtype,"div").css("display","block");
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
	     if($("#searchTextVal").val()!="${searchText?if_exists}"){
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
//根据不同的模块值显示SELECT的对应值
function backselect(){
       //获取url地址      
       var urlHref = location.href;
       //清除导航样式
       $("#navlist li").removeClass("nav_hover");
       var nav_class="";
       var mod_type=""
       //匹配导航与URL高亮
       $("#navlist li").each(function(){
          var nav_id= $(this).attr("id");         
          if(nav_id!=""&&urlHref.indexOf(nav_id)>-1){                   
              $(this).addClass("nav_hover");
              var nav_style=$(this).attr("class");               
	          //当导航样式不为空,赋值  
	          if(nav_style!=""){       
                 nav_class=nav_style;
                 mod_type=nav_id;
	          }
          }  
        });  
        //当导航中不存在该样式时，赋于首页样式
        if(nav_class!=""){
             $("#navlist li").eq(0).removeClass("nav_hover");
             //改变搜索模块框的值
             $("#modselect").val(mod_type);
        }else{
           //判断是否存在人才与招聘的搜索类型
           if($("#searchtype").length>0){
                var seaType=$("#searchtype").val();
                if(seaType=="2"){
                  //select框反选
                  $("#modselect").val("job");  
                  //例外处理，把导航人才的位置加亮
                  $("#navlist #resume").addClass("nav_hover");
                }                        
           }else{
             $("#navlist li").eq(0).addClass("nav_hover");
             //默认选中供应
             $("#modselect").val("supply");    
           }
        }     
}
//用于select的反选
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
 //热搜关键字
function serarchkeywords(searchType,searchValue)
{
   searchValue=encodeURIComponent(encodeURIComponent(searchValue));
   searchClass(searchType,searchValue);
}
//对于单个搜索框，没有下拉选择模块类型的搜索
function SearchAllWords(searchType,searchID)
{
  var searchValue=$("#"+searchID).val();
  serarchkeywords(searchType,searchValue);
}
