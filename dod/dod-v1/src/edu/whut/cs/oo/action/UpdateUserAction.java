package edu.whut.cs.oo.action;

import java.util.Scanner;

import edu.whut.cs.oo.domain.Administrator;
import edu.whut.cs.oo.domain.Browser;
import edu.whut.cs.oo.domain.Operator;
import edu.whut.cs.oo.domain.User;

public class UpdateUserAction extends BaseAction {
	
	private User user;
	
	public static final String UPDATE_USER_TEXT = "修改用户";
	
	private void input() throws Exception {
		Scanner scanner = new Scanner(System.in);
    	System.out.println(UPDATE_USER_TEXT);
    	System.out.println("请输入用户名：");
        String name=scanner.next();
        System.out.println("请输入口令：");
        String password=scanner.next();
        System.out.println("请输入角色：");
        String role=scanner.next();
		
		switch (role.toLowerCase()) {
		case "administrator" :
			user = new Administrator();
			break;
		case "operator" :
			user = new Operator();
			break;
		default :
			user = new Browser();
		}
		
		user.setName(name);
		user.setPassword(password);
//		scanner.close();
	}
	
	protected void output(String message) {
		System.out.println(message);
	}

	@Override
	public void execute() {
		try {
			input();
			userService.updateUser(user);
			output(UPDATE_USER_TEXT + "成功！！！");
		} catch (Exception e) {
			output(e.getMessage());
		}
	}

	@Override
	public String getText() {
		return UPDATE_USER_TEXT;
	}
	
}
