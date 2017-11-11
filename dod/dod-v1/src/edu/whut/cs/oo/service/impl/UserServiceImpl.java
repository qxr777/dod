package edu.whut.cs.oo.service.impl;

import java.util.List;

import edu.whut.cs.oo.dao.UserDao;
import edu.whut.cs.oo.domain.User;
import edu.whut.cs.oo.exception.BaseException;
import edu.whut.cs.oo.exception.HaveExistException;
import edu.whut.cs.oo.exception.NoObjectException;
import edu.whut.cs.oo.exception.PasswordEqualException;
import edu.whut.cs.oo.exception.PasswordErrorException;
import edu.whut.cs.oo.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public User createUser(User user) throws BaseException {
		if (userDao.findByName(user.getName()) != null) {
			throw new HaveExistException(user.getName());
		}
		return userDao.insert(user);
	}
	
	public User deleteUser(String userName) throws BaseException {
		User user = userDao.findByName(userName);
		if (user == null) {
			throw new NoObjectException(userName);
		}
		return userDao.delete(user);
	}
	
	public User updateUser(User user) throws BaseException {
		if (userDao.findByName(user.getName()) == null) {
			throw new NoObjectException(user.getName());
		}
		return userDao.update(user);
	}
	
	public User changePassword(User user, String newPassword) throws BaseException {
		if (user == null || userDao.findByName(user.getName()) == null) {
			throw new NoObjectException(user.getName());
		}		
		if (user.getPassword().equals(newPassword)) {
			throw new PasswordEqualException();
		}
		user.setPassword(newPassword);
		return userDao.update(user);
	}
	
	public User getUser(String name) throws BaseException {
		return userDao.findByName(name);
	}
	
	public List<User> getAllUsers() throws BaseException {
		return userDao.findAllOnes();
	}
	
	public User login(String username, String password) throws BaseException {
		User user = userDao.findByName(username);
		if (user == null) {
			throw new NoObjectException(username);
		} else if (!user.getPassword().equals(password)) {
			throw new PasswordErrorException();
		}
		return user;
	}

	@Override
	public void clear() throws BaseException {
		List<User> users = userDao.findAllOnes();
		for (User user : users) {
			userDao.delete(user);
		}
	}

}
