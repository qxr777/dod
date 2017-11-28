package edu.whut.cs.oo.jmeter;

import edu.whut.cs.oo.domain.User;
import edu.whut.cs.oo.exception.BaseException;
import edu.whut.cs.oo.service.UserService;
import edu.whut.cs.oo.service.impl.UserServiceClient;

public class LoginTest extends BaseTest {

	@Override
	public void service() throws BaseException {
        UserService userService = new UserServiceClient();
        User user = userService.login("Jack", "123");
		sr.setResponseMessage(user.toString());
		sr.setSuccessful(true);  
		
	}

}
