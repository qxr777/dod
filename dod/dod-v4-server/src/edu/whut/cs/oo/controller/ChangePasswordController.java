package edu.whut.cs.oo.controller;

import edu.whut.cs.oo.domain.User;
import edu.whut.cs.oo.exception.BaseException;

public class ChangePasswordController extends BaseController {

	@Override
	public void service() {
		String username = (String)message.getParameter("username");
		String newPassword = (String)message.getParameter("newPassword");
		User user;
		try {
			user = userService.getUser(username);
			user = userService.changePassword(user, newPassword);
			message.setData(user);		
			onSuccess();
		} catch (BaseException e) {
			onException(e);
		}
		
	}

}
