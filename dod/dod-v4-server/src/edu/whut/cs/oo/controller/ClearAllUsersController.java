package edu.whut.cs.oo.controller;

import edu.whut.cs.oo.exception.BaseException;

public class ClearAllUsersController extends BaseController {

	@Override
	public void service() {
		try {
			userService.clear();
			onSuccess();
		} catch (BaseException e) {
			onException(e);
		}
		
	}

}
