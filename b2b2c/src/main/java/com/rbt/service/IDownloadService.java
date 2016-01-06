/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IDownloadService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;
import com.rbt.model.Download;

/**
 * @function 功能 记录下载信息Service层业务接口实现类
 * @author  创建人 蔡毅存
 * @date  创建日期 Thu Jul 28 13:55:21 CST 2011
 */

public interface IDownloadService extends IGenericService<Download,String>{
	
	/**
	 * 方法描述：修改记录图库信息
	 * @param gallery
	 */
	public void updateauditDownload(Download download);
	/**
	 * 方法描述：批量修改
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateisrecom(List list);
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
	 */
	public void updateClickNum(String pk);
	/**
	 * @MethodDescribe 方法描述    更新下载次数
	 * @author  创建人  胡惜坤
	 */
	public void updateDownNum(String pk);
	
	public String insertGetPk(Download t, List objList);
	    
	public void update(Download t, List objList, String id);
}

