/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.function
 * FileName: AuditModelFuc.java 
 */
package com.rbt.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.createHtml.InfoVo;
import com.rbt.model.Advpos;
import com.rbt.service.IAdvinfoService;
import com.rbt.service.IAdvposService;
import com.rbt.service.IAudithistoryService;
import com.rbt.service.IAuditmodelService;

/**
 * @function 功能  后台审核模型
 * @author  创建人 胡惜坤
 * @date  创建日期  2012-08-09
 */
public class AuditModelFuc extends CreateSpringContext{
	
	//根据模块类型获取模块所对于的审核人信息
	public static List getAuditmodel(String model){
		
		IAuditmodelService auditmodelService = (IAuditmodelService)getContext().getBean("auditmodelService");
		Map modelMap=new HashMap();
		modelMap.put("model_type", model);
		List modeList=new ArrayList();
		modeList=auditmodelService.getList(modelMap);
		return modeList;
	}
	//根据模块类型获取模块所对于的审核人信息
	public static List getAuditmodelhistory(String model,String info_id){
		
		IAudithistoryService audithistoryService = (IAudithistoryService)getContext().getBean("audithistoryService");
		Map modelMap=new HashMap();
		modelMap.put("module_type", model);
		modelMap.put("info_id", info_id);
		List historyList=new ArrayList();
		historyList=audithistoryService.getAuditList(modelMap);
		if(historyList!=null&&historyList.size()>0){
			for (int i = 0; i < historyList.size(); i++) {
				
				Map hMap=new HashMap();
				hMap=(HashMap)historyList.get(i);
				if(hMap!=null&&hMap.get("in_date")!=null){
					String out_date=hMap.get("in_date").toString();
					if(out_date!=null){
						out_date=out_date.substring(0,out_date.lastIndexOf(".0"));
					}
					hMap.put("out_date", out_date);
				}
				
			}
		}
		return historyList;
	}
	//根据审核人信息获取审核历史信息
	public static List getAuditmemberhistory(String model,String info_id,String user_id){
		
		IAudithistoryService audithistoryService = (IAudithistoryService)getContext().getBean("audithistoryService");
		Map modelMap=new HashMap();
		modelMap.put("module_type", model);
		modelMap.put("info_id", info_id);
		modelMap.put("user_id", user_id);
		List historyList=new ArrayList();
		historyList=audithistoryService.getAuditList(modelMap);
		return historyList;
	}
	//根据模块类型获取模块获取当前用户是不是在审核的最后一个；ture表示是最后一个，false表示不是最后一个
	public static boolean boolauditIndex(String model,String curuserid,String info_state){
		
		IAuditmodelService auditmodelService = (IAuditmodelService)getContext().getBean("auditmodelService");
		boolean flage=false;
		Map modelMap=new HashMap();
		modelMap.put("model_type", model);
		List modeList=new ArrayList();
		modeList=auditmodelService.getList(modelMap);
		if(modeList!=null&&modeList.size()>0){
			Map aMap=new HashMap();
			aMap=(HashMap)modeList.get(modeList.size()-1);
			if(aMap!=null&&aMap.get("userid")!=null){
				if(curuserid.equals(aMap.get("userid").toString())){
					if(info_state!=null&&info_state.equals("1")){
						flage=true;
					}
				}
			}
		}
		else {
			flage=true;
		}
		
		return flage;
	}
public static List getAuditRemind(String user_id){
		
		IAuditmodelService auditmodelService = (IAuditmodelService)getContext().getBean("auditmodelService");
		IAudithistoryService audithistoryService = (IAudithistoryService)getContext().getBean("audithistoryService");
		InfoVo infoVo=new InfoVo();
		//存放最终返回的List
		List remindList=new ArrayList();
		//获取用户需要审核的模块信息
		List modeList=new ArrayList();
		Map modeMap=new HashMap ();
		modeMap.put("user_id", user_id);
		modeMap.put("para_code", "audit_model_commpara");
		modeList=auditmodelService.getAuditList(modeMap);
		if(modeList!=null&&modeList.size()>0){
			//一层循环获取用户拥有审核的模块信息
			for(int i=0;i<modeList.size();i++){
				
				//每次循环获取一个MAP的值
				Map finalMap=new HashMap ();
				Integer count=0;//获取每一个模块需提醒的信息条数
				
				Map gmodeMap=new HashMap ();
				gmodeMap=(HashMap)modeList.get(i);
				String model_name="",model_type="";
				if(gmodeMap!=null&&gmodeMap.get("model_type")!=null){
					model_name=gmodeMap.get("model_type").toString();
				}
				if(gmodeMap!=null&&gmodeMap.get("para_key")!=null){
					model_type=gmodeMap.get("para_key").toString();
					
				}
			    //存放获取需要审核信息
				List infoList=new ArrayList();
				Map infoMap=new HashMap();
				//infoMap.put("","")
				infoMap=getinfo_id_name(model_name,infoMap);
				String sqlmap="",table_id="",table_name="";
				//获取表主键字段名称
				if(infoMap.get("info_id")!=null){
					table_id=infoMap.get("info_id").toString();
				}
				//获取表名称
				if(infoMap.get("table_name")!=null){
					table_name=infoMap.get("table_name").toString();
				}
				//获取模块表信息的SQL  如：SELECT brand_id  FROM brand WHERE info_state='0';
				sqlmap="SELECT "+table_id+" AS info_id, " 
					+"(SELECT info_state FROM audithistory a  WHERE a.info_id=s."+table_id+" "
					+"AND module_type='"+table_name+"' AND user_id='"+user_id+"' ORDER BY info_id,in_date DESC LIMIT 1) as info_state  "
					+"FROM "+table_name+" s WHERE info_state='0'";
				infoList=infoVo.getInfoList(sqlmap, null);
				if(infoList!=null&&infoList.size()>0){
					//二层循环获取模块对于审核历史信息
					for(int j=0;j<infoList.size();j++){
						
						Map getinfoMap=new HashMap();
						getinfoMap=(HashMap)infoList.get(j);
						String info_state="";
						//在审核历史表中有操作信息，就要进行处理,如果审核成功，就不要要提醒，否需要提醒审核操作
						if(getinfoMap!=null&&getinfoMap.get("info_state")!=null&&!getinfoMap.get("info_state").equals("")){
							
							info_state=getinfoMap.get("info_state").toString();
							//如果审核状态是显示：未通过的话，还需要继续提醒操作
							if(!info_state.equals("")&&info_state.equals("2")){
							    count=count+1;
							}
						}
						else {
							//在审核历史表中没有审核操作历史，说明还有开始审核操作该条信息，需要要提醒审核信息
							count=count+1;
						}
					}
				}
				//加入需要提醒审核信息条数
				finalMap.put("count", count);
				//加入模块类型
				table_name=table_name.replaceFirst(table_name.substring(0, 1),table_name.substring(0, 1).toUpperCase()) ;
				finalMap.put("model_type", table_name);
				//加入模块名称
				finalMap.put("model_name", model_type);
				remindList.add(finalMap);
				
				
			}
		}
		return remindList;
		
	}
	//根据模块名称获取模块的信息主键ID名称
	public static Map getinfo_id_name(String mode_name,Map keyMap)
	{
		
		if(mode_name.equals("supply")){
			keyMap.put("info_id", "supply_id");
			keyMap.put("table_name", "supply");
		}
        if(mode_name.equals("buy")){
        	keyMap.put("info_id", "buy_id");
        	keyMap.put("table_name", "buy");
		}
        if(mode_name.equals("news")){
        	keyMap.put("info_id", "news_id");
        	keyMap.put("table_name", "news");
		}
        if(mode_name.equals("product")){
        	keyMap.put("info_id", "product_id");
        	keyMap.put("table_name", "product");
		}
        if(mode_name.equals("job")){
        	keyMap.put("info_id", "job_id");
        	keyMap.put("table_name", "job");
		}
        if(mode_name.equals("resume")){
        	keyMap.put("info_id", "resume_id");
        	keyMap.put("table_name", "resume");
		}
        if(mode_name.equals("showinfo")){
        	keyMap.put("info_id", "exh_id");
        	keyMap.put("table_name", "showinfo");
		}
        if(mode_name.equals("know")){
        	keyMap.put("info_id", "ask_id");
        	keyMap.put("table_name", "ask");
		}
        if(mode_name.equals("gallery")){
        	keyMap.put("info_id", "gal_id");
        	keyMap.put("table_name", "gallery");
		}
        if(mode_name.equals("video")){
        	keyMap.put("info_id", "video_id");
        	keyMap.put("table_name", "video");
		}
        if(mode_name.equals("download")){
        	keyMap.put("info_id", "down_id");
        	keyMap.put("table_name", "download");
		}
        if(mode_name.equals("brand")){
        	keyMap.put("info_id", "brand_id");
        	keyMap.put("table_name", "brand");
		}
        if(mode_name.equals("company")){
        	keyMap.put("info_id", "cust_id");
        	keyMap.put("table_name", "member");
		}
        if(mode_name.equals("classified")){
        	keyMap.put("info_id", "info_id");
        	keyMap.put("table_name", "classified");
		}
        if(mode_name.equals("goods")){
        	keyMap.put("goods", "goods_id");
        	keyMap.put("table_name", "goods");
		}
        if(mode_name.equals("orderinfo")){
        	keyMap.put("info_id", "order_id");
        	keyMap.put("table_name", "orderinfo");
		}        
    			
    	return keyMap;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}