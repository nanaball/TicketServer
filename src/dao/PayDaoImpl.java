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
	// PayMain에 예매한 정보를 TextField에 덮어씌우고 싶음
	// PayCheck도 동일함
	// 방법을 모름
	
	// 선택한 정보를 불러와서 PayMain에 넣기
	// 모름 큰일남
	@Override
	public PayVO pay(String name, String date, String seat, String musical, Integer price) {
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
			pstmt.setInt(5, price);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				price = rs.getInt("price");
				seat = rs.getString("seat");
				musical = rs.getString("musical");
				date = rs.getString("date");
				name = rs.getString("name");
				paying = new PayVO(price,date,seat,musical,name);
				
			}
			
		}catch (SQLException e) {
			
		}finally {
			DBUtil.close(rs, pstmt);
		}
		return paying;
	}
	
	// 예매한 정보를 DB에 send
	@Override
	public boolean addPay(PayVO payVO) {
		boolean addPay = false;
		Connection conn = DBUtil.getConnection();
		String sql = "INSERT INTO pay VALUES(?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, payVO.getName());
			pstmt.setString(2, payVO.getDate());
			pstmt.setString(3, payVO.getSeat());
			pstmt.setString(4, payVO.getMusical());
			pstmt.setInt(5, payVO.getPrice());

			int result = pstmt.executeUpdate();
			
			if(result == 1) {
				addPay = true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(rs, pstmt);
		}
		return addPay;
	}
	





}
