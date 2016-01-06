//通过ID获取资讯标题newsid为：资讯ID，newshtml为：追加的HTML代码，limtnum为：限制显示字数
 function getnewsititles(newsid,newshtml,limtnum)
 {
  var straddnews1="";
  $.ajax({  	 
            type: "post",    	     
            url: "subjectnews!getSubjectNews.action?newsid="+newsid+"&ajaxRequestRandom="+Math.random(),       
            datatype:"json",
            async:false,
            success: function(data){
             straddnews1+=newshtml+data.toString().substring(0,limtnum)+"</a></li>";
   	        } 
       });
    return straddnews1;
 }   
    
