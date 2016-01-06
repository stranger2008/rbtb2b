/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: DownloadService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.IDownloadDao;
import com.rbt.model.Download;
import com.rbt.service.IDownloadService;

/**
 * @function 功能 记录下载信息Service层业务接口实现
 * @author 创建人 蔡毅存
 * @date 创建日期 Thu Jul 28 13:55:21 CST 2011
 */
@Service
public class DownloadService extends GenericService<Download,String> implements IDownloadService {
	
	IDownloadDao downloadDao;

	@Autowired
	public DownloadService(IDownloadDao downloadDao) {
		super(downloadDao);
		this.downloadDao = downloadDao;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IGalleryService#updateGallery(com.rbt.model.Gallery)
	 */
	public void updateauditDownload(Download download) {
		this.downloadDao.updateauditDownload(download);
	}
	
	/**
	 * 批量更新Nav是否显示
	 */
	public void updateisrecom(List list) {
		this.downloadDao.updateDownloadState(list);

	}
	public void updateClickNum(String pk)
	{
	  this.downloadDao.updateClickNum(pk);
	}
	public void updateDownNum(String pk)
	{
	  this.downloadDao.updateDownNum(pk);
	}
	/* (non-Javadoc)
	 * @see com.rbt.service.IDownloadService#getWebDownloadCount(java.util.Map)
	 */
	public int getWebDownloadCount(Map map) {
	   return this.downloadDao.getWebDownloadCount(map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.IDownloadService#getWebDownloadList(java.util.Map)
	 */
	public List getWebDownloadList(Map map) {
		return this.downloadDao.getWebDownloadList(map);
	}
	
	public String insertGetPk(Download t,List objList){
		return this.downloadDao.insertGetPk(t, objList);
	}
	
	public void update(Download t,List objList,String id){
		this.downloadDao.update(t,objList,id);
	}

}

