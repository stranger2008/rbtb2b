/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: AboutusAction.java 
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.CategoryFuc;
import com.rbt.model.Aboutus;
import com.rbt.service.IAboutusService;
import com.rbt.service.ICommparaService;

/**
 * @function 功能 关于我们action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Mon Jul 11 12:15:32 CST 2011
 */
@Controller
public class AboutusAction extends BaseAction implements Preparable {

	private static final long serialVersionUID = 1L;
	/*
	 * 关于我们对象
	 */
	public Aboutus aboutus;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IAboutusService aboutusService;
	@Autowired
	public ICommparaService commparaService;
	/*
	 * 关于我们信息集合
	 */
	public List aboutusList;
	/*
	 * 参数列表
	 */
	public List commparaList;
	/*
	 * 参数匹配列表
	 */
	public List commparaList_value;
	/*
	 * 搜索字段
	 */
	public String title_s;
	public String ch_id_s;
	public String starttime_s;
	public String endtime_s;
	//商城获取列表
	public String malllist() throws Exception{
		setPlatType();
		return list();
	}
	//商城添加信息
    public String malladd()throws Exception
    {
    	setPlatType();
    	return add();
    }
    //商城查看信息
    public String  mallview()throws Exception
    {
    	setPlatType();
    	return view();
    }
    //商城删除信息
    public String  malldelete() throws Exception
    {
    	setPlatType();
    	return delete();
    }
	/**
	 * 方法描述：返回新增关于我们页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		//获取下拉列表
		getcommparaList(mall_type);
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增关于我们
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {

		if ("0".equals(this.aboutus.getCh_id())) {
			this.addFieldError("aboutus.ch_id", "请选择关于我们类型");
		}
		aboutus.setPlat_type(mall_type);
		aboutus.setUser_id(this.session_user_id);
		//字段验证
		super.commonValidateField(aboutus);
		if (super.ifvalidatepass) {
			return add();
		}
		this.aboutusService.insert(aboutus);
        //获取下拉列表
		getcommparaList(mall_type);
		this.addActionMessage("新增关于我们成功");
		this.aboutus = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改关于我们信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String id = aboutus.getInfo_id();
		aboutus.setPlat_type(mall_type);
		//判断实体ID是否存在
		if (ValidateUtil.isDigital(id)) {
			return list();
		}
		aboutus.setUser_id(this.session_user_id);
		//字段验证
		super.commonValidateField(aboutus);
		if (super.ifvalidatepass) {
			return view();
		}
		this.aboutusService.update(aboutus);
		//获取下拉列表
		getcommparaList(mall_type);
		this.addActionMessage("修改关于我们成功");
		return list();
	}

	/**
	 * 方法描述：删除关于我们信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.aboutus.getInfo_id();
		id = id.replace(" ", "");
		this.aboutusService.delete(id);
		this.addActionMessage("删除关于我们成功");
		return list();
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		//获取下拉列表
		getcommparaList(mall_type);
		//页面搜索提交的参数
		Map pageMap = new HashMap();
		pageMap.put("plat_type", mall_type);
		if (title_s != null && !"".equals(title_s))
			pageMap.put("title", title_s);
		if(ch_id_s!=null && !"".equals(ch_id_s))
			pageMap.put("ch_id", ch_id_s);
		if (starttime_s != null && !starttime_s.equals(""))
			pageMap.put("starttime", starttime_s);
		if (endtime_s != null && !endtime_s.equals(""))
			pageMap.put("endtime", endtime_s);
		//过滤地区
		if(mall_type.equals("b2b"))
		{
			pageMap.put("para_code", "ch_id");
		}
		else if(mall_type.equals("b2c")) {
			pageMap.put("para_code", "help_type");
		}
		pageMap=super.areafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count = this.aboutusService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count, pageMap);
		aboutusList = this.aboutusService.getList(pageMap);
		//aboutusList = CategoryFuc.replaceList(aboutusList, strch_type);
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出关于我们信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		aboutus=this.aboutusService.get(this.aboutus.getInfo_id());
		getcommparaList(mall_type);
		return goUrl(VIEW);
	}
	
	
	//获取参数表字段para_code为ch_id的列表
	public void getcommparaList(String strmall_type){
		Map map = new HashMap();
		if(strmall_type.equals("b2b"))
		{
			map.put("para_code", "ch_id");
		}
		else if(strmall_type.equals("b2c")) {
			map.put("para_code", "help_type");
		}
		commparaList = commparaService.getList(map);
	}

	/**
	 * @return the AboutusList
	 */
	public List getAboutusList() {
		return aboutusList;
	}

	/**
	 * @param aboutusList
	 *  the AboutusList to set
	 */
	public void setAboutusList(List aboutusList) {
		this.aboutusList = aboutusList;
	}

	public List getCommparaList_value() {
		return commparaList_value;
	}

	public void setCommparaList_value(List commparaList_value) {
		this.commparaList_value = commparaList_value;
	}

	public List getCommparaList() {
		return commparaList;
	}

	public void setCommparaList(List commparaList) {
		this.commparaList = commparaList;
	}

	/**
	 * @return the aboutus
	 */
	public Aboutus getAboutus() {
		return aboutus;
	}

	/**
	 * @param Aboutus
	 *            the aboutus to set
	 */
	public void setAboutus(Aboutus aboutus) {
		this.aboutus = aboutus;
	}

	public String getTitle_s() {
		return title_s;
	}

	public void setTitle_s(String title_s) {
		this.title_s = title_s;
	}

	public String getCh_id_s() {
		return ch_id_s;
	}

	public void setCh_id_s(String ch_id_s) {
		this.ch_id_s = ch_id_s;
	}

	/**
	 * 方法描述：在执行这个类中的其他方法前先调用此方法，保证存在实体类
	 * 
	 * @return
	 * @throws Exception
	 */
	public void prepare() throws Exception { super.saveRequestParameter();
		if (aboutus == null) {
			aboutus = new Aboutus();
		}
		String id = this.aboutus.getInfo_id();
		if (!ValidateUtil.isDigital(id)) {
			aboutus = this.aboutusService.get(id);
		}
	}
}
