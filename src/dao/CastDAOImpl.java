package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import utils.DBUtil;
import vo.CastVO;

public class CastDAOImpl implements CastDAO {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	@Override
	public List<CastVO> getCastInfoListString(String date) {
		// TODO Auto-generated method stub
		List<CastVO> list = new ArrayList<>();
		conn = DBUtil.getConnection();
		String sql = "SELECT * FROM datetbl WHERE date = ?";
		
//		StringBuilder sb = new StringBuilder();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt(1);
				Date d1 = rs.getDate(2);
				System.out.println(d1.toString());
				Time t1 = rs.getTime(3);
				System.out.println(t1.toString());
				String casting = rs.getString(4);
				System.out.println(casting);
				CastVO cast = new CastVO(id, d1.toString(), t1.toString(),casting);
				list.add(cast);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(rs,pstmt);
		}
		return list;
	}

}
