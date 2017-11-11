package edu.whut.cs.oo.service;

import java.util.List;

import edu.whut.cs.oo.dao.UserDao;
import edu.whut.cs.oo.domain.User;
import edu.whut.cs.oo.exception.BaseException;

public interface UserService {
	
	void setUserDao(UserDao userDao);
	
	User createUser(User user) throws BaseException;
	
	User deleteUser(String userName) throws BaseException;
	
	User updateUser(User user) throws BaseException;
	
	User changePassword(User user, String newPassword) throws BaseException;
	
	User getUser(String name) throws BaseException;
	
	List<User> getAllUsers() throws BaseException;
	
	User login(String username, String password) throws BaseException;
	
	void clear() throws BaseException;

}
