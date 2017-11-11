package edu.whut.cs.oo.controller;

import java.util.List;

import edu.whut.cs.oo.domain.User;
import edu.whut.cs.oo.exception.BaseException;

public class GetAllUsersController extends BaseController {

	@Override
	public void service() {
		try {
			List<User> users = userService.getAllUsers();
			message.setData(users);		
			onSuccess();
		} catch (BaseException e) {
			onException(e);
		}
		
	}

}
