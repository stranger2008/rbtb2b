/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: BuyAction.java 
 */
package com.rbt.webaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.browseengine.bobo.api.BrowseException;
import com.rbt.common.Constants;
import com.rbt.function.ToolsFuc;
import com.rbt.index.SearchIndex;
import com.rbt.model.Jobtalent;
import com.rbt.service.ICommparaService;
import com.rbt.service.IJobService;
import com.rbt.service.IJobtalentService;

/**
 * @function 功能 前台招聘列表页
 * @author 创建人 林俊钦
 * @date 创建日期 Sep 1, 2011 2:20:13 PM
 */
@Controller
public class WebjobAction extends WebbaseAction {

	private static final long serialVersionUID = -4006149577992645400L;
	/*
	 * 业务层接口
	 */
	@Autowired
	public ICommparaService commparaService;
	@Autowired
	public IJobService jobService;
	@Autowired
	public IJobtalentService jobtalentService;

	/*
	 * 招聘表信息集合
	 */
	public List recomList;
	public List resueList;
	public List recomsumeList;
	public List newsumeList;
	public List hotsearchList;
	public List recomsearchList;
	public List positionList;
	public List areaselectList;
	public List salarList;
	public List catselectList;
	/*
	 * 接收前台参数值
	 */
	public String hotName;
	public String recomName;
	public String searchtype;
	public String searcharea;
	public String worktype;
	public String gender;
	public String marriage;
	public String education;
	public String experience;
	public String recomlevel;
	public String salar;
	public String sup_time;
	public String searchkeyword;
	public String imgpath;
	public String recomjoblevel;
	public String VIP;
	public String module_type = "job";
	public String serarchcate;
	public String search_cat_id;
	public String search_area_id;
	public String select_cat_id;
	public String memeberlevel = "4";// 会员的等级
	//定义是否委托求职
	public String is_trust;
	//定义国外招聘还是海外招聘标识
	public String is_where;
	/*
	 * 简历ID
	 */
	public String resumeid;

	/**
	 * @MethodDescribe 方法描述 招聘所需的绑定列表
	 * @author 创建人 林俊钦
	 * @throws com.browseengine.bobo.api.BrowseException
	 * @throws org.apache.lucene.queryParser.ParseException
	 * @throws java.io.IOException
	 * @date 创建日期 Sep 7, 2011 2:33:28 PM
	 */
	public String list() throws IOException, ParseException, BrowseException {
		//用于判断是否是招聘列表页
		searchtype = "2";
		// 工作招聘的情况特殊，需要传resume
		super.setPage_position("resume");
		
		//构造list列表搜索条件
		List shList = new ArrayList();
		Sort sort=new  Sort();  
		//过滤是否是委托求职
		if(is_trust!=null&&!"".equals(is_trust)){
			shList = normalSearch("is_trust",is_trust,shList);
			is_souch = true;
		}
		if(is_where!=null&&!"".equals(is_where)){
			shList = normalSearch("is_where",is_where,shList);
			is_souch = true;
		}
		// 薪水标准资金表
		Map salarMap = new HashMap();
		salarMap.put("para_code", "salar_type");
		salarList = this.commparaService.getWebCommparaList(salarMap);
		//招聘列表页
		if (!reqKeyword("title",module_type, shList)) {
			if (getRequest().getParameter("searchkeyword") != null && !getRequest().getParameter("searchkeyword").equals("")) {
				String minfo_title = URLDecoder.decode(getRequest().getParameter("searchkeyword"), "UTF-8");				
				shList = normalSearch("title",minfo_title,shList);
				searchkeyword = minfo_title;
			}
			// 分类属性的搜索
			if(attrString!=null && !attrString.equals("")){
				String attrs[] = attrString.split(",");
				for(int i=0;i<attrs.length;i++){
					if(attrs[i]!=null && !attrs[i].equals("")){
						String vals[] = attrs[i].split("\\|");
						if(vals!=null && vals.length>1 && !vals[1].equals("none")){
							shList = normalSearch("attr_desc",vals[1],shList);
						}
					}
				}
			}
		}
		
		// 找通过审核的记录
		shList = normalSearch("info_state","1",shList);
		// 搜索关键字名称
		shList = this.normalSearch("title", searchText, shList);
			// 搜索工作性质
		shList = this.normalSearch("job_type", worktype, shList);			
			// 搜索性别
		shList = this.normalSearch("sex", gender, shList);
			// 搜索婚姻情况
		shList = this.normalSearch("marry", marriage, shList);
			// 搜索学历要求
		shList = this.normalSearch("enducation", education, shList);
			// 搜索地区
		shList = this.normalSearch("area_attr", searcharea, shList);
			// 搜索地区
		shList = this.normalSearch("area_attr", area_id, shList);
			// 搜索分类
		shList = this.normalSearch("cat_attr", cat_id, shList);
			// 搜索工作经验
		shList = this.normalSearch("workexper", experience, shList);
			// 搜索薪资水平
		if(salar!=null&&salar.contains("~")){
			salar=salar.replace("~", "");
		}
		shList = this.normalSearch("salary", salar, shList);
		// 选出是几天内发布的信息数据
		if(sup_time!=null && !sup_time.equals("")){
		   int int_day = Integer.parseInt(sup_time);
		   String yestedayDate = getYestedayDate(int_day);
		   shList = rangeSearch("lu_in_date",yestedayDate,null,shList);
		}
			
			// 搜索分类
			if (search_cat_id != null && !search_cat_id.equals("") && !search_cat_id.equals("0")) {
				search_cat_id = search_cat_id.replace(" ", "");
				if (search_cat_id.contains(",0")) {
					search_cat_id = search_cat_id.replace(",0", "");
				}
				shList = this.normalSearch("cat_attr", search_cat_id, shList);
				is_souch = true;
			}
			
			// 搜索地区
			if (search_area_id != null && !search_area_id.equals("") && !search_area_id.equals("0")) {
				search_area_id = search_area_id.replace(" ", "");
				if (search_area_id.contains(",0")) {
					search_area_id = search_area_id.replace(",0", "");
				}
				shList = this.normalSearch("area_attr", search_area_id, shList);
				is_souch = true;
			}
			// 搜索推荐
			if (recomjoblevel != null && !recomjoblevel.equals("")) {
				shList = this.normalSearch("is_recom", "1", shList);
				is_souch = true;
			}
		// 如果搜索内容为空则不搜索
		if (is_souch) {
			//过滤地区
			shList=portalAreaFilterList(shList);
			SearchIndex si=new SearchIndex(module_type);
			//计算符合条件条数
			int count=si.getCount(shList);
			infoCount=count;
			//lucene的分页插件
			lucenePageTool(count);
			//分类信息分组
			cateList = si.catInfoNum(shList);

			//地区信息分组
			areaList = si.areaInfoNum(shList);
			//搜索符合条件的list
			positionList = si.search(shList, sort , pages_s, pageSize_s);  
			// 替换list表中字段的处理
			positionList = ToolsFuc.replaceList(positionList);
			if(cateList!=null &&cateList.size()==0){
				if(cat_id!=null && !cat_id.equals("")){
					//分类属性信息开始
					SearchIndex attrsi=new SearchIndex("categoryattr");
					List aList = new ArrayList();
					aList = normalSearch("cat_attr",cat_id,aList);
					aList = normalSearch("attr_type","2",aList);
					aList = normalSearch("is_must","1",aList);
					attrList = attrsi.search(aList,null,0,0);
					//分类属性信息结束
					//分类属性值信息开始
					SearchIndex attrvalue=new SearchIndex("attrvalue");
					List vList = new ArrayList();
					vList = normalSearch("cat_attr",cat_id,vList);
					vList = normalSearch("attr_type","2",vList);
					vList = normalSearch("is_must","1",vList);
					attrvalueList = attrvalue.search(aList,null,0,0);
					//分类属性值信息结束
				}
			}
			
		} else {
			positionList = new ArrayList();
		}
		// 热搜职位
		SearchIndex si=new SearchIndex(module_type);
		List hotsearchMap = new ArrayList();
		hotsearchMap = normalSearch("info_state","1",hotsearchMap);
		Sort sort1=new  Sort();
		SortField sf1=new  SortField("clicknum", SortField.STRING,true);//降序
		sort1.setSort(new  SortField[]{sf1}); 
		hotsearchList = si.search(hotsearchMap, sort1 , 1, 10); 
		
		//推荐职位
		SearchIndex si1=new SearchIndex(module_type);
		List recomsearchMap = new ArrayList();
		recomsearchMap = normalSearch("info_state","1",recomsearchMap);
		recomsearchMap = normalSearch("is_recom","1",recomsearchMap);
		recomsearchList = si1.search(recomsearchMap, null , 1, 10); 
		return goUrl("jobList");
	}

	/**
	 * 方法描述：AJAX加入人才库
	 * @return
	 * @throws Exception
	 */
	public void addJobTalents() throws Exception {
		String outprintString = "";
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Jobtalent jt = new Jobtalent();
		if (this.session_cust_id != null && !"".equals(session_cust_id)) {
			jt.setCust_id(this.session_cust_id);
			jt.setResume_id(resumeid);
			if (getSession().getAttribute(Constants.MEM_TYPE) != null) {
				if (getSession().getAttribute(Constants.MEM_TYPE).equals(Constants.COMPANY_MEM_TYPE)) {
					if (this.jobtalentService.get(resumeid) != null) {
						outprintString = "3";//表示该简历已经在人才库中
					} else {
						this.jobtalentService.insert(jt);
						outprintString = "2";//表示简历加入人才库成功！
					}
				} else {
					outprintString = "1";//表示该用户表示企业用户，无法加入人才库！
				}
			} else {
				outprintString = "1";
			}
		} else {
			outprintString = "0";//表示还没有登录；
		}

		out.write(outprintString);
	}

	public String getResumeid() {
		return resumeid;
	}

	public void setResumeid(String resumeid) {
		this.resumeid = resumeid;
	}
}
