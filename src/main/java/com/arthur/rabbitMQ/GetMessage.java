package com.arthur.rabbitMQ;

import com.arthur.sms.bean.SendBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by wangtao on 17/5/23.
 */
@RestController
@RequestMapping("/message")
public class GetMessage {

	private static final Logger log = Logger.getLogger(GetMessage.class);
	@Autowired
	Runner runner;

	@RequestMapping("/sender")
	public void sender(HttpServletRequest request){

		try {

			//String jsonStr = this.getJsonMsg(request);
			//runner.run(jsonStr);
			sendThread sendThread1 = new sendThread("send1");
			sendThread sendThread2 = new sendThread("send2");
			sendThread sendThread3 = new sendThread("send3");
			sendThread sendThread4 = new sendThread("send4");
			sendThread sendThread5 = new sendThread("send5");
			sendThread1.start();
			sendThread2.start();
			sendThread3.start();
			sendThread4.start();
			sendThread5.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class sendThread extends Thread {
		private  String threadName ;
		sendThread(String name){
			super(name);
			this.threadName = name;
		}
		@Override
		public void run() {

			for (int i = 0;i < 10000;i ++){
				try {
					runner.run(this.threadName + "测试" + i);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}


	private String getJsonMsg(HttpServletRequest request) throws IOException {
		long start = System.currentTimeMillis();
		BufferedReader br = request.getReader();
		String inputLine;
		String str = "";
		try {
			while ((inputLine = br.readLine()) != null) {
				str += inputLine;
			}
		} catch (Exception e) {
			log.error(">>getJson err!str=" + str + "\r\n" + e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					log.error("io close error!", e);
				}
			}
		}
		log.info((System.currentTimeMillis() - start) + "ms");
		log.info(str);

		return str;
	}
}
