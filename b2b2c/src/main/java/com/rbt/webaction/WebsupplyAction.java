/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: MemberAction.java 
 */
package com.rbt.webaction;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.rbt.function.AreaFuc;
import com.rbt.function.CategoryFuc;
import com.rbt.function.CommparaFuc;
import com.rbt.function.ToolsFuc;
import com.rbt.index.Constants;
import com.rbt.index.LuceneUtil;
import com.rbt.index.ParaModel;
import com.rbt.index.SearchIndex;
import com.rbt.model.Member;
import com.rbt.model.Supply;
import com.rbt.service.IAdvinfoService;
import com.rbt.service.ICommparaService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMemberlevelService;
import com.rbt.service.ISupplyService;

/**
 * @function 功能 前台供应模块的操作action类
 * @author 创建人 林俊钦
 * @date 创建日期 Aug 25, 2011 2:46:34 PM
 */
@Controller
public class WebsupplyAction extends WebbaseAction {

	private static final long serialVersionUID = -4358571326626926126L;
	
	/*
	 * 业务层接口
	 */
	@Autowired
	public ISupplyService supplyService;
	@Autowired
	public ICommparaService commparaService;
	@Autowired
	public IMemberService memberService;
	@Autowired
	public IAdvinfoService advinfoService;
	@Autowired
	public IMemberlevelService memberlevelService;

	/*
	 * 供应表信息集合
	 */
	public List supplyList;
	public List commparaList;
	public List paraList;
	public List supplyTopList;
	public List keywordAdsList;
	public List sponsorAdsList;
	public List levelList;
	public List clientList;
	public List testList;

	/*
	 * 接收前台参数值
	 */
	public String type;
	public String sortby;
	public String lowwer_price;
	public String height_price;
	public String search_images;
	public String min_order;
	public String contrastValue;
	public String cb_box;
	public String client_status;
	public String date_in_date;
	public String level_code;// 所有会员的等级ID
	public String attrString;// ID与值的拼串
	public String attrValue;// 只存属性值的串
	public String cust_id;

	/*
	 * 供应模块名称
	 */
	private String module_type = "supply";

	
	/**
	 * @author : 林俊钦
	 * @date : Aug 2, 2012 4:22:52 PM
	 * @Method Description : 供应列表页的数据查询
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		// 设置网页位置
		super.setPage_position(this.module_type);
		//构造list列表搜索条件
		List shList = new ArrayList();
		// 找通过审核的记录
		shList = normalSearch("info_state","1",shList);
		Sort sort=new  Sort();  
		if (!reqKeyword("title",module_type, shList)) {
			// 按分类列表选择列表
			shList = normalSearch("cat_attr",cat_id,shList);
			// 按地区选择列表
			shList = normalSearch("area_attr",area_id,shList);
			// 搜索供应标题
			shList = normalSearch("title",searchText,shList);
			// 按搜索供应类型选择列表
			shList = normalSearch("supply_type",type,shList);
			// 选出是所有会员的信息数据
			shList = normalSearch("level_code",level_code,shList);
			// 选出是几天内发布的信息数据
			if(date_in_date!=null && !date_in_date.equals("")){
			   int int_day = Integer.parseInt(date_in_date);
			   String yestedayDate = getYestedayDate(int_day);
			   shList = rangeSearch("lu_in_date",yestedayDate,null,shList);
			}
			// 选出是经营模式信息数据
			shList = normalSearch("client_status",client_status,shList);
			// 选出最低价,最高价的信息数据
			String l_price = fullBit(lowwer_price);
			l_price=isRmb(l_price);
			String h_price = fullBit(height_price);
			h_price=isRmb(h_price);
			shList = rangeSearch("lpad_price",l_price,h_price,shList);
			// 选出最小起订量的信息数据
			String m_order= fullBit(min_order);
			shList = rangeSearch("lpad_min_order",null,m_order,shList);
			// 选出有图片的数据
			if (search_images != null && search_images.equals("1")) {
				shList = normalSearch("lu_img_path",Constants.IFIMG,shList);
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
			// 按条件进行排序
			if (sortby != null && sortby.equals("1")) {
				SortField sf=new  SortField("lpad_price", SortField.STRING,true);//降序  
				sort.setSort(new  SortField[]{sf}); 
			} else if (sortby != null && sortby.equals("2")) {
				SortField sf=new  SortField("lpad_price", SortField.STRING,false);//升序  
				sort.setSort(new  SortField[]{sf}); 
			} else if (sortby != null && sortby.equals("3")) {
				SortField sf=new  SortField("lu_in_date", SortField.STRING,true);//降序
				sort.setSort(new  SortField[]{sf}); 
			} else if (sortby != null && sortby.equals("4")) {
				SortField sf=new  SortField("lu_in_date", SortField.STRING,false);//升序
				sort.setSort(new  SortField[]{sf}); 
			} else if (sortby != null && sortby.equals("5")) {
				SortField sf=new  SortField("c_num", SortField.STRING,false);//升序
				sort.setSort(new  SortField[]{sf}); 
			} else if (sortby != null && sortby.equals("6")) {	
				SortField sf=new  SortField("c_num", SortField.STRING,true);//降序
				sort.setSort(new  SortField[]{sf}); 
			} 
		}
		// 如果搜索内容为空则不搜索
		if (is_souch) {
			//过滤地区
			shList=portalAreaFilterList(shList);
			SearchIndex si=new SearchIndex("supply");
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
			supplyList = si.search(shList, sort , pages_s, pageSize_s);  
			// 替换list表中字段的处理
			supplyList = ToolsFuc.replaceList(supplyList);
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
			// 关键字广告的列表
			Map keywordAdsMap = new HashMap();
			keywordAdsMap.put("keywordName", searchText);// 搜索的内容
			keywordAdsList = this.supplyService.getWebSupplyAdsList(keywordAdsMap);
			keywordAdsList = ToolsFuc.replaceList(keywordAdsList);
		} 
		// 绑定前台供应类型
		Map paraMap = new HashMap();
		paraMap.put("para_code", "supply_type");
		paraList = this.commparaService.getWebCommparaList(paraMap);
		// 绑定前台经营模式
		Map clientMap = new HashMap();
		clientMap.put("para_code", "client_status");
		clientList = this.commparaService.getWebCommparaList(clientMap);
		// 供应推荐信息
		SearchIndex si=new SearchIndex("supply");
		List recomList = new ArrayList();
		recomList = normalSearch("info_state","1",recomList);
		recomList = normalSearch("is_recom","1",recomList);
		supplyTopList = si.search(recomList, null , 1, 10); 
		// 所有会员等级的获取
		Map levelMap = new HashMap();
		levelList = this.memberlevelService.getList(levelMap);
		// 供应商广告的列表
		Map sponsorAdsMap = new HashMap();
		sponsorAdsMap.put("keywordName", searchText);// 搜索的内容
		sponsorAdsMap.put("cat_attr", cat_id);
		sponsorAdsList = this.advinfoService.getKeywordAdList(sponsorAdsMap);
		return goUrl("supplyList");
		
	}
	
	
	/**
	 * @MethodDescribe 方法描述 跳转到对比栏页面
	 * @author 创建人 林俊钦
	 * @date 创建日期 Aug 30, 2011 4:37:21 PM
	 */
	public String contrast() {
		if (this.cb_box != null) {
			String check_box = this.cb_box;
			check_box = check_box.replace(" ", "");
			contrastValue = check_box;
		}
		return goUrl("contrastProduct");
	}

	/**
	 * @MethodDescribe 方法描述 通过AJAX方式获取对比的数据
	 * @author 创建人 林俊钦
	 * @throws java.io.IOException
	 * @date 创建日期 Aug 30, 2011 4:37:41 PM
	 */
	public String contrastList() throws IOException {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String id = "";
		if (request.getParameter("id") != null) {
			id = request.getParameter("id");
		}
		Supply supply = this.supplyService.get(id);
		StringBuffer sb = new StringBuffer();
		// 获取供应的标题
		if (supply.getTitle() != null) {
			sb.append("<td class=td" + id + ">" + supply.getTitle() + "</td>");
			sb.append("<,>");
		} else {
			sb.append("<td class=td" + id + ">&nbsp;</td>");
			sb.append("<,>");
		}
		if (supply != null) {
			// 获取图片路径
			if (supply.getImg_path() != null) {
				String img = supply.getImg_path();
				if(img!=null && !"".equals(img)){
						String[] imgArray = img.split(",");
						if (imgArray[0] != null) {
							sb.append("<td class=td" + id + "><img src='" + imgArray[0] + "' width='80' height='80'/></td>");
							sb.append("<,>");
						}
				}
				else {
					sb.append("<td  class=td" + id + "><img src='" + super.cfg_nopic + "' width='80' height='80'/></td>");// 请求默认图片
					sb.append("<,>");
				}
			} else {
				sb.append("<td class=td" + id + ">&nbsp;</td>");
				sb.append("<,>");
			}			
			// 获取供应产品的公司名称
			if (supply.getCust_id() != null) {
				String m_id = supply.getCust_id();
				Member member = this.memberService.get(m_id);
				if (member != null) {
					if (member.getCust_name() != null) {
						sb.append("<td class=td" + id + ">" + member.getCust_name() + "</td>");
						sb.append("<,>");
					} else {
						sb.append("<td class=td" + id + ">&nbsp;</td>");
						sb.append("<,>");
					}
				} else {
					sb.append("<td class=td" + id + ">&nbsp;</td>");
					sb.append("<,>");
				}
			} else {
				sb.append("<td class=td" + id + ">&nbsp;</td>");
				sb.append("<,>");
			}
			// 获取供应类型
			if (supply.getSupply_type() != null) {
				String type_name = "";
				type_name = CommparaFuc.get_commparakey_By_value(supply.getSupply_type(), "supply_type");
				sb.append("<td class=td" + id + ">" + type_name + "</td>");
				sb.append("<,>");
			} else {
				sb.append("<td class=td" + id + ">&nbsp;</td>");
				sb.append("<,>");
			}
			// 获取所属分类
			if (supply.getCat_attr() != null) {
				String cate_name = "";
				cate_name = CategoryFuc.getCateNameByMap(supply.getCat_attr());
				sb.append("<td class=td" + id + ">" + cate_name + "</td>");
				sb.append("<,>");
			} else {
				sb.append("<td class=td" + id + ">&nbsp;</td>");
				sb.append("<,>");
			}
			// 获取所属地区
			if (supply.getArea_attr() != null) {
				String area_name = "";
				area_name = AreaFuc.getAreaNameByMap(supply.getArea_attr());
				sb.append("<td class=td" + id + ">" + area_name + "</td>");
				sb.append("<,>");
			} else {
				sb.append("<td class=td" + id + ">&nbsp;</td>");
				sb.append("<,>");
			}
			// 获取供应产品型号
			if (supply.getModel() != null) {
				sb.append("<td class=td" + id + ">" + supply.getModel() + "</td>");
				sb.append("<,>");
			} else {
				sb.append("<td class=td" + id + ">&nbsp;</td>");
				sb.append("<,>");
			}
			// 获取供应产品规格
			if (supply.getStandard() != null) {
				sb.append("<td class=td" + id + ">" + supply.getStandard() + "</td>");
				sb.append("<,>");
			} else {
				sb.append("<td class=td" + id + ">&nbsp;</td>");
				sb.append("<,>");
			}
			// 获取供应产品品牌
			if (supply.getBrand() != null) {
				sb.append("<td class=td" + id + ">" + supply.getBrand() + "</td>");
				sb.append("<,>");
			} else {
				sb.append("<td class=td" + id + ">&nbsp;</td>");
				sb.append("<,>");
			}
			// 获取供应产品计量单位
			if (supply.getUnit() != null) {
				sb.append("<td class=td" + id + ">" + supply.getUnit() + "</td>");
				sb.append("<,>");
			} else {
				sb.append("<td class=td" + id + ">&nbsp;</td>");
				sb.append("<,>");
			}
			// 获取供应产品的单价
			if (supply.getPrice() != null) {
				sb.append("<td class=td" + id + ">" + supply.getPrice() + "</td>");
				sb.append("<,>");
			} else {
				sb.append("<td class=td" + id + ">&nbsp;</td>");
				sb.append("<,>");
			}
			// 获取供应产品的最小起订量
			if (supply.getMin_order() != null) {
				sb.append("<td class=td" + id + ">" + supply.getMin_order() + "</td>");
				sb.append("<,>");
			} else {
				sb.append("<td class=td" + id + ">&nbsp;</td>");
				sb.append("<,>");
			}
			// 获取供应产品的供应总量
			if (supply.getMax_supply() != null) {
				sb.append("<td class=td" + id + ">" + supply.getMax_supply() + "</td>");
				sb.append("<,>");
			} else {
				sb.append("<td class=td" + id + ">&nbsp;</td>");
				sb.append("<,>");
			}
			// 获取供应产品的发货天数
//			if (supply.getSend_day() != null) {
//				sb.append("<td class=td" + id + ">" + supply.getSend_day() + "</td>");
//				sb.append("<,>");
//			} else {
//				sb.append("<td class=td" + id + ">&nbsp;</td>");
//				sb.append("<,>");
//			}
			// 获取供应产品的会员推荐
//			if (supply.getMem_recom() != null) {
//				if (supply.getMem_recom().equals("1")) {
//					sb.append("<td class=td" + id + ">" + "会员推荐" + "</td>");
//					sb.append("<,>");
//				} else {
//					sb.append("<td class=td" + id + ">" + "无" + "</td>");
//					sb.append("<,>");
//				}
//
//			} else {
//				sb.append("<td class=td" + id + ">&nbsp;</td>");
//				sb.append("<,>");
//			}
			// 获取供应产品的过期时间
			if (supply.getEnd_date() != null) {
				sb.append("<td class=td" + id + ">" + supply.getEnd_date().substring(0, 10) + "</td>");
				sb.append("<,>");
			} else {
				sb.append("<td class=td" + id + ">&nbsp;</td>");
				sb.append("<,>");
			}
			// 获取供应产品的是否推荐
			if (supply.getIs_recom() != null) {
				if (supply.getIs_recom().equals("1")) {
					sb.append("<td class=td" + id + ">" + "推荐" + "</td>");
					sb.append("<,>");
				} else {
					sb.append("<td class=td" + id + ">" + "无" + "</td>");
					sb.append("<,>");
				}
			} else {
				sb.append("<td class=td" + id + ">&nbsp;</td>");
				sb.append("<,>");
			}
			// 获取供应产品的发布时间
//			if (supply.getIn_date() != null) {
//				sb.append("<td class=td" + id + ">" + supply.getIn_date() + "</td>");
//				sb.append("<,>");
//			} else {
//				sb.append("<td class=td" + id + ">&nbsp;</td>");
//				sb.append("<,>");
//			}
			// 获取供应产品的点击量
//			if (supply.getClicknum() != null) {
//				sb.append("<td class=td" + id + ">" + supply.getClicknum() + "</td>");
//				sb.append("<,>");
//			} else {
//				sb.append("<td class=td" + id + ">&nbsp;</td>");
//				sb.append("<,>");
//			}
			// 获取供应产品的内容收费
//			if (supply.getFare() != null) {
//				sb.append("<td class=td" + id + ">" + supply.getFare() + "</td>");
//				sb.append("<,>");
//			} else {
//				sb.append("<td class=td" + id + ">&nbsp;</td>");
//				sb.append("<,>");
//			}
			// 获取供应产品的询价
//			if (supply.getFare() != null) {
//				sb.append("<td class=td" + id + ">" + "询价图标" + "</td>");
//				sb.append("<,>");
//			} else {
//				sb.append("<td class=td" + id + ">&nbsp;</td>");
//				sb.append("<,>");
//			}
			// 获取供应产品的订购
//			if (supply.getFare() != null) {
//				sb.append("<td class=td" + id + ">" + "订购图标" + "</td>");
//				sb.append("<,>");
//			} else {
//				sb.append("<td class=td" + id + ">&nbsp;</td>");
//				sb.append("<,>");
//			}

		}
		sb.append("<td class=td" + id + "><a href='javascript:void(0);'  onclick='removeTd(" + id + ");'>移除</a></td>");
		out.write(sb.toString());
		return null;
	}
	
}
