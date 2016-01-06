package com.rbt.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.model.Goodsorder;
import com.rbt.service.IGoodsService;
import com.rbt.service.IGoodsorderService;
import com.rbt.service.IOrderdetailService;
import com.rbt.service.IRolerightService;

public class GoodsFuc  extends CreateSpringContext{

	/**
	 * @author : 林俊钦
	 * @date : Apr 5, 2012 7:30:18 PM
	 * @Method Description : 通过订单号获取对应的订单商品
	 */
	@SuppressWarnings("unchecked")
	public static String getGoodsListByOrderid(String orderid) {
		IOrderdetailService orderdetailService = (IOrderdetailService) getContext().getBean("orderdetailService");
		Map map = new HashMap();
		map.put("order_id", orderid);
		List list = orderdetailService.getList(map);
		StringBuffer sb = new StringBuffer();
		HashMap sbMap = new HashMap();
		if (list.size() < 0) {
			return null;
		} else {
			for (int i = 0; i < list.size(); i++) {
				sbMap = (HashMap) list.get(i);
				String goods_id = "",goods_name="";
				if (sbMap.get("goods_id") != null) {
					goods_id = sbMap.get("goods_id").toString();
				}
				if (sbMap.get("goods_name") != null) {
					goods_name = sbMap.get("goods_name").toString();
				}
				sb.append("<a href=''><img src='"+goods_id+"' width='35' height='35'/>"+goods_name+"</a>");
				sb.append("&nbsp;");
				if ((i + 1) % 5 == 0) {
					sb.append("<br/>");
				}
			}
			return sb.toString();
		}

	}
	
}
