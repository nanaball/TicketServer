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
	
	// 아.. 큰일났네
	
	@Override
	public PayVO pay(String name, String date, String seat, String musical, String price) {
		PayVO paying = null;
		Connection conn = DBUtil.getConnection();
		String sql = "select name, date, seat, musical, price from pay";
		try {
			PayDAO m;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, date);
			pstmt.setString(3, seat);
			pstmt.setString(4, musical);
			pstmt.setString(5, price);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				price = rs.getString("price");
				seat = rs.getString("seat");
				musical = rs.getString("musical");
				date = rs.getString("date");
				name = rs.getString("name");
				paying = new PayVO(name,price,date,musical,seat);
				
			}
			
		}catch (SQLException e) {
			
		}finally {
			DBUtil.close(rs, pstmt);
		}
		return paying;
	}
	
	@Override
	public boolean addPay(PayVO payVO) {
		boolean isJoin = false;
		Connection conn = DBUtil.getConnection();
		String sql = "INSERT INTO pay VALUES(?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, payVO.getName());
			pstmt.setString(2, payVO.getDate());
			pstmt.setString(3, payVO.getSeat());
			pstmt.setString(4, payVO.getMusical());
			pstmt.setString(5, payVO.getPrice());

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
	





}
