/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: BuyDao.java 
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
import com.rbt.dao.IBuyDao;
import com.rbt.dao.IInfoattrDao;
import com.rbt.model.Brand;
import com.rbt.model.Buy;
import com.rbt.model.Infoattr;
import com.rbt.model.Supply;

/**
 * @function 功能 求购表dao层业务接口实现类
 * @author 创建人 林俊钦
 * @date 创建日期 Fri Jul 29 14:52:50 CST 2011
 */
@Repository
public class BuyDao extends GenericDao<Buy,String> implements IBuyDao {
	@Autowired
	private IInfoattrDao infoattrDao;
	
	 public BuyDao() {
		 super(Buy.class);
	 }
	 
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IBuyDao#getWebBuyCount(java.util.Map)
	 */
	public int getWebBuyCount(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject(
				"buy.getWebBuyCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IBuyDao#getWebBuyList(java.util.Map)
	 */
	public List getWebBuyList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("buy.getWebBuyList", map);
	}

	public void updateClickNum(String pk) {
		this.getSqlMapClientTemplate().update("buy.updateclicknum", pk);
	}
	
	/* (non-Javadoc)
	 * @see com.rbt.dao.ISupplyDao#getCatBuyList(java.util.Map)
	 */
	public List getCatBuyList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("buy.getCatBuyList",map);		
	}
	/**
	 * 方法描述：批量修改推荐
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateBuyState(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("buy.updateisrecom", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}

	public String insertGetPk(Buy t, List objList) {
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
	public void update(Buy t,List objList,String infoid){
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
			Buy buy = super.get(ids[i].trim());
			if(buy!=null&&buy.getInfoattr_id()!=null&&!buy.getInfoattr_id().equals("")){
				String infoattr_id = buy.getInfoattr_id();
				this.infoattrDao.delete(infoattr_id);
			}			
		}		
	}
	
}
