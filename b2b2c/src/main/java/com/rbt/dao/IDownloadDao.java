/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IDownloadDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Download;

/**
 * @function 功能 记录下载信息dao层业务接口
 * @author  创建人蔡毅存
 * @date  创建日期 Thu Jul 28 13:55:21 CST 2011
 */

public interface IDownloadDao extends IGenericDao<Download,String>{

	/**
	 * 方法描述：修改记录图库信息
	 * @param gallery
	 */
	public void updateauditDownload(Download download);

	/**
	 * 方法描述：批量修改推荐
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public void updateDownloadState(List lists);
    /**
	 * @MethodDescribe 方法描述    前台下载列表绑定
	 * @author  创建人  林俊钦
	 * @date  创建日期  Sep 1, 2011 3:33:41 PM
	 */ 
	@SuppressWarnings("unchecked")
	public List getWebDownloadList(Map map);

    /**
	 * @MethodDescribe 方法描述    前台下载列表绑定的数量
	 * @author  创建人  林俊钦
	 * @date  创建日期  Sep 1, 2011 3:33:41 PM
	 */ 
	@SuppressWarnings("unchecked")
	public int getWebDownloadCount(Map map);

	/**
	 * @MethodDescribe 方法描述    更新浏览次数
	 * @author  创建人  胡惜坤
	 * @param pk
	 */
	public void updateClickNum(String pk);
	/**
	 * @MethodDescribe 方法描述    更新下载次数
	 * @author  创建人  胡惜坤
	 * @param pk
	 */
	public void updateDownNum(String pk);
	
	public String insertGetPk(Download t, List objList);
	    
	public void update(Download t, List objList, String id);

}

