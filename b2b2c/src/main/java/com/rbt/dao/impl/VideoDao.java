/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: VideoDao.java 
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
import com.rbt.dao.IInfoattrDao;
import com.rbt.dao.IVideoDao;
import com.rbt.model.Gallery;
import com.rbt.model.Infoattr;
import com.rbt.model.Member;
import com.rbt.model.Subject;
import com.rbt.model.Video;

/**
 * @function 功能  记录视频信息dao层业务接口实现类
 * @author 创建人 蔡毅存
 * @date 创建日期 Wed Jul 27 09:07:53 CST 2011
 */
@Repository
public class VideoDao extends GenericDao<Video,String> implements IVideoDao {
	@Autowired
	private IInfoattrDao infoattrDao;
	public VideoDao() {
		super(Video.class);
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IGalleryDao#updateGallery(com.rbt.model.Gallery)
	 */
	public void updateauditVideo(Video video) {
		this.getSqlMapClientTemplate().update("video.updateaudit", video);
	}
	
	/**
	 * 方法描述：批量修改
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateGalleryState(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("video.updateisrecom", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});

	}
	public void updateClickNum(String pk)
    {
      this.getSqlMapClientTemplate().update("video.updateclicknum", pk);
    }

	/* (non-Javadoc)
	 * @see com.rbt.dao.IVideoDao#getWebVideoCount(java.util.Map)
	 */
	public int getWebVideoCount(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject(
				"video.getWebVideoCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}

	/* (non-Javadoc)
	 * @see com.rbt.dao.IVideoDao#getWebVideoList(java.util.Map)
	 */
	public List getWebVideoList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("video.getWebVideoList",
				map);
	}
	
	
	public String insertGetPk(Video t, List objList) {
		if(objList != null && objList.size()>0){
			for(int i = 0;i<objList.size();i++){
				this.infoattrDao.insert((Infoattr) objList.get(i));
			}
		}
		return super.insertGetPk(t);
	}

	public void update(Video t, List objList, String id) {
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
			Video video = super.get(ids[i].trim());
			if(video!=null&&video.getInfoattr_id()!=null&&!video.getInfoattr_id().equals("")){
				String infoattr_id = video.getInfoattr_id();
				this.infoattrDao.delete(infoattr_id);
			}			
		}		
	}
}

