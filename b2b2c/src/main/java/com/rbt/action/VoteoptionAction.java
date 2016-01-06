/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: VoteoptionAction.java 
 */
package com.rbt.action;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Vote_option;
import com.rbt.service.IVote_optionService;

/**
 * @function 功能  在线调查选项action类
 * @author  创建人 蔡毅存
 * @date  创建日期  July 8, 2011
 */
@Controller
public class VoteoptionAction extends BaseAction implements Preparable{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 9016308751175044783L;
	/*
	 * Vote_option在线选项
	 */
	public Vote_option vote_option;
	
	/*
	 * 业务接口
	 */
	@Autowired
	public IVote_optionService vote_optionService;
    /*
	 *在线选项列表
	 */
	public List vote_optionList;
    
    /*
	 *在线调查ID号
	 */
	public String vote_id;
	public String vote_id_s;
    

	/**
	 * 方法描述：根据主键找出在线调选项信息
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		return goUrl(VIEW);
	}
	
	/**
	 * 方法描述：修改在线调查信息
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		//字段验证
		super.commonValidateField(vote_option);
		if(super.ifvalidatepass){
			return view();
		}
		this.vote_optionService.update(vote_option);
		this.addActionMessage("修改在线调查选项成功");
		return list();
	}
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		
		//页面搜索提交的参数
		Map pageMap = new HashMap();
		if(vote_id!=null && !vote_id.equals("")) pageMap.put("vote_id", vote_id);
		if(vote_id == null || vote_id.equals("")){
			if(vote_id_s!=null && !vote_id_s.equals("")) pageMap.put("vote_id", vote_id_s);
		}
		//过滤地区
		pageMap=super.areafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.vote_optionService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		//找出信息列表，放入list
		vote_optionList=this.vote_optionService.getList(pageMap);
		return goUrl(INDEXLIST);
	} 
	
	/**
	 * 方法描述：删除在线调查选项信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String optionid = this.vote_option.getOption_id();
		optionid = optionid.replace(" ", "");
		this.vote_optionService.delete(optionid);
		this.addActionMessage("删除在线调查选项成功");
		return list();
	}
	
	/**
	 * 方法描述：返回新增页面
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}
	
	/**
	 * 方法描述：新增友情链接信息
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		vote_option.setVote_id(vote_id);
		//字段验证
		super.commonValidateField(vote_option);
		if(super.ifvalidatepass){
			return add();
		}
		this.vote_optionService.insert(vote_option);
		this.addActionMessage("新增在线调查选项成功");
		this.vote_option = null;
		return INPUT;
	}
	
	
	
	/**
	 * 方法描述：get/set方法
	 * @return
	 * @throws Exception
	 */

	public Vote_option getVote_option() {
		return vote_option;
	}

	public void setVote_option(Vote_option vote_option) {
		this.vote_option = vote_option;
	}

	public List getVote_optionList() {
		return vote_optionList;
	}

	public void setVote_optionList(List vote_optionList) {
		this.vote_optionList = vote_optionList;
	}
	public String getVote_id() {
		return vote_id;
	}
	public void setVote_id(String vote_id) {
		this.vote_id = vote_id;
	}
	public String getVote_id_s() {
		return vote_id_s;
	}
	public void setVote_id_s(String vote_id_s) {
		this.vote_id_s = vote_id_s;
	}

	public void prepare() throws Exception { super.saveRequestParameter();
		if(vote_option == null){
			vote_option = new Vote_option();
		}
		String id = vote_option.getOption_id();
		if(!ValidateUtil.isDigital(id)){
			vote_option = this.vote_optionService.get(id);
		}
	}
	
}
