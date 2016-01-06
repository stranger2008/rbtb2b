/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: ClassifiedDao.java 
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
import com.rbt.dao.IClassifiedDao;
import com.rbt.dao.IInfoattrDao;
import com.rbt.model.Buy;
import com.rbt.model.Classified;
import com.rbt.model.Infoattr;
import com.rbt.model.Supply;

/**
 * @function 功能 记录动态分类信息dao层业务接口实现类
 * @author 创建人 蔡毅存
 * @date 创建日期 Fri Oct 14 08:59:55 CST 2011
 */
@Repository
public class ClassifiedDao extends GenericDao<Classified,String> implements IClassifiedDao {

	@Autowired
	private IInfoattrDao infoattrDao;
	
	public ClassifiedDao() {
		super(Classified.class);
	}
	

	/**
	 * 方法描述：批量修改推荐
	 * 
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateClassifiedState(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if (list != null && list.size() > 0) {
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap) iter.next();
						executor.update("classified.updateisrecom", temp);
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
	 * @see com.rbt.dao.IGalleryDao#updateGallery(com.rbt.model.Gallery)
	 */
	public void updateauditClassified(Classified classified) {
		this.getSqlMapClientTemplate().update("classified.updateaudit",
				classified);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IClassifiedDao#getWebClassifiedCount(com.rbt.model.Classified)
	 */
	public int getWebClassifiedCount(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject(
				"classified.getWebClassifiedCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IClassifiedDao#getWebClassifiedList(com.rbt.model.Classified)
	 */
	public List getWebClassifiedList(Map map) {
		return this.getSqlMapClientTemplate().queryForList(
				"classified.getWebClassifiedList", map);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IClassifiedDao#getCatClassifiedList(com.rbt.model.Classified)
	 */
	public List getCatClassifiedList(Map map) {
		return this.getSqlMapClientTemplate().queryForList(
				"classified.getclassifiedList", map);
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.rbt.dao.IClassifiedDao#updateclicknum(java.lang.String)
	 */
	public void updateclicknum(String info_id) {
		this.getSqlMapClientTemplate().update("classified.updateclicknum", info_id);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IClassifiedDao#getSearchclassList(com.rbt.model.Classified)
	 */
	public List getSearchclassList(Map map) {
		return this.getSqlMapClientTemplate().queryForList(
				"classified.getsearchclassList", map);
	}
	/*
	 * 重载InsertGetPk方法（重载：方法名相同，参数不同）
	 * @see com.rbt.dao.impl.GenericDao#insertGetPk(java.lang.Object)
	 */
	public String insertGetPk(Classified t,List objList) {
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
	public void update(Classified t,List objList,String infoid){
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
	/*
	@Override
	public void delete(String id) {
		Supply supply = super.get(id);
		String infoattr_id = supply.getInfoattr_id();
		this.infoattrDao.delete(infoattr_id);
		super.delete(id);
	}*/
	/*
	 * 重写delete方法（重写：子类继承父类中）
	 * @see com.rbt.dao.impl.GenericDao#delete(java.lang.Object)
	 */
	@Override
	public void delete(String id) {
		super.delete(id);
		String[] ids=id.split(",");
		for(int i=0;i<ids.length;i++){
			Classified classified = super.get(ids[i].trim());
			if(classified!=null&&classified.getInfoattr_id()!=null&&!classified.getInfoattr_id().equals("")){
				String infoattr_id = classified.getInfoattr_id();
				this.infoattrDao.delete(infoattr_id);
			}			
		}		
	}
	
	
	
}
