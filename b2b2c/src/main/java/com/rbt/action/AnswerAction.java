/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: AnswerAction.java 
 */
package com.rbt.action;

import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.rbt.action.BaseAction;
import com.rbt.common.Constants;
import com.rbt.common.util.IpSeekerInit;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Answer;
import com.rbt.model.Ask;
import com.rbt.model.Memberinter;
import com.rbt.service.IAnswerService;
import com.rbt.service.IAskService;
import com.rbt.service.IMemberinterService;
 
/**
 * @function 功能 答案信息action类
 * @author 创建人 邱景岩
 * @date 创建日期 Mon Jul 25 13:28:09 CST 2011
 */
@Controller             
public class AnswerAction extends BaseAction implements Preparable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -4435193364954159567L;
	/*
	 * 搜索字段 answer_desc_s:答案内容 ip_s：回答人IP isselect_s：是否最佳 info_state_s：信息状态
	 * in_date_s：最短回答时间 end_date_s：最长回答时间 ask_id_s:问题字段
	 */
	public String answer_desc_s;
	public String ip_s;
	public String isselect_s;
	public String info_state_s;
	public String in_date_s;
	public String end_date_s;
	public String title_s;
	public String answer_id_s;
	public String ask_id_s;
	/*
	 * 答案信息对象
	 */
	public Answer answer;
	/*
	 * 问题对象
	 */
	public Ask ask;
	/*
	 * 会员积分对象
	 */
	public Memberinter memberinter;
	/*
	 * 答案业务层接口
	 */
	@Autowired
	public IAnswerService answerService;
	/*
	 * 问题业务接口
	 */
	@Autowired
	public IAskService askService;
	/*
	 * 会员积分业务接口
	 */
	@Autowired
	public IMemberinterService memberinterService;
	/*
	 * 答案信息信息集合
	 */
	public List answerList;

	/**
	 * 方法描述：返回新增答案信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		if(answer != null && answer.getAsk_id() != null){
			ask = this.askService.get(answer.getAsk_id());
			// 获取问题标题
			title_s = ask.getTitle();
		}
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增答案信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		answer.setUser_id(this.session_user_id);
		String ip =IpSeekerInit.getIpAddr(getRequest());
		answer.setIp(ip);
		//字段验证
		super.commonValidateField(answer);
		if(super.ifvalidatepass){
			return add();
		}
		this.answerService.insert(answer);
		this.addActionMessage("新增答案成功");
		this.answer = null;
		return list();
	}

	/**
	 * 方法描述：修改答案信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String id = answer.getAnswer_id();
		//判断实体ID是否存在,若不存在该实体，返回到列表页，不进行任何操作
		if (ValidateUtil.isDigital(id)) {
			return list();
		}
		answer.setUser_id(this.session_user_id);
		//字段验证
		super.commonValidateField(answer);
		if(super.ifvalidatepass){
			return view();
		}
		this.answerService.update(answer);
		this.addActionMessage("修改答案成功");
		return list();
	}

	/**
	 * 方法描述：删除答案信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.answer.getAnswer_id();
		id = id.replace(" ", "");
		this.answerService.delete(id);
		this.addActionMessage("删除答案成功");
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
		if (ask_id_s != null && !ask_id_s.equals("")) {
			pageMap.put("ask_id", ask_id_s);
		}
		if (answer_desc_s != null && !answer_desc_s.equals("")) {
			pageMap.put("answer_desc", answer_desc_s);
		}
		if (ip_s != null && !ip_s.equals("")) {
			pageMap.put("ip", ip_s);
		}
		if (isselect_s != null && !isselect_s.equals("")) {
			pageMap.put("isselect", isselect_s);
		}
		if (info_state_s != null && !info_state_s.equals("")) {
			pageMap.put("info_state", info_state_s);
		}
		if (in_date_s != null && !in_date_s.equals("")) {
			pageMap.put("in_date", in_date_s);
		}
		if (end_date_s != null && !end_date_s.equals("")) {
			pageMap.put("end_date", end_date_s);
		}
		
		//过滤地区
		pageMap=super.areafilter(pageMap);
		
		// 根据页面提交的条件找出信息总数
		int count = this.answerService.getCount(pageMap);

		//分页插件
		pageMap = super.pageTool(count, pageMap);

		answerList = this.answerService.getList(pageMap);
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出答案信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		return goUrl(VIEW);
	}

	/**
	 * @function 功能 根据主键找出答案信息
	 * @author 创建人 邱景岩
	 * @date 创建日期 Aug 10, 2011 5:22:38 PM
	 */

	public String audit() throws Exception {
		return goUrl(AUDIT);
	}

	/**
	 * @function 功能 审核答案
	 * @author 创建人 邱景岩
	 * @date 创建日期 Aug 10, 2011 5:34:00 PM
	 */
	public String auditstate() throws Exception {
		String id = answer.getAnswer_id();
		// 判断实体ID是否存在,若不存在该实体，返回到审核列表页，不进行任何操作
		if (ValidateUtil.isDigital(id)) {
			return list();
		}
        //审核状态为通过并且选择的是最佳答案时
		if (answer.getInfo_state().equals("1")
				&& answer.getIsselect().equals("1")) {
			// 判断该问题是否解决，若已解决则不能再选最佳答案
			Map answerMap = new HashMap();
			answerMap.put("ask_id", answer.getAsk_id());
			answerMap.put("isselect", answer.getIsselect());
			int bestanswer = this.answerService.getCount(answerMap);
			if (bestanswer != 0) {
				this.addFieldError("answer.isselect", "该问题已有最佳答案，请重新选择");
				return audit();
			} else {
				ask = this.askService.get(answer.getAsk_id());
				ask.setAnswer_state("1");// 1:表示问题已解决状态
				this.askService.update(ask);
			}
		}
		Map stateMap = new HashMap();
		stateMap.put("answer_id", answer.getAnswer_id());
		stateMap.put("isselect", answer.getIsselect());
		stateMap.put("info_state", answer.getInfo_state());
		this.answerService.auditAnswer(stateMap);
		this.addActionMessage("已审核答案");
		return list();
	}

	/**
	 * 方法描述：选择最佳答案的时候更改提问者和回答的积分值
	 * 
	 * @return
	 * @throws Exception
	 */
	public void updateIntegral() throws Exception {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		PrintWriter out = response.getWriter();
		isselect_s = request.getParameter("isselect_s");
		//设置为最佳答案时执行操作
		if("1".equals(isselect_s)){
			this.answerService.updateIntegral(ask_id_s,answer_id_s);
		}
		out.write("1");
	}

	/**
	 * 方法描述：找出问题对应的所有答案
	 * 
	 * @return
	 * @throws Exception
	 */
	public String isselectlist() throws Exception {
		Map pageMap = new HashMap();
		if (ask_id_s != null && !"".equals(ask_id_s)) {
			pageMap.put("ask_id", ask_id_s);
			ask = this.askService.get(ask_id_s);
			title_s = ask.getTitle();
		}
		// 根据页面提交的条件找出信息总数
		int count = this.answerService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		answerList = this.answerService.getList(pageMap);
		return INPUT;
	}

	/**
	 * @return the AnswerList
	 */
	public List getAnswerList() {
		return answerList;
	}

	/**
	 * @param answerList
	 *            the AnswerList to set
	 */
	public void setAnswerList(List answerList) {
		this.answerList = answerList;
	}

	/**
	 * @return the answer_desc_s
	 */
	public String getAnswer_desc_s() {
		return answer_desc_s;
	}

	/**
	 * @param answer_desc_s
	 *            the answer_desc_s to set
	 */
	public void setAnswer_desc_s(String answer_desc_s) {
		this.answer_desc_s = answer_desc_s;
	}

	/**
	 * @return the ip_s
	 */
	public String getIp_s() {
		return ip_s;
	}

	/**
	 * @param ip_s
	 *            the ip_s to set
	 */
	public void setIp_s(String ip_s) {
		this.ip_s = ip_s;
	}

	/**
	 * @return the isselect_s
	 */
	public String getIsselect_s() {
		return isselect_s;
	}

	/**
	 * @param isselect_s
	 *            the isselect_s to set
	 */
	public void setIsselect_s(String isselect_s) {
		this.isselect_s = isselect_s;
	}

	/**
	 * @return the info_state_s
	 */
	public String getInfo_state_s() {
		return info_state_s;
	}

	/**
	 * @param info_state_s
	 *            the info_state_s to set
	 */
	public void setInfo_state_s(String info_state_s) {
		this.info_state_s = info_state_s;
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
	 * @return the ask_id_s
	 */
	public String getAsk_id_s() {
		return ask_id_s;
	}

	/**
	 * @param ask_id_s
	 *            the ask_id_s to set
	 */
	public void setAsk_id_s(String ask_id_s) {
		this.ask_id_s = ask_id_s;
	}

	public String getAnswer_id_s() {
		return answer_id_s;
	}

	public void setAnswer_id_s(String answer_id_s) {
		this.answer_id_s = answer_id_s;
	}

	/**
	 * @return the answer
	 */
	public Answer getAnswer() {
		return answer;
	}

	/**
	 * @param Answer
	 *            the answer to set
	 */
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	public Ask getAsk() {
		return ask;
	}

	public void setAsk(Ask ask) {
		this.ask = ask;
	}

	public Memberinter getMemberinter() {
		return memberinter;
	}

	public void setMemberinter(Memberinter memberinter) {
		this.memberinter = memberinter;
	}

	/**
	 * 方法描述：在执行这个类中的其他方法前先调用此方法，保证存在实体类
	 * 
	 * @return
	 * @throws Exception
	 */
	public void prepare() throws Exception { super.saveRequestParameter();
		if (answer == null) {
			answer = new Answer();
		}
		String id = this.answer.getAnswer_id();
		if (!ValidateUtil.isDigital(id)) {
			answer = this.answerService.get(id);
		}
	}
}
