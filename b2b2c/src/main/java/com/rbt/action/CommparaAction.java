/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: CommparaAction.java 
 */
package com.rbt.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Commpara;
import com.rbt.service.ICommparaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * @function 功能  系统信息action类
 * @author  创建人 胡惜坤
 * @date  创建日期  July 6, 2011
 */
@Controller
public class CommparaAction extends BaseAction implements Preparable{

	private static final long serialVersionUID = -4903741339231855792L;
	/*
	 * 业务层接口
	 */
	@Autowired
	public ICommparaService commparaService;
	/*
	 * 搜索字段
	 * para_code_s:参数编码
	 */
	public String para_code_s;	
	/*
	 * 搜索字段
	 * para_name_s:参数名称
	 */
	public String para_name_s;
	/*
	 * 搜索字段
	 * para_key_s ：参数键
	 */
	public String para_key_s;
	/*
	 * 搜索字段
	 * para_value_s：参数值
	 */
	public String para_value_s;
	/*
	 * 搜索字段
	 */
	public String isshow_s;
	/*
	 * 系统参数对象
	 */
	public Commpara commpara;
	/*
	 * 排序id号
	 */
	public String admin_commp_id;
    /*
	 * 排序值
	 */
	public String isort_no;
	/*
	 * 列表页参数信息集合
	 */
	public List commparaList;
	
	/**
	 * 方法描述：返回新增页面
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {

		return goUrl(ADD);
	}
	/**
	 * 方法描述：新增参数信息
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception
	{
		//字段验证
		super.commonValidateField(commpara);
		if(super.ifvalidatepass){
			return add();
		}
		this.commparaService.insert(commpara);		
		this.addActionMessage("新增参数成功");
		return INPUT;
	}
	
	/**
	 * 方法描述：修改参数信息
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		//字段验证
		super.commonValidateField(commpara);
		if(super.ifvalidatepass){
			return view();
		}
		this.commparaService .update(commpara);
		this.addActionMessage("修改参数成功");
		return list();
	}
	
	/**
	 * 方法描述：删除参数信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String paraid = this.commpara.getPara_id();
		paraid = paraid.replace(" ", "");
		this.commparaService.delete(paraid);
		this.addActionMessage("删除参数成功");
		return list();
	}
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {		
		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		if (para_code_s != null && !"".equals(para_code_s)){
			pageMap.put("para_code", para_code_s);
		}	
		if (para_name_s != null && !"".equals(para_name_s)){
			pageMap.put("para_name", para_name_s);
		}
		if (para_key_s != null && !"".equals(para_key_s)){
			pageMap.put("para_key", para_key_s);
		}
		if (para_value_s != null && !"".equals(para_value_s)){
			pageMap.put("para_value", para_value_s);
		}
		if (isshow_s != null && !"".equals(isshow_s)){
			pageMap.put("enabled", isshow_s);
		}
		//过滤地区
		pageMap=super.areafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count = this.commparaService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		//找出信息列表，放入list
		commparaList = this.commparaService.getList(pageMap);	
		return goUrl(INDEXLIST);
	} 
	
	/**
	 * 方法描述：根据主键找出参数信息
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		return goUrl(VIEW);
	}
	
	/**
	 * 方法描述：批量显示/不显示操作
	 * @return
	 * @throws Exception
	 */
	public String updateisshow() throws Exception {
		String para_id = this.commpara.getPara_id();
		String isenabled = this.commpara.getEnabled();
		para_id = para_id.replace(" ", "");
		String paraStr[] = para_id.split(",");
		List paraList = new ArrayList();
		if(paraStr.length>0){
			for(int i=0;i<paraStr.length;i++){
				Map paraMap = new HashMap();
				paraMap.put("enabled", isenabled);
				paraMap.put("para_id", paraStr[i]);
				paraList.add(paraMap);
			}
		}
		this.commparaService.updateenabled(paraList);
		String tip = "";
		if(isenabled.equals("0")){
			tip = "设置有效参数成功";
		}else if(isenabled.equals("1")){
			tip = "设置无效参数成功";
		}
		this.addActionMessage(tip);
		return list();
	}
	
	/**
	 * 方法描述：批量修改导航
	 * @return
	 * @throws Exception
	 */
	public String updatesortno() throws Exception { 
		String id = this.admin_commp_id;
		String sort_no =isort_no;
		id = id.replace(" ", "");
		sort_no=sort_no.replace(" ", "");
		String paraidStr[] = id.split(",");
		String parasortStr[] = sort_no.split(",");
		List paraList = new ArrayList();
		if(paraidStr.length>0){
			Map sortMap = new HashMap();
			for(int i=0;i<paraidStr.length;i++){	
				sortMap.put("sort_no", parasortStr[i]);
				sortMap.put("para_id", paraidStr[i]);
				paraList.add(sortMap);
			}
		}
		this.commparaService.updatesort_no(paraList);
		this.addActionMessage("参数批量排序成功");
		return list();
	}
	
	public String getPara_code_s() {
		return para_code_s;
	}

	public void setPara_code_s(String para_code_s) {
		this.para_code_s = para_code_s;
	}

	public String getPara_name_s() {
		return para_name_s;
	}

	public void setPara_name_s(String para_name_s) {
		this.para_name_s = para_name_s;
	}

	public String getPara_key_s() {
		return para_key_s;
	}

	public void setPara_key_s(String para_key_s) {
		this.para_key_s = para_key_s;
	}

	public String getPara_value_s() {
		return para_value_s;
	}

	public void setPara_value_s(String para_value_s) {
		this.para_value_s = para_value_s;
	}

	

	public List getCommparaList() {
		return commparaList;
	}

	public void setCommparaList(List commparaList) {
		this.commparaList = commparaList;
	}
	public String getAdmin_commp_id() {
		return admin_commp_id;
	}
	public void setAdmin_commp_id(String admin_commp_id) {
		this.admin_commp_id = admin_commp_id;
	}
	public String getIsort_no() {
		return isort_no;
	}
	public void setIsort_no(String isort_no) {
		this.isort_no = isort_no;
	}
	public String getIsshow_s() {
		return isshow_s;
	}
	public void setIsshow_s(String isshow_s) {
		this.isshow_s = isshow_s;
	}
	
	/**
	 * @return the commpara
	 */
	public Commpara getCommpara() {
		return commpara;
	}

	/**
	 * @param Commpara
	 *            the commpara to set
	 */
	public void setCommpara(Commpara commpara) {
		this.commpara = commpara;
	}

	public void prepare() throws Exception { super.saveRequestParameter();
		if(commpara == null){
			commpara = new Commpara();
		}
		String id = commpara.getPara_id();
		if(!ValidateUtil.isDigital(id)){
			commpara = this.commparaService.get(id);
		}
	}

}
