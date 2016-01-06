$(document).ready(function() {	 
     /*图片排行特效样式*/
	 $(".picph_main .ico_top:lt(3)").addClass("n1");
	 $(".picph_main .ico_top:gt(2)").addClass("n2");
	 $(".picph_main li:last").css("border-bottom","0");
     var value_array = [1,2,3,4,5,6,7,8,9,10]; 
	 $(".picph_main .ico_top").each(function(i){  
		 $(this).text(value_array[i]);
     });
     
	//Show Banner
	$(".main_image .desc").show(); //Show Banner
	$(".main_image .bigimg").animate({ opacity: 0.85 }, 1 ); //Set Opacity
	//Click and Hover events for thumbnail list
	$(".image_thumb ul li:first").addClass('active'); 
    var firstAlt=$(".image_thumb ul li:first").find("img").attr("alt"); 
    var firstSrc=$(".image_thumb ul li:first").find("img").attr("src"); 
    var firstHref=$(".image_thumb ul li:first").find("a").attr("href"); 
	$(".main_image img").attr({ src: firstSrc , alt: firstAlt});
	$(".main_image a").attr({ href: firstHref});
	$(".main_image .block").html(firstAlt).animate({ opacity: 0.85,	marginBottom: "0" }, 250 );
	$(".main_image .block").html(firstAlt).css("text-align","center");
	$(".main_image .block").html(firstAlt).css("font-size","16px");
	$(".main_image img").css("width","420");
	$(".main_image img").css("height","273");
	
	$(".image_thumb ul li").mouseover(function(){ 
		//Set Variables
		var imgAlt = $(this).find('img').attr("alt"); //Get Alt Tag of Image
		var imgTitle = $(this).find('a').attr("href"); //Get Main Image URL
		var imgDesc = $(this).find('.block a').html(); 	//Get HTML of block
		var imgDescHeight = $(".main_image").find('.block').height();	//Calculate height of block	
		var imgsrc=$(this).find("img").attr("src");
		var ahref=$(this).find("a").attr("href");
		if ($(this).is(".active")) {  //If it's already active, then...
			return false; // Don't click through
		} else {
			//Animate the Teaser				
			$(".main_image .block").animate({ opacity:0.85, marginBottom: -imgDescHeight }, 250 , function() {
				$(".main_image .block").html(imgDesc).animate({ opacity: 0.85,	marginBottom: "0" }, 250 );
				$(".main_image .block").html(imgDesc).css("text-align","center");
				$(".main_image .block").html(imgDesc).css("font-size","16px");
				$(".main_image img").attr({ src: imgsrc , alt: imgAlt});
				$(".main_image a").attr({ href: ahref});
			});
		var imgs = document.getElementsByTagName('.main_image img');
  		var imgw = 420;
  		var imgh=273;
  		for(var i=0;i<imgs.length;i++){
  			if(imgs[i].width > imgw){
  				imgs[i].width = imgw;
  			}
  			if(imgs[i].height > imgh){
  				imgs[i].height = imgh;
  			}
  		}	
		}
		
		$(".image_thumb ul li").removeClass('active'); //Remove class of 'active' on all lists
		$(this).addClass('active');  //add class of 'active' on this list only
		
		return false;
				
	}) .hover(function(){
		$(this).addClass('hover');
		}, function() {
		$(this).removeClass('hover');
	});			
	
});//Close Function
