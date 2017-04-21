package com.arthur.sms.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by wangtao on 17/4/21.
 */
public interface IBaseDao {

	Long update(String sql,Object... objects);

	Long update(String sql);

	Long queryForLong(String sql);

	Long quergForLong(String sql,Object... objects);

	List queryForlist(String sql);

	List quergForList(String sql,Object... objects);

	List listByPage(String statementName, Map<String, Object> map,int skipResults, int pageSize);
}
