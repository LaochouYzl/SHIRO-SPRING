package cn.laochou.shiro.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;

import cn.laochou.shiro.dao.UserDao;
import cn.laochou.shiro.enums.UserEnum;
import cn.laochou.shiro.pojo.User;



public class ShiroRealm extends AuthenticatingRealm{
	
	private UserDao userDao = new UserDao();

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 1. 将AuthenticationToken 转为 UserNamePasswordToken
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
		String userName = usernamePasswordToken.getUsername();
		String password = new String(usernamePasswordToken.getPassword());
		User user = new User(userName, password);
		UserEnum userEnum = userDao.checkUser(user);
		if(userEnum.getStatus() == "EXISTS") {
			return new SimpleAuthenticationInfo(userName, password, getName());
		}else {
			throw new UnknownAccountException("不存在改用户");
		}
	}
	
	public static void main(String[] args) {
		System.out.println(new SimpleHash("MD5", "laochou", null, 2));
	}
	
}
