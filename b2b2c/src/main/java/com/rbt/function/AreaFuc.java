package com.rbt.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.rbt.common.util.IpSeekerInit;
import com.rbt.model.Area;
import com.rbt.model.Sendmode;
import com.rbt.service.IAreaService;
import com.rbt.service.ISendmodeService;

/**
 * @function 功能 Tomcat服务器启动时加载arealist
 * @author 创建人 林俊钦
 * @date 创建日期 Jul 28, 2011 11:11:59 AM
 */
public class AreaFuc extends CreateSpringContext {

	
	// 根据地区名称找出地区的ID串
	public static String getarea_id(String area_name) {
		// 地区id
		String id = "";
		HashMap map = new HashMap();
		map.put("area_name", area_name);
		List areaList = getAreaList(map);
		if (areaList != null && areaList.size() > 0) {
			HashMap idmap = new HashMap();
			idmap = (HashMap) areaList.get(0);
			if (idmap.get("area_id") != null) {
				id = idmap.get("area_id").toString();
			}
		}
		return id;
	}
	
	// 根据地区拼音找出地区的ID串
	public static String getparea_id(String en_name) {
		// 地区id
		String id = "";
		HashMap map = new HashMap();
		map.put("en_name", en_name);
		List areaList = getAreaList(map);
		if (areaList != null && areaList.size() > 0) {
			HashMap idmap = new HashMap();
			idmap = (HashMap) areaList.get(0);
			if (idmap.get("area_id") != null) {
				id = idmap.get("area_id").toString();
			}
		}
		return id;
	}

	// 通过地区英文名称找出地区

	public static HashMap getAreaNameMap(String en_name) {
		HashMap areaMap = new HashMap();
		if (en_name != null && !en_name.equals("")) {
			Map map = new HashMap();
			map.put("en_name", en_name);
			List areaList = getAreaObj().getList(map);
			if (areaList != null && areaList.size() > 0) {
				areaMap = (HashMap) areaList.get(0);
			}
		}
		return areaMap;
	}

	// 从这个方法getAllArea()里的list转化为HashMap，此处的list启用ibatis的缓存处理
	// area_id和area_name成键值对存放在HashMap中
	// 作者：李良林
	// 日期：2011-08-31
	public static HashMap getAreaMap() {
		List areaList = getAllArea();
		HashMap areaMap = new HashMap();
		if (areaList != null && areaList.size() > 0) {
			HashMap aMap = new HashMap();
			for (int i = 0; i < areaList.size(); i++) {
				String area_id = "", area_name = "";
				aMap = (HashMap) areaList.get(i);
				if (aMap.get("area_id") != null) {
					area_id = aMap.get("area_id").toString();
				}
				if (aMap.get("area_name") != null) {
					area_name = aMap.get("area_name").toString();
				}
				areaMap.put(area_id, area_name);
			}
		}
		return areaMap;
	}

	// 从Spring容器中获取地区业务Bean
	public static IAreaService getAreaObj() {
		return (IAreaService) getContext().getBean("areaService");
	}

	// 获取所有地区信息
	// 作者：李良林
	// 日期：2011-08-31
	public static List getAllArea() {
		IAreaService areaService = getAreaObj();
		return areaService.getAll();
	}

	// 获取地区信息
	// 日期：2011-08-31
	public static List getAreaList(HashMap map) {
		IAreaService areaService = getAreaObj();
		return areaService.getList(map);
	}

	// 在全局areaMap中根据地区标识找出地区名称
	public static String getAreaNameByMap(String area_id_string) {
		StringBuffer sb = new StringBuffer();
		HashMap areaMap = getAreaMap();
		String area_name = "";
		// 定义String分隔串
		if (area_id_string != null && !"".equals(area_id_string)) {
			area_id_string = area_id_string.replace(" ","");
			String[] area_id = area_id_string.split(",");
			for (int j = 0; j < area_id.length; j++) {
				if (area_id[j] != null && !area_id[j].equals("")) {
					if (areaMap != null && areaMap.get(area_id[j]) != null) {
						area_name = areaMap.get(area_id[j]).toString();
						sb.append(area_name);
						if (j != area_id.length - 1) {
							sb.append(",");
						}
					}
				}
			}
		}
		return sb.toString();
	}
	
	// 在全局替换配送公司
	public static String getSmodeByMap(String smode_attr) {
		StringBuffer sb = new StringBuffer();
		ISendmodeService  sendmodeService  =	(ISendmodeService) getContext().getBean("sendmodeService");
		Sendmode sendmode = new Sendmode();
		// 定义String分隔串
		if (smode_attr != null && !"".equals(smode_attr)) {
			smode_attr = smode_attr.replace(" ","");
			String[] smode_id = smode_attr.split(",");
			for (int j = 0; j < smode_id.length; j++) {
				if (smode_id[j] != null && !smode_id[j].equals("")) {
					sendmode  = sendmodeService.get(smode_id[j]);
						sb.append(sendmode.getSmode_name());
						if (j != smode_id.length - 1) {
							sb.append(",");
						}
				}
			}
		}
		return sb.toString();
	}
	
	
	
	// 在全局areaMap中根据地区标识找出地区名称
	public static String getAreaNameByListMap(String area_id_string) {
		StringBuffer sb = new StringBuffer();
		HashMap areaMap = getAreaMap();
		String area_name = "";
		// 定义String分隔串
		if (area_id_string != null && !"".equals(area_id_string)) {
			area_id_string = area_id_string.replace(" ","");
			String[] area_id = area_id_string.split("\\|");
			for (int j = 0; j < area_id.length; j++) {
				if (area_id[j] != null && !area_id[j].equals("")) {
					if (areaMap != null && areaMap.get(area_id[j]) != null) {
						area_name = areaMap.get(area_id[j]).toString();
						sb.append(area_name);
						if (j != area_id.length - 1) {
							sb.append(",");
						}
					}
				}
			}
		}
		return sb.toString();
	}

	// 通过地区找出英文名称
	public static String getAreaEnglishName(String area_id) {
		String en_nameString = "";
		if (area_id != null && !area_id.equals("")) {
			en_nameString = getAreaObj().get(area_id).getEn_name();
		}
		return en_nameString;
	}

	// 在全局areaMap中找出第一个ID的名称
	public static String getFiresAreaName(String area_id_string) {
		String first_area_name = "";
		HashMap areaMap = getAreaMap();
		// 定义String分隔串
		String[] area_id = area_id_string.split(",");
		if (area_id.length > 0 && area_id[0] != null) {
			if (areaMap != null && areaMap.get(area_id[0]) != null
					&& !areaMap.get(area_id[0]).equals("")) {
				first_area_name = areaMap.get(area_id[0]).toString();
			}
		}
		return first_area_name;
	}
	
	//任意一个地区id号找到顶级地区名称
	public static String getTopAreaName(String area_id){
		String area_name="";
		Area area=getAreaObj().get(area_id);
		if(area!=null && area.getArea_level()!=null){
			area_name=area.getArea_name();
			if(!"1".equals(area.getArea_level().toString())){
				return getTopAreaName(area.getUp_area_id());
			}
		}
		return area_name;
	}
	
	//任意一个地区id好找到顶级地区id号
	public static String getTopAreaId(String area_id){
		String areaid="";
		Area area=getAreaObj().get(area_id);
		if(area!=null && area.getArea_level()!=null){
			areaid=area.getArea_id();
			if(!"1".equals(area.getArea_level().toString())){
				return getTopAreaId(area.getUp_area_id());
			}
		}
		return areaid;
	}
	/**
	 * @author : 林俊钦
	 * @date : May 23, 2012 1:23:32 PM
	 * @Method Description : 根据IP找出应的数据
	 */
	public static List getAreaListByIpaddr(HttpServletRequest request){
		IAreaService areaService = (IAreaService) getAreaObj();		
		// 获取IP地址
		String ipaddr = IpSeekerInit.getIpAddr(request);
		if (ipaddr.equals("127.0.0.1")) {
			ipaddr = "210.076.200.225";
		}
		String addrName = IpSeekerInit.getAreaName(ipaddr);
		HashMap areamap = new HashMap();
		areamap.put("address", addrName);
		List areaList = areaService.getList(areamap);
		return areaList;
	}

	// 根据IP地址获取本系统地区id
	public static String getAreaidByIpaddr(HttpServletRequest request) {
		List list = getAreaListByIpaddr(request);
		String idvlaue = "";
		// 获取与IP匹配的地区列表 筛选级别最低的地区ID
		if (list != null && list.size() > 0) {
			HashMap valuemap = (HashMap) list.get(0);
			if (valuemap.get("area_id") != null){
				idvlaue = valuemap.get("area_id").toString();
			}
		}
		return idvlaue;
	}
	
	/**
	 * @author : 林俊钦
	 * @date : May 23, 2012 1:32:33 PM
	 * @Method Description :根据ip地址获取本系统地区名称
	 */
	public static String getAreanameByIpaddr(HttpServletRequest request){
		List list = getAreaListByIpaddr(request);
		String area_name = "";
		// 获取与IP匹配的地区列表 筛选级别最低的地区ID
		if (list != null && list.size() > 0) {
			HashMap valuemap = (HashMap) list.get(0);
			if (valuemap.get("area_name") != null){
				area_name = valuemap.get("area_name").toString();
			}
		}
		return area_name;
	}
	
}