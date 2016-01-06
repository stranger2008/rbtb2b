/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: SysuserDao.java 
 */
package com.rbt.dao.impl;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.rbt.dao.IAreaDao;
import com.rbt.model.Area;

/**
 * @function 功能 地区管理dao层接口实现
 * @author 创建人 胡惜坤
 * @date 创建日期 Jun 28, 2011
 */

@Repository
public class AreaDao  extends GenericDao<Area,String> implements IAreaDao {
	public AreaDao() {
		super(Area.class);
	}
	
	/* (non-Javadoc)
	 * @see com.rbt.dao.IAreaDao#getIndexList(java.util.Map)
	 */
	public List getAreaIndexList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("area.getAreaIndexList", map);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IAreaDao#updateAreaSortNo(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public void updateAreaSortNo(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if (list != null && list.size() > 0) {
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap) iter.next();
						executor.update("area.updateSortNo", temp);
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
	 * @see com.rbt.dao.IAreaDao#updateOneAreaSortNo(java.util.Map)
	 */
	public void updateOneAreaSortNo(Map map) {
		this.getSqlMapClientTemplate().update("area.updateSortNo", map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.dao.IAreaDao#getWebAreaList(java.util.Map)
	 */
	public List getWebAreaList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("area.getWebAreaList", map);
	}

	public List getAll() {
		return this.getSqlMapClientTemplate().queryForList("area.getAll");
	}

    public List getWebAreaIndexList(Map map) {
    	return this.getSqlMapClientTemplate().queryForList("area.getWebAreaIndexList", map);
	}
    
    public List getAreaCityList(Map map) {
    	return this.getSqlMapClientTemplate().queryForList("area.getAreaCityList", map);
	}
    
    public List getCharacterList(Map map) {
    	return this.getSqlMapClientTemplate().queryForList("area.getCharacterList", map);
	}
    
    public List getCountryList(Map map) {
    	return this.getSqlMapClientTemplate().queryForList("area.getCountryList", map);
	}
}
