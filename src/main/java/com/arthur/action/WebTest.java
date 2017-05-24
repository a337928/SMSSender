package com.arthur.action;

import com.arthur.dao.TestMapper;
import com.arthur.dao.TestNAME;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangtao on 17/5/24.
 */
@RestController
@RequestMapping("/webTest")
public class WebTest {

	@Autowired
	private TestMapper testMapper;



	@RequestMapping(value = "/templateTest" , method = RequestMethod.POST)
	public String templateTest(String name,String sex){
		try {
			findByName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	@Rollback
	public void findByName() throws Exception {
		testMapper.insert("AAA", "男");
		TestNAME u = testMapper.findByName("AAA");

	}

}
