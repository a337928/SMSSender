package com.arthur.sender;

/**
 * 构建一个短信发送器的抽象接口
 * 所有的短信发送实体都需要继承这个接口
 * Created by wangtao on 17/4/16.
 */
public interface ISender {

	//定义发送短信的接口
	void sendMessage(String messageContent);

	//定义短信发送后收到的响应接口
	String getResponse();

	//定义短信发送收到回执的接口
	void getReceipt();

}
