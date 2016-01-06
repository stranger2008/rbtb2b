/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: VideoService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.IVideoDao;
import com.rbt.model.Video;
import com.rbt.service.IVideoService;

/**
 * @function 功能 记录视频信息Service层业务接口实现
 * @author 创建人 蔡毅存
 * @date 创建日期 Wed Jul 27 09:07:53 CST 2011
 */
@Service
public class VideoService extends GenericService<Video,String> implements IVideoService {

	IVideoDao videoDao;

	@Autowired
	public VideoService(IVideoDao videoDao) {
		super(videoDao);
		this.videoDao = videoDao;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IGalleryService#updateGallery(com.rbt.model.Gallery)
	 */
	public void updateauditVideo(Video video) {
		this.videoDao.updateauditVideo(video);
	}
	
	/**
	 * 批量更新Nav是否显示
	 */
	public void updateisrecom(List list) {
		this.videoDao.updateGalleryState(list);

	}
	public void updateClickNum(String pk)
	{
	  this.videoDao.updateClickNum(pk);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.IVideoService#getWebVideoCount(java.util.Map)
	 */
	public int getWebVideoCount(Map map) {
		return this.videoDao.getWebVideoCount(map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.IVideoService#getWebVideoList(java.util.Map)
	 */
	public List getWebVideoList(Map map) {
		return this.videoDao.getWebVideoList(map);
	}

	public String insertGetPk(Video t, List objList) {
		return this.videoDao.insertGetPk(t, objList);
	}

	public void update(Video t, List objList, String id) {
		this.videoDao.update(t, objList, id);
	}

}

