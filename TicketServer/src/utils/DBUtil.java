package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	
	private static Connection conn;
	
	public static Connection getConnection() {
		// conn field에서 참조하는 객체 - 값이 없으면 Connection 객체 생성
		if(conn == null) {
			try {
				File file = new File("prop/mysql.properties");
				System.out.println(file.getAbsolutePath());
				Properties prop = new Properties();
				prop.load(new FileReader(file));
				
				String driver = prop.getProperty("driver");
				String url = prop.getProperty("url");
				
				Class.forName(driver);
				conn = DriverManager.getConnection(url,prop);
				
			} catch (FileNotFoundException e) {
				System.out.println("properties 파일을 찾을 수 없음");
			} catch (IOException e) {
				System.out.println("properties 파일을 읽을 수 없음");
			} catch (ClassNotFoundException e) {
				System.out.println("Driver class를 찾을 수 없음");
			} catch (SQLException e) {
				System.out.println("DB 연결 url user password 오류");
			}
		}
		return conn;
	}

	public static void close(AutoCloseable... closer) {
		for(AutoCloseable c : closer) {
			if(c != null) {
				try {
					c.close();
				} catch (Exception e) {}
			}
		}
	}
	
}






















