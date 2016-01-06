
<script type="text/javascript">
 function changetoptype(tid,tcss,clearid,type)
 {
   $("#"+tid).attr("class",tcss);
   $("#"+clearid).attr("class","");
   $("#mallsearchType").val(type);
 }
 $(document).ready(function(){
    var stype=$("#mallsearchType").val();
    if(stype!=null&&stype!="")
    {
      $("#comtype_id").addClass("se_dp_h");
      $("#goodtype_id").removeClass();
     }
    //加载地区
	$.ajax({  	 
	     type: "post",    	     
	     url: "/mall/goods!getareaName.action",       
	     datatype:"json",
	     success: function(data){   
	     var goinWebStr = data.split(",");
	     //国家
	     $("#countryweb").html(goinWebStr[0]);
	     //地区
	     $("#goinWeb").html(goinWebStr[1]);
	     //地区英文名称
	     $("#countryf").html("<a href=\'/mall/"+goinWebStr[2]+"/country.html\'>【切换国家】</a>");
	     if(goinWebStr[3]!=''&&goinWebStr[2]!=goinWebStr[3]){
	     $("#areaf").html("<a href=\'/mall/"+goinWebStr[3]+"/area.html\'>【切换城市】</a>");
	     }
	      $("#otherareaname").val(goinWebStr[0]+" "+goinWebStr[1]);
	     if(goinWebStr[0]==goinWebStr[1]){
	     $("#xm").hide();
	     }
	  }
	   });  	
});
</script>
<script type="text/javascript" src="/templets/bmall/js/goods.js"></script>
<div class="user_top">
  <div class="user">
      <P class="welcome f_left">您好，欢迎来到${cfg_mallwebname?if_exists}！  
      <script src="/include/ismalllogin.jsp"></script>
       <#if cfg_area_shop?if_exists=='0'>
          &nbsp;
	      <span class="area">
	       <#if cfg_country_filter?if_exists=='0'>
	      <span id="countryweb"></span> <span id="countryf"></span>
	      </#if>
	      <span id="xm"><span id="goinWeb"></span></span>
	      <span id="areaf"> </span>
          </span>
     </#if>
      </P>
      <P class="help f_right">
      	<span class="order"><a href="/bmall_Goodsorder_list.action">我的订单</a></span>
      	<span class="help_sp"><a href="/mall/goods!mallcart.action"  class="help_car">购物车</a></span>
      	<span><a href="/mallhelp.html" target="_blank">帮助中心</a></span>
      </P>
  </div>
</div>

<div class="logo_top w980">
  <a href="/mallindex.html"><img src="/templets/bmall/images/logo_06.gif"  class="f_left"/></a>
  <div class="search f_left">
   <#if web_openmall?if_exists=='0'>
     <ul class="se_dp">
     	<li id="goodtype_id" onclick="changetoptype('goodtype_id','se_dp_h','comtype_id','product')" class="se_dp_h" >商品</li>
     	<li id="comtype_id" onclick="changetoptype('comtype_id','se_dp_h','goodtype_id','shop')" >店铺</li>
     </ul>
     </#if>
     <br class="clear"/>
     <div class="sea_main" >
        <input type="text" class="sea_text" id="selValue" value="${selvaluep?if_exists}"/>
        <input type="button" class="sea_button" onclick="return pselect('p','');"/>
        <input type="hidden" id="catstr" name="catstr" value="${catstr?if_exists}"/>
        <input type="hidden" id="mallsearchType"  value="${mallsearchType}" />
        <@s.hidden id="catString" name="catString"/>
     </div>
  </div>
  <a href="#"><img src="/templets/bmall/images/phone.gif"  class="f_right"/></a>
</div>
<div class="clear"></div>

	                     
<!--导航开始-->
<div class="nav">
   <div class="nav_main">
       <ul class="nav_main_cont">
	       <#assign ncount=1>
	       <#list navList as navs>
	            <#if ncount  lt 10>
                     <#assign nav_url=navs.link_url?if_exists>
                     <#assign nav_top=nav_url.replace("/mall-goodslist-","").replace(".html","").replace(" ","")>
	                     <li class="<#if nav_top==topString>nav_h</#if>">
	                     	<a href="${nav_url?if_exists}" target="${ navs.isopen?if_exists}"  >${ navs.nav_name?if_exists}</a>
	                     </li>
     				 <#assign ncount=ncount+1>
	      		</#if>
	       </#list>

       </ul>
      <ul class="nav_tg">
           <#assign ncountend=11>
	       <#list navList as navs>
	            <#if navs_index  gt 8  && navs_index lt ncountend>
	           			 <#assign nav_url=navs.link_url?if_exists>
                     	 <#assign nav_top=nav_url.replace("/mall-goodslist-","").replace(".html","").replace(" ","")>
	                     <li class="<#if nav_top==topString>nav_h</#if>" <#if -1 <nav_top.indexOf('group') >id="group"</#if>>
	                     <a href="${nav_url?if_exists}" target="${ navs.isopen?if_exists}"  >${ navs.nav_name?if_exists}</a>
	                     </li>
	      		</#if>
	       </#list>
       </ul>
   </div>
   <div class="clear"></div>
   <script text="javascript">
      var hrefurl = window.location.href;
      if(hrefurl.indexOf('group') > -1){
          $(".nav_h").removeClass("nav_h");
          $("#group").addClass("nav_h");
          
      }
   </script>
</div>
<!--导航结束-->



