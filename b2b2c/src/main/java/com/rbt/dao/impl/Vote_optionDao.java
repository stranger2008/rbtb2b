package com.rbt.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.rbt.dao.IVote_optionDao;
import com.rbt.model.Vote_option;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

/**
 * @function 功能  记录投票选项信息dao层业务接口实现类
 * @author 创建人 蔡毅存
 * @date 创建日期 Wed Jul 27 09:07:53 CST 2011
 */

@Repository
public class Vote_optionDao extends GenericDao<Vote_option,String> implements IVote_optionDao {
	
	public Vote_optionDao() {
		super(Vote_option.class);
	}

	/**
	 * 方法描述：批量修改投票次数
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public void update_optioncount(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("vote_option.updateoptioncount", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}

}
