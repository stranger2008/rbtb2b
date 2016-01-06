/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: WebMemberAction.java 
 */
package com.rbt.webaction;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rbt.common.Md5;
import com.rbt.common.link.discuz.discuzOptUtil;
import com.rbt.common.util.MailUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Emailhistory;
import com.rbt.model.Emailtemplate;
import com.rbt.model.Levelinfo;
import com.rbt.model.Member;
import com.rbt.model.Membercredit;
import com.rbt.model.Memberfund;
import com.rbt.model.Memberinter;
import com.rbt.model.Memberuser;
import com.rbt.service.ICommparaService;
import com.rbt.service.IEmailhistoryService;
import com.rbt.service.IEmailtemplateService;
import com.rbt.service.ILevelinfoService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMembercreditService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IMemberinterService;
import com.rbt.service.IMemberuserService;
import com.rbt.function.SysconfigFuc;

/**
 * @function 功能 会员action类
 * @author 创建人 邱景岩
 * @date 创建日期 Wed Jul 13 08:48:07 CST 2011
 */
@Controller
public class WebmemberAction extends WebbaseAction {

	/*
	 * 序列化
	 */
	private static final long serialVersionUID = 7214704723619945814L;
	/*
	 * 系统参数设置默认变量名 cfg_mb_notallow：不允许注册的登陆名 cfg_mb_isaudit:注册是否需要人工审核 reg_active:会员注册成功是否需要邮件激活 cfg_webname：网站名称
	 */
	private static final String CFG_MB_NOTALLOW = "cfg_mb_notallow";
	private static final String CFG_MB_ISAUDIT = "cfg_mb_isaudit";
	private static final String CFG_MB_EMAILACTIVE = "cfg_mb_emailactive";
	private static final String TEMP_CODE = "reg_active";
	private static final String CFG_WEBNAME = "cfg_webname";
	private static final String CFG_BASEHOST = "cfg_basehost";
	/*
	 * 注册会员是否需要邮箱激活
	 */
	public String isemailactive;
	/*
	 * 注册用户名
	 */
	public String user_name;
	/*
	 * 注册密码
	 */
	public String passwd;
	/*
	 * 验证码
	 */
	public String commentrand;
	/*
	 * 各大邮箱的登录地址
	 */
	public String emailurl;
	
	/*
	 * 会员对象
	 */
	public Member member;
	/*
	 * 会员账号对象
	 */
	public Memberuser memberuser;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IMemberService memberService;
	/*
	 * 参数业务接口
	 */
	@Autowired
	public ICommparaService commparaService;
	/*
	 * 会员账号业务接口
	 */
	@Autowired
	public IMemberuserService memberuserService;
	/*
	 * 会员级别信息业务接口
	 */
	@Autowired
	public ILevelinfoService levelinfoService;
	/*
	 * 会员资金业务接口
	 */
	@Autowired
	public IMemberfundService memberfundService;
	/*
	 * 会员积分业务接口
	 */
	@Autowired
	public IMemberinterService memberinterService;
	/*
	 * 会员信誉指数业务接口
	 */
	@Autowired
	public IMembercreditService membercreditService;
	/*
	 * 邮件模板业务接口
	 */
	@Autowired
	public IEmailtemplateService emailtemplateService;
	/*
	 * 邮件发送历史记录
	 */
	@Autowired
	public IEmailhistoryService emailhistoryService;

	/*
	 * 会员信息集合
	 */
	public List memberList;
	/*
	 * 参数集合
	 */
	public List commparaList;
	/*
	 * 经营模式信息
	 */
    public List clientStrList;
    /*
     * 企业类型集合
     */
    public List cust_typeList;
	/*
	 * 分类的字符串
	 */
    public String cat_attr;
	/*
	 * 地区的字符串
	 */
    public String area_attr;
	/*
	 * 存入所属分类串的隐藏域
	 */
    public String hiddenvalue;
	/*
	 * 定义隐藏的等级
	 */
    public String hidden_up_level;
	/*
	 * 定义所属分类串的上一级ID
	 */
    public String hidden_up_cate_id;
	/*
	 * 定义所属地区的隐藏域
	 */
    public String hidden_area_value;
	/*
	 * 定义所属地区的上一级ID
	 */
    public String hidden_up_area_id;
    public String select_cat_id;

	/**
	 * 方法描述：检查用户名是否已经注册
	 * 
	 * @return
	 * @throws Exception
	 */
	public void checkUserName() throws Exception {
		PrintWriter out = getResponse().getWriter();
		getRequest().setCharacterEncoding("UTF-8");
		getResponse().setCharacterEncoding("UTF-8");
		if (user_name != null && existsTitle(user_name, "", "memberuser", "user_name")) {
			out.write("1");
		} else {
			String notallow_name = "";
			notallow_name = SysconfigFuc.getSysValue(CFG_MB_NOTALLOW);
			if (notallow_name.indexOf(user_name) > -1) {
				out.write("2");
			}
		}
	}
	/**
	 * 方法描述：服务条款页面跳转
	 * 
	 * @author 陈晓艺
	 * @return
	 * @throws Exception
	 * @date : Apr 18, 2012 1:23:19 PM
	 */
	public String service(){		
		return goUrl("service");		
	}
	
	/**
	 * 方法描述：检查是否已存在邮箱
	 * 
	 * @return
	 * @throws Exception
	 */
	public void checkEmail() throws Exception {
		PrintWriter out = getResponse().getWriter();
		getRequest().setCharacterEncoding("UTF-8");
		getResponse().setCharacterEncoding("UTF-8");
		String email = getRequest().getParameter("email");
		if (email != null && existsTitle(email,"","member","email")) {
			out.write("1");
		}
	}
    
	/**
	 * 方法描述：验证公司名称是否已经存在
	 * 
	 * @return
	 * @throws Exception
	 */
	public void checkCompanyname() throws Exception {
		getRequest().setCharacterEncoding("UTF-8");
		getResponse().setCharacterEncoding("UTF-8");
		PrintWriter out = getResponse().getWriter();
		String cust_name = "";
		if (!ValidateUtil.isRequired(getRequest().getParameter("cust_name"))) {
			cust_name = URLDecoder.decode(getRequest().getParameter("cust_name"), "UTF-8");
			if (existsTitle(cust_name,"","member","cust_name")) {
				out.write("1");
			}
		}
	}
	
	/**
	 * 方法描述：验证码是否正确
	 * 
	 * @return
	 * @throws Exception
	 */
	public void validateCode() throws Exception {
		PrintWriter out = getResponse().getWriter();
		getRequest().setCharacterEncoding("UTF-8");
		getResponse().setCharacterEncoding("UTF-8");
		String sysrand = "";
		// 获取系统生成的验证码
		if (getSession().getAttribute("sysrand") != null) {
			sysrand = getSession().getAttribute("sysrand").toString();
		}
		if (!sysrand.equals(commentrand)) {
			out.write("1");
		}
	}
	
	/**
	 * 方法描述：跳转到注册页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String joinus() throws Exception {
		Map comMap = new HashMap();
		comMap.put("para_code", "client_status");
		clientStrList = commparaService.getList(comMap);
		comMap.put("para_code", "cust_type");
		cust_typeList = commparaService.getList(comMap);
		return goUrl("register");
	}

	/**
	 * 方法描述：会员注册
	 * 
	 * @return
	 * @throws Exception
	 */
	public String register() throws Exception {
		 // 用于所属分类的回选开始
		loadCategory();
		// 用于所属地区的回选开始
		loadArea();
		if (ValidateUtil.isRequired(user_name)) {
			this.addFieldError("user_name", "用户名不能为空");
			return joinus();
		}
		String notallow_name = "";
		notallow_name = SysconfigFuc.getSysValue(CFG_MB_NOTALLOW);
		if (notallow_name.indexOf(user_name) > -1) {
			this.addFieldError("user_name", "本网站不允许注册此类用户名");
			return joinus();
		}
		if (existsTitle(user_name,"","memberuser","user_name")) {
			this.addFieldError("user_name", "该用户名已经存在,请重新输入");
			return joinus();
		}
		if (ValidateUtil.isRequired(passwd)) {
			this.addFieldError("passwd", "密码不能为空");
			return joinus();
		} else if (ValidateUtil.isAlphasLimtLength(passwd)) {
			this.addFieldError("passwd", "密码只能由6-20个字母、数字、下划线组成");
			return joinus();
		}
		if (ValidateUtil.isRequired(member.getContact_name())) {
			this.addFieldError("member.contact_name", "联系人不能为空");
			return joinus();
		}
		//验证所属地区是否有选择
		if (ValidateUtil.isRequired(area_attr) || hidden_area_value.indexOf("0")>-1) {
			this.addFieldError("area_attr", "请选择地区");
			return joinus();
		}
		area_attr=area_attr.replace(" ","");
		this.member.setArea_attr(area_attr);
		// 用于所属地区的结束 
		if (ValidateUtil.isRequired(member.getEmail())) {
			this.addFieldError("member.email", "电子邮件不能为空");
			return joinus();
		} else if (ValidateUtil.isEmail(member.getEmail())) {
			this.addFieldError("member.email", "电子邮件格式不正确，请重新输入");
			return joinus();
		}
		if (existsTitle(member.getEmail(),"","member","email")) {
			this.addFieldError("member.email", "该电子邮箱已被使用，请重新输入");
			return joinus();
		}
		if (!member.getPhone().equals("") && ValidateUtil.isTelephone(member.getPhone())) {
			this.addFieldError("member.phone", "电话号码格式不正确");
			return joinus();
		}
		if (!member.getContact_cellphone().equals("") && ValidateUtil.isMobile(member.getContact_cellphone())) {
			this.addFieldError("member.contact_cellphone", "手机号码格式不正确");
			return joinus();
		}
		if (member.getMem_type().equals("0")) {
			if (ValidateUtil.isRequired(member.getCust_name())) {
				this.addFieldError("member.cust_name", "企业名称不能为空");
				return joinus();
			}
			if (existsTitle(member.getCust_name(),"","member","cust_name")) {
				this.addFieldError("member.cust_name", "企业名称不能为重复");
				return joinus();
			}
			if (member.getCust_type().equals("0")) {
				this.addFieldError("member.cust_type", "请选择企业类型");
				return joinus();
			}
			//去掉经营模式空格
			if (ValidateUtil.isRequired(member.getClient_status())) {
				this.addFieldError("member.client_status", "请选择经营模式");
				return joinus();
			}else{
				String clientName = member.getClient_status().replace(" ", "");
				member.setClient_status(clientName);
			}
			//验证所属分类是否有选择
			if (ValidateUtil.isRequired(cat_attr) || cat_attr.indexOf("0")>-1) {
				this.addFieldError("cate_attr", "请选择分类");
				return joinus();
			}			
			cat_attr=cat_attr.replace(" ","");
			this.member.setCat_attr(cat_attr);
			// 用于所属分类的结束
		}
		if(!ValidateUtil.isRequired(member.getMem_type()) && "1".equals(member.getMem_type())){
			// 会员类型
		    member.setCust_name(member.getContact_name());
		}
		// 会员状态
		// 注册是否需要人工审核
		String isaduit = SysconfigFuc.getSysValue(CFG_MB_ISAUDIT);
		if ("0".equals(isaduit)) {
			member.setInfo_state("0");// 0:未审核
		} else {
			member.setInfo_state("1");// 1:审核通过
		}
		// 注册会员是否需要邮件激活
		isemailactive = SysconfigFuc.getSysValue(CFG_MB_EMAILACTIVE);
		// 是否推荐 0:未推荐
		member.setRecommend("0");
		if (isemailactive.equals("0")) {
			// 激活状态 1：未激活
			member.setIs_active("1");
		}
		else {                    
			// 激活状态 0：已激活
			member.setIs_active("0");
		}
		
		//discuz保存原始密码
		String discuzpwd=passwd;
		//discuz保存邮箱
		String discuzemail=member.getEmail();
		
		// 插入一条会员信息后返回该会员的ID
		String cust_id = this.memberService.insertMember(member);
		// 获取刚插入的会员对象
		Member mem = this.memberService.get(cust_id);
		// 初始化会员账号对象
		Memberuser user = new Memberuser();
		user.setCust_id(cust_id);
		user.setUser_type("1");
		user.setUser_name(getUser_name());
		passwd = Md5.getMD5(passwd.getBytes());
		user.setPasswd(passwd);
		user.setEmail(member.getEmail());
		user.setCellphone(member.getContact_cellphone());
		user.setPhone(member.getPhone());
		String user_id = this.memberuserService.insertMemberuser(user);
		// 初始化会员级别信息对象
		Levelinfo levelinfo = new Levelinfo();
		levelinfo.setCust_id(cust_id);
		if (mem.getMem_type().equals("0")) {
			levelinfo.setLevel_code("3");// 初始化会员类型为企业的级别为3,3代表普通企业会员
		} else if (mem.getMem_type().equals("1")) {
			levelinfo.setLevel_code("1");// 初始化会员类型为个人的级别为1,1代表普通隔个人会员
		}
		levelinfo.setStart_date(mem.getIn_date());
		levelinfo.setEnd_date("2050-12-31");//会员注册时默认的会员级别的过期时间
		levelinfo.setUser_id(user_id);
		this.levelinfoService.insert(levelinfo);
		// 初始化会员资金
		Memberfund memberfund = new Memberfund();
		memberfund.setCust_id(cust_id);
		memberfund.setFreeze_num("0");
		memberfund.setFund_num("0");
		memberfund.setUse_num("0");
		memberfund.setPay_passwd(passwd);
		this.memberfundService.insert(memberfund);
		// 初始化会员积分对象
		Memberinter memberinter = new Memberinter();
		memberinter.setCust_id(cust_id);
		memberinter.setFund_num("0");
		this.memberinterService.insert(memberinter);
		// 初始化会员信誉指数
		Membercredit membercredit = new Membercredit();
		membercredit.setCust_id(cust_id);
		membercredit.setC_num("0");
		this.membercreditService.insert(membercredit);
		if (isemailactive.equals("0")) {
			String finalContent = "";
			String web_name = "";
			String domain_name = "";
			Emailtemplate emailTemp = this.emailtemplateService.getEmailtemplateByTempcode(TEMP_CODE);
			// 获取网站名称
			web_name = SysconfigFuc.getSysValue(CFG_WEBNAME);
			// 获取网站域名
			domain_name = SysconfigFuc.getSysValue(CFG_BASEHOST);
			// 获取模板内容
			String temp_con = emailTemp.getTemp_con();
			
			//验证邮件模板内容是否为空,若为空，则不发送邮件
			if(ValidateUtil.isRequired(temp_con)){
				// 用正文的内容替换模板中的标签，以达到最终的邮件正文
				finalContent = temp_con.replace("{cfg_basehost}", domain_name).replace("{user_name}", user_name).replace("{web_name}", web_name).replace(
						"{passwd}", getPasswd()).replace("{user_id}", user_id);
				// 邮件发送工具
				MailUtil mailUtil = new MailUtil();
				String title = "会员激活邮件";
				mailUtil.sendMail(title, finalContent, mem.getEmail());
				// 邮件发送历史记录
				Emailhistory emailhistory = new Emailhistory();
				emailhistory.setSend_email(mailUtil.getFromMailAddr());
				emailhistory.setSend_name(mailUtil.getUser_name());
				emailhistory.setContent(finalContent);
				emailhistory.setGet_email(mem.getEmail());
				emailhistory.setTitle(title);
				emailhistory.setSend_date(mem.getIn_date());
				emailhistory.setTemp_id(emailTemp.getTemp_id());
				emailhistory.setUser_id(user_id);
				this.emailhistoryService.insert(emailhistory);
				emailurl = this.emailSubstring(mem.getEmail());
				return goUrl("registerok");
			}			
			
		}
		mem = null;
	    levelinfo = null;
		memberfund = null;
		memberinter = null;
		user = null;
		membercredit = null;
		
		 //同步Discuz论坛注册
		 discuzOptUtil discuzopt=new discuzOptUtil();
		 discuzopt.userregister(user_name,discuzpwd,discuzemail,cfg_discuz_open);
		
		return goUrl("registerok");
	}

	/**
	 * 方法描述：商城会员注册
	 * 
	 * @return
	 * @throws Exception
	 */
	public String malljoinus() throws Exception{
		return goUrl("joinus");
	}
	
	/**
	 * 方法描述：商城后台注册
	 * 
	 * @return
	 * @throws Exception
	 */
	public String mallregister() throws Exception{
		if (ValidateUtil.isRequired(user_name)) {
			this.addFieldError("user_name", "用户名不能为空");
			return malljoinus();
		}
		String notallow_name = "";
		notallow_name = SysconfigFuc.getSysValue(CFG_MB_NOTALLOW);
		if (notallow_name.indexOf(user_name) > -1) {
			this.addFieldError("user_name", "本网站不允许注册此类用户名");
			return malljoinus();
		}
		if (existsTitle(user_name,"","memberuser","user_name")) {
			this.addFieldError("user_name", "该用户名已经存在,请重新输入");
			return malljoinus();
		}
		if (ValidateUtil.isRequired(passwd)) {
			this.addFieldError("passwd", "密码不能为空");
			return malljoinus();
		} else if (ValidateUtil.isAlphasLimtLength(passwd)) {
			this.addFieldError("passwd", "密码只能由6-20个字母、数字、下划线组成");
			return malljoinus();
		} 
		if (ValidateUtil.isRequired(member.getEmail())) {
			this.addFieldError("member.email", "电子邮件不能为空");
			return malljoinus();
		} else if (ValidateUtil.isEmail(member.getEmail())) {
			this.addFieldError("member.email", "电子邮件格式不正确，请重新输入");
			return malljoinus();
		}
		if (existsTitle(member.getEmail(),"","member","email")) {
			this.addFieldError("member.email", "该电子邮箱已被使用，请重新输入");
			return malljoinus();
		}
		member.setMem_type("1");//会员类型 1:个人会员
		member.setCust_name(user_name);
		member.setCust_rage("3");
		// 会员状态
		// 注册是否需要人工审核
		String isaduit = SysconfigFuc.getSysValue(CFG_MB_ISAUDIT);
		if ("0".equals(isaduit)) {
			member.setInfo_state("0");//  0:未审核
		} else {
			member.setInfo_state("1");//  1:审核通过
		}
		// 注册会员是否需要邮件激活
		isemailactive = SysconfigFuc.getSysValue(CFG_MB_EMAILACTIVE);
		// 是否推荐 0:未推荐
		member.setRecommend("0");
		if (isemailactive.equals("0")) {
			// 激活状态 1：未激活
			member.setIs_active("1");
		}
		else {
			// 激活状态 0：已激活
			member.setIs_active("0");
		}
		// 插入一条会员信息后返回该会员的ID
		String cust_id = this.memberService.insertMember(member);
		// 获取刚插入的会员对象
		Member mem = this.memberService.get(cust_id);
		// 初始化会员账号对象
		Memberuser user = new Memberuser();
		user.setCust_id(cust_id);
		user.setUser_type("1");
		user.setUser_name(getUser_name());
		passwd = Md5.getMD5(passwd.getBytes());
		user.setPasswd(passwd);
		user.setEmail(member.getEmail());
		user.setCellphone(member.getContact_cellphone());
		user.setPhone(member.getPhone());
		String user_id = this.memberuserService.insertMemberuser(user);
		// 初始化会员级别信息对象
		Levelinfo levelinfo = new Levelinfo();
		levelinfo.setCust_id(cust_id);
		levelinfo.setLevel_code("1");// 初始化会员类型为个人的级别为1,1代表普通隔个人会员
		levelinfo.setStart_date(mem.getIn_date());
		levelinfo.setEnd_date("2050-12-31");//会员注册时默认的会员级别的过期时间
		levelinfo.setUser_id(user_id);
		this.levelinfoService.insert(levelinfo);
		// 初始化会员资金
		Memberfund memberfund = new Memberfund();
		memberfund.setCust_id(cust_id);
		memberfund.setFreeze_num("0");
		memberfund.setFund_num("0");
		memberfund.setUse_num("0");
		memberfund.setPay_passwd(passwd);
		this.memberfundService.insert(memberfund);
		// 初始化会员积分对象
		Memberinter memberinter = new Memberinter();
		memberinter.setCust_id(cust_id);
		memberinter.setFund_num("0");
		this.memberinterService.insert(memberinter);
		// 初始化会员信誉指数
		Membercredit membercredit = new Membercredit();
		membercredit.setCust_id(cust_id);
		membercredit.setC_num("0");
		this.membercreditService.insert(membercredit);
		if (isemailactive.equals("0")) {
			String finalContent = "";
			String web_name = "";
			String domain_name = "";
			Emailtemplate emailTemp = this.emailtemplateService.getEmailtemplateByTempcode(TEMP_CODE);
			// 获取网站名称
			web_name = SysconfigFuc.getSysValue(CFG_WEBNAME);
			// 获取网站域名
			domain_name = SysconfigFuc.getSysValue(CFG_BASEHOST);
			// 获取模板内容
			String temp_con = emailTemp.getTemp_con();
			
			//验证邮件模板内容是否为空,若为空，则不发送邮件
			if(ValidateUtil.isRequired(temp_con)){
				// 用正文的内容替换模板中的标签，以达到最终的邮件正文
				finalContent = temp_con.replace("{cfg_basehost}", domain_name).replace("{user_name}", user_name).replace("{web_name}", web_name).replace(
						"{passwd}", getPasswd()).replace("{user_id}", user_id);
				// 邮件发送工具
				MailUtil mailUtil = new MailUtil();
				String title = "会员激活邮件";
				mailUtil.sendMail(title, finalContent, mem.getEmail());
				// 邮件发送历史记录
				Emailhistory emailhistory = new Emailhistory();
				emailhistory.setSend_email(mailUtil.getFromMailAddr());
				emailhistory.setSend_name(mailUtil.getUser_name());
				emailhistory.setContent(finalContent);
				emailhistory.setGet_email(mem.getEmail());
				emailhistory.setTitle(title);
				emailhistory.setSend_date(mem.getIn_date());
				emailhistory.setTemp_id(emailTemp.getTemp_id());
				emailhistory.setUser_id(user_id);
				this.emailhistoryService.insert(emailhistory);
				emailurl = this.emailSubstring(mem.getEmail());
				return goUrl("joinusok");
			}			
			
		}
		mem = null;
	    levelinfo = null;
		memberfund = null;
		memberinter = null;
		user = null;
		membercredit = null;
		return goUrl("joinusok");
	}
	
	/**
	 * @function 功能 获取各大邮箱登录url地址
	 * @author 创建人 邱景岩
	 * @date 创建日期 Sep 20, 2011 2:42:19 PM
	 */
	public String emailSubstring(String email) {
		String loginemail[] = { "yeah.net", "gmail.com", "mail.sohu.com", "mail.sogou.com", "foxmail.com", "mail.qq.com", "mail.56.com", "mail.cn.yahoo.com",
				"mail.sina.com", "mail.hexun.com", "live.cn", "126.com", "mail.163.com" };
		String emailstr = "";
		String emailSuffix[] = email.split("@");
		for (int i = 0; i < loginemail.length; i++) {
			if (loginemail[i].indexOf(emailSuffix[1]) > -1) {
				emailstr = loginemail[i];
			}
		}
		return emailstr;
	}

	/**
	 * @function 功能 注册会员邮箱激活账号
	 * @author 创建人 邱景岩
	 * @date 创建日期 Sep 20, 2011 4:55:55 PM
	 */
	public String activeMember() throws Exception {
		getRequest().setCharacterEncoding("UTF-8");
		getResponse().setCharacterEncoding("UTF-8");
		String user_id = getRequest().getParameter("user_id");
		memberuser = this.memberuserService.get(user_id);
		member = this.memberService.get(memberuser.getCust_id());
		// is_active 激活状态 0：激活
		member.setIs_active("0");
		// state_code 审核状态：2:审核通过
		member.setInfo_state("1");
		this.memberService.update(member);// 激活会员
		return goUrl("active_successfully");
	}
	
	/**
	 * @return the member
	 */
	public Member getMember() {
		return member;
	}

	/**
	 * @param Member
	 *            the member to set
	 */
	public void setMember(Member member) {
		this.member = member;
	}

	/**
	 * @return the MemberList
	 */
	public List getMemberList() {
		return memberList;
	}

	/**
	 * @param memberList
	 *            the MemberList to set
	 */
	public void setMemberList(List memberList) {
		this.memberList = memberList;
	}

	/**
	 * @return the commparaList
	 */
	public List getCommparaList() {
		return commparaList;
	}

	/**
	 * @param commparaList
	 *            the commparaList to set
	 */
	public void setCommparaList(List commparaList) {
		this.commparaList = commparaList;
	}

	/**
	 * @return the cat_attr
	 */
	public String getCat_attr() {
		return cat_attr;
	}

	/**
	 * @param cat_attr
	 *            the cat_attr to set
	 */
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}

	/**
	 * @return the area_attr
	 */
	public String getArea_attr() {
		return area_attr;
	}

	/**
	 * @param area_attr
	 *            the area_attr to set
	 */
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}

	/**
	 * @return the hiddenvalue
	 */
	public String getHiddenvalue() {
		return hiddenvalue;
	}

	/**
	 * @param hiddenvalue
	 *            the hiddenvalue to set
	 */
	public void setHiddenvalue(String hiddenvalue) {
		this.hiddenvalue = hiddenvalue;
	}

	/**
	 * @return the hidden_up_level
	 */
	public String getHidden_up_level() {
		return hidden_up_level;
	}

	/**
	 * @param hidden_up_level
	 *            the hidden_up_level to set
	 */
	public void setHidden_up_level(String hidden_up_level) {
		this.hidden_up_level = hidden_up_level;
	}

	/**
	 * @return the hidden_up_cate_id
	 */
	public String getHidden_up_cate_id() {
		return hidden_up_cate_id;
	}

	/**
	 * @param hidden_up_cate_id
	 *            the hidden_up_cate_id to set
	 */
	public void setHidden_up_cate_id(String hidden_up_cate_id) {
		this.hidden_up_cate_id = hidden_up_cate_id;
	}

	/**
	 * @return the hidden_area_value
	 */
	public String getHidden_area_value() {
		return hidden_area_value;
	}

	/**
	 * @param hidden_area_value
	 *            the hidden_area_value to set
	 */
	public void setHidden_area_value(String hidden_area_value) {
		this.hidden_area_value = hidden_area_value;
	}

	/**
	 * @return the hidden_up_area_id
	 */
	public String getHidden_up_area_id() {
		return hidden_up_area_id;
	}

	/**
	 * @param hidden_up_area_id
	 *            the hidden_up_area_id to set
	 */
	public void setHidden_up_area_id(String hidden_up_area_id) {
		this.hidden_up_area_id = hidden_up_area_id;
	}

	/**
	 * @param commentrand
	 *            the commentrand to set
	 */
	public void setCommentrand(String commentrand) {
		this.commentrand = commentrand;
	}

	/**
	 * @return the memberuser
	 */
	public Memberuser getMemberuser() {
		return memberuser;
	}

	/**
	 * @param memberuser
	 *            the memberuser to set
	 */
	public void setMemberuser(Memberuser memberuser) {
		this.memberuser = memberuser;
	}

	/**
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
	}

	/**
	 * @param user_name
	 *            the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	/**
	 * @return the passwd
	 */
	public String getPasswd() {
		return passwd;
	}

	/**
	 * @param passwd
	 *            the passwd to set
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	/**
	 * @return the select_cat_id
	 */
	public String getSelect_cat_id() {
		return select_cat_id;
	}

	/**
	 * @param select_cat_id
	 *            the select_cat_id to set
	 */
	public void setSelect_cat_id(String select_cat_id) {
		this.select_cat_id = select_cat_id;
	}

	/**
	 * @return the emailurl
	 */
	public String getEmailurl() {
		return emailurl;
	}

	/**
	 * @param emailurl
	 *            the emailurl to set
	 */
	public void setEmailurl(String emailurl) {
		this.emailurl = emailurl;
	}

	/**
	 * @return the isemailactive
	 */
	public String getIsemailactive() {
		return isemailactive;
	}

	/**
	 * @param isemailactive
	 *            the isemailactive to set
	 */
	public void setIsemailactive(String isemailactive) {
		this.isemailactive = isemailactive;
	}

	public List getClientStrList() {
		return clientStrList;
	}

	public void setClientStrList(List clientStrList) {
		this.clientStrList = clientStrList;
	}

}
