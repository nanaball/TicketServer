package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import utils.DBUtil;
import vo.TicketVO;

public class ReservationDAOImpl implements ReservationDAO {
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	@Override
	public List<TicketVO> listReservTicket(String date, String time) {
		return null;
	}

	@Override
	public boolean reservationTicket(TicketVO vo) {
		
		boolean isReservation = false;
		
		String sql = "INSERT INTO ticket VALUES(null,?,?,?,?,?,?);";
		conn = DBUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUserID());
			pstmt.setString(2, vo.getMusical());
			pstmt.setString(3, vo.getSeatNum());
			pstmt.setInt(4, vo.getPay());
			pstmt.setString(5, vo.getDate());
			pstmt.setString(6, vo.getTime());
			
			int result = pstmt.executeUpdate();
			if(result == 1) {
				// 예약 정상 등록
				isReservation = true;
			}
		} catch (SQLException e) {
			isReservation = false;
		}finally {
			DBUtil.close(pstmt);
		}
		return isReservation;
	}

}
