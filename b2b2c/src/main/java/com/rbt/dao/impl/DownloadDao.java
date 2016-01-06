/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: DownloadDao.java 
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
import com.rbt.dao.IDownloadDao;
import com.rbt.dao.IInfoattrDao;
import com.rbt.model.Classified;
import com.rbt.model.Download;
import com.rbt.model.Infoattr;
import com.rbt.model.Supply;

/**
 * @function 功能  记录下载信息dao层业务接口实现类
 * @author 创建人 蔡毅存
 * @date 创建日期 Thu Jul 28 13:55:21 CST 2011
 */
@Repository
public class DownloadDao extends GenericDao<Download,String> implements IDownloadDao {

	@Autowired
	private IInfoattrDao infoattrDao;
	
	public DownloadDao() {
		super(Download.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IGalleryDao#updateGallery(com.rbt.model.Gallery)
	 */
	public void updateauditDownload(Download download) {
		this.getSqlMapClientTemplate().update("download.updateaudit", download);
	}
	
	/**
	 * 方法描述：批量修改
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateDownloadState(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("download.updateisrecom", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}
	public void updateClickNum(String pk)
    {
      this.getSqlMapClientTemplate().update("download.updateclicknum", pk);
    }
	public void updateDownNum(String pk)
    {
      this.getSqlMapClientTemplate().update("download.updatedownnum", pk);
    }
	/* (non-Javadoc)
	 * @see com.rbt.dao.IDownloadDao#getWebDownloadCount(java.util.Map)
	 */
	public int getWebDownloadCount(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject(
				"download.getWebDownloadCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}

	/* (non-Javadoc)
	 * @see com.rbt.dao.IDownloadDao#getWebDownloadList(java.util.Map)
	 */
	public List getWebDownloadList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("download.getWebDownloadList",
				map);
	}
	
	/*
	 * 重载InsertGetPk方法（重载：方法名相同，参数不同）
	 * @see com.rbt.dao.impl.GenericDao#insertGetPk(java.lang.Object)
	 */
	public String insertGetPk(Download t,List objList) {
		if(objList != null && objList.size()>0){
			for(int i = 0;i<objList.size();i++){
				this.infoattrDao.insert((Infoattr) objList.get(i));
			}
		}
		return super.insertGetPk(t);
	}
	
	/*
	 * 重载update方法（重载：方法名相同，参数不同）
	 * @see com.rbt.dao.impl.GenericDao#update(java.lang.Object)
	 */
	public void update(Download t,List objList,String infoid){
		if(infoid !=null && "".equals(infoid)){
			this.infoattrDao.delete(infoid);
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
			Download download = super.get(ids[i].trim());
			if(download!=null&&download.getInfoattr_id()!=null&&!download.getInfoattr_id().equals("")){
				String infoattr_id = download.getInfoattr_id();
				this.infoattrDao.delete(infoattr_id);
			}			
		}		
	}
}

