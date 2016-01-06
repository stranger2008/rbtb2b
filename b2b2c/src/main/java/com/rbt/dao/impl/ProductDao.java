/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: ProductDao.java 
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
import com.rbt.dao.IProductDao;
import com.rbt.model.Infoattr;
import com.rbt.model.News;
import com.rbt.model.Product;
import com.rbt.model.Supply;

/**
 * @function 功能  产品表dao层业务接口实现类
 * @author 创建人 胡惜坤
 * @date 创建日期 Mon Jul 25 17:02:42 CST 2011
 */
@Repository
public class ProductDao extends GenericDao<Product,String> implements IProductDao {

	@Autowired
	private IInfoattrDao infoattrDao;
	
	public ProductDao() {
		super(Product.class);
	}
	
	 /**
	 * 
	 * @param map
	 */
	public void updateState(Map map) {
		this.getSqlMapClientTemplate().update("product.updatestate", map);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IProductDao#getProductList(java.util.Map)
	 */
	public List getMemberCatList(String custid) {
		return this.getSqlMapClientTemplate().queryForList("product.membercatlist",
				custid);
	}
	public void updateClickNum(String pk)
    {
      this.getSqlMapClientTemplate().update("product.updateclicknum", pk);
    }

	/* (non-Javadoc)
	 * @see com.rbt.dao.IProductDao#getWebProductCount(java.util.Map)
	 */
	public int getWebProductCount(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject(
				"product.getWebProductCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}

	/* (non-Javadoc)
	 * @see com.rbt.dao.IProductDao#getWebProductList(java.util.Map)
	 */
	public List getWebProductList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("product.getWebProductList",
				map);
	}
	
	/* (non-Javadoc)
	 * @see com.rbt.dao.ISupplyDao#getCatProductList(java.util.Map)
	 */
	public List getCatProductList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("product.getCatProductList",map);		
	}
    /*
     * (non-Javadoc)
     * @see com.rbt.dao.IProductDao#updateRecommendState(java.util.List)
     */
	public void updateRecommendState(final List list) {
			this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
					Map<String, Object> temp = new HashMap<String, Object>();
					executor.startBatch();
					if(list!=null && list.size()>0){
						for (Iterator iter = list.iterator(); iter.hasNext();) {
							temp = (HashMap)iter.next();
							executor.update("product.updaterecommend", temp);
						}
					}
					executor.executeBatch();
					return null;
				}
			});

		}
	/*
	 * 重载InsertGetPk方法（重载：方法名相同，参数不同）
	 * @see com.rbt.dao.impl.GenericDao#insertGetPk(java.lang.Object)
	 */
	public String insertGetPk(Product t,List objList) {
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
	public void update(Product t,List objList,String infoid){
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
			Product product = super.get(ids[i].trim());
			if(product!=null&&product.getInfoattr_id()!=null&&!product.getInfoattr_id().equals("")){
				String infoattr_id = product.getInfoattr_id();
				this.infoattrDao.delete(infoattr_id);
			}			
		}		
	}

}

