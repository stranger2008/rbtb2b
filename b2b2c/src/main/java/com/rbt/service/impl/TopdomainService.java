/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: TopdomainService.java 
 */
package com.rbt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.ITopdomainDao;
import com.rbt.model.Topdomain;
import com.rbt.service.ITopdomainService;

/**
 * @function 功能 记录会员申请的顶级域名申请信息Service层业务接口实现
 * @author 创建人 蔡毅存
 * @date 创建日期 Mon Aug 01 14:24:27 CST 2011
 */
@Service
public class TopdomainService extends GenericService<Topdomain,String> implements ITopdomainService {

	ITopdomainDao topdomainDao;

	@Autowired
	public TopdomainService(ITopdomainDao topdomainDao) {
		super(topdomainDao);
		this.topdomainDao = topdomainDao;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.ITopdomainService#updateenabled(com.rbt.model.Topdomain)
	 */
	public void updateenabled(Topdomain topdomain) {
		this.topdomainDao.updateenabled(topdomain);
	}
	/*
	 * (non-Javadoc)
	 * @see com.rbt.service.ITopdomainService#domainRetUrl(java.lang.String)
	 */
	public String domainRetUrl(String subdomain)
	{
		String gourl="";
		Map pageMap = new HashMap();
		pageMap.put("domain_url", subdomain);
		pageMap.put("enabled", "1");
		List topdomainList = topdomainDao.getList(pageMap);
		if (topdomainList != null && topdomainList.size() > 0) {
			HashMap map_value = new HashMap();
			map_value = (HashMap) topdomainList.get(0);
			if (map_value != null && map_value.get("go_url") != null) {
				gourl = map_value.get("go_url").toString();
			}
		}
		return gourl;
	}

}

