/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IMembertemplateService.java 
 */
package com.rbt.service;

import java.util.List;
import com.rbt.model.Membertemplate;

/**
 * @function 功能 记录企业站模板信息Service层业务接口实现类
 * @author  创建人 蔡毅存
 * @date  创建日期 Thu Aug 25 14:37:44 CST 2011
 */

public interface IMembertemplateService extends IGenericService<Membertemplate,String>{
	
	/**
	 * 方法描述：批量修改模板排序
	 * @param pk
	 * @return java.util.Map
	 */
	public void updatesort_no(List list);
}

