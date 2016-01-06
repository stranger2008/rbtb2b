/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: MembertemplateService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.IMembertemplateDao;
import com.rbt.model.Membertemplate;
import com.rbt.service.IMembertemplateService;

/**
 * @function 功能 记录企业站模板信息Service层业务接口实现
 * @author 创建人 蔡毅存
 * @date 创建日期 Thu Aug 25 14:37:44 CST 2011
 */
@Service
public class MembertemplateService extends GenericService<Membertemplate,String> implements IMembertemplateService {

	
	IMembertemplateDao membertemplateDao;

	@Autowired
	public MembertemplateService(IMembertemplateDao membertemplateDao) {
		super(membertemplateDao);
		this.membertemplateDao = membertemplateDao;
	}
	
	/**
	 * 批量更新sort_no排序字段
	 */
	public void updatesort_no(List list) {
		this.membertemplateDao.updatetemp_sort(list);
	}

}

