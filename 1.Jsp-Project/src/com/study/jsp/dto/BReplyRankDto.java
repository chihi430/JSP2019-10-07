package com.study.jsp.dto;

public class BReplyRankDto {
	String replyid;
	int replycount;
	int ranknum;
	
	
	public BReplyRankDto(String replyid, int replycount, int ranknum) {
		super();
		this.replyid = replyid;
		this.replycount = replycount;
		this.ranknum = ranknum;
	}
	
	public BReplyRankDto() {

	}
	
	public String getReplyid() {
		return replyid;
	}
	public void setReplyid(String replyid) {
		this.replyid = replyid;
	}
	public int getReplycount() {
		return replycount;
	}
	public void setReplycount(int replycount) {
		this.replycount = replycount;
	}
	public int getRanknum() {
		return ranknum;
	}
	public void setRanknum(int ranknum) {
		this.ranknum = ranknum;
	}
	
	

}
