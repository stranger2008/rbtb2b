/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: SysuserAction.java 
 */

package com.rbt.action;

import java.net.InetAddress;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.Md5;
import com.rbt.common.util.DbUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.MemberuserFuc;
import com.rbt.listener.UserLoginListener;
import com.rbt.model.Organize;
import com.rbt.model.Role;
import com.rbt.model.Sysuser;
import com.rbt.service.IBackupService;
import com.rbt.service.ICertificationService;
import com.rbt.service.IFundtocashService;
import com.rbt.service.ILogsService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMembercertService;
import com.rbt.service.IMembercommentService;
import com.rbt.service.IMemberleaveService;
import com.rbt.service.IMemberlinkService;
import com.rbt.service.IMembernewsService;
import com.rbt.service.IMemberupgradeService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.IOrderinfoService;
import com.rbt.service.IOrganizeService;
import com.rbt.service.IRoleService;
import com.rbt.service.ISubscribeService;
import com.rbt.service.ISysmoduleService;
import com.rbt.service.ISysuserService;
import com.rbt.service.ITopdomainService;

/**
 * @function 功能 系统管理员action类
 * @author 创建人 李良林
 * @date 创建日期 Jun 13, 2011
 */
@Controller
public class SysuserAction extends BaseAction implements Preparable {

	private static final long serialVersionUID = -1223097111722934440L;

	/*
	 * 搜索字段 user_name_s:用户名 nike_name_s:匿名
	 */
	public String user_name_s;

	public String nike_name_s;

	//系统用户管理搜索 角色搜索
	public String role_id_s;

	/*
	 * 会员未审核数量
	 */
	public int member_num;
	/*
	 * 招聘未审核数量
	 */
	public int job_num;
	/*
	 * 简历未审核数量
	 */
	public int resume_num;
	/*
	 * 展会未审核数量
	 */
	public int exhibition_num;
	/*
	 * 求购未审核数量
	 */
	public int buy_num;
	/*
	 * 图库未审核数量
	 */
	public int gallery_num;
	/*
	 * 产品未审核数量
	 */
	public int product_num;
	/*
	 * 会员未审核数量
	 */
	public int download_num;
	/*
	 * 下载未审核数量
	 */
	public int news_num;
	/*
	 * 视频未审核数量
	 */
	public int video_num;
	/*
	 * 供应未审核数量
	 */
	public int supply_num;
	/*
	 * 知道未审核数量
	 */
	public int ask_num;
	/*
	 * 安装时间
	 */
	public String install_date;
	/*
	 * 数据库产品版本信息
	 */
	public String database_product_version;
	/*
	 * 
	 */
	public String database_name;
	/*
	 * 服务器时间
	 */
	public String server_datetime;
	/*
	 * 服务器信息
	 */
	public String server_info;
	/*
	 * 服务器IP
	 */
	public String server_ip;
	/*
	 * 操作系统名称
	 */
	public String OS_name;
	/*
	 * 操作系统架构
	 */
	public String OS_arch;
	/*
	 * 登陆页面输入的验证码
	 */
	public String userrand;

	/*
	 * 系统管理员对象
	 */
	public Sysuser sysuser;
	/*
	 * 所属部门串
	 */
	public String org_attr;
	/*
	 * 所属部门隐藏值
	 */
	public String org_hidden_value;
	public String up_id = "1111111111";
	/*
	 * 业务层接口
	 */
	@Autowired
	public ISysuserService sysuserService;
	@Autowired
	public IRoleService roleService;
	@Autowired
	public ILogsService logsService;
	@Autowired
	public IOrganizeService organizeService;
	@Autowired
	public IMemberuserService memberuserService;
	@Autowired
	private ISysmoduleService sysmoduleService;
	@Autowired
	public IBackupService backupService;
	@Autowired
	public IFundtocashService fundtocashService;
	@Autowired
	public IOrderinfoService orderinfoService;
	@Autowired
	public IMemberService memberService;
	@Autowired
	public IMemberupgradeService memberupgradeService;
	@Autowired
	public ICertificationService certificationService;
	@Autowired
	public IMembercertService membercertService;
	@Autowired
	public IMembernewsService membernewsService;
	@Autowired
	public IMemberlinkService memberlinkService;
	@Autowired
	public ITopdomainService topdomainService;
	@Autowired
	public ISubscribeService subscribeService;
	@Autowired
	public IMembercommentService membercommentService;
	@Autowired
	public IMemberleaveService memberLeaveService;

	
	/*
	 * 列表页用户信息集合
	 */
	public List userList;
	public List roleList;
	public List countlist;
	public List todayList;
	public List loglist;
	public String remusername;
	public String opername;
	public String lastIp;
	public String lastlogintime;
	public String logintimes;
	public int counts;
	public int nowcount;
	public int upgroups;
	public int upcount;
	public Organize organize;
	public int fundcount;
	public int certcount;
	public int auditcount;
	public int auditmcertcount;
	public int newsaudit;
	public int newscount;
	public int linkaudit;
	public int linkcount;
	public int toaudit;
	public int tocount;
	public int subaudit;
	public int subcount;
	public int comaudit;
	public int commcount;
	public int leaveaudit;
	public int leavecount;
	//定义上次登陆时间字段
	public String login_time="";
	//定义上次登陆IP字段
	public String login_IP="";

	// 修改系统用户页面，保存原用户名
	public String user_name_bak;

	/**
	 * 方法描述：返回新增页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		Map map = new HashMap();
		map.put("cust_id", this.session_cust_id);
		roleList = this.roleService.getList(map);
		return goUrl(ADD);
	}

	/**
	 * 方法描述：保存所属部门的隐藏域
	 * 
	 * @return
	 * @throws Exception
	 */
	private void loadOrg(){
		//保存所属部门的隐藏域
		if(org_attr != null){
			if(org_hidden_value != null && !org_hidden_value.equals("")){
				org_hidden_value = up_id + "," + org_attr.replace(" ", "").replace(",0", "");
			}else{
				org_hidden_value = up_id;
			}
			String org_id=org_attr.replace(" ","");
			this.sysuser.setOrg_id(org_id);
		}	
	}
	
	
	/**
	 * 方法描述：新增管理员信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		//保存所属部门的隐藏域
		loadOrg();
		// 通过用户名找出用户信息
		if(!ValidateUtil.isRequired(sysuser.getUser_name()) && existsTitle(sysuser.getUser_name(),"","sysuser","user_name")){
			this.addFieldError("sysuser.user_name", "用户名已存在,请重新输入");
		}
		
		if(!ValidateUtil.isRequired(sysuser.getPasswd()) && !ValidateUtil.isAlphasLimtLength(sysuser.getPasswd())){
				String passwd = sysuser.getPasswd();
				passwd = Md5.getMD5(passwd.getBytes());
				sysuser.setPasswd(passwd);
		}
		
		sysuser.setUser_type("4");//子账户
		sysuser.setLoginnum("0");
		//字段验证
		super.commonValidateField(sysuser);
		if(super.ifvalidatepass){
			return add();
		}
		this.sysuserService.insert(sysuser);
		this.addActionMessage("新增系统管理员信息成功");
		this.sysuser = null;
		return add();
	}

	/**
	 * @function 功能 系统后台默认首页信息
	 * @author 创建人 邱景岩
	 * @date 创建日期 Aug 25, 2011 2:13:08 PM
	 */
	public String main() throws Exception {
		// 绑定运营商中心的会员未审核与今日新增的条数
		admincountList();
		//待审核信息
		getfundaudit();
		//获取未审核订单
		getorderaudit();
		//会员升级
		// 获取操作日志列表
		Map logMap = new HashMap();		
		opername = this.session_user_name;
		logMap.put("user_name", opername);
		logMap.put("start", 0);
		logMap.put("limit", 5);
		loglist = logsService.getList(logMap);
		//获取操作者最后的登录时间和登录IP
		Map userMap = new HashMap();
		userMap.put("user_name", opername);
		List userlist = this.sysuserService.getList(userMap);
		if (userlist != null && userlist.size() > 0) {
			Map lastMap = new HashMap();
			lastMap = (HashMap) userlist.get(0);
			if (lastMap.get("logintime") != null) {
				lastlogintime = lastMap.get("logintime").toString();
			}
			if (lastMap.get("loginip") != null) {
				lastIp = lastMap.get("loginip").toString();
			}
			if (lastMap.get("loginnum") != null) {
				logintimes = lastMap.get("loginnum").toString();
			}
		}
		// 获取安装时间
		Date date = new Date();
		DateFormat formatter = DateFormat.getDateTimeInstance(); // 得到一个指定时区(中国是东8区的)的
		TimeZone timezone = TimeZone.getTimeZone("GMT+08:00 "); // 实例化时区对象
		formatter.setTimeZone(timezone);
		install_date = formatter.format(date);
		DbUtil dbutil = new DbUtil();
		// 获取数据库系统版本信息
		database_product_version = this.backupService.getDatabaseversion();
		// 获取数据库系统名称
		database_name = dbutil.getDbName();
		// 获取服务器时间
		server_datetime = new java.sql.Date(System.currentTimeMillis()).toString();
		// 获取操作系统相关参数
		Properties props = System.getProperties(); // 系统属性
		OS_name = props.getProperty("os.name");// 操作系统名称
		OS_arch = props.getProperty("os.arch");// 操作系统架构
		// 获取服务器IP地址
		InetAddress localhost = InetAddress.getLocalHost();
		server_ip = localhost.getHostAddress();
		// 获取服务器信息
		server_info = ServletActionContext.getServletContext().getServerInfo();
		//获取上一次登陆时间
		Map timemap=new HashMap();
		timemap = MemberuserFuc.getlast_loginTime(this.session_user_id);
		if(timemap.get("in_date")!=null){
			login_time = timemap.get("in_date").toString();
		}
		if(timemap.get("ip")!=null){
			login_IP = timemap.get("ip").toString();
		}
		return SUCCESS;
	}
	
	/**
	 * @MethodDescribe 方法描述 找出后台桌面待审核的数据
	 * @author 创建人 蔡毅存
	 * @date 创建日期 Sep 20, 2011 2:53:16 PM
	 */
	private void getfundaudit(){
		//获取申请提现记录
		HashMap mapfund = new HashMap();
		mapfund.put("order_state", "0");
		fundcount = fundtocashService.getCount(mapfund);
		
		//会员升级未审核
		HashMap mapup = new HashMap();
		mapup.put("audit_state", "0");
		upgroups = memberupgradeService.getCount(mapup);
		//今日新增会员升级
		HashMap mapupcount = new HashMap();
		mapupcount.put("today", "now");
		upcount = memberupgradeService.getCount(mapupcount);
		
		//实名认证未审核
		HashMap mapcert = new HashMap();
		mapcert.put("info_state", "0");
		auditcount = certificationService.getCount(mapcert);
		//今日新增
		//HashMap mapcertcount = new HashMap();
		//mapcertcount.put("today", "now");
		//certcount = certificationService.getCount(mapcertcount);
		
		//荣誉资质未审核
		HashMap  mapmcert = new HashMap();
		mapmcert.put("cert_state", "1");
		auditmcertcount = membercertService.getCount(mapmcert);
		//今日新增荣誉资质
		HashMap mapmcertcount = new HashMap();
		mapmcertcount.put("today", "now");
		certcount = membercertService.getCount(mapmcertcount);
		
		//企业新闻未审核
		HashMap mapnewsaudit = new HashMap();
		mapnewsaudit.put("news_state", "1");
		newsaudit = membernewsService.getCount(mapnewsaudit);
		//今日新增企业新闻
		HashMap mapnewscount = new HashMap();
		mapnewscount.put("today", "now");
	    newscount = membernewsService.getCount(mapnewscount);
	    
	    //友情链接未审核
	    HashMap maplinkaudit = new HashMap();
	    maplinkaudit.put("link_state", "1");
	    linkaudit = memberlinkService.getCount(maplinkaudit);
	    //今日新增友情链接
	    HashMap maplinkcount = new HashMap();
	    maplinkcount.put("today", "now");
	    linkcount = memberlinkService.getCount(maplinkcount);
	    
	    //域名绑定审核
	    HashMap maptoaudit = new HashMap();
	    maptoaudit.put("enabled", "0");
	    toaudit = topdomainService.getCount(maptoaudit);
	    //今日申请域名绑定
	    HashMap maptocount = new  HashMap();
	    maptocount.put("today", "now");
	    tocount = topdomainService.getCount(maptocount);
	    
	    //商机订阅审核
	    HashMap mapsubaudit = new  HashMap();
	    mapsubaudit.put("enabled", "1");
	    subaudit = subscribeService.getCount(mapsubaudit);
	    //今日商机订阅
	    HashMap mapsubcount = new HashMap();
	    mapsubcount.put("today", "now");
	    subcount = subscribeService.getCount(mapsubcount);
	    
	    //评论审核
	    HashMap mapcomaudit = new HashMap();
	    mapcomaudit.put("comm_state", "1");
	    comaudit = membercommentService.getCount(mapcomaudit);
	    //今日评论
	    HashMap mapcommcount = new HashMap();
	    mapcommcount.put("today", "now");
	    commcount = membercommentService.getCount(mapcommcount);
	    
	    //留言审核
	    HashMap mapleaveaudit = new HashMap();
	    mapleaveaudit.put("is_del","1");
	    leaveaudit = memberLeaveService.getCount(mapleaveaudit);
	    //今日新增留言
	    HashMap mapleavecount = new HashMap();
	    mapleavecount.put("today", "now");
	    leavecount = memberLeaveService.getCount(mapleavecount);
	    
	}
	/**
	 * 方法描述：找出后台桌面待审核的数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String fundaudit() throws Exception {
		admincountList();
		getfundaudit();
		return goUrl("more");
	}
	/**
	 * @MethodDescribe 方法描述 找出后台桌面待审核的数据
	 * @author 创建人 蔡毅存
	 * @date 创建日期 Sep 20, 2011 2:53:16 PM
	 */
	//获取未审核订单
	private void getorderaudit(){
		HashMap map =new HashMap();
		//7等待运营商审核
		map.put("order_state","7");
		counts= orderinfoService.getCount(map);
		map.put("today", "now");
		nowcount= orderinfoService.getCount(map);
	}

	/**
	 * @MethodDescribe 方法描述 找出后台桌面未审核的条数
	 * @author 创建人 林俊钦
	 * @date 创建日期 Sep 20, 2011 2:53:16 PM
	 */
	private List admincountList() {
		Map modListMap =new HashMap();
		//找出系统模块的
		List modlist=this.sysmoduleService.getList(modListMap);
		StringBuffer sb=new StringBuffer();
		if(modlist!=null&&modlist.size()>0){
			for(int i=0;i<modlist.size();i++){
				Map listMap=(HashMap)modlist.get(i);
				String module_type="",is_catattr="",module_name="",table_name="";
				if(listMap.get("module_type")!=null){
					module_type=listMap.get("module_type").toString();
				}
				if(module_type.equals("subject")){
					continue;
				}
				if(listMap.get("is_catattr")!=null){
					is_catattr=listMap.get("is_catattr").toString();
				}
				if(listMap.get("module_name")!=null){
					module_name=listMap.get("module_name").toString();
				}	
				if(listMap.get("table_name")!=null){
					table_name=listMap.get("table_name").toString();
				}	
				//首字母大写的模块表名
				String up_table_name="";
				if(module_name.length()>0){
					String oneWord = table_name.substring(0,1);
					up_table_name=oneWord.toUpperCase()+table_name.substring(1,table_name.length());
				}
				sb.append("'"+module_name+"' AS "+module_type+"module_name,");
				sb.append("'"+is_catattr+"' AS "+module_type+"is_catattr,");
				sb.append("'"+up_table_name+"' AS "+module_type+"table_name,");
				sb.append("(SELECT COUNT(*) FROM "+table_name+" WHERE " +
						"DAY("+table_name+".in_date)=DAY(NOW()) AND MONTH("+table_name+".in_date)=MONTH(NOW()) AND YEAR("+table_name+".in_date)=YEAR(NOW())) AS "+module_type+"todaycount,");						
				//过滤地区
				String org_id="",org_area_sql="";
				if(cfg_area_filter.equals("0") && this.session_cust_type.equals(Constants.ADMIN_TYPE) 
						&& this.getSessionFieldValue(Constants.USER_TYPE).equals("4")){
					org_id = this.getSession().getAttribute(Constants.ORG_ID).toString();
					org_area_sql=" and INSTR("+table_name+".area_attr,"+org_id+") >0 " ;
				}
				if(i==(modlist.size()-1)){
					sb.append("(SELECT COUNT(*) FROM "+table_name+" WHERE info_state='0'"+org_area_sql+") AS  "+module_type+"countaudit");
				}else{
					sb.append("(SELECT COUNT(*) FROM "+table_name+" WHERE info_state='0'"+org_area_sql+") AS  "+module_type+"countaudit,");
				}
			}
			Map modMap=new HashMap();
			modMap.put("table_name", sb.toString());
			List list=this.sysuserService.getMsgCount(modMap);
			createList(list,modlist);
		}
		return null;
	}

	/**
	 * 方法描述：运营商后台重构未审核与今日新增条数LIST
	 * 
	 * @return
	 * @throws Exception
	 */
	private void createList(List list,List modlist){
		countlist=new ArrayList();
		if(list!=null&&list.size()>0){
			Map listMap = (HashMap)list.get(0);
			for(int i=0;i<modlist.size();i++){					
				Map modtypeMap=new HashMap();
				modtypeMap=(HashMap)modlist.get(i);
				String module_type="";
				if(modtypeMap.get("module_type")!=null){
					module_type=modtypeMap.get("module_type").toString();
				}
				if(module_type.equals("subject")){
					continue;
				}
				Map modcountMap=new HashMap();
				//获取模块名称
				if(!this.validateFactory.isRequired(listMap.get(module_type+"module_name").toString())){
					modcountMap.put("module_name", listMap.get(module_type+"module_name").toString());
				}
				//获取模块分类属性
				if(!this.validateFactory.isRequired(listMap.get(module_type+"is_catattr").toString())){
					modcountMap.put("is_catattr", listMap.get(module_type+"is_catattr").toString());
				}
				//获取模块表名
				if(!this.validateFactory.isRequired(listMap.get(module_type+"table_name").toString())){
					modcountMap.put("table_name", listMap.get(module_type+"table_name").toString());
				}
				//获取模块信息的总数
				if(!this.validateFactory.isRequired(listMap.get(module_type+"todaycount").toString())){
					modcountMap.put("todaycount", listMap.get(module_type+"todaycount").toString());
				}
				//获取模块未审核信息的条数
				if(!this.validateFactory.isRequired(listMap.get(module_type+"countaudit").toString())){
					modcountMap.put("countaudit", listMap.get(module_type+"countaudit").toString());
				}
				String is_admin = this.session_user_type;
				modcountMap.put("is_admin", is_admin);
				countlist.add(modcountMap);
			}
		}
	}
	
	
	/**
	 * 方法描述： 
	 * 
	 * @return
	 * @throws Exception
	 */
	public String execute() throws Exception {
		return INPUT;
	}

	//获取COKES的值
	public String readcokes() {
		HttpServletRequest request = getRequest();
		Cookie cokLoginName = this.getCookieByName(request, "loginName");
		String loginName = "";
		if (cokLoginName != null && cokLoginName.getValue() != null) {
			loginName = cokLoginName.getValue();
		}
		return loginName;
	}

	//保存COKES的值
	public void savecokes() {
		HttpServletResponse response = getResponse();
		int loginMaxAge = 30 * 24 * 60 * 60; //定义账户密码的生命周期，这里是一个月。单位为秒
		String username = this.sysuser.getUser_name();
		if (remusername != null && remusername.equals("on")) {
			addCookie(response, "loginName", username, loginMaxAge);
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

	/**
	 * 方法描述：管理员登陆
	 * 
	 * @return
	 * @throws Exception
	 */
	
	public String adminStr;
	
	
	public String getAdminStr() {
		return adminStr;
	}

	public void setAdminStr(String adminStr) {
		this.adminStr = adminStr;
	}

	@Action(value = "adminlogin", results = {@Result(name = "input", location = "/${adminStr}/index.jsp")})
	public String login() throws Exception {
		
		setAdminStr("admin");

		String sysrand = "";
		// 获取系统生成的验证码
		if (getSession().getAttribute("sysrand") != null) {
			sysrand = getSession().getAttribute("sysrand").toString();
		}
		
		if (ValidateUtil.isRequired(this.sysuser.getUser_name())) {
			this.addFieldError("adminloginMessage", "请输入用户名");
			return INPUT;
		}
		
		if (ValidateUtil.isRequired(this.sysuser.getPasswd())) {
			this.addFieldError("adminloginMessage", "请输入密码");
			return INPUT;
		}
		
		if (ValidateUtil.isRequired(sysrand)) {
			this.addFieldError("adminloginMessage", "请输入验证码");
			return INPUT;
		}
		
		String user_name = this.sysuser.getUser_name();
		Map pageMap = new HashMap();
		pageMap.put("user_name", user_name);

		// 通过用户名找出用户信息
		List userList = this.sysuserService.getList(pageMap);
		if (userList == null || userList.size() == 0) {
			this.addFieldError("adminloginMessage", "用户名不存在");
			return INPUT;
		} else {
			HashMap userMap = (HashMap) userList.get(0);
			String state = "";
			if (userMap.get("state") != null) {
				state = userMap.get("state").toString();
			}
			if (state.equals("") || state.equals("1")) {
				this.addFieldError("adminloginMessage", "该用户已禁用，不允许登陆");
				return INPUT;
			}
		}
		String passwd = this.sysuser.getPasswd();
		passwd = Md5.getMD5(passwd.getBytes());
		pageMap.put("passwd", passwd);

		// 通过用户名和密码找出用户信息
		userList = this.sysuserService.getList(pageMap);
		if (userList == null || userList.size() == 0) {
			this.addFieldError("adminloginMessage", "密码输入不正确");
			return INPUT;
		}
		
		if (!sysrand.equals(userrand)) {
			this.addFieldError("adminloginMessage", "验证码输入不正确");
			return INPUT;
		}
		//获取静态的已登录的用户的数据
		Map map=new UserLoginListener().loginMap;
		if(map.get(user_name)!=null){
			this.addFieldError("adminloginMessage", "该用户已在系统登录");
			return goUrl("INPUT");
		}
		// 获取该用户id
		String user_id = "", role_id = "", user_type = "", area_id = "";
		if (userList != null && userList.size() > 0) {
			HashMap userMap = (HashMap) userList.get(0);
			if (userMap.get("user_id") != null) {
				user_id = userMap.get("user_id").toString();
			}
			if (userMap.get("role_id") != null) {
				role_id = userMap.get("role_id").toString();
			}
			if (userMap.get("user_type") != null) {
				user_type = userMap.get("user_type").toString();
			}
			//获取系统用户表中的部门串,再获取所属的地区ID,用于分类信息的过滤
			if (userMap.get("org_id") != null && !userMap.get("org_id").equals("") ) {
				String org_id = userMap.get("org_id").toString();
				if (org_id.indexOf(",") > -1) {
					int len = org_id.lastIndexOf(",");
					org_id = org_id.substring((len + 1), org_id.length());					
				}
				Organize organize = this.organizeService.get(org_id);
				if (organize != null && !organize.getArea_attr().equals("")) {
					area_id = organize.getArea_attr();
					if (area_id.indexOf(",") > 0) {
						int area_len = area_id.lastIndexOf(",");
						area_id = area_id.substring((area_len + 1), area_id.length());
					}
				}
			}
		}
		// 获取运营商后台角色的菜单串
		String menu_right = "", oper_right = "";
		Role role = this.roleService.get(role_id);
		if (role != null) {
			if (role.getMenu_right() != null) {
				menu_right = role.getMenu_right();
			}
			if (role.getOper_right() != null) {
				oper_right = role.getOper_right();
			}
		}
		

		//登录成功后，为用户添加最后登录时间，最后登录IP，登录的次数
		Sysuser user = new Sysuser();
		String lastip = getRequest().getRemoteAddr();
		user.setUser_id(user_id);
		user.setLoginip(lastip);
		this.sysuserService.updatelaststate(user);
		//商城地区子站，管理登录后台的时候，获取该管理对应组织部门的地区ID
		loginSysUserArea(area_id,user_type);
		// 把值存放在session中
		getSession().setAttribute(Constants.CUST_ID, "0"); //运营商的默认cust_id为0，member表必须有cust_id为0与其对应
		getSession().setAttribute(Constants.CUST_TYPE, Constants.ADMIN_TYPE);
		getSession().setAttribute(Constants.USER_NAME, user_name);
		getSession().setAttribute(Constants.USER_ID, user_id);
		getSession().setAttribute(Constants.ORG_ID, area_id);
		getSession().setAttribute(Constants.ROLE_ID, role_id);
		
		//分类信息地区标识过滤
		getSession().setAttribute(Constants.AREA_MANAGER, area_id);

		//角色的菜单权限和操作权限
		getSession().setAttribute("menu_right", menu_right);
		getSession().setAttribute("oper_right", oper_right);

		// 运营商用户类型 3：管理员 4：子账户
		getSession().setAttribute(Constants.USER_TYPE, user_type);
		getSession().setAttribute(Constants.ROLE_ID, role_id);
		
		//获取运营商后台用户的管理权限
		
		//将用户名存入cokes中
		savecokes();

		this.addActionMessage("管理员登陆");
		getResponse().sendRedirect("/adminindex.action");
		return INPUT;
	}
	
	//商城地区子站，管理登录后台的时候，获取该管理对应组织部门的地区ID
	public void loginSysUserArea(String org_area_id, String sys_user_type)
	{
		 //是否开启商城地区过滤，0：是，1：否
		 if("0".equals(cfg_area_shop)){
			 //非系统管理员
			 if(!sys_user_type.equals("3")){
				 //组织部门地区ID不能为空值
				 if(org_area_id!=null&&!org_area_id.equals(""))
				 {
					 getSession().setAttribute(Constants.ORG_AREA_ID, org_area_id);
				 }
			 }
		 }
	}

	/**
	 * 方法描述：管理员登出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String logout() throws Exception {
		getSession().invalidate();
		return Constants.ADMIN_LOGIN;
	}

	/**
	 * 方法描述：修改系统管理员信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String userid = sysuser.getUser_id();
		if (userid == null || userid.equals("")) {
			return list();
		}
		//保存所属部门的隐藏域
		loadOrg();		
		String user_name = this.sysuser.getUser_name();
		Map pageMap = new HashMap();
		pageMap.put("user_name", user_name);

		// user_name_bak:用于保存原用户名
		if (!user_name_bak.equals(user_name)) {
			// 通过用户名找出用户信息
			List userList = this.sysuserService.getList(pageMap);
			if (userList != null && userList.size() > 0) {
				this.addFieldError("sysuser.user_name", "用户名已存在,请重新输入");
			}
		}

		String passwd = sysuser.getPasswd();
		if (passwd != null && !passwd.equals("")) {
			passwd = Md5.getMD5(passwd.getBytes());
		} else {
			passwd = null;
		}
		sysuser.setPasswd(passwd);
		//字段验证
		super.commonValidateField(sysuser);
		if(super.ifvalidatepass){
			return add();
		}
		this.sysuserService.update(sysuser);
		this.addActionMessage("修改系统管理员成功");
		return list();
	}

	/**
	 * 方法描述：删除系统管理员信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String userid = this.sysuser.getUser_id();
		userid = userid.replace(" ", "");
		this.sysuserService.delete(userid);
		this.addActionMessage("删除系统管理员成功");
		return list();
	}

	/**
	 * 方法描述：批量启用/禁用管理员
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateState() throws Exception {
		String userid = this.sysuser.getUser_id();
		String state = this.sysuser.getState();
		userid = userid.replace(" ", "");
		String useridStr[] = userid.split(",");
		List userList = new ArrayList();
		if (useridStr.length > 0) {
			for (int i = 0; i < useridStr.length; i++) {
				Map userMap = new HashMap();
				userMap.put("state", state);
				userMap.put("user_id", useridStr[i]);
				userList.add(userMap);
			}
		}
		this.sysuserService.updateSysuserState(userList);
		String tip = "";
		if (state.equals("0")) {
			tip = "启用管理员成功";
		} else if (state.equals("1")) {
			tip = "禁用管理员成功";
		}
		this.addActionMessage(tip);
		return list();
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {

		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		if (user_name_s != null && !user_name_s.equals("")) {
			pageMap.put("user_name", user_name_s);
		}
		if (nike_name_s != null && !nike_name_s.equals("")) {
			pageMap.put("nike_name", nike_name_s);
		}
		if (role_id_s != null && !role_id_s.equals("")) {
			pageMap.put("role_id", role_id_s);
		}
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.sysuserService.getCount(pageMap);

		//分页插件
		pageMap = super.pageTool(count, pageMap);

		// 找出信息列表，放入list
		userList = this.sysuserService.getList(pageMap);
		HashMap rolesMap = new HashMap();
		if (!this.session_cust_id.equals("")) {
			rolesMap.put("cust_id", this.session_cust_id);
		}
		roleList = this.roleService.getList(rolesMap);

		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出管理员信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		Map map = new HashMap();
		map.put("cust_id", this.session_cust_id);
		roleList = this.roleService.getList(map);
		org_hidden_value = Constants.UP_ORG_ID+","+sysuser.getOrg_id();
		return goUrl(VIEW);
	}

	/**
	 * @return the userrand
	 */
	public String getUserrand() {
		return userrand;
	}

	/**
	 * @param userrand
	 *            the userrand to set
	 */
	public void setUserrand(String userrand) {
		this.userrand = userrand;
	}

	/**
	 * @return the user_name_s
	 */
	public String getUser_name_s() {
		return user_name_s;
	}

	/**
	 * @param user_name_s
	 *            the user_name_s to set
	 */
	public void setUser_name_s(String user_name_s) {
		this.user_name_s = user_name_s;
	}

	/**
	 * @return the nike_name_s
	 */
	public String getNike_name_s() {
		return nike_name_s;
	}

	/**
	 * @param nike_name_s
	 *            the nike_name_s to set
	 */
	public void setNike_name_s(String nike_name_s) {
		this.nike_name_s = nike_name_s;
	}

	/**
	 * @return the roleList
	 */
	public List getRoleList() {
		return roleList;
	}

	/**
	 * @param roleList
	 *            the roleList to set
	 */
	public void setRoleList(List roleList) {
		this.roleList = roleList;
	}

	/**
	 * @return the sysuser
	 */
	public Sysuser getSysuser() {
		return sysuser;
	}

	/**
	 * @param sysuser
	 *            the sysuser to set
	 */
	public void setSysuser(Sysuser sysuser) {
		this.sysuser = sysuser;
	}

	/**
	 * @return the member_num
	 */
	public int getMember_num() {
		return member_num;
	}

	/**
	 * @param member_num
	 *            the member_num to set
	 */
	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}

	/**
	 * @return the job_num
	 */
	public int getJob_num() {
		return job_num;
	}

	/**
	 * @param job_num
	 *            the job_num to set
	 */
	public void setJob_num(int job_num) {
		this.job_num = job_num;
	}

	/**
	 * @return the resume_num
	 */
	public int getResume_num() {
		return resume_num;
	}

	/**
	 * @param resume_num
	 *            the resume_num to set
	 */
	public void setResume_num(int resume_num) {
		this.resume_num = resume_num;
	}

	/**
	 * @return the exhibition_num
	 */
	public int getExhibition_num() {
		return exhibition_num;
	}

	/**
	 * @param exhibition_num
	 *            the exhibition_num to set
	 */
	public void setExhibition_num(int exhibition_num) {
		this.exhibition_num = exhibition_num;
	}

	/**
	 * @return the buy_num
	 */
	public int getBuy_num() {
		return buy_num;
	}

	/**
	 * @param buy_num
	 *            the buy_num to set
	 */
	public void setBuy_num(int buy_num) {
		this.buy_num = buy_num;
	}

	/**
	 * @return the gallery_num
	 */
	public int getGallery_num() {
		return gallery_num;
	}

	/**
	 * @param gallery_num
	 *            the gallery_num to set
	 */
	public void setGallery_num(int gallery_num) {
		this.gallery_num = gallery_num;
	}

	/**
	 * @return the product_num
	 */
	public int getProduct_num() {
		return product_num;
	}

	/**
	 * @param product_num
	 *            the product_num to set
	 */
	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}

	/**
	 * @return the download_num
	 */
	public int getDownload_num() {
		return download_num;
	}

	/**
	 * @param download_num
	 *            the download_num to set
	 */
	public void setDownload_num(int download_num) {
		this.download_num = download_num;
	}

	/**
	 * @return the news_num
	 */
	public int getNews_num() {
		return news_num;
	}

	/**
	 * @param news_num
	 *            the news_num to set
	 */
	public void setNews_num(int news_num) {
		this.news_num = news_num;
	}

	/**
	 * @return the video_num
	 */
	public int getVideo_num() {
		return video_num;
	}

	/**
	 * @param video_num
	 *            the video_num to set
	 */
	public void setVideo_num(int video_num) {
		this.video_num = video_num;
	}

	/**
	 * @return the supply_num
	 */
	public int getSupply_num() {
		return supply_num;
	}

	/**
	 * @param supply_num
	 *            the supply_num to set
	 */
	public void setSupply_num(int supply_num) {
		this.supply_num = supply_num;
	}

	/**
	 * @return the ask_num
	 */
	public int getAsk_num() {
		return ask_num;
	}

	/**
	 * @param ask_num
	 *            the ask_num to set
	 */
	public void setAsk_num(int ask_num) {
		this.ask_num = ask_num;
	}

	/**
	 * @return the database_install_date
	 */
	public String getInstall_date() {
		return install_date;
	}

	/**
	 * @param database_install_date
	 *            the database_install_date to set
	 */
	public void setInstall_date(String install_date) {
		this.install_date = install_date;
	}

	/**
	 * @return the database_product_version
	 */
	public String getDatabase_product_version() {
		return database_product_version;
	}

	/**
	 * @param database_product_version
	 *            the database_product_version to set
	 */
	public void setDatabase_product_version(String database_product_version) {
		this.database_product_version = database_product_version;
	}

	/**
	 * @return the database_name
	 */
	public String getDatabase_name() {
		return database_name;
	}

	/**
	 * @param database_name
	 *            the database_name to set
	 */
	public void setDatabase_name(String database_name) {
		this.database_name = database_name;
	}

	/**
	 * @return the server_datetime
	 */
	public String getServer_datetime() {
		return server_datetime;
	}

	/**
	 * @param server_datetime
	 *            the server_datetime to set
	 */
	public void setServer_datetime(String server_datetime) {
		this.server_datetime = server_datetime;
	}

	/**
	 * @return the server_info
	 */
	public String getServer_info() {
		return server_info;
	}

	/**
	 * @param server_info
	 *            the server_info to set
	 */
	public void setServer_info(String server_info) {
		this.server_info = server_info;
	}

	/**
	 * @return the server_ip
	 */
	public String getServer_ip() {
		return server_ip;
	}

	/**
	 * @param server_ip
	 *            the server_ip to set
	 */
	public void setServer_ip(String server_ip) {
		this.server_ip = server_ip;
	}

	/**
	 * @return the oS_name
	 */
	public String getOS_name() {
		return OS_name;
	}

	/**
	 * @param os_name
	 *            the oS_name to set
	 */
	public void setOS_name(String os_name) {
		OS_name = os_name;
	}

	/**
	 * @return the oS_arch
	 */
	public String getOS_arch() {
		return OS_arch;
	}

	/**
	 * @param os_arch
	 *            the oS_arch to set
	 */
	public void setOS_arch(String os_arch) {
		OS_arch = os_arch;
	}

	public String getUser_name_bak() {
		return user_name_bak;
	}

	public void setUser_name_bak(String user_name_bak) {
		this.user_name_bak = user_name_bak;
	}

	/**
	 * @return the todayList
	 */
	public List getTodayList() {
		return todayList;
	}

	/**
	 * @param todayList
	 *            the todayList to set
	 */
	public void setTodayList(List todayList) {
		this.todayList = todayList;
	}

	/**
	 * @return the loglist
	 */
	public List getLoglist() {
		return loglist;
	}

	/**
	 * @param loglist
	 *            the loglist to set
	 */
	public void setLoglist(List loglist) {
		this.loglist = loglist;
	}

	public String getRemusername() {
		return remusername;
	}

	public void setRemusername(String remusername) {
		this.remusername = remusername;
	}

	/**
	 * @return the opername
	 */
	public String getOpername() {
		return opername;
	}

	/**
	 * @param opername the opername to set
	 */
	public void setOpername(String opername) {
		this.opername = opername;
	}

	/**
	 * @return the lastIp
	 */
	public String getLastIp() {
		return lastIp;
	}

	/**
	 * @param lastIp the lastIp to set
	 */
	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}

	/**
	 * @return the lastlogintime
	 */
	public String getLastlogintime() {
		return lastlogintime;
	}

	/**
	 * @param lastlogintime the lastlogintime to set
	 */
	public void setLastlogintime(String lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	/**
	 * @return the logintimes
	 */
	public String getLogintimes() {
		return logintimes;
	}

	/**
	 * @param logintimes the logintimes to set
	 */
	public void setLogintimes(String logintimes) {
		this.logintimes = logintimes;
	}

	public String getRole_id_s() {
		return role_id_s;
	}

	public void setRole_id_s(String role_id_s) {
		this.role_id_s = role_id_s;
	}

	public String getOrg_hidden_value() {
		return org_hidden_value;
	}

	public void setOrg_hidden_value(String org_hidden_value) {
		this.org_hidden_value = org_hidden_value;
	}

	public void prepare() throws Exception { super.saveRequestParameter();
		if (sysuser == null) {
			sysuser = new Sysuser();
		}
		String id = sysuser.getUser_id();
		if (!ValidateUtil.isDigital(id)) {
			sysuser = this.sysuserService.get(id);
		}
	}

}
