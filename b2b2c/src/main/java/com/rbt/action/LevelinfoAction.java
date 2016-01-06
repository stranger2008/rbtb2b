/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: LevelinfoAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.opensymphony.xwork2.Preparable;
import com.rbt.action.BaseAction;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Levelinfo;
import com.rbt.model.Member;
import com.rbt.service.ILevelinfoService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMemberlevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 会员级别信息action类
 * @author 创建人 邱景岩
 * @date 创建日期 Tue Jul 19 14:31:17 CST 2011
 */
@Controller
public class LevelinfoAction extends BaseAction  implements Preparable{

	private static final long serialVersionUID = 1L;
	/*
	 * 会员级别信息对象
	 */
	public Levelinfo levelinfo;
	/*
	 * 业务层接口
	 */
	@Autowired
	public ILevelinfoService levelinfoService;
	@Autowired
	public IMemberlevelService memberlevelService;
	@Autowired
	public IMemberService memberService;
	/*
	 * 会员级别信息信息集合
	 */
	public List levelinfoList;
	/*
	 * 会员级别配置信息
	 */
    public List levelList; 
    /*
     * 会员名称
     */
    public String cust_name;
    /*
     * 设置全局变量的ID，因为id需要被另外的表操作处理来获取会员名称
     */
    public String id;
	/**
	 * 方法描述：返回新增会员级别信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增会员级别信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		//字段验证
		super.commonValidateField(levelinfo);
		if(super.ifvalidatepass){
			return add();
		}
		this.levelinfoService.insert(levelinfo);
		this.addActionMessage("新增会员级别信息成功");
		this.levelinfo = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改会员级别信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if(levelinfo.getLevel_code().equals("0")){
			this.addFieldError("levelinfo.level_code", "请选择会员级别");
		}
		levelinfo.setUser_id(this.session_user_id);
		//字段验证
		super.commonValidateField(levelinfo);
		if(super.ifvalidatepass){
			return view();
		}
		this.levelinfoService.update(levelinfo);
		this.addActionMessage("修改会员级别信息成功");
		return view();
	}

	/**
	 * 方法描述：删除会员级别信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.levelinfo.getCust_id();
		id = id.replace(" ", "");
		this.levelinfoService.delete(id);
		this.addActionMessage("删除会员级别信息成功");
		return list();
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		setLevel();
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出会员级别信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		//判断id的值是否符合条件，不符合的话返回到列表
		String rbtid=this.levelinfo.getCust_id();
		if(ValidateUtil.isDigital(rbtid)){
			return list();
		}	
		setLevel();
		if(id!=null){
			Member member = this.memberService.get(id);
			//获取会员名称
			cust_name = member.getCust_name();
		}				
		return goUrl(VIEW);
	}
	
	/**
	 * 方法描述：会员级别集合信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void setLevel(){
		Map levelMap = new HashMap();
		levelList = this.memberlevelService.getList(levelMap);
	}
	/**
	 * @return the LevelinfoList
	 */
	public List getLevelinfoList() {
		return levelinfoList;
	}

	/**
	 * @param levelinfoList
	 *            the LevelinfoList to set
	 */
	public void setLevelinfoList(List levelinfoList) {
		this.levelinfoList = levelinfoList;
	}

	/**
	 * @return the levelinfo
	 */
	public Levelinfo getLevelinfo() {
		return levelinfo;
	}

	/**
	 * @param Levelinfo
	 *            the levelinfo to set
	 */
	public void setLevelinfo(Levelinfo levelinfo) {
		this.levelinfo = levelinfo;
	}

	/**
	 * @return the levelList
	 */
	public List getLevelList() {
		return levelList;
	}

	/**
	 * @param levelList the levelList to set
	 */
	public void setLevelList(List levelList) {
		this.levelList = levelList;
	}

	/**
	 * @return the cust_name
	 */
	public String getCust_name() {
		return cust_name;
	}

	/**
	 * @param cust_name the cust_name to set
	 */
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	/**
	 * @Method Description : 当进入方法后初始化对象
	 * @author : 林俊钦
	 * @date : Nov 8, 2011 2:36:50 PM
	 */
	public void prepare() throws Exception { super.saveRequestParameter();
		if(levelinfo == null){
			levelinfo = new Levelinfo();
		}
		id = levelinfo.getCust_id();
		if(!ValidateUtil.isDigital(id)){
			levelinfo = this.levelinfoService.get(id);
		}
	}
}
