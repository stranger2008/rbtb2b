/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: BuyeraddrAction.java 
 */
package com.rbt.action;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.Preparable;
import com.rbt.action.BaseAction;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.CategoryFuc;
import com.rbt.model.Buyeraddr;
import com.rbt.service.IBuyeraddrService;
import com.rbt.service.IMemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 买家收货地址表action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Fri Oct 21 10:50:11 CST 2011
 */
@Controller
public class BuyeraddrAction extends BaseAction implements Preparable {

	private static final long serialVersionUID = 1L;
	/*
	 * 买家收货地址表对象
	 */
	public Buyeraddr buyeraddr;
	/*
	 * 业务层接口  
	 */
	@Autowired
	public IBuyeraddrService buyeraddrService;
	@Autowired
	public IMemberService memberService;
	/*
	 * 买家收货地址表信息集合
	 */
	public List buyeraddrList;

	
	
	
	/**
	 * @author : 林俊钦
	 * @date : Jul 13, 2012 1:42:18 PM
	 * @Method Description : 会员发布条数的判断
	 */
	public boolean controlInfoNum(){
		//查出已发布的信息个数
		Map tmap = new HashMap();
		tmap.put("cust_id", this.session_cust_id);
		int count = this.buyeraddrService.getBuyeraddrCount(tmap);
		if(controlMsgNum(count,"buyeraddr")){
			return true;
		}else{
			return false;
		}
	}
	
	
	/**
	 * 方法描述：返回新增买家收货地址表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		//发布信息数量控制
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			controlInfoNum();
		}
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增买家收货地址表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		// 用于所属地区的回选开始
		loadArea();
		HashMap map = new HashMap();
		map.put("cust_id",this.session_cust_id);
		int count = this.buyeraddrService.getBuyeraddrCount(map);
		if (count >= 5) {
			this.addActionMessage("收货地址不能超过5条记录");
			return add();
		}
		//验证所属地区是否有选择
		if (ValidateUtil.isRequired(area_attr) || area_attr.indexOf("0") > -1) {
			this.addFieldError("area_attr", "请选择地区");
		}

		if ("".equals(this.buyeraddr.getCell_phone()) && "".equals(this.buyeraddr.getPhone())) {
			this.addFieldError("buyeraddr.cell_phone", "固定电话和手机号码至少一个不为空");
		} 
		// 将处理后的所属属性串注入到buy对象中
		this.buyeraddr.setArea_attr(area_attr);
		// 获取客户标识
		this.buyeraddr.setCust_id(this.session_cust_id);
		// 获取session中的值
		this.buyeraddr.setUser_id(this.session_user_id);
		//发布信息数量控制
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			if(controlInfoNum()){
				return add();
			}
		}
		//字段验证
		super.commonValidateField(buyeraddr);
		if(super.ifvalidatepass){
			return add();
		}
		this.buyeraddrService.insertBuyeraddr(buyeraddr);
		this.addActionMessage("新增收货地址成功");
		this.buyeraddr = null;
		is_crotorl=true;
		return INPUT;
	}

	/**
	 * 方法描述：修改买家收货地址表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		// 用于所属地区的回选开始
		loadArea();
		String id = buyeraddr.getAddr_id();
		//判断实体ID是否存在,若不存在该实体，返回到列表页，不进行任何操作
		if (ValidateUtil.isDigital(id)) {
			return list();
		}
		
		//验证所属地区是否有选择
		if (ValidateUtil.isRequired(area_attr) || area_attr.indexOf("0") > -1) {
			this.addFieldError("area_attr", "请选择地区");
		}
		
		if ("".equals(this.buyeraddr.getCell_phone()) && "".equals(this.buyeraddr.getPhone())) {
			this.addFieldError("buyeraddr.cell_phone", "固定电话和手机号码至少一个不为空");
		} 
		
		// 将处理后的所属属性串注入到buy对象中
		this.buyeraddr.setArea_attr(area_attr);
		// 获取客户标识
		this.buyeraddr.setCust_id(this.session_cust_id);
		// 获取session中的值
		this.buyeraddr.setUser_id(this.session_user_id);
		//字段验证
		super.commonValidateField(buyeraddr);
		if(super.ifvalidatepass){
			return view();
		}
		this.buyeraddrService.updateBuyeraddr(buyeraddr);
		this.addActionMessage("修改收货地址成功");
		return list();
	}

	/**
	 * 方法描述：删除买家收货地址表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.buyeraddr.getAddr_id();
		id = id.replace(" ", "");
		this.buyeraddrService.deleteBuyeraddr(id);
		this.addActionMessage("删除收货地址成功");
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
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		buyeraddrList = this.buyeraddrService.getBuyeraddrList(pageMap);
		buyeraddrList = CategoryFuc.replaceList(buyeraddrList, "");
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出买家收货地址表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(buyeraddr.getCust_id()!=null){
					if(accessControl(buyeraddr.getCust_id())){
						return list();
					}
				}
		if (buyeraddr.getArea_attr() != null) {
			// 找出地区字段的存入隐藏值中
			backArea(buyeraddr.getArea_attr()); 
		}		
		return goUrl(VIEW);
	}

	/**
	 * @return the BuyeraddrList
	 */
	public List getBuyeraddrList() {
		return buyeraddrList;
	}

	/**
	 * @param buyeraddrList
	 *            the BuyeraddrList to set
	 */
	public void setBuyeraddrList(List buyeraddrList) {
		this.buyeraddrList = buyeraddrList;
	}

	public String getArea_attr() {
		return area_attr;
	}

	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}

	public String getHidden_area_value() {
		return hidden_area_value;
	}

	public void setHidden_area_value(String hidden_area_value) {
		this.hidden_area_value = hidden_area_value;
	}

	/**
	 * 方法描述：在执行这个类中的其他方法前先调用此方法，保证存在实体类
	 * 
	 * @return
	 * @throws Exception
	 */
	public void prepare() throws Exception { super.saveRequestParameter();
		if (buyeraddr == null) {
			buyeraddr = new Buyeraddr();
		}
		String id = this.buyeraddr.getAddr_id();
		if (!ValidateUtil.isDigital(id)) {
			buyeraddr = this.buyeraddrService.getBuyeraddrByPk(id);
		}
	}

	/**
	 * @return the buyeraddr
	 */
	public Buyeraddr getBuyeraddr() {
		return buyeraddr;
	}

	/**
	 * @param Buyeraddr
	 *            the buyeraddr to set
	 */
	public void setBuyeraddr(Buyeraddr buyeraddr) {
		this.buyeraddr = buyeraddr;
	}
}
