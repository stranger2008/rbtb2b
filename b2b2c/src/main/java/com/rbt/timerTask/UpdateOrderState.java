/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.timerTask
 * FileName: SendSubscribeJobAction.java 
 */
package com.rbt.timerTask;

import java.util.HashMap;
import java.util.List;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.rbt.function.CreateSpringContext;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Orderinfo;
import com.rbt.service.IOrderinfoService;

/**
 * @function 功能  执行商机订阅自动发送
 * @author  创建人  胡惜坤
 * @date  创建日期  2011-09-24
 */
public class UpdateOrderState extends CreateSpringContext implements Job {

	
	//订单列表
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			String ifflageString = "";// 获取系统配置是否启用更新的值
			ifflageString = SysconfigFuc.getSysValue("cfg_qtzUpdateChannel");
			// 根据系统配置表取值，如果cfg_qtzUpdateChannel的值为0：表示启用定时更新栏目；1：不启用定时更新
			if (ifflageString.equals("0")) {
				updateorder();// 执行更新栏目的方法
			}
		} catch (Exception e) {
			System.err.println("定时修改订单出现异常情况");
		}
	}
	
	
	/**
	 * 方法描述：AJAX更新栏目信息的方法
	 * 
	 * @throws Exception
	 */
	//批量修改过期订单状态
	public void updateorder() throws Exception {
		IOrderinfoService orderinfoService = (IOrderinfoService) getContext().getBean("orderinfoService");
		HashMap map=new HashMap();
		//定义多长时间订单过期
		String endtime="15";
		//获取过期订单列表
		List orderinfoList=orderinfoService.getEndOrderList(endtime);
		StringBuilder orderidBuilder=new StringBuilder();
		if(orderinfoList!=null&&orderinfoList.size()>0){
			//获取过期订单ID号串
			for(int i=0;i<orderinfoList.size();i++){
				HashMap maporder=new HashMap();
				maporder=(HashMap)orderinfoList.get(i);
				orderidBuilder.append(maporder.get("order_id").toString()+",");
			}
			if(orderidBuilder.length()>0){
			String idstring=orderidBuilder.substring(0, orderidBuilder.length()-1);
			//批量修改订单状态
			HashMap mapvalue=new HashMap();
			mapvalue.put("order_state", "4");
			mapvalue.put("order_id", idstring);
			orderinfoService.UpdateOrderState(mapvalue);
			}	
		}
		
	}
	
	
}
