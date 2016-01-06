/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: WebbuyAction.java 
 */
package com.rbt.webaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.browseengine.bobo.api.BrowseException;
import com.rbt.function.ToolsFuc;
import com.rbt.index.SearchIndex;
import com.rbt.service.IBuyService;
import com.rbt.service.ICommparaService;
import com.rbt.service.IMemberlevelService;

/**
 * @function 功能 前台求购列表页
 * @author 创建人 林俊钦
 * @date 创建日期 Sep 1, 2011 2:20:13 PM
 */
@Controller
public class WebbuyAction extends WebbaseAction {

	private static final long serialVersionUID = 8526638849847460732L;
	
	/*
	 * 业务层接口
	 */
	@Autowired
	public IBuyService buyService;
	@Autowired
	public ICommparaService commparaService;
	@Autowired
	public IMemberlevelService memberlevelService;

	/*
	 * 求购表信息集合
	 */
	public List buyList;
	public List buyTopList;
	public List paraList;
	public List levelList;
	public List attrvalueList;
	
	/*
	 * 接收前台参数值
	 */
	public String type;
	public String sortby;
	public String level_code;// 会员VIP的等级
	public String date_in_date;
	
	/*
	 * 求购模块名称
	 */
	private String module_type = "buy";

	public String list() throws IOException, ParseException, BrowseException {
		// 设置网页位置
		super.setPage_position(this.module_type);
		
		//构造list列表搜索条件
		List shList = new ArrayList();
		// 找通过审核的记录
		shList = normalSearch("info_state","1",shList);
		Sort sort=new  Sort();  
		if (!reqKeyword("title",module_type, shList)) {
			
			// 按分类列表选择列表
			shList = this.normalSearch("cat_attr", cat_id, shList);
			// 按求购类型选择列表
			shList = this.normalSearch("buy_type", type, shList);
			// 按地区选择列表
			shList = this.normalSearch("area_attr", area_id, shList);
			// 选出是所有会员的信息数据
			shList = this.normalSearch("level_code", level_code, shList);
			//搜索内容
			shList = this.normalSearch("title", searchText, shList);
			// 选出是几天内发布的信息数据
			if(date_in_date!=null && !date_in_date.equals("")){
			   int int_day = Integer.parseInt(date_in_date);
			   String yestedayDate = getYestedayDate(int_day);
			   shList = rangeSearch("lu_in_date",yestedayDate,null,shList);
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
			// 按条件进行排序
			if (sortby != null && sortby.equals("1")) {
				SortField sf=new  SortField("lu_in_date", SortField.STRING,true);//降序
				sort.setSort(new  SortField[]{sf}); 
			} else if (sortby != null && sortby.equals("2")) {
				SortField sf=new  SortField("lu_in_date", SortField.STRING,false);//升序
				sort.setSort(new  SortField[]{sf}); 
			} else if (sortby != null && sortby.equals("3")) {
				SortField sf=new  SortField("c_num", SortField.STRING,true);//升序
				sort.setSort(new  SortField[]{sf}); 
			} else if (sortby != null && sortby.equals("4")) {	
				SortField sf=new  SortField("c_num", SortField.STRING,false);//降序
				sort.setSort(new  SortField[]{sf}); 
			} 
			else {
				SortField sf=new  SortField("level_code", SortField.STRING,true);//默认level_code降序排序
				SortField sf1=new  SortField("fund_num", SortField.STRING,true);//默认fund_num降序排序
				SortField sf2=new  SortField("lu_in_date", SortField.STRING,true);//默认lu_in_date降序排序
				sort.setSort(new  SortField[]{sf,sf1,sf2}); 
			}

			
		}
		// 如果搜索内容为空则不搜索
		if (is_souch) {
			SearchIndex si=new SearchIndex(module_type);
			//过滤地区未实现
			//pageMap=super.portalAreaFilter(pageMap);
			//过滤地区
			shList=portalAreaFilterList(shList);
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
			buyList = si.search(shList, sort , pages_s, pageSize_s);  
			// 替换list表中字段的处理
			buyList = ToolsFuc.replaceList(buyList);
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
		// 绑定前台的参数列表
		Map paraMap = new HashMap();
		paraMap.put("para_code", "buy_type");
		paraList = this.commparaService.getWebCommparaList(paraMap);
		
		// 求购排行版
		SearchIndex si=new SearchIndex(module_type);
		List recomList = new ArrayList();
		recomList = normalSearch("info_state","1",recomList);
		Sort sortr=new  Sort();  
		SortField sf=new  SortField("clicknum", SortField.STRING,true);//升序
		sortr.setSort(new  SortField[]{sf}); 
		buyTopList = si.search(recomList, sortr , 1, 10); 
		

		// 所有会员等级的获取
		Map levelMap = new HashMap();
		levelList = this.memberlevelService.getList(levelMap);
		return goUrl("buyList");
	}
	
	
}
