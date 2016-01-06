package com.rbt.timerTask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.rbt.function.CreateSpringContext;
import com.rbt.function.SysconfigFuc;
import com.rbt.service.ICertificationService;

public class MemberVipShip extends CreateSpringContext implements Job {
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			String ifflageString = "";// 获取系统配置是否启用更新的值
			ifflageString = SysconfigFuc.getSysValue("cfg_qtzUpdateChannel");
			// 根据系统配置表取值，如果cfg_qtzUpdateChannel的值为0：表示启用定时更新栏目；1：不启用定时更新
			if (ifflageString.equals("0")) {
				yearVipcredit();
			}
		} catch (Exception e) {
			System.err.println("定时修改订单出现异常情况");
		}
	}
	
	public static void main(String[] args) {
		MemberVipShip vip=new MemberVipShip();
		vip.yearVipcredit();
	}

	/**
	 * @Method Description : 一年的定时任务为年限VIP加分
	 * @author : 林俊钦
	 * @date : Dec 5, 2011 2:38:07 PM
	 */
	@SuppressWarnings("unchecked")
	private void yearVipcredit(){
		ICertificationService certificationService = (ICertificationService) getContext().getBean("certificationService");
		//获取VIP年限所要得的年限积分
		Map map=new HashMap();
		@SuppressWarnings("unused")
		List list=certificationService.getList(map);
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Map listMap=new HashMap();
				listMap=(HashMap)list.get(i);
				String cust_id="",state_in="";
				if(listMap.get("cust_id")!=null){
					cust_id=listMap.get("cust_id").toString();
				}
				if(listMap.get("state_in")!=null){
					state_in=listMap.get("state_in").toString();
				}
				if(state_in.equals("1")){
					certificationService.creditChangeNum(cust_id, 1, "cfg_vip","d","审核成功","");
				}
			}
		}
	}	
}
