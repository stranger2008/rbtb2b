/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: ShiptemplateService.java 
 */
package com.rbt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.dao.IAreasetDao;
import com.rbt.dao.IShiptemplateDao;
import com.rbt.model.Areaset;
import com.rbt.model.Shiptemplate;
import com.rbt.service.IShiptemplateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录运费模板信息Service层业务接口实现
 * @author 创建人 林俊钦
 * @date 创建日期 Thu May 24 15:00:05 CST 2012
 */
@Service
public class ShiptemplateService extends GenericService<Shiptemplate,String> implements IShiptemplateService {
	
	IShiptemplateDao shiptemplateDao;
	@Autowired
	private IAreasetDao areasetDao;

	@Autowired
	public ShiptemplateService(IShiptemplateDao shiptemplateDao) {
		super(shiptemplateDao);
		this.shiptemplateDao = shiptemplateDao;
	}

	public void insertShipMode(Shiptemplate t, List objList) {
		String s_id=this.shiptemplateDao.insertGetPk(t);
		insertareaset(s_id,objList);
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Jun 6, 2012 1:47:23 PM
	 * @Method Description :插入区域设置表数据
	 */
	private void insertareaset(String s_id,List objList){
		if(objList.size()>0){
			for(int i=0;i<objList.size();i++){
				Map listMap=new HashMap();
				listMap=(HashMap)objList.get(i);
				Areaset as=new Areaset();
				String d_ship="",f_weight="",f_price="",c_weight="",c_price="",c_mid="",c_area="";
				if(listMap.get("d_ship")!=null){
					d_ship=listMap.get("d_ship").toString();
				}
				if(listMap.get("f_weight")!=null){
					f_weight=listMap.get("f_weight").toString();
				}
				if(listMap.get("f_price")!=null){
					f_price=listMap.get("f_price").toString();
				}
				if(listMap.get("c_weight")!=null){
					c_weight=listMap.get("c_weight").toString();
				}
				if(listMap.get("c_price")!=null){
					c_price=listMap.get("c_price").toString();
				}
				if(listMap.get("c_mid")!=null){
					c_mid=listMap.get("c_mid").toString();
				}
				if(listMap.get("c_area")!=null){
					c_area=listMap.get("c_area").toString();
				}
				as.setShip_id(s_id);				
				as.setDefault_ship(d_ship);
				as.setFirst_weight(f_weight);
				as.setFirst_price(f_price);
				as.setCont_weight(c_weight);
				as.setCont_price(c_price);
				as.setSmode_id(c_mid);
				as.setEnd_area(c_area);	
				this.areasetDao.insert(as);
			}				
		}		
	}
	
	public void updateShipMode(Shiptemplate t,List objList){		
		this.shiptemplateDao.update(t);
		String s_id=t.getShip_id();
		if(s_id!=null){
			this.areasetDao.deleteByShopid(s_id);
		}
		insertareaset(s_id,objList);
	}
	
}

