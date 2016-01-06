package com.rbt.function;

import java.util.HashMap;

import com.rbt.model.Goodsorder;
import com.rbt.service.IGoodsorderService;

public class GoodsOrderFuc  extends CreateSpringContext{


	/**
	 * @author : 胡惜坤
	 * @date : Apr 5, 2012 7:30:18 PM
	 * @Method Description : 通过订单号获取订单总额
	 */
	public static String getOrderAllMomey(String order_id)
	{
		String order_allmomey="";
		if(order_id!=null&&!order_id.equals(""))
		{
			IGoodsorderService goodsorderService = (IGoodsorderService) getContext().getBean("goodsorderService");
			Goodsorder gordermode=new Goodsorder();
			gordermode=goodsorderService.get(order_id);
			if(gordermode!=null&&gordermode.getGoods_amount()!=null)
			{
				order_allmomey=gordermode.getGoods_amount();
			}
		}
	    return order_allmomey;
	}
	/**
	 * @author : 胡惜坤
	 * @date : Apr 5, 2012 7:30:18 PM
	 * @Method Description : 通过支付接口返回的值，更新订单状态
	 */
	public static void changeOrderState(String order_id)
	{
		if(order_id!=null&&!order_id.equals(""))
		{
			IGoodsorderService goodsorderService = (IGoodsorderService) getContext().getBean("goodsorderService");
			HashMap orderMap=new HashMap ();
			orderMap.put("order_id", order_id);
			//订单状态：0:未确认 1：已确认 2:取消 3：无效 4：退货
			orderMap.put("order_state", "1");
			//支付状态：0:未付款 1：已付款 2：付款中
			orderMap.put("pay_state", "1");
			//发货状态：0:未发货 1：已发货 2：发货中 3：配货中4：收货确认
			orderMap.put("send_state", "0");
			goodsorderService.update(orderMap);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
