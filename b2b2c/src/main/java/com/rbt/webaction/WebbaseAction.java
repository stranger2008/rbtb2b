/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: WebBaseAction.java 
 */
package com.rbt.webaction;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.rbt.action.BaseAction;
import com.rbt.common.Constants;
import com.rbt.common.util.PageUtil;
import com.rbt.createHtml.SpecialField;
import com.rbt.function.AreaFuc;
import com.rbt.function.CommparaFuc;
import com.rbt.function.KeyWordInsertFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.index.ParaModel;
import com.rbt.model.Area;
import com.rbt.model.Category;
import com.rbt.service.IAreaService;
import com.rbt.service.ICategoryService;
import com.rbt.service.ICategoryattrService;


/**
 * @function 功能 前台列表页通用父类
 * @author  创建人 李良林
 * @date  创建日期 2011-10-20
 */
@SuppressWarnings("static-access")
@Controller
public class WebbaseAction extends BaseAction {
	
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 6601865002767423743L;

	/*
	 * 业务层接口
	 */
	@Autowired
	public ICategoryService categoryService;	
	@Autowired
	public IAreaService areaService;
	@Autowired
	public ICategoryattrService categoryattrService;	
	public KeyWordInsertFuc keyWordInsert;
	public String searchText;
	public String attrString;// ID与值的拼串
	/*
	 * 变量的定义
	 */
	public String page_position;
	public String cat_id;
	public String area_id;
	public boolean is_souch=false;
	public List attrList;
	public List cateList;
	public List areaList;
	public int infoCount;
	public List attrvalueList;
	private static final String SESSION_AREA_ID = "session_area_id";
	
	//获取地区的主ID
    public String up_area_info=SysconfigFuc.getSysValue("cfg_topareaid");
    //获取分类的主ID
    public String up_cat_info=SysconfigFuc.getSysValue("cfg_topcatid");
    //是否显示地区
	public String isviewarea=SysconfigFuc.getSysValue("cfg_classifiedisarea");
	
	public String web_title=SysconfigFuc.getSysValue("cfg_webname");
	
	public String web_phone=SysconfigFuc.getSysValue("cfg_phone");
	
	public String web_basehost=SysconfigFuc.getSysValue("cfg_basehost");
	
	public String web_openmall=SysconfigFuc.getSysValue("cfg_openmall");
	
	public String cfg_mallwebname=SysconfigFuc.getSysValue("cfg_mallwebname");
	
	public String cfg_mallindexurl=SysconfigFuc.getSysValue("cfg_mallindexurl");
	
	public String cfg_malllogo=SysconfigFuc.getSysValue("cfg_malllogo");
	public String cfg_logo_img=SysconfigFuc.getSysValue("cfg_logourl");
	
	//列表页SEO标题、关键字、描述
	public String list_seo_title,list_seo_keyword,list_seo_desc;
	public String mallsearchType;
	
	//商城是否开启地区子站，0：是，1：否
	public String cfg_area_shop=SysconfigFuc.getSysValue("cfg_area_shop");
	//是否开启商城国家地区过滤
	public String cfg_country_filter=SysconfigFuc.getSysValue("cfg_country_filter");
	
	
	//设置列表页的导航位置
	@SuppressWarnings("unchecked")
	public void setPage_position(String module_type) {
		Map map = new HashMap();
		map.put("area_attr", this.area_id);
		map.put("cat_attr", this.cat_id);
		map.put("module_type", module_type);
		SpecialField sf = new SpecialField();
		this.page_position = sf.getListPosition(map);
		
		//设置列表页seo
		setListPageSeo(module_type);
		
	}	
	
	//设置列表页SEO值
	//主要是对搜索关键字、地区、分类设置seo
	public void setListPageSeo(String module_type){
		
		StringBuffer sb = new StringBuffer();

		//获取系统设置的seo标题间隔符
		String title_separator = SysconfigFuc.getSysValue("cfg_separator");
		
		//获取前台搜索的分类，参数名称cat_id
		Category category = new Category();
		if(this.cat_id != null && !"".equals(this.cat_id)){
			category = categoryService.get(this.cat_id);
		}
		String seo_title = "",seo_keyword = "",seo_desc = "",cat_name = "";
		if(category != null){
			if(category.getCat_name() != null) cat_name = category.getCat_name();
			if(category.getSeotitle() != null) seo_title = category.getSeotitle();
			if(category.getSeokeyword() != null) seo_keyword = category.getSeokeyword();
			if(category.getSeodesc() != null) seo_desc = category.getSeodesc();
		}
		//获取前台搜索的关键字
		String wd = "";
		HttpServletRequest request = getRequest();
		if (request.getParameter("wd") != null && !request.getParameter("wd").equals("")) {
			try {
				wd = URLDecoder.decode(request.getParameter("wd"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		//获取前台搜索的地区，参数名称area_id
		Area area = new Area();
		if(this.area_id != null && !"".equals(this.area_id)){
			area = areaService.get(area_id);
		}
		String area_name = "";
		if(area != null){
			if(area.getArea_name() != null) area_name = area.getArea_name();
		}
		
		//开始拼seo标题
		//分类需要单独处理，分类后台有设置seo
		if(this.cat_id != null && !"".equals(wd) && this.area_id == null){
			sb = new StringBuffer();
			sb.append(seo_title);
			this.list_seo_keyword = seo_keyword;
			this.list_seo_desc = seo_desc;
		}else{
			if(this.getSession().getAttribute("ses_area_id")!=null){
				String areaname=AreaFuc.getFiresAreaName(this.getSession().getAttribute("ses_area_id").toString());
				sb.append(areaname+title_separator);
			}else{
				if(!"".equals(area_name)){
					sb.append(area_name+title_separator);
				}
			}
			if(!"".equals(wd)){
				sb.append(wd+title_separator);
			}
			
			if(!"".equals(cat_name)){
				sb.append(cat_name+title_separator);
			}
		}
		
		//获取栏目名称
		String channelname=CommparaFuc.get_commparakey_By_value(module_type, "module_type");
		if(!"".equals(module_type)&&!"".equals(channelname)){
			sb.append(channelname+title_separator);
		}
		
		sb.append(web_title);
		list_seo_title = sb.toString();
	}
	
	/**
	 * @Method Description : 列表页分类的列表
	 * @author : 林俊钦
	 * @date : Nov 1, 2011 1:14:10 PM
	 */	
	@SuppressWarnings("unchecked")
	public void setCatList(Map pageMap){
		
		//分类是否显示
		pageMap.put("is_display", "1");
		if(cat_id == null){
			cat_id = "";
		}
		pageMap.put("up_cat_id", cat_id);
		
		String module_type = "";
		if(pageMap.get("table_name") != null){
			module_type = pageMap.get("table_name").toString();
		}
		
		//处理特殊module_type的值
		//如果module_type的类型为company，则转换成member,为know时则转换成ask表
		module_type = changeModuleType(module_type);
		
		pageMap.put("table_name", module_type);
		
		cateList = categoryService.getWebCategroyList(pageMap);
		if(cateList!=null && cateList.size()==0 && !cat_id.equals("")){
			Map map = new HashMap();
			map.put("cat_attr", cat_id);
			map.put("attr_type", "2");
			map.put("is_must", "1");
			//返回产品属性列表	
			attrList= categoryattrService.getList(map);	
		}
	}
	
	/**
	 * @Method Description : 列表页地区的列表
	 * @author : 林俊钦
	 * @date : Nov 1, 2011 1:26:03 PM
	 */
    @SuppressWarnings({ "static-access", "unchecked" })
	public void setAreaList(Map pageMap){	
		
		if(area_id == null){
			area_id = "";
		}
		pageMap.put("up_area_id", area_id);
		
		String module_type = "";
		if(pageMap.get("table_name") != null){
			module_type = pageMap.get("table_name").toString();
		}
		
		//处理特殊module_type的值
		//如果module_type的类型为company，则转换成member,为know时则转换成ask表
		module_type = changeModuleType(module_type);
		
		pageMap.put("table_name", module_type);
		
		areaList = areaService.getWebAreaList(pageMap);
    }
    
    
    public String changeModuleType(String module_type){
    	if(module_type.equals("company")){
    		module_type = "member";
		}else if(module_type.equals("know")){
			module_type = "ask";
		}
    	return module_type;
    }
    
    //是否开启地区过滤管理功能
	//控制运营商后台地区过滤
	//user_type是4，代表为运营商后台子账户
	@SuppressWarnings("unchecked")
	public Map areafilter(Map pageMap){
		if(cfg_area_filter.equals("0") && this.session_cust_type.equals(Constants.CUST_TYPE)){
			String area_filter = this.getSessionFieldValue(Constants.AREA_MANAGER);
			if(!area_filter.equals("")&&!area_filter.equals("0")){
				pageMap.put("area_filter", area_filter);
			}
		}
		return pageMap;
	}
	
	//设置session中的区域标识
	public void setSessionAreaId(String area_id){
		this.getSession().setAttribute(SESSION_AREA_ID, area_id);
	}
    
	//获取session中的区域标识
	//先从session中取，如果取不到，在根据IP定位找出地区标识
	public String getSessionAreaId(){
		String area_id ="";
		if(!this.getSessionFieldValue(SESSION_AREA_ID).equals("")){
			area_id = this.getSessionFieldValue(SESSION_AREA_ID);
		}else{
			area_id = AreaFuc.getAreaidByIpaddr(this.getRequest());
			this.setSessionAreaId(area_id);
		}
		return area_id;
	}
	
	//前台列表页区域过滤
	public Map portalAreaFilter(Map map){
		if(this.cfg_area_filter!=null&&!this.cfg_area_filter.equals("")&&this.cfg_area_filter.equals("0")){
			String area_id = getSessionAreaId();
			map.put("area_attr", area_id);
		}
		return map;
	}
	//前台列表页区域过滤
	public List portalAreaFilterList(List slist){
		if(this.cfg_area_filter!=null&&!this.cfg_area_filter.equals("")&&this.cfg_area_filter.equals("0")){
			String area_id = getSessionAreaId();
			slist=normalSearch("area_attr",area_id,slist);
		}
		return slist;
	}
	
	/**
	 * @Method Description : list列表Map的赋值
	 * @author : 林俊钦
	 * @date : 1,1,2012 1:26:03 PM
	 */
	public Map setMapValue(String fieldName,String fieldValue,Map map){
		if(map == null) return map;
		if(fieldName == null) return map;
		if(fieldValue!=null&&!fieldValue.equals("")){
			map.put(fieldName, fieldValue);
			is_souch = true;
		}
		return map;
	}
	

	
	/**
	 * @author : 林俊钦
	 * @date : Apr 10, 2012 4:05:10 PM
	 * @Method Description :AJAX分页搜索条件的过滤
	 */
	public Map ajaxMap(Map map){
		HttpServletRequest request = getRequest();
		String currentPage="1",pageSize="20";
		if(request.getParameter("cp")!=null){
			currentPage=request.getParameter("cp");
		}
		if(request.getParameter("ps")!=null){
			pageSize=request.getParameter("ps");
		}
		int startRow=(Integer.parseInt(currentPage)-1)*Integer.parseInt(pageSize);//计算开始行
		map.put("start", startRow);
		map.put("limit", pageSize);		
		return map;
	}
	
	
	
	
	/**
	 * @Method Description : 返回按关键字查找列表的次数，插入关键字表
	 * @author : 林俊钦
	 * @throws java.io.UnsupportedEncodingException
	 * @date : 1,1,2012 1:26:03 PM
	 */
	public Boolean reqKeyword(String fieldName,String module_type,List list) throws UnsupportedEncodingException{
		HttpServletRequest request = super.getRequest();
		if (request.getParameter("wd") != null && !request.getParameter("wd").equals("")) {
			String keyword = URLDecoder.decode(request.getParameter("wd"), "UTF-8");
			//setMapValue(fieldName, keyword,map);
			normalSearch(fieldName,keyword,list);
			searchText = keyword;
			// 插入关键字表中
			KeyWordInsertFuc.wdInsert(keyword, module_type);
			is_souch = true;
		}else{
			is_souch = false;
		}
		return is_souch;
	}
	
	/**
	 * @Method Description : 返回按关键字查找列表的次数，插入关键字表
	 * @author : 林俊钦
	 * @throws java.io.UnsupportedEncodingException
	 * @date : 1,1,2012 1:26:03 PM
	 */
	public Boolean reqKeyword(String fieldName,String module_type,Map map) throws UnsupportedEncodingException{
		HttpServletRequest request = super.getRequest();
		if (request.getParameter("wd") != null && !request.getParameter("wd").equals("")) {
			String keyword = URLDecoder.decode(request.getParameter("wd"), "UTF-8");
			setMapValue(fieldName, keyword,map);
			searchText = keyword;
			// 插入关键字表中
			KeyWordInsertFuc.wdInsert(keyword, module_type);
			is_souch = true;
		}
		return is_souch;
	}
	
	
	/**
	 * @author : 林俊钦
	 * @date : Jul 25, 2012 10:15:33 AM
	 * @Method Description : lucene索引分页数据
	 */
	public void lucenePageTool(int count) {
		PageUtil page = new PageUtil();
		page.setCurPage(pages_s);
		page.setPageSize(pageSize_s);
		page.setTotalRow(count);
		pageBar = page.getToolsMenu();
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Jul 25, 2012 10:20:30 AM
	 * @Method Description : lucene普通搜索条件的赋值
	 */
	public List normalSearch(String fieldName,String fieldValue,List list){
		if(fieldName==null || fieldName.equals("")) return list;
		if(fieldValue!=null&&!fieldValue.equals("")){
			ParaModel pm = new ParaModel(fieldName,fieldValue);
			list.add(pm);
			is_souch = true;
		}
		return list;
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Jul 25, 2012 2:44:11 PM
	 * @Method Description : lucene 范围搜索条件的赋值
	 */
	public List rangeSearch(String fieldName,String start,String end,List list){
		if(fieldName==null || fieldName.equals("")) return list;
		if(start!=null && start.equals("") && end!=null && end.equals("")){
			return list;
		}else{
			ParaModel pm = new ParaModel(fieldName,start,end);
			list.add(pm);
			

			System.out.println(fieldName+"======================="+start+","+end);
			
			
			is_souch = true;			
		}
		return list;
	}
	
	
	/**
	 * @author : 林俊钦
	 * @date : Jul 27, 2012 9:42:07 AM
	 * @Method Description : 补位的方法
	 */
	public String fullBit(String n){
		  if(n==null || n.equals("")){
			  return null;
		  }
		  int in = Integer.parseInt(n);
		  NumberFormat formatter = NumberFormat.getNumberInstance();   
		  formatter.setMinimumIntegerDigits(17);   
		  formatter.setGroupingUsed(false);   
		  String s = formatter.format(in);   
		  System.out.println(s+"=============");
		  return s;
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Jul 27, 2012 9:59:16 AM
	 * @Method Description : 过滤不符合RMB格式的字符串
	 */
	public String isRmb(String s){
		if(s==null || s.equals("")){
			return null;
		}else{
			if(s.indexOf(".")==-1){
				s=s+".00";
			}
		}
		return s;
	}
	
	
	/**
	 * @author : 林俊钦
	 * @date : Jul 27, 2012 2:40:30 PM
	 * @Method Description : 获取当前的日期
	 */
	public String getYestedayDate(int d){
		Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
		calendar.add(Calendar.DATE, d); //得到前一天
		String yestedayDate = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
		return yestedayDate;
	}
	/**
	 * @author : 胡惜坤
	 * @date : Jul 30, 2012 9:40:30 PM
	 * @Method Description : 转换日期格式
	 */
	public String formatLuDate(String d){
		if(d==null || d.equals("")){
			return null;
		}else{
			d= new SimpleDateFormat("yyyyMMdd").format(d);
		}
		return d;
		
	}
	

}
