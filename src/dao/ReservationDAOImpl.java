package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import utils.DBUtil;
import vo.TicketVO;

public class ReservationDAOImpl implements ReservationDAO {
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	// 1/10 수정
	@Override
	public List<TicketVO> listReservTicket(String musicalNa, String date, String time){
		String sql = "SELECT * FROM ticket WHERE musical=? AND date = ? AND time = ?";
		ArrayList<TicketVO> list = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, musicalNa);
			pstmt.setString(2, date);
			pstmt.setString(3, time);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				// int ticketNum, String userID, String musical, String seatNum, int pay, String date, String time
				TicketVO vo = new TicketVO(
					rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getInt(5),
					rs.getDate(6).toString(),
					rs.getTime(7).toString()
				);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(rs,pstmt);
		}
		return list;
	}
	
	// 로그인 했던 정보를 바탕으로 예매한 정보를 mysql에 입력
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
	
	// 로그인 했던 정보를 바탕으로 예매했던 정보를 mysql 에서 삭제
	@Override
	public boolean reservationTicketCancel(TicketVO vo) {
		
		boolean isReservationDelete = false;
		
		String sql = "DELETE FROM ticket WHERE userID='"+vo.getUserID()+"' AND musical='"+vo.getMusical()+"' AND seatNum='"+vo.getSeatNum()+"' AND Date='"+vo.getDate()+"' AND Time='" +vo.getTime()+"';" ;
		System.out.println(sql);
		
		conn = DBUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			
			int result = pstmt.executeUpdate();
			if(result == 1) {
				isReservationDelete = true;
			}
		} catch (SQLException e) {
			isReservationDelete = false;
		}finally {
			DBUtil.close(pstmt);
		}
		return isReservationDelete;
	}
// 1/10 추가	
	

	// 로그인한 정보에서 예매 내역 뽑아오기 
	@Override
	public List<TicketVO> getTicketInfoListString(String userID) {
		
		List<TicketVO> list = new ArrayList<>();
		conn = DBUtil.getConnection();
		String sql = "SELECT * FROM ticket WHERE userID = ?";
				
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int num = rs.getInt(1);
				String id = rs.getString(2);
				System.out.println(id.toString());				
				String m1 = rs.getString(3);
				System.out.println(m1.toString());
				String s1 = rs.getString(4);
				System.out.println(s1.toString());
				int p1 = rs.getInt(5);
				System.out.println(p1);				
				Date d1 = rs.getDate(6);
				System.out.println(d1.toString());
				Time t1 = rs.getTime(7);
				System.out.println(t1.toString());
								
				TicketVO ck = new TicketVO(num, id.toString(), m1.toString(), s1.toString(), p1, d1.toString(), t1.toString());
				list.add(ck);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(rs,pstmt);
		}
		return list;
	}
}
