 /*
*功能：系统企业站的栏目选择
*主要有：更新企业站栏目的排序
*/
      
//更新企业站栏目的排序
function updatesort_no(actionName)
{
           var admin_group_id='';
           var sort='';
           var name='';
           var num='';
	       var chks =document.getElementsByTagName('input');//得到所有input
	       var inputnum =document.getElementsByTagName('input');//得到所有input
	       var sort_no=document.getElementsByName('isort_no');//获得所有排序列表框的值
	       var ch_name=document.getElementsByName('ch_name');//获取所有栏目名称
	       var ch_num=document.getElementsByName('chnum');//获取所有数量的值
           for(var i=0;i <chks.length;i++)
          { 
            if(chks[i].type.toLowerCase() == 'checkbox'&&chks[i].value!='on')
            {
                //得到所名为checkbox的input
                admin_group_id+=chks[i].value+','; 
             }
           }
           for(var i=0;i<sort_no.length;i++){
              sort+=sort_no[i].value+',';
              name+=ch_name[i].value+',';
              num+=ch_num[i].value+',';
           }
            var len=admin_group_id.length;
            var group_id=admin_group_id.substring(0,len-1);
            document.getElementById('member_memberchannel_id').value = group_id;//用于隐藏所有的ID
            
            var lensort=sort.length;
            var sort_nos=sort.substring(0,lensort-1);
            document.getElementById('member_sort').value = sort_nos;//用于隐藏所有的ID
            
            var lenname=name.length;
            var ch_names=name.substring(0,lenname-1);
            document.getElementById('member_name').value = ch_names;//用于隐藏所有的ID
            
            var lennum=num.length;
            var ch_nums=num.substring(0,lennum-1);
            document.getElementById('member_num').value = ch_nums;//用于隐藏所有的ID
			document.forms[0].action=actionName;
			document.forms[0].submit();
}
      