package dao;

import java.util.List;

import vo.CastVO;
import vo.TicketVO;

/**
 * ticket table의 예약 정보를 Database와 연결하여 관리할 객체
 * Database Access Object
 */
public interface ReservationDAO {
	/**
	 * @return 예약완료된 좌석 목록
	 * @param 예약 목록을 확인할 날짜, 시간 정보
	 */
	List<TicketVO> listReservTicket(String date, String time);
	
	/**
	 * 예약완료 등록
	 * @param 예약 정보
	 * @return 예약 등록 성공 여부 true  - 성공 , false - 실패
	 */
	boolean reservationTicket(TicketVO vo);

	boolean reservationTicketCancel(TicketVO vo);

	List<TicketVO> getTicketInfoListString(String id); 
	
}
