package com.rbt.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.rbt.service.ICommparaService;

/**
 * @function 功能  用于加载系统基本参数
 * @author  创建人  林俊钦
 * @date  创建日期  Jul 29, 2011 1:10:56 PM
 */
public class CommparaFuc extends CreateSpringContext {
	//定义一个静态Map数据
	@SuppressWarnings("unchecked")
	public static Map commparaMap;

	@SuppressWarnings("unchecked")
	public static void initCommpara(String para_code) {
		commparaMap = new HashMap();
		try {			
			//定义一个list数组
			List commparaList = getCommparaList(para_code);
			if (commparaList != null && commparaList.size() > 0) {
				HashMap aMap = new HashMap();
				for (int i = 0; i < commparaList.size(); i++) {
					String para_value = "", para_key = "";
					aMap = (HashMap) commparaList.get(i);
					if (aMap.get("para_value") != null) {
						para_value = aMap.get("para_value").toString();
					}
					if (aMap.get("para_key") != null) {
						para_key = aMap.get("para_key").toString();
					}
					//将键值对存放在静态的MAP数据中
					commparaMap.put(para_value, para_key);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List getCommparaList(String para_code){
		//获取commparaService对象
		ICommparaService commparaService = (ICommparaService) getContext().getBean("commparaService");
		Map<String,String> pageMap = new HashMap<String,String>();
		if (para_code != null && !para_code.equals("")) {
			pageMap.put("para_code", para_code);
		}
		//找出相应的系统参数类型的列表
		return commparaService.getList(pageMap);
	}

	//在全局commparaMap中根据模块找出相应的键值对
	public static String get_commparakey_By_value(String value, String para_code) {
		String para_key = "";
		if (para_code != null) {
			//加载commparaMap对象
			initCommpara(para_code);
			if (commparaMap != null && commparaMap.get(value) != null) {
				para_key = commparaMap.get(value).toString();
			}
		}
		return para_key;
	}
}