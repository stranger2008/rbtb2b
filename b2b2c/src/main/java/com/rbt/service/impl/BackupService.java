/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.service.impl
 * FileName: BackupService.java 
 */

package com.rbt.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.model.Backup;
import com.rbt.service.IBackupService;
import com.rbt.dao.IAboutusDao;
import com.rbt.dao.IBackupDao;

/**
 * @function 功能 数据库备份/还原业务层
 * @author 创建人 林俊钦
 * @date 创建日期 Jul 11, 2011 9:36:38 AM
 */
@Service
public class BackupService extends GenericService<Backup,String> implements IBackupService {

	/*
	 * 数据库备份/还原实现层接口
	 */
	IBackupDao backupDao;

	@Autowired
	public BackupService(IBackupDao backupDao) {
		super(backupDao);
		this.backupDao = backupDao;
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IBackupService#getTableCount(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public int getTableCount(Map map) {
		return backupDao.getTableCount(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IBackupService#getTableList(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public List getTableList(Map map) {
		return backupDao.getTableList(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IBackupService#getTablestructure(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public List getTablestructure(Map map) {
		return backupDao.getTablestructure(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IBackupService#getDatabaseversion()
	 */
	public String getDatabaseversion() {
		return this.backupDao.getDatabaseversion();
	}

	public List updateGetClickNum(Map map) {
		return this.backupDao.updateGetClickNum(map);
	}

}
