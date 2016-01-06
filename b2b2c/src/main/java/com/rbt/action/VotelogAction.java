/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: VotelogAction.java 
 */
package com.rbt.action;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Vote;
import com.rbt.model.Vote_log;
import com.rbt.service.IVoteService;
import com.rbt.service.IVote_logService;

/**
 * @function 功能  在线调查记录action类
 * @author  创建人 蔡毅存
 * @date  创建日期  July 8, 2011
 */
@Controller
public class VotelogAction extends BaseAction implements Preparable{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1312695036839018415L;
	/*
	 * 在线调查记录对象
	 */
	public Vote_log vote_log;
	/*
	 * 注入vote_logService
	 */
	@Autowired
	public IVote_logService vote_logService;
    @Autowired
    public IVoteService voteService;
	/*
	 *搜索字段
	 */
	public String vote_id_s;
    /*
	 *在线调查记录列表
	 */
	public List vote_logList;
	 /*
	 * 在线调查记录对象
	 */
	public List voteoptionList;
    /*
	 *在线调查ID号
	 */
	public String vote_id;
    /*
	 * 在线调查记录对象
	 */
	public Vote vote;

	/**
	 * 方法描述：根据主键找出在线调记录信息
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		return goUrl(VIEW);
	}
	
	/**
	 * 方法描述：修改在线调查记录信息
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		this.vote_logService.update(vote_log);
		this.addActionMessage("修改在线调查记录成功");
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
		int count=this.vote_logService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		//找出信息列表，放入list
		vote_logList=this.vote_logService.getList(pageMap);
		return goUrl(INDEXLIST);
	} 
	
	/**
	 * 方法描述：删除在线调查选项信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String logid = this.vote_log.getLog_id();
		logid = logid.replace(" ", "");
		this.vote_logService.delete(logid);
		this.addActionMessage("删除在线调查选项成功");
		return list();
	}
	
	/**
	 * 方法描述：返回新增页面
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		vote=this.voteService.get(vote_id);
		Map map=new HashMap();
		map.put("vote_id", vote_id);
		voteoptionList=this.vote_logService.getVoteoption(map);
		return goUrl(ADD);
	}
	
	/**
	 * 方法描述：新增友情链接信息
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		vote_log.setVote_id(vote_id);
		this.vote_logService.insert(vote_log);
		this.addActionMessage("新增在线调查选项成功");
		this.vote_log = null;
		return add();
	}
	
	
	
	/**
	 * 方法描述：get/set方法
	 * @return
	 * @throws Exception
	 */
	public String getVote_id() {
		return vote_id;
	}
	public void setVote_id(String vote_id) {
		this.vote_id = vote_id;
	}

	public Vote_log getVote_log() {
		return vote_log;
	}

	public void setVote_log(Vote_log vote_log) {
		this.vote_log = vote_log;
	}

	public List getVote_logList() {
		return vote_logList;
	}

	public void setVote_logList(List vote_logList) {
		this.vote_logList = vote_logList;
	}

	public List getVoteoptionList() {
		return voteoptionList;
	}

	public void setVoteoptionList(List voteoptionList) {
		this.voteoptionList = voteoptionList;
	}

	public Vote getVote() {
		return vote;
	}

	public void setVote(Vote vote) {
		this.vote = vote;
	}
	
	public String getVote_id_s() {
		return vote_id_s;
	}

	public void setVote_id_s(String vote_id_s) {
		this.vote_id_s = vote_id_s;
	}

	public void prepare() throws Exception { super.saveRequestParameter();
		if(vote_log == null){
			vote_log = new Vote_log();
		}
		String id = vote_log.getLog_id();
		if(!ValidateUtil.isDigital(id)){
			vote_log = this.vote_logService.get(id);
		}
	}

}
