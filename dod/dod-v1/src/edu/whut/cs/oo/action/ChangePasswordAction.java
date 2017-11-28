package edu.whut.cs.oo.action;

import edu.whut.cs.oo.Application;
import edu.whut.cs.oo.exception.BaseException;

public class ChangePasswordAction extends BaseAction {
	
	private String newPassword;
	
	public static final String CHANGE_PASSWORD_TEXT = "修改本人密码";
	
	private void input() {
    	System.out.println(CHANGE_PASSWORD_TEXT);
        System.out.println("请输入口令：");
        newPassword=scanner.next();
	}

	@Override
	public void execute() {
		input();
		try {
			userService.changePassword(Application.currentUser, newPassword);
			output(CHANGE_PASSWORD_TEXT + "成功！！！");
		} catch (BaseException e) {
			output(e.getMessage());
		}
	}

	@Override
	public String getText() {
		return CHANGE_PASSWORD_TEXT;
	}
	
}
