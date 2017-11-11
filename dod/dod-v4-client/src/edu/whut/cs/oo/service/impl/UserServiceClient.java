package edu.whut.cs.oo.service.impl;

import java.util.List;

import edu.whut.cs.oo.common.Constants;
import edu.whut.cs.oo.common.Message;
import edu.whut.cs.oo.dao.UserDao;
import edu.whut.cs.oo.domain.User;
import edu.whut.cs.oo.exception.BaseException;
import edu.whut.cs.oo.service.UserService;

public class UserServiceClient extends BaseServiceClient implements UserService {
	
	public User createUser(User user) throws BaseException {
		Message message = new Message();
		message.setController(Constants.CREATE_USER_CONTROLLER);
		message.setParameter("user", user);
		message = send(message);
		user = (User)message.getData();
		return user;
	}
	
	public User deleteUser(String userName) throws BaseException {
		Message message = new Message();
		message.setController(Constants.DELETE_USER_CONTROLLER);
		message.setParameter("username", userName);
		message = send(message);
		User user = (User)message.getData();
		return user;
	}
	
	public User updateUser(User user) throws BaseException {
		Message message = new Message();
		message.setController(Constants.UPDATE_USER_CONTROLLER);
		message.setParameter("user", user);
		message = send(message);
		user = (User)message.getData();
		return user;
	}
	
	public User changePassword(User user, String newPassword) throws BaseException {
		Message message = new Message();
		message.setController(Constants.CHANGE_PASSWORD_CONTROLLER);
		message.setParameter("username", user.getName());
		message.setParameter("newPassword", newPassword);
		message = send(message);
		user = (User)message.getData();
		return user;
	}
	
	public User getUser(String name) throws BaseException {
		Message message = new Message();
		message.setController(Constants.GET_USER_CONTROLLER);
		message.setParameter("username", name);
		message = send(message);
		User user = (User)message.getData();
		return user;
	}
	
	public List<User> getAllUsers() throws BaseException {
		Message message = new Message();
		message.setController(Constants.GET_ALL_USERS_CONTROLLER);
		message = send(message);
		List<User> users = (List<User>)message.getData();
		return users;
	}
	
	public User login(String username, String password) throws BaseException {
		Message message = new Message();
		message.setController(Constants.LOGIN_CONTROLLER);
		message.setParameter("username", username);
		message.setParameter("password", password);
		message = send(message);
		User user = (User)message.getData();
		return user;
	}
	


	@Override
	public void clear() throws BaseException {
		Message message = new Message();
		message.setController(Constants.CLEAR_ALL_USERS_CONTROLLER);
		message = send(message);
	}

	@Override
	public void setUserDao(UserDao userDao) {
		
	}
	
}
