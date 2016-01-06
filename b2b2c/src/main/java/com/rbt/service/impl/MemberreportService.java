/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: MemberreportService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IMemberreportDao;
import com.rbt.model.Memberreport;
import com.rbt.service.IMemberreportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 会员举报信息表Service层业务接口实现
 * @author 创建人 蔡毅存
 * @date 创建日期 Wed Nov 30 14:19:40 CST 2011
 */
@Service
public class MemberreportService extends GenericService<Memberreport,String> implements IMemberreportService {
	
	IMemberreportDao memberreportDao;

	@Autowired
	public MemberreportService(IMemberreportDao memberreportDao) {
		super(memberreportDao);
		this.memberreportDao = memberreportDao;
	}
	
}

