package com.study.jsp.dto;

public class BLikeDto {
	
	int likerId; // 고유 시퀀스 번호
	int likebId; // 보드 아이디
	String likeid; // 회원 아이디
	int good; // 좋아요 판별
	
	
	public BLikeDto(int likerId, int likebId, String likeid, int good) {
		super();
		
		this.likerId = likerId;
		this.likebId = likebId;
		this.likeid = likeid;
		this.good = good;
		
	}


	public BLikeDto() {

	}


	public int getLikebId() {
		return likebId;
	}


	public void setLikebId(int likebId) {
		this.likebId = likebId;
	}


	public String getLikeid() {
		return likeid;
	}


	public void setLikeid(String likeid) {
		this.likeid = likeid;
	}


	public int getGood() {
		return good;
	}


	public void setGood(int good) {
		this.good = good;
	}


	public int getLikerId() {
		return likerId;
	}


	public void setLikerId(int likerId) {
		this.likerId = likerId;
	}
	
	
	
}
