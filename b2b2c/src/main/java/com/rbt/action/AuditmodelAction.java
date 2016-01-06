/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: AuditmodelAction.java 
 */
package com.rbt.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.rbt.model.Auditmodel;
import com.rbt.service.IAuditmodelService;
import com.rbt.service.ICommparaService;
import com.rbt.service.ISysmoduleService;
import com.rbt.service.ISysuserService;

/**
 * @function 功能 审核模型信息action类
 * @author 创建人 胡惜坤
 * @date 创建日期 Mon Aug 06 15:40:22 CST 2012
 */
@Controller
public class AuditmodelAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 审核模型信息对象
	 */
	private Auditmodel auditmodel;
	/*
	 * 审核模型信息业务层接口
	 */
	@Autowired
	private IAuditmodelService auditmodelService;
	@Autowired
	private ISysmoduleService sysmoduleService;
	@Autowired
	private ISysuserService sysuserService;
	@Autowired
	public ICommparaService commparaService;
	/*
	 * 审核模型信息信息集合
	 */
	public List auditmodelList;
	public List auditmodeltypeList;
	public List sysuserList;
	public List sel_member_list;
	public String sel_mem_str;
	public String mem_count;
	
	public List auditList;
	
	public List audittypeList;
	public String audti_model_type;
	
	
	/**
	 * 方法描述：返回新增审核模型信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}
    
	/**
	 * 方法描述：获取审核模型类型信息
	 * 
	 * @return
	 * @throws Exception
	 */
    public void auditmodeltype(){
    	Map map_list = new HashMap();
		map_list.put("para_code", "audit_model_commpara");
		auditmodeltypeList = commparaService.getList(map_list);
    }
	/**
	 * 方法描述：新增审核模型信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		
		if(sel_mem_str==null||sel_mem_str.equals("")){
			this.addFieldError("mem_count", "请选择审核人员!");
			return add();
		}
		sel_member_list(sel_mem_str);
		if (auditmodel.getModel_type()!=null&&auditmodel.getModel_type().equals("0")) {
			this.addFieldError("auditmodel.model_type", "请选择模型类型!");
			return add();
		}
		if (existsAuditModelType(auditmodel.getModel_type())==true) {
			this.addFieldError("auditmodel.model_type", "该模型类型已经存在,请选择其它或者修改该模型!");
			return add();
		}
		if(Integer.parseInt(mem_count)<2){
			this.addFieldError("mem_count", "请选择2个以上的审核人员!");
			return add();
		}
		super.commonValidateField(auditmodel);
		if(super.ifvalidatepass){
			return add();
		}
		inserinfo();
		this.addActionMessage("新增审核模型信息成功！");
		this.auditmodel = null;
		return INPUT;
	}
	public void inserinfo()
	{
		if(sel_mem_str!=null)
		{
			String ids[]=null;
			if(sel_mem_str.contains("|")){
				ids=sel_mem_str.split("\\|");
			}else {
				ids=new String[1];
				ids[0]=sel_mem_str;
			}
			sel_member_list=new ArrayList();		
			for(int i=0;i<ids.length;i++){
				Map listMap=new HashMap();
				String id=ids[i].replace(" ","");
				String values[]=id.split(",");
				if(values!=null){
					if(values[0]!=null){
						auditmodel.setUserid(values[0].toString());
					}
					if(values[1]!=null){
						auditmodel.setUsername(values[1].toString());
					}
					auditmodel.setSort_no(String.valueOf(i+1));
					if(!values[0].equals("")&&!values[1].equals("")){
						this.auditmodelService.insert(auditmodel);
					}
				}
			}
		}
	}
	/**
	 * 方法描述：构造LIST用于信息的回选
	 * @return
	 * @throws Exception
	 */
	private void sel_member_list(String user_ids){
		if(user_ids!=null&&!user_ids.equals(""))
		{
			String ids[]=null;
			if(user_ids.contains("|")){
				ids=user_ids.split("\\|");
			}else {
				ids=new String[1];
				ids[0]=user_ids;
			}
			sel_member_list=new ArrayList();		
			for(int i=0;i<ids.length;i++){
				Map listMap=new HashMap();
				String id=ids[i].replace(" ","");
				String values[]=id.split(",");
				if(values!=null){
					if(values[0]!=null){
						listMap.put("id",values[0].toString());
					}
					if(values[1]!=null){
						listMap.put("val", values[1].toString());
					}
					if(!values[0].equals("")&&!values[1].equals("")){
						sel_member_list.add(i,listMap);
					}
				}
			}
		}
	}
	/**
	 * 方法描述：获取系统用户信息
	 * 
	 * @return
	 * @throws Exception
	 */
    public void systemUserList(){
		Map pageMap = new HashMap();
    	pageMap.put("state", "0");
    	sysuserList = this.sysuserService.getList(pageMap);
    }
	/**
	 * 方法描述：插入模型的时候，判断该审核模型是否已经存在数据中！，如果存在只能修改，不能新增重复的审核模块
	 * 
	 * @return
	 * @throws Exception
	 */
    public boolean existsAuditModelType(String modeltype){
    	boolean flage=false;//flage的值为：ture表示找到该模型信息，如果为false表示没有找到模型信息
    	List modelList=new ArrayList();
		Map pageMap = new HashMap();
    	pageMap.put("model_type", modeltype);
    	modelList = this.auditmodelService.getList(pageMap);
    	if(modelList!=null&&modelList.size()>0){
    		flage=true;
    	}
    	return flage;
    }
	/**
	 * 方法描述：修改审核模型信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		
		if(sel_mem_str==null||sel_mem_str.equals("")){
			this.addFieldError("mem_count", "请选择审核人员!");
			return view();
		}
		sel_member_list(sel_mem_str);
		if (auditmodel.getModel_type()!=null&&auditmodel.getModel_type().equals("0")) {
			this.addFieldError("auditmodel.model_type", "请选择模型类型!");
			return view();
		}
		 if (existsTitle(auditmodel.getModel_type(),oldinfotitle,"auditmodel","model_type")) {
				this.addFieldError("auditmodel.model_type", "该模型类型已经存在,请选择其它!");
					return view();
		}
		if(Integer.parseInt(mem_count)<2){
			this.addFieldError("mem_count", "请选择2个以上的审核人员!");
			return view();
		}
		
		super.commonValidateField(auditmodel);
		if(super.ifvalidatepass){
			return view();
		}
	    //先删除信息，后新增
		if(audti_model_type!=null&&!audti_model_type.equals("")){
			
			this.auditmodelService.delete(audti_model_type);
			inserinfo();
		}
		
		this.addActionMessage("修改审核模型信息成功！");
		return list();
	}
	/**
	 * 方法描述：删除审核模型信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		
		if(auditmodel!=null&&auditmodel.getModel_type()!=null)
		{
			String modelattr=auditmodel.getModel_type().toString();
			modelattr=modelattr.replace(" ", "");
			if(modelattr.contains(",")){
				String strmodelattr[]=modelattr.split(",");
				for(int i=0;i<strmodelattr.length;i++){
					if(strmodelattr[i]!=null){
						this.auditmodelService.delete(strmodelattr[i].toString());
					}
				}
			}else {
				this.auditmodelService.delete(modelattr);
			}
			
			this.addActionMessage("删除审核模型信息成功！");
		}
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Map pageMap = new HashMap();
		
		audittypeList=this.auditmodelService.getList(pageMap);
		int count=0;
		List countList=new ArrayList();
		countList=this.auditmodelService.getModelList(pageMap);
		
		if(countList!=null&&countList.size()>0){
			//根据页面提交的条件找出信息总数
			 count=countList.size();
			 
		}
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		auditmodelList = this.auditmodelService.getModelList(pageMap);
		
		if(auditmodelList!=null&&auditmodelList.size()>0){
			
			auditList=new ArrayList();
			for(int i=0;i<auditmodelList.size();i++){
				String model_type="";
				Map auditmodelMap=new HashMap();
				auditmodelMap=(HashMap)auditmodelList.get(i);
				if(auditmodelMap!=null&&auditmodelMap.get("model_type")!=null){
					String trade_id="",module_name="",str_username="";
					model_type=auditmodelMap.get("model_type").toString();
					trade_id=auditmodelMap.get("trade_id").toString();
					module_name=auditmodelMap.get("para_key").toString();
					for(int j=0;j<audittypeList.size();j++){
						Map audittypeMap=new HashMap();
						audittypeMap=(HashMap)audittypeList.get(j);
						if(audittypeMap!=null&&audittypeMap.get("model_type")!=null){
							String str_model_type="",sort_no="",username="";
							str_model_type=audittypeMap.get("model_type").toString();
							if(model_type.equals(str_model_type)){
								
								if(audittypeMap.get("sort_no")!=null){
									sort_no=audittypeMap.get("sort_no").toString();
								}
								if(audittypeMap.get("username")!=null){
									username=audittypeMap.get("username").toString();
								}
								str_username +=" ["+sort_no+"]."+username+" <img src='/include/images/admin/litjian.png' />";
							}
							
						}
					}
					//构造一个新的List
					Map auditMap=new HashMap();
					auditMap.put("trade_id", trade_id);
					auditMap.put("para_key", module_name);
					auditMap.put("model_type", model_type);
					if(str_username!=null&&!str_username.equals("")){
						str_username=str_username.substring(0,str_username.lastIndexOf("<img"));
						auditMap.put("audit_sortno_name", str_username);
					}
					auditList.add(auditMap);
					
					
				}
			}
			
		}
		
		
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出审核模型信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		
		Map aMap=new HashMap();
		if(audti_model_type!=null&&!audti_model_type.equals("")){
			aMap.put("model_type", audti_model_type);
			auditmodel.setModel_type(audti_model_type);
		}
		else {
			return list();
		}
		
		List aList=new ArrayList();
		aList=auditmodelService.getList(aMap);
		if(aList!=null&&aList.size()>0){
			mem_count=String.valueOf(aList.size());
			sel_mem_str="";
			for(int i=0;i<aList.size();i++){
				Map mMap=new HashMap();
				mMap=(HashMap)aList.get(i);
				String user_id="",user_name="";
				if(mMap!=null&&mMap.get("userid")!=null){
					user_id=mMap.get("userid").toString();
				}
				if(mMap!=null&&mMap.get("username")!=null){
					user_name=mMap.get("username").toString();
				}
				if(mMap!=null&&mMap.get("userid")!=null&&mMap.get("username")!=null){
					sel_mem_str+=user_id+","+user_name+"|";
				}
			}
		}
		if(sel_mem_str!=null){
			sel_mem_str=sel_mem_str.substring(0,sel_mem_str.lastIndexOf("|"));
			sel_member_list(sel_mem_str);
		}
		
		return goUrl(VIEW);
	}
	/**
	 * @return the AuditmodelList
	 */
	public List getAuditmodelList() {
		return auditmodelList;
	}
	/**
	 * @param auditmodelList
	 *  the AuditmodelList to set
	 */
	public void setAuditmodelList(List auditmodelList) {
		this.auditmodelList = auditmodelList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(auditmodel == null){
			auditmodel = new Auditmodel();
		}
		String id = this.auditmodel.getTrade_id();
		if(!this.validateFactory.isDigital(id)){
			auditmodel = this.auditmodelService.get(id);
		}
		//获取参数模型类型
		auditmodeltype();
		//获取系统用户
		systemUserList();
	}

	public List getAuditList() {
		return auditList;
	}
	public void setAuditList(List auditList) {
		this.auditList = auditList;
	}
	public String getSel_mem_str() {
		return sel_mem_str;
	}
	public void setSel_mem_str(String sel_mem_str) {
		this.sel_mem_str = sel_mem_str;
	}
	/**
	 * @return the auditmodel
	 */
	public Auditmodel getAuditmodel() {
		return auditmodel;
	}
	/**
	 * @param Auditmodel
	 *            the auditmodel to set
	 */
	public void setAuditmodel(Auditmodel auditmodel) {
		this.auditmodel = auditmodel;
	}

	public String getAudti_model_type() {
		return audti_model_type;
	}

	public void setAudti_model_type(String audti_model_type) {
		this.audti_model_type = audti_model_type;
	}
	

}

