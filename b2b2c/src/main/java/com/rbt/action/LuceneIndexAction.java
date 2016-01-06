/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: BuyAction.java 
 */
package com.rbt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.opensymphony.xwork2.Action;
import com.rbt.action.BaseAction;
import com.rbt.index.CreateIndex;
import com.rbt.service.ICommparaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


/**
 * @function 功能  表索引的创建
 * @author  创建人  林俊钦
 * @date  创建日期  Aug 24, 2011 1:50:56 PM
 */
@Controller
public class LuceneIndexAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	/*
	 * 业务层接口
	 */
	@Autowired
	public ICommparaService commparaService;

	
	/*
	 * 参数名称，找出需要建立索引的表
	 */
	private String para_code = "lucene_index";
	
	private String index_code="index_code";
	/*
	 * 参数表数据列表
	 */
	public List commparalist;

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map map = new HashMap();
		//查找出求购的所有类型，并在新增页上显示
		map.put("para_code", para_code);
		commparalist = this.commparaService.getList(map);
		return goUrl(INDEXLIST);
	}

	/**
	 * @MethodDescribe 方法描述   建立相应的索引
	 * @author  创建人  林俊钦
	 * @throws java.io.IOException
	 * @date  创建日期  Aug 24, 2011 11:05:51 AM
	 */
	public String createIndex() throws IOException {
		// 创建request,response对象
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		PrintWriter out = response.getWriter();
		// 定义接收字符的格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//获取后台参数传过来的要更新的索引名称
		String keyparm = "",className="",methodName="";
		if (request.getParameter("key") != null) {
			keyparm = request.getParameter("key");			
		}
		if (keyparm.equals("0")) {
			out.write("请选择要更新索引的名称");
		} else {
			if(keyparm.indexOf(",")>0){
				String[] parmValue=keyparm.split(",");
				if(parmValue!=null){
					if(parmValue[0].toString()!=null&&!parmValue[0].equals("")){
						className=parmValue[0].toString();
					}
					if(parmValue[1].toString()!=null&&!parmValue[1].equals("")){
						methodName=parmValue[1].toString();
					}
					try {				
						Class c = Class.forName(className);
						Object obj=c.newInstance();
						Method m = c.getMethod(methodName);
						//执行相应的方法
						m.invoke(obj);
						out.write("更新成功");
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
					
				}
			}
		}
		return Action.NONE;
	}

	/*
	 * 获取参数列表
	 */
    public void getCommparaLists()
    {
    	Map pageMap = new HashMap();
    	pageMap.put("para_code", index_code);
    	commparalist = this.commparaService.getList(pageMap);
    }
    
	/**
	 * @author : 林俊钦
	 * @date : Aug 27, 2012 2:45:26 PM
	 * @Method Description : 更新索引页面
	 */
	public String update(){
		getCommparaLists();
		return goUrl("update");
	}
	
	/**
	 * @author : 林俊钦
	 * @throws java.io.IOException
	 * @date : Sep 10, 2012 2:50:47 PM
	 * @Method Description : 更新全部索引
	 */
	public void allUpdate() throws IOException{
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		//设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		//实例化建立索引对象
		CreateIndex ci=new CreateIndex();
		try {
			ci.createSupplyIndex();
			ci.createCatIndex();
			ci.createCateAttrValueIndex();
			ci.createCateAttrIndex();
			ci.createAreaIndex();
			ci.createBuyIndex();
			ci.createClassifiedIndex();
			ci.createProductIndex();
			ci.createShowinfoIndex();
			ci.createJobIndex();
			ci.createResumeIndex();
			ci.createKnowIndex();
			ci.createSubjectIndex();
			ci.createGalleryIndex();
			ci.createVideoIndex();
			ci.createDownloadIndex();
			ci.createBrandIndex();
			ci.createMemberIndex();
			ci.createNewsIndex();
			ci.CreateGoodsIndex();
			out.print("1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("更新全部索引失败!");
			out.print("0");
			e.printStackTrace();
		}
	}
	
	/**
	 * @author : 林俊钦
	 * @throws java.io.IOException
	 * @date : Aug 27, 2012 3:18:42 PM
	 * @Method Description : 更新对应的模块索引
	 */
	public void updateIndex() throws IOException{
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		//设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		//实例化建立索引对象
		CreateIndex ci=new CreateIndex();
		//获取模块名称
		String modelName=request.getParameter("mod");
		if(modelName.equals("supply")){
			ci.createSupplyIndex();
			out.print("1");
			return;
		}else if(modelName.equals("category")){
			ci.createCatIndex();
			out.print("1");
			return;
		}else if(modelName.equals("attrvalue")){
			ci.createCateAttrValueIndex();
			out.print("1");
			return;
		}else if(modelName.equals("categoryattr")){
			ci.createCateAttrIndex();
			out.print("1");
			return;
		}else if(modelName.equals("area")){
			ci.createAreaIndex();
			out.print("1");
			return;
		}else if(modelName.equals("buy")){
			ci.createBuyIndex();
			out.print("1");
			return;
		}else if(modelName.equals("classified")){
			ci.createClassifiedIndex();
			out.print("1");
			return;
		}else if(modelName.equals("product")){
			ci.createProductIndex();
			out.print("1");
			return;
		}else if(modelName.equals("showinfo")){
			ci.createShowinfoIndex();
			out.print("1");
			return;
		}else if(modelName.equals("job")){
			ci.createJobIndex();
			out.print("1");
			return;
		}else if(modelName.equals("resume")){
			ci.createResumeIndex();
			out.print("1");
			return;
		}else if(modelName.equals("know")){
			ci.createKnowIndex();
			out.print("1");
			return;
		}else if(modelName.equals("subject")){
			ci.createSubjectIndex();
			out.print("1");
			return;
		}else if(modelName.equals("gallery")){
			ci.createGalleryIndex();
			out.print("1");
			return;
		}else if(modelName.equals("video")){
			ci.createVideoIndex();
			out.print("1");
			return;
		}else if(modelName.equals("download")){
			ci.createDownloadIndex();
			out.print("1");
			return;
		}else if(modelName.equals("brand")){
			ci.createBrandIndex();
			out.print("1");
			return;
		}else if(modelName.equals("company")){
			ci.createMemberIndex();
			out.print("1");
			return;
		}else if(modelName.equals("news")){
			ci.createNewsIndex();
			out.print("1");
			return;
		}else if(modelName.equals("goods")){
			ci.CreateGoodsIndex();
			out.print("1");
			return;
		}
		out.print("0");
	}
	
	
	/**
	 * @author : 林俊钦
	 * @throws java.io.IOException
	 * @date : Aug 28, 2012 11:26:58 AM
	 * @Method Description : 增量更新索引
	 */
	public void updateAddIndex() throws IOException{
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		//设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		//实例化建立索引对象
		CreateIndex ci=new CreateIndex();
		//获取模块名称
		String modelName=request.getParameter("mod");
		if(modelName.equals("supply")){
			ci.createSupplyIncreIndex();
			out.print("1");
			return;
		}else if(modelName.equals("category")){
			ci.createCatIncreIndex();
			out.print("1");
			return;
		}else if(modelName.equals("attrvalue")){
			ci.createCateAttrValueIncreIndex();
			out.print("1");
			return;
		}else if(modelName.equals("categoryattr")){
			ci.createCateAttrIncreIndex();
			out.print("1");
			return;
		}else if(modelName.equals("area")){
			ci.createAreaIncreIndex();
			out.print("1");
			return;
		}else if(modelName.equals("buy")){
			ci.createBuyIncreIndex();
			out.print("1");
			return;
		}else if(modelName.equals("classified")){
			ci.createClassifiedIncreIndex();
			out.print("1");
			return;
		}else if(modelName.equals("product")){
			ci.createProductIncreIndex();
			out.print("1");
			return;
		}else if(modelName.equals("showinfo")){
			ci.createShowinfoIncreIndex();
			out.print("1");
			return;
		}else if(modelName.equals("job")){
			ci.createJobIncreIndex();
			out.print("1");
			return;
		}else if(modelName.equals("resume")){
			ci.createResumeIncreIndex();
			out.print("1");
			return;
		}else if(modelName.equals("know")){
			ci.createKnowIncreIndex();
			out.print("1");
			return;
		}else if(modelName.equals("subject")){
			ci.createSubjectIncreIndex();
			out.print("1");
			return;
		}else if(modelName.equals("gallery")){
			ci.createGalleryIncreIndex();
			out.print("1");
			return;
		}else if(modelName.equals("video")){
			ci.createVideoIncreIndex();
			out.print("1");
			return;
		}else if(modelName.equals("download")){
			ci.createDownloadIncreIndex();
			out.print("1");
			return;
		}else if(modelName.equals("brand")){
			ci.createBrandIncreIndex();
			out.print("1");
			return;
		}else if(modelName.equals("company")){
			ci.createMemberIncreIndex();
			out.print("1");
			return;
		}else if(modelName.equals("news")){
			ci.createNewsIncreIndex();
			out.print("1");
			return;
		}else if(modelName.equals("goods")){
			ci.createGoodsIncreIndex();
			out.print("1");
			return;
		}
		out.print("0");
	}
	

	
	/**
	 * @return the commparalist
	 */
	public List getCommparalist() {
		return commparalist;
	}

	/**
	 * @param commparalist the commparalist to set
	 */
	public void setCommparalist(List commparalist) {
		this.commparalist = commparalist;
	}
}
