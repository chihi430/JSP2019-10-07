package com.joker.board.db;

public class RecDTO {
	private int board_no;
	private String rec_id;
	private int good;

	public int getBoard_no() {
		return board_no;
	}

	public String getRec_id() {
		return rec_id;
	}

	public int getGood() {
		return good;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public void setRec_id(String rec_id) {
		this.rec_id = rec_id;
	}

	public void setGood(int good) {
		this.good = good;
	}
}
