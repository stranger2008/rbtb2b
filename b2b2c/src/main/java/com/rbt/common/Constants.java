/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.common
 * FileName: Constants.java
 */
package com.rbt.common;

/**
 * @function 功能  系统常量		
 * @author  创建人 李良林
 * @date  创建日期  Jun 25, 2011
*/
public class Constants {

	/*
	 * struts配置文件新定义的跳转常量
	 * view:跳转至修改页面
	 * add：跳转至新增页面
	 * audit:审核的详细页面
	 * auditlist:审核列表页面
	 */
	public static final String VIEW = "update";
	
	public static final String ADD = "insert";
	
	public static final String AUDIT = "audit";
	
	public static final String INDEX = "index";
	
	public static final String AUDITLIST = "auditindex";
	
	/*
	 * session里存放的user名称
	 */
	public static final String USER_ID = "user_id";
	public static final String USER_NAME = "user_name";
	public static final String CUST_ID = "cust_id";
	public static final String ORG_ID = "org_id";
	public static final String CUST_TYPE = "cust_type";
	public static final String MEM_TYPE="mem_type";
	public static final String USER_TYPE = "user_type";
	public static final String ROLE_ID = "role_id";
	public static final String AREA_MANAGER="area_manger";
	public static final String LEVEL_CODE="level_code";
	
	
	
	
	/*
	 * 存放Discuz的session值
	 */
	//存放请求论坛登录返回的可执行的JS串的session名称
	public static final String DISCUZ_LOGIN_JS="discuz_login_js";
	//存放请求论坛退出登录返回的可执行的JS串的session名称
	public static final String DISCUZ_LOGINOUT_JS="discuz_loginout_js";
	//获取判断是否已经登录论坛，从session中获取DISCUZ_STATE的状态，如果值为：1：表示已经登录。如果值为：0表示还没有登录
	public static final String DISCUZ_STATE="discuz_state";
	
	/*
	 * 管理员登陆的action请求名称
	 */
	public static final String ADMIN_LOGIN = "adminlogin";
	
	/*
	 * 会员登陆的action请求名称
	 */
	public static final String MEMBER_LOGIN = "memberlogin";

	/*
	 * 商城会员登陆的action请求名称
	 */
	public static final String MEMBER_SIGNIN = "membersignin";
	
	/*
	 * 以上两个名称用于session失效控制跳转
	 * com.rbt.interceptor.SessionIterceptor
	 */
	
	/*
	 * 企业会员类型标识值
	 */
	public static final String COMPANY_MEM_TYPE = "0";
	/*
	 * 个人会员类型标识值
	 */
	public static final String PERSONAL_MEM_TYPE = "1";
	public static final String MALL_TYPE_B2B = "b2b";
	public static final String MALL_TYPE_B2C = "b2c";
	/*
	 * 管理员类型名称
	 */
	public static final String ADMIN_TYPE = "admin";
	/*
	 * 会员的类型名称
	 */
	public static final String MEMBER_TYPE = "member";
	/*
	 * B2C商城会员的类型名称
	 */
	public static final String BMALL_TYPE = "bmall";
	//模板文件存储路径
	public static final String TEMPLATE_PORTAL_FOLDER="WEB-INF/template/portal/";
	//运营商部门顶级ID
	public static final String UP_ORG_ID="1111111111";
	//运营商部门顶级菜单ID
	public static final String UP_MENU_ID="1111111111";
	//中国的地区ID
	public static final String UP_AREA_ID="1111111111";
	//JDBC属性文件名称
	public static final String JDBC_NAME="jdbc.properties";
	//获取系统用户所所对于组织部门的地区ID，主要是用于系统后台地区信息过滤
	public static final String ORG_AREA_ID="org_area_id";
	//国家顶级的地区ID
	public static final String UP_COUNTRY_AREA_ID="9999999999";

}
