/*
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: CorpomemberAction.java 
 */
package com.rbt.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.Md5;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.CategoryFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Levelinfo;
import com.rbt.model.Member;
import com.rbt.model.Membercredit;
import com.rbt.model.Memberfund;
import com.rbt.model.Memberinter;
import com.rbt.model.Memberuser;
import com.rbt.service.ICategoryService;
import com.rbt.service.ICommparaService;
import com.rbt.service.ILevelinfoService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMembercreditService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IMemberinterService;
import com.rbt.service.IMemberlevelService;
import com.rbt.service.IMemberuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 企业会员action类
 * @author 创建人 邱景岩
 * @date 创建日期 Jul 20, 2011 11:27:52 AM
 */
@Controller
public class CorpomemberAction extends BaseAction implements Preparable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 3808349971223579516L;
	/*
	 * 会员类型：
	 */
	private static final String MEM_TYPE_DEFAULT_VALUE = "0";
	/*
	 * 系统参数设置默认变量名 cfg_mb_notallow：不允许注册的登陆名 cfg_md_mailtest：是否限制Email只能注册一个帐号
	 */
	private static final String CFG_MB_NOTALLOW = "cfg_mb_notallow";
	
	public String unique="";
	/*
	 * 搜索字段 cust_name_s：企业会员名称 cust_type_s：企业类型 cust_rage_s：企业类别 email_s：电子邮箱
	 * in_date_s：最小注册日期 end_date_s:最大注册日期 recommend_s：是否推荐 state_code_s：企业状态
	 * search_cat_attr:搜索分类字段
	 */
	public String cust_name_s;
	public String cust_type_s;
	public String cust_rage_s;
	public String email_s;
	public String in_date_s;
	public String end_date_s;
	public String recommend_s;
	public String state_code_s;
	public String area_attr_s;
	public String cat_attr_s;
	public String level_s;
	public String username;
	public String passwd;
	public String oldemail;
	public String cat_attr_str; 
	
	public String other_money_type;

	/*
	 * 会员对象
	 */
	public Member member;
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
	 * 注册币种
	 */
	public List moneyList;
	/*
	 * 年销售额
	 */
	public List sumList;
	/*
	 * 会员级别信息集合
	 */
	public List levelList;
	/*
	 * 多分类信息集合
	 */
	public List cat_attr_list;
	/*
	 * 用于回选经营模式属性的绑定
	 */
	public List clientStrList;
	/*
	 * 回显经营模式选中的值集合
	 */
	public List clientList;
	/*
	 * 获取自定义属性的字符串
	 */
	public String[] client;

	/**
	 * 方法描述：返回新增会员页面
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
		// 用于所属分类的回选开始
		//loadCategory();
		cat_attr_list(cat_attr_str);
		// 用于所属地区的回选开始
		loadArea();
		// 检查该会员是否已经存在
		if (existsTitle(member.getCust_name(),"","member","cust_name")) {
			this.addFieldError("member.cust_name", "该会员已经存在,请重新输入");
		}
		if (ValidateUtil.isRequired(username)) {
			this.addFieldError("username", "用户名不能为空");
		} else {
			if(ValidateUtil.isAlphasFirst(username)){
				this.addFieldError("username", "用户名以字母开头，由数字、字母、下划线组成");
			}else{
				String notallow_name = "";
				notallow_name = SysconfigFuc.getSysValue(CFG_MB_NOTALLOW);
				if (notallow_name.indexOf(username) > -1) {
					this.addFieldError("username", "本网站不允许注册此类用户名");
				} else {
					if (existsTitle(username,"","memberuser","user_name")) {
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
		
		if(!ValidateUtil.isRequired(member.getEmail()) && existsTitle(member.getEmail(),"","member","email")){
			this.addFieldError("member.email", "该邮箱已被占用，请重新输入");
		}
		//验证分类是选择
		if (ValidateUtil.isRequired(cat_attr_str)) {
			this.addFieldError("cate_attr", "请选择分类");
		}
		//验证地区是选择
		validateAreaIfSelect();
		this.member.setArea_attr(area_attr);
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
		//去掉经营模式空格
		if (ValidateUtil.isRequired(member.getClient_status())) {
			this.addFieldError("member.client_status", "请选择经营模式");
		}else{
			String clientName = this.member.getClient_status().replace(" ", "");
	        member.setClient_status(clientName);
		}
		// 设置会员类型默认为企业会员
		member.setMem_type(MEM_TYPE_DEFAULT_VALUE);
		//othertype:注册币种类型
		if(member.getReg_money_type().equals("othertype")){
			member.setReg_money_type(other_money_type);
		}
		//字段验证
		super.commonValidateField(member);
		if(super.ifvalidatepass){
			return add();
		}
		// 插入一条会员信息后返回该会员的ID
		String cust_id = this.memberService.insertMember(member);
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
		levelinfo.setLevel_code("3");// 初始化会员类型为个人的级别为3,1代表企业普通会员
		levelinfo.setStart_date(member.getIn_date());
		levelinfo.setEnd_date("2050-12-31");
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
		membercredit = null;
		memberuser = null;
		this.addActionMessage("新增企业会员信息成功");
		return add();
	}

	/**
	 * 方法描述：修改会员信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		//判断修改页面提交，未通过过滤时，清除其他内容
		unique="view";
		// 用于所属分类的回选开始
		//loadCategory();
		// 用于所属地区的回选开始
		loadArea();
		// 检查该会员是否已经存在
		if (existsTitle(member.getCust_name(), oldinfotitle, "member", "cust_name")) {
			this.addFieldError("member.cust_name", "企业名称不能重复");
		}
		if(!ValidateUtil.isRequired(member.getEmail())){
			if (existsTitle(member.getEmail(),oldemail,"member","email")) {
				this.addFieldError("member.email", "该邮箱已被占用，请重新输入");
			}
		}
		//验证所属地区是否有选择
		validateAreaIfSelect();
		this.member.setArea_attr(area_attr);
		//验证所属分类是否有选择
		if (ValidateUtil.isRequired(cat_attr_str)) {
			this.addFieldError("cate_attr", "请选择分类");
		}
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
		//去掉经营模式空格
		if (ValidateUtil.isRequired(member.getClient_status())) {
			this.addFieldError("member.client_status", "请选择经营模式");
		}else{
			String clientName = this.member.getClient_status().replace(" ", "");
	        this.member.setClient_status(clientName);
		}
		// 设置会员类型默认为企业会员
		member.setMem_type(MEM_TYPE_DEFAULT_VALUE);
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			member.setInfo_state("0");
		}
		//othertype:注册币种类型
		if(member.getReg_money_type().equals("othertype")){
			member.setReg_money_type(other_money_type);
		}
		super.commonValidateField(member);
		if(super.ifvalidatepass){
			return view();
		}
		this.memberService.update(member);
		this.addActionMessage("修改企业会员信息成功");
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			return view();
		} else {
			return list();
		}
	}

	/**
	 * 方法描述：删除企业会员信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.member.getCust_id();
		id = id.replace(" ", "");
		this.memberService.deleteRelate(id);
		this.addActionMessage("删除企业会员信息成功");
		return list();
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		// 获得企业类型
		Map custtypeMap = new HashMap();
		custtypeMap.put("para_code", "cust_type");
		commparaList = this.commparaService.getList(custtypeMap);
		//找出会员级别信息
		Map levelMap = new HashMap();
		levelMap.put("start", "0");
		levelMap.put("limit", "2");
		levelList = this.memberlevelService.getList(levelMap);
		// 根据条件搜索数据
		Map pageMap = new HashMap();
		pageMap.put("infostate", "1,3");
		if (cust_name_s != null && !cust_name_s.equals("")) {
			pageMap.put("cust_name", cust_name_s);
		}
		if (level_s != null && !level_s.equals("")) {
			pageMap.put("level_id", level_s);
		}
		if (cust_type_s != null && !cust_type_s.equals("")) {
			pageMap.put("cust_type", cust_type_s);
		}
		if (cust_rage_s != null && !cust_rage_s.equals("")) {
			pageMap.put("cust_rage", cust_rage_s);
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
		if (recommend_s != null && !recommend_s.equals("")) {
			pageMap.put("recommend", recommend_s);
		}
		if (state_code_s != null && !state_code_s.equals("")) {
			pageMap.put("info_state", state_code_s);
		}
		pageMap.put("mem_type", MEM_TYPE_DEFAULT_VALUE);
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.memberService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count, pageMap);
		memberList = this.memberService.getList(pageMap);
		// 分类代码转换成中文字符
		memberList = CategoryFuc.replaceList(memberList, "");
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出会员信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		setCommpara();
		String cust_id = "";
		// 如果操作者是会员，则从session中取得cust_id
		if (this.session_cust_type != null && this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			cust_id = this.session_cust_id;
		}else {
			cust_id = this.member.getCust_id();
		}
		if (cust_id == null || cust_id == "") {
			return list();
		}
		//判断修改页面提交，未通过过滤时，清除其他内容
		if("".equals(unique)){
			member = this.memberService.get(cust_id);
		}
		if (member != null) {
			// 将从数据库中查询的所属分类存入分类隐藏域中
			//backCategory(member.getCat_attr());	
			// 找出地区字段的存入隐藏值中
			backArea(member.getArea_attr());			
			// 多分类的选择
			if(member.getCat_attr()!=null && !"".equals(member.getCat_attr())){
				cat_attr_list(member.getCat_attr());
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
		}	
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
	 * 方法描述：根据参数所属类型找出集合信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void setCommpara(){
		//找出企业类型
		Map comMap = new HashMap();
		comMap.put("para_code", "cust_type");
		commparaList = this.commparaService.getList(comMap);
		//找出企业经营模式
		comMap.put("para_code", "client_status");
		clientStrList = this.commparaService.getList(comMap);
		//找出公司规模
		comMap.put("para_code", "staff_num");
		staffList = this.commparaService.getList(comMap);
		//找出注册币种
		comMap.put("para_code", "money_type");
		moneyList = this.commparaService.getList(comMap);
		//找出年销售额
		comMap.put("para_code", "year_sum");
		sumList = this.commparaService.getList(comMap);
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
	 * @return the memberList
	 */
	public List getmemberList() {
		return memberList;
	}

	/**
	 * @param memberList
	 *            the memberList to set
	 */
	public void setmemberList(List memberList) {
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
	 * @return the cust_type_s
	 */
	public String getCust_type_s() {
		return cust_type_s;
	}

	/**
	 * @param cust_type_s
	 *            the cust_type_s to set
	 */
	public void setCust_type_s(String cust_type_s) {
		this.cust_type_s = cust_type_s;
	}

	/**
	 * @return the cust_rage_s
	 */
	public String getCust_rage_s() {
		return cust_rage_s;
	}

	/**
	 * @param cust_rage_s
	 *            the cust_rage_s to set
	 */
	public void setCust_rage_s(String cust_rage_s) {
		this.cust_rage_s = cust_rage_s;
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

	public String getArea_attr_s() {
		return area_attr_s;
	}

	public void setArea_attr_s(String area_attr_s) {
		this.area_attr_s = area_attr_s;
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
	 * @return the member
	 */
	public Member getmember() {
		return member;
	}

	/**
	 * @param member
	 *            the member to set
	 */
	public void setmember(Member member) {
		this.member = member;
	}

	public String getLevel_s() {
		return level_s;
	}

	public void setLevel_s(String level_s) {
		this.level_s = level_s;
	}

	public List getLevelList() {
		return levelList;
	}

	public void setLevelList(List levelList) {
		this.levelList = levelList;
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

	public void prepare() throws Exception { super.saveRequestParameter();
		if (member == null) {
			member = new Member();
		}
		String id = member.getCust_id();
		if (!ValidateUtil.isDigital(id)) {
			member = this.memberService.get(id);
		}
	}

}
