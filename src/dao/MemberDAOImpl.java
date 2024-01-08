package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.DBUtil;
import vo.MemberVO;

public abstract class MemberDAOImpl implements MemberDAO{

		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt =null;
		ResultSet rs = null;
	
		@Override
		public MemberVO join(MemberVO memberVO) {
			conn= DBUtil.getConnection();
			String sql = "INSERT INTO member(userID,password,userName,phoneNum) VALUES(?,?,?,?)";
			try {
				MemberVO m;
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memberVO.getmId());
				pstmt.setString(2, memberVO.getmPw());
				pstmt.setString(3, memberVO.getmName());
				pstmt.setString(4, memberVO.getpNum());

				int result = pstmt.executeUpdate();
				

			}catch (SQLException e) {
					e.printStackTrace();
				}finally {
					DBUtil.close(rs, pstmt);
				}
			return memberVO;
		}
		
		@Override
		public MemberVO login(String id, String pass) {
			
			try {
				stmt = conn.createStatement();
				String sql = "SELECT * FROM member WHERE id = ? AND pass = ?";
				
				rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
					String userId = rs.getString(1);
					String password = rs.getString(2);
					String result = String.format(id,pass);
					System.out.println(result);
				}
				
				rs.close();
				stmt.close();
				
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			
			
			return null;
		}
	
}
			
	