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
import com.study.jsp.dto.BDto;
import com.study.jsp.dto.BLikeDto;
import com.study.jsp.dto.BReplyDto;
import com.study.jsp.dto.BReplyRankDto;
import com.study.jsp.dto.BboardRankDto;
import com.study.jsp.dto.MemberDto;

public class BDao {
	private static BDao instance = new BDao();
	DataSource dataSource = null;
	int listCount = 10;
	int pageCount = 10;
	public static final int LIKE_BOARD = 1;
	public static final int UNLIKE_BOARD = -1;

	public BDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static BDao getInstance() {
		return instance;
	}

	public void write(String bName, String bPw, String bTitle, String bContent, String selectInfo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			String query = "";
			System.out.println("write db" + selectInfo);
			if ("Info".equals(selectInfo)) {
				query = " insert into mvc_board "
						+ " (bId, bName, bPw, bTitle, bContent, bHit, bGroup, bStep, bIndent, bGrade, bLike) "
						+ " values " + " (mvc_board_seq.nextval,?,?,?,?,0, mvc_board_seq.currval, 0, 0, 1, 0)";
			} 
			else {
				query = " insert into mvc_board "
						+ " (bId, bName, bPw, bTitle, bContent, bHit, bGroup, bStep, bIndent, bGrade, bLike) "
						+ " values " + " (mvc_board_seq.nextval,?,?,?,?,0, mvc_board_seq.currval, 0, 0, 0, 0)";
			}

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bPw);
			pstmt.setString(3, bTitle);
			pstmt.setString(4, bContent);
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

	// ���� �Խ��� �Խù� ��ü ��ȸ
	public ArrayList<BDto> list(int curPage) {
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;

		try {
			con = getConnection();

			String query = "select * " + " from ( " + " select rownum num, A.* " + " from( " + " 	select * "
					+ " 		from mvc_board " + " 		order by bgrade desc, bgroup desc, bstep asc ) A "
					+ "  where rownum <= ? ) B " + " where B.num >= ? ";

			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, nEnd);
			pstmt.setInt(2, nStart);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bPw = resultSet.getString("bPw");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				int bGrade = resultSet.getInt("bGrade");
				int bLike = resultSet.getInt("bLike");

				BDto dto = new BDto(bId, bName, bPw, bContent, bTitle, bDate, bHit, bGroup, bStep, bIndent, bGrade,
						bLike);

				dtos.add(dto);

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
		return dtos;
	}

	// �˻� �ϱ�
	public ArrayList<BDto> serach(String search, String choicemenu, int curPage) {
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;

		try {
			con = getConnection();
			// ��������
			if ("1".equals(choicemenu)) {
				String query = "select * " + " from ( " + " select rownum num, A.* " + " from( " + " 	select * "
						+ " 		from mvc_board where btitle like ?"
						+ " 		order by bgroup desc, bstep asc ) A " + "  where rownum <= ? ) B "
						+ " where B.num >= ? ";

				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%" + search + "%");
				pstmt.setInt(2, nEnd);
				pstmt.setInt(3, nStart);
				resultSet = pstmt.executeQuery();

			}

			// �������� ã��
			else if ("2".equals(choicemenu)) {
				String query = "select * " + " from ( " + " select rownum num, A.* " + " from( " + " 	select * "
						+ " 		from mvc_board where bcontent like ?"
						+ " 		order by bgroup desc, bstep asc ) A " + "  where rownum <= ? ) B "
						+ " where B.num >= ? ";

				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%" + search + "%");
				pstmt.setInt(2, nEnd);
				pstmt.setInt(3, nStart);
				resultSet = pstmt.executeQuery();

			}
			// �ۼ��ڷ� ã��
			else if ("3".equals(choicemenu)) {
				String query = "select * " + " from ( " + " select rownum num, A.* " + " from( " + " 	select * "
						+ " 		from mvc_board where bname like ?" + " 		order by bgroup desc, bstep asc ) A "
						+ "  where rownum <= ? ) B " + " where B.num >= ? ";

				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%" + search + "%");
				pstmt.setInt(2, nEnd);
				pstmt.setInt(3, nStart);
				resultSet = pstmt.executeQuery();

			}

			while (resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bPw = resultSet.getString("bpw");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				int bGrade = resultSet.getInt("bGrade");
				int bLike = resultSet.getInt("bLike");

				BDto dto = new BDto(bId, bName, bPw, bContent, bTitle, bDate, bHit, bGroup, bStep, bIndent, bGrade, bLike);
				dtos.add(dto);
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
		return dtos;
	}

	// ������ ȸ�� ������ȸ
	public ArrayList<MemberDto> memberlist(int curPage, String menuchoice, String search) {
		
		ArrayList<MemberDto> memberdtos = new ArrayList<MemberDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;

		try {
			con = getConnection();

			if ("1".equals(menuchoice)) {
				String query = "select * " + " from ( " + " select rownum num, A.* " + " from( " + " 	select * "
						+ " 		from members where name like ? " + " 		order by rdate desc) A "
						+ "  where rownum <= ? ) B " + " where B.num >= ? and mgrade!=2";

				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%" + search + "%");
				pstmt.setInt(2, nEnd);
				pstmt.setInt(3, nStart);
				resultSet = pstmt.executeQuery();

			} else if ("2".equals(menuchoice)) {
				String query = "select * " + " from ( " + " select rownum num, A.* " + " from( " + " 	select * "
						+ " 		from members where id like ? " + " 		order by rdate desc) A "
						+ "  where rownum <= ? ) B " + " where B.num >= ? and mgrade!=2";

				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%" + search + "%");
				pstmt.setInt(2, nEnd);
				pstmt.setInt(3, nStart);
				resultSet = pstmt.executeQuery();

			} else if ("3".equals(menuchoice)) {
				String query = "select * " + " from ( " + " select rownum num, A.* " + " from( " + " 	select * "
						+ " 		from members where email like ? " + " 		order by rdate desc) A "
						+ "  where rownum <= ? ) B " + " where B.num >= ? and mgrade!=2";

				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%" + search + "%");
				pstmt.setInt(2, nEnd);
				pstmt.setInt(3, nStart);
				resultSet = pstmt.executeQuery();

			} else {
				String query = "select * " + " from ( " + " select rownum num, A.* " + " from( " + " 	select * "
						+ " 		from members " + " 		order by rdate desc) A " + "  where rownum <= ? ) B "
						+ " where B.num >= ? and mgrade!=2";

				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, nEnd);
				pstmt.setInt(2, nStart);
				resultSet = pstmt.executeQuery();
			}

			while (resultSet.next()) {
				String id = resultSet.getString("id");
				String pw = resultSet.getString("pw");
				String name = resultSet.getString("name");
				String eMail = resultSet.getString("eMail");
				Timestamp rDate = resultSet.getTimestamp("rDate");
				String address = resultSet.getString("address");
				String mgrade = resultSet.getString("mgrade");

				MemberDto MemberDto = new MemberDto(id, pw, name, eMail, rDate, address, mgrade);

				memberdtos.add(MemberDto);

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
		return memberdtos;
	}

	// �Խ��� ���� ��ȸ
	public BDto contentView(String strID) {		
		upHit(strID);
		
		BDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			con = getConnection();

			String query = "select * from mvc_board where bId=?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strID));
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				int bId = resultSet.getInt("bid");
				String bName = resultSet.getString("bName");
				String bPw = resultSet.getString("bPw");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				int bGrade = resultSet.getInt("bGrade");
				int bLike = resultSet.getInt("bLike");

				dto = new BDto(bId, bName, bPw, bContent, bTitle, bDate, bHit, bGroup, bStep, bIndent, bGrade, bLike);
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

		return dto;
	}

	// ������ ȸ�� ���� ��ȸ
	public MemberDto membercontentView(String strID) {

		MemberDto memberDto = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			con = getConnection();

			String query = "select * from members where id=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, strID);
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				String id = resultSet.getString("id");
				String pw = resultSet.getString("pw");
				String name = resultSet.getString("name");
				String eMail = resultSet.getString("eMail");
				Timestamp rDate = resultSet.getTimestamp("rDate");
				String address = resultSet.getString("address");
				String mgrade = resultSet.getString("mgrade");

				memberDto = new MemberDto(id, pw, name, eMail, rDate, address, mgrade);

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
		return memberDto;
	}
	
	//���� �ϱ�
	public void modify(String bId, String bTitle, String bContent) {
		Connection con = null;
		PreparedStatement pstmt = null;

		String query = "update mvc_board set" + "	bTitle = ?, " + "	  bContent = ? "
				+ " where bId=? ";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bTitle);
			pstmt.setString(2, bContent);
			pstmt.setString(3, bId);
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
	
	// �Խù��ø�  ���� �ű��
	public ArrayList<BboardRankDto> board_rank() {

		ArrayList<BboardRankDto> dtos = new ArrayList<BboardRankDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			con = getConnection();
			String query = 
					"select * from "
					+ " (select bname,count(*)as boardcount,rank() over(order by count(*) desc) as ranknum "
					+ " from mvc_board where bdate-1 "
					+ " between trunc(sysdate) - 7 and trunc(sysdate) group by bname)"
					+ " rn1  where rownum <=3";
			pstmt = con.prepareStatement(query);
			
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				String bname = resultSet.getString("bname");
				int ranknum = resultSet.getInt("ranknum");
				int boardcount = resultSet.getInt("boardcount");
												
				BboardRankDto dto = new BboardRankDto(bname, boardcount, ranknum);
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
	
	

	//��ȸ�� ����
	public void upHit(String bId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String query = "update mvc_board set bHit = bHit+1 where bId=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bId);

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
	//��ȸ�� ����
	public void downHit(String bId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String query = "update mvc_board set bHit = bHit-1 where bId=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bId);

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
	
	//�Խù� ����
	public void delete(String bId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String query = "delete from mvc_board where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(bId));
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
	


	// ������ ȸ�� ���� ������
	public BPageInfo memberPage(int curPage, String menuchoice, String search) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		int listCount = 10;
		int pageCount = 10;

		int totalCount = 0;
		try {
			con = dataSource.getConnection();
			if ("1".equals(menuchoice)) {
				String query = "select count(*) as total from members where name like ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%" + search + "%");
				resultSet = pstmt.executeQuery();
				if (resultSet.next()) {
					totalCount = resultSet.getInt("total");
					System.out.println("1�� : " + totalCount);
				}

			} else if ("2".equals(menuchoice)) {
				String query = "select count(*) as total from members where id like ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%" + search + "%");
				resultSet = pstmt.executeQuery();
				if (resultSet.next()) {
					totalCount = resultSet.getInt("total");
					System.out.println("2�� : " + totalCount);
				}
			} else if ("3".equals(menuchoice)) {
				String query = "select count(*) as total from members where email like ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%" + search + "%");
				resultSet = pstmt.executeQuery();
				if (resultSet.next()) {
					totalCount = resultSet.getInt("total");
					System.out.println("3�� : " + totalCount);
				}
			} else {
				String query = "select count(*) as total from members";
				pstmt = con.prepareStatement(query);
				resultSet = pstmt.executeQuery();

				if (resultSet.next()) {
					totalCount = resultSet.getInt("total");
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

		// �������� ��
		int totalPage = totalCount / listCount;
		if (totalCount % listCount > 0)
			totalPage++;

		// ���� ������
		int myCurPage = curPage;
		if (myCurPage > totalPage)
			myCurPage = totalPage;
		if (myCurPage < 1)
			myCurPage = 1;

		// ���� ������
		int startPage = ((myCurPage - 1) / pageCount) * pageCount + 1;

		// �� ������
		int endPage = startPage + pageCount - 1;
		if (endPage > totalPage)
			endPage = totalPage;

		BPageInfo pinfo = new BPageInfo();
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(curPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);

		return pinfo;
	}

	// �����Խ��� ������
	public BPageInfo articlePage(int curPage, String menuchoice, String search) {
		System.out.println("page���� ����");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		int listCount = 10;
		int pageCount = 10;

		int totalCount = 0;
		try {
			con = dataSource.getConnection();

			// �������� ã�� ����
			if ("1".equals(menuchoice)) {
				String query = "select count(*) as total from mvc_board where btitle like ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%" + search + "%");
				resultSet = pstmt.executeQuery();
				if (resultSet.next()) {
					totalCount = resultSet.getInt("total");
					System.out.println("1�� : " + totalCount);
				}
				// �������� ã�� ����
			} else if ("2".equals(menuchoice)) {
				String query = "select count(*) as total from mvc_board where bcontent like ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%" + search + "%");
				resultSet = pstmt.executeQuery();
				if (resultSet.next()) {
					totalCount = resultSet.getInt("total");
					System.out.println("2�� : " + totalCount);

				}
				// �̸����� ã�� ����
			} else if ("3".equals(menuchoice)) {
				String query = "select count(*) as total from mvc_board where bname like ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%" + search + "%");
				resultSet = pstmt.executeQuery();
				if (resultSet.next()) {
					totalCount = resultSet.getInt("total");
					System.out.println("3�� : " + totalCount);

				}
			}
			// ����Ʈ ��ü�˻� ����
			else {
				String query = "select count(*) as total from mvc_board";
				pstmt = con.prepareStatement(query);
				resultSet = pstmt.executeQuery();
				if (resultSet.next()) {
					totalCount = resultSet.getInt("total");
					System.out.println(totalCount);
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

		// �������� ��
		int totalPage = totalCount / listCount;
		if (totalCount % listCount > 0)
			totalPage++;

		// ���� ������
		int myCurPage = curPage;
		if (myCurPage > totalPage)
			myCurPage = totalPage;
		if (myCurPage < 1)
			myCurPage = 1;

		// ���� ������
		int startPage = ((myCurPage - 1) / pageCount) * pageCount + 1;

		// �� ������
		int endPage = startPage + pageCount - 1;
		if (endPage > totalPage)
			endPage = totalPage;

		BPageInfo pinfo = new BPageInfo();
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(curPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);

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
