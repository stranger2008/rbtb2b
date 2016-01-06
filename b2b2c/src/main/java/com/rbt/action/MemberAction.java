/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: MemberAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.opensymphony.xwork2.Preparable;
import com.rbt.action.BaseAction;
import com.rbt.common.Constants;
import com.rbt.common.Md5;
import com.rbt.common.util.ValidateUtil;
import com.rbt.createHtml.DoHtml;
import com.rbt.model.Levelinfo;
import com.rbt.model.Member;
import com.rbt.model.Membercredit;
import com.rbt.model.Memberfund;
import com.rbt.model.Memberinter;
import com.rbt.model.Memberuser;
import com.rbt.model.Video;
import com.rbt.service.IAreaService;
import com.rbt.service.ICategoryService;
import com.rbt.service.ICommparaService;
import com.rbt.service.ILevelinfoService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMembercreditService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IMemberinterService;
import com.rbt.service.IMemberlevelService;
import com.rbt.service.IMemberuserService;
import com.rbt.function.AreaFuc;
import com.rbt.function.CategoryFuc;
import com.rbt.function.CommparaFuc;
import com.rbt.function.MemberFuc;
import com.rbt.function.SysconfigFuc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 会员action类
 * @author 创建人 邱景岩
 * @date 创建日期 Wed Jul 13 08:48:07 CST 2011
 */
@Controller
public class MemberAction extends BaseAction implements Preparable {

	/*
	 * 序列化
	 */
	private static final long serialVersionUID = 7214704723619945814L;
	/*
	 * 系统参数设置默认变量名 cfg_mb_notallow：不允许注册的登陆名 cfg_md_mailtest：是否限制Email只能注册一个帐号
	 */
	private static final String CFG_MB_NOTALLOW = "cfg_mb_notallow";
	/*
	 * 验证会员状态标识
	 */
	public String statecodeTip = "";
	/*
	 * 会员类型标识符
	 */
	public String custtypeTip = "";
	/*
	 * 所属分类标识符
	 */
	public String classattrTip = "";
	/*
	 * 搜索字段 cust_name_s:会员名称 mem_type_s:会员类型 contact_name_s:联系人姓名
	 * contact_cellphone_s:联系人手机 email_s:邮箱 in_date_s:最小注册日期 end_date_s:最大注册日期
	 * state_code_s:会员状态 recommend_s:推荐 search_area_attr:搜索分类字段 会员级别信息：level_s
	 * 会员用户名：user_name 密码：passwd
	 */
	public String cust_name_s;
	public String mem_type_s;
	public String email_s;
	public String in_date_s;
	public String end_date_s;
	public String state_code_s;
	public String recommend_s;
	public String today;
	public String area_attr_s;
	public String cat_attr_s;
	public String level_s;
	public String info_state_s;
	public String username;
	public String passwd;
	public String oldemail;
	public String cat_attr_str;
	
	public String other_money_type;
	/*
	 * 会员对象及相关对象
	 */
	public Member member;
	public Levelinfo levelinfo;
	public Memberinter memberinter;
	public Memberfund memberfund;
	public Membercredit membercredit;
	//会员头像
	public String logo_path;
	public String modType="company";

	
	
	
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
	 * 会员级别配置信息业务接口
	 */
	@Autowired
	public IMemberlevelService memberlevelService;
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
	 * 地区业务接口
	 */
	@Autowired
	public IAreaService areaService;
	/*
	 * 分类业务接口
	 */
	@Autowired
	public ICategoryService categoryService;
	/*
	 * 会员信息集合
	 */
	public List memberList;
	/*
	 * 参数集合
	 */
	public List commparaList;
	/*
	 * 公司规模集合
	 */
	public List staffList;
	/*
	 * 注册币种集合
	 */
	public List moneyList;
	/*
	 * 年销售额集合
	 */
	public List sumList;
	/*
	 * 级别信息集合
	 */
	public List levelList;
	/*
	 * 分类信息集合
	 */
	public List cat_attr_list;
	/*
	 * 获取自定义属性的字符串
	 */
	public String[] client;
	/*
	 * 用于回选经营模式属性的绑定
	 */
	public List clientStrList;
	/*
	 * 用于回显勾选的经营模式
	 */
	public List clientList;
	/*
	 * 用于审核页面经营模式的属性文本
	 */
	public String clientstateall;

	/**
	 * 方法描述：返回新增企业会员页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		setCommpara();		
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增企业会员
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		if(cat_attr_str!=null){
			cat_attr_list(cat_attr_str.replace(" ",""));
		}		
		// 用于所属地区的回选开始
		loadArea();
		if (ValidateUtil.isRequired(username)) {
			this.addFieldError("username", "用户名不能为空");
		} else {
			if (ValidateUtil.isAlphasFirst(username)) {
				this.addFieldError("username", "用户名以字母开头，由数字、字母、下划线组成");
			} else {
				String notallow_name = "";
				notallow_name = SysconfigFuc.getSysValue(CFG_MB_NOTALLOW);
				if (notallow_name.indexOf(username) > -1) {
					this.addFieldError("username", "本网站不允许注册此类用户名");
				} else {
					if (existsTitle(username, "", "memberuser", "user_name")) {
						this.addFieldError("username", "该用户名已经存在,请重新输入");
					}
				}
			}
		}

		if (ValidateUtil.isRequired(passwd)) {
			this.addFieldError("passwd", "密码不能为空");
		} else if (ValidateUtil.isAlphasLimtLength(passwd)) {
			this.addFieldError("passwd", "密码只能由6-20个字母、数字、下划线组成");
		}
		// 检查该会员是否已经存在
		if (existsTitle(member.getCust_name(), "", "member", "cust_name")) {
			this.addFieldError("member.cust_name", "该会员已经存在,请重新输入");
		}
		// 限制Email只能注册一个帐号
		if (!ValidateUtil.isRequired(member.getEmail()) && existsTitle(member.getEmail(), "", "member", "email")) {
			this.addFieldError("member.email", "该邮箱已被占用，请重新输入");
		}
    	//验证所属分类是否有选择
		if (ValidateUtil.isRequired(cat_attr_str)) {
			this.addFieldError("cate_attr", "请选择分类");
		}
		//验证地区是选择
		validateAreaIfSelect();
		this.member.setArea_attr(area_attr);
		// 用于所在地区的结束
		this.member.setCat_attr(cat_attr_str);
		// 用于行业分类的结束
		if (member.getCust_type().equals("0")) {
			this.addFieldError("member.cust_type", "请选择企业类型");
		}
		//验证公司介绍是否填写
		if (ValidateUtil.isRequired(member.getCust_desc())) {
			this.addFieldError("member.cust_desc", "请填写公司介绍");
		}
		//公司介绍不得小于50个字符
		if(member.getCust_desc().length()<50)
		{
			this.addFieldError("member.cust_desc", "公司介绍不得小于50个字符");
		}
		//公司介绍不支持html语言
		String company_desc= ValidateUtil.delHTMLTag(member.getCust_desc());
		member.setCust_desc(company_desc);
		// 去掉经营模式空格
		if (ValidateUtil.isRequired(member.getClient_status())) {
			this.addFieldError("member.client_status", "请选择经营模式");
		} else {
			String clientName = member.getClient_status().replace(" ", "");
			member.setClient_status(clientName);
		}
		//othertype:注册币种类型
		if(member.getReg_money_type().equals("othertype")){
			member.setReg_money_type(other_money_type);
		}
		// 字段验证
		super.commonValidateField(member);
		if (super.ifvalidatepass) {
			return add();
		}
		String cust_id = this.memberService.insertMember(member);
		//初始化企业会员相关信息，3：代表企业普通会员
		member_related_info(cust_id,"3");
		this.addActionMessage("新增企业会员信息成功");
		return add();
	}
	
	

	/**
	 * 方法描述：跳转到新增个人会员页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addPerson() throws Exception {
		return goUrl("insertPerson");
	}

	/**
	 * 方法描述：新增个人会员
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insertPerson() throws Exception {
		// 用于所属地区的回选开始
		loadArea();
		if (ValidateUtil.isRequired(username)) {
			this.addFieldError("username", "用户名不能为空");
		} else {
			if (ValidateUtil.isAlphasFirst(username)) {
				this.addFieldError("username", "用户名以字母开头，由数字、字母、下划线组成");
			} else {
				String notallow_name = SysconfigFuc.getSysValue(CFG_MB_NOTALLOW);
				if (notallow_name.indexOf(username) > -1) {
					this.addFieldError("username", "本网站不允许注册此类用户名");
				} else {
					if (existsTitle(username, "", "memberuser", "user_name")) {
						this.addFieldError("username", "该用户名已经存在,请重新输入");
					}
				}
			}

		}
		if (ValidateUtil.isRequired(passwd)) {
			this.addFieldError("passwd", "密码不能为空");
		} else if (ValidateUtil.isAlphasLimtLength(passwd)) {
			this.addFieldError("passwd", "密码只能由6-20个字母、数字、下划线组成");
		}
		// 检查该会员是否已经存在
		if (existsTitle(member.getCust_name(), "", "member", "cust_name")) {
			this.addFieldError("member.cust_name", "该会员已经存在,请重新输入");
		} else {
			member.setContact_name(member.getCust_name());
		}
		// 限制Email只能注册一个帐号
		if (!ValidateUtil.isRequired(member.getEmail()) && existsTitle(member.getEmail(), "", "member", "email")) {
			this.addFieldError("member.email", "该邮箱已被占用，请重新输入");
		}
		//验证地区是选择
		validateAreaIfSelect();
		area_attr = area_attr.replace(" ", "");
		this.member.setArea_attr(area_attr);
		// 用于所在地区的结束
		// 字段验证
		super.commonValidateField(member);
		if (super.ifvalidatepass) {
			return goUrl("insertPerson");
		}
		// 插入一条会员信息后返回该会员的ID
		member.setIs_active("1");
		String cust_id = this.memberService.insertMember(member);
		//初始化个人会员相关信息，1：代表个人普通会员
		member_related_info(cust_id,"1");
		this.addActionMessage("新增个人会员信息成功");
		return goUrl("insertPerson");
	}

	/**
	 * 方法描述：
	 * 
	 * @return
	 * @throws Exception
	 */
	public void member_related_info(String cust_id,String level_code){
		// 获取刚插入的会员对象
		Member member = this.memberService.get(cust_id);
		// 初始化会员账号对象
		Memberuser memberuser = new Memberuser();
		memberuser.setCust_id(cust_id);
		//user_type 1：管理员 2：子账号
		memberuser.setUser_type("1");
		memberuser.setUser_name(username);
		passwd = Md5.getMD5(passwd.getBytes());
		memberuser.setPasswd(passwd);
		memberuser.setEmail(member.getEmail());
		memberuser.setCellphone(member.getContact_cellphone());
		memberuser.setPhone(member.getPhone());
		String user_id = memberuserService.insertMemberuser(memberuser);
		// 初始化会员级别信息对象
		Levelinfo levelinfo = new Levelinfo();
		levelinfo.setCust_id(cust_id);
		levelinfo.setLevel_code(level_code);// 初始化会员类型为个人的级别为1,1代表普通个人会员
		levelinfo.setStart_date(member.getIn_date());
		levelinfo.setEnd_date("2050-12-31");// 默认会员级别的到期时间
		levelinfo.setUser_id(user_id);
		this.levelinfoService.insert(levelinfo);
		// 初始化会员资金
		Memberfund memberfund = new Memberfund();
		memberfund.setCust_id(cust_id);
		memberfund.setFreeze_num("0");
		memberfund.setFund_num("0");
		memberfund.setUse_num("0");
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
		member = null;
	    levelinfo = null;
		memberfund = null;
		memberinter = null;
		memberuser = null;
		memberinter = null;
	}
	
	/**
	 * 方法描述：根据主键找出会员信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {		
		// 判断id的值是否符合条件，不符合的话返回到列表
		String rbtid = this.member.getCust_id();
		if (ValidateUtil.isDigital(rbtid)) {
			return list();
		}
		setCommpara();
		//注册币种回显
		if(!ValidateUtil.isRequired(member.getReg_money_type())){
			boolean flag=false;
			other_money_type=member.getReg_money_type();
			if(moneyList!= null && moneyList.size()>0){
				Map otherMap = new HashMap();
				for(int i=0;i<moneyList.size();i++){
					otherMap = (Map) moneyList.get(i);
					if(other_money_type.equals(otherMap.get("para_value").toString())){
						flag=true;
						break;
					}
				}
				other_money_type = "";
			}
			if(flag==false){
				other_money_type=member.getReg_money_type();
				member.setReg_money_type("othertype");
			}
		}
		String cust_id = "";
		// 如果操作者是会员，则从session中取得cust_id
		if (this.session_cust_type != null
				&& this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			cust_id = this.session_cust_id;
		} else {
			cust_id = this.member.getCust_id();
		}
		member = this.memberService.get(cust_id);
		// 获取会员类型标识
		setCusttypeTip(member.getMem_type());
		// 获取所属类型标识
		setClassattrTip(member.getMem_type());
		// 根据ID获取所属地区的串
		if (this.member.getArea_attr() != null) {
			backArea(member.getArea_attr());
		}
		// 判断member对象是否为空
		if (member.getCat_attr() != null && !member.getCat_attr().equals("")) {
			cat_attr_list(member.getCat_attr());
			// 将从数据库中查询的所属分类存入分类隐藏域中
			//backCategory(cate_attr);
		}
		// 复选框的回显
		if (member.getClient_status() != null) {
			String clientString = member.getClient_status().replace(" ", "");
			String[] client = clientString.split(",");
			clientList = new ArrayList();
			for (int i = 0; i < client.length; i++) {
				clientList.add(client[i].toString());
			}
		}
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：构造LIST用于信息的回选
	 * @return
	 * @throws Exception
	 */
	private void cat_attr_list(String cat_ids){
		String ids[]=cat_ids.split("\\|");
		cat_attr_list=new ArrayList();		
		for(int i=0;i<ids.length;i++){
			Map listMap=new HashMap();
			String id=ids[i].replace(" ","");
			listMap.put("id",id);
			String catName=CategoryFuc.getCateNameByMap(id);
			listMap.put("val", catName);
			if(!id.equals("")&&!catName.equals("")){
				cat_attr_list.add(i,listMap);
			}
		}
	}
	/**
	 * 方法描述：根据
	 * 
	 * @return
	 * @throws Exception
	 */
	public void setCommpara() {
		// 找出企业类型
		Map comMap = new HashMap();
		comMap.put("para_code", "cust_type");
		commparaList = this.commparaService.getList(comMap);
		// 找出企业经营模式
		comMap.put("para_code", "client_status");
		clientStrList = this.commparaService.getList(comMap);
		// 找出公司规模
		comMap.put("para_code", "staff_num");
		staffList = this.commparaService.getList(comMap);
		// 找出注册币种
		comMap.put("para_code", "money_type");
		moneyList = this.commparaService.getList(comMap);
		// 找出年销售额
		comMap.put("para_code", "year_sum");
		sumList = this.commparaService.getList(comMap);
	}

	/**
	 * 方法描述：修改会员信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		// 用于所属地区的回选开始
		loadArea();
		// 检查该会员是否已经存在
		if (existsTitle(member.getCust_name(), oldinfotitle, "member",
				"cust_name")) {
			this.addFieldError("member.cust_name", "会员名称不能重复");
		}
		if (!ValidateUtil.isRequired(member.getEmail()) && existsTitle(member.getEmail(), oldemail, "member", "email")) {
			this.addFieldError("member.email", "该邮箱已被占用，请重新输入");
		}
		//验证地区是选择
		validateAreaIfSelect();
		// 将处理后的所属地区串注入到member对象中
		this.member.setArea_attr(area_attr);
		// 用于所属地区的回选结束
		// 验证所属分类是否有选择
		if (ValidateUtil.isRequired(cat_attr_str)) {
			this.addFieldError("cate_attr", "请选择分类");
		}
		// 将处理后的所属分类串注入到member对象中
		this.member.setCat_attr(cat_attr_str);
		// 用于所属分类结束
		if (member.getCust_type().equals("0")) {
			this.addFieldError("member.cust_type", "请选择企业类型");
		}
		//验证公司介绍是否填写
		if (ValidateUtil.isRequired(member.getCust_desc())) {
			this.addFieldError("member.cust_desc", "请填写公司介绍");
		}
		//公司介绍不得小于50个字符
		if(member.getCust_desc().length()<50)
		{
			this.addFieldError("member.cust_desc", "公司介绍不得小于50个字符");
		}
		//公司介绍不支持html语言
		String company_desc= ValidateUtil.delHTMLTag(member.getCust_desc());
		member.setCust_desc(company_desc);
		// 去掉经营模式空格
		if (ValidateUtil.isRequired(member.getClient_status())) {
			this.addFieldError("member.client_status", "请选择经营模式");
		} else {
			String clientName = this.member.getClient_status().replace(" ", "");
			this.member.setClient_status(clientName);
		}
		//othertype:注册币种类型
		if(member.getReg_money_type().equals("othertype")){
			member.setReg_money_type(other_money_type);
		}
		// 字段验证
		super.commonValidateField(member);
		if (super.ifvalidatepass) {
			return view();
		}
		this.memberService.update(member);
		this.addActionMessage("修改会员信息成功");
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			return view();
		} else {
			return list();
		}
	}

	/**
	 * 方法描述：删除会员信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.member.getCust_id();
		id = id.replace(" ", "");
		if (id!=null&&!"".equals(id)) {
			id = id.replace(" ", "");
			String[] ids=id.split(",");
			DoHtml dohtml=new DoHtml();
			for(int i =0;i<ids.length;i++){
				//获取当前ID的对象
				Member member=this.memberService.get(ids[i]);
				dohtml.delArticeHtml(modType,ids[i],member.getIn_date());
				this.memberService.delete(ids[i]);
			}
			this.addActionMessage("删除会员信息成功");	
		}
		return list();
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		// 找出企业类型信息
		Map custtypeMap = new HashMap();
		custtypeMap.put("para_code", "cust_type");
		commparaList = this.commparaService.getList(custtypeMap);
		// 找出会员级别信息
		Map levelMap = new HashMap();
		levelList = this.memberlevelService.getList(levelMap);
		// 根据条件搜索
		Map pageMap = new HashMap();
		if (today != null && !today.equals("")) {
			pageMap.put("today", this.today);
		} else {
			pageMap.put("infostate", "1,3");
		}
		if (cust_name_s != null && !cust_name_s.equals("")) {
			pageMap.put("cust_name", cust_name_s);
		}
		if (mem_type_s != null && !mem_type_s.equals("")) {
			pageMap.put("mem_type", mem_type_s);
		}
		if (level_s != null && !level_s.equals("")) {
			pageMap.put("level_id", level_s);
		}
		if (email_s != null && !email_s.equals("")) {
			pageMap.put("email", email_s);
		}
		// 获取搜索的所在地区
		if (area_attr_s != null && !area_attr_s.equals("")) {
			pageMap.put("area_attr", area_attr_s);
		}
		// 获取搜索的所属分类
		if (cat_attr_s != null && !cat_attr_s.equals("")) {
			pageMap.put("class_attr", cat_attr_s);
		}
		if (in_date_s != null && !in_date_s.equals("")) {
			pageMap.put("in_date", in_date_s);
		}
		if (end_date_s != null && !end_date_s.equals("")) {
			pageMap.put("end_date", end_date_s);
		}
		if (state_code_s != null && !state_code_s.equals("")) {
			pageMap.put("info_state", state_code_s);
		}
		if (recommend_s != null && !recommend_s.equals("")) {
			pageMap.put("recommend", recommend_s);
		}
		// 过滤地区
		pageMap = super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.memberService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		memberList = this.memberService.getList(pageMap);
		// 地区代码转换成中文字符
		memberList = CategoryFuc.replaceList(memberList, "");
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String auditList() throws Exception {
		Map pageMap = new HashMap();
		pageMap.put("infostate", "0,2");
		if (cust_name_s != null && !cust_name_s.equals("")) {
			pageMap.put("cust_name", cust_name_s);
		}
		if (mem_type_s != null && !mem_type_s.equals("")) {
			pageMap.put("mem_type", mem_type_s);
		}
		// 获取搜索的所在地区
		if (area_attr_s != null && !area_attr_s.equals("")) {
			pageMap.put("area_attr", area_attr_s);
		}
		// 获取搜索的所属分类
		if (cat_attr_s != null && !cat_attr_s.equals("")) {
			pageMap.put("class_attr", cat_attr_s);
		}
		if (email_s != null && !email_s.equals("")) {
			pageMap.put("email", email_s);
		}
		if (in_date_s != null && !in_date_s.equals("")) {
			pageMap.put("in_date", in_date_s);
		}
		if (end_date_s != null && !end_date_s.equals("")) {
			pageMap.put("end_date", end_date_s);
		}
		if (state_code_s != null && !state_code_s.equals("")) {
			pageMap.put("info_state", state_code_s);
		}
		if (info_state_s != null && !info_state_s.equals("")) {
			pageMap.put("info_state", info_state_s);
		}
		// 根据页面提交的条件找出信息总数
		int count = this.memberService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		memberList = this.memberService.getList(pageMap);
		// 地区代码转换成中文字符
		memberList = CategoryFuc.replaceList(memberList, "");
		return goUrl(AUDITLIST);
	}

	/**
	 * 方法描述：更新联系人信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateContactInfo() throws Exception {
		if (!ValidateUtil.isRequired(member.getEmail())
				&& existsTitle(member.getEmail(), oldemail, "member", "email")) {
			this.addFieldError("member.email", "该邮箱已被占用，请重新输入");
		}
		// 字段验证
		super.commonValidateField(member);
		if (super.ifvalidatepass) {
			return view();
		}
		this.memberService.updateContactInfo(member);
		this.addActionMessage("修改联系人信息成功");
		return view();
	}

	/**
	 * 方法描述：跳转到个人信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String viewPerson() throws Exception {
		String cust_id = "";
		// 如果操作者是会员，则从session中取得cust_id
		if (this.session_cust_type != null && this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			cust_id = this.session_cust_id;
		} else {
			cust_id = this.member.getCust_id();
		}
		member = this.memberService.get(cust_id);
		// 根据ID获取所属地区的串
		if (this.member.getArea_attr() != null) {
			backArea(member.getArea_attr());
		}
		return goUrl("updatePerson");
	}

	/**
	 * 方法描述：更新个人信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updatePersonInfo() throws Exception {
		loadArea();
		// 检查该会员是否已经存在
		if (existsTitle(member.getCust_name(), oldinfotitle, "member",
				"cust_name")) {
			this.addFieldError("member.cust_name", "会员名称不能重复");
		}
		//验证地区是选择
		validateAreaIfSelect();
		area_attr = area_attr.replace(" ", "");
		// 将处理后的所属地区串注入到member对象中
		this.member.setArea_attr(area_attr);
		if (!ValidateUtil.isRequired(member.getEmail()) && existsTitle(member.getEmail(), oldemail, "member", "email")) {
			this.addFieldError("member.email", "该邮箱已被占用，请重新输入");
		}
		// 字段验证
		super.commonValidateField(member);
		if (super.ifvalidatepass) {
			return viewPerson();
		}
		this.memberService.update(member);
		this.addActionMessage("修改个人信息成功");
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			return viewPerson();
		} else {
			return list();
		}
	}

	/**
	 * 方法描述：根据主键找出会员信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String audit() throws Exception {
		// 判断id的值是否符合条件，不符合的话返回到列表
		String rbtid = this.member.getCust_id();
		if (ValidateUtil.isDigital(rbtid)) {
			return auditList();
		}
		//进入审核页面时候的加载方法
		auditView("company",member.getCust_id());
		// 获取企业类型的中文字符
		if (member.getCust_type() != null) {
			String cust_type = CommparaFuc.get_commparakey_By_value(member
					.getCust_type(), "cust_type");
			member.setCust_type(cust_type);
		}
		// 找出企业经营模式的中文字符
		if (member.getClient_status() != null) {
			String client_status = MemberFuc.getStatusName(member
					.getClient_status().replace(" ", ""));
			member.setClient_status(client_status);
		}
		// 获取地区名称
		String area_name = "";
		if (member.getArea_attr() != null) {
			area_name = AreaFuc.getAreaNameByMap(member.getArea_attr());
		}
		member.setArea_attr(area_name);
		// 获取分类名称
		String cat_name = "";
		if (member.getCat_attr() != null) {
			cat_name = CategoryFuc.getCateNameByMap(member.getCat_attr());
		}
		member.setCat_attr(cat_name);
		setStatecodeTip(member.getInfo_state());
		return goUrl(AUDIT);
	}

	/**
	 * @function 功能 审核会员
	 * @author 创建人 邱景岩
	 * @date 创建日期 Jul 19, 2011 2:08:39 PM
	 */
	public String auditstate() throws Exception {
		
		//启用审核流程操作
		//判断当前操作是插入审核历史表信息，还是改变主信息表的审核状态
		if(auditInfoState("company",member.getInfo_state()).equals("1")){
			this.addActionMessage("审核信息成功");
			return audit();
		}
		if (ValidateUtil.isRequired(member.getInfo_state())) {
			this.addFieldError("member.state_code", "请选择审核状态");
			return audit();
		}
		if (member.getInfo_state().equals("2")
				&& ValidateUtil.isRequired(member.getReason())) {
			this.addFieldError("member.reason", "请输入拒绝理由");
			setStatecodeTip("2");
			return audit();
		}
		Map stateMap = new HashMap();
		// 判断审核状态是否为未审核
		if (!"2".equals(member.getInfo_state())) {
			stateMap.put("reason", "");
		} else {
			stateMap.put("reason", member.getReason());
		}
		stateMap.put("cust_id", member.getCust_id());
		stateMap.put("info_state", member.getInfo_state());
		this.memberService.updateMemberState(stateMap);
		this.addActionMessage("已审核会员");
		return auditList();
	}
	
	/**
	 * 方法描述：跳转到审核页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String auditPerson() throws Exception {
		
		//进入审核页面时候的加载方法
		auditView("company",member.getCust_id());
		
		// 判断id的值是否符合条件，不符合的话返回到列表
		String rbtid = this.member.getCust_id();
		if (ValidateUtil.isDigital(rbtid)) {
			return auditList();
		}
		// 获取地区名称
		String area_name = "";
		if (member.getArea_attr() != null) {
			area_name = AreaFuc.getAreaNameByMap(member.getArea_attr());
		}
		member.setArea_attr(area_name);
		setStatecodeTip(member.getInfo_state());
		return goUrl("auditPerson");
	}
	
	
	
	
	
	
	/**
	 * 方法描述：审核个人会员
	 * 
	 * @return
	 * @throws Exception
	 */
	public String auditPersonState() throws Exception {
		
		//启用审核流程操作
		//判断当前操作是插入审核历史表信息，还是改变主信息表的审核状态
		if(auditInfoState("company",member.getInfo_state()).equals("1")){
			this.addActionMessage("审核信息成功");
			return audit();
		}
		
		if (ValidateUtil.isRequired(member.getInfo_state())) {
			this.addFieldError("member.state_code", "请选择审核状态");
			return audit();
		}
		if ("2".equals(member.getInfo_state()) && ValidateUtil.isRequired(member.getReason())) {
			this.addFieldError("member.reason", "请输入拒绝理由");
			setStatecodeTip("2");
			return audit();
		}
		Map stateMap = new HashMap();
		// 判断审核状态是否为未审核
		if (!"2".equals(member.getInfo_state())) {
			stateMap.put("reason", "");
		} else {
			stateMap.put("reason", member.getReason());
		}
		stateMap.put("cust_id", member.getCust_id());
		stateMap.put("info_state", member.getInfo_state());
		this.memberService.updateMemberState(stateMap);
		this.addActionMessage("已审核个人会员");
		return auditList();
	}

	/**
	 * 方法描述：是否推荐批量修改
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateisrecom() throws Exception {
		String custid = this.member.getCust_id();
		String isrecom = this.member.getRecommend();
		custid = custid.replace(" ", "");
		String memberStr[] = custid.split(",");
		List memList = new ArrayList();
		if (memberStr.length > 0) {
			for (int i = 0; i < memberStr.length; i++) {
				Map memberMap = new HashMap();
				memberMap.put("recommend", isrecom);
				memberMap.put("cust_id", memberStr[i]);
				memList.add(memberMap);
			}
		}
		this.memberService.updateIsrecom(memList);
		String tip = "";
		if (isrecom.equals("0")) {
			tip = "取消推荐成功";
		} else if (isrecom.equals("1")) {
			tip = "推荐成功";
		}
		this.addActionMessage(tip);
		return list();
	}

	/**
	 * @author : 林俊钦
	 * @throws Exception 
	 * @date : Sep 7, 2012 4:02:55 PM
	 * @Method Description : 审核会员列表删除会员
	 */
	public String checkDel() throws Exception{
		String id = this.member.getCust_id();
		id = id.replace(" ", "");
		if (id!=null&&!"".equals(id)) {
			id = id.replace(" ", "");
			String[] ids=id.split(",");
			DoHtml dohtml=new DoHtml();
			for(int i =0;i<ids.length;i++){
				//获取当前ID的对象
				Member member=this.memberService.get(ids[i]);
				dohtml.delArticeHtml(modType,ids[i],member.getIn_date());
				this.memberService.delete(ids[i]);
			}
			this.addActionMessage("删除会员信息成功");	
		}
		return auditList();
	}
	
	/**
	 * 查看公司或者个人信息
	 * @return
	 * @throws Exception
	 */
	public String viewinfo() throws Exception{
		String cust_id = member.getCust_id();
		String mem_type = "";
		if(cust_id != null){
			member = memberService.get(cust_id);
			mem_type = member.getMem_type();
			// 获取企业类型的中文字符
			if (member.getCust_type() != null) {
				String cust_type = CommparaFuc.get_commparakey_By_value(member
						.getCust_type(), "cust_type");
				member.setCust_type(cust_type);
			}
			// 找出企业经营模式的中文字符
			if (member.getClient_status() != null) {
				String client_status = MemberFuc.getStatusName(member
						.getClient_status().replace(" ", ""));
				member.setClient_status(client_status);
			}
			// 获取地区名称
			String area_name = "";
			if (member.getArea_attr() != null) {
				area_name = AreaFuc.getAreaNameByMap(member.getArea_attr());
			}
			member.setArea_attr(area_name);
			levelinfo = levelinfoService.get(cust_id);
			memberinter = memberinterService.get(cust_id);
			memberfund =  memberfundService.get(cust_id);
			membercredit = membercreditService.get(cust_id);
		}
		if(mem_type != null && mem_type.equals("0")){
			return goUrl("companyinfo");
		}else{
			return goUrl("personinfo");
		}
	}
	
	/**
	 * 方法描述：修改会员头像页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateMemberPhoto() throws Exception {
		member = memberService.get(this.session_cust_id);
		logo_path=member.getLogo_path();
		return goUrl("updateMemberPhoto");
	}
	/**
	 * 方法描述：修改会员头像
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updatePhoto() throws Exception {
		member = memberService.get(this.session_cust_id);
		if(member==null){
			member=new Member();
		}
		if ("".equals(logo_path)|| logo_path == null) {
			this.addFieldError("logo_path", "请上传头像");
			return goUrl("updateMemberPhoto");
		}
		member.setLogo_path(logo_path);
		//字段验证
		super.commonValidateField(member);
		
		memberService.update(member);
		this.addActionMessage("头像修改成功");
		return goUrl("updateMemberPhoto");
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
	 * @return the client
	 */
	public String[] getClient() {
		return client;
	}

	/**
	 * @param client
	 *            the client to set
	 */
	public void setClient(String[] client) {
		this.client = client;
	}

	/**
	 * @return the clientStrList
	 */
	public List getClientStrList() {
		return clientStrList;
	}

	/**
	 * @param clientStrList
	 *            the clientStrList to set
	 */
	public void setClientStrList(List clientStrList) {
		this.clientStrList = clientStrList;
	}

	/**
	 * @return the cust_name_s
	 */
	public String getCust_name_s() {
		return cust_name_s;
	}

	/**
	 * @param cust_name_s
	 *            the cust_name_s to set
	 */
	public void setCust_name_s(String cust_name_s) {
		this.cust_name_s = cust_name_s;
	}

	/**
	 * @return the clientstateall
	 */
	public String getClientstateall() {
		return clientstateall;
	}

	/**
	 * @param clientstateall
	 *            the clientstateall to set
	 */
	public void setClientstateall(String clientstateall) {
		this.clientstateall = clientstateall;
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
	 * @return the email_s
	 */
	public String getEmail_s() {
		return email_s;
	}

	/**
	 * @param email_s
	 *            the email_s to set
	 */
	public void setEmail_s(String email_s) {
		this.email_s = email_s;
	}

	/**
	 * @return the in_date_s
	 */
	public String getIn_date_s() {
		return in_date_s;
	}

	/**
	 * @param in_date_s
	 *            the in_date_s to set
	 */
	public void setIn_date_s(String in_date_s) {
		this.in_date_s = in_date_s;
	}

	/**
	 * @return the end_date_s
	 */
	public String getEnd_date_s() {
		return end_date_s;
	}

	/**
	 * @param end_date_s
	 *            the end_date_s to set
	 */
	public void setEnd_date_s(String end_date_s) {
		this.end_date_s = end_date_s;
	}

	/**
	 * @return the state_code_s
	 */
	public String getState_code_s() {
		return state_code_s;
	}

	/**
	 * @param state_code_s
	 *            the state_code_s to set
	 */
	public void setState_code_s(String state_code_s) {
		this.state_code_s = state_code_s;
	}

	/**
	 * @return the recommend_s
	 */
	public String getRecommend_s() {
		return recommend_s;
	}

	/**
	 * @param recommend_s
	 *            the recommend_s to set
	 */
	public void setRecommend_s(String recommend_s) {
		this.recommend_s = recommend_s;
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
	 * @return the custtypeTip
	 */
	public String getCusttypeTip() {
		return custtypeTip;
	}

	/**
	 * @param custtypeTip
	 *            the custtypeTip to set
	 */
	public void setCusttypeTip(String custtypeTip) {
		this.custtypeTip = custtypeTip;
	}

	/**
	 * @return the classattrTip
	 */
	public String getClassattrTip() {
		return classattrTip;
	}

	/**
	 * @param classattrTip
	 *            the classattrTip to set
	 */
	public void setClassattrTip(String classattrTip) {
		this.classattrTip = classattrTip;
	}

	/**
	 * @return the area_attr_s
	 */
	public String getArea_attr_s() {
		return area_attr_s;
	}

	/**
	 * @param area_attr_s
	 *            the area_attr_s to set
	 */
	public void setArea_attr_s(String area_attr_s) {
		this.area_attr_s = area_attr_s;
	}

	/**
	 * @return the today
	 */
	public String getToday() {
		return today;
	}

	/**
	 * @param today
	 *            the today to set
	 */
	public void setToday(String today) {
		this.today = today;
	}

	/**
	 * @return the cat_attr_s
	 */
	public String getCat_attr_s() {
		return cat_attr_s;
	}

	/**
	 * @param cat_attr_s
	 *            the cat_attr_s to set
	 */
	public void setCat_attr_s(String cat_attr_s) {
		this.cat_attr_s = cat_attr_s;
	}

	/**
	 * @return the statecodeTip
	 */
	public String getStatecodeTip() {
		return statecodeTip;
	}

	/**
	 * @param statecodeTip
	 *            the statecodeTip to set
	 */
	public void setStatecodeTip(String statecodeTip) {
		this.statecodeTip = statecodeTip;
	}

	public List getLevelList() {
		return levelList;
	}

	public void setLevelList(List levelList) {
		this.levelList = levelList;
	}

	public String getLevel_s() {
		return level_s;
	}

	public void setLevel_s(String level_s) {
		this.level_s = level_s;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getOldemail() {
		return oldemail;
	}

	public void setOldemail(String oldemail) {
		this.oldemail = oldemail;
	}

	public List getStaffList() {
		return staffList;
	}

	public void setStaffList(List staffList) {
		this.staffList = staffList;
	}

	public List getMoneyList() {
		return moneyList;
	}

	public void setMoneyList(List moneyList) {
		this.moneyList = moneyList;
	}

	public List getSumList() {
		return sumList;
	}

	public void setSumList(List sumList) {
		this.sumList = sumList;
	}

	public List getCat_attr_list() {
		return cat_attr_list;
	}

	public void setCat_attr_list(List cat_attr_list) {
		this.cat_attr_list = cat_attr_list;
	}

	public String getCat_attr_str() {
		return cat_attr_str;
	}

	public void setCat_attr_str(String cat_attr_str) {
		this.cat_attr_str = cat_attr_str;
	}
	
	public List getClientList() {
		return clientList;
	}

	public void setClientList(List clientList) {
		this.clientList = clientList;
	}

	public String getOther_money_type() {
		return other_money_type;
	}

	public void setOther_money_type(String other_money_type) {
		this.other_money_type = other_money_type;
	}

	/**
	 * @Method Description : 当进入方法后初始化对象
	 * @author : 林俊钦
	 * @date : Nov 8, 2011 2:36:50 PM
	 */
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (member == null) {
			member = new Member();
		}
		// 运营商后台与会员的后台来实例化不同的cust_id
		String cust_id = "";
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			cust_id = member.getCust_id();
		} else {
			cust_id = this.session_cust_id;
		}
		if (!ValidateUtil.isDigital(cust_id)) {
			member = this.memberService.get(cust_id);
		}
	}

	public String getLogo_path() {
		return logo_path;
	}

	public void setLogo_path(String logo_path) {
		this.logo_path = logo_path;
	}


}
