package com.study.jsp.dto;

import java.sql.Timestamp;

public class BDto {
	int bId;
	String bName;
	String bPw;
	String bContent;
	String bTitle;
	Timestamp bDate;
	int bHit;
	int bGroup;
	int bStep;
	int bIndent;
	int bGrade;
	int bLike;
	int likememeer;
	
	public BDto() {
	
	}

	public BDto(int bId, String bName, String bPw, String bContent, String bTitle, Timestamp bDate, int bHit,
			int bGroup, int bStep, int bIndent, int bGrade, int bLike) {
		super();
		this.bId = bId;
		this.bName = bName;
		this.bPw = bPw;
		this.bContent = bContent;
		this.bTitle = bTitle;
		this.bDate = bDate;
		this.bHit = bHit;
		this.bGroup = bGroup;
		this.bStep = bStep;
		this.bIndent = bIndent;
		this.bGrade = bGrade;
		this.bLike = bLike;
	}

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getbPw() {
		return bPw;
	}

	public void setbPw(String bPw) {
		this.bPw = bPw;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public Timestamp getbDate() {
		return bDate;
	}

	public void setbDate(Timestamp bDate) {
		this.bDate = bDate;
	}

	public int getbHit() {
		return bHit;
	}

	public void setbHit(int bHit) {
		this.bHit = bHit;
	}

	public int getbGroup() {
		return bGroup;
	}

	public void setbGroup(int bGroup) {
		this.bGroup = bGroup;
	}

	public int getbStep() {
		return bStep;
	}

	public void setbStep(int bStep) {
		this.bStep = bStep;
	}

	public int getbIndent() {
		return bIndent;
	}

	public void setbIndent(int bIndent) {
		this.bIndent = bIndent;
	}

	public int getbGrade() {
		return bGrade;
	}

	public void setbGrade(int bGrade) {
		this.bGrade = bGrade;
	}

	public int getbLike() {
		return bLike;
	}

	public void setbLike(int bLike) {
		this.bLike = bLike;
	}

	
	
	
	
}