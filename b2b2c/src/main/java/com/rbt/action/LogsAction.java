/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: LogsAction.java
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.util.BackMysql;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Logs;
import com.rbt.service.ILogsService;

/**
 * @function 功能 系统日志管理action类
 * @author 创建人 林俊钦
 * @date 创建日期 Jul 5, 2011 9:44:54 AM
 */
@Controller
public class LogsAction extends BaseAction  implements Preparable{
	private static final long serialVersionUID = -8779637169950952665L;
	/*
	 * 系统日志对象
	 */
	public Logs logs;
	/*
	 * 系统日志业务层接口
	 */
	@Autowired
	public ILogsService logsService;
	/*
	 * 系统日志信息集合
	 */
	public List logslist;
	/*
	 * 搜索字段
	 */
	public String user_name_s;
	public String starttime_s;
	public String endtime_s;	
	public String content_s;
	public String ip_s;
	//备份的表名
	private String rbtTable="logs";

	/**
	 * @MethodDescribe 方法描述 返回系统日志的列表
	 * @author 创建人 林俊钦
	 * @date 创建日期 Jul 5, 2011 9:57:28 AM
	 */
	public String list() throws Exception {
		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		if (user_name_s != null && !user_name_s.equals(""))
			pageMap.put("user_name", user_name_s);

		if (starttime_s != null && !starttime_s.equals(""))
			pageMap.put("starttime", starttime_s);
		
		if (endtime_s != null && !endtime_s.equals(""))
			pageMap.put("endtime", endtime_s);
		
		if (content_s != null && !content_s.equals(""))
			pageMap.put("content", content_s);
		
		if (ip_s != null && !ip_s.equals(""))
			pageMap.put("ip", ip_s);
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.logsService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		// 找出信息列表，放入list
		logslist = this.logsService.getList(pageMap);
		return goUrl(INDEXLIST);
	}

	/**
	 * @MethodDescribe 方法描述 根据ID删除系统日志记录
	 * @author 创建人 林俊钦
	 * @date 创建日期 Jul 5, 2011 10:35:56 AM
	 */
	public String delete() throws Exception {
		if(this.logs.getLog_id()!=null){
			String logsid = this.logs.getLog_id();
			logsid = logsid.replace(" ", "");
			this.logsService.delete(logsid);
			this.addActionMessage("删除系统日志信息成功");
		}
		return list();
	}

	/**
	 * @MethodDescribe 方法描述 重置数据库logs表
	 * @author 创建人 林俊钦
	 * @date 创建日期 Jul 5, 2011 10:35:56 AM
	 */
	public String reset() throws Exception {
		//清空操作日志数据操作
		this.logsService.deleteAlllogs();
		BackMysql bm = new BackMysql();
		String dbSqlName = bm.backupDbTable(rbtTable);
		this.addActionMessage("系统日志重置成功,被重置记录已保存"+dbSqlName+"文件中");
		return list();
	}
	/**
	 * @MethodDescribe 方法描述 通过ID返回系统日志的详细记录
	 * @author 创建人 林俊钦
	 * @date 创建日期 Jul 5, 2011 11:16:23 AM
	 */
	public String view() throws Exception {
		if(logs.getCust_id()!=null){
			if(accessControl(logs.getCust_id())){
				return list();
			}
		}
		return goUrl(VIEW);
	}

	public List getLogslist() {
		return logslist;
	}

	public void setLogslist(List logslist) {
		this.logslist = logslist;
	}

	public Logs getLogs() {
		return logs;
	}

	public void setLogs(Logs logs) {
		this.logs = logs;
	}

	
	/**
	 * @Method Description : 当进入方法后初始化对象
	 * @author : 林俊钦
	 * @date : Nov 8, 2011 2:36:50 PM
	 */
	public void prepare() throws Exception { super.saveRequestParameter();
		if(logs == null){
			logs = new Logs();
		}
		String id = logs.getLog_id();
		if(!ValidateUtil.isDigital(id)){
			logs = this.logsService.get(id);
		}
	}

	public String getUser_name_s() {
		return user_name_s;
	}

	public void setUser_name_s(String user_name_s) {
		this.user_name_s = user_name_s;
	}

	public String getStarttime_s() {
		return starttime_s;
	}

	public void setStarttime_s(String starttime_s) {
		this.starttime_s = starttime_s;
	}

	public String getEndtime_s() {
		return endtime_s;
	}

	public void setEndtime_s(String endtime_s) {
		this.endtime_s = endtime_s;
	}

	public String getContent_s() {
		return content_s;
	}

	public void setContent_s(String content_s) {
		this.content_s = content_s;
	}

	public String getIp_s() {
		return ip_s;
	}

	public void setIp_s(String ip_s) {
		this.ip_s = ip_s;
	}
}
