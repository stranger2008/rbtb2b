/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IMemberDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Member;

/**
 * @function 功能 会员dao层业务接口
 * @author 创建人邱景岩
 * @date 创建日期 Wed Jul 13 08:48:07 CST 2011
 */

public interface IMemberDao extends IGenericDao<Member, String> {

	/**
	 * 方法描述：插入会员并且返回该会员ID
	 * 
	 * @param member
	 */
	public String insertMember(Member member);

	/**
	 * 方法描述：审核会员
	 * 
	 * @param member
	 */
	public void updateMemberState(Map map);

	/**
	 * 方法描述：修改会员后台联系人信息
	 * 
	 * @param member
	 */
	public void updateContactInfo(Member member);

	/**
	 * 方法描述：批量修改推荐
	 * 
	 * @param map
	 * @return java.util.List
	 */
	public void updateIsrecom(List list);
	
	/**
	 * 删除会员相关数据
	 * @param id
	 */
	public void deleteRelate(String id);

	/**
	 * 方法描述：根据条件查询会员表信息
	 * 
	 * @param pk
	 * @return java.util.Map
	 */
	public List getIndexList(Map map);

	/**
	 * @MethodDescribe 方法描述 绑定前台的指定数据
	 * @author 创建人 林俊钦
	 * @date 创建日期 Sep 5, 2011 2:20:56 PM
	 */
	@SuppressWarnings("unchecked")
	public List getWebMemberList(Map map);

	/**
	 * @MethodDescribe 方法描述 绑定前台指定数据的条数
	 * @author 创建人 林俊钦
	 * @date 创建日期 Sep 5, 2011 2:21:03 PM
	 */
	@SuppressWarnings("unchecked")
	public int getWebMemberCount(Map map);

	/**
	 * @MethodDescribe 方法描述 绑定前台会员中心的数据
	 * @author 创建人 胡惜坤
	 * @date 创建日期 Sep 5, 2011 2:21:03 PM
	 */
	public List getWebMemberHomeList(Map map);
	
	/**
	 * @MethodDescribe 方法描述   绑定前台会员中心的信息统计 
	 * @author  创建人  林俊钦
	 * @date  创建日期  Sep 5, 2011 2:21:03 PM
	 */
	public List getWebMemberModCountList(Map map);
}
