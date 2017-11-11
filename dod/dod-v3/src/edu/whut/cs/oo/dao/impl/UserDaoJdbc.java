package edu.whut.cs.oo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.whut.cs.oo.dao.UserDao;
import edu.whut.cs.oo.domain.Administrator;
import edu.whut.cs.oo.domain.Browser;
import edu.whut.cs.oo.domain.Operator;
import edu.whut.cs.oo.domain.User;
import edu.whut.cs.oo.exception.BaseException;
import edu.whut.cs.oo.exception.DaoException;

public class UserDaoJdbc extends BaseDaoJdbc implements UserDao {

	@Override
	public User insert(User object) throws BaseException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into user_info (username, password, role) values (?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, object.getName());
			pstmt.setString(2, object.getPassword());
			pstmt.setString(3, object.getClass().getName());
			int rt = pstmt.executeUpdate();
			if (rt > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					object.setId(rs.getInt(1));
				}
			}
			return object;
		} catch (SQLException exception) {
			throw new DaoException();
		} finally {
			closeResultSet(rs);
			closeStatement(pstmt);
			closeConnection(conn);
		}
	}

	@Override
	public User update(User object) throws DaoException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("update user_info set username=?,password=?,role=? where username=?");
			pstmt.setString(1, object.getName());
			pstmt.setString(2, object.getPassword());
			pstmt.setString(3, object.getClass().getName());
			pstmt.setString(4, object.getName());
			int rt = pstmt.executeUpdate();
			if (rt > 0) {
				return object;
			} else {
				return null;
			}
		} catch (SQLException exception) {
			throw new DaoException();
		} finally {
			closeStatement(pstmt);
			closeConnection(conn);
		}
	}

	@Override
	public User delete(User object) throws BaseException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("delete from user_info where user_id=?");
			pstmt.setLong(1, object.getId());
			int rt = pstmt.executeUpdate();
			if (rt > 0) {
				return object;
			} else {
				return null;
			}
		} catch (SQLException exception) {
			throw new DaoException();
		} finally {
			closeStatement(pstmt);
			closeConnection(conn);
		}
	}

	@Override
	public User findById(long id) throws BaseException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		User user = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from user_info where user_id=?");
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				String role = rs.getString("role").toLowerCase();
				if (role.indexOf("administrator") > -1) {
					user = new Administrator();
				} else if (role.indexOf("operator") > -1) {
					user = new Operator();
				} else {
					user = new Browser();
				}
				user.setName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setId(id);
			}
		} catch (Exception exception) {
			throw new DaoException();
		} finally {
			closeResultSet(rs);
			closeStatement(pstmt);
			closeConnection(conn);
		}
		return user;
	}

	@Override
	public List<User> findAllOnes() throws BaseException {
		List<User> users = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from user_info");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				User user;
				String role = rs.getString("role").toLowerCase();
				if (role.indexOf("administrator") > -1) {
					user = new Administrator();
				} else if (role.indexOf("operator") > -1) {
					user = new Operator();
				} else {
					user = new Browser();
				}
				user.setId(rs.getLong("user_id"));
				user.setName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				users.add(user);
			}
			return users;
		} catch (Exception exception) {
			throw new DaoException();
		} finally {
			closeResultSet(rs);
			closeStatement(pstmt);
			closeConnection(conn);
		}
	}

	@Override
	public User findByName(String name) throws BaseException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		User user = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from user_info where username=?");
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				String role = rs.getString("role").toLowerCase();
				if (role.indexOf("administrator") > -1) {
					user = new Administrator();
				} else if (role.indexOf("operator") > -1) {
					user = new Operator();
				} else {
					user = new Browser();
				}
				user.setName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setId(rs.getLong("user_id"));
			}
		} catch (Exception exception) {
			throw new DaoException();
		} finally {
			closeResultSet(rs);
			closeStatement(pstmt);
			closeConnection(conn);
		}
		return user;
	}

}
