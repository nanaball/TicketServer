package ticket_server_main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dao.CastDAO;
import dao.CastDAOImpl;
import dao.MemberDAO;
import dao.MemberDAOImpl;
import dao.ReservationDAO;
import dao.ReservationDAOImpl;
import vo.CastVO;
import vo.MemberVO;
import vo.TicketVO;

public class Client {

	public static ExecutorService threadPool = Executors.newCachedThreadPool();

	public static List<Client> clients = new Vector<>();
	
	public static CastDAO castDAO = new CastDAOImpl();
	public static MemberDAO memberDAO = new MemberDAOImpl();
	public static ReservationDAO ticketDAO = new ReservationDAOImpl();

	public Socket client;

	// client 입력 스트림
	BufferedReader reader;

	// client 출력 스트림
	PrintWriter pw;

	public Client(Socket client) {
		this.client = client;
		try {
			reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			pw = new PrintWriter(new OutputStreamWriter(client.getOutputStream()),true);
		} catch (IOException e) {
			stopSocket();
		}
		receive();
	}

	private void stopSocket() {
		if(client != null && !client.isClosed()){
			try {
				client.close();
			} catch (IOException e) {}
		}
		clients.remove(this);
	}

	private void receive() {
		threadPool.execute(() -> {
			while (true) {
				try {
					String readMessage = reader.readLine();
					if(readMessage == null) {
						break;
					}
					System.out.println("server receive ..");
					// 메세지로 요청 처리
					// 회원, 예매 등등
					System.out.println(readMessage);
					System.out.println("------------------------------");
					String[] datas = readMessage.split("\\|");
					String order = datas[0];
					// first order| second order|data...
					// order == 0 회원 관련 요청 처리
					if(order.equals("0")) {
						if(datas[1].equals("0")) {
							// "0|0|"+txtId.getText().trim()+","+txtPw.getText().trim()
							// 0|0|id,pass ...
							// 로그인 관련 요청 처리에 대한 서버의 결과
							String[] strs = datas[2].split(",");
							String id = strs[0];
							String pw = strs[1];
							// id 패스워드가 일치하는 사용자 정보 DB에서 검색
							MemberVO member = memberDAO.login(id, pw);
							if(member == null) {
								// 로그인 실패
								// 0|0|0
								sendData("0|0|0");
							}else {
								// 로그인 성공
								// 0|0|1
								sendData("0|0|1|"+member.toString());
							}
						}else if(datas[1].equals("1")){
							// 0|1|data...
							// 회원가입 관련 요청 처리에 대한 서버의 결과
							// database 에 회원 정보 저장
							// 저장 여부에 따라 성공여부 출력
							String[] joins = datas[2].split(",");
							
							boolean isJoin = memberDAO.join(new MemberVO(joins[0],joins[1],joins[2],joins[3]));
							sendData("0|1|"+isJoin);
						}
						
						
					}else if(order.equals("1")) {
						// 1|data...
						// 예매 관련 요청 처리에 대한 서버의 결과
						// 1|musicalName,date,time
						//       0        1     2
						// [musicalName][date][time] 
						String[] reservData = datas[1].split(",");
						List<TicketVO> reservList = ticketDAO.listReservTicket(reservData[0],reservData[1],reservData[2]);
						String sendSeatDatas = "1|";
						for(TicketVO vo : reservList) {
							sendSeatDatas += vo.getSeatNum()+",";
						}
						System.out.println(sendSeatDatas);
						sendSeatDatas = sendSeatDatas.substring(0,sendSeatDatas.length()-1);
						System.out.println(sendSeatDatas);
						sendData(sendSeatDatas);
					}else if(order.equals("2")) {
						// 2|0|data...
						// 2|0|33,레베카,E-8,100000,2024-01-11,18:00:00
						// 결제 관련 요청 처리에 대한 서버의 결과
						if(datas[1].equals("0")) {
							String[] ticket = datas[2].split(",");
							//  0   1      2  ...
							// [33][레베카][E-8][100000][2024-01-11][18:00:00]
							TicketVO vo = new TicketVO();
							vo.setUserID(ticket[0]);
							vo.setMusical(ticket[1]);
							vo.setSeatNum(ticket[2]);
							vo.setPay(Integer.parseInt(ticket[3]));
							vo.setDate(ticket[4]);
							vo.setTime(ticket[5]);
							System.out.println(vo);
							boolean isReservation = ticketDAO.reservationTicket(vo);
							sendData("2|0|"+isReservation);
						} else if(datas[1].equals("1")) {
							// 2|1|data...
							// 결제 관련 완료 요청 처리에 대한 서버의 결과
							// payDone
							String[] ticket = datas[2].split(",");
							TicketVO vo = new TicketVO();
							vo.setUserID(ticket[0]);
							vo.setMusical(ticket[1]);
							vo.setSeatNum(ticket[2]);
							vo.setPay(Integer.parseInt(ticket[3]));
							vo.setDate(ticket[4]);
							vo.setTime(ticket[5]);
							System.out.println(vo);
							boolean isReservation = ticketDAO.reservationTicket(vo);
							sendData("2|1|"+isReservation);
							
						}else if(datas[1].equals("2")){
							// payCheck
							String[] ticket = datas[2].split(",");
							TicketVO vo = new TicketVO();
							vo.setUserID(ticket[0]);
							vo.setMusical(ticket[1]);
							vo.setSeatNum(ticket[2]);
							vo.setPay(Integer.parseInt(ticket[3]));
							vo.setDate(ticket[4]);
							vo.setTime(ticket[5]);
							System.out.println(vo);
							boolean isReservation = ticketDAO.reservationTicketCancel(vo);
							sendData("2|2|"+isReservation);
						}else {
							// reserveCheck
							// 2|3
							List<TicketVO> list = TicketVO.getTicketInfoListString(datas[1]);
							// CastVO == row
							// rows == CastVO...
							// 3|0|0| ~~~~~~~ 뮤지컬 목록 정보가 있음 나열에서 출력
							// 3|0|1| ~~~~~~~ 뮤지컬 목록 없음.
							String result = "2|3|1";
							String[] ticket = datas[2].split(",");
							TicketVO vo = new TicketVO();
							vo.setUserID(ticket[0]);
							vo.setMusical(ticket[1]);
							vo.setSeatNum(ticket[2]);
							vo.setPay(Integer.parseInt(ticket[3]));
							vo.setDate(ticket[4]);
							vo.setTime(ticket[5]);
							System.out.println(vo);
							boolean isReservation = ticketDAO.reservationTicket(vo);
							sendData("2|3|"+isReservation);
						}
					}else if(order.equals("3")) {
						// 3|data...
						// 포스트 관련 요청 처리에 대한 서버의 결과
						String secondOrder = datas[1];
						// 3|0| == 날짜별 뮤지컬 목록 조회
						if(secondOrder.equals("0")) {
							// 해당 날짜로 저장된 뮤지컬 목록 정보를 DB에서 검색
							List<CastVO> list = castDAO.getCastInfoListString(datas[2]);
							// CastVO == row
							// rows == CastVO...
							// 3|0|0| ~~~~~~~ 뮤지컬 목록 정보가 있음 나열에서 출력
							// 3|0|1| ~~~~~~~ 뮤지컬 목록 없음.
							String result = "3|0|0|";
							// 3|0|0|1,!2024-01-11!레미제라블!19:00:00!손오공, 삼장법사, 저팔계, 사오정, 요괴!^2!2024-01-11!레미제라블!22:00:00!고길동, 둘리, 또치, 도우너, 마이콜, 희동!^... 
							if(!list.isEmpty()){
								for(CastVO vo : list) {
									result +=  vo.getId()+"!"+vo.getDate()+"!"+vo.getMusicalNa()+"!"+vo.getTime()+"!"+vo.getCasting()+"!"+"^";
								}
							}else {
								result = "3|0|1";
							}
							System.out.println("sendData : " + result);
							sendData(result);
						}
						
					}
				} catch (IOException e) {
					break;
				}
			}
			stopSocket();
		});
	} // end receive
	
	
	public void sendData(String text) {
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(client.getOutputStream()),true);
			pw.println(text);
		} catch (IOException e) {
			stopSocket();
		}
	}
	
}
