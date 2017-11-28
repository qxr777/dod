package edu.whut.cs.oo.action;

import edu.whut.cs.oo.domain.User;
import edu.whut.cs.oo.exception.BaseException;
import edu.whut.cs.oo.service.UserService;

public class LoginAction extends BaseAction{
	
	private String username;
	private String password;
	
	public static final String LOGIN_TEXT = "用户登录";
	
	private void input() {
		System.out.println(LOGIN_TEXT);
        System.out.print("请输入用户名：");		
        username=scanner.next();
        System.out.println("请输入口令：");	
        password=scanner.next();
	}
	
	public User login() {
		User user = null;
		input();
		try {
			user = userService.login(username, password);
		} catch (BaseException e) {
			output(e.getMessage());
		}
		return user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getText() {
		return LOGIN_TEXT;
	}

}
