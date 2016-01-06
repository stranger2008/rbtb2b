/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: GallerypicAction.java 
 */
package com.rbt.action;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.createHtml.DoHtml;
import com.rbt.model.Gallery;
import com.rbt.model.Gallerypic;
import com.rbt.service.IGalleryService;
import com.rbt.service.IGallerypicService;

/**
 * @function 功能 记录图库图片信息action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Tue Jul 26 10:31:40 CST 2011
 */
@Controller
public class GallerypicAction extends BaseAction implements Preparable {

	private static final long serialVersionUID = 1L;
	/*
	 * 记录图库图片信息对象
	 */
	public Gallerypic gallerypic;
	
	public Gallery gallery;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IGallerypicService gallerypicService;
	@Autowired
	public IGalleryService galleryService;
	/*
	 * 记录图片信息信息集合
	 */
	public List galleryList;
	/*
	 * 记录图库图片信息信息集合
	 */
	public List gallerypicList;
	/*
	 * 图库id号
	 */
	public String gal_id="";
	/*
	 * 搜索字段
	 */
	public String gal_id_s;
	public String is_page;
	private String modType="gallery";
	//图片描述
	public String gal_desc;

	/**
	 * 方法描述：返回新增记录图库图片信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		Map map = new HashMap();
		String cust_id = this.session_cust_id;
		if (cust_id != null && !"0".equals(cust_id)) {
			map.put("cust_id", cust_id);
		}
		galleryList = this.galleryService.getGalleryIDList(map);
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录图库图片信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		
		//字段验证
		super.commonValidateField(gallerypic);
		if(super.ifvalidatepass){
			return add();
		}
		
		String[] arr = gallerypic.getImg_path().split(",");
		String cust_id;
		for (int i = 0; i < arr.length; i++) {
			// 插入多张图片
			cust_id = this.session_cust_id;
			if (cust_id != null && !"0".equals(cust_id)) {
				gallerypic.setCust_id(cust_id);
			} else {
				gallerypic.setCust_id("0");
			}
			gallerypic.setGal_id(this.gallerypic.getGal_id());
			gallerypic.setImg_path(arr[i]);
			gallerypic.setPic_desc(this.gallerypic.getPic_desc());
			String user_id = this.session_user_id;
			if (user_id != null && !"0".equals(user_id)) {
				gallerypic.setUser_id("0");
			} else {
				gallerypic.setUser_id(user_id);
			}
			this.gallerypicService.insert(gallerypic);
		}
		// 对插入成功的信息生成HTML静态页面
		DoHtml doHtml = new DoHtml();
		doHtml.doArticleHtml(modType, gallerypic.getGal_id());		
		this.addActionMessage("新增图库图片成功");
		this.gallerypic.setImg_path("");
		return add();
	}

	/**
	 * 方法描述：修改记录图库图片信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		this.gallerypicService.update(gallerypic);
		this.addActionMessage("修改图库图片成功");
		return list();
	}

	/**
	 * 方法描述：删除记录图库图片信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.gallerypic.getPic_id();
		this.gallerypicService.delete(id);
		// 对插入成功的信息生成HTML静态页面
		DoHtml doHtml = new DoHtml();
		doHtml.doArticleHtml(modType, gallerypic.getGal_id());	
		this.addActionMessage("删除图库图片成功");
		return list();
	}
	
	/**
	 * 方法描述：批量保存图片描述
	 * 
	 * @author 陈晓艺
	 * @throws Exception
	 * @date : Jul 3, 2012 9:38:39 AM
	 */
	public String updateDesc() throws Exception{
		String pic_id = this.gallerypic.getPic_id();
		String pic_descs =gal_desc;
		if(pic_id!=null&&!"".equals(pic_id))
		{
			pic_id = pic_id.replace(" ", "");
			String picStr[] = pic_id.split(",");			
			pic_descs = pic_descs.replace(" ", "");
			String descStr[] = pic_descs.split(",");			
			if (picStr.length > 0) {
				for (int i = 0; i < picStr.length; i++) {
					   Gallerypic gmodel=new Gallerypic();
					   gmodel=gallerypicService.get(picStr[i]);
					   if(!"".equals(descStr[i])){
						   gmodel.setPic_desc(descStr[i]);
					   }
					   this.gallerypicService.update(gmodel);			
					}
			}		
		}
		this.addActionMessage("保存图片描述成功");
		return list();		
	}
	

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map pageMap = new HashMap();
		String id = this.gallerypic.getGal_id();
		pageMap.put("gal_id", id);
		int count = this.gallerypicService.getCount(pageMap);
		// 分页插件	
		pageMap = super.pageTool(count, pageMap);
		getgallerypic(pageMap);
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出记录图库图片信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(gallerypic.getCust_id()!=null){
					if(accessControl(gallerypic.getCust_id())){
						return list();
					}
				}
		Map pageMap = new HashMap();
		String id = this.gallerypic.getGal_id();
		pageMap.put("gal_id", id);
		int count = this.gallerypicService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		getgallerypic(pageMap);
		return goUrl(VIEW);
	}
	
	public void getgallerypic(Map map){
		gallerypicList = this.gallerypicService.getList(map);
	}

	/**
	 * @return the GallerypicList
	 */
	public List getGallerypicList() {
		return gallerypicList;
	}

	/**
	 * @param gallerypicList
	 *            the GallerypicList to set
	 */
	public void setGallerypicList(List gallerypicList) {
		this.gallerypicList = gallerypicList;
	}

	public List getGalleryList() {
		return galleryList;
	}

	public void setGalleryList(List galleryList) {
		this.galleryList = galleryList;
	}

	public String getGal_id_s() {
		return gal_id_s;
	}

	public void setGal_id_s(String gal_id_s) {
		this.gal_id_s = gal_id_s;
	}

	/**
	 * @return the gallerypic
	 */
	public Gallerypic getGallerypic() {
		return gallerypic;
	}

	/**
	 * @param Gallerypic
	 *            the gallerypic to set
	 */
	public void setGallerypic(Gallerypic gallerypic) {
		this.gallerypic = gallerypic;
	}
	
	

	public void prepare() throws Exception { super.saveRequestParameter();
		if (gallerypic == null) {
			gallerypic = new Gallerypic();
		}
		String id = gallerypic.getPic_id();
		if (!ValidateUtil.isDigital(id)) {
			gallerypic = this.gallerypicService.get(id);
		}
	}
}
