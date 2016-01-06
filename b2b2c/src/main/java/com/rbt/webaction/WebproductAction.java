/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: MemberAction.java 
 */
package com.rbt.webaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.rbt.service.ICommparaService;
import com.rbt.service.IMemberlevelService;
import com.rbt.service.IProductService;
import com.rbt.function.ToolsFuc;
import com.rbt.index.Constants;
import com.rbt.index.SearchIndex;

/**
 * @function 功能 前台产品列表页
 * @author 创建人 林俊钦
 * @date 创建日期 Aug 25, 2011 2:46:34 PM
 */
@Controller
public class WebproductAction extends WebbaseAction {

	private static final long serialVersionUID = 8552683525825780533L;
	
	/*
	 * 业务层接口
	 */
	@Autowired
	public IProductService productService;
	@Autowired
	public ICommparaService commparaService;
	@Autowired
	public IMemberlevelService memberlevelService;
	
	/*
	 * 产品表信息集合
	 */
	public List productList;
	public List commparaList;
	public List paraList;
	public List TopList;
	public List clientList;
	public List levelList;
	
	/*
	 * 接收前台参数值
	 */
	public String sortby;
	public String search_images;
	public String module_type = "product";
	public String client_status;
	public String level_code;// 会员VIP的等级

	/**
	 * @Method Description : 前台产品数据列表
	 * @author : 林俊钦
	 * @date : Nov 1, 2011 2:14:43 PM
	 */
	public String list() throws Exception {
		// 设置网页位置
		super.setPage_position(this.module_type);
		//构造list列表搜索条件
		List shList = new ArrayList();
		Sort sort=new  Sort();  
		if (!reqKeyword("title",module_type, shList)) {
			// 按分类列表选择列表
			shList = this.normalSearch("cat_attr", cat_id, shList);
			// 按地区选择列表
			shList = this.normalSearch("area_attr", area_id, shList);
			// 按搜索标题选择列表
			shList = this.normalSearch("title", searchText, shList);
			// 选出是所有会员的信息数据
			shList = this.normalSearch("level_code", level_code, shList);
			// 选出是经营模式信息数据
			shList = normalSearch("client_status",client_status,shList);
			// 选出有图片的数据
			if (search_images != null && search_images.equals("1")) {
				shList = normalSearch("lu_img_path",Constants.IFIMG,shList);
				is_souch = true;
			}
			// 按条件进行排序
			if (sortby != null && sortby.equals("1")) {
				SortField sf=new  SortField("lu_in_date", SortField.STRING,true);//降序
				sort.setSort(new  SortField[]{sf}); 
			} else if (sortby != null && sortby.equals("2")) {
				SortField sf=new  SortField("lu_in_date", SortField.STRING,false);//升序
				sort.setSort(new  SortField[]{sf}); 
			} else if (sortby != null && sortby.equals("3")) {
				SortField sf=new  SortField("clicknum", SortField.STRING,true);//升序
				sort.setSort(new  SortField[]{sf}); 
			} else if (sortby != null && sortby.equals("4")) {	
				SortField sf=new  SortField("clicknum", SortField.STRING,false);//降序
				sort.setSort(new  SortField[]{sf}); 
			} 
			else {
				SortField sf=new  SortField("level_code", SortField.STRING,true);//默认level_code降序排序
				SortField sf1=new  SortField("fund_num", SortField.STRING,true);//默认fund_num降序排序
				SortField sf2=new  SortField("lu_in_date", SortField.STRING,true);//默认lu_in_date降序排序
				sort.setSort(new  SortField[]{sf,sf1,sf2}); 
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
		// 如果搜索内容为空则不搜索
		if (is_souch) {
			//过滤地区未实现
			//pageMap=super.portalAreaFilter(pageMap);
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
			productList = si.search(shList, sort , pages_s, pageSize_s);  
			// 替换list表中字段的处理
			productList = ToolsFuc.replaceList(productList);
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
		// 产品排行版
		SearchIndex si=new SearchIndex(module_type);
		List ptopList = new ArrayList();
		ptopList = normalSearch("info_state","1",ptopList);
		Sort sort1=new  Sort();  
		SortField sf=new  SortField("clicknum", SortField.STRING,true);
		sort1.setSort(new  SortField[]{sf});
		TopList = si.search(ptopList, sort1 , 1, 10); 
		
		
		// 所有会员等级的获取
		Map levelMap = new HashMap();
		levelList = this.memberlevelService.getList(levelMap);
		// 绑定前台经营模式
		Map clientMap = new HashMap();
		clientMap.put("para_code", "client_status");
		clientList = this.commparaService.getWebCommparaList(clientMap);
		// 绑定当前的和行业	
		return goUrl("productList");
	}
}
