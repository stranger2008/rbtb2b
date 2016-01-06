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
import com.rbt.model.Resumeinbox;
import com.rbt.service.ICommparaService;
import com.rbt.service.IResumeService;
import com.rbt.service.IResumeinboxService;

/**
 * @function 功能 前台人才列表页
 * @author 创建人 林俊钦
 * @date 创建日期 Sep 1, 2011 2:20:13 PM
 */
@Controller
public class WebresumeAction extends WebbaseAction {

	private static final long serialVersionUID = 8665628415192701972L;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IResumeService resumeService;
	@Autowired
	public ICommparaService commparaService;	
	@Autowired
	public IResumeinboxService resumeinboxService;

	/*
	 * 人才表信息集合
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
	public List resumeguidanceList;
	public List workplaceList;
	public List skillList;
	public List policyList;
	
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
	public String recomjoblevel;
	public String search_cat_id;
	public String search_area_id;
	public String is_trust;
	/*
	 * 简历收件箱表信息集合
	 */
	public List resumeinboxList;
	public List resumeList;
	
	
	/*
	 * 人才模块名称
	 */
	private String module_type = "resume";
	
	/*
	 * 搜索字段：应聘时间的结束时间
	 */
	public String enddateString;
	public String resumeid;
	public String jobid;
	public String companyid;
	

	/**
	 * @MethodDescribe 方法描述 前台人才绑定列表页
	 * @author 创建人 林俊钦
	 * @throws com.browseengine.bobo.api.BrowseException
	 * @throws org.apache.lucene.queryParser.ParseException
	 * @throws java.io.IOException
	 * @date 创建日期 Sep 7, 2011 2:03:52 PM
	 */
	public String list() throws IOException, ParseException, BrowseException {
		// 设置网页位置
		super.setPage_position(this.module_type);

		//构造list列表搜索条件
		List shList = new ArrayList();
		Sort sort=new  Sort();  
		// 设置分类列表的TITLE
		if(!"".equals(is_trust)){
			shList = normalSearch("is_trust",is_trust,shList);
			is_souch = true;
		}
		// 薪水标准资金表
		Map salarMap = new HashMap();
		salarMap.put("para_code", "salar_type");
		salarList = this.commparaService.getWebCommparaList(salarMap);
		// 判断人才页面
		searchtype = "1";
		if (!reqKeyword("resume_name",module_type, shList)) {
			if (getRequest().getParameter("searchkeyword") != null && !getRequest().getParameter("searchkeyword").equals("")) {
				String minfo_title = URLDecoder.decode(getRequest().getParameter("searchkeyword"), "UTF-8");
				shList = normalSearch("resume_name",minfo_title,shList);
				searchText = minfo_title;
				is_souch = true;
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
			//	搜索标题
		shList = this.normalSearch("resume_name", searchText, shList);
			// 搜索工作性质
		shList = this.normalSearch("job_type", worktype, shList);
			// 搜索性别要求
		shList = this.normalSearch("sex", gender, shList);
			// 搜索工作经验
		shList = this.normalSearch("work_exper", experience, shList);
			// 搜索婚姻状况
		shList = this.normalSearch("marry", marriage, shList);
			// 搜索学历要求
		shList = this.normalSearch("education", education, shList);
			// 搜索地区
		shList = this.normalSearch("area_attr", searcharea, shList);
		shList = this.normalSearch("area_attr", area_id, shList);
			// 搜索分类
		shList = this.normalSearch("cat_attr", cat_id, shList);
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
			shList = this.normalSearch("cat_attr", cat_attr, shList);
			is_souch = true;
		}
		// 搜索地区
		if (search_area_id != null && !search_area_id.equals("") && !search_area_id.equals("0")) {
			search_area_id = search_area_id.replace(" ", "");
			if (search_area_id.contains(",0")) {
				search_area_id = search_area_id.replace(",0", "");
				is_souch = true;
			}
			shList = this.normalSearch("area_attr", search_area_id, shList);
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

		}
		// 热搜人才
		SearchIndex si=new SearchIndex(module_type);
		List hotsearchMap = new ArrayList();
		Sort sort1=new  Sort();  
		SortField sf2=new  SortField("clicknum", SortField.STRING,true);//降序
		sort1.setSort(new  SortField[]{sf2}); 
		hotsearchMap = normalSearch("is_recom","1",hotsearchMap);
		hotsearchList = si.search(hotsearchMap, sort1 , 1, 10); 
		
		// 推荐人才
		SearchIndex si1=new SearchIndex(module_type);
		List recomsearchMap = new ArrayList();
		recomsearchMap = normalSearch("info_state","1",recomsearchMap);
		recomsearchMap = normalSearch("is_recom","1",recomsearchMap);
		recomsearchList = si1.search(recomsearchMap, null , 1, 10); 
		
		return goUrl("resumeList");
	}
	
	
	
	/**
	 * 方法描述：AJAX获取用户简历列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public void getResumeInBoxs() throws Exception {
		String outprintString = "";
		StringBuilder strBuilder = new StringBuilder();
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 判断用户是否登录
		if (this.session_cust_id != null) {
			String custidString = this.session_cust_id;
			// 判断用户的类型，如果为企业用户，则无法申请职位
			if (getSession().getAttribute(Constants.MEM_TYPE)!=null && getSession().getAttribute(Constants.MEM_TYPE).equals(Constants.PERSONAL_MEM_TYPE)) {
				HashMap mapresume = new HashMap();
				mapresume.put("cust_id", custidString);
				mapresume.put("info_state", 1);// 审核通过状态
				resumeList = this.resumeService.getList(mapresume);
				// 判断用户是否有多个简历，若有多个简历的话，提示用户选择其中一种简历
				if (resumeList != null && resumeList.size() != 0) {
					for (int i = 0; i < resumeList.size(); i++) {
						HashMap hresume = new HashMap();
						hresume = (HashMap) resumeList.get(i);
						strBuilder.append(hresume.get("resume_name"));
						strBuilder.append("&&&&&");
						strBuilder.append(hresume.get("resume_id"));
						strBuilder.append("#####");
					}
					outprintString = strBuilder.toString();

				} else {
					outprintString = "0";// 简历是非法的或者未审核的
				}
			} else {
				outprintString = "0";// 表示是企业用户，无法申请职位
			}
		} else {
			outprintString = "0";// 表示还没有登录；
		}
		out.write(outprintString);
	}

	/**
	 * 方法描述：AJAX加入人才库
	 * 
	 * @return outprintString="0";//表示还没有登录； outprintString="1";//表示是企业用户，无法申请职位
	 *         outprintString="2";//表示该人才还没有简历，跳转到新增简历页面
	 *         outprintString="3";//表示已经申请过该职位了
	 *         outprintString="4";//表示该用户有多个简历，需要选择其中的一个简历
	 *         outprintString="5";//表示发送申请职位成功！
	 *         outprintString="6";//简历是非法的或者未审核的
	 * @throws Exception
	 */
	public void addResumeInBox() throws Exception {
		String outprintString = "";
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Resumeinbox jt = new Resumeinbox();
		// 判断用户是否登录
		if (!this.session_user_id.equals("")) {
			String useridString = this.session_user_id;
			jt.setApply_cust_id(useridString);
			jt.setGet_cust_id(companyid);
			jt.setJob_id(jobid);
			jt.setIsinvite("0");
			jt.setState("0");
			if(getSession().getAttribute(Constants.MEM_TYPE)!=null)
			{
			// 判断用户的类型，如果为企业用户，则无法申请职位
			if (getSession().getAttribute(Constants.MEM_TYPE).equals(Constants.PERSONAL_MEM_TYPE)) {
				// 判断用户是否已经填写了简历，若没有填写简历的话，填写简历
				HashMap mapresume = new HashMap();
				mapresume.put("user_id", useridString);
				mapresume.put("info_state", 1);// 审核通过状态
				resumeList = this.resumeService.getList(mapresume);
				if (resumeList!= null&&resumeList.size()>0) {
					HashMap maps = new HashMap();
					maps.put("job_id", jobid);
					maps.put("apply_cust_id", useridString);
					// 判断页面请求是否有传简历ID过来，如果有resumeid的话，就去resumeid的值
					if (resumeid != null && !resumeid.equals("0")&& !resumeid.equals("-1") && !resumeid.equals("")) {
						maps.put("resume_id", resumeid);
					}
					resumeinboxList = this.resumeinboxService.getList(maps);
					// 判断用户是否已经申请过该职位
					if (resumeinboxList != null && resumeinboxList.size() != 0) {
						outprintString = "3";// 表示已经申请过该职位了
					} else {
						// 判断用户是否有多个简历，若有多个简历的话，提示用户选择其中一种简历
						if (resumeList != null && resumeList.size() != 0&& resumeList.size() == 1) {
							// 判断页面请求是否有传简历ID过来，如果有resumeid的话，就去resumeid的值
							if (resumeid != null && !resumeid.equals("0")&& !resumeid.equals("-1")&& !resumeid.equals("")) {
								jt.setResume_id(resumeid);
							} else {
								HashMap getresume = new HashMap();
								getresume = (HashMap) resumeList.get(0);
								jt.setResume_id(getresume.get("resume_id").toString());
							}
							this.resumeinboxService.insert(jt);
							outprintString = "5";// 表示发送申请职位成功！

						} else if (resumeList != null && resumeList.size() == 0) {
							outprintString = "6";// 简历是非法的或者未审核的
						} else if (resumeList != null && resumeList.size() > 1) {
							if (resumeid != null && !resumeid.equals("0")&& !resumeid.equals("-1")&& !resumeid.equals("")) {
								jt.setResume_id(resumeid);
								this.resumeinboxService.insert(jt);
								outprintString = "5";// 表示发送申请职位成功！
							} else {
								outprintString = "4";// 表示该用户有多个简历，需要选择其中的一个简历
							}

						}
					}

				} else {
					outprintString = "2";// 表示该人才还没有简历，跳转到新增简历页面
				}
			} else {
				outprintString = "1";// 表示是企业用户，无法申请职位
			}
		} else {
			outprintString = "0";// 表示还没有登录；
		}
		}
		else {
			outprintString = "0";// 表示还没有登录；
		}
		out.write(outprintString);
	}
}
