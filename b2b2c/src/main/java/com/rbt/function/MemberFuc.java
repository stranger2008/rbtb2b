/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.function
 * FileName: MemberFuc.java 
 */
package com.rbt.function;

import java.util.HashMap;
import java.util.List;

import com.rbt.model.Member;
import com.rbt.service.IMemberService;
/**
 * @function 功能 会员表一些相关操作
 * @author  创建人 邱景岩  会员表的相关操作
 * @date  创建日期 Dec 9, 2011 11:23:43 AM
 */
public class MemberFuc extends CreateSpringContext{
	
	//根据公司ID找到其相应信息
	public static Member getMemberByPk(String cust_id){
		return  getMemberObj().get(cust_id);
	}
	//从Spring容器中获取招聘业务Bean
	public static IMemberService getMemberObj(){
		return (IMemberService)getContext().getBean("memberService");
	}
	
	/**
	 * @function 功能 获取企业经营模式的中文名称
	 * @author 创建人 邱景岩
	 * @date 创建日期 Dec 2, 2011 9:40:41 AM
	 */
	public static String getStatusName(String statusname) {
		if(statusname == null){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		HashMap sMap = getStatusMap();
		String status_name = "";
		// 定义String分隔串
		String[] status = statusname.split(",");
		for (int j = 0; j < status.length; j++) {
			if (status[j] != null && !status[j].equals("")) {
				if (sMap != null && sMap.get(status[j].replace(" ", "")) != null) {
					status_name = sMap.get(status[j].replace(" ","")).toString();
					sb.append(status_name);
					if (j != status.length - 1) {
						sb.append(",");
					}
				}
			}
		}
		return sb.toString();
	}
	/**
	 * @function 功能 从参数表里找出经营模式的对应的中文名称
	 * @author  创建人 邱景岩
	 * @date  创建日期 Dec 2, 2011 10:25:31 AM
	 */
	public static HashMap getStatusMap() {
		List sList = CommparaFuc.getCommparaList("client_status");
		HashMap statusMap = new HashMap();
		if (sList != null && sList.size() > 0) {
			HashMap aMap = new HashMap();
			for (int i = 0; i < sList.size(); i++) {
				String para_key = "", para_value = "";
				aMap = (HashMap) sList.get(i);
				if (aMap.get("para_value") != null) {
					para_value = aMap.get("para_value").toString();
				}
				if (aMap.get("para_key") != null) {
					para_key = aMap.get("para_key").toString();
				}
				statusMap.put(para_value, para_key);
			}
		}
		return statusMap;
	}
}
