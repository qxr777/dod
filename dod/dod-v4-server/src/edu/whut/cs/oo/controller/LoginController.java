package edu.whut.cs.oo.controller;

import edu.whut.cs.oo.domain.User;
import edu.whut.cs.oo.exception.BaseException;

public class LoginController extends BaseController {

	@Override
	public void service() {
		String username = (String)message.getParameter("username");
		String password = (String)message.getParameter("password");
		User user;
		try {
			user = userService.login(username, password);
			message.setData(user);		
			onSuccess();
		} catch (BaseException e) {
			onException(e);
		}
	}

}
