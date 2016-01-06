//////////////////////////////////////////////////
/*

		val:当前数字

		num:tab页总数

		btnName:选项卡id名称前缀（去掉数字的名称）

		divName:显示隐藏的下部div名称前缀

		btncss1:选项卡当前的样式名称

		btncss2:选项卡其他的样式名称

	*/
function re_show(val,num,btnName,divName,btncss1,btncss2){
		for(var i=1;i<=num;i++)
		{
			if(val==i)
			{
				document.getElementById(divName+i).style.display = 'block';
				document.getElementById(btnName+i).className = btncss1;
			}else{
				document.getElementById(divName+i).style.display = 'none';
				document.getElementById(btnName+i).className = btncss2;
			}
		}
	}

//////////////////////////////////////////////////
function job_resume_search(area_attrid,cat_atrrid,modul_type){
     //地区获取
	    if(area_attrid!="")
	    {
		 var area_attr = document.getElementsByName(area_attrid);
		 var area_attr_str = '';
		 for(var i=0;i<area_attr.length;i++){
			if(area_attr[i].value!='0'){
				area_attr_str += area_attr[i].value+',';
			}
		 }
		 if(area_attr_str!="")
		 {
		   area_attr_str=area_attr_str.substring(0,area_attr_str.length-1);
		 }
		}
		//分类获取
		if(cat_atrrid!="")
		{
		var cat_attr= document.getElementsByName(cat_atrrid);
		var cat_attr_str = '';
		for(var i=0;i<cat_attr.length;i++){
			if(cat_attr[i].value!='0'){
				cat_attr_str += cat_attr[i].value+',';
			}
		}
		if(cat_attr_str!="")
		{
		  cat_attr_str=cat_attr_str.substring(0,cat_attr_str.length-1);
		}
		}
        var keyword = $("#keyword").val();   
        var vtext=encodeURIComponent(encodeURIComponent(keyword));  
       window.location.href="/portal/"+modul_type+"!list.action?searchkeyword="+vtext+"&cat_id="+cat_attr_str+"&area_id="+area_attr_str;   
}

