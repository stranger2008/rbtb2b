/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: WebaboutusAction.java 
 */
package com.rbt.webaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rbt.common.Constants;
import com.rbt.model.Aboutus;
import com.rbt.model.Commpara;
import com.rbt.service.IAboutusService;
import com.rbt.service.ICommparaService;

/**
 * @function 功能 会员action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Wed sep 16 08:48:07 CST 2011
 */
@Controller
public class WebaboutusAction extends WebbaseAction {
 
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 6030511427979470104L;
	/*
	 * 参数对象
	 */
	public Commpara compara;
	/*
	 * 关于我们对象
	 */
	public Aboutus aboutus;
	/*
	 * 业务层接口
	 */
	@Autowired
	public ICommparaService commparaService;
	@Autowired
	public IAboutusService aboutusService;
	
	public List commpareaIndexList;
	public List aboutusIndexList;
	public String aboutus_id;
	/*
	 * 参数列表
	 */
	public List comparaList;
	/*
	 * 关于我们列表
	 */
	public List aboutusList;
	private static final String CH_ID_type = "ch_id";
	//定义关于我们栏目值
	public String paravalue;
	//定义关于我们栏目标识
	public String parakey;
	public String info_id;
	public String ch_id;
   
	
	/**
	 * @author : 胡惜坤
	 * @Method Description :帮助中心
	 */
	public   String execute() throws Exception
	{
		//获取帮助中心分类信息
		HashMap commparaMap=new HashMap();
		commparaMap.put("para_code", CH_ID_type);
		commparaMap.put("enabled", "0");
		commpareaIndexList=commparaService.getList(commparaMap);
		//获取商城底部帮助中心信息
		aboutusIndexList=gethelplist("");
		//获取帮助中心详细内容
		aboutus=new Aboutus();
		if(info_id!=null&&!"".equals(info_id))
		{
			aboutus=aboutusService.get(info_id);
		}else if(aboutusIndexList!=null&&aboutusIndexList.size()>0) 
		{	//用于获取帮助中心默认第一条信息
			HashMap  abMap =new HashMap ();
			if(ch_id!=null&&!"".equals(ch_id))
			{
				List ahList=gethelplist(ch_id);
				if(ahList!=null&&ahList.size()>0)
				{
					abMap=(HashMap)ahList.get(0);
				}
			}
			else {
				abMap=(HashMap)aboutusIndexList.get(0);
			}
			if(abMap.get("info_id")!=null)
			{
				aboutus.setInfo_id(abMap.get("info_id").toString());
			}
			if(abMap.get("title")!=null)
			{
				aboutus.setTitle(abMap.get("title").toString());
			}
			if(abMap.get("content")!=null)
			{
				aboutus.setContent(abMap.get("content").toString());
			}
		}
		return goUrl("about_us");
	}
	/**
	 * 获取帮助中心信息列表
	 * 胡惜坤
	 * @return
	 */
	public List gethelplist(String str_ch_id)
	{
		List aList=new ArrayList();
		//获取商城底部帮助中心信息
		HashMap aboutusMap=new HashMap ();
		aboutusMap.put("plat_type", Constants.MALL_TYPE_B2B);
		aboutusMap.put("para_code", CH_ID_type);
		if(str_ch_id!=null&&!"".equals(str_ch_id))
		{
			aboutusMap.put("ch_id", str_ch_id);
		}
		aList=aboutusService.getList(aboutusMap);
		return aList;
	}
	

//	// 绑定关于我们
//	public String execute() throws Exception {
//		Map map = new HashMap();
//		map.put("para_code", CH_ID);
//		comparaList = this.commparaService.getList(map);
//		mall_type =Constants.MALL_TYPE_B2B;
//		HttpServletRequest request = getRequest();
//		// 获取url参数
//		String info_id = request.getParameter("info_id");
//		// 如果info_id不为空 说明点击列表页面跳转到内容页面
//		// 如果info_id等于空 说明点击左侧菜单项
//		if (info_id == null) {
//			String ch_id = request.getParameter("ch_id");
//			// 如果ch_id等于空 说明页面刚刚加载 给ch_id初始值
//			if (ch_id == null) {
//				ch_id = "1";
//			}
//			Map pageMap = new HashMap();
//			pageMap.put("ch_id", ch_id);
//			pageMap.put("para_code", CH_ID);
//			pageMap.put("plat_type", mall_type);
//			map.put("para_value", ch_id);
//			map.put("plat_type", mall_type);
//			// 根据页面提交的条件找出信息总数
//			int count = this.aboutusService.getCount(pageMap);
//			// 分页插件
//			pageMap = super.pageTool(count, pageMap);
//			// 根据配置表ch_id查找aboutus对应的列表
//			aboutusList = this.aboutusService.getList(pageMap);
//			// 获取参数表对象
//			Map hmap = new HashMap();
//			hmap.put("para_code", "ch_id");
//			hmap.put("ch_id", "ch_id");
//			List comList = this.commparaService.getList(map);
//			if (comList != null && comList.size() > 0) {
//				hmap = (HashMap) comList.get(0);
//				parakey = hmap.get("para_key").toString();
//				paravalue = hmap.get("para_value").toString();
//			}
//			// 如果查找的列表行数大于1 说明跳转页面应该为列表页 否则为内容页面
//			if (aboutusList != null && aboutusList.size() > 1) {
//				return goUrl("about_us");
//			} else {
//				aboutus = new Aboutus();
//				// 如果列表行数不为空 跳转到内容页显示数据 否则提示内容页为空
//				if (aboutusList != null && aboutusList.size() > 0) {
//					Map value = (HashMap) aboutusList.get(0);
//					aboutus.setTitle(value.get("title").toString());
//					aboutus.setContent(value.get("content").toString());
//					aboutus.setIn_date(value.get("in_date").toString());
//				} else {
//					aboutus.setTitle("该页暂无内容");
//				}
//				return goUrl("about_us_detail");
//			}
//		} else {
//			aboutus = new Aboutus();
//			aboutus = this.aboutusService.get(info_id);
//			return goUrl("about_us_detail");
//		}
//
//	}

	public Commpara getCompara() {
		return compara;
	}

	public void setCompara(Commpara compara) {
		this.compara = compara;
	}

	public List getComparaList() {
		return comparaList;
	}

	public void setComparaList(List comparaList) {
		this.comparaList = comparaList;
	}

	public Aboutus getAboutus() {
		return aboutus;
	}

	public void setAboutus(Aboutus aboutus) {
		this.aboutus = aboutus;
	}

	public List getAboutusList() {
		return aboutusList;
	}

	public void setAboutusList(List aboutusList) {
		this.aboutusList = aboutusList;
	}
	public String getInfo_id() {
		return info_id;
	}
	public void setInfo_id(String info_id) {
		this.info_id = info_id;
	}
	public String getCh_id() {
		return ch_id;
	}
	public void setCh_id(String ch_id) {
		this.ch_id = ch_id;
	}
	

	
}