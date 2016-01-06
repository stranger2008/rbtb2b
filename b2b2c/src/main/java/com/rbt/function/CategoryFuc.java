package com.rbt.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Category;

import com.rbt.service.ICategoryService;

/**
 * @function 功能 Tomcat服务器启动时加载catelist
 * @author 创建人 林俊钦
 * @date 创建日期 Aug 10, 2011 2:05:57 PM
 */
public class CategoryFuc extends CreateSpringContext {

	// 通过上级分类标识和分类类型找出分类信息

	@SuppressWarnings("unchecked")
	public static List getCategoryList(Map map) {
		return getCatObj().getList(map);
	}

	// 从这个方法getAllCategory()里的list转化为HashMap，此处的list启用ibatis的缓存处理
	// cat_id和cat_name成键值对存放在HashMap中
	// 作者：李良林
	// 修改日期：2011-08-31
	@SuppressWarnings("unchecked")
	public static HashMap getCategoryMap() {
		List catList = getAllCategory();
		HashMap catMap = new HashMap();
		if (catList != null && catList.size() > 0) {
			HashMap aMap = new HashMap();
			for (int i = 0; i < catList.size(); i++) {
				String cat_id = "", cat_name = "";
				aMap = (HashMap) catList.get(i);
				if (aMap.get("cat_id") != null) {
					cat_id = aMap.get("cat_id").toString();
				}
				if (aMap.get("cat_name") != null) {
					cat_name = aMap.get("cat_name").toString();
				}
				catMap.put(cat_id, cat_name);
			}
		}
		return catMap;
	}

	public static ICategoryService getCatObj() {
		return (ICategoryService) getContext().getBean("categoryService");
	}

	// 获取所有分类信息
	// 作者：李良林
	// 修改日期：2011-08-31
	@SuppressWarnings("unchecked")
	public static List getAllCategory() {
		ICategoryService categoryService = getCatObj();
		return categoryService.getAll();
	}

	// 在全局categoryMap中根据所属分类ID找出对应的所属分类名称
	@SuppressWarnings("unchecked")
	public static String getCateNameByMap(String cat_id_string) {
		StringBuffer sb = new StringBuffer();
		HashMap categoryMap = getCategoryMap();
		String cat_name = "";
		String column="0";
		// 定义String分隔串
		if(cat_id_string!=null&&!cat_id_string.equals("")){
			cat_id_string = cat_id_string.replace(" ","");
			/*****会员所属分类换行*******/
			cat_id_string=cat_id_string.replace("|", "|,");
			/************/
			String[] cat_id = cat_id_string.split(",");
			for (int j = 0; j < cat_id.length; j++) {
				if (!cat_id[j].equals("")) {
					/******会员所属分类换行******/
					if(cat_id[j].indexOf("|")!=-1){
						cat_id[j] = cat_id[j].replace("|", "");
					     column="1";
					}
					/************/
					if (categoryMap != null && categoryMap.get(cat_id[j]) != null) {
						cat_name = categoryMap.get(cat_id[j]).toString();
						sb.append(cat_name);
						if("1".equals(column)){
							sb.append("</br>");
						}else{
							if (j != cat_id.length - 1) {
								sb.append(",");
							}
						}
					}
					column="0";
				}
			}
		}
		return sb.toString();
	}

	// 在全局areaMap中找出第一个ID的名称
	public static String getFiresCateName(String cate_id_string) {
		String first_cate_name = " ";
		HashMap categoryMap = getCategoryMap();
		// 定义String分隔串
		if(cate_id_string!=null&&cate_id_string.contains("/")){
			cate_id_string=cate_id_string.replace("/", ",");
		}
		String[] cate_id = cate_id_string.split(",");
		String first_cat_id="";
		if (cate_id.length > 1 && cate_id[0] != null) {
			first_cat_id=cate_id[0].toString();
		}else{
			first_cat_id=cate_id_string;
		}
		if (categoryMap != null && categoryMap.get(first_cat_id) != null) {
			first_cate_name = categoryMap.get(first_cat_id).toString();
		}
		return first_cate_name;
	}

	// 在全局cateMap中找出最后一个ID的名称
	public static String getLastCateName(String cate_id_string) {
		String Last_cate_name = "";
		HashMap categoryMap = getCategoryMap();
		// 定义String分隔串
		String[] area_id = cate_id_string.split(",");
		int len = 0;
		if (area_id != null) {
			len = area_id.length;
		}
		if (categoryMap != null && len != 0) {
			if (categoryMap.get(area_id[len - 1]) != null) {
				Last_cate_name = categoryMap.get(area_id[len - 1]).toString();
			}
		}
		return Last_cate_name;
	}

	// 在全局cateMap中找出对应的ID的名称
	@SuppressWarnings("unchecked")
	public static String getCateName(String cate_id) {
		String cart_name = "";
		HashMap categoryMap = getCategoryMap();
		if (categoryMap != null) {
			if (categoryMap.get(cate_id) != null) {
				cart_name = categoryMap.get(cate_id).toString();
			}
		}
		return cart_name;
	}
	
	// 在全局cateMap中找出对应的英文名称的名称
	@SuppressWarnings("unchecked")
	public static String getenName(String cate_id) {
		String en_name = "";
		if(getCatObj().get(cate_id)!=null){
			en_name=getCatObj().get(cate_id).getEn_name();
		}
		return en_name;
	}

	// 地区和分类的ID替换成名称,第一个参数要替换的列表，第二个参数属于模块类型
	@SuppressWarnings("unchecked")
	public static List replaceList(List replist, String type) {
		Map repListMap = new HashMap();
		String area_attr = "", cat_type = "", mod_value = "";
		if (replist != null && replist.size() > 0) {
			for (int i = 0; i < replist.size(); i++) {
				repListMap = (HashMap) replist.get(i);
				if (repListMap.get("area_attr") != null
						&& !repListMap.get("area_attr").equals("")) {
					area_attr = repListMap.get("area_attr").toString();
					// 将ID全部用名称代替
					area_attr = AreaFuc.getAreaNameByMap(area_attr);
					repListMap.put("area_attr", area_attr);
				}
				if (repListMap.get("c_area_attr") != null
						&& !repListMap.get("c_area_attr").equals("")) {
					area_attr = repListMap.get("c_area_attr").toString();
					// 将ID全部用名称代替
					area_attr = AreaFuc.getAreaNameByListMap(area_attr);
					repListMap.put("c_area_attr", area_attr);
				}	
				if (repListMap.get("cat_attr") != null
						&& !repListMap.get("cat_attr").equals("")) {
					repListMap.put("cat_attr_id", repListMap.get("cat_attr").toString());
				}
				if (repListMap.get("cat_attr") != null
						&& !repListMap.get("cat_attr").equals("")) {
					cat_type = repListMap.get("cat_attr").toString();
					// 将ID全部用分类代替
					cat_type = CategoryFuc.getCateNameByMap(cat_type);
					repListMap.put("cat_attr", cat_type);
				}
				
				if (repListMap.get("start_area") != null
						&& !repListMap.get("start_area").equals("")) {
					cat_type = repListMap.get("start_area").toString();
					// 将ID全部用分类代替
					cat_type = AreaFuc.getAreaNameByMap(cat_type);
					repListMap.put("start_area", cat_type);
				}
				if (repListMap.get("smode_attr") != null
						&& !repListMap.get("smode_attr").equals("")) {
					cat_type = repListMap.get("smode_attr").toString();
					// 将ID全部用分类代替
					cat_type = AreaFuc.getSmodeByMap(cat_type);
					repListMap.put("smode_attr", cat_type);
				}
				if (repListMap.get(type) != null
						&& !repListMap.get(type).equals("")) {
					mod_value = repListMap.get(type).toString();
					// 将模块类型的ID转换成对应的名称
					mod_value = CommparaFuc.get_commparakey_By_value(mod_value,
							type);
					repListMap.put("model_value", mod_value);
				}
			}
		}
		return replist;
	}

}