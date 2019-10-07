package com.study.jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.study.jsp.dto.BLikeDto;
import com.study.jsp.dto.BboardRankDto;

public class BLikeDao {
	private static BLikeDao instance = new BLikeDao();
	DataSource dataSource = null;
	int listCount = 10;
	int pageCount = 10;
	
	public BLikeDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	public static BLikeDao getInstance() {
		return instance;
	}
	
	// 좋아요
	public int likeboard(String bId, String id, String check) {
		BDao dao = new BDao();
		
		dao.downHit(bId);
		liketest(bId, id, check);
		
		int ri = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String query = "";
			if (check.equals("like")) {
				query = "update mvc_board set blike=blike+1 where bId=?";				
				ri = BDao.LIKE_BOARD; // 1
			} else if (check.equals("unlike")) {
				query = "update mvc_board set blike=blike-1 where bId=?";
				ri = BDao.UNLIKE_BOARD; // 0
			}

			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(bId));
			pstmt.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}	
	
	
	// 게시물 좋아요테이블에 생성및 삭제
	public int liketest(String bId, String id, String check) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		int ri = 0;
		try {
			con = getConnection();
			if(check.equals("like")) {
				query = "insert into ex_like (likerid, likebid, likeid) values(like_seq.nextval, ?, ?)";	
			}else if(check.equals("unlike")){
				query = "delete from ex_like where likebid=? and likeid=?";	
			}	
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(bId));
			pstmt.setString(2, id);
			pstmt.executeUpdate();
						
		
		} catch (Exception e) {
			e.printStackTrace();			
		}  finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}

	// 좋아요 누른 회원 개별 조회
	public BLikeDto likecontentView(String strID, String sbId) {
		
		BLikeDto bLikeDto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			con = getConnection();

			String query = "select * from ex_like where likeid = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, strID);
			resultSet = pstmt.executeQuery();
			
			while (resultSet.next()) {
				if (resultSet.getString("likebid").equals(sbId)) {
					
					int likerId = resultSet.getInt("likerid");
					int likebId = resultSet.getInt("likebid");
					String likeid = resultSet.getString("likeid");
					int good = resultSet.getInt("good");
					
					bLikeDto = new BLikeDto(likerId, likebId, likeid, good);										
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return bLikeDto;
	}

	// 좋아요 누른 회원 조회
	public ArrayList<BLikeDto> findlikemember(String bId) {

		ArrayList<BLikeDto> dtos = new ArrayList<BLikeDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			con = getConnection();
			String query = "select * from ex_like where likebid=?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(bId));
			resultSet = pstmt.executeQuery();
			
			while (resultSet.next()) {
				int likerId = resultSet.getInt("likerId");				
				int likebId = resultSet.getInt("likebId");
				String likeid = resultSet.getString("likeid");
				int good = resultSet.getInt("good");
												
				BLikeDto dto = new BLikeDto(likerId, likebId, likeid, good);
				dtos.add(dto);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
				if(resultSet != null) {
					resultSet.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		return dtos;
	}		
	
	
	private Connection getConnection() {
		Context context = null;
		DataSource dataSource = null;
		Connection con = null;
		try {
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/Oracle11g");
			con = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
		
	}
	
}
