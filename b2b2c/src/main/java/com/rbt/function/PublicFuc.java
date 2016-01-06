package com.rbt.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.service.impl.BackupService;

public class PublicFuc extends CreateSpringContext{
	
	public static String updateGetClickNum(Map map){
		BackupService backupService = (BackupService)getContext().getBean("backupService");
		List list =  backupService.updateGetClickNum(map);
		String clickNum = "0";
		if(list!=null && list.size()>0){
			HashMap bmap = (HashMap)list.get(0);
			if(bmap.get("clicknum")!=null){
				clickNum = bmap.get("clicknum").toString();
			}
		}
		return clickNum;
	}
	
}
