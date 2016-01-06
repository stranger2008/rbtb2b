/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: MemberchconfigService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.IMemberchconfigDao;
import com.rbt.model.Memberchconfig;
import com.rbt.service.IMemberchconfigService;

/**
 * @function 功能 记录会员企业站栏目配置信息Service层业务接口实现
 * @author 创建人 蔡毅存
 * @date 创建日期 Fri Aug 26 13:24:50 CST 2011
 */
@Service
public class MemberchconfigService extends GenericService<Memberchconfig,String> implements IMemberchconfigService {

	IMemberchconfigDao memberchconfigDao;

	@Autowired
	public MemberchconfigService(IMemberchconfigDao memberchconfigDao) {
		super(memberchconfigDao);
		this.memberchconfigDao = memberchconfigDao;
	}

	
	/**
	 * 批量更新是否显示
	 */
	public void updateisdis(List list) {
		this.memberchconfigDao.updateState(list);

	}
	
	/**
	 * 批量更新sort_no排序字段
	 */
	public void updatesort_no(List list) {
		this.memberchconfigDao.update_sort(list);
	}

}

