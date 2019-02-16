package cn.laochou.shiro.handlers;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shiro")
public class ShiroHandler {
	
	@RequestMapping("/login")
	public String login(@RequestParam("username")String userName, @RequestParam("password")String password) {
		Subject currentUser = SecurityUtils.getSubject();
		if(!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
			token.setRememberMe(true);
            try {
                currentUser.login(token);
            }catch (AuthenticationException ae) {
                System.out.println("µÇÂ½Ê§°Ü"+ae.getMessage());
            }
			
		}
		return "redirect:/Index.jsp";
	}
	

}
