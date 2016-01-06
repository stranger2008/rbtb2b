<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<link href="/templets/${templateStyle?if_exists}/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/${templateStyle?if_exists}/css/comments.css" rel="stylesheet" type="text/css" />
<#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/jsinclude.html">
<script type="text/javascript" src="/templets/${templateStyle?if_exists}/js/addcomments_detail.js"></script>

<title>${commtitle?if_exists}评论信息</title>
</head>
<body>
<@s.form action="/portal/membercomment!addcomments.action" method="post" validate="true" name="commentForm" >

<#include "/${templateRoute?if_exists}/top.html">
<!--导航结束-->
<div class="position"><P>当前位置: <a href="/">首页</a> > 评论列表 </P></div>
<div class="w960">
  <input type="hidden" name="infotype" id="info_type" value="${infotype?if_exists}" />
  <input type="hidden" name="infoids" id="infoids" value="${infoids?if_exists}" />
  <input type="hidden" name="commtitle" id="comment_title" value="${commtitle?if_exists}" />
  <input type="hidden" id="idwidth" value="620" />
  <div class="comp_porod_title">
    <p class="sm">
    <a href="javascript:history.go(-1);" title="${commtitle?if_exists}" >
    <#if commtitle?if_exists!=''>
    <#if commtitle?length lt 60>
     ${commtitle?if_exists}
    <#else>
    ${commtitle[0..59]}...
    </#if>
    </#if>
    </a>
     评论列表</P><P class="sm_sj">共 <span class="red" id="allcount"></span> 条 </P>
  </div>
  <div class="comp_porod_pl">
        <div class="pl_bfb">
          <ul class="pl_g f_left">
             <li class="pl_1">好评</li>
             <li class="pl_2">中评</li>
             <li class="pl_3">差评</li>
          </ul>
          <ul class="lp_100 f_left">
              <li style="background:#FFF7D2;height:30px;width:620px;" >
              <table >
             <tr style="width:350px;height:30px;" id="colorgoodcomment"></tr>
             </table>
              </li>
              
             <li style="background:#FFF7D2;height:30px;width:620px;">
             <table  >
             <tr style="width:350px;height:30px;" id="colormidcomment">
             </tr>
             </table>
             </li>

             <li style="background:#FFF7D2;height:30px;width:620px;">
              <table >
             <tr style="width:350px;height:30px;" id="colorbadcomment" ></tr>
             </table>
             </li>
             
          </ul>
          <ul class="l_100 f_left">
              <li id="pergoodcomment">0%</li>
              <li id="permidcomment">0%</li>
              <li id="perbadcomment">0%</li>
          </ul>
          <ul class="l_100 f_left">
              <li id="goodcomment" class="ll_100_1"  style="background-color:#E1F0FB;width:60px;text-align: center;">0</li>
              <li id="midcomment" class="ll_100_2" style="background-color:#F2F8FD;width:60px;text-align: center;">0</li>
              <li id="badcomment" class="ll_100_3" style="background-color:#F9FCFE;width:60px;text-align: center;">0</li>
          </ul>
          
      
       </div>
       
       
       <div class="comment_list">
	       <#list allcommentList as syscomment>
		         <div class="<#if syscomment_index%2==0>comment<#else>comment_1</#if>">
		            <P class="comment_name"><span class="f_left">
		            <#if syscomment.username?if_exists=="">
		              <font style="color:#07519a">匿名</font>
		            <#else>
		             ${syscomment.username?if_exists}
		            </#if>
		              于${syscomment.in_date?if_exists} 评论到：</span><span class="f_right floor">第<b>${syscomment_index+1?if_exists}</b>楼</span></P>
		            <div class="clear"></div>
		            <div>
		            <p style="table-layout:fixed; word-break: break-all; overflow:hidden;">
		            ${syscomment.content?if_exists}    
		            </P>
		            </div>
		            </P>
		            <div class="ju">
			            <#if syscomment.comm_num?if_exists=1>
			            	[好评]<img src="/templets/${templateStyle?if_exists}/images/pl_1.gif" />
			            <#elseif syscomment.comm_num?if_exists=0>
			            	[中评]<img src="/templets/${templateStyle?if_exists}/images/pl_2.gif" />
			            <#elseif syscomment.comm_num?if_exists=-1>
			            	[差评]<img src="/templets/${templateStyle?if_exists}/images/pl_3.gif" />
			            </#if>
			            &nbsp;&nbsp;
			            <a href="###;" onclick="addSupport(${syscomment.comm_id?if_exists})">支持</a>(${syscomment.up_num?if_exists})
			            &nbsp;&nbsp;
			            <a href="###;" onclick="addOpposition(${syscomment.comm_id?if_exists})">反对</a>(${syscomment.down_num?if_exists})
		            
		            </div>
		            <div class="clear"></div>
		         </div>
	      </#list>
       </div>
         <div class="listbottom f_right" style="margin-top:10px; margin-bottom:10px;margin-right:20px;" >${pageBar?if_exists}</div>
         <div class="clear"></div>
        <ul class="pl_df">
           <li class="pl_1"><input type="radio" id="goodrdc" name="comm_num" value="1" /> 好评</li>
           <li class="pl_2"><input type="radio" id="midrdc" name="comm_num" value="0"/> 中评</li>
           <li class="pl_3"><input type="radio" id="badrdc" name="comm_num" value="-1" /> 差评</li>
        </ul>
        <input type="hidden" name="membercomment.Info_title" value="${commtitle?if_exists}" />
        <textarea name="content" id="com_content" onKeyDown="gbcount();" onKeyUp="gbcount();"
         cols="" rows="3" class="sry"></textarea>
        <div class="yzm">验证码：<input type="text" maxlength="4" name="commentrand" id="rands" style="width:50px;" /> 
        <img src="/imgrand.action" style="vertical-align:middle;" onclick="changeValidateCode(this)"/>
         </div>
        <div class="fb">
        <input type="button" onclick="addcommentss();"  value="发表评论" class="fb_pl"/> 
        <span id="messagepl">(请登录评论)</span>
        <span class="fbspan">(内容限1至300字)</span>   
        <span>当前还可以输入 <span class="red" ><label id="countnum" style="color: red;font-weight: bold;">300</label></span> 字</span> </div>  
  </div>
</div>

</from>
<div class="clear"></div>


<#include "/${templateRoute?if_exists}/bottom.html">

</@s.form>
</body>
</html>