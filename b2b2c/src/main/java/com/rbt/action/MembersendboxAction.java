/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: MembersendboxAction.java 
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Membersendbox;
import com.rbt.service.IMembersendboxService;
/**
 * @function 功能 记录发件箱的发送信息action类
 * @author 创建人 林俊钦
 * @date 创建日期 Wed Sep 28 21:32:25 CST 2011
 */
@Controller
public class MembersendboxAction extends BaseAction implements Preparable {
	
	private static final long serialVersionUID = 1L;
	/*
	 * 记录发件箱的发送信息对象
	 */
	public Membersendbox membersendbox;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IMembersendboxService membersendboxService;
	/*
	 * 记录发件箱的发送信息信息集合
	 */
	public List membersendboxList;

	
	/**
	 * 方法描述：返回新增记录发件箱的发送信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}
	public void prepare() throws Exception { super.saveRequestParameter();
		if(membersendbox == null){
			membersendbox = new Membersendbox();
		}
		String id = membersendbox.getInfo_id();
		if(!ValidateUtil.isDigital(id)){
			membersendbox = this.membersendboxService.get(id);
		}
	}
	/**
	 * 方法描述：新增记录发件箱的发送信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		this.membersendboxService.insert(membersendbox);
		this.addActionMessage("新增发件箱的发送信息成功");
		this.membersendbox = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录发件箱的发送信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String sendidString=membersendbox.getInfo_id();
		if(ValidateUtil.isDigital(sendidString))
		{
			return list();
		}
		this.membersendboxService.update(membersendbox);
		this.addActionMessage("修改发件箱的发送信息成功");
		return list();
	}
	/**
	 * 方法描述：删除记录发件箱的发送信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.membersendbox.getInfo_id();
		id = id.replace(" ", "");
		this.membersendboxService.delete(id);
		this.addActionMessage("删除发件箱的发送信息成功");
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map pageMap = new HashMap();
		membersendboxList = this.membersendboxService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录发件箱的发送信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		return goUrl(VIEW);
	}
	/**
	 * @return the MembersendboxList
	 */
	public List getMembersendboxList() {
		return membersendboxList;
	}
	/**
	 * @param membersendboxList
	 *  the MembersendboxList to set
	 */
	public void setMembersendboxList(List membersendboxList) {
		this.membersendboxList = membersendboxList;
	}
	
	/**
	 * @return the membersendbox
	 */
	public Membersendbox getMembersendbox() {
		return membersendbox;
	}
	/**
	 * @param Membersendbox
	 *            the membersendbox to set
	 */
	public void setMembersendbox(Membersendbox membersendbox) {
		this.membersendbox = membersendbox;
	}

}

