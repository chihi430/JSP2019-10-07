package com.study.jsp.dto;

public class BboardRankDto {

	String bname;
	int boardcount;
	int ranknum;
	
	public BboardRankDto() {

	}
	
	public BboardRankDto(String bname, int boardcount, int ranknum) {
		super();
		this.bname = bname;
		this.boardcount = boardcount;
		this.ranknum = ranknum;
	}
	
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public int getBoardcount() {
		return boardcount;
	}
	public void setBoardcount(int boardcount) {
		this.boardcount = boardcount;
	}
	public int getRanknum() {
		return ranknum;
	}
	public void setRanknum(int ranknum) {
		this.ranknum = ranknum;
	}
	
	
}
