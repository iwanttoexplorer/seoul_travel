package com.pcwk.ehr.cmn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMaker implements PLog {
	final static String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	// jdbc:oracle:thin:@IP:PORT:전역DB명칭(SID)
	// "jdbc:oracle:thin:@192.168.0.30:1521:xe"
	final static String DB_URL = "jdbc:oracle:thin:@192.168.0.30:1521:xe";
	final static String DB_USER = "seoul_travel";
	final static String DB_PASSWORD = "journey";
	
	
	
	public ConnectionMaker() {
		log.debug("ConnectionMaker()");
	}


	/**
	 * DB연결 정보 생성
	 * 
	 * @return Connection
	 */
	public Connection getConnection() {
		Connection conn = null;
		log.debug("1");
		try {
			Class.forName(DB_DRIVER);
			log.debug("2");

			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			log.debug("3 conn: {}", conn);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}
	
}
