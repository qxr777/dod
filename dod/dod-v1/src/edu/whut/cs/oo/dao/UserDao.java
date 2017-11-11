package edu.whut.cs.oo.dao;

import edu.whut.cs.oo.domain.User;
import edu.whut.cs.oo.exception.BaseException;

public interface UserDao extends BaseDao<User> {

	User findByName(String name) throws BaseException;
}
