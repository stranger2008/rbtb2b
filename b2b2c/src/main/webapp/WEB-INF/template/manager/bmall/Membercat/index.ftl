<html>
<head>
<title>商品分类管理</title>
 <link rel="StyleSheet" href="/include/components/dtree/dtree.css" type="text/css" />
	<script type="text/javascript" src="/include/components/dtree/dtree.js"></script>
	
</head>
<body>
	<div class="postion">
  		  <a href="#">我是卖家</a><span>></span><a href="#">商品管理</a><span>></span><a href="#">商品分类管理</a>
    </div>
<@s.form action="/bmall_Membercat_list.action" method="post">
   <div class="rightside f_right">
	    <div class="rpostion"><h2>商品管理</h2></div>
		     <div class="ropot">
			       <h2 class="rotitle"><span>商品分类管理</span></h2>
			       <table cellspacing="0" class="bmall_list_table">
				         <tr>
					            <td  class="manadiv">
										<input type="button" class="submitbut" value="添加" onclick="linkToInfo('/bmall_Membercat_add.action','');"/>
					           </td>
				         </tr>    
				         <tr>
					           <td>
					                    <script type="text/javascript">
											a = new dTree('a');
									
											a.add(1111111111,-1,'商品自定义分类&nbsp;');
											
											<#list membercatList as catgory>
											
											a.add(${catgory.cat_id?if_exists},${catgory.up_cat_id?if_exists},
											'${catgory.cat_name?if_exists}<#if catgory.is_recom==1>&nbsp;<span style="color:red;">[推荐]</span></#if>&nbsp;<a href=### onclick=delOneInfo("membercat.cat_id","/bmall_Membercat_deleteGoodsCat.action","${catgory.cat_id?if_exists}");>删除</a>&nbsp;<a href=### onclick=linkToInfo("/bmall_Membercat_view.action","membercat.cat_id=${catgory.cat_id?if_exists}&membertype=3");>修改</a>&nbsp;<#if catgory.cat_level==1><a href=### onclick=linkToInfo("/bmall_Membercat_add.action","membercat.up_cat_id=${catgory.cat_id?if_exists}&membercat.cat_level=${catgory.cat_level?if_exists}&membercat.cat_type=3");>添加</a></#if>','#');
											
											 </#list>
											
											document.write(a);
										  </script>
					           </td>
				         </td>
			       </table>
		      </div>     
	   </div>
   </div>
  <div class="clear"></div>
</@s.form>
</body>
</html>




