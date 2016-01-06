/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: SupplyDao.java 
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
import com.rbt.dao.ISupplyDao;
import com.rbt.model.Infoattr;
import com.rbt.model.Supply;

/**
 * @function 功能  供应表dao层业务接口实现类
 * @author 创建人 林俊钦
 * @date 创建日期 Thu Jul 21 17:15:43 CST 2011
 */
@Repository
public class SupplyDao extends GenericDao<Supply,String> implements ISupplyDao {

	@Autowired
	private IInfoattrDao infoattrDao;

	public SupplyDao() {
		super(Supply.class);
	}

	/* (non-Javadoc)
	 * @see com.rbt.dao.ISupplyDao#getSupplyIndexList(java.util.Map)
	 */
	public List getSupplyIndexList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("supply.getSupplyIndexList",map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.dao.ISupplyDao#getWebSupplyList(java.util.Map)
	 */
	public List getWebSupplyList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("supply.getWebSupplyList",map);		
	}

	/* (non-Javadoc)
	 * @see com.rbt.dao.ISupplyDao#getWebSupplyCount(java.util.Map)
	 */
	public int getWebSupplyCount(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject(
				"supply.getWebSupplyCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}
	public void updateClickNum(String pk)
    {
      this.getSqlMapClientTemplate().update("supply.updateclicknum", pk);
    }
	
	/* (non-Javadoc)
	 * @see com.rbt.dao.ISupplyDao#getCatSupplyList(java.util.Map)
	 */
	public List getCatSupplyList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("supply.getCatSupplyList",map);		
	}

	/**
	 * 方法描述：批量修改推荐
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateSupplyState(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("supply.updateisrecom", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}

	public List getWebSupplyAdsList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("supply.getWebSupplyAdsList",map);		
	}
	/*
	 * 重载InsertGetPk方法（重载：方法名相同，参数不同）
	 * @see com.rbt.dao.impl.GenericDao#insertGetPk(java.lang.Object)
	 */
	public String insertGetPk(Supply t,List objList) {
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
	public void update(Supply t,List objList,String infoid){
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

	/*
	 * 重写delete方法（重写：子类继承父类中）
	 * @see com.rbt.dao.impl.GenericDao#delete(java.lang.Object)
	 */
	@Override
	public void delete(String id) {
		super.delete(id);
		String[] ids=id.split(",");
		for(int i=0;i<ids.length;i++){
			Supply supply = super.get(ids[i].trim());
			if(supply!=null&&supply.getInfoattr_id()!=null&&!supply.getInfoattr_id().equals("")){
				String infoattr_id = supply.getInfoattr_id();
				this.infoattrDao.delete(infoattr_id);
			}			
		}		
	}
	
}

