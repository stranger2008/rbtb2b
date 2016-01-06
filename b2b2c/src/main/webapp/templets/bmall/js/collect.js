  function insertColl(type){
   var title = encodeURIComponent(encodeURIComponent($("#title").val()));
   var link_url = document.location.href;
   $.ajax({  	 
          type: "post",    	     
          url: "/collect!ajxinsert.action?title="+ title + "&link_url=" + link_url + "&colltype=" + type,
          datatype:"json",
          success: function(data){
             if(data=='1'){
                alert("您还没登陆，请先登陆");
             }
             if(data=='0'){
                alert("收藏成功");
             }
          }
      });  
  }
  
    function insertCollCat(type){
   var title = encodeURIComponent(encodeURIComponent($("#collect_title").val()));
   var link_url = $("#collect_link_url").val();
   $.ajax({  	 
          type: "post",    	     
          url: "/collect!ajxinsert.action?title="+ title + "&link_url=" + link_url + "&colltype=" + type,
          datatype:"json",
          success: function(data){
             if(data=='1'){
                alert("您还没登陆，请先登陆");
             }
             if(data=='0'){
                alert("收藏成功");
             }
          }
      });  
  }
 