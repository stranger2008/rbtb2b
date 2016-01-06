/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.function
 * FileName: ToolsFuc.java 
 */
package com.rbt.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.rbt.function.MemberFuc;

/**
 * @function 功能  前台freemarker工具类
 * @author  创建人 李良林
 * @date  创建日期  2011-09-30
 */

public class ToolsFuc {
	

	/**
	 * @Method Description :传入字符串，直接返回字符串中的中文汉字
	 * @author : 林俊钦
	 * @date : Dec 13, 2011 1:24:40 PM
	 */
	public static String getChinese(String str){
		
		  String htmlStr = str; //含html标签的字符串 
		  
	      Pattern p_script;
	      Matcher m_script;
	      Pattern p_style;
	      Matcher m_style;
	      Pattern p_html;
	      Matcher m_html;
	  
	      //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> } 
	      String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; 
	      //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> } 
	      String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; 
	      //定义HTML标签的正则表达式 
	      String regEx_html = "<[^>]+>"; 
	      
	      //过滤script标签 
          p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
          m_script = p_script.matcher(htmlStr); 
          htmlStr = m_script.replaceAll(""); 
          //过滤style标签 
          p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
          m_style = p_style.matcher(htmlStr); 
          htmlStr = m_style.replaceAll(""); 
          //过滤html标签 
          p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
          m_html = p_html.matcher(htmlStr); 
          htmlStr = m_html.replaceAll("");           
          //去除字符串中的空格、回车、换行符、制表符
          Pattern pat = Pattern.compile("\\s*|\t|\r|\n|");
  		  Matcher mat = pat.matcher(htmlStr);
  		  //htmlStr = mat.replaceAll(""); 
  		  
  		  return htmlStr;
	}
	
	/**
	 * @Method Description : 替换list表中字段的处理
	 * @author : 林俊钦
	 * @date : Oct 28, 2011 9:56:06 AM
	 */
	@SuppressWarnings( { "unchecked", "static-access" })
	public static List replaceList(List list) {
		if(list==null || list.size()<=0){
			return null;
		}
		Map map = new HashMap();
		String area_name = "", cat_name = "", img_path = "",
		content = "",state_in="",state_before="",state_after="";
		for (int i = 0; i < list.size(); i++) {
			map = (HashMap) list.get(i);
			if (map.get("area_attr") != null) {
				// 转换名称，取第二级的ID
				String area_attr_id = map.get("area_attr").toString();
				if(area_attr_id!=null&&area_attr_id.contains("/")){
					area_attr_id=area_attr_id.replace("/", ",");
				}
				if (area_attr_id.indexOf(",") > 0) {
					String[] attr_id = area_attr_id.split(",");
					if (attr_id.length > 2 && attr_id[1] != null) {
						map.put("attr_id", attr_id[1]);
					}
				}
				// 转换名称，取第一级的名称
				area_name = AreaFuc.getFiresAreaName(area_attr_id);
				map.put("area_name", area_name);
			}
			if (map.get("cat_attr") != null) {
				// 转换名称，取第一级的名称
				cat_name = CategoryFuc.getFiresCateName(map.get("cat_attr").toString());
				map.put("cat_name", cat_name);
			}
			if (map.get("img_path") != null) {
				img_path = map.get("img_path").toString();
				img_path = img_path.replace("090909090909","");
				if (img_path.indexOf(",") > 0) {
					String[] img_src = img_path.split(",");
					if (img_src[0] != null) {
						map.put("img_path", img_src[0].toString());
					}
				}
				// 若无图，则取系统默认无图图片
				if (img_path.equals("")) {
					img_path = SysconfigFuc.getSysValue("cfg_nopic");
					map.put("img_path", img_path);
				}
			}
			if (map.get("content") != null) {
				content = map.get("content").toString();
				// 把html代码去掉
				content = getChinese(content);
				map.put("content", content);
			}
			if(map.get("sub_desc")!=null){
				content = map.get("sub_desc").toString();
				// 把html代码去掉
				content = getChinese(content);
				map.put("sub_desc", content);
			}
			// is_progress:状态 0，未开始,1，进行中,2，已结束 展会状态的判断
			if (map.get("state_in") != null) {
				state_in = map.get("state_in").toString();
				if (state_in.equals("1")) {
					map.put("is_progress", "1");
				} else {
					if (map.get("state_before") != null) {
						state_before = map.get("state_before").toString();
						if (state_before.equals("1")) {
							map.put("is_progress", "0");
						} else {
							if (map.get("state_after") != null) {
								state_after = map.get("state_after").toString();
								if (state_after.equals("1")) {
									map.put("is_progress", "2");
								}
							}
						}
					}
				}
			}
			//经营模式转换成名称
			if (map.get("status_id") != null) {
				String status_id=map.get("status_id").toString();
				String status_name=MemberFuc.getStatusName(status_id);
				map.put("status_name", status_name);
			}
			list.set(i, map);
		}
		return list;
	}
	/**
	 * @Method Description : 替换特殊符号过滤
	 * @author : 胡惜坤
	 * @date : 2012-01-11
	 */
    public static String xssFilter(String inputContent)
    {
		if(inputContent!=null&&!"".equals(inputContent))
		{
			inputContent=inputContent.replace(">", "");
			inputContent=inputContent.replace(">", "");
			//可以扩充过滤符号;		
		}
		return inputContent;
    }
    /**
	 * @Method Description : 替换特殊符号过滤
	 * @author : 蔡毅存
	 * @date : 2012-01-13
	 */
    public static List getlastpic(List objectlist){
		if(objectlist!=null&&objectlist.size()>0){
			String img="";
		    for(int i=0;i<objectlist.size();i++){
		    	HashMap mapimg = (HashMap)objectlist.get(i);
				if(mapimg.get("img_path")!=null){
				    String imgpath=mapimg.get("img_path").toString();
				    if(imgpath.indexOf(",")!=-1){
				    	//获取字符串最后一张图片名称（最新上传图片）
				    	img=imgpath.substring(imgpath.lastIndexOf(",")+1,imgpath.length());
				    	mapimg.put("img_path", img);
				    }
				    
				}
				
		    }  
	    }
		return objectlist;
	}
}
