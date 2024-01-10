package vo;

import java.util.List;

public class TicketVO {
	
	private int ticketNum;
	private String userID;
	private String musical;
	private String seatNum;
	private int pay;
	private String date;
	private String time;
	
	public TicketVO() {
		super();
	}
	
	public TicketVO(int ticketNum, String userID, String musical, String seatNum, int pay, String date, String time) {
		this.ticketNum = ticketNum;
		this.userID = userID;
		this.musical = musical;
		this.seatNum = seatNum;
		this.pay = pay;
		this.date = date;
		this.time = time;
	}

	public int getTicketNum() {
		return ticketNum;
	}

	public void setTicketNum(int ticketNum) {
		this.ticketNum = ticketNum;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getMusical() {
		return musical;
	}

	public void setMusical(String musical) {
		this.musical = musical;
	}

	public String getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}

	public int getPay() {
		return pay;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "TicketVO [ticketNum=" + ticketNum + ", userID=" + userID + ", musical=" + musical + ", seatNum="
				+ seatNum + ", pay=" + pay + ", date=" + date + ", time=" + time + "]";
	}

	public static List<TicketVO> getTicketInfoListString(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
