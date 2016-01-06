/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: GoodsDao.java 
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
import com.rbt.dao.IGoodsDao;
import com.rbt.dao.IInfoattrDao;
import com.rbt.model.Goods;
import com.rbt.model.Infoattr;
import com.rbt.model.Supply;

/**
 * @function 功能  商品表dao层业务接口实现类
 * @author 创建人 林俊钦
 * @date 创建日期 Mon Feb 27 11:28:48 CST 2012
 */
@Repository
public class GoodsDao extends GenericDao<Goods,String> implements IGoodsDao {
	
	@Autowired
	private IInfoattrDao infoattrDao;
	
	public GoodsDao() {
		super(Goods.class);
	}
	
	
	/* (non-Javadoc)
	 * @see com.rbt.dao.ISupplyDao#getWebSupplyList(java.util.Map)
	 */
	public List getRelateList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("goods.getrelateList",map);		
	}
	
	/**
	 * 方法描述：批量商品上架
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateShelves(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("goods.updateshelves", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Apr 5, 2012 4:23:10 PM
	 * @Method Description : 批量商品下架
	 */
	public void updatedownshelves(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("goods.updatedownshelves", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Sep 18, 2012 11:29:32 AM
	 * @Method Description : 插入表数据
	 */
	public String insertGetPk(Goods t,List objList) {
		if(objList != null && objList.size()>0){
			for(int i = 0;i<objList.size();i++){
				System.out.println((Infoattr) objList.get(i));
				this.infoattrDao.insert((Infoattr) objList.get(i));
			}
		}
		return super.insertGetPk(t);
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Sep 18, 2012 11:29:32 AM
	 * @Method Description : 更新表属性
	 */
	public void update(Goods t,List objList,String infoid){
		if(infoid !=null && !"".equals(infoid)){
			this.infoattrDao.delete(infoid);
		}
		if(objList != null && objList.size()>0){
			for(int i= 0;i<objList.size();i++){
				this.infoattrDao.insert((Infoattr) objList.get(i));
			}
		}
		super.update(t);
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Sep 18, 2012 11:30:52 AM
	 * @Method Description : 删除表属性
	 */
	@Override
	public void delete(String id) {
		super.delete(id);
		String[] ids=id.split(",");
		for(int i=0;i<ids.length;i++){
			Goods goods = super.get(ids[i].trim());
			if(goods!=null&&goods.getInfoattr_id()!=null&&!goods.getInfoattr_id().equals("")){
				String infoattr_id = goods.getInfoattr_id();
				this.infoattrDao.delete(infoattr_id);
			}			
		}		
	}

	
}

