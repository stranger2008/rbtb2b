/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: AdvposAction.java 
 */
package com.rbt.webaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rbt.function.CommparaFuc;
import com.rbt.model.Advinfo;
import com.rbt.service.IAdvinfoService;
import com.rbt.service.IAdvposService;
import com.rbt.service.ICommparaService;

/**
 * @function 功能 广告位WebAction层实现
 * @author 创建人 胡惜坤
 * @date 创建日期 2011.12.07
 */
@Controller
public class WebadvposAction extends WebbaseAction {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;	
	/*
	 * 搜索字段 pos_type_s:广告位类型 
	 */
	public String pos_type_s;
	public String isshow_s="0";//0:表示广告位显示，1：表示不显示
	public String pos_type_name="";
	public String stradvtype="adv_type";
	/*
	 * 广告位Service业务层接口
	 */
	@Autowired
	public IAdvposService advposService;
	/*
	 * 参数业务接口
	 */
	@Autowired
	public ICommparaService commparaService;
	/*
	 * 广告位信息集合
	 */
	public List advposList;
	/*
	 * 参数集合
	 */
	public List commparaList;

	/*
	 * 搜索字段
	 */
	public String module_type_s;
	public List advinfoList;
	public Advinfo advinfo;
	@Autowired
	public IAdvinfoService advinfoService;
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		// 绑定左边广告位类型
		Map map = new HashMap();
		map.put("para_code", stradvtype);
		commparaList = this.commparaService.getList(map);
		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		if (pos_type_s != null && !pos_type_s.equals("")&&!pos_type_s.equals("all")) 
		{
			pageMap.put("pos_type", pos_type_s);
			pos_type_name=CommparaFuc.get_commparakey_By_value(pos_type_s, stradvtype);
		}
		if (isshow_s != null && !isshow_s.equals("")) {
			pageMap.put("isshow", isshow_s);
		}
		pageMap.put("para_code", stradvtype);
		// 根据页面提交的条件找出信息总数
		int count = this.advposService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);	
		advposList = this.advposService.getList(pageMap);
		return goUrl("advpos");
	}
	
	/**
	 * 方法描述：修改点击数
	 * 
	 * @return
	 * @throws Exception
	 */
	public void updateaddnum() throws Exception {
		HttpServletRequest request = getRequest();
		HttpServletResponse response=getResponse(); 
		request.setCharacterEncoding("UTF-8");
		//显示信息
		Map pageMap = new HashMap();
	   
		// 根据页面提交的条件找出信息总数
		String pos_id=request.getParameter("pos_id");
		String link_url=request.getParameter("link_url");
		if(pos_id!=null){
		    Map posmap=new HashMap();
		    posmap.put("pos_id", pos_id);
		    advinfoList=advinfoService.getList(posmap);
		    String adv_id="";
		    if(advinfoList!=null&&advinfoList.size()>0){
		    	posmap=(HashMap)advinfoList.get(0);
		    	if(posmap.get("adv_id")!=null){
		    		adv_id=posmap.get("adv_id").toString();
		    	}
		    }
			advinfo=advinfoService.get(adv_id); 
		}
		//存放点击数
		String addnum="";
		if(advinfo!=null&&!"".equals(advinfo.getAddnum())){
			addnum=Integer.toString(Integer.parseInt(advinfo.getAddnum())+1);
		}
		advinfo.setAddnum(addnum);
		advinfoService.update(advinfo);
		response.sendRedirect(link_url);   
	}
  
	/**
	 * @return the pos_type_s
	 */
	public String getPos_type_s() {
		return pos_type_s;
	}

	/**
	 * @param pos_type_s
	 *            the pos_type_s to set
	 */
	public void setPos_type_s(String pos_type_s) {
		this.pos_type_s = pos_type_s;
	}
	/**
	 * @return the advposList
	 */
	public List getAdvposList() {
		return advposList;
	}
	/**
	 * @param advposList
	 *            the advposList to set
	 */
	public void setAdvposList(List advposList) {
		this.advposList = advposList;
	}
	/**
	 * @return the commparaList
	 */
	public List getCommparaList() {
		return commparaList;
	}

	/**
	 * @param commparaList
	 *            the commparaList to set
	 */
	public void setCommparaList(List commparaList) {
		this.commparaList = commparaList;
	}
	/**
	 * @return the isshow_s
	 */
	public String getIsshow_s() {
		return isshow_s;
	}
	/**
	 * @param isshow_s
	 *            the isshow_s to set
	 */
	public void setIsshow_s(String isshow_s) {
		this.isshow_s = isshow_s;
	}
    
	public String getArea_attr() {
		return area_attr;
	}

	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}

	public String getHidden_area_value() {
		return hidden_area_value;
	}

	public void setHidden_area_value(String hidden_area_value) {
		this.hidden_area_value = hidden_area_value;
	}

	public String getModule_type_s() {
		return module_type_s;
	}

	public void setModule_type_s(String module_type_s) {
		this.module_type_s = module_type_s;
	}

	public String getPos_type_name() {
		return pos_type_name;
	}

	public void setPos_type_name(String pos_type_name) {
		this.pos_type_name = pos_type_name;
	}
	
}
