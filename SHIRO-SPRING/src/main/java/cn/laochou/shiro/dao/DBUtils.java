package cn.laochou.shiro.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.cj.jdbc.ClientPreparedStatement;
import com.mysql.cj.jdbc.ConnectionImpl;

public class DBUtils {
	
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	
	static {
		Properties properties = new Properties();
		InputStream in = DBUtils.class.getResourceAsStream("db.properties");
		try {
			properties.load(in);
			driver = properties.getProperty("jdbc.driver");
			url = properties.getProperty("jdbc.url");
			username = properties.getProperty("jdbc.username");
			password = properties.getProperty("jdbc.password");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static ConnectionImpl getConnectionImpl() {
		try {
			// 加载驱动
			Class.forName(driver);
			ConnectionImpl conn = (ConnectionImpl)DriverManager.getConnection(url, username, password);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 对于没有结果的进行关闭资源
	 * @param conn
	 * @param ps
	 */
	public static void close(ConnectionImpl conn, ClientPreparedStatement ps) {
		try {
			if(ps != null) {
				ps.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ConnectionImpl conn, ClientPreparedStatement ps, ResultSet set) {
		try {
			if(set != null) {
				set.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
