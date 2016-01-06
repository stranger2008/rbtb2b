/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: BaseAction.java 
 */

package com.rbt.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionSupport;
import com.rbt.common.Constants;
import com.rbt.common.util.FieldValidateFromXmlUtil;
import com.rbt.common.util.PageUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.AreaFuc;
import com.rbt.function.AuditModelFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Audithistory;
import com.rbt.model.Interhistory;
import com.rbt.model.Logs;
import com.rbt.model.Memberinter;
import com.rbt.model.Memberlevel;
import com.rbt.service.IAudithistoryService;
import com.rbt.service.IInterhistoryService;
import com.rbt.service.IInterruleService;
import com.rbt.service.ILogsService;
import com.rbt.service.IMemberinterService;
import com.rbt.service.IMemberlevelService;
import com.rbt.service.INewsService;
import com.rbt.common.util.RandomStrUtil;

/**
 * @function 功能 所有struts2框架action的父类
 * @author 创建人 李良林
 * @date 创建日期 Jun 13, 2011
 */
@Controller
public class BaseAction extends ActionSupport {

	/**
	 * 序列化      
	 */
	private static final long serialVersionUID = -8089763167338830169L;

	public final String VIEW = Constants.VIEW;

	public final String ADD = Constants.ADD;

	public final String AUDIT = Constants.AUDIT;
	
	public final String INDEXLIST = Constants.INDEX;

	public final String AUDITLIST = Constants.AUDITLIST;
	
	//查看商城是否关闭 0：开启 1：关闭
	public String web_openmall=SysconfigFuc.getSysValue("cfg_openmall");	
	public String cfg_topareaid = SysconfigFuc.getSysValue("cfg_topareaid");
	public String cfg_topcatid = SysconfigFuc.getSysValue("cfg_topcatid");
	public String cfg_area_filter = SysconfigFuc.getSysValue("cfg_area_filter");
	// 系统默认无图图片
	public String cfg_nopic = SysconfigFuc.getSysValue("cfg_nopic");
	public String cfg_basehost = SysconfigFuc.getSysValue("cfg_basehost");
	// 网站名称
	public String cfg_webname = SysconfigFuc.getSysValue("cfg_webname");
	// 注册条款
	public String cfg_serviceterms = SysconfigFuc.getSysValue("cfg_serviceterms");
	
	public String b2ccopyright= SysconfigFuc.getSysValue("cfg_b2ccopyright");
	//是否开启未审核会员登录  0:是  1:否
	public String cfg_mb_islogin = SysconfigFuc.getSysValue("cfg_mb_islogin");
	
	// 获取系统变量中积分规则是否开启开关，1关闭，0开启
	private String scoreSwitch = SysconfigFuc.getSysValue("cfg_sc_switch");
	// 获取每天的最高上限积分
	private String scoreToday = SysconfigFuc.getSysValue("cfg_sc_dayup");
    // 获取系统变量是否开启商城地区过滤 0开启 1 关闭
	public String cfg_area_shop = SysconfigFuc.getSysValue("cfg_area_shop");
	//是否开启团购地区过滤 0标识开启地区过滤  1则关闭
	public String cfg_area_group = SysconfigFuc.getSysValue("cfg_area_group");
	//是否开启ip过滤 0开启过滤  1则关闭
	public String cfg_area_ip = SysconfigFuc.getSysValue("cfg_area_ip");
	//获取系统变量首页地址
	public String cfg_index = SysconfigFuc.getSysValue("cfg_index");
	//获取是否开启审核流程开关
	public String cfg_auditmodel=SysconfigFuc.getSysValue("cfg_auditmodel");
	//是否开启Discuz论坛整合
	public String cfg_discuz_open=SysconfigFuc.getSysValue("cfg_discuz_open");
	//判断是否有权限点击桌面未审核记录
	public String is_admin="0";
	
	public ValidateUtil validateFactory;
	//判断是否是两个页面的跳转
	public String two_pages_link;

	public String ajaxRequestRandom;	
	public String token_value;
	public String get_token_value;	
	private String tokenErrorPage = "/include/tokenError.jsp";
	public  String mall_type =Constants.MALL_TYPE_B2B;
	//判断修改页面提交，未通过过滤时，清除其他内容
	public String unique="";
	//判断是否添加后影响数量改变
	public boolean is_crotorl=false;

	
	/*
	 * 系统业务层接口
	 */
	@Autowired
	public ILogsService logsService;
	@Autowired
	public IInterhistoryService interhistoryService;
	@Autowired
	public IInterruleService interruleService;
	@Autowired
	public IMemberinterService memberinterService;
	@Autowired
	public IAudithistoryService audithistoryService;
	@Autowired
	public INewsService newsService;
	@Autowired
	public IMemberlevelService memberlevelService;

	/*
	 * 当前页码
	 */
	public int pages_s = 1;
	/*
	 * 一页展示的信息数
	 */
	public int pageSize_s = 20;
	/*
	 * 程序生成的分页字符串
	 */
	public String pageBar;

	/*
	 * action名中是否包含该参数
	 */
	private String mem_is_login = "会员登陆";
	private String delete_mothod = "delete";
	private String login_mothod = "login";
	private String audit_mothod = "audit";
	public int int_scoreToday = 0;
	private int numToday = 0;
	public String oldinfotitle;// 信息标题,用于控制信息标题重复出现
	/*
	 * 定义分类串
	 */
	public String cat_attr;
	/*
	 * 定义地区串
	 */
	public String area_attr;
    public String mcat_attr;
	public String hidden_area_value;
	public String hidden_cat_value;
	public String hidden_membercat_value;
	public String crotrolTip;

	private HttpServletRequest req = getRequest();
	// 获取执行方法的action名
	private String actionName = req.getRequestURI();

	private String info_id, info_state,release_cust_id, module_type;
	String audit_state = "0";
	// 模板的样式，可在系统后台设置
	public String templateStyle = SysconfigFuc.getSysValue("cfg_templatestyle");
	// 模板生成文件路径，可在系统后台设置
	public String templateRoute = SysconfigFuc.getSysValue("cfg_templateroute");
	// 模板文件路径，可在系统后台设置
	public String templateFiles = SysconfigFuc.getSysValue("cfg_templatefolder");
	//商城顶级地区ID
	public String cfg_mall_topareaid=SysconfigFuc.getSysValue("cfg_mall_topareaid");
	// 模板文件引用存放路径
	public String templatePortalFolder = Constants.TEMPLATE_PORTAL_FOLDER;
	//输入验证是否通过true:通过，false:不通过
    public boolean ifvalidatepass=false;
    
    //审核人信息列表
    public List auditMemberList;
    //审核信息历史表
    public List auditHistoryList;
    //用户是否有审核权限 0:表示没有权限操作，1：表示有权限操作
    public String if_opt_audit="0";
    
    
    public String session_cust_id = this.getSessionFieldValue(Constants.CUST_ID);
    public String session_user_id = this.getSessionFieldValue(Constants.USER_ID);
    public String session_user_name = this.getSessionFieldValue(Constants.USER_NAME);
    public String session_cust_type = this.getSessionFieldValue(Constants.CUST_TYPE);
    public String session_mem_type = this.getSessionFieldValue(Constants.MEM_TYPE);
    public String session_level_code = this.getSessionFieldValue(Constants.LEVEL_CODE);
    public String session_org_area_id = this.getSessionFieldValue(Constants.ORG_AREA_ID);
    public String session_user_type = this.getSessionFieldValue(Constants.USER_TYPE);
    
    
   

	/**
	 * @Method Description : 在进入更新,审核页面时进行回选
	 * @author : 林俊钦
	 * @date : Dec 6, 2011 1:45:46 PM
	 */
	public void backArea(String areaString) {
		if (areaString != null) {
			hidden_area_value = cfg_topareaid + "," + areaString.replace(" ", "");
		}
	}
	public void setPlatType(){
		mall_type =Constants.MALL_TYPE_B2C;
	}
	/**
	 * @Method Description :在进入更新,审核页面时进行回选
	 * @author : 林俊钦
	 * @date : Dec 6, 2011 2:26:50 PM
	 */
	public void backCategory(String catString) {
		if (catString != null) {
			hidden_cat_value = cfg_topcatid + "," + catString.replace(" ", "");
		}
	}
    
    
	/**
	 * @Method Description : 加载分类级别
	 * @author : 林俊钦
	 * @date : Dec 6, 2011 1:30:16 PM
	 */
	@SuppressWarnings("static-access")
	public void loadCategory() {
		if (!ValidateUtil.isRequired(cat_attr)) {
			// 保存分类的串，用于回选分类
			String areaString = cat_attr.replace(" ", "").replace(",0", "");
			hidden_cat_value = cfg_topcatid + "," + areaString;
			cat_attr = cat_attr.replace(" ", "");
		}
	}
	
	
	/**
	 * @Method Description : 加载地区级别
	 * @author : 林俊钦
	 * @date : Dec 6, 2011 1:00:31 PM
	 */
	@SuppressWarnings("static-access")
	public void loadArea() {
		if (!ValidateUtil.isRequired(area_attr)) {
			// 保存地区的串，用于回选地区
			String areaString = area_attr.replace(" ", "").replace(",0", "");
			hidden_area_value = cfg_topareaid + "," + areaString;
			area_attr = area_attr.replace(" ", "");
		}
	}
	
	/**
	 * @Method Description : 在进入更新,审核页面时进行回选自定义分类
	 * @author : 胡惜坤
	 * @date : Dec 6, 2011 1:45:46 PM
	 */
	public void backMemberCat(String catString) {
		if (catString != null) {
			hidden_membercat_value ="1111111111," + catString.replace(" ", "");
		}
	}
	/**
	 * @Method Description : 加载自定义分类
	 * @author : 胡惜坤
	 * @date : Dec 6, 2011 1:00:31 PM
	 */
	@SuppressWarnings("static-access")
	public void loadMemberCat() {
		if (!ValidateUtil.isRequired(mcat_attr)) {
			// 保存地区的串，用于回选地区
			String mcat_attrString = mcat_attr.replace(" ", "").replace(",0", "");
			hidden_membercat_value = hidden_membercat_value + "," + mcat_attrString;
			mcat_attr = mcat_attr.replace(" ", "");
		}
	}
	
	
	

	/**
	 * @Method Description : 加载数据库表与主键的关系
	 * @author : 林俊钦
	 * @date : Nov 16, 2011 9:25:14 AM
	 */
	@SuppressWarnings("unchecked")
	private static Map keyMap;

	@SuppressWarnings("unchecked")
	public void initKeyMap() {
		keyMap = new HashMap();
		keyMap.put("supply", "supply_id");
		keyMap.put("buy", "buy_id");
		keyMap.put("news", "news_id");
		keyMap.put("product", "product_id");
		keyMap.put("job", "job_id");
		keyMap.put("resume", "resume_id");
		keyMap.put("showinfo", "exh_id");
		keyMap.put("ask", "ask_id");
		keyMap.put("gallery", "gal_id");
		keyMap.put("video", "video_id");
		keyMap.put("download", "down_id");
		keyMap.put("brand", "brand_id");
		keyMap.put("member", "cust_id");
		keyMap.put("classified", "info_id");
		keyMap.put("goods", "goods_id");
	}
	
	/**
	 * @Method Description : 初始化加载信息
	 * @author : 林俊钦
	 * @date : Nov 15, 2011 1:21:11 PM
	 */
	public void initField() {
		synchronized (this) {
			this.session_cust_id = this.getSessionFieldValue(Constants.CUST_ID);
			this.session_user_id = this.getSessionFieldValue(Constants.USER_ID);
			this.session_user_name = this.getSessionFieldValue(Constants.USER_NAME);
		}
	}

	/**
	 * @Method Description :提示操作信息
	 * @author : 林俊钦
	 * @date : Nov 15, 2011 10:43:36 AM
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void addActionMessage(String message) {
		super.addActionMessage(message);
		initField();
		if (keyMap == null) {
			// 加载数据库表与主键的关系
			initKeyMap();
		}
		// 记录审核历史
		insertAudit();
		// 积分规则控制
		interRuleSet();
		// 记录日志
		insertLogs(message);
	}

	/**
	 * @Method Description :当审核时插入数据到审核记录历史表中
	 * @author : 林俊钦
	 * @date : Nov 15, 2011 10:44:35 AM
	 */
	public void insertAudit() {
		int startLen = this.actionName.indexOf("_");
		int endLen = this.actionName.lastIndexOf("_");
		if (startLen < 0 || endLen < 0 || startLen + 1 > endLen) {
			return;
		}
		// 当不是审核操作时，不执行方法返回
		if (this.actionName.indexOf("audit") == -1) {
			return;
		}
		
		// 接取表名并转换成小写
		module_type = this.actionName.substring(startLen + 1, endLen).toLowerCase();
		if(keyMap.get(module_type) == null){
			return ;
		}
		String pkId  = keyMap.get(module_type).toString();
		
		// 获取主键值，状态和理由
		info_id = this.getRequestFieldValue(module_type + "." + pkId);
		info_state = this.getRequestFieldValue(module_type + ".info_state");
		String no_reason = this.getRequestFieldValue(module_type + ".no_reason");
		// 获取发布人cust_id
		release_cust_id = this.getRequestFieldValue(module_type + ".cust_id");
		if (info_state.equals("1")) no_reason = "";
		
		// 查询出在插入审核记录之前的表中的记录
		// 找出通过审核的记录
		Map auditMap = new HashMap();
		auditMap.put("info_id", info_id);
		auditMap.put("module_type", module_type);
		auditMap.put("info_state", "1");
		auditMap.put("cust_id", release_cust_id);
		List list = this.audithistoryService.getList(auditMap);
		if (list != null && list.size() == 0) {
			//管理员第一次审核通过一条信息，加积分
			//对于一条已经审核通过的信息，经过会员修改变成未审核状态，管理员在此审核此条信息，不加积分
			if (info_state.equals("1")) {
				audit_state = "1";
			}
		}
		
		// 重构审核历史对象
		Audithistory audit = new Audithistory(info_id,module_type,this.session_user_id,this.session_user_name,info_state,no_reason,release_cust_id);
		this.audithistoryService.insert(audit);
	}

	/**
	 * @Method Description :积分规则设置的方法是否符合设置的规则
	 * @author : 林俊钦
	 * @date : Nov 11, 2011 2:01:09 PM
	 */
	@SuppressWarnings("unchecked")
	public void interRuleSet() {
		// 转换最高上限积分为整型
		int_scoreToday = Integer.parseInt(scoreToday);
		// 获取当天会员获取的积分数
		if (release_cust_id !=null && !release_cust_id.equals("")) {
			Map map = new HashMap();
			map.put("cust_id", release_cust_id);
			numToday = this.interhistoryService.getInterhistorySumScore(map);
		}
		// 计算当前会员当天所获得的积分数
		if (scoreSwitch.equals("0") && numToday <= int_scoreToday) {
			interRule();
		}
	}

	/**
	 * @Method Description :会员积分规则处理方法
	 * @author : 林俊钦
	 * @date : Nov 11, 2011 12:51:50 PM
	 */
	@SuppressWarnings("unchecked")
	public void interRule() {
		// 查找出相应的积分规则列表
		Map ruleMap = new HashMap();
		String rule_code = this.actionName;
		int s_len=rule_code.indexOf("/");
		int e_len=rule_code.indexOf(".");
		if(s_len>-1 && e_len > s_len ){
			rule_code=rule_code.substring(s_len+1,e_len);
		}		
		ruleMap.put("rule_code", rule_code);
		List ruleList = this.interruleService.getList(ruleMap);// 用于取积分数
		// 定义批量删除时默认ID个数为1
		int valLen = 1;
		// 当启动积分规则时且规则表中也存在该规则，则按规则处理积分
		if (ruleList != null && ruleList.size() > 0) {
			// 判断是否是删除的操作
			if (this.actionName.indexOf(delete_mothod) > -1) {
				deleteByCust(valLen, ruleList);
			} else if (this.actionName.indexOf(login_mothod) > -1) {// 判断是否是登录方法
				if (interLoginRule(this.session_cust_id)) {
					// 如果会员今天未登录，则加积分
					interhistory(ruleList, valLen, this.session_cust_id, this.session_user_id, int_scoreToday);
				}
			} else if (this.actionName.indexOf(audit_mothod) > -1) {
				if (interAuditRule()) {
					// 插入积分历史表数据，审核操作
					interhistory(ruleList, valLen, release_cust_id, this.session_user_id, int_scoreToday);
				}
			}
		}
	}

	/**
	 * @Method Description : 判断是会员自己删除还是运营商删除
	 * @author : 林俊钦
	 * @date : Nov 16, 2011 1:30:47 PM
	 */
	@SuppressWarnings("unchecked")
	public void deleteByCust(int valLen, List list) {
		// 获取删除的序号的ID
		String[] Id_val = null;
		Enumeration enume = this.req.getParameterNames();
		while (enume.hasMoreElements()) {
			String fieldName = enume.nextElement().toString();
			String fieldValue = "";
			if (this.req.getParameter(fieldName) != null) {
				fieldValue = this.req.getParameter(fieldName);
			}
			if (fieldValue.indexOf(",") > -1) {
				Id_val = fieldValue.split(",");
			}
		}
		Map auditMap = new HashMap();
		auditMap.put("module_type", module_type);
		auditMap.put("info_state", 1);// 找出通过审核的记录
		auditMap.put("cust_id", this.session_cust_id);
		// 当串中不存在，号时，则valLen的长度等于1
		if (Id_val != null) {
			for (int i = 0; i < Id_val.length; i++) {
				// 删除前找出审核记录之前的表中的是否存在审核过的记录
				auditMap.put("info_id", Id_val[i]);
				List auditList = this.audithistoryService.getList(auditMap);
				if (auditList != null && auditList.size() > 0) {// 当存在已加分的记录时则减掉该记录的积分
					// 插入积分历史表数据
					interhistory(list, valLen, this.session_cust_id, this.session_user_id, int_scoreToday);
				}
			}
		} else {
			// 删除前找出审核记录之前的表中的是否存在审核过的记录
			auditMap.put("info_id", info_id);
			List auditList = this.audithistoryService.getList(auditMap);
			if (auditList != null && auditList.size() > 0) {// 当存在已加分的记录时则减掉该记录的积分
				// 插入积分历史表数据
				interhistory(list, valLen, this.session_cust_id, this.session_user_id, int_scoreToday);
			}
		}
	}

	/**
	 * @Method Description : 审核积分的页面,找出当前的记录并排好序后获取第一条记录，并处理数据
	 * @author : 林俊钦
	 * @date : Nov 15, 2011 1:32:31 PM
	 */
	@SuppressWarnings("unchecked")
	public boolean interAuditRule() {
		if (audit_state.equals("1")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @Method Description : 会员登录积分获取
	 * @author : 林俊钦
	 * @date : Nov 11, 2011 12:53:31 PM
	 */
	@SuppressWarnings("unchecked")
	public boolean interLoginRule(String cust_id) {
		//当用户如果为新用户登录时需要插入会员积分历史记录中
		Map loginMap=new HashMap();
		loginMap.put("cust_id", cust_id);
		List loginList=this.interhistoryService.getList(loginMap);
		if(loginList!=null && loginList.size()==0){
			return true;
		}else{
			//在积分历史记录中已有记录时
			Map map = new HashMap();
			map.put("cust_id", cust_id);
			map.put("todays", "todays");
			map.put("reason", mem_is_login);
			List list = this.interhistoryService.getList(map);
			if (list != null && list.size() == 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * @Method Description : 对处理积分的变动插入积分异动表
	 * @author : 林俊钦
	 * @date : Nov 11, 2011 12:56:58 PM
	 */
	@SuppressWarnings("unchecked")
	public void interhistory(List ruleList, int valLen, String cust_id, String user_id, int int_scoreToday) {
		String rule_name = "";
		int internum = 0;
		if (ruleList != null && ruleList.size() > 0) {
			Map listMap = new HashMap();
			listMap = (HashMap) ruleList.get(0);
			rule_name = listMap.get("rule_name").toString();
			// 转换成整型
			internum = Integer.parseInt(listMap.get("internum").toString());
		} else {// 当不是从规则表中获取的名称
			rule_name = "评价获得积分";
			internum = valLen;// 对评价获得的积分
			valLen = 1;
		}
		// 查出当前公司的总积分数//如果该公司还未在积分表中有积分的话，则会返回null,则要实例化一个对象
		Memberinter inter = this.memberinterService.get(cust_id);
		if (inter == null) {
			inter = new Memberinter();
		}
		int score = 0;
		if (inter != null && inter.getFund_num() != null) {
			score = Integer.parseInt(inter.getFund_num());
		}
		// 对于当天得到的总积分数 =今天已获得的积分+操作规则马上获取的积分
		int todayInterScore = 0;
		// 对得到的积分数与操作所获得或失去的积分数加减,得到现有的总积分
		int interScore = score + valLen * internum;
		String interStr = String.valueOf(interScore);
		// 对存入积分异动表前数据的一些处理
		Interhistory history = new Interhistory();
		history.setCust_id(cust_id);
		history.setReason(rule_name);
		history.setUser_id(user_id);
		history.setThisinter(interStr);
		//先暂写B2B平台类型
		history.setPlat_type(mall_type);
		// 批量删除后的积分数是多少
		int costNum = internum * valLen;
		// 判断当前操作的所需的积分是正数还是负数
		if (internum > 0) {
			history.setInter_in(String.valueOf(costNum));
			history.setInter_out("0");
			// 对于当天得到的总积分数 =今天已获得的积分+马上获取的积分
			todayInterScore = numToday + internum;
		} else {
			history.setInter_out(String.valueOf(costNum));
			history.setInter_in("0");
		}
		if (todayInterScore <= int_scoreToday) {
			// 对处理后的得到剩下的积分存入公司剩下的积分表中
			inter.setFund_num(interStr);
			inter.setCust_id(cust_id);
			
			if (inter != null && inter.getFund_num() != null) {
				this.memberinterService.update(inter);
			} else {
				this.memberinterService.insert(inter);
			}
			// 存入积分异动表中
			this.interhistoryService.insert(history);
		}
	}

	/**
	 * 方法描述：记录操作日志
	 * 
	 * @date 2011-11-1
	 * @author 李良林
	 */
	public void insertLogs(String message) {
		Logs logs = new Logs();
		logs.setIp(getRequest().getRemoteAddr());
		logs.setCust_id(this.session_cust_id);
		logs.setUser_id(this.session_user_id);
		logs.setUser_name(this.session_user_name);
		logs.setContent(message);
		this.logsService.insert(logs);
	}
	
	//获取session中的值公共方法
	public String getSessionFieldValue(String fieldName){
		HttpSession session = getSession();
		String fieldValue = "";
		if (session.getAttribute(fieldName) != null) {
			fieldValue = session.getAttribute(fieldName).toString();
		}
		return fieldValue;
	}
	
	//获取request中的值的公共方法
	public String getRequestFieldValue(String fieldName){
		return getRequestFieldValue(fieldName,true);
	}
	public String getRequestFieldValue(String fieldName,boolean b){
		HttpServletRequest request = this.getRequest();
		String fieldValue = "";
		if(b){
			if (request.getParameter(fieldName) != null) {
				fieldValue = request.getParameter(fieldName);
			}
		}else{
			if (request.getAttribute(fieldName) != null) {
				fieldValue = request.getAttribute(fieldName).toString();
			}
		}
		return fieldValue;
	}
	
	
	public String listSearchHiddenField;

	/**
	 * 方法描述：分页统一处理
	 * 
	 * @date 2011-11-3
	 * @author 李良林
	 * @param count
	 *            信息数量
	 * @param pageMap
	 *            信息参数
	 */
	@SuppressWarnings("unchecked")
	public Map pageTool(int count, Map pageMap) {
		PageUtil page = new PageUtil();
		//判断是两个页面间的跳转则给page页面赋值
		if(two_pages_link!=null&&two_pages_link.equals("no")){
			page.setCurPage(1);
			page.setPageSize(20);
		}else{
			page.setCurPage(pages_s);
			page.setPageSize(pageSize_s);
		}
		
		page.setTotalRow(count);
		pageBar = page.getToolsMenu();
		pageMap.put("start", page.getStart());
		pageMap.put("limit", page.getEnd());
		return pageMap;
	}
	
	//是否开启地区过滤管理功能
	//控制运营商后台地区过滤
	//user_type是4，代表为运营商后台子账户
	@SuppressWarnings("unchecked")
	public Map areafilter(Map pageMap){
		if(cfg_area_filter.equals("0") && this.session_cust_type.equals(Constants.ADMIN_TYPE) 
				&& this.getSessionFieldValue(Constants.USER_TYPE).equals("4")){
			String area_filter = this.getSessionFieldValue(Constants.AREA_MANAGER);
			if(!area_filter.equals("")&&!area_filter.equals("0")){
				pageMap.put("area_filter", area_filter);
			}
		}
		return pageMap;
	}
	
	//商城地区过滤
	public Map shopareafilter(Map pageMap){
		//地区过滤
		if("0".equals(cfg_area_shop)){
			//获取系统用户所对于的组织机构的地区ID
			if(session_org_area_id!=null&&!session_org_area_id.equals(""))
			{
				pageMap.put("area_attr", session_org_area_id);
			}
		}
		return pageMap;
	}
	
	
	private void tokenControl() throws IOException{
		//重复提交控制
		HttpSession session = getSession();
		if(token_value != null){
			String session_token = "";
			if(session.getAttribute("token") != null){
				session_token = session.getAttribute("token").toString();
			}
			//当token_value为空时，用于找回密码和找回用户名
			if(token_value.equals("")||!token_value.equals(session_token)){
				getResponse().sendRedirect(tokenErrorPage);
			}else{
				this.token_value = RandomStrUtil.getNumberRand();
				session.setAttribute("token", token_value);
			}
		}else{
			token_value = RandomStrUtil.getNumberRand();
			session.setAttribute("token", token_value);
		}
		get_token_value=token_value;
	}
	
	/**
	 * 方法描述：保存列表搜索的参数域和值，以至于修改、删除、新增动作后返回列表页仍然是之前搜索时所留下的值
	 * 这里对搜索的字段名有个控制，字段名必须是"_s"结尾才会被保存
	 * 
	 * @date 2011-12-16
	 * @author 李良林
	 */
	public void saveRequestParameter() throws IOException{
		
	    if(this.req.getParameter("top_menu")!=null&&!this.req.getParameter("top_menu").equals("")){
	    	getSession().setAttribute("first_menu_id",this.req.getParameter("top_menu").toString());
	    }	   
		
		if(this.req.getParameter("ajaxRequestRandom") != null){
			this.ajaxRequestRandom = this.req.getParameter("ajaxRequestRandom");
		}
		if(ajaxRequestRandom == null || ajaxRequestRandom.equals("")) {
			tokenControl();
		}
		
		Enumeration reqParamNames = this.req.getParameterNames();   
		StringBuffer sb = new StringBuffer();
		int i = 0;
	    while(reqParamNames.hasMoreElements()){
	    	String fieldName  = (String)reqParamNames.nextElement();
	    	String fieldValue = "";
	    	if(this.req.getParameter(fieldName)!=null){
	    		fieldValue = this.req.getParameter(fieldName);
	    	}
	    	if(fieldName.endsWith("_s")){
	    		sb.append("<input type='hidden' name='"+fieldName+"' value='"+fieldValue+"' id='rsrvId"+(i++)+"'/>\n");
	    	}
	    }
	    sb.append("<input type='hidden' name='rsrvNum' value='"+i+"' id='rsrvNum'/>\n");
	    listSearchHiddenField = sb.toString();
		
	}
	
	
	/**
	 * @author : 林俊钦
	 * @date : Apr 9, 2012 9:51:22 PM
	 * @Method Description :前台AJAX分页转json格式数据的转换
	 */
	public String pageList(List list,int totalNum){
		StringBuffer buf = new StringBuffer();
		JSONArray jsonArr = new JSONArray();
		JSONObject jsonObj = new JSONObject();		
		HttpServletRequest request = getRequest();
		String currentPage=request.getParameter("cp");
		String pageSize=request.getParameter("ps");			
		for(int i=0;i<list.size();i++){
			Object obj = list.get(i);
			jsonArr.add(obj);
		}		
		jsonObj.put("currentPage",currentPage);
		jsonObj.put("pageSize", pageSize);
		jsonObj.put("totalCount",totalNum);
		jsonObj.put("data", jsonArr);
		buf.append(jsonObj.toString());		
		return buf.toString();
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Apr 10, 2012 4:05:10 PM
	 * @Method Description :AJAX分页搜索条件的过滤
	 */
	public Map ajaxMap(Map map){
		HttpServletRequest request = getRequest();
		String currentPage="1",pageSize="20";
		if(request.getParameter("cp")!=null){
			currentPage=request.getParameter("cp");
		}
		if(request.getParameter("ps")!=null){
			pageSize=request.getParameter("ps");
		}
		int startRow=(Integer.parseInt(currentPage)-1)*Integer.parseInt(pageSize);//计算开始行
		map.put("start", startRow);
		map.put("limit", pageSize);		
		return map;
	}
	
	static HashMap numControlMap;
	
	/**
	 * @author : 林俊钦
	 * @date : Jul 17, 2012 4:10:36 PM
	 * @Method Description :从内存中取的HashMap对应的模块值
	 */
	public HashMap getMemberLevelNumControl(){
		numControlMap = new HashMap();		
		Memberlevel mbl=this.memberlevelService.get(this.session_level_code);
		String numControl = "";
		if(mbl!=null && mbl.getNum_control()!=null){
			numControl = mbl.getNum_control();
		}
		if(!numControl.equals("") && numControl.indexOf(",") > -1){
			String numC[] = numControl.split(",");
			if(numC.length > 0){
				for(int i = 0;i < numC.length;i++){
					String module_name = "",module_num = "";
					if(numC[i].indexOf("-") > -1){
						String numCSon[] = numC[i].split("-");
						if(numCSon.length > 1){
							module_name = numCSon[0];
							module_num = numCSon[1];
							numControlMap.put(module_name, module_num);
						}
					}
				}
			}
		}
		return numControlMap;
	}
	

	/**
	 * @author : 林俊钦
	 * @date : Jul 12, 2012 4:26:29 PM
	 * @Method Description : 控制需要发布模块信息的数量方法
	 */
	public boolean controlMsgNum(int num,String mod_type){
		HashMap numMap = getMemberLevelNumControl();
		int controlNum = -1;
		boolean flag=false;
		if( numMap != null && numMap.get(mod_type) != null && !numMap.get(mod_type).toString().equals("")){
			controlNum = Integer.parseInt( numMap.get(mod_type).toString() );
			//num：已发布信息数量
			//controlNum：系统控制数量
			if(is_crotorl){
				num=num-1;
			}
			if(num<controlNum){
			}else{
				this.ifvalidatepass = true;
				if(crotrolTip==null || crotrolTip.equals("")){
					crotrolTip="您发布的信息不能超过"+controlNum+"条";
					this.addActionMessage(crotrolTip);
				}
				flag=true;
			}
		}
		return flag;
	}
	
	
	/**
	 * 方法描述：判断信息标题是否存在 strnewtilte:修改后的标题 stroldtitle:原来的标题。只要用用于更新操作,如果是新增信息的话，直接填入空值就可以了 tablename:表名 tablecocolumn: 表的标题列名称
	 * 
	 * @date 2011-11-24
	 * @author 胡惜坤
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Boolean existsTitle(String strnewtilte, String stroldtitle, String tablename, String tablecocolumn) {
		// falges为：true的时候就是重复出现标题，为false的时候没有重复出现标题
		boolean falges = false;
		HashMap titleMap = new HashMap();
		strnewtilte = strnewtilte.replace(" ", "");
		titleMap.put("strtitle", strnewtilte);
		titleMap.put("tablename", tablename);
		titleMap.put("titlecol", tablecocolumn);
		int count = this.newsService.getInfoCount(titleMap);
		// 更新页面使用的判断
		if (stroldtitle != null && !stroldtitle.equals("")) {
			if (!strnewtilte.equals(stroldtitle)) {
				if (count != 0)
					falges = true;// 重复出现
			}
		} else {// 新增页面使用的判断
			if (count != 0)
				falges = true;// 重复出现
		}
		return falges;// 没有重复出现
	}
	/**
	 * 方法描述：验证分类是否选择
	 * 
	 * @author 胡惜坤
	 */
    public void validateCategoryIfSelect()
    {
    	//验证所属分类是否有选择
		if (ValidateUtil.isRequired(cat_attr) || cat_attr.indexOf("0")>-1) {
			this.addFieldError("cate_attr", "请选择分类");
		}
		
    }
    /**
	 * 方法描述：验证地区是否选择
	 * 
	 * @author 胡惜坤
	 */
    public void validateAreaIfSelect()
    {
    	//验证所属地区是否有选择
		if (ValidateUtil.isRequired(area_attr) || hidden_area_value.indexOf("0")>-1) {
			this.addFieldError("area_attr", "请选择地区");
		}
    }
	/**
	 * @return the pageBar
	 */
	public String getPageBar() {
		return pageBar;
	}

	/**
	 * @param pageBar
	 *            the pageBar to set
	 */
	public void setPageBar(String pageBar) {
		this.pageBar = pageBar;
	}
	
	

	public int getPageSize_s() {
		return pageSize_s;
	}

	public void setPageSize_s(int pageSize_s) {
		this.pageSize_s = pageSize_s;
	}

	/**
	 * @return the pages
	 */
	public int getPages_s() {
		return pages_s;
	}

	/**
	 * @param pages
	 *            the pages to set
	 */
	public void setPages_s(int pages_s) {
		this.pages_s = pages_s;
	}

	/**
	 * Convenience method to get the request
	 * 
	 * @return current request
	 */
	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * Convenience method to get the response
	 * 
	 * @return current response
	 */
	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * Convenience method to get the session. This will create a session if one doesn't exist.
	 * 
	 * @return the session from the request (request.getSession()).
	 */
	protected HttpSession getSession() {
		return getRequest().getSession();
	}

	
	public String getOldinfotitle() {
		return oldinfotitle;
	}

	public void setOldinfotitle(String oldinfotitle) {
		this.oldinfotitle = oldinfotitle;
	}
	

    public String redirectUrl;
    
    //自定义前台展示模版名称
	public String selfTemplate;
	
    
    public String goUrl(String redirectUrl){
    	if(this.selfTemplate != null && !this.selfTemplate.equals("")){
    		this.redirectUrl = this.selfTemplate;
    	}else{
    		this.redirectUrl = redirectUrl;
    	}
    	return "redirectUrl";
    }
    
    
    
    /*
     * 验证字段
     * obj：需要验证的标题提交字段
     */
    
    public void commonValidateField(Object obj) {
    	commonValidateField(obj,"public");
    }
    
    /*
     * 验证字段
     * obj：需要验证的标题提交字段
     * methodName：特殊方法的验证名称
     */
    public void commonValidateField(Object obj,String methodName) {
		
    	//获取对象名称，小写
    	String className = obj.getClass().getSimpleName().toLowerCase();
    	
		Map objMap = ValidateUtil.convertObjToMap(obj);
		
		//从xml文档中获取验证规则ArrayList
		List validateList = FieldValidateFromXmlUtil.getObjValidateList(className,methodName);
		
		if(validateList!=null&&validateList.size()>0)
		{
			for(int i=0;i<validateList.size();i++)
			{
				
				HashMap vtableMap = (HashMap)validateList.get(i);
				String name="",type="",length="",required="",cnname="";
				if(vtableMap.get("name")!=null) name = vtableMap.get("name").toString();
				if(vtableMap.get("type")!=null) type = vtableMap.get("type").toString();
				if(vtableMap.get("length")!=null) length = vtableMap.get("length").toString();
				if(vtableMap.get("required")!=null) required = vtableMap.get("required").toString();
				if(vtableMap.get("cnname")!=null) cnname = vtableMap.get("cnname").toString();
				
				//获取表单提交的值
				String fieldValue = "";
				if(objMap.get(name)!=null){
					fieldValue = objMap.get(name).toString();
				}
				//验证字段为必填
				if(required.equals("true") && fieldValue.trim().equals("")){
					this.addFieldError(className+"."+name, cnname + "不能为空");
				}
				if(!fieldValue.trim().equals(""))
				{
					//type：字段类型 
					//string为字符串,  int为整数数字,       email为电子邮件,       tel为固定电话,    mobile为移动电话,
		            //double为浮点型,   chinese为中文,  idcard为18位身份证格式,  ip为IP格式,      time为时间格式,
		            //alpha为字母,     repeat为重复字符  alphas 为数字、字母、下划线, rmb人民币类型 
					//alphasfirst由字母开头和数字、字母、下划线组成 ,alphaslimt 数字、字母、下划线组成6-20位
					//验证字段类型为：int为整数数字
					if(fieldValue.length() > Integer.parseInt(length)){
						this.addFieldError(className+"."+name, cnname + "的长度限制在"+length+"个字符");
					}
					else 
					{
						if(type.equals("int")&&ValidateUtil.isDigital(fieldValue.trim()) ){
							this.addFieldError(className+"."+name, cnname + "只能为数字(0-9)");
						}
						//验证字段类型为：email为电子邮件
						if(type.equals("email")&&ValidateUtil.isEmail(fieldValue.trim()) ){
							this.addFieldError(className+"."+name, cnname + "格式不正确,格式如:xxxx@xxx.com");
						}
						//验证字段类型为：tel为固定电话
						if(type.equals("tel")&&ValidateUtil.isTelephone(fieldValue.trim()) ){
							this.addFieldError(className+"."+name, cnname + "格式不正确,格式如:xxx-xxxxxxx");
						}
						//验证字段类型为：mobile为移动电话
						if(type.equals("mobile")&&ValidateUtil.isMobile(fieldValue.trim()) ){
							this.addFieldError(className+"."+name, cnname + "格式不正确");
						}
						//验证字段类型为：double为浮点型
						if(type.equals("double")&&ValidateUtil.isDouble(fieldValue.trim()) ){
							this.addFieldError(className+"."+name, cnname + "只能为浮点型");
						}
						//验证字段类型为：chinese为中文
						if(type.equals("chinese")&&ValidateUtil.isChinese(fieldValue.trim()) ){
							this.addFieldError(className+"."+name, cnname + "只能为汉字");
						}
						//验证字段类型为：idcard为18位身份证格式
						if(type.equals("idcard")&&ValidateUtil.isIdcard_18(fieldValue.trim()) ){
							this.addFieldError(className+"."+name, cnname + "只能为18位身份证格式");
						}
						//验证字段类型为：ip为IP格式
						if(type.equals("ip")&&ValidateUtil.isIP(fieldValue.trim()) ){
							this.addFieldError(className+"."+name, cnname + "只能为IP地址格式");
						}
						//验证字段类型为：time为时间格式
						if(type.equals("time")&&ValidateUtil.isTime(fieldValue.trim()) ){
							this.addFieldError(className+"."+name, cnname + "格式不正确,格式如:1900-01-01");
						}
						//验证字段类型为：alpha为字母
						if(type.equals("alpha")&&ValidateUtil.isAlpha(fieldValue.trim()) ){
							this.addFieldError(className+"."+name, cnname + "只能为字母(a-z A-Z)");
						}
						//验证字段类型为：repeat为重复字符
						if(type.equals("repeat")&&ValidateUtil.hasRepeat(fieldValue.trim()) ){
							this.addFieldError(className+"."+name, cnname + "不能出现重复");
						}
						//验证字段类型为：alphas 为数字、字母、下划线
						if(type.equals("alphas")&&ValidateUtil.isAlphas(fieldValue.trim()) ){
							this.addFieldError(className+"."+name, cnname + "由数字、字母或下划线组成");
						}
						//验证字段类型为：rmb人民币类型
						if(type.equals("rmb")&&ValidateUtil.isRmb(fieldValue.trim()) ){
							this.addFieldError(className+"."+name, cnname + "格式不正确,格式如:100.00");
						}
						//验证字段类型为：alphasfirst由字母开头和数字、字母、下划线结束
						if(type.equals("alphasfirst")&&ValidateUtil.isAlphasFirst(fieldValue.trim()) ){
							this.addFieldError(className+"."+name, cnname + "以字母开头,由数字、字母或下划线组成");
						}
						//验证字段类型为：alphaslimt 数字、字母、下划线组成6-20位
						if(type.equals("alphaslimt")&&ValidateUtil.isAlphasLimtLength(fieldValue.trim()) ){
							this.addFieldError(className+"."+name, cnname + "由6-32位的数字、字母或下划线组成");
						}	
						//验证字段类型为：URL格式验证
						if(type.equals("url")&&ValidateUtil.isURL(fieldValue.trim()) ){
							this.addFieldError(className+"."+name, cnname + "格式不正确,格式如:http://www.ruibaotong.net");
						}
					}
				}
				
		    }
				 
		}
	}
    
    /**
	 * @author : 林俊钦
	 * @date : Jun 14, 2012 3:55:24 PM
	 * @Method Description :不同的会员只能操作属于本身的会员标识权限控制
	 */
	public boolean accessControl(String cust_id){
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			if(cust_id.equals(this.session_cust_id)){
				return false;
			}else{
				return true;
			}
		}else{
			return false;
		}
	}
	
	
	 /**
	 * @Method Description : 加载控制用户是否有审核权限，
	 * 最终结果为：1：表示有审核权限，0或者其他值都表示没有审核权限
	 * @author : 胡惜坤
	 * @date : 2012-08-09
	 */
	public void loadAudtiOpt(List memList,List memhistoryList,String info_id,String model) {
		
		String mem_sort_no="";//当前审核人的序号
		Integer listcount=0;//审核人的总数
		String up_user_id="";//获取当前审核人的前一个人的userid
		if(memList!=null&&memList.size()>0){
			listcount=memList.size();
			for(int i=0;i<memList.size();i++){
				Map mMap=new HashMap();
				mMap=(HashMap)memList.get(i);
				if(mMap!=null&&mMap.get("userid")!=null){
					if(this.session_user_id.equals(mMap.get("userid").toString())){
						//当前用户在审核名单中
						if_opt_audit="1";
						mem_sort_no=mMap.get("sort_no").toString();
						break;
					}else {
						//保存上一个用户ID
						up_user_id=mMap.get("userid").toString();
					}
				}
			}
		}
		//用户在审核名单中
		if(if_opt_audit.equals("1")){
			
			List audtipersonList=new ArrayList();
			audtipersonList=AuditModelFuc.getAuditmemberhistory(model, info_id, this.session_user_id);
			//判断用户是否已经审核过，是否在审核历史表中存有该用户的审核历史
			if(audtipersonList!=null&&audtipersonList.size()>0){
				//获取用户的审核状态，返回值：0：审核通过，1：审核未通过
				if_opt_audit=passAudit(audtipersonList);
			}else {
				//审核人的序号是不是处于第一个人，如果是，就直接显示审核操作
				if(mem_sort_no.equals("1")){
					if_opt_audit="1";
				}else {
					List up_member_history_List=new ArrayList();
					up_member_history_List=AuditModelFuc.getAuditmemberhistory(model, info_id, up_user_id);
					//获取上一个用户的审核状态，返回值：0：审核通过，1：审核未通过:
					if(up_member_history_List!=null&&up_member_history_List.size()>0){
						String audtisate=passAudit(up_member_history_List);
						if(audtisate.equals("0")){
							if_opt_audit="1";//表示上一个审核人员审核通过，当前操作人可以进行审核信息
						}else {
							if_opt_audit="2";//表示上一个审核人员，没有审核通过，要等待上一个人审核通过以后，才可以审核信息
						}
					}
					else {
						if_opt_audit="2";//表示上一个审核人员，没有审核通过，要等待上一个人审核通过以后，才可以审核信息
					}
					
					
				}
			}
			
		}
		
		
		
	}
	 /**
	 * @Method Description : 获取审核历史，判断该用户是已经审核通过
	 * @author : 胡惜坤
	 * @date : 2012-08-10
	 */
	public String  passAudit(List memList) {
		
		String str_state="0";//str_state表示审核状态：0：通过，1：表示未通过
		if(memList!=null&&memList.size()>0){
			Map mmap=new HashMap();
			mmap=(HashMap)memList.get(memList.size()-1);
			if(mmap!=null&&mmap.get("info_state")!=null){
				if(mmap.get("info_state").equals("2")){
					str_state="1";
				}
			}
		}
		
		return str_state;
	}
	/**
	 * 方法描述：通过开启审核流程的审核操作方法
	 * 
	 * @return 返回值：0：表示可以修改主信息的审核状态，如果返回值为1：表示只是往审核历史表中插入数据，不改变主信息的审核状态
	 * @throws Exception
	 */
	public String auditInfoState(String model,String info_state) throws Exception {
		
		String strret="0";
		//是否开启审核模型，0：是，1：否
		if(cfg_auditmodel.equals("0")){
			if(AuditModelFuc.boolauditIndex(model,this.session_user_id,info_state)==false){
				strret="1";
			}
		}
		return strret;
	}
	//进入审核页面时候的加载方法
	public void auditView(String model,String info_id){
		//是否开启审核模型，0：是，1：否
		if(cfg_auditmodel.equals("0")){
			String model_type_mem=model,model_type_history=model;
			//处理模型名称和表名称不相同的情况,例如：企业表，表名称：member ,模块名称为：company    知道表名称为：ask 模块名称为：know
			if(model.equals("company")){
				model_type_history="member";
			}
			if(model.equals("know")){
				model_type_history="ask";
			}
			auditMemberList=AuditModelFuc.getAuditmodel(model_type_mem);
			if(auditMemberList!=null&&auditMemberList.size()>0){
				auditHistoryList=AuditModelFuc.getAuditmodelhistory(model_type_history,info_id);
				//判断当前用户是否在审核名单中
				loadAudtiOpt(auditMemberList,auditHistoryList,info_id,model_type_history);
			}
			else {
				cfg_auditmodel="1";
				if_opt_audit="1";
			}
			
		}
		else {
			if_opt_audit="1";
		}
	}
    
    //重写父类addFieldError
    public void addFieldError(String fieldName,String errorMessage){
    	super.addFieldError(fieldName, errorMessage);
    	this.ifvalidatepass = true;
    }
    
    //ajax获取用户上传图片是否添加水印
    public void getimgshuiyin(){
    	HttpServletRequest request = getRequest();
    	String isshuiyin = request.getParameter("isshuiyin");
    	this.getSession().setAttribute("isshuiyin", isshuiyin);
    }

	public String getToken_value() {
		return token_value;
	}

	public void setToken_value(String token_value) {
		this.token_value = token_value;
	}

	public String getGet_token_value() {
		return get_token_value;
	}

	public void setGet_token_value(String get_token_value) {
		this.get_token_value = get_token_value;
	}
	public String getMall_type() {
		return mall_type;
	}
	public void setMall_type(String mall_type) {
		this.mall_type = mall_type;
	}
	public String getHidden_membercat_value() {
		return hidden_membercat_value;
	}
	public void setHidden_membercat_value(String hidden_membercat_value) {
		this.hidden_membercat_value = hidden_membercat_value;
	}
	public List getAuditMemberList() {
		return auditMemberList;
	}
	public void setAuditMemberList(List auditMemberList) {
		this.auditMemberList = auditMemberList;
	}
	public List getAuditHistoryList() {
		return auditHistoryList;
	}
	public void setAuditHistoryList(List auditHistoryList) {
		this.auditHistoryList = auditHistoryList;
	}

    

}
