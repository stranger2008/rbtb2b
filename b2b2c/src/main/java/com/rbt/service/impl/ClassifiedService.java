/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: ClassifiedService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;
import com.rbt.dao.IClassifiedDao;
import com.rbt.model.Classified;
import com.rbt.service.IClassifiedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @function 功能 记录动态分类信息Service层业务接口实现
 * @author 创建人 蔡毅存
 * @date 创建日期 Fri Oct 14 08:59:55 CST 2011
 */
@Service
public class ClassifiedService extends GenericService<Classified,String> implements IClassifiedService {

	IClassifiedDao classifiedDao;

	@Autowired
	public ClassifiedService(IClassifiedDao classifiedDao) {
		super(classifiedDao);
		this.classifiedDao = classifiedDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IClassifiedService#getCatClassifiedList(java.util.Map)
	 */
	public List getCatClassifiedList(Map map) {
		return this.classifiedDao.getCatClassifiedList(map);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IGalleryService#updateauditClassified(com.rbt.model.classified)
	 */
	public void updateauditClassified(Classified classified) {
		this.classifiedDao.updateauditClassified(classified);
	}

	/**
	 * 批量更新Nav是否显示
	 */
	public void updateisrecom(List list) {
		this.classifiedDao.updateClassifiedState(list);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IClassifiedService#getWebClassifiedCount(java.util.Map)
	 */
	public int getWebClassifiedCount(Map map) {
		return this.classifiedDao.getWebClassifiedCount(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IClassifiedService#getWebClassifiedList(java.util.Map)
	 */
	public List getWebClassifiedList(Map map) {
		return this.classifiedDao.getWebClassifiedList(map);
	}	
	
	public void updateclicknum(String info_id) {
		this.classifiedDao.updateclicknum(info_id);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IClassifiedService#getWebClassifiedList(java.util.Map)
	 */
	public List getSearchclassList(Map map) {
		return this.classifiedDao.getSearchclassList(map);
	}
	
	public String insertGetPk(Classified t,List objList){
		return this.classifiedDao.insertGetPk(t, objList);
	}
	
	public void update(Classified t,List objList,String id){
		this.classifiedDao.update(t,objList,id);
	}

}
