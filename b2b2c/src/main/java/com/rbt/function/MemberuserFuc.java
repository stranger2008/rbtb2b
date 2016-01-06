/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.function
 * FileName: AdvinfoFuc.java 
 */
package com.rbt.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.model.Memberuser;
import com.rbt.service.ILogsService;
import com.rbt.service.IMemberuserService;

/**
 * @function 功能  根据客户ID（cust_id）查找出memberuser表的用户名称user_name显示
 * @author  创建人 蔡毅存
 * @date  创建日期  2011-09-22
 */
public class MemberuserFuc extends CreateSpringContext{
	public static Memberuser memberuser;

	public static Memberuser getuserName(String cust_id){
		
		String userid="";
		HashMap usermap=new HashMap();
		usermap.put("cust_id", cust_id);
		List memberuserList=getMemberuserObj().getList(usermap);
		if(memberuserList!=null&&memberuserList.size()>0){
		  HashMap usernamemap=new HashMap();
		  usernamemap=(HashMap)memberuserList.get(0);
		  userid=usernamemap.get("user_id").toString();
		  memberuser=getMemberuserObj().get(userid);
		}
		return memberuser;
	}
	//根据客户ID（cust_id）查找出memberuser表用户类型为管理员的memberuser实体
	public static Memberuser getMemberuserById(String cust_id){
		String userid="";
		Map usermap=new HashMap();
		usermap.put("cust_id", cust_id);
		usermap.put("user_type", "1");
		List memberuserList=getMemberuserObj().getList(usermap);
		if(memberuserList!=null&&memberuserList.size()>0){
		  Map usernamemap=new HashMap();
		  usernamemap=(HashMap)memberuserList.get(0);
		  userid=usernamemap.get("user_id").toString();
		  memberuser=getMemberuserObj().get(userid);
		}
		return memberuser;
		
	}
	/**
	 * 方法描述：用于商城会员自动登录功能,从COOKIS取值判断,数据库中是否存在该用户
	 * 
	 * @return
	 * @throws Exception
	 */
	public static boolean login(String username, String password) {
		boolean flag = false;
		if(username != null && password != null){
			Map loginMap = new HashMap();
			loginMap.put("user_name", username);
			loginMap.put("passwd", password);
			List loginuserList = getMemberuserObj().getList(loginMap);
	        if (loginuserList != null && loginuserList.size()>0) {
	        	flag = true;
	            return flag;  
	        } else {  
	            return flag;  
	        }  
		}
		return flag;
		
    }  
	
	//从Spring容器中获取招聘业务Bean
	public static IMemberuserService getMemberuserObj(){
		return (IMemberuserService)getContext().getBean("memberuserService");
	}
	
	//从Spring容器中获取招聘业务Bean
	public static ILogsService getlogsServiceObj(){
		return (ILogsService)getContext().getBean("logsService");
	}
	//获取上次登录时间
	public static Map getlast_loginTime(String user_id){
		//获取上次登录时间或是第一次登录时间
		String login="登陆";
		List logsList;
		HashMap map=new HashMap();
		map.put("user_id", user_id);
		map.put("start",0);
		map.put("limit",2);
		map.put("content", login);
		logsList=getlogsServiceObj().getList(map);
		Map logmap=new HashMap();
		if(logsList.size()>1){
			logmap=(HashMap)logsList.get(1);
		}
		if(logsList.size()==1){
			logmap=(HashMap)logsList.get(0);
		}
		return logmap;
	}
}
