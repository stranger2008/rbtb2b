/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.function
 * FileName: MemberRightFuc.java 
 */
package com.rbt.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.service.IRolerightService;

/**
 * @function 功能 调用静态方法加载权限树
 * @author 创建人 邱景岩
 * @date 创建日期 Jul 13, 2011 3:52:30 PM
 */

public class MemberRightFuc extends CreateSpringContext {
	/**
	 * 方法描述：通过菜单ID查找关于该菜单所有的操作权限
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static String getMenuRightListByMenuid(String menu_id) {
		IRolerightService rolerightService = (IRolerightService) getContext().getBean("rolerightService");
		Map map = new HashMap();
		map.put("menu_id", menu_id);
		@SuppressWarnings("unused")
		List list = rolerightService.getRolerightMenuList(map);
		StringBuffer sb = new StringBuffer();
		HashMap sbMap = new HashMap();
		if (list.size() < 0) {
			return null;
		} else {
			for (int i = 0; i < list.size(); i++) {
				sbMap = (HashMap) list.get(i);
				@SuppressWarnings("unused")
				String right_id = "", right_name = "", menu_attr = "";
				if (sbMap.get("right_id") != null) {
					right_id = sbMap.get("right_id").toString();
				}
				if (sbMap.get("right_name") != null) {
					right_name = sbMap.get("right_name").toString();
				}
				if (sbMap.get("menu_attr") != null) {
					menu_attr = sbMap.get("menu_attr").toString();
				}
				sb.append("<input type=\"checkbox\" name=\"memberlevel.oper_right\" value=\"" + right_id + "\" id=" + menu_attr + right_id + "/>");
				sb.append(right_name);
				sb.append("&nbsp;");
				if ((i + 1) % 6 == 0) {
					sb.append("<br/>");
				}
			}
			return sb.toString();
		}

	}
}
