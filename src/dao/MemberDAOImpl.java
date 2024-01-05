package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.DBUtil;
import vo.MemberVO;

public abstract class MemberDAOImpl implements MemberDAO{

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		@Override
		public MemberVO join(MemberVO memberVO) {
			conn= DBUtil.getConnection();
			String sql = "INSERT INTO projecttbl(userID,password,userName,phoneNum) VALUES(?,?,?,?)";
			try {
				MemberDAO m;
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
			
			return null;
		}
	
}
			
	