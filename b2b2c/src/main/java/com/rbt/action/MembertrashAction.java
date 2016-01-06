/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: MembertrashAction.java 
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Membertrash;
import com.rbt.service.IMembertrashService;
/**
 * @function 功能 记录回收站中信息action类
 * @author 创建人 林俊钦
 * @date 创建日期 Wed Sep 28 21:36:10 CST 2011
 */
@Controller
public class MembertrashAction extends BaseAction implements Preparable {
	
	private static final long serialVersionUID = 1L;
	/*
	 * 记录回收站中信息对象
	 */
	public Membertrash membertrash;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IMembertrashService membertrashService;
	/*
	 * 记录回收站中信息信息集合
	 */
	public List membertrashList;

	
	/**
	 * 方法描述：返回新增记录回收站中信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录回收站中信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		this.membertrashService.insert(membertrash);
		this.addActionMessage("新增回收站中信息成功");
		this.membertrash = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录回收站中信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String idString=membertrash.getInfo_id();
		if(ValidateUtil.isDigital(idString))
		{
			return list();
		}
		this.membertrashService.update(membertrash);
		this.addActionMessage("修改回收站中信息成功");
		return list();
	}
	/**
	 * 方法描述：删除记录回收站中信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.membertrash.getInfo_id();
		id = id.replace(" ", "");
		this.membertrashService.delete(id);
		this.addActionMessage("删除回收站中信息成功");
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map pageMap = new HashMap();
		membertrashList = this.membertrashService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录回收站中信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		
		return goUrl(VIEW);
	}
	/**
	 * @return the MembertrashList
	 */
	public List getMembertrashList() {
		return membertrashList;
	}
	/**
	 * @param membertrashList
	 *  the MembertrashList to set
	 */
	public void setMembertrashList(List membertrashList) {
		this.membertrashList = membertrashList;
	}
	
	
	/**
	 * @return the membertrash
	 */
	public Membertrash getMembertrash() {
		return membertrash;
	}
	/**
	 * @param Membertrash
	 *            the membertrash to set
	 */
	public void setMembertrash(Membertrash membertrash) {
		this.membertrash = membertrash;
	}
	
	public void prepare() throws Exception { super.saveRequestParameter();
		if(membertrash==null)
		{
			membertrash=new Membertrash();
		}
		String id=this.membertrash.getInfo_id();
		if(!ValidateUtil.isDigital(id))
		{
			membertrash=this.membertrashService.get(id);
		}
	}

}

