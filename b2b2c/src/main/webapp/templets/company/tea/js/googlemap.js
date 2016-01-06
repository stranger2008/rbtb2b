/**********right.ftl页面google map加载脚本*************/ 
    var map = null;
    var geocoder = null;
    var mapReady = false;
    var mapResult = [];
    function initialize() {
        if (GBrowserIsCompatible()) {
    
        //搜索结果元素
        var resultElem = document.getElementById("googleResult");
        //地图容器元素
        var mapElem = document.getElementById("googleMap");
        
        // 指定实例化地图选项    
        var options = {
            //size : new GSize (220,180),
            googleBarOptions : {
                onSearchCompleteCallback : function(searcher) {
                    //设置结果
                    mapResult = searcher.results;
                    //统计结果数
                    //document.getElementById("sum").innerHTML = searcher.results.length;
                    //搜索完成时候自动居中第一个结果
                    setTimeout(function() {autoCenter(0);},1000);
                },
                resultList : resultElem ,
                maxCursorPages : 5 ,
                suppressZoomToBounds : true
            }
        };
          
        map = new GMap2(mapElem, options);
        
        // 平移及缩放控件（左上角）
        map.addControl(new GLargeMapControl());
        //比例尺控件（左下角）
        map.addControl(new GScaleControl());
        
        //创建缩略图控件（右下角）
       // var overviewMap = new GOverviewMapControl();
        //map.addControl(overviewMap);
        //最小化隐藏缩略图控件
       setTimeout(function (){overviewMap.hide();},500);
       geocoder = new GClientGeocoder();
        
        //为地图启用集成搜索控件GoogleBar(此句顺序位置不可修改)  获取搜索控件
        //map.enableGoogleBar();
        
        //自动居中结果点
        window.autoCenter = function (resultIndex){
            var result = mapResult[resultIndex];
            if( result ) {
                setTimeout(function() {
                        //构建以经度和纬度表示的地理坐标点
                        var point = new GLatLng(result.lat,result.lng);
                        //设置中心点
                        map.setCenter(point);//map.panTo(point);
                        //以动画方式平移指定的距离(左：40px,下：100px)
                        map.panBy(new GSize(-40,100));
                    },
                    500
                );
            }
        }
        
        //点击搜索结果事件
        resultElem.onclick = function (e){
            e = window.event || e;
            var target = e.target || e.srcElement;
            var trElem = target;
            //获取冒泡事件的TR层节点
            while(trElem && trElem.tagName!="TR")
            {
                if(trElem.tagName=="TABLE") break;
                trElem = trElem.parentNode;
            }
            if (trElem!=null)
            {
                //获取点击的结果索引
                var resultIndex = trElem.id.substring(trElem.id.length-1);
                //自动居中
                autoCenter(resultIndex);
            }
        }
        //初始化位置
        var address = $("#address").val();
        if (geocoder) {
            geocoder.getLatLng(
              address,
              function(point) {
                if (point) {
                  map.setCenter(point, 18);
                  var marker = new GMarker(point);
                  map.addOverlay(marker);
                  //获取冒泡弹出窗口
                  //marker.openInfoWindowHtml(address);
                  setTimeout(function (){map.panBy(new GSize(0,0));},2500);
                }
              }
            );
        }
        
        //获取搜索控件
        //var searchBar = mapElem.lastChild;
        //清空样式
        //searchBar.style.cssText = "";
        //改变搜索控件元素位置
        //document.getElementById("googleSearch").appendChild(searchBar);
        
      }
    }
    
    window.onload = function(){
        initialize();
        document.body.onunload = GUnload;
        mapReady = true;
    }
/**********right.ftl页面google map加载脚本*************/ 
    
/**********contactus.ftl页面脚本*************/     
   function doCheckSubmit(){
   var code=checkcode();
      if(code==1){
          return true;
       }else if(code==0){
         alert('验证码错误!');
         return false;
       }
       else if(code==2){ 
          alert('请输出验证码!');
          return false;
       }else{
          return false;
       }
   }
   
   function changeValidateCode(obj) {   
        //获取当前的时间作为参数，无具体意义   
        var timenow = new Date().getTime();   
        //每次请求需要一个不同的参数，否则可能会返回同样的验证码   
        //这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。   
        obj.src="/imgrand.action?d="+timenow;
    } 
   /**********contactus.ftl页面脚本*************/      

/**********top.ftl页面脚本*************/      
   function usubmit(){
   var user_name = $("#user_name").val();
   if(user_name==''){
       $("#usererror").html('用户名不能为空');
   }else{
       $("#usererror").html('');
   }
   var passwd = $("#passwd").val();
   if(passwd==''){
       $("#pwerror").html('密码不能为空');
   }else{
       $("#pwerror").html('');
   }
   var name= $("#username").val();
   if(passwd!='' && user_name!=''){
	   document.forms[0].action="/portal/memberuser!loginUser.action?memberuser.user_name="+user_name+"&memberuser.passwd="+passwd+"&loc=/showroom/"+name;
	   document.forms[0].submit();	
   }
  } 
/**********top.ftl页面脚本*************/  