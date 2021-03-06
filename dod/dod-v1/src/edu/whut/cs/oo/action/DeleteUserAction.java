package edu.whut.cs.oo.action;

import edu.whut.cs.oo.exception.BaseException;

public class DeleteUserAction extends BaseAction {
	
	private String userName;
	
	public static final String DELETE_USER_TEXT = "删除用户";
	
	private void input() {
    	System.out.println(DELETE_USER_TEXT);
    	System.out.println("请输入用户名：");
    	userName = scanner.next();
	}
	
	@Override
	public void execute() {
		input();
		try {
			userService.deleteUser(userName);
			output(DELETE_USER_TEXT + "成功！！！");
		} catch (BaseException e) {
			output(e.getMessage());
		}
	}

	@Override
	public String getText() {
		return DELETE_USER_TEXT;
	}
	
}
