package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.DBUtil;
import vo.MemberVO;

public class MemberDAOImpl implements MemberDAO{

		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt =null;
		ResultSet rs = null;
	
		@Override
		public boolean join(MemberVO memberVO) {
			boolean isJoin = false;
			conn= DBUtil.getConnection();
			String sql = "INSERT INTO member(userID,password,userName,phoneNum) VALUES(?,?,?,?)";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memberVO.getUserID());
				pstmt.setString(2, memberVO.getPassword());
				pstmt.setString(3, memberVO.getUserName());
				pstmt.setString(4, memberVO.getPhoneNum());

				int result = pstmt.executeUpdate();
				if(result == 1) {
					isJoin = true;
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBUtil.close(rs, pstmt);
			}
			return isJoin;
		}
		
		
		@Override
		public MemberVO login(String id, String pass) {
			MemberVO member = null;
			try {
				// conn field 초기화
				conn = DBUtil.getConnection();
				String sql = "SELECT * FROM member WHERE userID = ? AND password = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, pass);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					String userId = rs.getString(1);
					String password = rs.getString(2);
					String userName = rs.getString(3);
					String phoneNum = rs.getString(4);
					member = new MemberVO(userId,password,userName,phoneNum);
					System.out.println("loginMember : "+member);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBUtil.close(rs,pstmt);
			}
			return member;
		}
	
}
			
	