package edu.whut.cs.oo;

import java.util.List;
import java.util.Scanner;

import edu.whut.cs.oo.action.LoginAction;
import edu.whut.cs.oo.domain.Function;
import edu.whut.cs.oo.domain.User;

public class ConsoleApplication extends Application {
	
	public static void main(String[] args) throws Exception {

		LoginAction loginAction = new LoginAction();

		while (true) {
			User user = loginAction.login();
			Application.currentUser = user;
			if (user != null) {
				Scanner scanner = new Scanner(System.in);
				String input = null;
				while (true) {
					showFunctions(user);
					input = scanner.next();
					String menuPattern = getMenuPattern(user);
					if (!(input).matches(menuPattern)) {
						System.out.println("请输入有效的菜单编号！");
					} else if (input.equals(user.getFunctions().size() + 1 + "")) {
						break;
					} else {
						int index = Integer.parseInt(input);
						user.getFunctions().get(index - 1).getAction().execute();
					}
				}
			}
		}
	}
	
	private static String getMenuPattern(User user) {
		String menuPattern = "1|";
		for (int i = 0; i < user.getFunctions().size(); i++) {
			menuPattern +=  i + 2 + "|";
		}
		return menuPattern.substring(0, menuPattern.length() - 1);
	}
	
	private static void showFunctions(User user) throws Exception {
		String tip_menu = "["+user.getName()+"] 请选择菜单: ";
		System.out.println(tip_menu);
		if (user.getFunctions().size() == 0)
			user.loadFunctions();
		List<Function> functions = user.getFunctions();
		for (int i = 0; i < functions.size(); i++) {
			System.out.println((i + 1 + ". ") + functions.get(i).getName());
		}
		System.out.println(functions.size() + 1 + ". 登出");
	}
	
}
