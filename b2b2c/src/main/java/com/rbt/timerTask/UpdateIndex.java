package com.rbt.timerTask;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.rbt.function.CreateSpringContext;
import com.rbt.function.SysconfigFuc;
import com.rbt.index.CreateIndex;

public class UpdateIndex  extends CreateSpringContext implements Job {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			String is_index = "";// 获取系统配置是否启用是否启用更新索引
			is_index = SysconfigFuc.getSysValue("cfg_luindex");
			// 根据系统配置表取值，如果cfg_qtzUpdateChannel的值为0：表示启用定时更新栏目；1：不启用定时更新
			if (is_index.equals("0")) {
				updateIndex();// 执行更新栏目的方法
			}
		} catch (Exception e) {
			System.err.println("定时更新索引出现异常情况");
			e.printStackTrace();
		}
	}

	/**
	 * @author : 林俊钦
	 * @date : Sep 10, 2012 1:45:46 PM
	 * @Method Description :更新索引方法
	 */
	
	private void updateIndex(){
		CreateIndex ci = new CreateIndex();
		//ci.main(args)
	}
	
}
