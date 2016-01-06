package com.rbt.function;

import java.util.HashMap;
import java.util.List;

import com.rbt.service.ISysconfigService;

/**
 * @function 功能  统一加载系统配置值进入内存
 * @author  创建人  李良林
 * @date  创建日期  2011-08-30
 */
public class SysconfigFuc extends CreateSpringContext{
	
	static HashMap<String,String> confMap;
	
	//根据变量名找出变量值
	public static String getSysValue(String var_name){
		
		if(confMap == null) confMap = new HashMap<String,String>();
		
		if(confMap.get(var_name) != null){
			return confMap.get(var_name).toString();
		}
		
		List list = getSysList();
		String _var_name="",var_value="";
        if(list!=null && list.size()>0){
        	HashMap aMap = new HashMap();
        	for(int i=0;i<list.size();i++){
        		 aMap=(HashMap)list.get(i);
                 if(aMap.get("var_name")!=null){
                	 _var_name=aMap.get("var_name").toString();
                 }
                 if(aMap.get("var_value")!=null){
                	 var_value=aMap.get("var_value").toString();
                 }
                 if(var_name.equals(_var_name)){
                	 break;
                 }
        	}
        }
        
        confMap.put(var_name, var_value);
        
		return var_value;
	}
	
	//通过变量名得到变量值
	public static List getSysList(){
		ISysconfigService sysconfigService = (ISysconfigService)getContext().getBean("sysconfigService");
		return sysconfigService.getAll();
	}
	
}