package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.DBUtil;

public class CastDAOImpl implements CastDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	@Override
	public String getCastInfoListString(String date) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		String sql = "SELECT * FROM datetbl WHERE date=?";
		
		StringBuilder sb = new StringBuilder();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				sb.append(rs.getString("date"));
				sb.append(",");
				sb.append(rs.getString("time"));
				sb.append(",");
				sb.append(rs.getString("casting"));
				
				sb.append("|");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			
		}
		
		return sb.toString();
	}

}
