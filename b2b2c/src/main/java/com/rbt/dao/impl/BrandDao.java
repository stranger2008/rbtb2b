/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: BrandDao.java 
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
import com.rbt.dao.IBrandDao;
import com.rbt.dao.IInfoattrDao;
import com.rbt.model.Brand;
import com.rbt.model.Download;
import com.rbt.model.Infoattr;
import com.rbt.model.Supply;

/**
 * @function 功能 品牌信息dao层业务接口实现类
 * @author 创建人 邱景岩
 * @date 创建日期 Tue Nov 08 09:15:10 CST 2011
 */
@Repository
public class BrandDao extends GenericDao<Brand,String> implements IBrandDao {

	@Autowired
	private IInfoattrDao infoattrDao;
	
	public BrandDao() {
		super(Brand.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IBrandDao#updateBrandState(java.util.Map)
	 */
	public void updateBrandState(Map map) {
		this.getSqlMapClientTemplate().update("brand.updateBrandState", map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IBrandDao#updateIsrecom(java.util.List)
	 */
	public void updateIsrecom(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if (list != null && list.size() > 0) {
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap) iter.next();
						executor.update("brand.updateisrecom", temp);
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
	 * @see com.rbt.dao.IBrandDao#getWebBrandCount(java.util.Map)
	 */
	public int getWebBrandCount(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject(
				"brand.getWebBrandCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IBrandDao#getWebBrandList(java.util.Map)
	 */
	public List getWebBrandList(Map map) {
		return this.getSqlMapClientTemplate().queryForList(
				"brand.getWebBrandList", map);
	}
	
	/*
	 * 重载InsertGetPk方法（重载：方法名相同，参数不同）
	 * @see com.rbt.dao.impl.GenericDao#insertGetPk(java.lang.Object)
	 */
	public String insertGetPk(Brand t,List objList) {
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
	public void update(Brand t,List objList,String infoid){
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
			Brand brand = super.get(ids[i].trim());
			if(brand!=null&&brand.getInfoattr_id()!=null&&!brand.getInfoattr_id().equals("")){
				String infoattr_id = brand.getInfoattr_id();
				this.infoattrDao.delete(infoattr_id);
			}			
		}		
	}
	
	

}
