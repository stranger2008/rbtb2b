package com.rbt.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.service.IAttrvalueService;
import com.rbt.service.ICategoryattrService;

public class CategoryAttrFuc extends CreateSpringContext{

	
	public static ICategoryattrService getCatAttrObj() {
		return (ICategoryattrService) getContext().getBean("categoryattrService");
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Aug 22, 2012 9:18:13 AM
	 * @Method Description :获取分类属性的列表
	 */
	public static List getCatAttrListByCatid(String cat_attr,String ischange,String attr_value){
		
		ICategoryattrService categoryattrService = getCatAttrObj();
		
		List attrList = new ArrayList();
		
		String strattr="";
		if(cat_attr!=null&&!cat_attr.equals(""))
		{
		   String[] strattrs=cat_attr.split(",");
		   List subattrList=null;
		   for(int i=0;i<strattrs.length;i++)
		   {
			   if(strattrs[i]!=null)
			   {
				   if(i!=strattrs.length&&i!=0){
					   strattr+=","+strattrs[i].toString();
				   } else {
					   strattr=strattrs[i].toString();
				   }
				   //根据分类ID找出对应的各级分类属性
				   Map attrMap=new HashMap();
				   attrMap.put("cat_attr_more", strattr);
				   subattrList = categoryattrService.getList(attrMap);
			   }
			   //获得属性列表合并到最终的List中 返回产品属性列表
			   if(subattrList!=null&&subattrList.size()>0)
			   {
				   if(ischange!=null&&ischange.equals("1")){
					   String attrvalue[] = attr_value.split("##########");
					   for(int j=0;j<subattrList.size();j++)
					   {
						   Map supMap1=new HashMap();
						   supMap1=(Map)subattrList.get(j);
						   //获取重新提交的值替换原来的值
							supMap1.put("attr_value", attrvalue[j]);			   
						   attrList.add(supMap1);
					   }
				   }else{
					   for(int j=0;j<subattrList.size();j++)
					   {
						   Map supMap1=new HashMap();
						   supMap1=(Map)subattrList.get(j);		   
						   attrList.add(supMap1);
					   }					   
				   }
			   }
		   }
		}
		return attrList;
	}
	
	
	/**
	 * @author : 林俊钦
	 * @date : Aug 22, 2012 9:33:25 AM
	 * @Method Description :根据分类ID串获取分类属性列表
	 */
	public static List getCatAttrListByCatIdStr(String cat_attr,String ischange,String attr_value){
		ICategoryattrService categoryattrService = getCatAttrObj();
		List attrList = new ArrayList();
		String attrstr="";
		if(cat_attr!=null&&!cat_attr.equals("")){
		   String[] attrs=cat_attr.split(",");
		   List attrStrList=null;
		   for(int i=0;i<attrs.length;i++){
			   //找出所有的不同的分类等级的菜单
			   if(attrs[i]!=null && !"".equals(attrs[i])){
				   if(i!=attrs.length&&i!=0){
					   attrstr+=","+attrs[i].toString();
				   } else {
					   attrstr=attrs[i].toString();
				   }
				   //根据分类ID找出对应的各级分类属性
				   Map attrMap=new HashMap();
				   attrMap.put("cat_attr_more", attrstr);
				   attrStrList = categoryattrService.getList(attrMap);
			   }
			   attrList.addAll(attrStrList);	
		   }
		   //判断是否为提交后的页面操作，比如新增或更新
		   if(ischange!=null&&ischange.equals("1")){
			   String attrvalue[] = attr_value.split("##########");
			   for(int j=0;j<attrList.size();j++)
			   {
				   Map supMap=new HashMap();
				   supMap=(Map)attrList.get(j);
				   //获取重新提交的值替换原来的值
				   supMap.put("dft_value", attrvalue[j].trim());			   
				   attrList.set(j, supMap);
			   }
		   }
		}
		return attrList;
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Aug 22, 2012 2:58:03 PM
	 * @Method Description :获取属性值列表
	 */
	public static List getAttrValueListByCatIdStr(String cat_attr){
		List attrvalList = getListByCatId(cat_attr);
		return attrvalList;
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Aug 23, 2012 2:21:42 PM
	 * @Method Description : 通过分类id中
	 */
	/**
	 * @author : 林俊钦
	 * @date : Aug 23, 2012 2:21:42 PM
	 * @Method Description : 通过分类id串找出相应的属性列表
	 */
	public static List getListByCatId(String cat_attr){
		ICategoryattrService categoryattrService = getCatAttrObj();
		List list = new ArrayList();
		String attrstr="";
		if(cat_attr!=null&&!cat_attr.equals("")){
		   String[] attrs=cat_attr.split(",");
		   List attrStrList=null;
		   for(int i=0;i<attrs.length;i++){
			   //找出所有的不同的分类等级的菜单
			   if(attrs[i]!=null && !"".equals(attrs[i])){
				   if(i!=attrs.length&&i!=0){
					   attrstr+=","+attrs[i].toString();
				   } else {
					   attrstr=attrs[i].toString();
				   }
				   //根据分类ID找出对应的各级分类属性
				   Map attrMap=new HashMap();
				   attrMap.put("cat_attr_more", attrstr);
				   attrStrList = categoryattrService.getCatAttrList(attrMap);
			   }
			   list.addAll(attrStrList);	
		   }
		}
		return list;
	}
}
