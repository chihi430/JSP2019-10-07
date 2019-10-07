package member;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class MemberMgr {

        private ResultSet rs;
        private Connection conn = null;
        private PreparedStatement pstmt = null;
        private String jdbc_driver = "org.postgresql.Driver";
        private String jdbc_url = "jdbc:postgresql://localhost:5432/postgres";
    	
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
    

    public ArrayList<ZipcodeBean> addressRead(String p_area3) {
    	connect();
        ArrayList<ZipcodeBean> list = new ArrayList<>();
        try {
            String sql = "select * from ziptab where area3 like ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, p_area3 + "%");
            rs = pstmt.executeQuery();
            while(rs.next()) {
            //System.out.println(rs.getString("area3"));
            ZipcodeBean bean = new ZipcodeBean();
            bean.setZipcode(rs.getString("zipcode"));
            bean.setArea1(rs.getString("area1"));
            bean.setArea2(rs.getString("area2"));
            bean.setArea3(rs.getString("area3"));
            bean.setArea4(rs.getString("area4"));
            list.add(bean);
            
                
            }
        } catch (Exception e) {
            System.out.println("addressRead err:" + e);
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
    
    public boolean checkId(String id) { // regform을 거쳐서 registerproc.jsp 로 날아감
    	connect();
        boolean b = false;
        try {
            String sql = "select id from member where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            b = rs.next();    
        } catch (Exception e) {
            System.out.println("checkId err" + e);
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
    
    public boolean memInsert(MemberBean bean) {
    	connect();
        boolean b = false;
        try {
            String sql = "insert into member values(?,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bean.getId());
            pstmt.setString(2, bean.getPasswd());
            pstmt.setString(3, bean.getName());
            pstmt.setString(4, bean.getEmail());
            pstmt.setString(5, bean.getPhone());
            pstmt.setString(6, bean.getZipcode());
            pstmt.setString(7, bean.getAddress());
            pstmt.setString(8, bean.getJob());
            if(pstmt.executeUpdate() > 0) b = true;
            
        } catch (Exception e) {
            // TODO: handle exception
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
    
    public boolean loginCheck(String id, String passwd) {
    	connect();
        boolean b = false;
        try {
            String sql = "select * from member where id=? and passwd=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, passwd);
            rs = pstmt.executeQuery();
            b = rs.next();
        } catch (Exception e) {
            // TODO: handle exception
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
    
    public MemberBean getMember(String id) { //memberupdate.jsp를 위한 것
    	connect();
        MemberBean bean = null;
        try {
            String sql = "select * from member where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                bean = new MemberBean();
                bean.setId(rs.getString("id"));
                bean.setPasswd(rs.getString("passwd"));
                bean.setName(rs.getString("name"));
                bean.setEmail(rs.getString("email"));
                bean.setPhone(rs.getString("phone"));
                bean.setZipcode(rs.getString("zipcode"));
                bean.setAddress(rs.getString("address"));
                bean.setJob(rs.getString("job"));
            }
            
        } catch (Exception e) {
            System.out.println("getMember err: " + e);
        }finally {
            try {
                if(rs != null) rs.close();
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
            } catch (Exception e2) {
                // TODO: handle exception
            }    
        }
        return bean;
    }
    
    public boolean memberUpdate(MemberBean bean, String id) {
    	connect();
        boolean b = false;
        try {
            String sql = "update member set passwd=?, name=?, email=?, phone=?, zipcode=?, address=?, job=? where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bean.getPasswd());
            pstmt.setString(2, bean.getName());
            pstmt.setString(3, bean.getEmail());
            pstmt.setString(4, bean.getPhone());
            pstmt.setString(5, bean.getZipcode());
            pstmt.setString(6, bean.getAddress());
            pstmt.setString(7, bean.getJob());
            pstmt.setString(8, id);
            if(pstmt.executeUpdate() > 0) b = true; //결과가 0 이상이면 return

        } catch (Exception e) {
            System.out.println("memeberUpdate err : " + e);
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
    
    public boolean adminLoginCheck(String adminid, String adminpasswd) {
    	connect();
        boolean b = false;
        try {
            String sql = "select * from admin where admin_id=? and admin_passwd=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, adminid);
            pstmt.setString(2, adminpasswd);
            rs = pstmt.executeQuery();
            b = rs.next();    
        } catch (Exception e) {
            // TODO: handle exception
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
    

    
    public ArrayList<MemberBean> getMemberAll() {
    	connect();
        ArrayList<MemberBean> list = new ArrayList<>();
        try {
            String sql = "select * from member";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                MemberBean bean = new MemberBean();
                bean.setId(rs.getString("id"));
                bean.setName(rs.getString("name"));
                bean.setEmail(rs.getString("email"));
                bean.setPhone(rs.getString("phone"));
                list.add(bean);    
            }
        } catch (Exception e) {
            System.out.println("getMemberAll err:" + e);
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

    
}
