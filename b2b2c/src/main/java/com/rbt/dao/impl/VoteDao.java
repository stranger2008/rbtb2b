package com.rbt.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.rbt.dao.IVoteDao;
import com.rbt.model.Vote;


@Repository
public class VoteDao extends GenericDao<Vote,String> implements IVoteDao {
	
	public VoteDao() {
		super(Vote.class);
	}
	
	/**
	 * 更新投票总数
	 * @param pk
	 */
	public void updateVoteCountNum(String pk)
	{
		this.getSqlMapClientTemplate().update("vote.update_votecount", pk);
	}
	/**
	 * 方法描述：批量修改单选多选
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public void updateis_multiState(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("vote.updateis_multiState", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}


}
