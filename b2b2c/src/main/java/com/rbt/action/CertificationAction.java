/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: CertificationAction.java 
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.AreaFuc;
import com.rbt.model.Certification;
import com.rbt.model.Member;
import com.rbt.model.Sysuser;
import com.rbt.service.ICategoryService;
import com.rbt.service.ICertificationService;
import com.rbt.service.ICommparaService;
import com.rbt.service.IMemberService;
import com.rbt.service.ISysuserService;

/**
 * @function 功能 记录企业身份认证信息action类
 * @author 创建人 林俊钦 */
@Controller
public class CertificationAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 记录企业身份认证信息对象
	 */
	public Certification certification;
	/*
	 * 业务层接口
	 */   
	@Autowired
	public ICertificationService certificationService;
	@Autowired
	public IMemberService memberService;
	@Autowired
	public ICommparaService commparaService;
	@Autowired
	public ICategoryService categoryService;
	@Autowired
	public ISysuserService sysuserService;
	
	/*
	 * 记录企业身份认证信息信息集合
	 */
	public List certificationList;		
	public List commparaList;
	public List cust_type_List;
	public List catList;
	public Member member;
	public Sysuser sysuser ;
	/*
	 * 定义常量字段,用于从基本参数中找出相应的模块值
	 */
	private String para_code = "currency_type";
	private String cust_para_code="cust_type";
	private String module_type="company";	
	public String state_s;	
	public String cust_name_s;
	public String corporate_s;
	public String cust_type_s;
	public String app_name_s;
	//地区名称
	public String areaName;

	/**
	 * 方法描述：返回新增记录企业身份认证信息页面
	 * 
	 * @return
	 * @throws Exception
	 */

	public String add() throws Exception {
		// 获取币种的参数
		Map paraMap=new HashMap();
		paraMap.put("para_code", para_code);
		commparaList=this.commparaService.getList(paraMap);
		// 获取企业类型
		Map custMap=new HashMap();
		custMap.put("para_code", cust_para_code);
		cust_type_List=this.commparaService.getList(custMap);
		// 获取公司的经营范围
		Map catMap=new HashMap();
		catMap.put("cat_level", 1);
		catMap.put("module_type", module_type);
		catList=this.categoryService.getList(catMap);		
		// 获取公司的信息
		member=this.memberService.get(this.session_cust_id);
		//找出企业认证表中存在该数据则取表数据，如果没有，则从会员表中取数据
		Map map=new HashMap();
		map.put("cust_id", this.session_cust_id);
		List list=this.certificationService.getList(map);
		if(list!=null&&list.size()>0){
			certification=this.certificationService.get(this.session_cust_id);
			backArea(certification.getArea_attr()); //设置地区
			String audit_user_id=certification.getAudit_user_id();//根据审核人的ID获取审核人的名称
			if(!ValidateUtil.isRequired(audit_user_id)){
				sysuser=this.sysuserService.get(audit_user_id);
			}
		}else{
            //从会员表中获取企业认证数据			
			backArea(member.getArea_attr()); //设置地区
			certification.setCust_name(member.getCust_name());
			certification.setArea_attr(member.getArea_attr());
			certification.setAddress(member.getAddress());
			certification.setCorporate(member.getCorporate());
			certification.setCust_type(member.getCust_type());
			certification.setReg_money(member.getReg_money());
			if(certification.getClass_attr()==null){
				certification.setClass_attr(member.getMain_product());
			}			
			certification.setReg_date(member.getReg_date());
			certification.setApp_name(member.getContact_name());
			certification.setApp_depart(member.getContact_depart());
			certification.setApp_career(member.getContact_job());
			certification.setApp_contact(member.getContact_cellphone());
		}
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录企业身份认证信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		// 用于所属地区的回选开始
		loadArea();
		//验证所属地区是否有选择
		if (ValidateUtil.isRequired(area_attr) || hidden_area_value.indexOf("0")>-1) {
			this.addFieldError("areaerror", "请选择地区");
		}	
		// 存入地区
		certification.setArea_attr(area_attr);	
		certification.setCust_id(this.session_cust_id);
		certification.setUser_id(this.session_user_id);
		// 去掉年检记录的空格符号
		if(certification.getIns_record()!=null){
			String ins_reconrd=this.certification.getIns_record();
			this.certification.setIns_record(ins_reconrd.replace(" ",""));
		}		
		//找出企业认证表中是否已存在该公司的认证
		Map map=new HashMap();
		map.put("cust_id", this.session_cust_id);
		List list=this.certificationService.getList(map);
		//字段验证
		super.commonValidateField(certification);
		if(super.ifvalidatepass){
			return add();
		}		
		if(list!=null&&list.size()>0){
			Certification cfc=new Certification();
			if(!ValidateUtil.isRequired(certification.getCust_id())){
				cfc=this.certificationService.get(certification.getCust_id().toString());
			}		
			String state=cfc.getInfo_state();
			//对审核通过的实名认证更新后减分
			if(state!=null&&state.equals("3")){
				this.certificationService.creditChangeNum(certification.getCust_id(), -1, "cfg_identity","a","修改成功","");
			}
			this.certification.setInfo_state("1");//认证中
			this.certificationService.update(certification);
		}else{
			this.certification.setInfo_state("0");//新加入
			this.certificationService.insert(certification);
		}
		return view();
	}

	/**
	 * 方法描述：修改记录企业身份认证信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		this.certificationService.update(certification);
		this.addActionMessage("修改企业身份认证成功");
		return list();
	}
	/**
	 * 方法描述：删除记录企业身份认证信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.certification.getCust_id();
		id = id.replace(" ", "");
		String[] ids=id.split(",");
		for(int i = 0;i<ids.length;i++){
			Certification cfc=new Certification();
			if(!ValidateUtil.isRequired(ids[i])){
				cfc=this.certificationService.get(ids[i]);
			}		
			String state=cfc.getInfo_state();
			//对审核通过的实名认证删除操作减分
			if(state!=null && state.equals("3")){
				this.certificationService.creditChangeNum(ids[i], -1, "cfg_identity","a","删除成功","");
			}
		}	
		this.certificationService.delete(id);
		this.addActionMessage("删除企业身份认证成功");
		return auditList();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		
		return goUrl(INDEXLIST);
	}
	
	/**
	 * @Method Description : 运营商审核实名认证列表
	 * @author : 林俊钦
	 * @date : Dec 2, 2011 11:20:35 AM
	 */
	public String auditList() throws Exception {		
		// 获取企业类型
		Map custMap=new HashMap();
		custMap.put("para_code", cust_para_code);
		cust_type_List=this.commparaService.getList(custMap);
		//搜索内容
        Map pageMap = new HashMap();		
        if(!ValidateUtil.isRequired(state_s)){
        	pageMap.put("info_state", state_s);
        }
        if(!ValidateUtil.isRequired(cust_name_s)){
        	pageMap.put("cust_name", cust_name_s);
        }	
        if(!ValidateUtil.isRequired(corporate_s)){
        	pageMap.put("corporate", corporate_s);
        }	
        if(!ValidateUtil.isRequired(cust_type_s)){
        	pageMap.put("cust_type", cust_type_s);
        }
        if(!ValidateUtil.isRequired(app_name_s)){
        	pageMap.put("app_name", app_name_s);
        }
		//根据页面提交的条件找出信息总数
		int count=this.certificationService.getCount(pageMap);		
		//分页插件
		pageMap = super.pageTool(count,pageMap);		
		certificationList = this.certificationService.getList(pageMap);
		return goUrl(AUDITLIST);
	}
	
	/**
	 * 方法描述：根据主键找出记录企业身份认证信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(certification.getCust_id()!=null){
			if(accessControl(certification.getCust_id())){
				return list();
			}
		}
		//找出企业认证表中存在该数据则取表数据，如果没有，则从会员表中取数据
		Map map=new HashMap();
		map.put("cust_id", this.session_cust_id);
		List list=this.certificationService.getList(map);
		if(list!=null&&list.size()<=0){
			return add();
		}
		// 获取币种的参数
		Map paraMap=new HashMap();
		paraMap.put("para_code", para_code);
		commparaList=this.commparaService.getList(paraMap);
		// 获取企业类型
		Map custMap=new HashMap();
		custMap.put("para_code", cust_para_code);
		cust_type_List=this.commparaService.getList(custMap);
		// 获取公司的经营范围
		Map catMap=new HashMap();
		catMap.put("cat_level", "1");
		catMap.put("module_type", module_type);
		catList=this.categoryService.getList(catMap);		
        //实例化对象
		if(this.session_cust_id==null || "".equals(this.session_cust_id)){
			certification = new Certification();
		}else{
			certification = this.certificationService.get(this.session_cust_id);	
			String audit_user_id=certification.getAudit_user_id();
			if(!ValidateUtil.isRequired(audit_user_id)){
				sysuser=this.sysuserService.get(audit_user_id);
			}
			// 找出地区字段的存入隐藏值中
			areaName=AreaFuc.getAreaNameByMap(certification.getArea_attr()); 
		}
		return goUrl(VIEW);
	}
	
	/**
	 * @Method Description :运营商审核会员实名认证
	 * @author : 林俊钦
	 * @date : Dec 2, 2011 12:59:16 PM
	 */
	public String audit() throws Exception {
		String id=certification.getCust_id();
		if(id==null || id.equals("")){
			certification = new Certification();
		}else{
			certification = this.certificationService.get(id);	
			String audit_user_id=certification.getAudit_user_id();
			if(!ValidateUtil.isRequired(audit_user_id)){
				sysuser=this.sysuserService.get(audit_user_id);
			}
			hidden_area_value=certification.getArea_attr();
		}
		//获取地区中文字符
		String area_name = "";
		if(certification.getArea_attr() != null){
			area_name = AreaFuc.getAreaNameByMap(certification.getArea_attr());
		}
		certification.setArea_attr(area_name);
		// 获取币种的参数
		Map paraMap=new HashMap();
		paraMap.put("para_code", para_code);
		commparaList=this.commparaService.getList(paraMap);
		// 获取企业类型
		Map custMap=new HashMap();
		custMap.put("para_code", cust_para_code);
		cust_type_List=this.commparaService.getList(custMap);
		// 获取公司的经营范围
		Map catMap=new HashMap();
		catMap.put("cat_level", "1");
		catMap.put("module_type", module_type);
		catList=this.categoryService.getList(catMap);		
		return goUrl(AUDIT);
	}
	
	/**
	 * @Method Description : 运营商审核实名认证的状态
	 * @author : 林俊钦
	 * @date : Dec 2, 2011 1:29:20 PM
	 */
	public String auditState()throws Exception {
		String info_state=this.certification.getInfo_state();
		if("2".equals(info_state)){
			if(ValidateUtil.isRequired(certification.getReason())){
				this.addFieldError("certification.reason", "拒绝理由不能为空");
				return audit();
			}
		}
		Certification cfc=new Certification();
		String cust_id="";
		if(!ValidateUtil.isRequired(certification.getCust_id())){
			cust_id=certification.getCust_id().toString();
			cfc=this.certificationService.get(cust_id);
		}		
		String state=cfc.getInfo_state();
		//对审核通过的实名认证加分,3代表审核通过
		if(state!=null&&!"3".equals(state)&&"3".equals(info_state)){
			this.certificationService.creditChangeNum(cust_id, 1, "cfg_identity","a","审核通过","");
		}
		//修改审核状态
		this.certification.setAudit_user_id(this.session_user_id);//设置审核人ID	
		this.certificationService.auditState(certification);
		this.addActionMessage("审核企业身份认证信息成功");		
		return auditList();
	}
	
	
	/**
	 * @return the CertificationList
	 */
	public List getCertificationList() {
		return certificationList;
	}
	/**
	 * @param certificationList
	 *  the CertificationList to set
	 */
	public void setCertificationList(List certificationList) {
		this.certificationList = certificationList;
	}
	
	public void prepare() throws Exception { super.saveRequestParameter();
		if(certification == null){
			certification = new Certification();
		}
		String id = this.certification.getCust_id();
		if(!ValidateUtil.isDigital(id)){
			certification = this.certificationService.get(id);
		}
	}
	/**
	 * @return the certification
	 */
	public Certification getCertification() {
		return certification;
	}
	/**
	 * @param Certification
	 *            the certification to set
	 */
	public void setCertification(Certification certification) {
		this.certification = certification;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

}

