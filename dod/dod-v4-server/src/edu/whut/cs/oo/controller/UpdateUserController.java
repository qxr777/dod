package edu.whut.cs.oo.controller;

import edu.whut.cs.oo.domain.User;
import edu.whut.cs.oo.exception.BaseException;

public class UpdateUserController extends BaseController {

	@Override
	public void service() {
		User user = (User)message.getParameter("user");
		try {
			user = userService.updateUser(user);
			message.setData(user);		
			onSuccess();
		} catch (BaseException e) {
			onException(e);
		}
		
	}

}
