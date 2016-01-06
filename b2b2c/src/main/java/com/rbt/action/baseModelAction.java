package com.rbt.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.CategoryAttrFuc;
import com.rbt.function.CategoryFuc;
import com.rbt.model.Infoattr;
import com.rbt.model.Sysmodule;
import com.rbt.service.IInfoattrService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMembercatService;
import com.rbt.service.ISysmoduleService;


public class baseModelAction extends BaseAction {
	
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	public IInfoattrService infoattrService;
	@Autowired
	public IMembercatService membercatService;
	@Autowired
	public ISysmoduleService sysmoduleService;
	@Autowired
	public IMemberService memberService;
	public Sysmodule sysmodule;
		
	public CategoryFuc categoryFuc;	

	public String cate_attr_value;
	public String cate_attr_id;
	public String cate_attr_is_must;
	public String cate_attr_type;
	public String cate_attr_name;
	public String cate_attr_sort;
	public String cate_value_trade_id;
	
	public String info_state_s;
	public String ischange;
	public String cate_name;	
	public String is_select;
	public String update_value;// 用来保存更新供应的值
	public String supply_infoattr_id;
	public String info_infoattr_id;
	
	public List attrList;
	public List objList;
	public List unitList;
	public List selfCatList;
	public List attrValueList;
	
	//是否为重新选择的分类
	public boolean isNewCat=false;
	
	
	
	/**
	 * @author : 林俊钦
	 * @date : Mar 26, 2012 2:35:36 PM
	 * @Method Description : 验证属性值是否必填字段
	 */
	public void checkMust(String infoattr_id){
		objList=new ArrayList();
		if (cate_attr_value != null && cate_attr_id != null && cate_attr_is_must != null
				&& cate_attr_name != null && cate_attr_type != null) {
            
			String attrvalue[] = cate_attr_value.split("##########");
			String attrid[] = cate_attr_id.split(",");
			String musts[] = cate_attr_is_must.split(",");
			String names[] = cate_attr_name.split(",");
			String types[] = cate_attr_type.split(",");
			String sorts[] =cate_attr_sort.split(",");
			String trade_id[] = cate_value_trade_id.split(",");
 			
			for (int i = 0; i < attrid.length; i++) {
				String id="",val = "",must="",name="",type="",sort="",trade_ids="";
				if(attrid[i]!=null && !attrid[i].equals("")){
					id = attrid[i].trim();
				}
				if(attrvalue[i]!=null && !attrvalue[i].equals("")){
					val = attrvalue[i].trim();
				}
				if(musts[i]!=null && !musts[i].equals("")){
					must = musts[i].trim();
				}
				if(names[i]!=null && !names[i].equals("")){
					name = names[i].trim();
				}
				if(types[i]!=null && !types[i].equals("")){
					type = types[i].trim();
				}
				if(sorts[i]!=null && !sorts[i].equals("")){
					sort = sorts[i].trim();
				}
				if(trade_id!=null && (trade_id.length > i) && trade_id[i]!=null && !trade_id[i].equals("")){
					trade_ids = trade_id[i].trim();
				}

				// 字段域为text或textarea时验证控制
				if ((type.equals("0") || type.equals("1") || type.equals("4") || type.equals("5") || type.equals("6") || type.equals("7"))
						&& must.equals("1") && val.trim().equals("")) {
						this.addFieldError("attr_" + id, "请输入" + name);
				}
				
				// 字段域为radio或checkbox时验证控制
				if ((type.equals("2") || type.equals("3")) && must.equals("1") && val.trim().equals("")) {
						this.addFieldError("attr_" + id, "请选择" + name);
				}
				
				Infoattr infoattr = new Infoattr();
				infoattr.setInfoattr_id(infoattr_id);
				infoattr.setAttr_id(id);
				infoattr.setAttr_name(name);
				infoattr.setSort_no(sort);
				infoattr.setAttr_value(val);
				if ((type.equals("2") || type.equals("3"))){
					infoattr.setValue_id(val);
				}else{
					infoattr.setValue_id(trade_ids);
				}
				objList.add(infoattr);
			}
		}
	}
	
	
	/**
	 * @author : 林俊钦
	 * @date : Apr 18, 2012 4:59:51 PM
	 * @Method Description :支持分类属性列表
	 */
	public void checkIsAttr(){
		attrList=new ArrayList();
		cat_attr = cat_attr.replace(" ", "");			
		//获取分类属性和继承属性
		attrList = CategoryAttrFuc.getCatAttrListByCatIdStr(cat_attr,ischange,cate_attr_value);		
		//获取属性值列表
		attrValueList = CategoryAttrFuc.getAttrValueListByCatIdStr(cat_attr);
		// 设置分类串cat_attr处理后的值,将得到的供应分类代码转换成名称
		cate_name = categoryFuc.getCateNameByMap(cat_attr).replace(" ", "");	
	}
	
	

	/**
	 * @author : 林俊钦
	 * @date : Aug 23, 2012 1:22:46 PM
	 * @Method Description : 根据对象获取信息属性ID与信息所属分类找出属性列表
	 */
	public	void getIsCatAttr(String infoattr_id,String attr_cat){
		if (isNewCat) {
			checkIsAttr();			
		} else {
			backAttr(infoattr_id,attr_cat);				
		}
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Mar 26, 2012 2:36:05 PM
	 * @Method Description :更新页面回选值返回列表
	 */
	public void backAttr(String infoattr_id,String attr_cat){
		Map infoattrMap = new HashMap();
		if(infoattr_id!=null&&!infoattr_id.equals("")){
			infoattrMap.put("infoattr_id", infoattr_id);
			attrList = this.infoattrService.getList(infoattrMap);	
		}		
		//获得属性列表合并到最终的List中 返回产品属性列表
	    if(ischange!=null&&ischange.equals("1")&&cate_attr_value!=null){
		   String attrvalue[] = cate_attr_value.split("##########");
		   for(int j=0;j<attrList.size();j++)
		   {
			   Map supMap1=new HashMap();
			   supMap1=(Map)attrList.get(j);
			   //获取重新提交的值替换原来的值
			   supMap1.put("dft_value", attrvalue[j]);			   
			   attrList.set(j, supMap1);
		   }
	    }
	    //获取属性值列表
		attrValueList = CategoryAttrFuc.getAttrValueListByCatIdStr(attr_cat);
	}
	
	
	/**
	 * @author : 林俊钦
	 * @date : Apr 18, 2012 6:31:03 PM
	 * @Method Description :所属模块公共将分类ID转换成分类名称
	 */
	public void catIdTocatName(String dbCat_attr){
		String cate_attr = "";			
		// 判断用户是否重选所属分类/
		if (ValidateUtil.isRequired(cat_attr)) {
			// 根据ID获取所属分类串
			cate_attr = dbCat_attr;	
			cat_attr = cate_attr;			
		} else {
			cate_attr = cat_attr.replace(" ", "");	
			isNewCat=true;
		}
		// 将得到的供应分类代码转换成名称
		cate_name = categoryFuc.getCateNameByMap(cate_attr).replace(" ", "");
	}

	
	
	public String getCate_attr_value() {
		return cate_attr_value;
	}

	public void setCate_attr_value(String cate_attr_value) {
		this.cate_attr_value = cate_attr_value;
	}

	public String getCate_attr_id() {
		return cate_attr_id;
	}

	public void setCate_attr_id(String cate_attr_id) {
		this.cate_attr_id = cate_attr_id;
	}

	public String getCate_attr_is_must() {
		return cate_attr_is_must;
	}

	public void setCate_attr_is_must(String cate_attr_is_must) {
		this.cate_attr_is_must = cate_attr_is_must;
	}

	public String getCate_attr_type() {
		return cate_attr_type;
	}

	public void setCate_attr_type(String cate_attr_type) {
		this.cate_attr_type = cate_attr_type;
	}

	public String getCate_attr_name() {
		return cate_attr_name;
	}

	public void setCate_attr_name(String cate_attr_name) {
		this.cate_attr_name = cate_attr_name;
	}

	public String getCate_attr_sort() {
		return cate_attr_sort;
	}

	public void setCate_attr_sort(String cate_attr_sort) {
		this.cate_attr_sort = cate_attr_sort;
	}
	
	
}


