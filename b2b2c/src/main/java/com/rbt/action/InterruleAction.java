/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: InterruleAction.java 
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
import com.rbt.model.Interrule;
import com.rbt.service.IInterruleService;

/**
 * @function 功能 积分规则表action类
 * @author 创建人 林俊钦
 * @date 创建日期 Thu Nov 10 14:26:30 CST 2011
 */
@Controller
public class InterruleAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 积分规则表对象
	 */
	public Interrule interrule;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IInterruleService interruleService;
	/*
	 * 积分规则表信息集合
	 */
	public List interruleList;
	public String scoreid;
	public String scoreNum;

	
	
	/**
	 * 方法描述：返回新增积分规则表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增积分规则表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		return null;
	}

	/**
	 * 方法描述：修改积分规则表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateScore() throws Exception {
		if(ValidateUtil.isRequired(this.scoreid)){
			return list();
		}
     	String rule_id = this.scoreid;
		String rule_num = this.scoreNum;		
		rule_id=rule_id.replace(" ","");
		String ruleStr[]=rule_id.split(",");
		String ruleNumStr[]=rule_num.split(",");
		List ruleList=new ArrayList();
		if(ruleStr.length>0){
			for(int i=0;i<ruleStr.length;i++){
				HashMap ruleMap = new HashMap();
				ruleMap.put("rule_id", ruleStr[i]);
				ruleMap.put("internum", ruleNumStr[i]);
				ruleList.add(ruleMap);
			}
		}
		this.interruleService.updateInterruleList(ruleList);
		this.addActionMessage("修改积分规则信息成功");
		return list();
	}
	/**
	 * 方法描述：删除积分规则表信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map pageMap = new HashMap();	
		//过滤地区
		pageMap=super.areafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.interruleService.getCount(pageMap);		
		//分页插件
		pageMap = super.pageTool(count,pageMap);		
		interruleList = this.interruleService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出积分规则表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.interrule.getRule_id();
		if(id==null || id.equals("")){
			interrule = new Interrule();
		}else{
			interrule = this.interruleService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the InterruleList
	 */
	public List getInterruleList() {
		return interruleList;
	}
	/**
	 * @param interruleList
	 *  the InterruleList to set
	 */
	public void setInterruleList(List interruleList) {
		this.interruleList = interruleList;
	}
	
	/**
	 * @return the interrule
	 */
	public Interrule getInterrule() {
		return interrule;
	}
	/**
	 * @param Interrule
	 *            the interrule to set
	 */
	public void setInterrule(Interrule interrule) {
		this.interrule = interrule;
	}
	
	public void prepare() throws Exception { super.saveRequestParameter();
		if(interrule == null){
			interrule = new Interrule();
		}
		String id = this.interrule.getRule_id();
		if(!ValidateUtil.isDigital(id)){
			interrule = this.interruleService.get(id);
		}
	}

}

