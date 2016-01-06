/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: GalleryService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.IGalleryDao;
import com.rbt.model.Gallery;
import com.rbt.service.IGalleryService;

/**
 * @function 功能 记录图库信息Service层业务接口实现
 * @author 创建人 蔡毅存
 * @date 创建日期 Mon Jul 25 14:50:37 CST 2011
 */
@Service
public class GalleryService extends GenericService<Gallery,String> implements IGalleryService {

	
	IGalleryDao galleryDao;

	@Autowired
	public GalleryService(IGalleryDao galleryDao) {
		super(galleryDao);
		this.galleryDao = galleryDao;
	}
	

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IGalleryService#getGalleryList(java.util.Map)
	 */
	public List getGalleryIDList(Map map) {
		return this.galleryDao.getGalleryIDList(map);
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IGalleryService#updateGallery(com.rbt.model.Gallery)
	 */
	public void updateauditGallery(Gallery gallery) {
		this.galleryDao.updateauditGallery(gallery);
	}

	/**
	 * 批量更新Nav是否显示
	 */
	public void updateisrecom(List list) {
		this.galleryDao.updateGalleryState(list);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IGalleryService#getWebGalleryCount(java.util.Map)
	 */
	public int getWebGalleryCount(Map map) {
		return this.galleryDao.getWebGalleryCount(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IGalleryService#getWebGalleryList(java.util.Map)
	 */
	public List getWebGalleryList(Map map) {
		return this.galleryDao.getWebGalleryList(map);
	}



	public String insertGetPk(Gallery t, List objList) {
		return this.galleryDao.insertGetPk(t, objList);
	}



	public void update(Gallery t, List objList, String id) {
		this.galleryDao.update(t, objList, id);
	}

}
