 function addMessage(){
   var title = encodeURIComponent(encodeURIComponent($("#mescontentid").val()));
   var link_url = document.location.href;
   var shopid=$("#shopid").val();
   $.ajax({  	 
          type: "post",    	     
          url: "/insertBusinmes.action?shop_id="+shopid+"&mescotent="+title,
          datatype:"json",
          success: function(data){
             if(data=='1'){
                alert("您还没登陆，请先登陆");
                document.location.href="/signin.html?loc="+link_url;
             }
             if(data=='0'){
                alert("留言成功,请等待回复!");
                document.location.reload();
             }
          }
      });  
  }





