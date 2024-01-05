package ticket_server_main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;

import utils.DBUtil;

public class TicketServerMain {
	
	
	ServerSocket serverSocket;
	
	public TicketServerMain() {
		try {
			serverSocket = new ServerSocket(5001);
			Connection conn = DBUtil.getConnection();
			System.out.println(conn);
			startServer();
		} catch (IOException e) {
			stopServer();
			return;
		}
	}
	
	/**
	 * 할당 받은 포트번호로 Server 시작
	 */
	private void startServer() {
		while(true) {
			try {
				// blocking
				System.out.println("blocking");
				Socket client = serverSocket.accept();
				Client.clients.add(new Client(client));
				System.out.println(client);
			} catch (IOException e) {
				stopServer();
				break;
			}
		}
	}

	/**
	 * server 종료
	 */
	public void stopServer() {
		Client.threadPool.shutdownNow();
		if(serverSocket != null && !serverSocket.isClosed()) {
			try {
				serverSocket.close();
			} catch (IOException e) {}
		}
		
		if(Client.clients != null) {
			for(Client c : Client.clients) {
				try {
					if(c != null)c.client.close();
				} catch (IOException e) {}
			}
			Client.clients.clear();
		}
	}


	public static void main(String[] args) {
		new TicketServerMain();
	}

}
