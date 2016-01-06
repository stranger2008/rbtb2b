/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: SysconfigAction.java 
 */
package com.rbt.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Sysconfig;
import com.rbt.model.Sysmodule;
import com.rbt.service.ICommparaService;
import com.rbt.service.ISysconfigService;
import com.rbt.service.ISysmoduleService;

/**
 * @function 功能 系统参数设置Action层实现
 * @author 创建人 邱景岩
 * @date 创建日期 Jul 7, 2011 3:40:23 PM
 */
@Controller
public class SysconfigAction extends BaseAction implements Preparable {
	
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -2345567567976466041L;

	/*
	 * 给系统配置分组赋初始值
	 * 2：站点设置
	 */
	private static final String VAR_GROUP_DEFAULT_VALUE = "2";

	/*
	 * 定义默认的系统标量 1：自定义标量
	 */
	private static final String VAL_SYS_VALUE = "1";
	/*
	 * 搜索字段 para_key_s：系统参数组别
	 */
	public String para_key_s;
	/*
	 * 批量更新隐藏字段 admin_sysconfig_varid
	 */
	public String admin_sysconfig_varid;
	/*
	 * 系统变量业务层接口
	 */
	@Autowired
	public ISysconfigService sysconfigService;
	@Autowired
	private ISysmoduleService sysmoduleService;
	@Autowired
	public ICommparaService commparaService;

	/*
	 * 系统变量集合
	 */
	public List sysconfigList;
	/*
	 * 参数集合
	 */
	public List commparaList;
	/*
	 * 系统变量对象
	 */
	public Sysconfig sysconfig;
	public String module_type_s;
	public String module_name;

	/**
	 * 方法描述：系统变量列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {		
		Map pageMap = new HashMap();
		if(module_type_s!=null&&!module_type_s.equals("")){
			Sysmodule sysmodule=this.sysmoduleService.get(module_type_s);
			if(sysmodule!=null){
				module_name=sysmodule.getModule_name();				
				pageMap.put("module_type", module_type_s);
				sysconfigList = this.sysconfigService.getList(pageMap);
			}
		}else{
			pageMap.put("para_code", "var_group");
			commparaList = this.commparaService.getList(pageMap);
			//给系统配置分组赋初始值2
			//2：站点设置
			if (para_key_s == null) {
				para_key_s = this.VAR_GROUP_DEFAULT_VALUE;
			}
			if (para_key_s != null && !para_key_s.equals(""))
				pageMap.put("para_key", para_key_s);
			sysconfigList = this.sysconfigService.getList(pageMap);
		}
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：跳转到新增页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		Map map = new HashMap();
		if(module_type_s!=null&&!module_type_s.equals("")){
			map.put("state_code", "0");
			commparaList = this.sysmoduleService.getList(map);
		}else{
			map.put("para_code", "var_group");
			commparaList = this.commparaService.getList(map);
		}
		if(sysconfig.getModule_type()!=null&&!sysconfig.getModule_type().equals("")){
			module_name=sysconfig.getModule_type();
		}
		return goUrl(ADD);
	}

	/**
	 * 方法描述：插入变量
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		if(!ValidateUtil.isRequired(sysconfig.getVar_name()) && existsTitle(sysconfig.getVar_name(),"","sysconfig","var_name")){
			this.addFieldError("sysconfig.var_name", "变量名称已存在,请重新输入");
		}
	    if(!ValidateUtil.isRequired(sysconfig.getVar_value())){
			if (sysconfig.getVar_type().equals("1") && ValidateUtil.isDigital(sysconfig.getVar_value())) {
				this.addFieldError("sysconfig.var_value", "请填写数字");
			} 
		}
		if (sysconfig.getVar_group()!=null&&sysconfig.getVar_group().equals("0")) {
			this.addFieldError("sysconfig.var_group", "请选择变量类型所属组");
		}
		if (sysconfig.getModule_type()!=null&&sysconfig.getModule_type().equals("0")) {
			this.addFieldError("sysconfig.var_group", "请选择变量类型所属模块");
		}
		sysconfig.setVal_sys(VAL_SYS_VALUE);
		//字段验证
		super.commonValidateField(sysconfig);
		if(super.ifvalidatepass){
			return add();
		}
		this.sysconfigService.insert(sysconfig);
		this.addActionMessage("新增变量信息成功");
		return add();
	}

	/**
	 * 方法描述：删除系统变量
	 * 
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		this.sysconfigService.delete(this.sysconfig.getVar_id());
		this.addActionMessage("删除变量信息成功");
		return list();
	}

	/**
	 * 方法描述：批量修改系统变量
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateSysconfigBatch() throws Exception {
		if (sysconfig.getVar_value() != null && !sysconfig.getVar_value().equals("")) {
			String varid = this.admin_sysconfig_varid;
			String varvalue = this.sysconfig.getVar_value();
			String varidStr[] = varid.split(",");
			String varValueStr[] = varvalue.split(",");
			List sysconfigList = new ArrayList();
			if (varidStr.length > 0) {
				for (int i = 0; i < varidStr.length; i++) {
					Map configMap = new HashMap();
					configMap.put("var_id", varidStr[i]);
					configMap.put("var_value", varValueStr[i].trim());
					sysconfigList.add(configMap);
				}
			}
			this.sysconfigService.updateSysconfigBatch(sysconfigList);
			this.addActionMessage("变量修改信息成功");
			return list();
		} else {
			return list();
		}

	}

	/**
	 * @return the sysconfigList
	 */
	public List getSysconfigList() {
		return sysconfigList;
	}

	/**
	 * @param sysconfigList
	 *            the sysconfigList to set
	 */
	public void setSysconfigList(List sysconfigList) {
		this.sysconfigList = sysconfigList;
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
	 * @return the sysconfig
	 */
	public Sysconfig getSysconfig() {
		return sysconfig;
	}

	/**
	 * @param sysconfig
	 *            the sysconfig to set
	 */
	public void setSysconfig(Sysconfig sysconfig) {
		this.sysconfig = sysconfig;
	}

	/**
	 * @return the admin_sysconfig_varid
	 */
	public String getAdmin_sysconfig_varid() {
		return admin_sysconfig_varid;
	}

	/**
	 * @param admin_sysconfig_varid
	 *            the admin_sysconfig_varid to set
	 */
	public void setAdmin_sysconfig_varid(String admin_sysconfig_varid) {
		this.admin_sysconfig_varid = admin_sysconfig_varid;
	}

	public String getPara_key_s() {
		return para_key_s;
	}

	public void setPara_key_s(String para_key_s) {
		this.para_key_s = para_key_s;
	}
	
	public void prepare() throws Exception { super.saveRequestParameter();
	if (sysconfig == null) {
		sysconfig = new Sysconfig();
	}
	String id = sysconfig.getVar_id();
	if (!ValidateUtil.isDigital(id)) {
		sysconfig = this.sysconfigService.get(id);
	}		   
}

}
