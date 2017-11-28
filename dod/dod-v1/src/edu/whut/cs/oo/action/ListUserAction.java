package edu.whut.cs.oo.action;

import java.util.List;

import edu.whut.cs.oo.domain.User;
import edu.whut.cs.oo.exception.BaseException;

public class ListUserAction extends BaseAction {
	
	private List<User> users;
	
	public static final String LIST_USER_TEXT = "列出用户";
	
	private void input() {
    	System.out.println(LIST_USER_TEXT);
	}
	
	protected void output(List<User> users) {
		for (User user : users) {
			System.out.println(user);
		}
	}

	@Override
	public void execute() {
		input();
		try {
			users = userService.getAllUsers();
			output(users);
		} catch (BaseException e) {
			output(e.getMessage());
		}
	}

	@Override
	public String getText() {
		return LIST_USER_TEXT;
	}
	
}
