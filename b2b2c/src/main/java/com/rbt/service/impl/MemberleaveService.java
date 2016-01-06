/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: MemberleaveService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.IMemberleaveDao;
import com.rbt.model.Memberleave;
import com.rbt.service.IMemberleaveService;

/**
 * @function 功能 记录会员留言信息表Service层业务接口实现
 * @author 创建人 蔡毅存
 * @date 创建日期 Mon Jul 25 08:40:47 CST 2011
 */
@Service
public class MemberleaveService extends GenericService<Memberleave,String> implements IMemberleaveService {

	IMemberleaveDao memberleaveDao;

	@Autowired
	public MemberleaveService(IMemberleaveDao memberleaveDao) {
		super(memberleaveDao);
		this.memberleaveDao = memberleaveDao;
	}
	
	/**
	 * 批量更新Nav是否显示
	 */
	public void updateisdel(List list) {
		this.memberleaveDao.updateMemberleaveState(list);

	}
}

