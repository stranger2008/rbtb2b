/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Link.java 
 */
package com.rbt.model;
import java.io.Serializable;
/**
 * @function 功能  友情链接pojo类
 * @author  创建人 蔡毅存
 * @date  创建日期  Jun 28, 2011
*/
public class Link  implements Serializable {

	static final long serialVersionUID = 2083827134077851396L;
	
	/*
	 * 友情链接唯一标识
	 */
	String link_id;
	/*
	 * 友情链接名称
	 */
	String link_name;
	/*
	 * 友情链接分组
	 */
	String link_group;
	/*
	 * 友情链接分组
	 */
	String area_attr;
	/*
	 * 排序
	 */
	String sort_no;
	
	/*
	 * 菜单url地址
	 */
	String url;
	
	/*
	 * 是否显示
	 */
    String is_display;
    
    /*
     * 友情链接图片
     */
	String img_path;
   
	String plat_type;

	public String getPlat_type() {
		return plat_type;
	}

	public void setPlat_type(String plat_type) {
		this.plat_type = plat_type;
	}

	/**
	 * @return the link_id
	 */
	public String getLink_id() {
		return link_id;
	}

	/**
	 * @param link_id the link_id to set
	 */
	public void setLink_id(String link_id) {
		this.link_id = link_id;
	}

	/**
	 * @return the link_name
	 */
	public String getLink_name() {
		return link_name;
	}

	/**
	 * @param link_name the link_name to set
	 */
	public void setLink_name(String link_name) {
		this.link_name = link_name;
	}


	/**
	 * @return the link_group
	 */
	public String getLink_group(){
		return link_group;
	}
	/**
	 * @param link_group the link_group to set
	 */
    public void setLink_group(String link_group){
    	this.link_group=link_group;
    }
	/**
	 * @return the sort_no
	 */
	public String getSort_no() {
		return sort_no;
	}

	/**
	 * @param sort_no the sort_no to set
	 */
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}


	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	/**
	 * @return the is_display
	 */
	public String getIs_display() {
		return is_display;
	}

	/**
	 * @param is_display the is_display to set
	 */
	public void setIs_display(String is_display) {
		this.is_display = is_display;
	}

	/**
	 * @return the area_attr
	 */
	public String getArea_attr() {
		return area_attr;
	}
	/**
	 * @param area_attr the area_attr to set
	 */
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}

	public String getImg_path() {
		return img_path;
	}

	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Link [link_id=");
		builder.append(this.link_id);
		builder.append(", link_name=");
		builder.append(this.link_name);
		builder.append(", link_group=");
		builder.append(this.link_group);
		builder.append(", plat_type=");
		builder.append(this.plat_type);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", url=");
		builder.append(this.url);
		builder.append(", is_display=");
		builder.append(this.is_display);
		builder.append(", img_path=");
		builder.append(this.img_path);
		builder.append(", area_attr=");
		builder.append(this.area_attr);
		builder.append("]");
		return builder.toString();
	}
	
}
