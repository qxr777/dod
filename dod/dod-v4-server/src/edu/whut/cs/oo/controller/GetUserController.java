package edu.whut.cs.oo.controller;

import edu.whut.cs.oo.domain.User;
import edu.whut.cs.oo.exception.BaseException;

public class GetUserController extends BaseController {

	@Override
	public void service() {
		String username = (String)message.getParameter("username");
		User user;
		try {
			user = userService.getUser(username);
			message.setData(user);		
			onSuccess();
		} catch (BaseException e) {
			onException(e);
		}
		
	}

}
