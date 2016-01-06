/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.service.impl
 * FileName: AreaService.java 
 */
package com.rbt.service.impl;

import com.rbt.service.IAreaService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.IAreaDao;
import com.rbt.model.Area;

/**
 * @function 功能 地区管理业务层接口实现
 * @author  创建人 胡惜坤
 * @date  创建日期  Jun 28, 2011
 */
@Service
public class AreaService extends GenericService<Area,String> implements IAreaService {
	
	private IAreaDao areaDao;

	@Autowired
	public AreaService(IAreaDao areaDao) {
		super(areaDao);
		this.areaDao = areaDao;
	}

	public void updateAreaSortNo(List list) {
		this.areaDao.updateAreaSortNo(list);
	}
	public void updateOneAreaSortNo(Map map) {
		this.areaDao.updateOneAreaSortNo(map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.IAreaService#getAreaIndexList(java.util.Map)
	 */
	public List getAreaIndexList(Map map) {
		return areaDao.getAreaIndexList(map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.IAreaService#getWebAreaList(java.util.Map)
	 */
	public List getWebAreaList(Map map) {
		return areaDao.getWebAreaList(map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.IAreaService#getAll()
	 */
	public List getAll() {
		return areaDao.getAll();
	}

	public List getWebAreaIndexList(Map map) {
		return areaDao.getWebAreaIndexList(map);
	}
	
	public List getAreaCityList(Map map) {
		return areaDao.getAreaCityList(map);
	}
	
	public List getCharacterList(Map map) {
		return areaDao.getCharacterList(map);
	}
	
	public List getCountryList(Map map) {
		return areaDao.getCountryList(map);
	}
}
