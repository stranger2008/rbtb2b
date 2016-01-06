/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: MemberchannelService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.IMemberchannelDao;
import com.rbt.model.Memberchannel;
import com.rbt.model.Memberconfig;
import com.rbt.service.IMemberchannelService;

/**
 * @function 功能 记录会员企业站栏目信息Service层业务接口实现
 * @author 创建人 蔡毅存
 * @date 创建日期 Fri Aug 26 16:21:41 CST 2011
 */
@Service
public class MemberchannelService extends GenericService<Memberchannel,String> implements IMemberchannelService {


	IMemberchannelDao memberchannelDao;

	@Autowired
	public MemberchannelService(IMemberchannelDao memberchannelDao) {
		super(memberchannelDao);
		this.memberchannelDao = memberchannelDao;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IMemberchannelService#insertintoMemberchannel(com.rbt.model.Memberchannel)
	 */
	public void insertintoMemberchannel(Map configchannel) {
		this.memberchannelDao.insertintoMemberchannel(configchannel);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IMemberchannelService#insertintoMemberchannel(com.rbt.model.Memberchannel)
	 */
	public void insertMemberconfig(Memberconfig memberconfig) {
		this.memberchannelDao.insertMemberconfig(memberconfig);
	}

	
	
	/**
	 * 批量更新是否显示
	 */
	public void updateisdis(List list) {
		this.memberchannelDao.updateState(list);

	}
	
	/**
	 * 批量更新sort_no排序字段
	 */
	public void updatechannel(List list) {
		this.memberchannelDao.updatechannel(list);
	}

}

