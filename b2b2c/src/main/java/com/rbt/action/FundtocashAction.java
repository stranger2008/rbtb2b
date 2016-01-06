/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: FundtocashAction.java 
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.CategoryFuc;
import com.rbt.model.Fundtocash;
import com.rbt.model.Memberuser;
import com.rbt.model.Sysuser;
import com.rbt.service.ICommparaService;
import com.rbt.service.IFundtocashService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.ISysuserService;

/**
 * @function 功能 会员资金提现记录action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Wed Jul 13 09:52:27 CST 2011
 */
@Controller
public class FundtocashAction extends BaseAction implements Preparable{

	private static final long serialVersionUID = 1L;
	/*
	 * 用户账号对象
	 */
	public Memberuser memberuser;
	/*
	 * 管理员对象
	 */
	public Sysuser sysuser;
	/*
	 * 会员资金提现记录对象
	 */
	public Fundtocash fundtocash;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IMemberuserService memberuserService;
	@Autowired
	public IFundtocashService fundtocashService;
	@Autowired
	public ISysuserService sysuserService;
	@Autowired
	public IMemberService memberService;
	@Autowired
	public ICommparaService commparaService;
	
	/*
	 * 会员资金提现记录信息集合
	 */
	public List fundtocashList;
	/*
	 * 参照表
	 */
	public List commparaList_value;
	/*
	 * 参照表
	 */
	public List commparaList;
	/*
	 * 搜索参数
	 */
	public String order_state_s;
	public String getcash_type_s;
	public String cust_name;
	public String cuts_name_s;
	public String getcash_type;
	public String order_state;
	public String starttime_s;
	public String endtime_s;
	
	/*
	 * 收款方式参数
	 */
	public String sgetcash_type;
	/*
	 * 申请人
	 */
	public String scust_name;
	/*
	 * 管理员名称
	 */
	public String username;
	

	

	/**
	 * 方法描述：返回新增会员资金提现记录页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		// 绑定收款方式下拉列表
		getcommpara();
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增会员资金提现记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {

		if ("0".equals(this.fundtocash.getGetcash_type())) {
			this.addFieldError("fundtocash.getcash_type", "付款方式不能为空");
		}
		if (!"2".equals(this.fundtocash.getGetcash_type())){
			if(ValidateUtil.isRequired(this.fundtocash.getAccount()) || ValidateUtil.isRequired(this.fundtocash.getAccount())) {
			this.addFieldError("fundtocash.account", "不能为空且只能输入数字");
			}
			if(ValidateUtil.isRequired(this.fundtocash.getAccount_name())){
			this.addFieldError("fundtocash.account_name", "账号名字不能为空");
			}
		}
		Map pageMap = new HashMap();
		pageMap.put("cust_name", cust_name);
		
		//判断当前登录的账号类型，执行相应的操作
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			List custList = this.memberService.getList(pageMap);
			if (custList == null || custList.size() <= 0) {
				this.addFieldError("cust_name", "用户名不存在,请重新输入");
				return add();
			}
			Map cust_value = (HashMap)custList.get(0);
			fundtocash.setCust_id(cust_value.get("cust_id").toString());
		} else {
			fundtocash.setCust_id(this.session_cust_id);
		}

		fundtocash.setUser_id(this.session_user_id);
		fundtocash.setOrder_state("0");
		fundtocash.setRemark("资金提现");
		
		//字段验证
		super.commonValidateField(fundtocash);
		if(super.ifvalidatepass){
			return add();
		}
		this.fundtocashService.insert(fundtocash);
		this.addActionMessage("新增资金提现成功");
		this.fundtocash = null;
		return add();
	}

	/**
	 * 方法描述：修改会员资金提现记录信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if (sgetcash_type != null && !sgetcash_type.equals("")) {
			Map pageMap_vlaue = new HashMap();
			pageMap_vlaue.put("para_key", sgetcash_type);
			commparaList_value = this.commparaService.getList(pageMap_vlaue);
			if (commparaList_value != null && commparaList_value.size() > 0) {
				HashMap valueMap =  (HashMap) commparaList_value.get(0);
				String module_type_v = valueMap.get("para_value").toString();
				fundtocash.setGetcash_type(module_type_v);
			}
		}

		this.fundtocash.setUser_id((String) getSession().getAttribute(
				Constants.USER_ID));
		
		//字段验证
		super.commonValidateField(fundtocash);
		if(super.ifvalidatepass){
			return view();
		}
		this.fundtocashService.update(fundtocash);
		this.addActionMessage("修改会员资金提现信息成功");
		return list();
	}

	/**
	 * 方法描述：删除会员资金提现记录信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.fundtocash.getTrade_id();
		id = id.replace(" ", "");
		this.fundtocashService.delete(id);
		this.addActionMessage("删除资金提现信息成功");
		return list();
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {

		// 绑定收款方式下拉列表
		getcommpara();

		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}

		if (order_state_s != null && !order_state_s.equals("")) {
			pageMap.put("order_state", order_state_s);
		}

		if (cuts_name_s != null && !cuts_name_s.equals("")) {
			pageMap.put("cust_name", cuts_name_s);
		}

		if (order_state != null && !order_state.equals("")) {
			pageMap.put("order_state", order_state);
		}

		if (starttime_s != null && !starttime_s.equals("")) {
			pageMap.put("starttime", starttime_s);
		}

		if (endtime_s != null && !endtime_s.equals("")) {
			pageMap.put("endtime", endtime_s);
		}

		if (getcash_type_s != null && !getcash_type_s.equals("")) {
			pageMap.put("getcash_type", getcash_type_s);
		}

		//过滤地区
		pageMap=super.areafilter(pageMap);
		
		// 根据页面提交的条件找出信息总数
		int count = this.fundtocashService.getCount(pageMap);

		//分页插件
		pageMap = super.pageTool(count,pageMap);
		fundtocashList = this.fundtocashService.getList(pageMap);
		fundtocashList = CategoryFuc.replaceList(fundtocashList, "getcash_type");
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出会员资金提现记录信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id=fundtocash.getTrade_id();
        if(id == null || "".equals(id)){
        	return list();
        }
		
		Map map_value = new HashMap();
		map_value.put("para_code", "getcash_type");
		map_value.put("para_value", fundtocash.getGetcash_type());
		commparaList_value = commparaService.getList(map_value);
        if(commparaList_value!=null){
			Map map_getcash = (HashMap) commparaList_value.get(0);
			sgetcash_type = map_getcash.get("para_key").toString();
        }
		
		// 绑定收款方式下拉列表
		getcommpara();
		
		// 申请人
		Map map_id = new HashMap();
		map_id.put("trade_id", id);
		fundtocashList = this.fundtocashService.getList(map_id);

		// 获取之前操作人
		Map map_sys = (HashMap) fundtocashList.get(0);
		if(map_sys.get("user_id")!=null)
		sysuser = sysuserService.get(map_sys.get("user_id").toString());
		if (sysuser != null) {
			username = sysuser.getUser_name();
		} else {
			memberuser = memberuserService.get(map_sys.get("user_id").toString());
			username = memberuser.getUser_name();
		}

		Map map_tcustname = new HashMap();
		map_tcustname = (HashMap) fundtocashList.get(0);
		scust_name = map_tcustname.get("cust_name").toString();

		return goUrl(VIEW);
	}
	
	public void getcommpara(){
		// 收款方式
		Map map = new HashMap();
		map.put("para_code", "getcash_type");
		commparaList = commparaService.getList(map);
	}

	/**
	 * @return the FundtocashList
	 */
	public List getFundtocashList() {
		return fundtocashList;
	}

	/**
	 * @param fundtocashList
	 *            the FundtocashList to set
	 */
	public void setFundtocashList(List fundtocashList) {
		this.fundtocashList = fundtocashList;
	}

	public List getCommparaList_value() {
		return commparaList_value;
	}

	public void setCommparaList_value(List commparaList_value) {
		this.commparaList_value = commparaList_value;
	}

	public String getOrder_state_s() {
		return order_state_s;
	}

	public void setOrder_state_s(String order_state_s) {
		this.order_state_s = order_state_s;
	}

	public String getGetcash_type_s() {
		return getcash_type_s;
	}

	public void setGetcash_type_s(String getcash_type_s) {
		this.getcash_type_s = getcash_type_s;
	}

	public List getCommparaList() {
		return commparaList;
	}

	public void setCommparaList(List commparaList) {
		this.commparaList = commparaList;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getCuts_name_s() {
		return cuts_name_s;
	}

	public void setCuts_name_s(String cuts_name_s) {
		this.cuts_name_s = cuts_name_s;
	}

	public String getGetcash_type() {
		return getcash_type;
	}

	public void setGetcash_type(String getcash_type) {
		this.getcash_type = getcash_type;
	}

	public String getOrder_state() {
		return order_state;
	}

	public void setOrder_state(String order_state) {
		this.order_state = order_state;
	}

	public String getSgetcash_type() {
		return sgetcash_type;
	}

	public void setSgetcash_type(String sgetcash_type) {
		this.sgetcash_type = sgetcash_type;
	}

	public String getScust_name() {
		return scust_name;
	}

	public void setScust_name(String scust_name) {
		this.scust_name = scust_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	

	public Sysuser getSysuser() {
		return sysuser;
	}

	public void setSysuser(Sysuser sysuser) {
		this.sysuser = sysuser;
	}
	
	public Memberuser getMemberuser() {
		return memberuser;
	}

	public void setMemberuser(Memberuser memberuser) {
		this.memberuser = memberuser;
	}
	
	/**
	 * @return the fundtocash
	 */
	public Fundtocash getFundtocash() {
		return fundtocash;
	}

	/**
	 * @param Fundtocash
	 *            the fundtocash to set
	 */
	public void setFundtocash(Fundtocash fundtocash) {
		this.fundtocash = fundtocash;
	}
	
	public void prepare() throws Exception { super.saveRequestParameter();
		if(fundtocash == null){
			fundtocash = new Fundtocash();
		}
		String id = fundtocash.getTrade_id();
		if(!ValidateUtil.isDigital(id)){
			fundtocash = this.fundtocashService.get(id);
		}
		System.out.println(fundtocash);
	}

}
