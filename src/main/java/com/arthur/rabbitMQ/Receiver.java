package com.arthur.rabbitMQ;

/**
 * Created by wangtao on 17/5/23.
 */
import java.nio.charset.Charset;
import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

	private CountDownLatch latch = new CountDownLatch(1);

	public void receiveMessage(byte[] message) {
		System.out.println("Received <" + new String(message,Charset.forName("UTF-8")) + ">");
		latch.countDown();
	}

	public CountDownLatch getLatch() {
		return latch;
	}

}
