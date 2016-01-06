/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: MembertemplateAction.java 
 */
package com.rbt.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Fundhistory;
import com.rbt.model.Interhistory;
import com.rbt.model.Levelinfo;
import com.rbt.model.Memberconfig;
import com.rbt.model.Memberfund;
import com.rbt.model.Memberinter;
import com.rbt.model.Memberlevel;
import com.rbt.model.Membertemplate;
import com.rbt.service.IFundhistoryService;
import com.rbt.service.IInterhistoryService;
import com.rbt.service.ILevelinfoService;
import com.rbt.service.IMemberconfigService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IMemberinterService;
import com.rbt.service.IMemberlevelService;
import com.rbt.service.IMembertemplateService;
/**
 * @function 功能 记录企业站模板信息action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Thu Aug 25 14:37:44 CST 2011
 */
@Controller
public class MembertemplateAction extends BaseAction implements Preparable {
	
	private static final long serialVersionUID = 1L;
	/*
	 * 记录企业站模板信息对象
	 */
	public Membertemplate membertemplate;
	/*
	 * 记录企业站配置信息对象
	 */
	public Memberconfig memberconfig;
	/*
	 * 会员级别配置对象
	 */
	public Memberlevel memberlevel;
	/*
	 * 会员积分对象
	 */
	public Memberinter memberinter;
	/*
	 * 会员积分异动对象
	 */
	public Interhistory interhistory;
	/*
	 * 会员资金对象
	 */
	public Memberfund memberfund;
	/*
	 * 会员资金对象
	 */
	public Fundhistory fundhistory;
	/*
	 * 会员级别对象
	 */
	public Levelinfo levelinfo;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IMembertemplateService membertemplateService;
	@Autowired
	public IMemberconfigService memberconfigService;
	@Autowired
	public IMemberlevelService memberlevelService;
	@Autowired
	public IMemberinterService memberinterService;
	@Autowired
	public IInterhistoryService interhistoryService;
	@Autowired
	public IMemberfundService memberfundService;
	@Autowired
	public IFundhistoryService fundhistoryService;
	@Autowired
	public ILevelinfoService levelinfoService;
	/*
	 * 记录企业站模板信息信息集合
	 */
	public List membertemplateList;
	/*
	 * 会员级别信息集合
	 */
	public List memberlevleList;
	/*
	 * 搜索字段
	 */
     public String temp_name_s;
     public String temp_code_s;
     public String temp_code;
     public String temp_mem_s;
     public String cust_name;
     
     public String temp_loc;
      /*
   	 *  批量修改排序标识字段
   	 */
     public String admin_temp_id;
      /*
      *  批量修改排序字段
      */
     public String isort_no;
	  /*
	   *  模板id号
	   */
     public String temp_id;
	
	public void prepare() throws Exception { super.saveRequestParameter();
		if(membertemplate == null){
			membertemplate = new Membertemplate();
		}
		String id = membertemplate.getTemp_id();
		if(!ValidateUtil.isDigital(id)){
			membertemplate = this.membertemplateService.get(id);
		}
	}
	//商城获取列表
	public String malllist() throws Exception{
		setPlatType();
		return list();
	}
	//商城添加信息
    public String malladd()throws Exception
    {
    	setPlatType();
    	return add();
    }
    //商城查看信息
    public String  mallview()throws Exception
    {
    	setPlatType();
    	return view();
    }
    //商城删除信息
    public String  malldelete() throws Exception
    {
    	setPlatType();
    	return delete();
    }
    //商城更新显示与不显示
	public String mallupdatetempcode()throws Exception
	{
		setPlatType();
		return updatetempcode();
	}
	 //商城排序信息
	public String mallupdatesortno()throws Exception
	{
		setPlatType();
		return updatesortno();
	}
	/**
	 * 方法描述：返回新增记录企业站模板信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		//会员级别绑定
		getmemberlevel();
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录企业站模板信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		String temp_code = this.membertemplate.getTemp_code();
		if(!"".equals(temp_code)&&temp_code!=null){
		Map pageMap = new HashMap();
		pageMap.put("temp_code", temp_code);
		// 通过模板ID号找出模板信息
		List tempList = this.membertemplateService.getList(pageMap);
		if (tempList != null && tempList.size() > 0) {
			this.addFieldError("membertemplate.temp_code", "模板编号已存在,请重新输入");
		}
		}
		membertemplate.setPlat_type(mall_type);
		//字段验证
		super.commonValidateField(membertemplate);
		if(super.ifvalidatepass){
			return add();
		}
		this.membertemplateService.insert(membertemplate);
		this.addActionMessage("新增企业站模板信息成功");
		this.membertemplate = null;
		return add();
	}

	/**
	 * 方法描述：修改记录企业站模板信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String tempid=membertemplate.getTemp_id();
		if(ValidateUtil.isDigital(tempid))
		{
		 return list();
		}
		membertemplate.setPlat_type(mall_type);
		//字段验证
		super.commonValidateField(membertemplate);
		if(super.ifvalidatepass){
			return view();
		}
		this.membertemplateService.update(membertemplate);
		this.addActionMessage("修改企业站模板成功");
		return list();
	}
	/**
	 * 方法描述：删除记录企业站模板信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.membertemplate.getTemp_id();
		id = id.replace(" ", "");
		this.membertemplateService.delete(id);
		this.addActionMessage("删除企业站模板成功");
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		//会员级别绑定
		getmemberlevel();
		cust_name = this.session_user_name;
		//会员模板列表
		Map tempMap=new HashMap();
		List membertemp=this.membertemplateService.getList(tempMap);
		StringBuilder values=new StringBuilder();
		if(membertemp!=null){
		    Map map=new HashMap();
			for(int i=0;i<membertemp.size();i++){
				map=(HashMap)membertemp.get(i);
				String result=map.get("mem_level").toString();
				result=result.replace(" ", "");
				String str[]=result.split(",");
				for(int j=0;j<str.length;j++){
					   if(str[j].equals(temp_mem_s)){
						  values.append(map.get("temp_id")+",");
					   }
					}
				}
			}
		String tempids="";
		String mapvalue=values.toString();
		if(mapvalue!=null&&!mapvalue.equals("")){
			tempids=mapvalue.substring(0, mapvalue.length()-1);
		}
		//页面搜索提交的参数
		Map pageMap = new HashMap();
		String cust_id= (String) getSession().getAttribute(
				Constants.CUST_ID);
		if(cust_id!=null && !cust_id.equals("")) pageMap.put("cust_id", cust_id);
		if(temp_code_s!=null && !temp_code_s.equals("")) pageMap.put("temp_code", temp_code_s);
		if(temp_name_s!=null && !temp_name_s.equals("")) pageMap.put("temp_name", temp_name_s);
		if(tempids!=null&&!tempids.equals(""))
			pageMap.put("temp_id", tempids);
		pageMap.put("plat_type", mall_type);
		//根据页面提交的条件找出信息总数
		int count=this.membertemplateService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		membertemplateList = this.membertemplateService.getList(pageMap);
		if(membertemplateList!=null){
			Map map=new HashMap();
			for(int i=0;i<membertemplateList.size();i++){
				map=(HashMap)membertemplateList.get(i);
				String result=map.get("mem_level").toString();
				result=result.replace(" ", "");
				String str[]=result.split(",");
				StringBuilder value=new StringBuilder();
				for(int j=0;j<str.length;j++){
					String strvalue=str[j].toString();
					memberlevel=this.memberlevelService.get(strvalue);
					if(memberlevel!=null){
					value.append(memberlevel.getLevel_name()+"</br>");
					}
				}
				if(value!=null){
				    map.put("mem_level", value.toString());
				}
			}
		}
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录企业站模板信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		//会员级别绑定
		getmemberlevel();
		// 复选框的回显
		if (membertemplate!=null&&membertemplate.getMem_level() != null) {
			String levelString = membertemplate.getMem_level().replace(" ", "");
		    String[] level = levelString.split(",");
			List levelStrList = new ArrayList();
			for (int i = 0; i < level.length; i++) {
				levelStrList.add(level[i].toString());
			}
			getRequest().setAttribute("level", levelStrList);
		}
		
		return goUrl(VIEW);
	}
	
	//会员级别绑定
	public void getmemberlevel(){
		Map levelMap=new HashMap();
		memberlevleList=this.memberlevelService.getList(levelMap);
	}
	/**
	 * 方法描述：批量修改企业模板排序
	 * @return
	 * @throws Exception
	 */
	public String updatesortno() throws Exception { 
		String id = this.admin_temp_id;
		String sort_no =isort_no;
		if(id!=null&&sort_no!=null){
		id = id.replace(" ", "");
		sort_no=sort_no.replace(" ", "");
		String tempidStr[] = id.split(",");
		String tempsortStr[] = sort_no.split(",");
		List sotrList = new ArrayList();
		if(tempidStr.length>0){
			for(int i=0;i<tempidStr.length;i++){
				Map sortMap = new HashMap();
				sortMap.put("sort_no", tempsortStr[i]);
				sortMap.put("temp_id", tempidStr[i]);
				sotrList.add(sortMap);
			}
		}
		this.membertemplateService.updatesort_no(sotrList);
		this.addActionMessage("批量排序修改成功");
		}
		return list();
	}
	
	public String updatetempcode() throws Exception{
		String cust_id= (String) getSession().getAttribute(
				Constants.CUST_ID);
		String user_id= (String) getSession().getAttribute(
				Constants.USER_ID);
		if(cust_id.equals("")){
			return list();
		}
		//获取用户企业站配置信息
		memberconfig=this.memberconfigService.get(cust_id);
		if(memberconfig==null || temp_id == null || temp_id.equals("")){
			return list();
		}
		//获取会员当前的积分数
		memberinter=this.memberinterService.get(cust_id);
		//获取会员当前的资金数
		memberfund=this.memberfundService.get(cust_id);
		//获取模板积分数或资金
		membertemplate=this.membertemplateService.get(temp_id);
		//判断会员级别
		String tag="0";
		//获取用户的会员级别
		levelinfo=this.levelinfoService.get(cust_id);
		if(levelinfo!=null&&membertemplate!=null){
			String level=levelinfo.getLevel_code();
		    //获取该模板的会员免费使用权限
		    String mem_level=membertemplate.getMem_level();
			mem_level=mem_level.replace(" ", "");
			String levels[]=mem_level.split(",");
            //该会员具有模板免费tag=“1”没有则tag=“0”		
			for(int i=0;i<levels.length;i++){
				if(level.equals(levels[i])){
					tag="1";
				}
			}
		}
		
		if(memberinter!=null&&memberfund!=null&&"0".equals(tag)){
			if(membertemplate==null){
				return list();
			}
		    //判断积分支付或资金支付
		    String Currency=membertemplate.getP_unit();
		    //采用积分支付
			if("0".equals(Currency)){
				String integral=membertemplate.getPrice();
				String fund_num=memberinter.getFund_num();
				if(Integer.parseInt(fund_num)>=Integer.parseInt(integral)){
					String thisinter=Integer.toString(Integer.parseInt(fund_num)-Integer.parseInt(integral));
					//跟新会员积分表
					memberinter.setFund_num(thisinter);
					this.memberinterService.update(memberinter);
					//插入会员积分异动历史
					interhistory=new Interhistory();
					interhistory.setCust_id(cust_id);
					interhistory.setInter_in("0");
					interhistory.setInter_out(integral);
					interhistory.setThisinter(thisinter);
					interhistory.setUser_id(user_id);
					interhistory.setReason("购买"+ membertemplate.getTemp_code() +"模板");
					interhistory.setRemark("");
					this.interhistoryService.insert(interhistory);
				}else{
					this.addActionMessage("您的积分不足，请充值");
					return list();
				}
			}
			//采用资金支付
	        if("1".equals(Currency)){
				String temp_num=this.membertemplate.getPrice();
				String use_fund=this.memberfund.getUse_num();
				String all_fund=this.memberfund.getFund_num();
				if(Double.parseDouble(use_fund)>=Double.parseDouble(temp_num)){
					use_fund=Double.toString(Double.parseDouble(use_fund)-Double.parseDouble(temp_num));
					all_fund=Double.toString(Double.parseDouble(all_fund)-Double.parseDouble(temp_num));
					//跟新资金表
					memberfund.setFund_num(all_fund);
					memberfund.setUse_num(use_fund);
					this.memberfundService.update(memberfund);
					////插入会员资金异动历史
					fundhistory=new Fundhistory();
					fundhistory.setCust_id(cust_id);
					fundhistory.setFund_in("0");
					fundhistory.setFund_out(temp_num);
					fundhistory.setBalance(use_fund);
					fundhistory.setUser_id(user_id);
					fundhistory.setReason("购买"+ membertemplate.getTemp_code() +"模板");
					fundhistory.setRemark("");
					this.fundhistoryService.insert(fundhistory);
				}else{
					this.addActionMessage("您的资金不足，请充值");
					return list();
				}
			}
			
		}
		memberconfig.setTemp_code(temp_code);
		memberconfig.setTemp_loc(temp_loc);
		this.memberconfigService.updatetempcode(memberconfig);
		return list();
	}
	
	
	/**
	 * @return the MembertemplateList
	 */
	public List getMembertemplateList() {
		return membertemplateList;
	}
	/**
	 * @param membertemplateList
	 *  the MembertemplateList to set
	 */
	public void setMembertemplateList(List membertemplateList) {
		this.membertemplateList = membertemplateList;
	}
	public String getTemp_name_s() {
		return temp_name_s;
	}
	public void setTemp_name_s(String temp_name_s) {
		this.temp_name_s = temp_name_s;
	}
	public String getTemp_code_s() {
		return temp_code_s;
	}
	public void setTemp_code_s(String temp_code_s) {
		this.temp_code_s = temp_code_s;
	}
	public String getAdmin_temp_id() {
		return admin_temp_id;
	}
	public void setAdmin_temp_id(String admin_temp_id) {
		this.admin_temp_id = admin_temp_id;
	}
	public String getIsort_no() {
		return isort_no;
	}
	public void setIsort_no(String isort_no) {
		this.isort_no = isort_no;
	}
	public Memberconfig getMemberconfig() {
		return memberconfig;
	}
	public void setMemberconfig(Memberconfig memberconfig) {
		this.memberconfig = memberconfig;
	}
	public String getTemp_code() {
		return temp_code;
	}
	public void setTemp_code(String temp_code) {
		this.temp_code = temp_code;
	}
	public List getMemberlevleList() {
		return memberlevleList;
	}
	public void setMemberlevleList(List memberlevleList) {
		this.memberlevleList = memberlevleList;
	}
	public String getTemp_mem_s() {
		return temp_mem_s;
	}
	public void setTemp_mem_s(String temp_mem_s) {
		this.temp_mem_s = temp_mem_s;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getTemp_id() {
		return temp_id;
	}
	public void setTemp_id(String temp_id) {
		this.temp_id = temp_id;
	}
	public String getTemp_loc() {
		return temp_loc;
	}
	public void setTemp_loc(String temp_loc) {
		this.temp_loc = temp_loc;
	}
	/**
	 * @return the membertemplate
	 */
	public Membertemplate getMembertemplate() {
		return membertemplate;
	}
	/**
	 * @param Membertemplate
	 *            the membertemplate to set
	 */
	public void setMembertemplate(Membertemplate membertemplate) {
		this.membertemplate = membertemplate;
	}
	/**
	 * @param membertemplateService
	 *            the membertemplateService to set
	 */
	public void setMembertemplateService(IMembertemplateService membertemplateService) {
		this.membertemplateService = membertemplateService;
	}

}

