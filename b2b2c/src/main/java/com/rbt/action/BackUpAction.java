/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: BackUpAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.service.IBackupService;
import com.rbt.common.util.BackMysql;
import com.rbt.common.util.DbUtil;
import com.rbt.common.util.FileUpDownFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 数据库备份/还原action类
 * @author 创建人 林俊钦
 * @date 创建日期 Jul 7, 2011 1:43:46 PM
 */
@Controller
public class BackUpAction extends BaseAction {
	private static final long serialVersionUID = -5707993749015761816L;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IBackupService backupService;
	/*
	 * 数据库表信息的集合
	 */
	public List tableList;
	/*
	 * 表的结构信息
	 */
	public List structureList;
	/*
	 * 数据库名
	 */
	public String databaseName;
	/*
	 * 表选择框checkbox
	 */
	public String cbtable;

	// 还原表结构传入的sql文件名
	public String sqlFileName;

	

	/**
	 * @MethodDescribe 方法描述 调用mysql 命令备份数据库
	 * @author 创建人 林俊钦
	 * @param mysql
	 *            执行的mysql cmd 的字符串
	 * @throws Exception
	 * @date 创建日期 Jul 7, 2011 2:04:17 PM
	 */
	public String backup() throws Exception {
		BackMysql bm = new BackMysql();
		String dbSqlName = bm.backupDb();
		this.addActionMessage("数据库备份成功,点击《还原数据库》可选择文件" + dbSqlName + "还原数据库");
		return list();
	}

	/**
	 * @MethodDescribe 方法描述 还原指定的表
	 * @author 创建人 林俊钦
	 * @date 创建日期 Jul 12, 2011 1:02:06 PM
	 */
	public String loadSql() throws Exception {
		BackMysql bm = new BackMysql();
		bm.load(sqlFileName);
		this.addActionMessage(sqlFileName + "还原成功");
		return list();
	}

	/**
	 * @Method Description :删除指定的文件名称
	 * @author : 林俊钦
	 * @date : Nov 9, 2011 2:39:58 PM
	 */
	public String deleteTab() throws Exception {
		BackMysql bm = new BackMysql();
		if (bm.deleteTable(sqlFileName)) {
			this.addActionMessage(sqlFileName + "删除成功");
		} else {
			this.addActionMessage(sqlFileName + "删除失败");
		}
		return list();
	}

	/**
	 * @MethodDescribe 方法描述 备份指定的表
	 * @author 创建人 林俊钦
	 * @date 创建日期 Jul 11, 2011 12:38:05 PM
	 */
	public String butable() throws Exception {
		BackMysql bm = new BackMysql();
		if (cbtable != null) {
			String dbSqlName = bm.backupDbTable(cbtable);
			this.addActionMessage("表备份成功,点击《还原表》可选择文件" + dbSqlName + "还原数据库表");
		} else {
			this.addActionMessage("表备份失败");
		}
		return list();
	}

	/**
	 * @Method Description : 下载表文件
	 * @author : 林俊钦
	 * @throws Exception
	 * @date : Nov 9, 2011 3:47:44 PM
	 */
	public void downloadTab() throws Exception {        
        FileUpDownFileUtil fudf=new FileUpDownFileUtil();
        String tabPath="/WEB-INF/dbBackup/";
        fudf.downloadFile(tabPath, sqlFileName);
	}	
	

	/**
	 * @MethodDescribe 方法描述 获取指定数据库中所有的表的名称
	 * @author 创建人 林俊钦
	 * @date 创建日期 Jul 7, 2011 2:07:33 PM
	 */
	public String list() throws Exception {	
		DbUtil dbUtil = new DbUtil();
		// 获取数据库名称
		String dbName = dbUtil.getDbName();
		Map pageMap = new HashMap();
		// 通过对数据库的名称查询数据库中的表数
		pageMap.put("dbName", dbName);
		tableList = this.backupService.getTableList(pageMap);
		// 重构原List数据
		List newTableList = new ArrayList();
		if (tableList != null && tableList.size() > 0) {
			Map tableMap = new HashMap();
			// 定义MAP对象
			Map countMap = new HashMap();
			for (int i = 0; i < tableList.size(); i++) {
				// 获取表中行的对象
				tableMap = (HashMap) tableList.get(i);
				if (tableMap.get("Tables_in_" + dbName) != null) {
					// 取出数据库中相应表的名称
					String tableName = tableMap.get("Tables_in_" + dbName).toString();
					countMap.put("tabName", tableName);
					// 通过表名查找该表中行的记录数
					int Count = backupService.getTableCount(countMap);
					// 通过表名获取该表的表结构
					structureList = backupService.getTablestructure(countMap);
					if (structureList != null && structureList.size() > 0) {
						Map stlMap = (HashMap) structureList.get(0);
						if (stlMap.get("Create Table") != null) {
							Map newTableMap = new HashMap();
							newTableMap.put("tableName", tableName);
							newTableMap.put("tableCount", Count);
							String createSql = stlMap.get("Create Table").toString();
							// 过滤掉非法的字符
							createSql = createSql.replace("COMMENT=", "").replace("COMMENT", "").replace("�", "").replace("''", "");
							// 为创建的新表添加表结构语句
							newTableMap.put("structure", createSql);
							// 为新List添加新表的MAP对象
							newTableList.add(newTableMap);
						}
					}
				}
			}
		}
		// 将表赋给表对象用于前台的显示
		tableList = newTableList;
		return goUrl(INDEXLIST);
	}

	public List getTableList() {
		return tableList;
	}

	public void setTableList(List tableList) {
		this.tableList = tableList;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	/**
	 * @return the cbtable
	 */
	public String getCbtable() {
		return cbtable;
	}

	/**
	 * @param cbtable
	 *            the cbtable to set
	 */
	public void setCbtable(String cbtable) {
		this.cbtable = cbtable;
	}

	/**
	 * @return the structureList
	 */
	public List getStructureList() {
		return structureList;
	}

	/**
	 * @param structureList
	 *            the structureList to set
	 */
	public void setStructureList(List structureList) {
		this.structureList = structureList;
	}
	public String getSqlFileName() {
		return sqlFileName;
	}

	public void setSqlFileName(String sqlFileName) {
		this.sqlFileName = sqlFileName;
	}

}
