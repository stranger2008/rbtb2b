/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: ChannelService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IAboutusDao;
import com.rbt.dao.IChannelDao;
import com.rbt.model.Aboutus;
import com.rbt.model.Channel;
import com.rbt.service.IChannelService;

/**
 * @function 功能 记录网站栏目信息Service层业务接口实现
 * @author 创建人 胡惜坤
 * @date 创建日期 Mon Aug 15 10:57:10 CST 2011
 */
@Service
public class ChannelService extends GenericService<Channel,String>  implements IChannelService {

	/*
	 * 记录网站栏目信息Dao层接口
	 */
	
	 IChannelDao channelDao;

	@Autowired
	public ChannelService(IChannelDao channelDao) {
		super(channelDao);
		this.channelDao = channelDao;
	}
	/*
	 * (non-Javadoc)
	 * @see com.rbt.service.IChannelService#updateChannelSortNo(java.util.List)
	 */
	public void updateChannelSortNo(List list) {
		this.channelDao.updateChannelSortNo(list);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.IChannelService#getChannelByType(java.lang.String)
	 */
	public Channel getChannelByType(String type) {
		return this.channelDao.getChannelByType(type);
	}

}

