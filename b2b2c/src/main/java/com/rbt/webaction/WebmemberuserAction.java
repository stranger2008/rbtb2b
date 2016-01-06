/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.webaction
 * FileName: WebmemberuserAction.java 
 */
package com.rbt.webaction;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.rbt.common.Constants;
import com.rbt.common.Md5;
import com.rbt.common.link.discuz.discuzOptUtil;
import com.rbt.common.util.MailUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.SysconfigFuc;
import com.rbt.listener.UserLoginListener;
import com.rbt.listener.UserSessionAttr;
import com.rbt.model.Emailtemplate;
import com.rbt.model.Memberlevel;
import com.rbt.model.Memberuser;
import com.rbt.model.Role;
import com.rbt.service.IEmailtemplateService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMemberlevelService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.IRoleService;

/**
 * @function 功能 找回用户名和密码模块的操作action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Sep 17, 2011 15:50:13 PM
 */
@Controller
public class WebmemberuserAction extends WebbaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5535005240249968461L;
	/*
	 * 记录会员邮件发送模板信息对象
	 */
	public Emailtemplate emailtemplate;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IEmailtemplateService emailtemplateService;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IMemberuserService memberuserService;
	@Autowired
	public IMemberService memberService;
	@Autowired
	public IMemberlevelService memberlevelService;
	@Autowired
	public IRoleService roleService;
	
	/*
	 * 用户账号对象
	 */
	public Memberuser memberuser;
	/*
	 * 记录会员邮件发送模板信息信息集合
	 */
	public List emailtemplateList;
	/*
	 * 用户名
	 */
	public String username;
	
	/*
	 * 邮箱
	 */
	public String email;
	/*
	 * 邮箱模板
	 */
	private static final String TEMP = "forget_username";
	/*
	 * 
	 */
	private static final String TEMP_CODE = "forget_passwd";
	/*
	 * 后台类型
	 */
	private final static String DEFAULT_SYSCODE_VALUE = "com";
	/*
	 * 验证码
	 */
	public String commentrand;
	/*
	 * 提交状态
	 */
	public String success;

	/*
	 * 密码重置
	 */
	private static final String PASS = "123456";
	/*
	 * 记住用户名
	 */
	public String mallusername;
	/*
	 * 标识是否自动登录
	 */
	public String autosignin;
	


	/**
	 * 方法描述：跳转到找回密码页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String executepwd() throws Exception {
		super.saveRequestParameter();
		return goUrl("forgot_password");
	}
	/**
	 * 方法描述：验证用户是否登录，如果登录的话，返回用户类型
	 * 
	 * @return
	 * @throws Exception
	 */
	public void checkUserIfLogin() throws Exception {
		getRequest().setCharacterEncoding("UTF-8");
		getResponse().setCharacterEncoding("UTF-8");
		PrintWriter out = getResponse().getWriter();
		String outString = "11111";
		if(!"".equals(session_cust_id))
		{
			outString=this.getSessionFieldValue(Constants.MEM_TYPE);
		}
		else {
			outString="11111";//表示用户还没有登录
		}
		out.write(outString);
	}
	/**
	 * 方法描述：找回密码功能实现
	 * 
	 * @return
	 * @throws Exception
	 */
	public String retrievepwd() throws Exception {
		success = "0";
		// mds加密实例
		Md5 md5 = new Md5();
		// 邮件发送实例
		MailUtil mail = new MailUtil();
		// 邮件标题
		String title = "找回密码";
		// 模板内容
		String temp_con = "";
		// 选择发送邮箱模板
		HashMap map = new HashMap();
		map.put("temp_code", TEMP_CODE);
		emailtemplateList = this.emailtemplateService.getList(map);
		if (emailtemplateList != null && emailtemplateList.size() > 0) {
			HashMap value = new HashMap();
			value = (HashMap) emailtemplateList.get(0);
			temp_con = value.get("temp_con").toString();
		}

		Map pageMap = new HashMap();
		pageMap.put("user_name", username);
		pageMap.put("email", email);
		// 通过用户名找出用户信息
		List userList = this.memberuserService.getList(pageMap);
		String sysrand = "";
		// 获取系统生成的验证码
		if (getSession().getAttribute("sysrand") != null) {
			sysrand = getSession().getAttribute("sysrand").toString();
		}
		if (ValidateUtil.isRequired(username)) {
			this.addFieldError("username", "请输入用户名");
			return executepwd();
		}
		if (ValidateUtil.isRequired(email)) {
			this.addFieldError("email", "请输入邮箱");
			return executepwd();
		}
		if (ValidateUtil.isEmail(email)) {
			this.addFieldError("email", "邮箱输入有误");
			return executepwd();
		}
		if (((userList == null || userList.size() == 0))) {
			this.addFieldError("username", "用户名不存在，或者邮箱输入有误");
			return executepwd();
		}

		// 验证码
		if (commentrand.equals("")) {
			this.addFieldError("trand", "请输入验证码");
			return executepwd();
		}
		if (!sysrand.equals(commentrand)) {
			this.addFieldError("trand", "验证码输入错误");
			return executepwd();
		}

		HashMap value = new HashMap();
		value = (HashMap) userList.get(0);
		String user_id = value.get("user_id").toString();
		String passwd = md5.getMD5(PASS.getBytes());
		// 获取用户名
		String user_name = value.get("user_name").toString();
		if (!temp_con.equals("")) {
			// 密码重置
			memberuser = new Memberuser();
			memberuser.setUser_id(user_id);
			memberuser.setPasswd(passwd);
			this.memberuserService.updatePassword(memberuser);
			// 替换邮件模板
			String content = temp_con.replace("{user_name}", user_name).replace("{passwd}", PASS);
			// 发送邮件
			mail.sendMail(title, content, email);
			success = "1";
		}
		return executepwd();
	}

	/**
	 * 方法描述：跳转到找回用户名页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String executename() throws Exception {
		super.saveRequestParameter();
		return goUrl("forgot_username");
	}

	/**
	 * 方法描述：找回用户名功能实现
	 * 
	 * @return
	 * @throws Exception
	 */
	public String retrievename() throws Exception {
		success = "0";
		// 邮件发送实例
		MailUtil mail = new MailUtil();
		// 邮件标题
		String title = "找回用户名";
		// 模板内容
		String temp_con = "";
		// 选择发送邮箱模板
		HashMap map = new HashMap();
		map.put("temp_code", TEMP);
		emailtemplateList = this.emailtemplateService.getList(map);
		if (emailtemplateList != null && emailtemplateList.size() > 0) {
			HashMap value = new HashMap();
			value = (HashMap) emailtemplateList.get(0);
			temp_con = value.get("temp_con").toString();
		}

		Map pageMap = new HashMap();
		pageMap.put("email", email);
		// 通过用户名找出用户信息
		List userList = this.memberuserService.getList(pageMap);
		String sysrand = "";
		// 获取系统生成的验证码
		if (getSession().getAttribute("sysrand") != null) {
			sysrand = getSession().getAttribute("sysrand").toString();
		}
		if (ValidateUtil.isRequired(email)) {
			this.addFieldError("email", "请输入邮箱");
			return executename();
		}
		if (ValidateUtil.isEmail(email)) {
			this.addFieldError("email", "邮箱输入有误");
			return executename();
		}
		if (((userList == null || userList.size() == 0))) {
			this.addFieldError("email", "邮箱不存在");
			return executename();
		}
		// 验证码
		if (commentrand.equals("")) {
			this.addFieldError("trand", "请输入验证码");
			return executename();
		}
		if (!sysrand.equals(commentrand)) {
			this.addFieldError("trand", "验证码输入错误");
			return executename();
		}
		if (userList != null && userList.size() > 0) {
			HashMap value = new HashMap();
			value = (HashMap) userList.get(0);
			// 获取用户名
			String user_name = value.get("user_name").toString();
			if (!temp_con.equals("")) {
				// 替换邮件模板
				String content = temp_con.replace("{user_name}", user_name).replace("{email}", email);
				// 发送邮件
				mail.sendMail(title, content, email);
				success = "1";
			}
		}
		return executename();
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Aug 31, 2012 9:03:07 AM
	 * @Method Description :会员登录页面
	 */
	public String login() throws Exception{
		//获取登陆用户的cust_id、user_id
		String custid=this.session_cust_id;
		if(custid!=null&&!"".equals(custid)&&this.session_cust_type.equals(Constants.MEMBER_TYPE)){
			this.getResponse().sendRedirect("/memberindex.action");
		}
		return goUrl("login");
	}
	
	/**
	 * 会员登陆 author 李良林 date 2011-07-21
	 * 
	 * @return
	 * @throws Exception
	 */
	public String loginUser() throws Exception {
		
		String loc = "";
		if (getRequest().getParameter("loc") != null) {
			loc = getRequest().getParameter("loc");
		}
		String user_name="";
		if (memberuser != null && memberuser.getUser_name() != null) {
			user_name = memberuser.getUser_name();
		}
		
		String passwd="",discuzpwd="";
		if (memberuser != null && memberuser.getPasswd() != null) {
			passwd = memberuser.getPasswd();
			//保存原始密码给登录Discuz论坛
			discuzpwd=memberuser.getPasswd();
		}
		Map pageMap = new HashMap();	
		if (ValidateUtil.isRequired(user_name)) {
			this.addFieldError("memberuser.user_name", "请输入用户名");
			return goUrl("login");
		}
		//是否通过邮箱登录
        if(user_name.indexOf("@")>-1){
			if (ValidateUtil.isEmail(user_name)) {
				this.addFieldError("memberuser.user_name", "邮箱格式不正确");
				return goUrl("login");
			}
			pageMap.put("u-email", user_name);
        }else if(!ValidateUtil.isDigital(user_name)){
        	//是否通过手机号码登录
			if (ValidateUtil.isMobile(user_name)) {
				this.addFieldError("memberuser.user_name", "手机号码格式不正确");
				return goUrl("login");
			}
			pageMap.put("u-cellphone", user_name);			
        }else{//为用户名登录
        	pageMap.put("user_name", user_name);
        }             
        List loginuserList = this.memberuserService.getList(pageMap);
		if (ValidateUtil.isRequired(passwd)) {
			this.addFieldError("memberuser.passwd", "请输入密码");
			return goUrl("login");
		}
		if (loginuserList == null || loginuserList.size() == 0) {
			this.addFieldError("memberuser.user_name", "用户名/邮箱/手机号码不存在");
			return goUrl("login");
		}
		passwd = Md5.getMD5(passwd.getBytes());
		pageMap.put("passwd", passwd);
		//用户的cust_id
		loginuserList = this.memberuserService.getList(pageMap);
		if (loginuserList == null || loginuserList.size() == 0) {
			this.addFieldError("memberuser.passwd", "密码不正确");
			return goUrl("login");
		}else{
			Map listMap=new HashMap();
			listMap=(HashMap)loginuserList.get(0);
			if(listMap.get("user_name")!=null){
				user_name=listMap.get("user_name").toString();
			}
		}

		String user_id = "", cust_id = "", user_type = "", role_code = "", is_active = "";
		// mem_type 0：企业 1：个人
		String mem_type = "";
		// 会员级别
		String level_code = "";
		if (loginuserList != null && loginuserList.size() > 0) {
			Map loginMap = (HashMap) loginuserList.get(0);
			if (loginMap.get("user_id") != null) {
				user_id = loginMap.get("user_id").toString();
			}
			if (loginMap.get("cust_id") != null) {
				cust_id = loginMap.get("cust_id").toString();
			}
			if (loginMap.get("user_type") != null) {
				user_type = loginMap.get("user_type").toString();
			}
			if (loginMap.get("role_code") != null) {
				role_code = loginMap.get("role_code").toString();
			}
			if (loginMap.get("is_active") != null) {
				is_active = loginMap.get("is_active").toString();
			}
			if (loginMap.get("mem_type") != null) {
				mem_type = loginMap.get("mem_type").toString();
			}
			if (loginMap.get("level_code") != null) {
				level_code = loginMap.get("level_code").toString();
			}
		}
		// is_active 0：已激活 1：未激活
		// 在系统开通注册激活的情况下，注册后必须收邮件，点击激活链接，方可激活账号，未激活账号不允许登陆
		if (is_active.equals("") || is_active.equals("1")) {
			this.addFieldError("memberuser.user_name", "用户未激活");
			return goUrl("login");
		}
		
		//判断用户是否已经通过审核，是否允许登录，如果是的话
		if(cfg_mb_islogin.equals("1")){
			Map memMap=new HashMap();
			memMap.put("cust_id",cust_id);
			List list=this.memberService.getList(memMap);
			if (list != null && list.size() > 0) {
				Map listMap=new HashMap();
				listMap=(HashMap)list.get(0);
				String info_state="";
				if(listMap.get("info_state")!=null){
					info_state=listMap.get("info_state").toString();
				}
				if(info_state.equals("0")){
					this.addFieldError("memberuser.user_name", "用户未审核");
					return goUrl("login");
				}
			}
		}		

		// 根据会员级别level_code找出该级别下的菜单权限和操作权限
		String menu_right = "", oper_right = "";
		if (!level_code.equals("")) {
			Memberlevel memberlevel = this.memberlevelService.get(level_code);
			if (memberlevel != null && memberlevel.getMenu_right() != null) {
				menu_right = memberlevel.getMenu_right();
			}
			if (memberlevel != null && memberlevel.getOper_right() != null) {
				oper_right = memberlevel.getOper_right();
			}
		 }

		// user_type 1：会员管理员 2：会员子账号
		if (user_type.equals("2") && !role_code.equals("")) {
			// 找出当前角色拥有哪些权限
			String role_menu_right = "", role_oper_right = "";
			Role role = this.roleService.get(role_code);
			if (role != null && role.getMenu_right() != null) {
				role_menu_right = role.getMenu_right();
			}
			if (role != null && role.getOper_right() != null) {
				role_oper_right = role.getOper_right();
			}

			// 会员子账号的菜单权限和操作权限都是根据会员级别来的
			// 如果会员的级别被降下来了，想要的菜单权限和操作权限肯定也会少很多
			// 可子账号还保留了之前的权限
			// 所以下面就要过滤一下了，如果当前的角色权限的长度大于级别权限的长度的话，就要过滤喽

			// 菜单权限过滤
			if (role_menu_right.length() > menu_right.length()) {
				String[] menu = role_menu_right.split(",");
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < menu.length; i++) {
					if (!menu[i].equals("") && menu_right.indexOf(menu[i]) > -1) {
						sb.append(menu[i]);
						sb.append(",");
					}
				}
				menu_right = sb.toString();
			}else{
				menu_right=role_menu_right;
			}

			// 操作权限过滤
			if (role_oper_right.length() > oper_right.length()) {
				String[] menu = role_oper_right.split(",");
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < menu.length; i++) {
					if (!menu[i].equals("") && oper_right.indexOf(menu[i]) > -1) {
						sb.append(menu[i]);
						sb.append(",");
					}
				}
				oper_right = sb.toString();
			}else{
				oper_right=role_oper_right;
			}
		}
		
		
		
		
		
		// 将值存入session中
		getSession().setAttribute(Constants.CUST_ID, cust_id);
		getSession().setAttribute(Constants.CUST_TYPE, Constants.MEMBER_TYPE);
		getSession().setAttribute(Constants.USER_NAME, user_name);
		getSession().setAttribute(Constants.USER_ID, user_id);

		getSession().setAttribute(Constants.MEM_TYPE, mem_type);
		// 会员用户类型 1：管理员 2：子账户
		getSession().setAttribute(Constants.USER_TYPE, user_type);
		getSession().setAttribute(Constants.ROLE_ID, role_code);
		// syscode sys:运营商 com:会员
		getSession().setAttribute("syscode", DEFAULT_SYSCODE_VALUE);

		// 会员级别
		getSession().setAttribute(Constants.LEVEL_CODE, level_code);

		// 会员的菜单权限和操作权限
		getSession().setAttribute("menu_right", menu_right);
		getSession().setAttribute("oper_right", oper_right);

		this.addActionMessage("会员登陆");

		if (!loc.equals("")) {
			getResponse().sendRedirect(loc);
			return null;
		}
		
		 /*
		  * 同步登录Discuz论坛
		  */
		  discuzOptUtil discuzopt=new discuzOptUtil();
		 //设置session的值，用于标识用户还没有登录论坛
		 getSession().setAttribute(Constants.DISCUZ_STATE, "0");
		 //登录论坛
		 discuzopt.userLoginDiscuz(user_name,discuzpwd,cfg_discuz_open);
		

		
		
		
		getResponse().sendRedirect("/memberindex.action");
		return goUrl("login");
	}
	/**
	 * 方法描述：AJAX请求：供应，求购，产品的会员登录
	 * 
	 * @return
	 * @throws Exception
	 */
	public void userlogin() throws Exception{
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String user_name = "",passwd = "";
		if(request.getParameter("user_name") != null){
			user_name = request.getParameter("user_name").toString();
		}
		if(request.getParameter("passwd") != null){
			passwd = request.getParameter("passwd").toString();
		}
		
		Map pageMap = new HashMap();	
       if(!ValidateUtil.isRequired(user_name)){				
        	pageMap.put("user_name", user_name);
        }             
        List loginuserList = this.memberuserService.getList(pageMap);
		if (loginuserList == null || loginuserList.size() == 0) {
//			this.addFieldError("memberuser.user_name", "用户名不存在");
			out.write("1");
		}
		passwd = Md5.getMD5(passwd.getBytes());
		pageMap.put("passwd", passwd);
		loginuserList = this.memberuserService.getList(pageMap);
		if (loginuserList == null || loginuserList.size() == 0) {
//			this.addFieldError("memberuser.passwd", "密码不正确");
			out.write("2");
		}else{
			Map listMap=new HashMap();
			listMap=(HashMap)loginuserList.get(0);
			if(listMap.get("user_name")!=null){
				user_name=listMap.get("user_name").toString();
			}
		}
		String user_id = "", cust_id = "", user_type = "", role_code = "", is_active = "";
		// mem_type 0：企业 1：个人
		String mem_type = "";
		// 会员级别
		String level_code = "";
		if (loginuserList != null && loginuserList.size() > 0) {
			Map loginMap = (HashMap) loginuserList.get(0);
			if (loginMap.get("user_id") != null) {
				user_id = loginMap.get("user_id").toString();
			}
			if (loginMap.get("cust_id") != null) {
				cust_id = loginMap.get("cust_id").toString();
			}
			if (loginMap.get("user_type") != null) {
				user_type = loginMap.get("user_type").toString();
			}
			if (loginMap.get("role_code") != null) {
				role_code = loginMap.get("role_code").toString();
			}
			if (loginMap.get("is_active") != null) {
				is_active = loginMap.get("is_active").toString();
			}
			if (loginMap.get("mem_type") != null) {
				mem_type = loginMap.get("mem_type").toString();
			}
			if (loginMap.get("level_code") != null) {
				level_code = loginMap.get("level_code").toString();
			}
		}
		// is_active 0：已激活 1：未激活
		// 在系统开通注册激活的情况下，注册后必须收邮件，点击激活链接，方可激活账号，未激活账号不允许登陆
		if (is_active.equals("") || is_active.equals("1")) {
//			this.addFieldError("memberuser.user_name", "用户未激活");
			out.write("3");
		}

		// 根据会员级别level_code找出该级别下的菜单权限和操作权限
		String menu_right = "", oper_right = "";
		if (!level_code.equals("")) {
			Memberlevel memberlevel = this.memberlevelService.get(level_code);
			if (memberlevel != null && memberlevel.getMenu_right() != null) {
				menu_right = memberlevel.getMenu_right();
			}
			if (memberlevel != null && memberlevel.getOper_right() != null) {
				oper_right = memberlevel.getOper_right();
			}
		}

		// user_type 1：会员管理员 2：会员子账号
		if (user_type.equals("2") && !role_code.equals("")) {
			// 找出当前角色拥有哪些权限
			String role_menu_right = "", role_oper_right = "";
			Role role = this.roleService.get(role_code);
			if (role != null && role.getMenu_right() != null) {
				role_menu_right = role.getMenu_right();
			}
			if (role != null && role.getOper_right() != null) {
				role_oper_right = role.getOper_right();
			}

			// 会员子账号的菜单权限和操作权限都是根据会员级别来的
			// 如果会员的级别被降下来了，想要的菜单权限和操作权限肯定也会少很多
			// 可子账号还保留了之前的权限
			// 所以下面就要过滤一下了，如果当前的角色权限的长度大于级别权限的长度的话，就要过滤喽

			// 菜单权限过滤
			if (role_menu_right.length() > menu_right.length()) {
				String[] menu = role_menu_right.split(",");
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < menu.length; i++) {
					if (!menu[i].equals("") && menu_right.indexOf(menu[i]) > -1) {
						sb.append(menu[i]);
						sb.append(",");
					}
				}
				menu_right = sb.toString();
			}

			// 操作权限过滤
			if (role_oper_right.length() > oper_right.length()) {
				String[] menu = role_oper_right.split(",");
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < menu.length; i++) {
					if (!menu[i].equals("") && oper_right.indexOf(menu[i]) > -1) {
						sb.append(menu[i]);
						sb.append(",");
					}
				}
				oper_right = sb.toString();
			}

		}

		// 将值存入session中
		getSession().setAttribute(Constants.CUST_ID, cust_id);
		getSession().setAttribute(Constants.CUST_TYPE, Constants.MEMBER_TYPE);
		getSession().setAttribute(Constants.USER_NAME, user_name);
		getSession().setAttribute(Constants.USER_ID, user_id);

		getSession().setAttribute(Constants.MEM_TYPE, mem_type);
		// 会员用户类型 1：管理员 2：子账户
		getSession().setAttribute(Constants.USER_TYPE, user_type);
		getSession().setAttribute(Constants.ROLE_ID, role_code);
		// syscode sys:运营商 com:会员
		getSession().setAttribute("syscode", DEFAULT_SYSCODE_VALUE);

		// 会员级别
		getSession().setAttribute("level_code", level_code);

		// 会员的菜单权限和操作权限
		getSession().setAttribute("menu_right", menu_right);
		getSession().setAttribute("oper_right", oper_right);
		
		out.write("4");

	}
	
	
	/**
	 * 方法描述：AJAX请求：会员购买产品的时候，判断是否购买自己的商品
	 * 
	 * @return
	 * @throws Exception
	 */
	public void isCheckCust_id() throws Exception{
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String cust_id = "",srtreturn = "";
		if(session_cust_id!=null&&!session_cust_id.equals("")){
			if(request.getParameter("cust_id") != null){
				cust_id = request.getParameter("cust_id").toString();
			}
			if(this.session_cust_id.equals(cust_id)){
				//表示不能购买自己的发布的商品
				srtreturn="1";
			}
			else{
				//可以进行购买
				srtreturn="2";
			}
		}
		else{
			//用户没有登录
			srtreturn="3";
		}
		out.write(srtreturn);

	}
	
	/**
	 * 方法描述：会员登出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String exit() throws Exception {
		getSession().invalidate();	 
		return Constants.MEMBER_SIGNIN;
	}
	/**
	 * 方法描述：商城会员登录页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String signin() throws Exception{		
		return goUrl("signin");
	}

		
	/**
	 * 方法描述：商城会员登录方法判断
	 * 
	 * @return
	 * @throws java.io.IOException
	 * @throws Exception
	 */
	public String signinUser() throws IOException {
		String loc = "";
		if (getRequest().getParameter("loc") != null) {
			loc = getRequest().getParameter("loc");
		}
		String user_name="";
		if (memberuser != null && memberuser.getUser_name() != null) {
			user_name = memberuser.getUser_name();
		}
		
		String passwd="";
		if (memberuser != null && memberuser.getPasswd() != null) {
			passwd = memberuser.getPasswd();
		}
		
		String sysrand = "";
		// 获取系统生成的验证码
		if (getSession().getAttribute("sysrand") != null) {
			sysrand = getSession().getAttribute("sysrand").toString();
		}
		
		Map pageMap = new HashMap();	
		if (ValidateUtil.isRequired(user_name)) {
			this.addFieldError("memberuser.user_name", "请输入用户名");
			return goUrl("signin");
		}
        pageMap.put("user_name", user_name);         
        List loginuserList = this.memberuserService.getList(pageMap);
		if (ValidateUtil.isRequired(passwd)) {
			this.addFieldError("memberuser.passwd", "请输入密码");
			return goUrl("signin");
		}
		passwd = Md5.getMD5(passwd.getBytes());
		pageMap.put("passwd", passwd);
		loginuserList = this.memberuserService.getList(pageMap);
		if (loginuserList == null || loginuserList.size() == 0) {
			this.addFieldError("memberuser.passwd", "密码不正确");
			return goUrl("signin");
		}else{
			Map listMap=new HashMap();
			listMap=(HashMap)loginuserList.get(0);
			if(listMap.get("user_name")!=null){
				user_name=listMap.get("user_name").toString();
			}
		}
		if (ValidateUtil.isRequired(commentrand)) {
			this.addFieldError("commentrand", "请输入验证码");
			return goUrl("signin");
		}
		if (!sysrand.equals(commentrand)) {
			this.addFieldError("commentrand", "验证码输入不正确");
			return goUrl("signin");
		}
		String user_id = "", cust_id = "", user_type = "", role_code = "", is_active = "";
		// mem_type 0：企业 1：个人
		String mem_type = "";
		// 会员级别
		String level_code = "";
		if (loginuserList != null && loginuserList.size() > 0) {
			Map loginMap = (HashMap) loginuserList.get(0);
			if (loginMap.get("user_id") != null) {
				user_id = loginMap.get("user_id").toString();
			}
			if (loginMap.get("cust_id") != null) {
				cust_id = loginMap.get("cust_id").toString();
			}
			if (loginMap.get("user_type") != null) {
				user_type = loginMap.get("user_type").toString();
			}
			if (loginMap.get("role_code") != null) {
				role_code = loginMap.get("role_code").toString();
			}
			if (loginMap.get("is_active") != null) {
				is_active = loginMap.get("is_active").toString();
			}
			if (loginMap.get("mem_type") != null) {
				mem_type = loginMap.get("mem_type").toString();
			}
			if (loginMap.get("level_code") != null) {
				level_code = loginMap.get("level_code").toString();
			}
		}
		// is_active 0：已激活 1：未激活
		// 在系统开通注册激活的情况下，注册后必须收邮件，点击激活链接，方可激活账号，未激活账号不允许登陆
		if (is_active.equals("") || is_active.equals("1")) {
			this.addFieldError("memberuser.user_name", "用户未激活");
			return goUrl("signin");
		}

		//判断用户是否已经通过审核，是否允许登录，如果是的话
		if(cfg_mb_islogin.equals("1")){
			Map memMap=new HashMap();
			memMap.put("cust_id",cust_id);
			List list=this.memberService.getList(memMap);
			if (list != null && list.size() > 0) {
				Map listMap=new HashMap();
				listMap=(HashMap)list.get(0);
				String info_state="";
				if(listMap.get("info_state")!=null){
					info_state=listMap.get("info_state").toString();
				}
				if(info_state.equals("0")){
					this.addFieldError("memberuser.user_name", "用户未审核");
					return goUrl("signin");
				}
			}
		}		
		
		
		// 根据会员级别level_code找出该级别下的菜单权限和操作权限
		String menu_right = "", oper_right = "";
		if (!level_code.equals("")) {
			Memberlevel memberlevel = this.memberlevelService.get(level_code);
			if (memberlevel != null && memberlevel.getMenu_right() != null) {
				menu_right = memberlevel.getMenu_right();
			}
			if (memberlevel != null && memberlevel.getOper_right() != null) {
				oper_right = memberlevel.getOper_right();
			}
		}

		// user_type 1：会员管理员 2：会员子账号
		if (user_type.equals("2") && !role_code.equals("")) {
			// 找出当前角色拥有哪些权限
			String role_menu_right = "", role_oper_right = "";
			Role role = this.roleService.get(role_code);
			if (role != null && role.getMenu_right() != null) {
				role_menu_right = role.getMenu_right();
			}
			if (role != null && role.getOper_right() != null) {
				role_oper_right = role.getOper_right();
			}

			// 会员子账号的菜单权限和操作权限都是根据会员级别来的
			// 如果会员的级别被降下来了，想要的菜单权限和操作权限肯定也会少很多
			// 可子账号还保留了之前的权限
			// 所以下面就要过滤一下了，如果当前的角色权限的长度大于级别权限的长度的话，就要过滤喽

			// 菜单权限过滤
			if (role_menu_right.length() > menu_right.length()) {
				String[] menu = role_menu_right.split(",");
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < menu.length; i++) {
					if (!menu[i].equals("") && menu_right.indexOf(menu[i]) > -1) {
						sb.append(menu[i]);
						sb.append(",");
					}
				}
				menu_right = sb.toString();
			}

			// 操作权限过滤
			if (role_oper_right.length() > oper_right.length()) {
				String[] menu = role_oper_right.split(",");
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < menu.length; i++) {
					if (!menu[i].equals("") && oper_right.indexOf(menu[i]) > -1) {
						sb.append(menu[i]);
						sb.append(",");
					}
				}
				oper_right = sb.toString();
			}

		}

		// 将值存入session中
		getSession().setAttribute(Constants.CUST_ID, cust_id);
		getSession().setAttribute(Constants.CUST_TYPE, Constants.MEMBER_TYPE);
		getSession().setAttribute(Constants.USER_NAME, user_name);
		getSession().setAttribute(Constants.USER_ID, user_id);

		getSession().setAttribute(Constants.MEM_TYPE, mem_type);
		// 会员用户类型 1：管理员 2：子账户
		getSession().setAttribute(Constants.USER_TYPE, user_type);
		getSession().setAttribute(Constants.ROLE_ID, role_code);
		// syscode sys:运营商 com:会员
		getSession().setAttribute("syscode", DEFAULT_SYSCODE_VALUE);

		// 会员级别
		getSession().setAttribute("level_code", level_code);

		// 会员的菜单权限和操作权限
		getSession().setAttribute("menu_right", menu_right);
		getSession().setAttribute("oper_right", oper_right);
        
		//记住用户名
		savecokes();
		
		this.addActionMessage("B2C商城会员登陆");

		if (!loc.equals("")) {
			getResponse().sendRedirect(loc);
			return null;
		}
		
		getResponse().sendRedirect("/bmall-buyer.action");
		return goUrl("signin");
		
	}
	
	//获取COKES的值
	public String readcokes() {
		HttpServletRequest request = getRequest();
		Cookie cokSignName = this.getCookieByName(request, "signinName");
		String user_name = "";
		if (cokSignName != null && cokSignName.getValue() != null) {
			user_name = cokSignName.getValue();
		}
		return user_name;
	}

	//保存COKES的值
	public void savecokes() {
		HttpServletResponse response = getResponse();
		int loginMaxAge = 30 * 24 * 60 * 60; //定义账户密码的生命周期，这里是一个月。单位为秒
		String user_name = this.memberuser.getUser_name();
		String password = this.memberuser.getPasswd();
		password=Md5.getMD5(password.getBytes());
		//保存用户名
		if (mallusername != null && mallusername.equals("on")) {
			addCookie(response, "signinName", user_name, loginMaxAge);
		}
		//自动登录
		if(autosignin != null && autosignin.equals("on")){
			addCookie(response, "username", user_name+"=="+password, loginMaxAge);
		}
	}
	
	
	
	
	/**
	 * 设置cookie（接口方法）
	 * 
	 * @param response
	 * @param name
	 *            cookie名字
	 * @param value
	 *            cookie值
	 * @param maxAge
	 *            cookie生命周期 以秒为单位
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		if (maxAge > 0)
			cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	/**
	 * 根据名字获取cookie（接口方法）
	 * 
	 * @param request
	 * @param name
	 *            cookie名字
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie;
		} else {
			return null;
		}
	}

	/**
	 * 将cookie封装到Map里面（非接口方法）
	 * 
	 * @param request
	 * @return
	 */
	private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}
	
	
	
	public Emailtemplate getEmailtemplate() {
		return emailtemplate;
	}

	public void setEmailtemplate(Emailtemplate emailtemplate) {
		this.emailtemplate = emailtemplate;
	}

	public Memberuser getMemberuser() {
		return memberuser;
	}

	public void setMemberuser(Memberuser memberuser) {
		this.memberuser = memberuser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCommentrand() {
		return commentrand;
	}

	public void setCommentrand(String commentrand) {
		this.commentrand = commentrand;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMallusername() {
		return mallusername;
	}
	public void setMallusername(String mallusername) {
		this.mallusername = mallusername;
	}
	public String getAutosignin() {
		return autosignin;
	}
	public void setAutosignin(String autosignin) {
		this.autosignin = autosignin;
	}

}
