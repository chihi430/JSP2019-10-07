package com.study.jsp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.study.jsp.dto.MemberDto;



public class MemberDao {
	
	public static final int MEMBER_NONEXISTENT = 0;
	public static final int MEMBER_EXISTENT = 1;
	public static final int MEMBER_JOIN_FAIL =0;
	public static final int MEMBER_JOINSUCCESS = 1;
	public static final int MEMBER_LOGIN_PW_NO_GOOD = 0;
	public static final int MEMBER_LOGIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_IS_NOT = -1;
	public static final int BLACKLIST = -2;
	public static final int MEMBER = 1;
	public static final int ADMIN = 2;
	
	private static MemberDao instance = new MemberDao();
	
	private MemberDao() {
		
	}
	
	public static MemberDao getInstance() {
		return instance;
	}
	
	//ȸ������
	public int insertMember(MemberDto dto) {
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "insert into members values(?,?,?,?,?,?,1,0,0)";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.geteMail());
			pstmt.setTimestamp(5, dto.getrDate());
			pstmt.setString(6, dto.getAddress());
			pstmt.executeUpdate();
			ri=MemberDao.MEMBER_JOINSUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null)con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
	// ���̵� �ߺ� üũ
	public int confirmId(String id) {
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select id from members where id = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			if(set.next()) {
				ri = MemberDao.MEMBER_EXISTENT;				
			}else {
				ri = MemberDao.MEMBER_NONEXISTENT;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {				
				set.close();
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		return ri;
	}
	
	// �α��� (���̵� �н�����) üũ
	public int userCheck(String id, String pw) {
		int ri=0;
		String dbPw;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query="select pw, mgrade from members where id=?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			
			if(set.next()) {
				if(set.getString("mgrade").equals("0")) {
					System.out.println("login BLACKLIST");
					ri = MemberDao.BLACKLIST; // ��������Ʈ
					return ri;
				}
				dbPw =set.getString("pw");
				if(dbPw.equals(pw)) {
					System.out.println("login ok");
					ri = MemberDao.MEMBER_LOGIN_SUCCESS;  //�α��� OK
				}else {
					System.out.println("login fail : PW");
					ri =MemberDao.MEMBER_LOGIN_PW_NO_GOOD; // ��й�ȣ x
				}
			}else {
				System.out.println("login fail : ID");
				ri = MemberDao.MEMBER_LOGIN_IS_NOT; //���̵� x
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return ri;	
	}
	/////////////////////////////////////
	
	//���̵����� ȸ������ �� �ҷ�����
	public MemberDto getMember(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query="select * from members where id=?";
		MemberDto dto = null;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			if(set.next()) {
				dto = new MemberDto();
				dto.setId(set.getString("id"));
				dto.setPw(set.getString("pw"));
				dto.setName(set.getString("name"));
				dto.seteMail(set.getString("eMail"));
				dto.setrDate(set.getTimestamp("rDate"));
				dto.setAddress(set.getString("address"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}
	
	//ȸ�� ������Ʈ
	public int updateMember(MemberDto dto) {
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String query="update members set pw=?, eMail=?, address=? where id=?";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.geteMail());
			pstmt.setString(3, dto.getAddress());
			pstmt.setString(4, dto.getId());
			ri = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	public int deleteMember(MemberDto dto) {
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String query="delete from members where id = ?";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getId());
			ri = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	public int adminlogin(String id, String pw) {
		System.out.println("������ �α���");
		int ri = 0;
		String dbPw;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select pw from members where id=? and mgrade='2'";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			if(set.next()) {
				dbPw =set.getString("pw");
				if(dbPw.equals(pw)) {
					System.out.println("admin login ok");
					ri = MemberDao.MEMBER_LOGIN_SUCCESS;  //�α��� OK
				}else {
					System.out.println("admin login fail : PW");
					ri =MemberDao.MEMBER_LOGIN_PW_NO_GOOD; // ��й�ȣ x
				}
			}else {
				System.out.println("admin login fail : ID");
				ri = MemberDao.MEMBER_LOGIN_IS_NOT; //���̵� x
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();				
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}	
		return ri;
	}
	
	//��������Ʈ
	public int BlacklistMember(MemberDto dto) {
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String query="update members set mgrade=0 where id=?";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getId());
			ri = pstmt.executeUpdate();
			

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	//��������Ʈ
	public int BlacklistfreeMember(MemberDto dto) {
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String query="update members set mgrade=1 where id=?";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getId());
			ri = pstmt.executeUpdate();
			

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	//������ ȸ�� ��� �Ǻ� �޼ҵ�
	public boolean gradecheck(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select mgrade from members where id=?";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			while(set.next()) {
				String grade = set.getString("mgrade");
				if("2".equals(grade)) {
					System.out.println("�Ǻ� ��� ceo �Դϴ�.");
					return true;
				}else {
					return false;
				}			
			}
		} catch (Exception e) {
			
		} finally {
			try {
				set.close();
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	
		return false;	
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