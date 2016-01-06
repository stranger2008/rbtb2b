/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IResumeinboxDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;
import com.rbt.model.Resumeinbox;

/**
 * @function 功能 简历收件箱表dao层业务接口
 * @author  创建人胡惜坤
 * @date  创建日期 Mon Aug 01 09:17:11 CST 2011
 */

public interface IResumeinboxDao extends IGenericDao<Resumeinbox,String> {
	/**
	 * 方法描述：更新简历收件箱的查看状态
	 * @param resumeinbox
	 */
	public void updateResumeinboxState(Map map);
}

