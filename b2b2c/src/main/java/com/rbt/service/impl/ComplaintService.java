/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: ComplaintService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IComplaintDao;
import com.rbt.model.Complaint;
import com.rbt.service.IComplaintService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 会员投诉信息Service层业务接口实现
 * @author 创建人 蔡毅存
 * @date 创建日期 Fri Dec 02 11:20:21 CST 2011
 */
@Service
public class ComplaintService extends GenericService<Complaint,String> implements IComplaintService {
	
	IComplaintDao complaintDao;

	@Autowired
	public ComplaintService(IComplaintDao complaintDao) {
		super(complaintDao);
		this.complaintDao = complaintDao;
	}
	
}

