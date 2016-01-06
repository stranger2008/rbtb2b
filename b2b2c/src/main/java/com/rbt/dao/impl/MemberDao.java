/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: MemberDao.java 
 */
package com.rbt.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.rbt.dao.IInfoattrDao;
import com.rbt.dao.ILevelinfoDao;
import com.rbt.dao.IMemberDao;
import com.rbt.dao.IMembercreditDao;
import com.rbt.dao.IMemberfundDao;
import com.rbt.dao.IMemberinterDao;
import com.rbt.dao.IMemberuserDao;
import com.rbt.dao.IShopconfigDao;
import com.rbt.model.Gallery;
import com.rbt.model.Infoattr;
import com.rbt.model.Member;

/**
 * @function 功能 会员dao层业务接口实现类
 * @author 创建人 邱景岩
 * @date 创建日期 Wed Jul 13 08:48:07 CST 2011
 */
@Repository
public class MemberDao extends GenericDao<Member,String> implements IMemberDao {
	@Autowired
	private IInfoattrDao infoattrDao;
	public MemberDao() {
		super(Member.class);
	}

	@Autowired
	private IMemberuserDao memberuserDao;
	@Autowired
	private ILevelinfoDao levelinfoDao;
	@Autowired
	private IMemberfundDao memberfundDao;
	@Autowired
	private IMembercreditDao membercreditDao;
	@Autowired
	private IMemberinterDao memberinterDao; 	
	@Autowired
	private IShopconfigDao shopconfigDao;
	
	public String insertMember(Member member){
	  return (String) this.getSqlMapClientTemplate().insert("member.insert",member);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IMemberDao#updateContactInfo(com.rbt.model.Member)
	 */
	public void updateContactInfo(Member member) {
		this.getSqlMapClientTemplate().update("member.updateContactInfo",
				member);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IMemberDao#updateMemberState(com.rbt.model.Member)
	 */
	public void updateMemberState(Map map) {
		this.getSqlMapClientTemplate().update("member.updateMemberState", map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.ISupplyDao#getIndexList(java.util.Map)
	 */
	public List getIndexList(Map map) {
		return this.getSqlMapClientTemplate().queryForList(
				"member.getIndexList", map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IMemberDao#getWebMemberCount(java.util.Map)
	 */
	public int getWebMemberCount(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject(
				"member.getWebMemberCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IMemberDao#getWebMemberList(java.util.Map)
	 */
	public List getWebMemberList(Map map) {
		return this.getSqlMapClientTemplate().queryForList(
				"member.getWebMemberList", map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IMemberDao#getWebMemberHomeList(java.util.Map)
	 */
	public List getWebMemberHomeList(Map map) {
		return this.getSqlMapClientTemplate().queryForList(
				"member.getWebMemberHome", map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IMemberDao#updateIsrecom(java.util.List)
	 */
	public void updateIsrecom(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if (list != null && list.size() > 0) {
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap) iter.next();
						executor.update("member.updateisrecom", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});

	}

	public List getWebMemberModCountList(Map map) {
		return this.getSqlMapClientTemplate().queryForList(
				"member.getWebMemberModCount", map);
	}
	/**
	 * 删除会员相关数据
	 * @param id
	 */
	public void deleteRelate(String id){
		super.delete(id);
		memberuserDao.deleteByCustId(id);
		levelinfoDao.delete(id);
		memberfundDao.delete(id);
		membercreditDao.delete(id);
		memberinterDao.delete(id);
		//删除店铺会员信息
		shopconfigDao.delShopMes(id);
	}
	
	
	public String insertGetPk(Member t, List objList) {
		if(objList != null && objList.size()>0){
			for(int i = 0;i<objList.size();i++){
				this.infoattrDao.insert((Infoattr) objList.get(i));
			}
		}
		return super.insertGetPk(t);
	}

	public void update(Member t, List objList, String id) {
		if(id !=null && "".equals(id)){
			this.infoattrDao.delete(id);
		}
		if(objList != null && objList.size()>0){
			for(int i= 0;i<objList.size();i++){
				this.infoattrDao.insert((Infoattr) objList.get(i));
			}
		}
		super.update(t);
	}

}
