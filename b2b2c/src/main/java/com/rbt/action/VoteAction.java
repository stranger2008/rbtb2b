/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: VoteAction.java 
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
import com.rbt.model.Vote;
import com.rbt.service.IVoteService;

/**
 * @function 功能  在线调查action类
 * @author  创建人 蔡毅存
 * @date  创建日期  July 8, 2011
 */
@Controller
public class VoteAction extends BaseAction implements Preparable{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 2115744686191990436L;
	/*
	 * 注入navService
	 */
	@Autowired
	public IVoteService voteService;
	/*
	 * 在线调查对象
	 */
	public Vote vote;
    /*
     * 在线调查列表
 	*/
	public List voteList;
    /*
  	* 是否显示字段
  	*/
	public String is_multi;
    /*
     * 搜索字段
     */
	public String vote_title_s;
	public String vote_is_multi_s;
	public String is_multi_s;
	public String starttime_s;
	public String endtime_s;
      
	/**
	 * 方法描述：根据主键找出在线调查信息
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

		vote.setUser_id((String) getSession().getAttribute(
				Constants.USER_ID));
		//字段验证
		super.commonValidateField(vote);
		if(super.ifvalidatepass){
			return view();
		}
		this.voteService.update(vote);
		this.addActionMessage("修改在线调查成功");
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
		if(is_multi_s!=null && !is_multi_s.equals("")) pageMap.put("is_multi", is_multi_s);
		if(vote_title_s!=null && !vote_title_s.equals("")) pageMap.put("vote_title", vote_title_s);
		if(vote_is_multi_s!=null && !vote_is_multi_s.equals("")) pageMap.put("is_multi", vote_is_multi_s);
		//过滤地区
		pageMap=super.areafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.voteService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		//找出信息列表，放入list
		voteList=this.voteService.getList(pageMap);
		return goUrl(INDEXLIST);
	} 
	
	/**
	 * 方法描述：删除导航链接信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String voteid = this.vote.getVote_id();
		voteid = voteid.replace(" ", "");
		this.voteService.delete(voteid);
		this.addActionMessage("删除导航链接成功");
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
	    
		vote.setStart_date(starttime_s);
		vote.setEnd_date(endtime_s);
		if("1".equals(vote.getIs_multi())){
			vote.setIs_multi("checkbox");
		}else{
			vote.setIs_multi("radio");
		}
		vote.setUser_id((String) getSession().getAttribute(
				Constants.USER_ID));
		//字段验证
		super.commonValidateField(vote);
		if(super.ifvalidatepass){
			return add();
		}
		this.voteService.insert(vote);
		this.addActionMessage("新增在线调查信息成功");
		this.vote = null;
		return add();
	}
	
	/**
	 * 方法描述：批量修改单选多选
	 * @return
	 * @throws Exception
	 */
	public String updateis_multiState() throws Exception {
		String voteid = this.vote.getVote_id();
		String is_multi = this.vote.getIs_multi();
		voteid = voteid.replace(" ", "");
		String voteidStr[] = voteid.split(",");
		List voteLists = new ArrayList();
		if(voteidStr.length>0){
			for(int i=0;i<voteidStr.length;i++){
				Map voteMap = new HashMap();
				voteMap.put("is_multi", is_multi);
				voteMap.put("vote_id", voteidStr[i]);
				voteLists.add(voteMap);
			}
		}
		this.voteService.updateis_multiState(voteLists);
		String tip = "";
		if(is_multi.equals("1")){
			tip = "多选选择成功";
		}else if(is_multi.equals("2")){
			tip = "单选选择成功";
		}
		this.addActionMessage(tip);
		return list();
	}
	
	
	/**
	 * 方法描述：get/set方法
	 * @return
	 * @throws Exception
	 */
	public Vote getVote() {
		return vote;
	}

	public void setVote(Vote vote) {
		this.vote = vote;
	}
	
	public List getVoteList() {
		return voteList;
	}

	public void setVoteList(List voteList) {
		this.voteList = voteList;
	}

	public String getIs_multi() {
		return is_multi;
	}

	public void setIs_multi(String is_multi) {
		this.is_multi = is_multi;
	}
	
	public String getVote_title_s() {
		return vote_title_s;
	}

	public void setVote_title_s(String vote_title_s) {
		this.vote_title_s = vote_title_s;
	}

	

	public String getVote_is_multi_s() {
		return vote_is_multi_s;
	}

	public void setVote_is_multi_s(String vote_is_multi_s) {
		this.vote_is_multi_s = vote_is_multi_s;
	}

	public String getIs_multi_s() {
		return is_multi_s;
	}

	public void setIs_multi_s(String is_multi_s) {
		this.is_multi_s = is_multi_s;
	}


	public String getStarttime_s() {
		return starttime_s;
	}

	public void setStarttime_s(String starttime_s) {
		this.starttime_s = starttime_s;
	}

	public String getEndtime_s() {
		return endtime_s;
	}

	public void setEndtime_s(String endtime_s) {
		this.endtime_s = endtime_s;
	}

	public void prepare() throws Exception { super.saveRequestParameter();
		if(vote == null){
			vote = new Vote();
		}
		String id = vote.getVote_id();
		if(!ValidateUtil.isDigital(id)){
			vote = this.voteService.get(id);
		}
	}
	
	
	
	
    
}
