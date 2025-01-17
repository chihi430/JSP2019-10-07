package com.study.jsp.dto;

import java.sql.Timestamp;

public class MemberDto {

	private String id;
	private String pw;
	private String name;
	private String eMail;
	private Timestamp rDate;
	private String address;
	private String mgrade;

	public MemberDto() {}

	public MemberDto(String id, String pw, String name, String eMail, Timestamp rDate, String address, String mgrade) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.eMail = eMail;
		this.rDate = rDate;
		this.address = address;
		this.mgrade = mgrade;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public Timestamp getrDate() {
		return rDate;
	}

	public void setrDate(Timestamp rDate) {
		this.rDate = rDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMgrade() {
		return mgrade;
	}

	public void setMgrade(String mgrade) {
		this.mgrade = mgrade;
	}



	

}
