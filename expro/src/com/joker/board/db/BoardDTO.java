package com.joker.board.db;

import java.util.Date;

public class BoardDTO {
	private String id;
	private int board_no;
	private String board_subject;
	private String board_content;
	private String ip;
	private int readcount;
	private String file_name;
	private String section;
	private Date write_date;

	public String getId() {
		return id;
	}

	public int getBoard_no() {
		return board_no;
	}

	public String getBoard_subject() {
		return board_subject;
	}

	public String getBoard_content() {
		return board_content;
	}

	public String getIp() {
		return ip;
	}

	public int getReadcount() {
		return readcount;
	}

	public String getFile_name() {
		return file_name;
	}

	public String getSection() {
		return section;
	}

	public Date getWrite_date() {
		return write_date;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public void setBoard_subject(String board_subject) {
		this.board_subject = board_subject;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}

}
