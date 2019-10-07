package com.study.jsp.dto;

import java.sql.Timestamp;

public class BReplyDto {
	
	int replynum;
	int replybid;
	String replyid;
	String replybcontent;
	Timestamp replyDate;	
	
	public BReplyDto() {

	}
	
	public BReplyDto(int replynum, int replybid, String replyid, String replybcontent, Timestamp replyDate) {
		super();
		this.replynum = replynum;
		this.replybid = replybid;
		this.replyid = replyid;
		this.replybcontent = replybcontent;
		this.replyDate = replyDate;
	}



	public Timestamp getReplyDate() {
		return replyDate;
	}



	public void setReplyDate(Timestamp replyDate) {
		this.replyDate = replyDate;
	}



	public String getReplybcontent() {
		return replybcontent;
	}
	public void setReplybcontent(String replybcontent) {
		this.replybcontent = replybcontent;
	}
	public int getReplynum() {
		return replynum;
	}
	public void setReplynum(int replynum) {
		this.replynum = replynum;
	}
	public int getReplybid() {
		return replybid;
	}
	public void setReplybid(int replybid) {
		this.replybid = replybid;
	}
	public String getReplyid() {
		return replyid;
	}
	public void setReplyid(String replyid) {
		this.replyid = replyid;
	}

		

}
