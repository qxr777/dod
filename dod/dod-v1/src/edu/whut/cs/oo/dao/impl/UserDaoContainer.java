package edu.whut.cs.oo.dao.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import edu.whut.cs.oo.dao.UserDao;
import edu.whut.cs.oo.domain.User;

public class UserDaoContainer implements UserDao {
	
	private static Hashtable<String, User> users = new Hashtable<String, User>();

	@Override
	public User insert(User object) {
		return users.put(object.getName(), object);
	}

	@Override
	public User update(User object) {
		return users.put(object.getName(), object);
	}

	@Override
	public User delete(User object) {
		return users.remove(object.getName());
	}

	@Override
	public User findById(long id) {
		for (User user : users.values()) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	@Override
	public List<User> findAllOnes() {
		return new ArrayList<User>(users.values());
	}

	@Override
	public User findByName(String name) {
		return users.get(name);
	}

}
