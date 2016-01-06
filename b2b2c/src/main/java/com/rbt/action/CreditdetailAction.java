/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: CreditdetailAction.java 
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Creditdetail;
import com.rbt.service.ICreditdetailService;
import com.rbt.service.ITradecommentService;

/**
 * @function 功能 会员信用指数明细action类
 * @author 创建人 林俊钦
 * @date 创建日期 Thu Dec 08 20:44:27 CST 2011
 */
@Controller
public class CreditdetailAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 会员信用指数明细对象
	 */
	private Creditdetail creditdetail;
	/*
	 * 业务层接口
	 */
	@Autowired
	private ICreditdetailService creditdetailService;
	@Autowired
	public ITradecommentService tradecommentService;
	/*
	 * 会员信用指数明细信息集合
	 */
	public List creditdetailList;
	public List creditList;
	public List certList;
	public List vipList;
	/*
	 * 信用指数明细表赋值
	 */
	public int total_num;
	public int name_num=0;
	public int cert_num=0;
	public int eva_num=0;
	public int vip_num=0;
	public int eva_good_num=0;
	public int eva_mid_num=0;
	public int eva_bad_num=0;
	public int eva_good_val=0;
	public int eva_mid_val=0;
	public int eva_bad_val=0;
	public String cre_id;
	
	/**
	 * 方法描述：返回新增会员信用指数明细页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增会员信用指数明细
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		this.creditdetailService.insert(creditdetail);
		this.addActionMessage("新增信用指数明细成功");
		this.creditdetail = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改会员信用指数明细信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		this.creditdetailService.update(creditdetail);
		this.addActionMessage("修改信用指数明细成功");
		return list();
	}
	/**
	 * 方法描述：删除会员信用指数明细信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.creditdetail.getTrade_id();
		id = id.replace(" ", "");
		this.creditdetailService.delete(id);
		this.addActionMessage("删除信用指数明细成功");
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map pageMap = new HashMap();
		if(cre_id!=null&&!cre_id.equals("")){
			pageMap.put("cust_id", cre_id);
		}else{
			if(this.session_cust_type.equals(Constants.MEMBER_TYPE)){
				pageMap.put("cust_id", this.session_cust_id);
			}
		}
		//根据页面提交的条件找出信息总数
		int count=this.creditdetailService.getCount(pageMap);		
		//分页插件
		pageMap = super.pageTool(count,pageMap);		
		creditdetailList = this.creditdetailService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出会员信用指数明细信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(creditdetail.getCust_id()!=null){
			if(accessControl(creditdetail.getCust_id())){
				return list();
			}
		}
		// 从Session中获取会员标识
		String cust_id = "";
		if(cre_id!=null&&!cre_id.equals("")){
			cust_id=cre_id;		
		}else{
			cust_id = this.session_cust_id;
		}
		//找出该会员的明细表
		Map detailMap=new HashMap();
		detailMap.put("cust_id", cust_id);
		List list=this.creditdetailService.getList(detailMap);
		Map listMap=new HashMap();
		for(int i=0;i<list.size();i++){
			listMap=(HashMap)list.get(i);
			String r_type="",r_num="";
			if(listMap.get("r_type")!=null){
				r_type=listMap.get("r_type").toString();
			}
			if(listMap.get("r_num")!=null){
				r_num=listMap.get("r_num").toString();
			}
			int num=Integer.parseInt(r_num);		
			//a:表示企业实名认证 b:表示资质证书审核 c:表示评价成功 d:VIP年限
			if(!r_type.equals("c")){
				total_num+=num;
			}	
			if(r_type.equals("a")){
				name_num+=num;		
			}else if(r_type.equals("b")){				
				cert_num+=num;
			}else if(r_type.equals("d")){				
				vip_num+=num;
			}
		}
		
		//找出交易评价表中好评，中评，差评个数
		Map comMap=new HashMap();
		comMap.put("get_cust_id",cust_id);
		List commList=this.tradecommentService.getList(comMap);
		String s_com_type="";
		for(int i=0;i<commList.size();i++){
			Map comlistMap=new HashMap();
			comlistMap=(HashMap)commList.get(i);
			if(comlistMap.get("com_type")!=null){
				s_com_type=comlistMap.get("com_type").toString();
			}
			int com_type=Integer.parseInt(s_com_type);
			eva_num+=com_type;			
			if(com_type>0){
				eva_good_num+=1;//好评个数
				eva_good_val+=com_type;//好评指数值
			}else if(com_type==0){
				eva_mid_num+=1;//中评个数
				eva_mid_val+=com_type;//中评指数值
			}else{
				eva_bad_num+=1;//差评个数
				eva_bad_val+=com_type;//差评指数值
			}	
		}
		total_num+=eva_num;//总的指数值	
		
	    //荣誉证书列表
		Map certMap=new HashMap();
		certMap.put("cust_id", cust_id);
		certMap.put("r_type","b");
		certList=this.creditdetailService.getList(certMap);
	    //VIP年限列表
		Map vipMap=new HashMap();
		vipMap.put("cust_id", cust_id);
		vipMap.put("r_type","d");
		vipList=this.creditdetailService.getList(vipMap);
		return goUrl(VIEW);
	}
	/**
	 * @return the CreditdetailList
	 */
	public List getCreditdetailList() {
		return creditdetailList;
	}
	/**
	 * @param creditdetailList
	 *  the CreditdetailList to set
	 */
	public void setCreditdetailList(List creditdetailList) {
		this.creditdetailList = creditdetailList;
	}
	
	public void prepare() throws Exception { super.saveRequestParameter();
		if(creditdetail == null){
			creditdetail = new Creditdetail();
		}
		String id = this.creditdetail.getTrade_id();
		if(!ValidateUtil.isDigital(id)){
			creditdetail = this.creditdetailService.get(id);
		}
	}
	/**
	 * @return the creditdetail
	 */
	public Creditdetail getCreditdetail() {
		return creditdetail;
	}
	/**
	 * @param Creditdetail
	 *            the creditdetail to set
	 */
	public void setCreditdetail(Creditdetail creditdetail) {
		this.creditdetail = creditdetail;
	}
}

