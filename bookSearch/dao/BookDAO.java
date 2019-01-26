package bookSearch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bookSearch.dto.BookDTO;

public class BookDAO {
	
	private BookConnectionMaker connectionMaker;
	
	public BookDAO(BookConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}
	
	public List<BookDTO> select(String keyword) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BookDTO> list = new ArrayList<BookDTO>();
		
		try {
			con = connectionMaker.getConnection();
			String sql = "select bisbn, btitle, bauthor from book where btitle like ?"; 
			pstmt = con.prepareStatement(sql);  
			pstmt.setString(1, "%" + keyword + "%");   
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BookDTO dto = new BookDTO();
				dto.setBisbn(rs.getString("bisbn"));
				dto.setBtitle(rs.getString("btitle"));
				dto.setBauthor(rs.getString("bauthor"));
				list.add(dto);
			}
			
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패");
		//	con.rollback(); 		// sql문을 실행하기 전 상태로 되돌림.
		} finally {
			try {
				
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch (Exception e2) {
				System.out.println("뭔가 이상해요");
			}
		}
		
		return list;
	}

	public int delete(String text) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			con = connectionMaker.getConnection();
			
			String sql = "delete from book where bisbn = ?";
			pstmt = con.prepareStatement(sql);  
			pstmt.setString(1, text);  
			
			count = pstmt.executeUpdate(); 
			
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패");
		} finally {
			try {
				// 6. close
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch (Exception e2) {
				System.out.println("뭔가 이상해요");
			}
		}
		
		return count;
	}
}
