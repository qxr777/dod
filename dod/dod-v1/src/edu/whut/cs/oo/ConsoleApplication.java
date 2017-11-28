package edu.whut.cs.oo;

import edu.whut.cs.oo.action.LoginAction;
import edu.whut.cs.oo.action.MainAction;
import edu.whut.cs.oo.domain.User;

/**
 * @author qixin
 *
 */
public class ConsoleApplication extends Application {
	
	public static void main(String[] args) throws Exception {

		LoginAction loginAction = new LoginAction();

		while (true) {
			User user = loginAction.login();
			Application.currentUser = user;
			if (user != null) {
					MainAction mainAction = new MainAction();
					mainAction.execute();
			}
		}
	}
	
}
