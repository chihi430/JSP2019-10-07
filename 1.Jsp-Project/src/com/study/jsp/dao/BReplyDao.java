package com.study.jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.study.jsp.BPageInfo;
import com.study.jsp.dto.BLikeDto;
import com.study.jsp.dto.BReplyDto;
import com.study.jsp.dto.BReplyRankDto;

public class BReplyDao {
	private static BReplyDao instance = new BReplyDao();
	DataSource dataSource = null;
	
	public BReplyDao(){
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static BReplyDao getInstance() {
		return instance;
	}
	
	//리플 리스트 뽑기
	public ArrayList<BReplyDto> reply_list(String bId) {

		ArrayList<BReplyDto> dtos = new ArrayList<BReplyDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			con = getConnection();
			String query = "select * from reply where replybid = ? order BY replydate desc";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(bId));
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				int replynum = resultSet.getInt("replynum");
				int replybid = resultSet.getInt("replybid");
				String replyid = resultSet.getString("replyid");
				String replybcontent = resultSet.getString("replybcontent");				
				Timestamp replyDate = resultSet.getTimestamp("replyDate");
				
				BReplyDto dto = new BReplyDto(replynum, replybid, replyid, replybcontent, replyDate);
				
				dtos.add(dto);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
				if(resultSet != null) {
					resultSet.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}		
		return dtos;
	}

	
	public ArrayList<BReplyRankDto> reply_rank() {

		ArrayList<BReplyRankDto> dtos = new ArrayList<BReplyRankDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			con = getConnection();
			String query = 
					"select * from "
					+ " (select replyid,count(*) as replycount,rank() over(order by count(*) desc) as ranknum "
					+ " from reply where replydate-1 between trunc(sysdate) - 7 and trunc(sysdate)"
					+ " group by replyid order by count(*) desc) rn1  "
					+ " where rownum <=3 ";
			pstmt = con.prepareStatement(query);
			
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				int ranknum = resultSet.getInt("ranknum");
				int replycount = resultSet.getInt("replycount");
				String replyid = resultSet.getString("replyid");
								
				BReplyRankDto dto = new BReplyRankDto(replyid, replycount, ranknum);
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
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}		
		return dtos;
	}	
	
	//bid로 찾기 삭제하자
	public void reply_delete(String str) {
		System.out.println("str : "+str);
		Connection con = null;
		PreparedStatement pstmt = null;
		System.out.println("댓글 str : "+str);
		try {
			con = getConnection();
			String query = "delete from reply where replynum = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(str));
			pstmt.executeQuery();

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
	}

	//댓글달기 insert
	public void reply(String bId, String id, String bContent) {
		//replyShape(bGroup, bStep);
		BDao dao = new BDao();
		dao.downHit(bId);
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			String query = "insert into reply " + " (replynum, replybid , replyid, replybcontent) "
					+ "values (reply_seq.nextval, ?, ?, ?)";
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, bId);
			pstmt.setString(2, id);			
			pstmt.setString(3, bContent);

			int rn = pstmt.executeUpdate();

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
	}
	
	public void replymodify(String replybcontent, String replynum) {
		Connection con = null;
		PreparedStatement pstmt = null;

		String query = "update reply set replybcontent=? where replynum = ?";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, replybcontent);
			pstmt.setString(2, replynum);			
			int rn = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public BPageInfo replycount(String replybId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int replytotalCount=0;
		try {
			con = dataSource.getConnection();
			
			String query = "select count(*) as replytotal from reply where replybid = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, replybId);
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				replytotalCount = resultSet.getInt("replytotal");
				System.out.println("리플 갯수 : " + replytotalCount);
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
		BPageInfo pinfo = new BPageInfo();
		pinfo.setTotalreplyCount(replytotalCount);
		
		return pinfo;
	}

	private Connection getConnection() {
		Context context = null;
		DataSource dataSource = null;
		Connection con = null;
		try {
			context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/Oracle11g");
			con = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;

	}
}
