/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IVideoService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Gallery;
import com.rbt.model.Video;

/**
 * @function 功能 记录视频信息Service层业务接口实现类
 * @author  创建人 蔡毅存
 * @date  创建日期 Wed Jul 27 09:07:53 CST 2011
 */

public interface IVideoService extends IGenericService<Video,String>{

	/**
	 * 方法描述：修改信息
	 * @param video
	 */
	public void updateauditVideo(Video video);
	

	/**
	 * 方法描述：批量修改
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateisrecom(List list);
	/**
	 * @MethodDescribe 方法描述    更新浏览次数
	 * @author  创建人  胡惜坤
	 */
	public void updateClickNum(String pk);
	
    /**
	 * @MethodDescribe 方法描述    前台视屏数据绑定
	 * @author  创建人  林俊钦
	 * @date  创建日期  Sep 2, 2011 3:06:21 PM
	 */
	@SuppressWarnings("unchecked")
	public List getWebVideoList(Map map);
    /**
	 * @MethodDescribe 方法描述    前台视屏数据绑定条数
	 * @author  创建人  林俊钦
	 * @date  创建日期  Sep 2, 2011 3:06:48 PM
	 */
	@SuppressWarnings("unchecked")
	public int getWebVideoCount(Map map);
	
	
    public String insertGetPk(Video t, List objList);
    
    public void update(Video t, List objList, String id);
	
}

