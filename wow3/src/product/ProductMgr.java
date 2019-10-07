package product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import order.OrderBean;



public class ProductMgr {
    private ResultSet rs;
    private Connection conn = null;
    private PreparedStatement pstmt;
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
	
	public ArrayList<ProductBean> getProductAll(){
		connect();
		ArrayList<ProductBean> list = new ArrayList<>();
		try {
			String sql = "select * from shop_product order by no desc;";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
			ProductBean bean = new ProductBean();
			bean.setNo(rs.getInt("no"));
			bean.setName(rs.getString("name"));
			bean.setPrice(rs.getString("price"));
			bean.setDetail(rs.getString("detail"));
			bean.setSdate(rs.getString("sdate"));
			bean.setPrice(rs.getString("price"));
			bean.setStock(rs.getString("stock"));
			bean.setImage(rs.getString("image"));
			list.add(bean);				
			}
		} catch (Exception e) {
			System.out.println("ProductBean err:" + e);
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
	
	public boolean insertProduct(HttpServletRequest request) {
		connect();
		boolean b = false;
		try {
			//업로드할 이미지 경로(절대 경로)
			String uploadDir ="C:/Users/박창환/eclipse-workspace/wow/WebContent/data";
			//MultipartRequest multi=new MultipartRequest(request, savePath, sizeLimit, new DefaultFileRenamePolicy());
			MultipartRequest multi = new MultipartRequest(request, uploadDir, 5*1024*1024, "utf-8", new DefaultFileRenamePolicy());
			
			String sql = "insert into shop_product(name, price, detail, sdate, stock, image) values(?,?,?,now(),?,?)"; //now는 sdate(지금) 의미
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  multi.getParameter("name"));
			pstmt.setString(2,  multi.getParameter("price"));
			pstmt.setString(3,  multi.getParameter("detail"));
			//sdate는 입력 안해도 자체적으로 생성됨
			pstmt.setString(4,  multi.getParameter("stock"));
			if(multi.getFilesystemName("image") == null) {
				//신상품 이미지를 택하지 않았을 경우 디폴트 이미지 선택
				pstmt.setString(5, "../images/ready.gif");
			}else {
				pstmt.setString(5,  multi.getFilesystemName("image"));
			}			
			if(pstmt.executeUpdate() > 0) b = true;
			
		} catch (Exception e) {
			System.out.println("insertProduct err:" + e);
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
	
	public ProductBean getProduct(String no) {
		connect();
		ProductBean bean = null;
		try {
			String sql = "select * from shop_product where no=?";
			pstmt = conn.prepareStatement(sql);
		    pstmt.setInt(1, Integer.parseInt(no));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bean = new ProductBean();
				bean.setNo(rs.getInt("no"));
				bean.setName(rs.getString("name"));
				bean.setPrice(rs.getString("price"));
				bean.setDetail(rs.getString("detail"));
				bean.setSdate(rs.getString("sdate"));
				bean.setStock(rs.getString("stock"));
				bean.setImage(rs.getString("image"));
			}		
		} catch (Exception e) {
			System.out.println("getProduct err:" + e);
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
	public boolean updateProduct(HttpServletRequest request) {
		connect();
		boolean b = false;
		try {
			String uploadDir ="C:/Users/박창환/eclipse-workspace/wow/WebContent/data";
			//MultipartRequest multi=new MultipartRequest(request, savePath, sizeLimit, new DefaultFileRenamePolicy());
			MultipartRequest multi = new MultipartRequest(request, uploadDir, 5*1024*1024, "utf-8", new DefaultFileRenamePolicy());
			
			
			if(multi.getFilesystemName("image") == null) {
				String sql ="update shop_product set name=?, price=?, detail=?, stock=? where no=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,multi.getParameter("name"));
				pstmt.setString(2,multi.getParameter("price"));
				pstmt.setString(3,multi.getParameter("detail"));
				pstmt.setString(4,multi.getParameter("stock"));
				pstmt.setString(5,multi.getParameter("no"));
				
			}else {
				String sql ="update shop_product set name=?, price=?, detail=?, stock=?, image=? where no=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,multi.getParameter("name"));
				pstmt.setString(2,multi.getParameter("price"));
				pstmt.setString(3,multi.getParameter("detail"));
				pstmt.setString(4,multi.getParameter("stock"));
				pstmt.setString(5,multi.getFilesystemName("image"));
				pstmt.setString(6,multi.getParameter("no"));
			}
			if(pstmt.executeUpdate() > 0 ) b = true;			
		} catch (Exception e) {
			System.out.println("updateProduct err:" + e);
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
	public boolean deleteProduct(String no) {
		connect();
		boolean b = false;
		try {
			
			String sql = "delete from shop_product where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(no));
			if(pstmt.executeUpdate() > 0) b = true;
			
		} catch (Exception e) {
			System.out.println("deleteProduct err:" + e);
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
	
	public void reduceProduct(OrderBean order) {
		connect();
		try {
		
			String sql = "update shop_product set stock=stock - ? where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, order.getQuantity());
			pstmt.setString(2, order.getProduct_no());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("reduceProduct err:" + e);
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
