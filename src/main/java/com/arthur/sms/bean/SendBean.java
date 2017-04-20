package com.arthur.sms.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 短信发送信息对象
 * Created by wangtao on 17/4/19.
 */
public class SendBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String oa;
	private String da;
	private String telecom;
	private String siid;
	private Date   sendDate;
	private String streamingno; //流水号
	private String transactionid;
	private String Content;
	private Integer total;

	public String getOa() {
		return oa;
	}

	public void setOa(String oa) {
		this.oa = oa;
	}

	public String getDa() {
		return da;
	}

	public void setDa(String da) {
		this.da = da;
	}

	public String getTelecom() {
		return telecom;
	}

	public void setTelecom(String telecom) {
		this.telecom = telecom;
	}

	public String getSiid() {
		return siid;
	}

	public void setSiid(String siid) {
		this.siid = siid;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getStreamingno() {
		return streamingno;
	}

	public void setStreamingno(String streamingno) {
		this.streamingno = streamingno;
	}

	public String getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
}
