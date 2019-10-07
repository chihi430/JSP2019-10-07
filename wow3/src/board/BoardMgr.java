package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardMgr { // Board 관련 process
	
    private ResultSet rs;
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private String jdbc_driver = "org.postgresql.Driver";
    private String jdbc_url = "jdbc:postgresql://localhost:5432/postgres";
    
	//paging
	private int tot = 0; // 전체 레코드 수
	private int pList = 5; // 한 페이지에 출력될 수
	private int pageSu = 0; // 전체 페이지 수
	

	
	void connect() {
		try {
			Class.forName(jdbc_driver);

			conn = DriverManager.getConnection(jdbc_url, "postgres", "ghks5523");
			System.out.print("db연결됨");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void disconnect() {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int currentGetmaxnum() { // board 테이블의 최대 num 구하기
		connect();
		String sql = "select max(num) from board";
		int cnt = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(!rs.next()) cnt = 1;
			else cnt = rs.getInt(1);
			
		} catch (Exception e) {
			System.out.println("currentGetmaxnum err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}	
		}
		return cnt;
	}
	
	
	public void saveData(BoardBean bean) {
		connect();
		String sql = "insert into board values(?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			 
			pstmt.setInt(1, bean.getNum());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getPass());
			pstmt.setString(4, bean.getMail());
			pstmt.setString(5, bean.getTitle());
			pstmt.setString(6, bean.getCont());
			pstmt.setString(7, bean.getBip());
			pstmt.setString(8, bean.getBdate());
			pstmt.setInt(9,0); // readcnt
			pstmt.setInt(10,bean.getGnum());
			pstmt.setInt(11,0); //onum
			pstmt.setInt(12,0); //nested
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("saveData err: " + e);
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}	
		}
	}
	public void totalList() {
		connect();
		String sql = "select count(*) from board";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			tot = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("totallist err :" + e);
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}	
		}
	}
	
	public int getPageSu() { // 총 페이지 수 반환
		pageSu = tot / pList;
		if(tot % pList > 0)pageSu++;
		return pageSu;
	}
	public ArrayList<BoardDto> getDataAll(int page, String stype, String sword){
		connect();
		ArrayList<BoardDto> list = new ArrayList<>();
		String sql = "select * from board"; //order by gnum desc, onum asc";
		
		try {
			if(sword == null) { //sword가 검색이 없을 경우
				sql += " order by gnum desc,onum asc";
				pstmt = conn.prepareStatement(sql);
			}else { // 검색이 있을 경우
				sql += " where " + stype + " like ?";
				sql += " order by gnum desc,onum asc";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + sword + "%");
			}
			
			rs = pstmt.executeQuery();
			
			for(int i = 0; i < (page -1) * pList; i++) {
				rs.next(); // 레코드 위치, 0, 4, 9...
			}
			int k = 0;
			while(rs.next() && k < pList) {
				BoardDto dto = new BoardDto();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setBdate(rs.getString("bdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				dto.setNested(rs.getInt("nested"));
				list.add(dto);				
				k++;
			}			
		} catch (Exception e) {
			System.out.println("getDataAll err" + e);
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}	
		}
		return list;
	}
	public BoardDto getData(String num) {
		connect();
		BoardDto dto = null;
		try {
			String sql = " select * from board where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(num));
			rs = pstmt.executeQuery();
			if(rs.next()){
				dto = new BoardDto();
				dto.setName(rs.getString("name"));
				dto.setPass(rs.getString("pass")); //비밀번호
				dto.setMail(rs.getString("mail")); // 이메일
				dto.setTitle(rs.getString("title")); // 글제목
				dto.setCont(rs.getString("cont")); // 컨텐트
				dto.setBip(rs.getString("bip")); // ip 주소
				dto.setBdate(rs.getString("bdate")); // 등록일
				dto.setReadcnt(rs.getInt("readcnt")); // 조회수
			}
			
		} catch (Exception e) {
			System.out.println("getData err" + e);
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}	
		}
		
		return dto;
	}
	public void updateReadcnt(String num) {
		connect();
		// 글 내용 보기 전에 조회수 증가 작업
		try {
			String sql = "update board set readcnt=readcnt + 1 where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(num));
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("updateReadcnt err" + e);
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}	
		}
	}
	public BoardDto getReplyData(String num) { //댓글용
		connect();
		BoardDto dto = null;
		try {
			String sql = "select * from board where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(num));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new BoardDto();
				dto.setTitle(rs.getString("title"));
				dto.setGnum(rs.getInt("gnum"));
				dto.setOnum(rs.getInt("onum"));
				dto.setNested(rs.getInt("nested"));
			}
			
		} catch (Exception e) {
			System.out.println("getReplyData err" + e);
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}	
		}
		
		
		return dto;
	}
	public void updateOnum(int gnum, int onum) {
		// 댓글용 - onum 갱신
		// 같은 그룹의 레코드는 모두 작업에 참여
		// 댓글의 onum은 이미 db에 있는 onum 보다 크거나 같은 경우에 변경
		connect();
		try {
			String sql = "update board set onum=onum+1 where onum >= ? and gnum=?";
			pstmt.getConnection().prepareStatement(sql);
			pstmt.setInt(1, onum);
			pstmt.setInt(2, gnum);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("updateOnum err" + e);
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}	
		}
	}
	public void saveReplyData(BoardBean bean) {
		connect();
		// 댓글 저장
		try {
			String sql = "insert into board values(?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bean.getNum());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getPass());
			pstmt.setString(4, bean.getMail());
			pstmt.setString(5, bean.getTitle());
			pstmt.setString(6, bean.getCont());
			pstmt.setString(7, bean.getBip());
			pstmt.setString(8, bean.getBdate());
			pstmt.setInt(9, 0);
			pstmt.setInt(10, bean.getGnum());
			pstmt.setInt(11, bean.getOnum());
			pstmt.setInt(12, bean.getNested());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("saveReplyData err" + e);
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}	
		}
	}
	public boolean checkPass(int num, String new_pass) {
		// 수정 작업을 하기 전에 비밀번호 검사 : db에 잇는 비밀 == new__pass 여부 확인
		connect();
		boolean b = false;
		try {
			String sql = "select pass from board where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(new_pass.equals(rs.getString("pass"))) {
					b = true;
				}
			}
			
		} catch (Exception e) {
			System.out.println("checkPass err" + e);
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}	
		}
		
		
		return b;
	}
	public void saveEdit(BoardBean bean, String num) {
		connect();
		String sql = "update board set name=?,mail=?,title=?,cont=? where num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getMail());
			pstmt.setString(3, bean.getTitle());
			pstmt.setString(4, bean.getCont());			
			pstmt.setInt(5,Integer.parseInt(num));
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("saveEdit err" + e);
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}	
		}
	}
	
	public void delete(BoardBean bean) {
		connect();
		String sql = "delete from board where gnum=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,bean.getNum());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("delete err" + e);
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}	
		}
	}
	
}