package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.DBUtil;
import vo.PayVO;

public class PayDaoImpl implements PayDAO {

	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	@Override
	public PayVO pay(PayVO payVO) {
		Connection conn = DBUtil.getConnection();
		String sql = "select name, date, seat, musical, price from pay";
		try {
			PayDAO m;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, payVO.getName());
			pstmt.setString(2, payVO.getDate());
			pstmt.setString(3, payVO.getSeat());
			pstmt.setString(4, payVO.getMusical());
			pstmt.setString(5, payVO.getPrice());
			
			int result = pstmt.executeUpdate();
			
		}catch (SQLException e) {
			
		}finally {
			DBUtil.close(rs, pstmt);
		}
		return payVO;
	}

}
