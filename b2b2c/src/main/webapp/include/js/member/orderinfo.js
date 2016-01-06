/*
*功能：系统订单信息
*主要有： 控制评论输入框的输入字数
*/
// 控制评论输入框的输入字数
function gbcount()
{
    var max="300";
    var messages=$("#content").val();
    if (messages.length > max) {
	     document.getElementById("content").value = messages.substring(0,max);
	     $("#countnum").html("0")
	     alert("评论不能超过 300 个字!");
	     return false;
    }
    else {
     var counts=max - messages.length;
    $("#countnum").html(counts);
    }
}