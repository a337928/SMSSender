package com.arthur.dao;

import com.arthur.dao.TestNAME;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by wangtao on 17/5/24.
 */
@Mapper
public interface TestMapper {
	@Insert("INSERT INTO TEST(NAME, SEX) VALUES(#{name}, #{sex})")
	int insert(@Param("name") String name, @Param("sex") String sex);

	@Select("SELECT * FROM TEST WHERE NAME = #{name}")
	TestNAME findByName(@Param("name") String name);
}
