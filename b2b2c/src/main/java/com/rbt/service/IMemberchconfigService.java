/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IMemberchconfigService.java 
 */
package com.rbt.service;

import java.util.List;
import com.rbt.model.Memberchconfig;

/**
 * @function 功能 记录会员企业站栏目配置信息Service层业务接口实现类
 * @author  创建人 蔡毅存
 * @date  创建日期 Fri Aug 26 13:24:50 CST 2011
 */

public interface IMemberchconfigService extends IGenericService<Memberchconfig,String>{
	
	/**
	 * 方法描述：批量修改是否显示
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateisdis(List list);
	
	/**
	 * 方法描述：批量修改排序
	 * @param pk
	 * @return java.util.Map
	 */
	public void updatesort_no(List list);
}

