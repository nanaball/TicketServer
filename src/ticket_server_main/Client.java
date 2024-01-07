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

public class Client {

	public static ExecutorService threadPool = Executors.newCachedThreadPool();

	public static List<Client> clients = new Vector<>();

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
							// 0|0|id,pass ...
							// 로그인 관련 요청 처리에 대한 서버의 결과
							String[] strs = datas[2].split(",");
							String id = strs[0];
							String pw = strs[1];
							// id 패스워드가 일치하는 사용자 정보 DB에서 검색
							sendData("0|0|id,pw,phone,name");
						}else {
							// 0|1|data...
							// 회원가입 관련 요청 처리에 대한 서버의 결과
							// database 에 회원 정보 저장
							// 저장 여부에 따라 성공여부 출력
							sendData("0|1|true");
						}
					}else if(order.equals("1")) {
						// 1|data...
						// 예매 관련 요청 처리에 대한 서버의 결과
					}else if(order.equals("2")) {
						// 2|0|data...
						// 결제 관련 요청 처리에 대한 서버의 결과
						if(datas[1].equals("0")) {
						} else {
							// 2|1|data...
							// 결제 관련 완료 요청 처리에 대한 서버의 결과
						}
					}else if(order.equals("3")) {
						// 3|data...
						// 포스트 관련 요청 처리에 대한 서버의 결과
						
						CastDAO castDAO = new CastDAOImpl();
						String s = castDAO.getCastInfoListString("3|" + datas[1]);
						sendData(s);
						
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
