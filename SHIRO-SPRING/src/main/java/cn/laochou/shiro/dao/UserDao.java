package cn.laochou.shiro.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.ClientPreparedStatement;
import com.mysql.cj.jdbc.ConnectionImpl;

import cn.laochou.shiro.enums.UserEnum;
import cn.laochou.shiro.pojo.User;

public class UserDao {
	
	
	public UserEnum checkUser(User user) {
		try {
			ConnectionImpl conn  = DBUtils.getConnectionImpl();
			ClientPreparedStatement ps = (ClientPreparedStatement) conn.clientPrepareStatement("select * from user where username = ? and password = ?");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ResultSet set = ps.executeQuery();
			if(set.next()) {
				System.out.println("”√ªß¥Ê‘⁄");
				return UserEnum.EXISTS;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return UserEnum.NONEXISTS;
	}
	
	public static void main(String[] args) throws SQLException {
		ConnectionImpl conn  = DBUtils.getConnectionImpl();
		ClientPreparedStatement ps = (ClientPreparedStatement) conn.clientPrepareStatement("select * from user");
		ResultSet set = ps.executeQuery();
		while(set.next()) {
			String username = set.getString("username");
			String password = set.getString("password");
			System.out.println(username + " : " +password);
		}
		DBUtils.close(conn, ps, set);
	}

}
