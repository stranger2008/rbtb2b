/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.common.util
 * FileName: PropertiesUtil.java
 */
package com.rbt.common.util;

public class PageUtil {

	private int curPage = 0; // 当前页
	private int pageSize_s = 0; // 每页多少行
	private int endSize; // 用于not in(select top endSize id)不在多少行内
	private int totalRow; // 共多少行
	private int totalPage; // 共多少页

	public int getStart() {
		if (curPage > 1)
			return (curPage - 1) * pageSize_s;
		else
			return 0;
	}

	public int getEnd() {
		return pageSize_s;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {

		int temp = pageSize_s * (curPage - 1);
		this.setEndSize(temp);
		this.curPage = curPage;
	}

	public int getEndSize() {
		return endSize;
	}

	public void setEndSize(int endSize) {
		this.endSize = endSize;
	}

	public int getPageSize() {
		return pageSize_s;
	}

	public void setPageSize(int pageSize_s) {
		this.pageSize_s = pageSize_s;
	}

	public int getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(int totalRow) {

		totalPage = totalRow / pageSize_s;
		if (totalRow % pageSize_s > 0)
			totalPage = totalPage + 1;

		this.totalRow = totalRow;
	}

	public int getTotalPage() {

		return this.totalPage;
	}

	public String getToolsMenu() {
		StringBuffer str = new StringBuffer("");
		int next, prev;
		prev = curPage - 1;
		next = curPage + 1;

		if (curPage > 1) {
			str.append("<input type=\"button\" class=\"sy\" onclick=\"this.form.pages_s.value=1;this.form.submit();\" value=\"首页\" />&nbsp;");
		} else {
			str.append("<input type=\"button\" class=\"sy\" style=\"color:#cecece;\" value=\"首页\" />&nbsp;");
		}
		if (curPage > 1) {
			str.append("<input type=\"button\" class=\"up\" onclick=\"this.form.pages_s.value=" + prev + ";this.form.submit();\" value=\"上页\" />&nbsp;");
		} else {
			str.append("<input type=\"button\" class=\"upno\" style=\"color:#cecece;\" value=\"上页\" />&nbsp;");
		}
		if (curPage < totalPage) {
			str.append("<input type=\"button\" class=\"next\" onclick=\"this.form.pages_s.value=" + next + ";this.form.submit();\" value=\"下页\" />&nbsp;");
		} else {
			str.append("<input type=\"button\" class=\"nextno\" style=\"color:#cecece;\" value=\"下页\" />&nbsp;");
		}
		if (totalPage > 1 && curPage != totalPage) {
			str.append("<input type=\"button\" class=\"sy\" onclick=\"this.form.pages_s.value=" + totalPage + ";this.form.submit();\" value=\"末页\" />&nbsp;&nbsp;");
		} else {
			str.append("<input type=\"button\" class=\"sy\" style='color:#cecece;' value=\"末页\" />&nbsp;&nbsp;");
		}
		str.append(" 共" + totalRow + "条记录");
		str.append("  每页<SELECT size=1 name=pagesize onchange='this.form.pages_s.value=1;this.form.pageSize_s.value=this.value;this.form.submit();'>");

		if (pageSize_s == 3) {
			str.append("<OPTION value=3 selected>3</OPTION>");
		} else {
			str.append("<OPTION value=3>3</OPTION>");
		}

		if (pageSize_s == 10) {
			str.append("<OPTION value=10 selected>10</OPTION>");
		} else {
			str.append("<OPTION value=10>10</OPTION>");
		}
		if (pageSize_s == 20) {
			str.append("<OPTION value=20 selected>20</OPTION>");
		} else {
			str.append("<OPTION value=20>20</OPTION>");
		}
		if (pageSize_s == 50) {
			str.append("<OPTION value=50 selected>50</OPTION>");
		} else {
			str.append("<OPTION value=50>50</OPTION>");
		}
		if (pageSize_s == 100) {
			str.append("<OPTION value=100 selected>100</OPTION>");
		} else {
			str.append("<OPTION value=100>100</OPTION>");
		}
		str.append("</SELECT>");
		str.append("条 分" + totalPage + "页显示 转到");
		str.append("<SELECT size=1 name=Pagelist onchange='this.form.pages_s.value=this.value;this.form.submit();'>");
		for (int i = 1; i < totalPage + 1; i++) {
			if (i == curPage) {
				str.append("<OPTION value=" + i + " selected>" + i + "</OPTION>");
			} else {
				str.append("<OPTION value=" + i + ">" + i + "</OPTION>");
			}
		}
		str.append("</SELECT>页");
		str.append("<INPUT type=\"hidden\"  value=\"" + curPage + "\" name=\"pages_s\" /> ");
		str.append("<INPUT type=\"hidden\"  value=\"" + pageSize_s + "\" name=\"pageSize_s\" /> ");
		return str.toString();
	}

}
