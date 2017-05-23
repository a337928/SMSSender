package com.arthur.rabbitMQ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangtao on 17/5/23.
 */
@RestController
@RequestMapping("/message")
public class GetMessage {

	@Autowired
	Runner runner;

	@RequestMapping("/sender")
	public void sender(){

		try {
			runner.run("王滔 Love 水琴");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
