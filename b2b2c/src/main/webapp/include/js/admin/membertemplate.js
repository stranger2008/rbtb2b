 /*
*功能：系统会员模板选择控制
*主要有：改变模板状态   
*/
//改变模板状态   
function changestatus(status) {
	var checks = document.getElementsByName(status);
	for (var j = 0; j < checks.length; j++) {
		if (checks[j].value == "4" && checks[j].checked) {
			for (var i = 0; i < checks.length; i++) {
				checks[i].checked = false;
				if (checks[i].value == "4") {
					checks[i].checked = true;
				}
			}
		}
	}
} 
