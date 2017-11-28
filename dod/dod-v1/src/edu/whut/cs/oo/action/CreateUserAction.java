package edu.whut.cs.oo.action;

import edu.whut.cs.oo.domain.Administrator;
import edu.whut.cs.oo.domain.Browser;
import edu.whut.cs.oo.domain.Operator;
import edu.whut.cs.oo.domain.User;

public class CreateUserAction extends BaseAction {
	
	private User user;
	
	public static final String CREATE_USER_TEXT = "新增用户";
	
	private void input() {
    	System.out.println(CREATE_USER_TEXT);
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
	
	@Override
	public void execute() {
		input();
		try {
			userService.createUser(user);
			output(CREATE_USER_TEXT + "成功！！！");
		} catch (Exception e) {
			output(e.getMessage());
		}
	}

	@Override
	public String getText() {
		return CREATE_USER_TEXT;
	}
	
}
