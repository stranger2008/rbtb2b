/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: MemberuserAction.java 
 */
package com.rbt.action;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.action.BaseAction;
import com.rbt.common.Constants;
import com.rbt.common.Md5;
import com.rbt.common.link.discuz.discuzOptUtil;
import com.rbt.common.util.DbUtil;
import com.rbt.common.util.MailUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Levelinfo;
import com.rbt.model.Membercredit;
import com.rbt.model.Memberinter;
import com.rbt.model.Memberlevel;
import com.rbt.model.Memberuser;
import com.rbt.model.Role;
import com.rbt.service.IBuyService;
import com.rbt.service.ILevelinfoService;
import com.rbt.service.ILogsService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMembercreditService;
import com.rbt.service.IMemberinboxService;
import com.rbt.service.IMemberinterService;
import com.rbt.service.IMemberlevelService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.IRoleService;
import com.rbt.service.ISupplyService;
import com.rbt.service.IOrderinfoService;
import com.rbt.service.ISysmoduleService;
import com.rbt.service.ITradecommentService;

/**
 * @function 功能 用户账号action类
 * @author 创建人 邱景岩
 * @date 创建日期 Tue Jul 19 09:37:16 CST 2011
 */
/**
 * @author Administrator
 *
 */
@Controller
public class MemberuserAction extends BaseAction implements Preparable {

	private static final long serialVersionUID = 1L;
	/*
	 * 隐藏字段message默认值
	 */
	private static final String MESSAGE_VALUE = "1";
	/*
	 * 提示信息
	 */
	public String message;
	/*
	 * 网站名称
	 */
	public String web_name;
	/*
	 * 搜索字段 mem_type_s：会员类型 cust_id:会员ID user_name_s:用户名 user_type_s：用户类型
	 * role_code_s：角色代码
	 */
	public String mem_type_s;
	public String cust_id_s;
	public String user_name_s;
	public String user_type_s;
	public String role_code_s;
	/*
	 * 批量更新隐藏字段 admin_memberuser_userid:用户ID串 admin_memberuser_passwd：用户的密码串
	 */
	public String admin_memberuser_userid;
	public String admin_memberuser_passwd;
	/*
	 * 旧密码：oldpasswd 新密码：newpasswd 确认密码：confirmpasswd 密保问题：passwd_answer 确认密保:confanswer
	 */
	public String oldpasswd;
	public String newpasswd;
	public String confirmpasswd;
	public String passwd_answer;
	public String confanswer;
	
	//旧邮箱：oldemail 新邮箱：newemail
	public String oldemail;
	public String newemail;

	/*
	 * 用户账号对象
	 */
	public Memberuser memberuser;
	//角色对象
	public Role role;
	/*
	 * 会员积分对象
	 */
	public Memberinter memberinter;
	/*
	 * 会员级别信息对象
	 */
	public Levelinfo levelinfo;
	
	public Memberlevel memberlevel;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IMemberService memberService;
	@Autowired
	public IMemberlevelService memberlevelService;
	@Autowired
	public IMemberuserService memberuserService;
	@Autowired
	public ILevelinfoService levelinfoService;
	@Autowired
	public IMemberinterService memberinterService;
	@Autowired
	public IMembercreditService membercreditService;	
	@Autowired
	private ISysmoduleService sysmoduleService;
	@Autowired
	public IRoleService roleService;
	@Autowired
	public ILogsService logsService;
	@Autowired
	public IMemberinboxService memberinboxService;
	@Autowired
	public ISupplyService supplyService;
	@Autowired
	public IBuyService buyService;
	@Autowired
	public IOrderinfoService orderinfoService;
	@Autowired
	public ITradecommentService tradecommentService;
	/*
	 * 用户账号信息集合
	 */
	public List memberuserList;
	public List roleList;
	public List memberList;
	public List memberinboxList;
	public List logsList;
	public List memberinterList;
	public List supplyList;
	public List buyList;
	public List countList;
	public List orderinfoList;

	// 网站当前域名
	public String web_host;

	// 会员积分
	private String web_integralCount = "0";
	// 积分排名
	public String web_orderRank;
    // 会员指数
	public String cust_name_credit;
	//会员后台用户类型
	public String user_type;
	
	//用户问题提示
	public String passwd_ques;
	//修改问题提示
	public String apasswd_ques;
	//修改答案
	public String apasswd_answer;
	//存放放密保状态
	public String setpos;
	//买家提醒
	public int buyerOrder;
	public int buyerCofirm;
	public int buyercomment;
	//卖家提醒
	public int daiorderCount;
	public int sellercomment;
	
	//现用等级名称
	public String now_levelName;
	
	//未读消息
	public int  unReadC; 
	//已读消息
	public int  markReadC;
	/**
	 * @function 功能 会员后台首页
	 * @author 创建人 邱景岩
	 * @date 创建日期 Aug 30, 2011 8:53:41 AM
	 */
	public String execute() throws Exception {	
		// 获取每日的上限积分
		String scoreToday = SysconfigFuc.getSysValue("cfg_sc_dayup");
		int_scoreToday = Integer.parseInt(scoreToday);
		if (this.session_cust_id != null) {
			levelinfo = this.levelinfoService.get(this.session_cust_id);
			if(levelinfo==null){
				levelinfo  = new Levelinfo();
			}
			String level_id = levelinfo.getLevel_code();
			if(level_id==null || "".equals(level_id)){
				level_id="1";
			}
			memberlevel= memberlevelService.get(level_id);
			if(memberlevel==null){
				memberlevel  = new Memberlevel();
			}
			now_levelName=memberlevel.getLevel_name();
		}
		//获取企业站的信用指数
		Membercredit cre=this.membercreditService.get(this.session_cust_id);
		cre=this.membercreditService.get(this.session_cust_id);
		if(cre!=null){
			cust_name_credit=cre.getC_num();
		}else{
			cust_name_credit="0";
		}
		web_host = getRequest().getHeader("host");
		
		// 绑定信息统计开始
		ModList();
		// 绑定信息统计结束
		// 绑定积分信息开始
		Memberinter inter = this.memberinterService.get(this.session_cust_id);
		if (inter != null) {
			web_integralCount = inter.getFund_num();
		}
		web_orderRank = getordernum(web_integralCount);
		// 绑定积分信息结束

		// 绑定消息中心开始
		//未读消息
		Map unReadmap = new HashMap();
		unReadmap.put("cust_id", this.session_cust_id);
		unReadmap.put("is_read", "0");		
		unReadC=this.memberinboxService.getCount(unReadmap);
		//已读消息
		Map markReadmap = new HashMap();
		markReadmap.put("cust_id", this.session_cust_id);
		markReadmap.put("is_read", "1");
		markReadC = this.memberinboxService.getCount(markReadmap);		
		// 绑定消息中心结束

		// 绑定操作日志开始
		Map logsmap = new HashMap();
		logsmap.put("cust_id", this.session_cust_id);
		logsmap.put("start", 0);
		logsmap.put("limit", 5);
		logsList = this.logsService.getList(logsmap);
		// 绑定操作日志结束

		//待付款订单
		Map payMap=new HashMap();
		payMap.put("cust_id", this.session_cust_id);		
		payMap.put("order_state","0");
		buyerOrder = this.orderinfoService.getCount(payMap);
		
		//待收货订单
		Map cofirmMap=new HashMap();
		cofirmMap.put("cust_id", this.session_cust_id);		
		cofirmMap.put("order_state","2");
		buyerCofirm = this.orderinfoService.getCount(cofirmMap);

		//待发货订单
		Map daiorderMap=new HashMap();
		daiorderMap.put("seller_id", this.session_cust_id);		
		daiorderMap.put("order_state","1");
		daiorderCount = this.orderinfoService.getCount(daiorderMap);
		
		//买家待评价
		Map buyMap = new HashMap();
		buyMap.put("cust_id", this.session_cust_id);
		buyMap.put("order_state", "3");
		orderinfoList = this.orderinfoService.getList(buyMap);
		Map buycomMap = new HashMap();
		for (int i = 0; i < orderinfoList.size(); i++) {
			Map orderMap = (HashMap) orderinfoList.get(i);
			// 找出是否已经评价的内容
			String order_id = orderMap.get("order_id").toString();// 获取订单号
			buycomMap.put("order_id", order_id);
			buycomMap.put("com_way", "1");
			// 查询交易评价表，是否已评价
			List list = this.tradecommentService.getList(buycomMap);
			if (list.size() == 0) {
				buyercomment++;// 买家未评数+1
			} 
		}
		
		//卖家待评价
		Map sellMap = new HashMap();
		sellMap.put("seller_id", this.session_cust_id);
		sellMap.put("order_state", "3");
		orderinfoList = this.orderinfoService.getList(sellMap);
		Map sellcomMap = new HashMap();
		for (int i = 0; i < orderinfoList.size(); i++) {
			Map orderMap = (HashMap) orderinfoList.get(i);
			// 找出是否已经评价的内容
			String order_id = orderMap.get("order_id").toString();// 获取订单号
			sellcomMap.put("order_id", order_id);
			sellcomMap.put("com_way", "2");
			// 查询交易评价表，是否已评价
			List list = this.tradecommentService.getList(sellcomMap);
			if (list.size() == 0) {
				sellercomment++;// 买家未评数+1
			} 
		}
		return SUCCESS;
	}
	
	
	/**
	 * @author : 林俊钦
	 * @date : Jul 13, 2012 1:42:18 PM
	 * @Method Description : 会员发布条数的判断
	 */
	public boolean controlInfoNum(){
		//查出已发布的信息个数
		Map tmap = new HashMap();
		tmap.put("cust_id", this.session_cust_id);
		int count = this.memberuserService.getCount(tmap);
		if(controlMsgNum(count,"memberuser")){
			return true;
		}else{
			return false;
		}
	}
	
	
	/**
	 * @Method Description : 获取模块中的条数与未审核条数
	 * @author : 林俊钦
	 * @date : Fri 17, 2012 13:41:16 PM
	 */
	private void ModList(){
		Map maps = new HashMap();
		maps.put("cust_id", this.session_cust_id);
		memberList = this.memberService.getWebMemberHomeList(maps);
		Map hashMaps = new HashMap();
		// 获取会员的身份供应或采购，或两者都有
		String cust_rage = "", cust_supply = "", cust_stock = "",module_attr="";
		if (memberList != null && memberList.size() != 0) {
			hashMaps = (HashMap) memberList.get(0);
			if (hashMaps.get("cust_rage") != null) {
				cust_rage = hashMaps.get("cust_rage").toString();
			}
			if (hashMaps.get("cust_supply") != null) {
				cust_supply = hashMaps.get("cust_supply").toString();
			}
			if (hashMaps.get("cust_stock") != null) {
				cust_stock = hashMaps.get("cust_stock").toString();
			}
			if (hashMaps.get("module_attr") != null) {
				module_attr = hashMaps.get("module_attr").toString();
				String mod[]=module_attr.split(",");				
				Map modListMap =new HashMap();
				//找出系统模块的
				List modlist=this.sysmoduleService.getList(modListMap);
				StringBuffer sb=new StringBuffer();
				String cust_id=this.session_cust_id;
				sb.append("SELECT ");
				for(int i=0;i<mod.length;i++){	
					for(int j=0;j<modlist.size();j++){
						Map listMap=(HashMap)modlist.get(j);
						String module_type="",is_catattr="",module_name="",table_name="";
						if(listMap.get("module_type")!=null){
							module_type=listMap.get("module_type").toString();
						}
						//如果在会员级别的串中存在系统模块中，则拼接SQL语句
						if(module_type.equals(mod[i])){
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
							sb.append("'"+module_name+"' AS "+mod[i]+"module_name,");
							sb.append("'"+is_catattr+"' AS "+mod[i]+"is_catattr,");
							sb.append("'"+up_table_name+"' AS "+mod[i]+"table_name,");
							sb.append("(SELECT COUNT(*) FROM "+table_name+" WHERE cust_id="+cust_id+" ) AS "+mod[i]+"count,");						
							if(i==(mod.length-1)){
								sb.append("(SELECT COUNT(*) FROM "+table_name+" WHERE cust_id="+cust_id+" AND info_state='0') AS  "+mod[i]+"countaudit");
							}else{
								sb.append("(SELECT COUNT(*) FROM "+table_name+" WHERE cust_id="+cust_id+" AND info_state='0') AS  "+mod[i]+"countaudit,");
							}
						}
					}
				}
				sb.append(" FROM member m WHERE m.cust_id='"+cust_id+"'");
				DbUtil db=new DbUtil();				
				List list=db.queryList(sb.toString());
				getAudtiCount(list,mod);
				
				if (cust_rage.equals("0")) {
					getBuyRecom(cust_stock);
				} else if (cust_rage.equals("1")) {
					getSupplyRecom(cust_supply);
				} else {
					getBuyRecom(cust_stock);
					getSupplyRecom(cust_supply);
				}
			}
		}		
	}
	
	/**
	 * @Method Description : 重构会员中心的审核条数数据
	 * @author : 林俊钦
	 * @date : Fri 17, 2012 12:49:14 PM
	 */
	private void getAudtiCount(List list,String[] mod){
		countList=new ArrayList();
		if(list!=null&&list.size()>0){
			Map listMap = (HashMap)list.get(0);
			for(int i=0;i<mod.length;i++){	
				Map modcountMap=new HashMap();
				String module_type=mod[i];

				//获取模块名称
				if(listMap.get(module_type+"module_name")!=null && !this.validateFactory.isRequired(listMap.get(module_type+"module_name").toString())){
					modcountMap.put("module_name", listMap.get(module_type+"module_name").toString());
				}
				//获取模块分类属性
				if(listMap.get(module_type+"is_catattr")!=null && !this.validateFactory.isRequired(listMap.get(module_type+"is_catattr").toString())){
					modcountMap.put("is_catattr", listMap.get(module_type+"is_catattr").toString());
				}
				//获取模块表名
				if(listMap.get(module_type+"table_name")!=null && !this.validateFactory.isRequired(listMap.get(module_type+"table_name").toString())){
					modcountMap.put("table_name", listMap.get(module_type+"table_name").toString());
				}
				//获取模块信息的总数
				if(listMap.get(module_type+"count")!=null && !this.validateFactory.isRequired(listMap.get(module_type+"count").toString())){
					modcountMap.put("count", listMap.get(module_type+"count").toString());
				}
				//获取模块未审核信息的条数
				if(listMap.get(module_type+"countaudit")!=null && !this.validateFactory.isRequired(listMap.get(module_type+"countaudit").toString())){
					modcountMap.put("countaudit", listMap.get(module_type+"countaudit").toString());
				}
				countList.add(modcountMap);
			}
		}
	}
	

	/**
	 * @Method Description : 获取绑定供应推荐
	 * @author : 林俊钦
	 * @date : Nov 24, 2011 12:49:14 PM
	 */
	public void getSupplyRecom(String cust_supply) {
		// 绑定供应推荐信息开始
		Map supplyMap = new HashMap();
		supplyMap.put("info_state", 1);
		supplyMap.put("start", 0);
		supplyMap.put("limit", 5);
		supplyMap.put("SortType", "SortType");
		if (!ValidateUtil.isRequired(cust_supply)) {
			if (cust_supply.indexOf(",") > -1) {
				String custSQL = "";
				String[] cust = cust_supply.split(",");
				for (int i = 0; i < cust.length; i++) {
					if (i == 0) {
						custSQL += "and INSTR(s.title,'" + cust[i] + "') >0 ";
					} else {
						custSQL += "or INSTR(s.title,'" + cust[i] + "') >0 ";
					}
				}
				supplyMap.put("attrString", custSQL);
			} else {
				supplyMap.put("title", cust_supply);// 如果没有逗号就直接查询数据
			}
			supplyList = this.supplyService.getWebSupplyList(supplyMap);
		}

		// 绑定供应推荐信息结束
	}

	/**
	 * @Method Description : 获取绑定求购推荐
	 * @author : 林俊钦
	 * @date : Nov 24, 2011 1:13:56 PM
	 */
	public void getBuyRecom(String cust_stock) {
		// 绑定求购推荐信息开始
		Map buyMap = new HashMap();
		buyMap.put("info_state", 1);
		buyMap.put("start", 0);
		buyMap.put("limit", 5);
		buyMap.put("SortType", "SortType");
		if (!ValidateUtil.isRequired(cust_stock)) {
			if (cust_stock.indexOf(",") > -1) {
				String custSQL = "";
				String[] cust = cust_stock.split(",");
				for (int i = 0; i < cust.length; i++) {
					if (i == 0) {
						custSQL += "and INSTR(buy.title,'" + cust[i] + "') >0 ";
					} else {
						custSQL += "or INSTR(buy.title,'" + cust[i] + "') >0 ";
					}

				}
				buyMap.put("attrString", custSQL);
			} else {
				buyMap.put("title", cust_stock);// 如果没有逗号就直接查询数据
			}
			buyList = this.buyService.getWebBuyList(buyMap);
		}
		// 绑定求购推荐信息结束
	}

	// 获取积分排名
	public String getordernum(String countnum) {
		Map ranksmap = new HashMap();
		ranksmap.put("fund_num", countnum);
		int count = this.memberinterService.getCount(ranksmap);
		count ++;//加1表自己的积分排名
		return String.valueOf(count);
	}

	/**
	 * 方法描述：用户登出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String logout() throws Exception {
		getSession().invalidate();
		
		//同步退出登录Discuz论坛
		 discuzOptUtil discuzopt=new discuzOptUtil();
		 discuzopt.userloginout(cfg_discuz_open);
		
		return Constants.MEMBER_LOGIN;
	}

	/**
	 * 方法描述：商城用户退出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String exit() throws Exception {
		getSession().invalidate();
		return Constants.MEMBER_SIGNIN;
	}
	
	/**
	 * 方法描述：跳转到修改密码页面
	 * 
	 * @author 陈晓艺
	 * @return 
	 * @throws Exception
	 * @date : Apr 20, 2012 10:45:59 AM
	 */
	public String updatePw() {
		//查找会员密保问题
		memberuser=this.memberuserService.get(this.session_user_id);
		if(memberuser==null){
			memberuser=new Memberuser();
		}
		 if(memberuser.getPasswd_ques()==null || "".equals(memberuser.getPasswd_ques())){
	        	setpos="1";
	        }else{
	        	setpos="2";
	        }
		 return goUrl("updatePw");

	}
	/**
	 * 方法描述：商城账户管理修改密码
	 * 
	 * @author 陈晓艺
	 * @throws Exception
	 * @date : Apr 20, 2012 10:45:59 AM
	 */
	public String updatePassword() {
		//查找会员密保问题
		memberuser=this.memberuserService.get(this.session_user_id);
		if(memberuser==null){
			memberuser=new Memberuser();
		}
		if(!"1".equals(setpos)){
			if (ValidateUtil.isRequired(this.getConfanswer())) {
				this.addFieldError("passwd_answer", "密保答案不能为空");
				return goUrl("updatePw");
			}
		
		
			//验证密保问题
			Map passMap = new HashMap();
			passMap.put("passwd_answer", confanswer);
			passMap.put("user_id", this.session_user_id);
			memberuserList = this.memberuserService.getList(passMap);
			if (memberuserList == null || memberuserList.size() == 0) {
				this.addFieldError("passwd_answer", "密保问题不正确，请重新输入");
				return goUrl("updatePw");
			}
		
		}
		
		if (ValidateUtil.isRequired(this.getOldpasswd())) {
			this.addFieldError("oldpasswd", "旧密码不能为空");
			return goUrl("updatePw");
		}		
		// 查找密码	
		Map pageMap = new HashMap();
		oldpasswd = Md5.getMD5(oldpasswd.getBytes());
		pageMap.put("passwd", oldpasswd);
		pageMap.put("user_id", this.session_user_id);
		memberuserList = this.memberuserService.getList(pageMap);
		if (memberuserList == null || memberuserList.size() == 0) {
			this.addFieldError("oldpasswd", "旧密码不正确，请重新输入");
			return goUrl("updatePw");
		}
		if (ValidateUtil.isRequired(this.getNewpasswd())) {
			this.addFieldError("newpasswd", "新密码不能为空");
			return goUrl("updatePw");
		}
		if (ValidateUtil.isRequired(this.getConfirmpasswd())) {
			this.addFieldError("confirmpasswd", "确认新密码不能为空");
			return goUrl("updatePw");
		}
		if (ValidateUtil.isAlphasLimtLength(this.getNewpasswd())) {
			this.addFieldError("newpasswd", "密码只能由数字、字母或者下划线组成6-20位，请重新输入");
			return goUrl("updatePw");
		}
		if (!this.getNewpasswd().equals(this.getConfirmpasswd())) {
			this.addFieldError("confirmpasswd", "确认密码与新密码不一致，请重新输入");
			return goUrl("updatePw");
		}
		
		//获取密码强度
		String password=this.getNewpasswd();
		String pass_strength = getpass_str(password);
		memberuser = this.memberuserService.get(this.session_user_id);
		// 对密码加密
		if (newpasswd != null && !newpasswd.equals("")) {
			newpasswd = Md5.getMD5(newpasswd.getBytes());
		} else {
			newpasswd = null;
		}
		memberuser.setPasswd(newpasswd);
		memberuser.setPass_strength(pass_strength);
		//字段验证
		super.commonValidateField(memberuser);
		if(super.ifvalidatepass){
			return goUrl("updatePw");
		}
		this.memberuserService.update(memberuser);
		this.addActionMessage("设置密码成功");
		this.setMessage(MESSAGE_VALUE);
		return goUrl("updatePw");

	}
	
	//获取密码强度
	public String getpass_str(String password){
		int str=2;
		if(password.matches("[0-9]*"))str--;
		if(password.matches("[a-zA-Z]*"))str--;
		if(password.length()<10 && str>=0)str--;
		String pass_strength=Integer.toString(str);
		return pass_strength;
	}
	
	
	/**
	 * 方法描述：跳转到修改邮箱页面
	 * 
	 * @author 陈晓艺
	 * @return
	 * @throws Exception
	 * @date : Apr 20, 2012 10:45:59 AM
	 */
	public String updateEmail() {
			// TODO Auto-generated method stub
		memberuser = this.memberuserService.get(this.session_user_id);	
		 if(memberuser.getPasswd_ques()==null || "".equals(memberuser.getPasswd_ques())){
	        	setpos="1";
	        }else{
	        	setpos="2";
	        }
		return goUrl("updateEmail");

		}
	/**
	 * 方法描述：跳转到修改邮箱页面
	 * 
	 * @author 陈晓艺
	 * @return
	 * @throws Exception
	 * @date : Apr 20, 2012 10:45:59 AM
	 */
	public String updateE() {
		//查找会员密保问题
		memberuser=this.memberuserService.get(this.session_user_id);
		if(memberuser==null){
			memberuser=new Memberuser();
		}
		if(!"1".equals(setpos)){
			if (ValidateUtil.isRequired(this.getConfanswer())) {
				this.addFieldError("passwd_answer", "密保答案不能为空");
				return goUrl("updateEmail");
			}
			
			//验证密保问题
			Map passMap = new HashMap();
			passMap.put("passwd_answer", confanswer);
			passMap.put("user_id", this.session_user_id);
			memberuserList = this.memberuserService.getList(passMap);
			if (memberuserList == null || memberuserList.size() == 0) {
				this.addFieldError("passwd_answer", "密保问题不正确，请重新输入");
				return goUrl("updateEmail");
			}
		}
		
		//判断密码是否有误
		if (ValidateUtil.isRequired(this.getOldpasswd())) {
			this.addFieldError("oldpasswd", "密码不能为空");
			return goUrl("updateEmail");
		}		
		// 查找密码	
		Map pageMap = new HashMap();
		oldpasswd = Md5.getMD5(oldpasswd.getBytes());
		pageMap.put("passwd", oldpasswd);
		pageMap.put("user_id", this.session_user_id);
		memberuserList = this.memberuserService.getList(pageMap);
		if (memberuserList == null || memberuserList.size() == 0) {
			this.addFieldError("oldpasswd", "密码不正确，请重新输入");
			return goUrl("updateEmail");
		}
		
		if (ValidateUtil.isRequired(this.getNewemail())) {
			this.addFieldError("newemail", "新邮箱不能为空");
			return goUrl("updateEmail");
		}		
		if (ValidateUtil.isEmail(this.getNewemail())) {
			this.addFieldError("newemail", "请输入正确新邮箱");
			return goUrl("updateEmail");
		}

		if (!ValidateUtil.isEmail(this.getNewemail()) && existsTitle(newemail,memberuser.getEmail(),"memberuser","email")) {
			this.addFieldError("newemail", "该邮箱已经存在，请重新输入");
			return goUrl("updateEmail");
		}
		if(newemail!=null && (memberuser.getEmail().equals(newemail.trim())))
		{
			this.addFieldError("newemail", "该邮箱已在使用");
			return goUrl("updateEmail");
		}
			memberuser = this.memberuserService.get(this.session_user_id);
			memberuser.setEmail(newemail);
			//字段验证
			super.commonValidateField(memberuser);
			if(super.ifvalidatepass){
				return goUrl("updateEmail");
			}
			this.memberuserService.update(memberuser);
			//验证发送新邮箱
			String title="这是来自瑞宝通的修改邮箱确认信";
			String content="恭喜您修改邮箱成功！";
			MailUtil mailUtil=new MailUtil();
			mailUtil.sendBatchMail(title, content, this.getNewemail());			
			this.addActionMessage("邮箱修改成功");
			this.setMessage(MESSAGE_VALUE);	 
			return goUrl("updateEmail");
		}
	
	
	
	/**
	 * 方法描述：返回新增用户账号页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		
		Map userMap = new HashMap();
		userMap.put("cust_id", this.session_cust_id);
		userMap.put("user_type", "1");//user_type:用户类型 1：管理员
		memberuserList = this.memberuserService.getList(userMap);
		if(memberuserList != null && memberuserList.size()>0){
			 Map typeMap = (Map) memberuserList.get(0);
			 if(typeMap.get("user_type") != null)
			 {
				 user_type = typeMap.get("user_type").toString();
			 }
		}
		setrole();
		//发布信息数量控制
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			controlInfoNum();
		}
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增用户账号
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		if(!ValidateUtil.isRequired(memberuser.getUser_name()) && existsTitle(memberuser.getUser_name(),"","memberuser","user_name")){
			this.addFieldError("memberuser.user_name", "该用户账号已经存在,请重新输入");
		}
		String pass_str="";
		if(!ValidateUtil.isRequired(memberuser.getPasswd()) && !ValidateUtil.isAlphasLimtLength(memberuser.getPasswd())){
			// 对密码进行MD5加密
			String passwd = memberuser.getPasswd();
			//密码强度
			pass_str = getpass_str(passwd);
			if (passwd != null && !passwd.equals("")) {
				passwd = Md5.getMD5(passwd.getBytes());
			} else {
				passwd = null;
			}
			memberuser.setPasswd(passwd);
		}
		memberuser.setCust_id(this.session_cust_id);
		memberuser.setPass_strength(pass_str);
		//发布信息数量控制
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			if(controlInfoNum()){
				return add();
			}
		}
		//字段验证
		super.commonValidateField(memberuser);
		if(super.ifvalidatepass){
			return add();
		}
		this.memberuserService.insertMemberuser(memberuser);
		this.addActionMessage("新增用户账号成功");
		this.memberuser = null;
		is_crotorl=true;
		return add();
	}

	/**
	 * 方法描述：修改用户账号信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String idString = memberuser.getUser_id();
		if (ValidateUtil.isDigital(idString)) {
			return list();
		}
		if(!ValidateUtil.isRequired(memberuser.getPasswd()) && ValidateUtil.isAlphasLimtLength(memberuser.getPasswd())){
			this.addFieldError("memberuser.passwd", "由6-32位的数字、字母或下划线组成");
		}
		if (!ValidateUtil.isRequired(memberuser.getEmail()) && existsTitle(memberuser.getEmail(),oldemail,"memberuser","email")) {
			this.addFieldError("memberuser.email", "该邮箱已经存在，请重新输入");
		}
		memberuser.setCust_id(this.session_cust_id);
		//字段验证
		super.commonValidateField(memberuser);
		if(super.ifvalidatepass){
			return view();
		}
		this.memberuserService.update(memberuser);
		this.addActionMessage("修改用户账号成功");
		return list();
	}

	/**
	 * @function 功能 跳转到密码修改页
	 * @author 创建人 邱景岩
	 * @date 创建日期 Aug 2, 2011 9:44:15 AM
	 */
	public String passwd() throws Exception {
		return INPUT;
	}

	/**
	 * @function 功能 设置密码
	 * @author 创建人 邱景岩
	 * @date 创建日期 Aug 2, 2011 9:40:22 AM
	 */
	public String updatepasswd() throws Exception {
		if (ValidateUtil.isRequired(this.message)) {
			this.setMessage(MESSAGE_VALUE);
			return INPUT;
		}
		if (ValidateUtil.isRequired(this.getOldpasswd())) {
			this.addFieldError("oldpasswd", "旧密码不能为空");
			return INPUT;
		}
		Map pageMap = new HashMap();
		
		String discuzNewPwd="",discuzOldPwd="";
		//获取原始的旧密码
		discuzOldPwd=oldpasswd;
		
		
		// 查找密码
		oldpasswd = Md5.getMD5(oldpasswd.getBytes());
		pageMap.put("passwd", oldpasswd);
		pageMap.put("user_id", this.session_user_id);
		memberuserList = this.memberuserService.getList(pageMap);
		if (memberuserList == null || memberuserList.size() == 0) {
			this.addFieldError("oldpasswd", "旧密码不正确，请重新输入");
			return INPUT;
		}
		if (ValidateUtil.isRequired(this.getNewpasswd())) {
			this.addFieldError("newpasswd", "新密码不能为空");
			return INPUT;
		}
		if (ValidateUtil.isRequired(this.getConfirmpasswd())) {
			this.addFieldError("confirmpasswd", "确认密码不能为空");
			return INPUT;
		}
		if (ValidateUtil.isAlphas(this.getNewpasswd())) {
			this.addFieldError("newpasswd", "密码只能由数字、字母或者下划线组成，请重新输入");
			return INPUT;
		}
		if (!this.getNewpasswd().equals(this.getConfirmpasswd())) {
			this.addFieldError("confirmpasswd", "确认密码与新密码不一致，请重新输入");
			return INPUT;
		}
		
		memberuser = this.memberuserService.get(this.session_user_id);
		
		
		//论坛获取新密码
		discuzNewPwd=newpasswd;
		
		// 对密码加密
		if (newpasswd != null && !newpasswd.equals("")) {
			newpasswd = Md5.getMD5(newpasswd.getBytes());
		} else {
			newpasswd = null;
		}
		memberuser.setPasswd(newpasswd);
		//字段验证
		super.commonValidateField(memberuser);
		if(super.ifvalidatepass){
			return INPUT;
		}
		this.memberuserService.update(memberuser);
		
		
		this.addActionMessage("设置密码成功");
		this.setMessage(MESSAGE_VALUE);
		 /*
		  * 系统开启论坛同步的时候，修改系统密码，也要修改论坛密码
		  */
		  discuzOptUtil discuzopt=new discuzOptUtil();
		  discuzopt.usermodifypwd(memberuser.getUser_name(), discuzOldPwd, discuzNewPwd, cfg_discuz_open);
		return INPUT;
	}

	/**
	 * 方法描述：删除用户账号信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.memberuser.getUser_id();
		id = id.replace(" ", "");
		this.memberuserService.delete(id);
		this.addActionMessage("删除用户账号成功");
		return list();
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		setrole();
		Map pageMap = new HashMap();
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		if (cust_id_s != null && !cust_id_s.equals("")) {
			pageMap.put("cust_id", cust_id_s);
		}
		if (user_name_s != null && !user_name_s.equals("")) {
			pageMap.put("user_name", user_name_s);
		}
		if (user_type_s != null && !user_type_s.equals("")) {
			pageMap.put("user_type", user_type_s);
		}
		if (role_code_s != null && !role_code_s.equals("")) {
			pageMap.put("role_code", role_code_s);
		}
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.memberuserService.getCount(pageMap);

		// 分页插件
		pageMap = super.pageTool(count, pageMap);

		memberuserList = this.memberuserService.getList(pageMap);		
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出用户账号信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(memberuser.getCust_id()!=null){
			if(accessControl(memberuser.getCust_id())){
				return list();
			}
		}
		//判断用户类型
		if(memberuser.getUser_type() != null){
			user_type = memberuser.getUser_type();
		}
		setrole();		
		if(memberuser.getUser_id() != null){
			memberuser=memberuserService.get(memberuser.getUser_id());
		}else{
			memberuser=memberuserService.get(this.session_user_id);
		}
		if(memberuser!=null && memberuser.getRole_code()!=null && !memberuser.getRole_code().equals("")){
			role=roleService.get(memberuser.getRole_code());
		}
		return goUrl(VIEW);
	}
	
	//修改账号信息
	public String updateuser() throws Exception{
		super.commonValidateField(memberuser);
		if(super.ifvalidatepass){
			return view();
		}
		this.addActionMessage("修改成功，请查看");
		this.setMessage(MESSAGE_VALUE);
		this.memberuserService.update(memberuser);
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：用户角色集合信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void setrole(){
		if(this.session_cust_id!=null && !this.session_cust_id.equals("")){
			Map map = new HashMap();
			map.put("cust_id", this.session_cust_id);
			roleList = this.roleService.getList(map);
		}
	}
	/**
	 * 方法描述：回显提问问题
	 * 
	 * @return
	 * @throws Exception
	 */
	public String viewque(){
		HttpServletRequest request = getRequest();
		//根据标识符查找分类ID
        memberuser=memberuserService.get(this.session_user_id);
        if(memberuser.getPasswd_ques()==null || "".equals(memberuser.getPasswd_ques())){
        	setpos="1";
        }else{
        	setpos="2";
        }
        passwd_ques=memberuser.getPasswd_ques();
		return goUrl("updateque");
	}
	
	/**
	 * 方法描述：跟新提问问题以及答案
	 * @return
	 * @throws java.io.IOException
	 * @throws Exception
	 */
	public String updateque() throws IOException{
		memberuser=memberuserService.get(this.session_user_id);
		if(!"1".equals(setpos)){
			if(this.validateFactory.isRequired(passwd_answer)){
				this.addFieldError("passwd_answer", "答案不能为空");
				return viewque();
			}
			if(!passwd_answer.equals(memberuser.getPasswd_answer())){
				this.addFieldError("passwd_answer", "答案错误");
				return viewque();
			}
		}
		if(this.validateFactory.isRequired(apasswd_ques)){
			this.addFieldError("apasswd_ques", "修改提示问题不能为空");
			return viewque();
		}
		if(this.validateFactory.isRequired(apasswd_answer)){
			this.addFieldError("apasswd_answer", "答案不能为空");
			return viewque();
		}
		memberuser.setPasswd_ques(apasswd_ques);
		memberuser.setPasswd_answer(apasswd_answer);
		memberuserService.update(memberuser);
		HttpServletResponse response = getResponse();
		this.addActionMessage("设置密保成功");
		this.setMessage(MESSAGE_VALUE);
		return viewque();
	}
	
	
	/**
	 * 方法描述：批量修改用户密码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updatePasswdBatch() throws Exception {
		String userid = this.admin_memberuser_userid;
		String passwd = this.admin_memberuser_passwd;
		userid = userid.replace(" ", "");
		passwd = passwd.replace(" ", "");
		String[] pwdstr = passwd.split(",");
		String pwdstring = "";
		StringBuffer pwdbf = new StringBuffer();
		// 对密码进行MD5加密
		for (int i = 0; i < pwdstr.length; i++) {
			if (pwdstr[i] != null && !pwdstr[i].equals("")) {
				pwdbf.append(Md5.getMD5(pwdstr[i].getBytes())).append(",");
			} else {
				pwdbf.append("");
			}
		}
		pwdstring = pwdbf.toString();
		int len = pwdstring.length();
		if (len > 0) {
			pwdstring = pwdstring.substring(0, len - 1);
		}
		String useridStr[] = userid.split(",");
		String passwdStr[] = pwdstring.split(",");
		List userList = new ArrayList();
		if (useridStr.length > 0) {
			for (int i = 0; i < useridStr.length; i++) {
				Map configMap = new HashMap();
				configMap.put("user_id", useridStr[i]);
				configMap.put("passwd", passwdStr[i]);
				userList.add(configMap);
			}
		}
		this.memberuserService.updatePasswdBatch(userList);
		this.addActionMessage("批量修改密码成功");
		setCust_id_s(cust_id_s);
		setMem_type_s(mem_type_s);
		return list();
	}

	/**
	 * @return the memberuser
	 */
	public Memberuser getMemberuser() {
		return memberuser;
	}

	/**
	 * @param Memberuser
	 *            the memberuser to set
	 */
	public void setMemberuser(Memberuser memberuser) {
		this.memberuser = memberuser;
	}

	/**
	 * @return the memberinter
	 */
	public Memberinter getMemberinter() {
		return memberinter;
	}

	/**
	 * @param memberinter
	 *            the memberinter to set
	 */
	public void setMemberinter(Memberinter memberinter) {
		this.memberinter = memberinter;
	}

	/**
	 * @return the levelinfo
	 */
	public Levelinfo getLevelinfo() {
		return levelinfo;
	}

	/**
	 * @param levelinfo
	 *            the levelinfo to set
	 */
	public void setLevelinfo(Levelinfo levelinfo) {
		this.levelinfo = levelinfo;
	}

	/**
	 * @return the MemberuserList
	 */
	public List getMemberuserList() {
		return memberuserList;
	}

	/**
	 * @param memberuserList
	 *            the MemberuserList to set
	 */
	public void setMemberuserList(List memberuserList) {
		this.memberuserList = memberuserList;
	}

	/**
	 * @return the mem_type_s
	 */
	public String getMem_type_s() {
		return mem_type_s;
	}

	/**
	 * @param mem_type_s
	 *            the mem_type_s to set
	 */
	public void setMem_type_s(String mem_type_s) {
		this.mem_type_s = mem_type_s;
	}

	/**
	 * @return the cust_id_s
	 */
	public String getCust_id_s() {
		return cust_id_s;
	}

	/**
	 * @param cust_id_s
	 *            the cust_id_s to set
	 */
	public void setCust_id_s(String cust_id_s) {
		this.cust_id_s = cust_id_s;
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
	 * @return the user_type_s
	 */
	public String getUser_type_s() {
		return user_type_s;
	}

	/**
	 * @param user_type_s
	 *            the user_type_s to set
	 */
	public void setUser_type_s(String user_type_s) {
		this.user_type_s = user_type_s;
	}

	/**
	 * @return the role_code_s
	 */
	public String getRole_code_s() {
		return role_code_s;
	}

	/**
	 * @param role_code_s
	 *            the role_code_s to set
	 */
	public void setRole_code_s(String role_code_s) {
		this.role_code_s = role_code_s;
	}

	/**
	 * @return the admin_memberuser_userid
	 */
	public String getAdmin_memberuser_userid() {
		return admin_memberuser_userid;
	}

	/**
	 * @param admin_memberuser_userid
	 *            the admin_memberuser_userid to set
	 */
	public void setAdmin_memberuser_userid(String admin_memberuser_userid) {
		this.admin_memberuser_userid = admin_memberuser_userid;
	}

	/**
	 * @return the admin_memberuser_passwd
	 */
	public String getAdmin_memberuser_passwd() {
		return admin_memberuser_passwd;
	}

	/**
	 * @param admin_memberuser_passwd
	 *            the admin_memberuser_passwd to set
	 */
	public void setAdmin_memberuser_passwd(String admin_memberuser_passwd) {
		this.admin_memberuser_passwd = admin_memberuser_passwd;
	}

	/**
	 * @return the oldpasswd
	 */
	public String getOldpasswd() {
		return oldpasswd;
	}

	/**
	 * @param oldpasswd
	 *            the oldpasswd to set
	 */
	public void setOldpasswd(String oldpasswd) {
		this.oldpasswd = oldpasswd;
	}

	/**
	 * @return the newpasswd
	 */
	public String getNewpasswd() {
		return newpasswd;
	}

	/**
	 * @param newpasswd
	 *            the newpasswd to set
	 */
	public void setNewpasswd(String newpasswd) {
		this.newpasswd = newpasswd;
	}

	/**
	 * @return the confirmpasswd
	 */
	public String getConfirmpasswd() {
		return confirmpasswd;
	}

	/**
	 * @param confirmpasswd
	 *            the confirmpasswd to set
	 */
	public void setConfirmpasswd(String confirmpasswd) {
		this.confirmpasswd = confirmpasswd;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
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
	 * @return the web_name
	 */
	public String getWeb_name() {
		return web_name;
	}

	/**
	 * @param web_name
	 *            the web_name to set
	 */
	public void setWeb_name(String web_name) {
		this.web_name = web_name;
	}

	public String getWeb_host() {
		return web_host;
	}

	public void setWeb_host(String web_host) {
		this.web_host = web_host;
	}

	public String getWeb_integralCount() {
		return web_integralCount;
	}

	public void setWeb_integralCount(String web_integralCount) {
		this.web_integralCount = web_integralCount;
	}

	public String getWeb_orderRank() {
		return web_orderRank;
	}

	public void setWeb_orderRank(String web_orderRank) {
		this.web_orderRank = web_orderRank;
	}

	public List getMemberList() {
		return memberList;
	}

	public void setMemberList(List memberList) {
		this.memberList = memberList;
	}

	public ILogsService getLogsService() {
		return logsService;
	}

	public void setLogsService(ILogsService logsService) {
		this.logsService = logsService;
	}

	public IMemberinboxService getMemberinboxService() {
		return memberinboxService;
	}

	public void setMemberinboxService(IMemberinboxService memberinboxService) {
		this.memberinboxService = memberinboxService;
	}

	public List getMemberinboxList() {
		return memberinboxList;
	}

	public void setMemberinboxList(List memberinboxList) {
		this.memberinboxList = memberinboxList;
	}

	public List getLogsList() {
		return logsList;
	}
	
	public void setLogsList(List logsList) {
		this.logsList = logsList;
	}

	public IMemberinterService getMemberinterService() {
		return memberinterService;
	}

	public List getMemberinterList() {
		return memberinterList;
	}

	public void setMemberinterList(List memberinterList) {
		this.memberinterList = memberinterList;
	}

	public String getOldemail() {
		return oldemail;
	}

	public void setOldemail(String oldemail) {
		this.oldemail = oldemail;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public void prepare() throws Exception { super.saveRequestParameter();
	if (memberuser == null) {
		memberuser = new Memberuser();
	}
	String id = memberuser.getUser_id();
	if (!ValidateUtil.isDigital(id)) {
		memberuser = this.memberuserService.get(id);
	}
}

	public String getNewemail() {
		return newemail;
	}

	public void setNewemail(String newemail) {
		this.newemail = newemail;
	}

	public String getPasswd_ques() {
		return passwd_ques;
	}

	public void setPasswd_ques(String passwd_ques) {
		this.passwd_ques = passwd_ques;
	}

	public String getPasswd_answer() {
		return passwd_answer;
	}

	public void setPasswd_answer(String passwd_answer) {
		this.passwd_answer = passwd_answer;
	}

	public String getConfanswer() {
		return confanswer;
	}

	public void setConfanswer(String confanswer) {
		this.confanswer = confanswer;
	}

	public String getSetpos() {
		return setpos;
	}

	public void setSetpos(String setpos) {
		this.setpos = setpos;
	}

}
