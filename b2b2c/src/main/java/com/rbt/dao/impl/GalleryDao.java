/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: GalleryDao.java 
 */
package com.rbt.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.rbt.dao.IGalleryDao;
import com.rbt.dao.IInfoattrDao;
import com.rbt.model.Ask;
import com.rbt.model.Download;
import com.rbt.model.Gallery;
import com.rbt.model.Infoattr;
import com.rbt.model.Job;

/**
 * @function 功能 记录图库信息dao层业务接口实现类
 * @author 创建人 蔡毅存
 * @date 创建日期 Mon Jul 25 14:50:37 CST 2011
 */
@Repository
public class GalleryDao extends GenericDao<Gallery,String> implements IGalleryDao {
	@Autowired
	private IInfoattrDao infoattrDao;
	
	public GalleryDao() {
		super(Gallery.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IGalleryDao#getGalleryList(java.util.Map)
	 */
	public List getGalleryIDList(Map map) {

		return this.getSqlMapClientTemplate().queryForList(
				"gallery.getgalleryList", map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IGalleryDao#updateGallery(com.rbt.model.Gallery)
	 */
	public void updateauditGallery(Gallery gallery) {
		this.getSqlMapClientTemplate().update("gallery.updateaudit", gallery);
	}

	/**
	 * 方法描述：批量修改
	 * 
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateGalleryState(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if (list != null && list.size() > 0) {
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap) iter.next();
						executor.update("gallery.updateisrecom", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IGalleryDao#getWebGalleryCount(java.util.Map)
	 */
	public int getWebGalleryCount(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject(
				"gallery.getWebGalleryCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IGalleryDao#getWebGalleryList(java.util.Map)
	 */
	public List getWebGalleryList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("gallery.getWebGalleryList", map);
	}

	public String insertGetPk(Gallery t, List objList) {
		if(objList != null && objList.size()>0){
			for(int i = 0;i<objList.size();i++){
				this.infoattrDao.insert((Infoattr) objList.get(i));
			}
		}
		return super.insertGetPk(t);
	}

	public void update(Gallery t, List objList, String id) {
		if(id !=null && "".equals(id)){
			this.infoattrDao.delete(id);
		}
		if(objList != null && objList.size()>0){
			for(int i= 0;i<objList.size();i++){
				this.infoattrDao.insert((Infoattr) objList.get(i));
			}
		}
		super.update(t);
	}
	

	/*
	 * 重写delete方法（重写：子类继承父类中）
	 * @see com.rbt.dao.impl.GenericDao#delete(java.lang.Object)
	 */
	@Override
	public void delete(String id) {
		super.delete(id);
		String[] ids=id.split(",");
		for(int i=0;i<ids.length;i++){
			Gallery gallery = super.get(ids[i].trim());
			if(gallery!=null&&gallery.getInfoattr_id()!=null&&!gallery.getInfoattr_id().equals("")){
				String infoattr_id = gallery.getInfoattr_id();
				this.infoattrDao.delete(infoattr_id);
			}			
		}		
	}
}
