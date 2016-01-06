/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: WebclassifiedAction.java 
 */
package com.rbt.webaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Sort;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.browseengine.bobo.api.BrowseException;
import com.rbt.function.AreaFuc;
import com.rbt.function.CategoryFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.function.ToolsFuc;
import com.rbt.index.Constants;
import com.rbt.index.SearchIndex;
import com.rbt.model.Category;
import com.rbt.model.Classified;
import com.rbt.model.Member;
import com.rbt.model.Organize;
import com.rbt.service.IAdvinfoService;
import com.rbt.service.IAreaService;
import com.rbt.service.ICategoryService;
import com.rbt.service.ICategoryattrService;
import com.rbt.service.IChannelService;
import com.rbt.service.IClassifiedService;
import com.rbt.service.IInfoattrService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.IOrganizeService;

/**
 * @function 功能 前台分类信息的操作action类
 * @author 创建人 邱景岩
 * @date 创建日期 Oct 17, 2011 9:48:28 AM
 */
@Controller
public class WebclassifiedAction extends WebbaseAction {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -1319019967973140973L;
	/*
	 * 业务层接口
	 */
	@Autowired
	private IClassifiedService classifiedService;
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private IMemberService memberService;
	@Autowired
	private IMemberuserService memberuserService;
	@Autowired
	private ICategoryattrService categoryattrService;
	@Autowired
	private IChannelService channelService;
	@Autowired
	private IOrganizeService organizeService;
	@Autowired
	public IAdvinfoService advinfoService;
	@Autowired
	public IAreaService areaService;
	@Autowired
	public IInfoattrService infoattrService;

	/*
	 * 公用方法
	 */
	private Organize organize;

	/*
	 * 供应表信息集合
	 */
	public List classifiedList;
	public List classCatList;
	public List twoclassCatList;
	public List channelList;
	public List infoattrList;
	/*
	 * 分类信息对象
	 */
	public Classified classified;
	/*
	 * 分类信息对象
	 */
	public Category category;
	/*
	 * 会员对象
	 */
	public Member member;
	public List memberuserList;
	public String user_name;

	/*
	 * 接收前台参数值
	 */
	private String mem_type;
	private String info_id;

	/*
	 * 获取地区名称
	 */
	private String area_name;
	/*
	 * 获取国家名称
	 */
	private String country;
	/*
	 * 获取地区列表
	 */

	public List arealevellist;
	public List searchList;
	public List areacountryList;
	public List areacharList;
	public List countrycharList;
	// 保留地区串
	public static String area_level;
	// 保留分类串
	public static String cat_level;
	public String categoryname;

	// 获取上一级名称
	public String cateupname;
	// 获取上一级ID号
	public String catupid;
	public String member_type;
	public String attr_desc;
	public String strbulider;
	public String attr;

	// 本类查询
	public String intrkey;
	// 是否有图
	public String img_state;
	// 地区名称
	public String charea_name;
	// 是否显示
	public String display;
	// 图文 文本切换
	public String styletype;
	// 全站搜
	public String title;
	// 判断是否是全站搜索
	public String all;
	// 搜索内容
	public String search;
	// 栏目名称
	public static final String channelName = "分类信息";
	// 表名
	private String module_type = "classified";
	// 导航地址
	public String nav_url;
	// 地区隐藏域
	public String hidden_area_id;
	// 搜索类型
	public String type;
	//获取地区拼音
	public String en_name;
	//属性列表
	public List cate_attrList;

	/*
	 * 定义常量字段,用于从基本参数中找出相应的模块值
	 */
	public String para_code = "classified_type";

	/**
	 * @author : 林俊钦
	 * @date : Sep 8, 2012 1:41:44 PM
	 * @Method Description :国家地区切换
	 */
	public String countylist() {
		HttpServletRequest request = getRequest();
		if (request.getParameter("type") != null) {
			type = request.getParameter("type");
		}
		String areaid = "";
		if (request.getParameter("en_name") != null) {
			areaid = AreaFuc.getparea_id(request.getParameter("en_name"));
			this.getSession().setAttribute("ses_area_id", areaid);
		} else {
			areaid = getareaidvalue();
		}
		en_name = AreaFuc.getAreaEnglishName(areaid);
		getCountryArea(areaid);

		//通过areaid获取国家id
		String TopAreaId = AreaFuc.getTopAreaId(areaid);
		HashMap map = new HashMap();
		map.put("up_area_id", TopAreaId);
		map.put("is_city", "1");
		areaList = areaService.getAreaCityList(map);
		areacharList = areaService.getCharacterList(map);
		//查找出国家
		HashMap mapcountry = new HashMap();
		//筛选国家 系统顶级地区ID：9999999999
		mapcountry.put("up_area_id", "9999999999");
		areacountryList = areaService.getList(mapcountry);
		countrycharList = areaService.getCountryList(mapcountry);

		return goUrl("classified");
	}

	/**
	 * @author : 林俊钦
	 * @date : Sep 8, 2012 1:42:02 PM
	 * @Method Description :分类页面
	 */
	public String arealist() throws IOException {
		HttpServletRequest request = getRequest();
		area_level = null;
		cat_level = null;
		// 按分类列表选择列表
		HashMap map = new HashMap();
		// 分类信息
		map.put("module_type", this.module_type);
		map.put("up_cat_id", cfg_topcatid);
		classCatList = this.categoryService.getAreaCategoryList(map);
		// 获取地区id
		if (request.getParameter("en_name") != null) {
			area_id = AreaFuc.getparea_id(request.getParameter("en_name"));
		}
		String areaid = getareaidvalue();
		// 获取地区id结果放隐藏域
		hidden_area_id = areaid;
		en_name = AreaFuc.getAreaEnglishName(areaid);
		this.getSession().setAttribute("ses_area_id", areaid);
		//获取国家和地区
		if (this.getSession().getAttribute("ses_area_id") != null)
			getCountryArea(this.getSession().getAttribute("ses_area_id").toString());
		// 获取地区分站信息
		getorganize(areaid);
		HashMap twomap = new HashMap();
		// 分类信息
		twomap.put("module_type", this.module_type);
		twoclassCatList = this.categoryService.getTwoAreaCategoryList(twomap);
		// 获取配置导航链接
		getnavlink();
		// 设置网页位置
		super.setPage_position(this.module_type);
		return goUrl("classcatList");
	}

	/**
	 * @author : 林俊钦
	 * @throws java.io.IOException
	 * @throws org.apache.lucene.queryParser.ParseException
	 * @throws com.browseengine.bobo.api.BrowseException
	 * @date : Sep 8, 2012 11:00:34 AM
	 * @Method Description :根据分类查询出相关信息
	 */
	public String list() throws ParseException, IOException, BrowseException {
		// 设置网页位置
		super.setPage_position(this.module_type);
		// 获取配置导航链接
		getnavlink();
		// 获取分类编号 主要用于保存第一次进来的分类id
		String level = "";
		if (cat_id != null && !"".equals(cat_id)) {
			category = this.categoryService.get(cat_id);
			if (category != null) {
				level = category.getCat_level();
			}
		}
		if (cat_level == null || "1".equals(level)) {
			cat_level = cat_id;
		}
		// 全局搜索后id为空 默认用第一次的初始分类id
		if (cat_id == null || "".equals(cat_id)) {
			cat_id = cat_level;
		}
		if (cat_id != null && !"".equals(cat_id)) {
			// 获取分类名称
			categoryname = CategoryFuc.getCateNameByMap(cat_id);
			// 获取分类名称上级
			category = this.categoryService.get(cat_id);
		}
		if (category != null) {
			catupid = category.getUp_cat_id();
			cateupname = CategoryFuc.getCateNameByMap(catupid);
		}
		String areaid = "";
		if ("0".equals(isviewarea)) {
			areaid = getareaidvalue();
		}
		en_name = AreaFuc.getAreaEnglishName(areaid);
		// 图文切换 1标识图 2标识文字 默认为0
		if (styletype == null || "0".equals(styletype)) {
			styletype = "2";
		}
		// 读取下一级地区名称
		if ("0".equals(isviewarea)) {
			HashMap arealevelMap = new HashMap();
			arealevelMap.put("up_area_id", areaid);
			arealevellist = areaService.getList(arealevelMap);
		}

		
		

		//构造list列表搜索条件
		List shList = new ArrayList();
		if ("0".equals(isviewarea)) {
			// 获取地区编号
			if (area_level == null) {
				area_level = areaid;
			}
			//获取国家和地区
			if (this.getSession().getAttribute("ses_area_id") != null)
				getCountryArea(this.getSession().getAttribute("ses_area_id").toString());
			// 获取地区id结果放隐藏域
			hidden_area_id = area_level;
			// 获取地区分站信息
			getorganize(area_level);
			charea_name = AreaFuc.getAreaNameByMap(areaid);
			// 获取地区的下一级地区名称
			HashMap areaMap = new HashMap();
			areaMap.put("up_area_id", area_level);
			areaList = areaService.getList(areaMap);
			// 判断地区
			shList = normalSearch("area_attr", areaid, shList);
		}

		// 本类搜
		if (intrkey != null && !"".equals(intrkey)) {
			shList = normalSearch("title", intrkey, shList);
			shList = normalSearch("cat_attr", cat_id, shList);
		}

		// 全站搜
		HttpServletRequest request = getRequest();
		// 按关键字查找列表
		if (request.getParameter("wd") != null && !request.getParameter("wd").equals("")) {
			String wd = URLDecoder.decode(request.getParameter("wd"), "UTF-8");
			searchText = wd;
			keyWordInsert.wdInsert(searchText, "classified");
			title = wd;
			all = "1";
		}
		//搜索标题
		if (title != null && !"".equals(title)) {
			search = title;
			// 清空分类
			if (!"1".equals(type)) {
				//cat_id = "";
			}
			shList = normalSearch("title", title, shList);
		}

		// 分类
		if (!"2".equals(all)) { // 2标识全站搜索
			if (!"1".equals(all)) { // 1标识本类搜索
				
			}
			shList = normalSearch("cat_attr", cat_id, shList);
			// 判断企业或是个人
			if (mem_type != null && !"2".equals(mem_type) && !"".equals(mem_type)) {
				shList = normalSearch("mem_type", mem_type, shList);
			}
			// 选出有图片的数据
			if (img_state != null && !"".equals(img_state) && !"0".equals(img_state)) {
				shList = normalSearch("lu_img_path", Constants.IFIMG, shList);
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

		shList = normalSearch("info_state", "1", shList);
		// 分类列表
		SearchIndex si = new SearchIndex("classified");
		//分类信息分组
		cateList = si.catInfoNum(shList);
		//地区信息分组
		areaList = si.areaInfoNum(shList);
		// 根据地区查询地区以及地区下一级的分类信息
		classifiedList = si.search(shList, null, pages_s, pageSize_s);
		//查找属性
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
		if (classifiedList != null && classifiedList.size() > 0) {
			HashMap claMap = new HashMap();
			// 批量替换地区名称
			for (int i = 0; i < classifiedList.size(); i++) {
				claMap = (HashMap) classifiedList.get(i);
				if (claMap.get("area_attr") != null) {
					claMap.put("area_id", claMap.get("area_attr").toString());
					claMap.put("area_attr", AreaFuc.getAreaNameByMap(claMap.get("area_attr").toString()));
				}
				// 取图片串第一条记录
				if (claMap.get("img_path") != null) {
					String imgpath = claMap.get("img_path").toString();
					if (imgpath.indexOf(",") > -1) {
						String[] words = imgpath.split(",");
						claMap.put("img_path", words[0]);
					}
				}
				// 过滤分类信息内容
				if (claMap.get("info_desc") != null) {
					String infodesc = ToolsFuc.getChinese(claMap.get("info_desc").toString());
					claMap.put("info_desc", infodesc);
				}
			}
		}
		
		return goUrl("classifiedList");
	}

	// 获取信息详细（内容页）
	public String detail() {
		// 获取配置导航链接
		getnavlink();
		// 获取地区名称
		String areaid = getareaidvalue();
		en_name = AreaFuc.getAreaEnglishName(areaid);
		// 获取地区分站信息
		getorganize(area_level);
		//获取国家和地区
		getCountryArea(areaid);
		if (info_id != null || "".equals(info_id)) {
			classified = this.classifiedService.get(info_id);
			if (classified != null) {
				// 跟新点击数
				classified.setClicknum(Integer.toString(Integer.parseInt(classified.getClicknum()) + 1));
				this.classifiedService.update(classified);
				member = this.memberService.get(classified.getCust_id());
				if (member != null) {
					member_type = member.getMem_type();
				}
				Map map = new HashMap();
				map.put("cust_id", member.getCust_id());
				map.put("user_type", 1);
				memberuserList = this.memberuserService.getList(map);
				if (memberuserList != null && memberuserList.size() > 0) {
					HashMap username_map = new HashMap();
					username_map = (HashMap) memberuserList.get(0);
					if (username_map != null) {
						user_name = username_map.get("user_name").toString();
					}
				}
			}
			// 获取地区id结果放隐藏域
			hidden_area_id = areaid;
			// 获取分类最后一级别
			String catid = classified.getCat_attr();
			if (catid != null && !"".equals(catid)) {
				cat_id = catid.substring(catid.lastIndexOf(",") + 1);
			}
			// 获取分类名称
			categoryname = CategoryFuc.getCateNameByMap(cat_id);
			// 获取分类名称上级
			category = this.categoryService.get(cat_id);
			if (category != null) {
				catupid = category.getUp_cat_id();
				cateupname = CategoryFuc.getCateNameByMap(category.getUp_cat_id());
			}
			// 读取信息属性拼串显示到页面
			String infoattr_id = classified.getInfoattr_id();
			Map map = new HashMap();
			map.put("infoattr_id", infoattr_id);
			infoattrList = infoattrService.getList(map);
			StringBuilder bulider = new StringBuilder();

			if (infoattrList != null && infoattrList.size() > 0) {
				bulider.append("<div class='infomain'><ul>");
				for (Iterator it = infoattrList.iterator(); it.hasNext();) {
					HashMap cMap = (HashMap) it.next();
					String attr_name = "", attr_value = "";
					if (cMap.get("attr_name") != null) {
						attr_name = cMap.get("attr_name").toString();
					}
					if (cMap.get("attr_value") != null) {
						attr_value = cMap.get("attr_value").toString();
					}
					bulider.append("<li>");
					bulider.append(attr_name + ":");
					if (attr_value.indexOf("|") > -1) {
						attr_value = attr_value.replace(" ", "").replace(",", "");
						String[] value = attr_value.split("\\|");
						for (int j = 0; j < value.length; j++) {
							bulider.append("<i>" + value[j] + ",</i>");
						}

					} else {
						bulider.append("<i>" + attr_value + "</i>");
					}
					bulider.append("</li>");
				}
			}
			bulider.append("</ul></div>");
			strbulider = bulider.toString();
		}

		// 设置网页位置
		super.setPage_position(this.module_type);
		return goUrl("classifiedInfo");
	}

	//Ajax根据IP获取地区名称
	public void getareaName() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		//设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		//核心板不一至的代码
		String areaid = getareaidvalue();
		String county = AreaFuc.getTopAreaName(areaid);
		String name = AreaFuc.getAreaNameByMap(areaid);
		String country_name = AreaFuc.getAreaEnglishName(AreaFuc.getTopAreaId(areaid));
		String area_name = AreaFuc.getAreaEnglishName(areaid);
		PrintWriter out = response.getWriter();
		out.write(county + "," + name + "," + country_name + "," + area_name);
	}

	// 获取配置导航链接
	public void getnavlink() {
		// 根据自定义编码获取导航实体
		String cfgroute = SysconfigFuc.getSysValue("cfg_templateroute");
		// 存储文件名
		String dir = "";
		String file_name = "";
		HashMap ch_map = new HashMap();
		ch_map.put("ch_name", channelName);
		channelList = channelService.getList(ch_map);
		if (channelList != null && channelList.size() > 0) {
			HashMap valueMap = new HashMap();
			valueMap = (HashMap) channelList.get(0);
			dir = valueMap.get("save_dir").toString();
			file_name = valueMap.get("file_name").toString();
		}
		nav_url = "/" + cfgroute + "/" + dir + "/" + file_name;
	}

	/**
	 * 方法描述：分类详细页浏览次数累加
	 * 
	 * @return
	 * @throws Exception
	 */
	public void updateClickNum() throws Exception {
		PrintWriter out = getResponse().getWriter();
		this.classifiedService.updateclicknum(info_id);
		Classified cla = new Classified();
		cla = this.classifiedService.get(info_id);
		out.write(cla.getClicknum());
	}

	// 判断area_id是否存在 如果不存在去session取 如果session过期 根据IP获取地区id
	// 通过城市标识获取城市名称
	public String getareaidvalue() {

		String areaid = "";
		if (area_id != null && !"".equals(area_id)) {
			areaid = area_id;
			getSession().setAttribute("ses_area_id", area_id);
		} else if ("0".equals(cfg_area_ip))
			areaid = AreaFuc.getAreaidByIpaddr(getRequest());
		else if (!getSessionFieldValue("ses_area_id").equals(""))
			areaid = getSessionFieldValue("ses_area_id");
		return areaid;
	}

	// 输出查找城市
	public void inputarea() throws IOException {
		// 获取订单ID号
		String outprintString = "";
		// 城市名称
		String cityName = "";
		getRequest().setCharacterEncoding("UTF-8");
		getResponse().setCharacterEncoding("UTF-8");
		PrintWriter out = getResponse().getWriter();
		HashMap areamap = new HashMap();
		if (getRequest().getParameter("areaName") != null && !"".equals(getRequest().getParameter("areaName"))) {
			cityName = URLDecoder.decode(getRequest().getParameter("areaName"), "UTF-8");
		}
		areamap.put("area_name", cityName);
		areaList = this.areaService.getList(areamap);
		if (areaList != null && areaList.size() > 0) {
			HashMap idmap = new HashMap();
			idmap = (HashMap) areaList.get(0);
			if (idmap.get("area_id") != null) {
				String area_id = idmap.get("area_id").toString();
				outprintString = area_id;
			}
		} else {
			outprintString = "0";
		}
		out.write(outprintString);
	}

	// 定位地址需要清空session
	public String dirlist() throws IOException {
		this.getSession().setAttribute("ses_area_id", "");
		return arealist();
	}

	// 清空session中的ses_area_id
	public void initSession() {
		getSession().setAttribute("ses_area_id", "");
	}

	//获取国家和地区
	public void getCountryArea(String areaid) {
		area_name = AreaFuc.getAreaNameByMap(areaid);
		country = AreaFuc.getTopAreaName(areaid);
	}

	// 获取地区分站信息
	public void getorganize(String areaid) {
		HashMap orgmap = new HashMap();
		orgmap.put("area_attr", areaid);
		List organizeList = this.organizeService.getList(orgmap);
		if (organizeList != null && organizeList.size() > 0) {
			HashMap idmap = (HashMap) organizeList.get(0);
			if (idmap.get("org_id") != null) {
				String organizeid = idmap.get("org_id").toString();
				organize = this.organizeService.get(organizeid);
			}
		} else {
			organize = this.organizeService.get("7264142871");
		}
	}

	/**
	 * @return the ClassifiedList
	 */
	public List getClassifiedList() {
		return classifiedList;
	}

	/**
	 * @param ClassifiedList
	 *            the ClassifiedList to set
	 */
	public void setClassifiedList(List classifiedList) {
		this.classifiedList = classifiedList;
	}

	/**
	 * @return the area_id
	 */
	public String getArea_id() {
		return area_id;
	}

	/**
	 * @param area_id
	 *            the area_id to set
	 */
	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}

	/**
	 * @return the searchText
	 */
	public String getSearchText() {
		return searchText;
	}

	/**
	 * @param searchText
	 *            the searchText to set
	 */
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getHidden_area_value() {
		return hidden_area_value;
	}

	public void setHidden_area_value(String hidden_area_value) {
		this.hidden_area_value = hidden_area_value;
	}

	public String getMem_type() {
		return mem_type;
	}

	public void setMem_type(String mem_type) {
		this.mem_type = mem_type;
	}

	public String getInfo_id() {
		return info_id;
	}

	public void setInfo_id(String info_id) {
		this.info_id = info_id;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public List getAreaList() {
		return areaList;
	}

	public void setAreaList(List areaList) {
		this.areaList = areaList;
	}

	public Classified getClassified() {
		return classified;
	}

	public void setClassified(Classified classified) {
		this.classified = classified;
	}

	public List getClassCatList() {
		return classCatList;
	}

	public void setClassCatList(List classCatList) {
		this.classCatList = classCatList;
	}

	public List getTwoclassCatList() {
		return twoclassCatList;
	}

	public void setTwoclassCatList(List twoclassCatList) {
		this.twoclassCatList = twoclassCatList;
	}

	public List getArealevellist() {
		return arealevellist;
	}

	public void setArealevellist(List arealevellist) {
		this.arealevellist = arealevellist;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getCateupname() {
		return cateupname;
	}

	public void setCateupname(String cateupname) {
		this.cateupname = cateupname;
	}

	public String getMember_type() {
		return member_type;
	}

	public void setMember_type(String member_type) {
		this.member_type = member_type;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getStrbulider() {
		return strbulider;
	}

	public void setStrbulider(String strbulider) {
		this.strbulider = strbulider;
	}

	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

	public String getIntrkey() {
		return intrkey;
	}

	public void setIntrkey(String intrkey) {
		this.intrkey = intrkey;
	}

	public String getImg_state() {
		return img_state;
	}

	public void setImg_state(String img_state) {
		this.img_state = img_state;
	}

	public String getCharea_name() {
		return charea_name;
	}

	public void setCharea_name(String charea_name) {
		this.charea_name = charea_name;
	}

	public String getCat_attr() {
		return cat_attr;
	}

	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getStyletype() {
		return styletype;
	}

	public void setStyletype(String styletype) {
		this.styletype = styletype;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List getSearchList() {
		return searchList;
	}

	public void setSearchList(List searchList) {
		this.searchList = searchList;
	}

	public String getAll() {
		return all;
	}

	public void setAll(String all) {
		this.all = all;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getArea_level() {
		return area_level;
	}

	public void setArea_level(String area_level) {
		this.area_level = area_level;
	}

	public String getNav_url() {
		return nav_url;
	}

	public void setNav_url(String nav_url) {
		this.nav_url = nav_url;
	}

	public String getHidden_area_id() {
		return hidden_area_id;
	}

	public void setHidden_area_id(String hidden_area_id) {
		this.hidden_area_id = hidden_area_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCatupid() {
		return catupid;
	}

	public void setCatupid(String catupid) {
		this.catupid = catupid;
	}

	public Organize getOrganize() {
		return organize;
	}

	public void setOrganize(Organize organize) {
		this.organize = organize;
	}

	public static String getCat_level() {
		return cat_level;
	}

	public static void setCat_level(String cat_level) {
		WebclassifiedAction.cat_level = cat_level;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List getAreacountryList() {
		return areacountryList;
	}

	public void setAreacountryList(List areacountryList) {
		this.areacountryList = areacountryList;
	}

	public List getAreacharList() {
		return areacharList;
	}

	public void setAreacharList(List areacharList) {
		this.areacharList = areacharList;
	}

	public List getCountrycharList() {
		return countrycharList;
	}

	public void setCountrycharList(List countrycharList) {
		this.countrycharList = countrycharList;
	}

	public String getEn_name() {
		return en_name;
	}

	public void setEn_name(String en_name) {
		this.en_name = en_name;
	}

	public IClassifiedService getClassifiedService() {
		return classifiedService;
	}

	public void setClassifiedService(IClassifiedService classifiedService) {
		this.classifiedService = classifiedService;
	}

	public List getCate_attrList() {
		return cate_attrList;
	}

	public void setCate_attrList(List cate_attrList) {
		this.cate_attrList = cate_attrList;
	}

}
