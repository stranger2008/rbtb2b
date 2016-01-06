	//用于设置隐藏域的值
	function setHiddenVal(para_name,para_value){
            document.getElementById(para_name).value = para_value;
	       	document.searchForm.submit();
	}
	function setHiddenValbyCheck(para_name,obj_id){
        if($(obj_id).attr("checked")==true){
          	   document.getElementById(para_name).value = "1";
		       document.searchForm.submit();
        }else{
               document.getElementById(para_name).value = " ";
		       document.searchForm.submit();
        }
	}
	//用于多属性搜索
	var productAttr="";
	function setAttrForm(id,obj_id){
        var value=$(obj_id).html();
        if(value=="不限"){
           value="none|"+id;//存入隐藏域字段方便回选
        }        
        if($("#product_attr_id").val()!=''){
            productAttr=$("#product_attr_id").val();
        }
        if(productAttr.indexOf(id)>-1){
           var getvalueLen=productAttr.indexOf(id);
           getvalueLen+=11;//ID+1=11位
           var singleVal="";
           if(productAttr.length>=getvalueLen){
	           singleVal=productAttr.substring(getvalueLen,productAttr.length);
	       }
           var getdhaoLen=singleVal.indexOf(",");
           if(getdhaoLen>0){
              singleVal=singleVal.substring(0,getdhaoLen);
              productAttr=productAttr.replace(id+"|"+singleVal,id+"|"+value); //拼成串ID用于回选       
           }  
        }else{
           productAttr+=id+"|"+value+",";
        }       
        $("#product_attr_id").val(productAttr);//存ID与Value的拼串
        document.searchForm.submit();       
	}
	
	/*
	*批量询价
	*/
	function send_askprice(){
	    var askcount=0;
	    var ask_cust_name="";
	    $("input:checkbox[name=cb_box]").each(function(){	  
	           if(this.checked){
	                askcount++;
	                var cust_name=$(this).parent("td").find(".hidden_cust_name").val();
	                if(ask_cust_name.indexOf(cust_name)==-1){
		                ask_cust_name+=cust_name;
		                ask_cust_name+=",";
	                } 
	           }
	    })
	    //接取最后一个逗号
	    var size = ask_cust_name.lastIndexOf(",");
	    if(size>0){
	         ask_cust_name=ask_cust_name.substring(0,size);
	    }
	    $("#send_name").val();//清空数据       
	    $("#send_name").val(ask_cust_name);
	    if(askcount>0){
	        document.addMemberinboxFormds.submit();
	    }else{
	        alert("请至少选择一条数据询价!");
	    }
   }
   
   	/*
	*单个询价
	*/	
   function single_askprice(ask_cust_name){
       $("#send_name").val();//清空数据       
       $("#send_name").val(ask_cust_name);
       document.addMemberinboxFormds.submit();
   }
   