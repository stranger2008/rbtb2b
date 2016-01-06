/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: MembercertAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.opensymphony.xwork2.Preparable;
import com.rbt.action.BaseAction;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Certification;
import com.rbt.model.Membercert;
import com.rbt.service.ICertificationService;
import com.rbt.service.IMembercertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 会员荣誉资质信息action类
 * @author 创建人 邱景岩
 * @date 创建日期 Wed Jul 20 13:11:13 CST 2011
 */
@Controller
public class MembercertAction extends BaseAction implements Preparable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 214859436968366634L;
	/*
	 * 验证证书状态标识
	 */
	public String certstateTip = "";
	/*
	 * 搜索字段 title_s:证书标题, organize_s：发证机构 添加时间段：in_date_s，end_date_s
	 * cert_state_s：证书状态
	 */
	public String title_s;
	public String organize_s;
	public String in_date_s;
	public String end_date_s;
	public String cust_name_s;
	public String cert_state_s;
	public String today;
	/*
	 * 会员荣誉资质信息对象
	 */
	public Membercert membercert;
	/*
	 * 荣誉资质业务层接口
	 */
	@Autowired
	public IMembercertService membercertService;
	@Autowired
	public ICertificationService certificationService;
	/*
	 * 会员荣誉资质信息信息集合
	 */
	public List membercertList;

	/**
	 * 方法描述：返回新增会员荣誉资质信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		//发布信息数量控制
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			controlInfoNum();
		}
		return goUrl(ADD);
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
		int count = this.membercertService.getCount(tmap);
		if(controlMsgNum(count,"membercert")){
			return true;
		}else{
			return false;
		}
	}


	
	
	/**
	 * 方法描述：新增会员荣誉资质信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		//查看本会员是否存在该标题
		Map map=new HashMap();
		map.put("cust_id", this.session_cust_id);
		map.put("title", membercert.getTitle());
		List list=this.membercertService.getList(map);
		if (!ValidateUtil.isRequired(membercert.getTitle()) && list!=null&&list.size()>0) {
			this.addFieldError("membercert.title", "证书标题已经存在");
		}
		membercert.setCust_id(this.session_cust_id);
		//发布信息数量控制
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			if(controlInfoNum()){
				return add();
			}
		}
		//字段验证
		super.commonValidateField(membercert);
		if(super.ifvalidatepass){
			return add();
		}
		this.membercertService.insert(membercert);
		this.addActionMessage("新增会员荣誉资质信息成功");
		this.membercert = null;
		is_crotorl=true;
		return add();
	}

	/**
	 * 方法描述：修改会员荣誉资质信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		//查看本会员是否存在该标题
		Map map=new HashMap();
		map.put("cust_id", this.session_cust_id);
		map.put("title", membercert.getTitle());
		List list=this.membercertService.getList(map);
		if (!ValidateUtil.isRequired(membercert.getTitle()) && list!=null&&list.size()>0) {
			Map listMap =(HashMap)list.get(0);
			if(listMap.get("cert_id")!=null && !membercert.getCert_id().equals(listMap.get("cert_id").toString())){//不是本条的ID
				this.addFieldError("membercert.title", "证书标题已经存在");		
			}
		}
		
		//对审核成功的证书加信用指数
		Membercert cfc=new Membercert();
		if(!ValidateUtil.isRequired(membercert.getCert_id())){
			cfc=this.membercertService.get(membercert.getCert_id());
		}		
		String state=cfc.getCert_state();
		//对审核成功证书后，更新证书成功减分
		if(state!=null&&state.equals("0")){
			this.certificationService.creditChangeNum(membercert.getCust_id(), -1, "cfg_certificate","b","更新成功",membercert.getTitle());
		}	
		// 当会员更新证书信息时，证书状态更新为未审核
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			membercert.setCert_state("1");
		}
		//字段验证
		super.commonValidateField(membercert);
		if(super.ifvalidatepass){
			return view();
		}
		this.membercertService.update(membercert);
		this.addActionMessage("修改会员荣誉资质信息成功");
		return list();
	}

	/**
	 * 方法描述：删除会员荣誉资质信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.membercert.getCert_id();
		id = id.replace(" ", "");
		String[] ids=id.split(",");
		for(int i=0;i<ids.length;i++){
			//对审核成功的证书加信用指数
			Membercert cfc=new Membercert();
			if(!ValidateUtil.isRequired(ids[i])){
				cfc=this.membercertService.get(ids[i]);
			}		
			String state=cfc.getCert_state();
			String cust_id=cfc.getCust_id();
			//对审核通过的证书删除后减分
			if(state!=null&&state.equals("0")){
				this.certificationService.creditChangeNum(cust_id, -1, "cfg_certificate","b","删除成功",membercert.getTitle());
			}	
		}
		this.membercertService.delete(id);
		this.addActionMessage("删除会员荣誉资质信息成功");
		return list();
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map pageMap = new HashMap();
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		if (title_s != null && !title_s.equals("")) {
			pageMap.put("title", title_s);
		}
		if (cust_name_s != null && !cust_name_s.equals("")) {
			pageMap.put("cust_name", cust_name_s);
		}
		if (organize_s != null && !organize_s.equals("")) {
			pageMap.put("organize", organize_s);
		}
		if (in_date_s != null && !in_date_s.equals("")) {
			pageMap.put("in_date", in_date_s);
		}
		if (end_date_s != null && !end_date_s.equals("")) {
			pageMap.put("end_date", end_date_s);
		}
		if (cert_state_s != null && !cert_state_s.equals("")) {
			pageMap.put("cert_state", cert_state_s);
		}
		
		if(today !=null && !"".equals(today)){
			pageMap.put("today", today);
		}
		
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.membercertService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count, pageMap);
		membercertList = this.membercertService.getList(pageMap);
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出会员荣誉资质信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(membercert.getCust_id()!=null){
			if(accessControl(membercert.getCust_id())){
				return list();
			}
		}
		//判断id的值是否符合条件，不符合的话返回到列表
		String rbtid = this.membercert.getCert_id();
		if (ValidateUtil.isDigital(rbtid)) {
			return list();
		}
		setCertstateTip(membercert.getCert_state());
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：根据主键找出会员荣誉资质信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String audit() throws Exception {
		String rbtid = this.membercert.getCert_id();
		if (ValidateUtil.isDigital(rbtid)) {
			return list();
		}
		setCertstateTip(membercert.getCert_state());
		return goUrl(AUDIT);
	}

	/**
	 * @function 功能 审核荣誉资质信息
	 * @author 创建人 邱景岩
	 * @date 创建日期 Jul 27, 2011 10:41:53 AM
	 */
	public String auditState() throws Exception {
		if (ValidateUtil.isRequired(membercert.getCert_state())) {
			this.addFieldError("membercert.cert_state", "请选择审核状态");
			return audit();
		}
		if (membercert.getCert_state().equals("2") && ValidateUtil.isRequired(membercert.getNo_reason())) {
			this.addFieldError("membercert.no_reason", "请输入拒绝理由");
			setCertstateTip("2");
			return audit();
		}
		Map stateMap = new HashMap();
		if (!membercert.getCert_state().equals("2")) {
			stateMap.put("no_reason", "");
		} else {
			stateMap.put("no_reason", membercert.getNo_reason());
		}
		stateMap.put("cert_id", membercert.getCert_id());
		stateMap.put("cert_state", membercert.getCert_state());
		
		String info_state=membercert.getCert_state();
		//info_state:0代表审核通过
		if(info_state!=null && "0".equals(info_state)){			 
			this.certificationService.creditChangeNum(membercert.getCust_id(), 1, "cfg_certificate","b","审核["+membercert.getTitle()+"证书]成功","");
		}
		this.membercertService.auditMembercert(stateMap);
		this.addActionMessage("已审核荣誉资质信息");
		return list();
	}

	/**
	 * @return the MembercertList
	 */
	public List getMembercertList() {
		return membercertList;
	}

	/**
	 * @param membercertList
	 *            the MembercertList to set
	 */
	public void setMembercertList(List membercertList) {
		this.membercertList = membercertList;
	}

	/**
	 * @return the title_s
	 */
	public String getTitle_s() {
		return title_s;
	}

	/**
	 * @param title_s
	 *            the title_s to set
	 */
	public void setTitle_s(String title_s) {
		this.title_s = title_s;
	}

	/**
	 * @return the organize_s
	 */
	public String getOrganize_s() {
		return organize_s;
	}

	/**
	 * @param organize_s
	 *            the organize_s to set
	 */
	public void setOrganize_s(String organize_s) {
		this.organize_s = organize_s;
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
	 * @return the cert_state_s
	 */
	public String getCert_state_s() {
		return cert_state_s;
	}

	/**
	 * @param cert_state_s
	 *            the cert_state_s to set
	 */
	public void setCert_state_s(String cert_state_s) {
		this.cert_state_s = cert_state_s;
	}

	/**
	 * @return the certstateTip
	 */
	public String getCertstateTip() {
		return certstateTip;
	}

	/**
	 * @param certstateTip
	 *            the certstateTip to set
	 */
	public void setCertstateTip(String certstateTip) {
		this.certstateTip = certstateTip;
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
	 * @return the membercert
	 */
	public Membercert getMembercert() {
		return membercert;
	}

	/**
	 * @param Membercert
	 *            the membercert to set
	 */
	public void setMembercert(Membercert membercert) {
		this.membercert = membercert;
	}

	
	/**
	 * @Method Description : 当进入方法后初始化对象
	 * @author : 林俊钦
	 * @date : Nov 8, 2011 2:36:50 PM
	 */
	public void prepare() throws Exception { super.saveRequestParameter();
		if (membercert == null) {
			membercert = new Membercert();
		}
		String id = membercert.getCert_id();
		if (!ValidateUtil.isDigital(id)) {
			membercert = this.membercertService.get(id);
		}
	}

}
