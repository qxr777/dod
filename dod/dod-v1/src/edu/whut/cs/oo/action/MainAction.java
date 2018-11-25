package edu.whut.cs.oo.action;

import java.util.List;

import edu.whut.cs.oo.Application;
import edu.whut.cs.oo.domain.Function;
import edu.whut.cs.oo.domain.User;
import edu.whut.cs.oo.exception.BaseException;
import edu.whut.cs.oo.exception.MenuException;
import edu.whut.cs.oo.exception.MenuIndexException;

public class MainAction extends BaseAction {
	
	public static final String MAIN_MENU_TEXT = "用户主菜单";
	
	private String menuIndex;
	
	private void input() throws MenuException, MenuIndexException {
		try {
			showFunctions(Application.currentUser);
			menuIndex = scanner.next();
			String menuPattern = getMenuPattern(Application.currentUser);
			if (!(menuIndex).matches(menuPattern)) {
				throw new MenuIndexException();
			} 			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new MenuException();
		}
	}
	
	private static void showFunctions(User user) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		String tip_menu = "["+user.getName()+"] 请选择菜单: ";
		System.out.println(tip_menu);
		if (user.getFunctions() == null || user.getFunctions().size() == 0)
			user.loadFunctions();
		List<Function> functions = user.getFunctions();
		for (int i = 0; i < functions.size(); i++) {
			System.out.println((i + 1 + ". ") + functions.get(i).getName());
		}
		System.out.println(functions.size() + 1 + ". 登出");
	}
	
	private static String getMenuPattern(User user) {
		String menuPattern = "1|";
		for (int i = 0; i < user.getFunctions().size(); i++) {
			menuPattern +=  i + 2 + "|";
		}
		return menuPattern.substring(0, menuPattern.length() - 1);
	}

	@Override
	public void execute() {
		try {
			while (true) {
				input();
				if (menuIndex.equals(Application.currentUser.getFunctions().size() + 1 + "")) {
					return;
				} else {
					int index = Integer.parseInt(menuIndex);
					Application.currentUser.getFunctions().get(index - 1).getAction().execute();
				}
			}
		} catch (BaseException e) {
			output(e.getMessage());
			return;
		}

	}

	@Override
	public String getText() {
		return MAIN_MENU_TEXT;
	}
	
}
