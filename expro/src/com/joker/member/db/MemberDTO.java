package com.joker.member.db;

import java.util.Date;

public class MemberDTO {
	private String id;
	private String passwd;
	private String name;
	private String email;
	private String nickname;
	private Date reg_date;

	public String getId() {
		return id;
	}

	public String getPasswd() {
		return passwd;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getNickname() {
		return nickname;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
}
