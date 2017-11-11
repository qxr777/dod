package edu.whut.cs.oo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.whut.cs.oo.dao.DocumentDao;
import edu.whut.cs.oo.domain.Administrator;
import edu.whut.cs.oo.domain.Browser;
import edu.whut.cs.oo.domain.Document;
import edu.whut.cs.oo.domain.Operator;
import edu.whut.cs.oo.domain.User;
import edu.whut.cs.oo.exception.BaseException;
import edu.whut.cs.oo.exception.DaoException;

public class DocumentDaoJdbc extends BaseDaoJdbc implements DocumentDao {

	@Override
	public Document insert(Document object) throws BaseException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"insert into document_info (sn, name, timestamp, description, absolute_path, document_user_id) values (?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, object.getSn());
			pstmt.setString(2, object.getName());
			pstmt.setTimestamp(3, object.getTimestamp());
			pstmt.setString(4, object.getDescription());
			pstmt.setString(5, object.getAbsolutePath());
			pstmt.setLong(6, object.getUser().getId());
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
	public Document update(Document object) throws BaseException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"update document_info set sn=?,name=?,timestamp=?,description=?,absolute_path=?,document_user_id=? where document_id=?");
			pstmt.setString(1, object.getSn());
			pstmt.setString(2, object.getName());
			pstmt.setTimestamp(3, object.getTimestamp());
			pstmt.setString(4, object.getDescription());
			pstmt.setString(5, object.getAbsolutePath());
			pstmt.setLong(6, object.getUser().getId());
			pstmt.setLong(7, object.getId());
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
	public Document delete(Document object) throws BaseException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("delete from document_info where document_id=?");
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
	public Document findById(long id) throws BaseException {
		Connection conn = null;
		PreparedStatement pstmt = null, pstmt1 = null;
		ResultSet rs = null, rs1 = null;

		Document document = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from document_info where document_id=?");
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				document = new Document();
				document.setId(rs.getLong("document_id"));
				document.setSn(rs.getString("sn"));
				document.setName(rs.getString("name"));
				document.setTimestamp(rs.getTimestamp("timestamp"));
				document.setDescription(rs.getString("description"));
				document.setAbsolutePath(rs.getString("absolute_path"));

				pstmt1 = conn.prepareStatement("select * from user_info where user_id=?");
				pstmt1.setLong(1, rs.getLong("document_user_id"));
				rs1 = pstmt1.executeQuery();
				if (rs1.next()) {
					User user;
					String role = rs1.getString("role").toLowerCase();
					if (role.indexOf("administrator") > -1) {
						user = new Administrator();
					} else if (role.indexOf("operator") > -1) {
						user = new Operator();
					} else {
						user = new Browser();
					}
					user.setName(rs1.getString("username"));
					user.setPassword(rs1.getString("password"));
					user.setId(rs1.getLong("user_id"));
					document.setUser(user);
				}
			}
		} catch (Exception exception) {
			throw new DaoException();
		} finally {
			closeResultSet(rs);
			closeStatement(pstmt);
			closeResultSet(rs1);
			closeStatement(pstmt1);
			closeConnection(conn);
		}
		return document;
	}

	@Override
	public List<Document> findAllOnes() throws BaseException {
		List<Document> documents = new ArrayList<Document>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from document_info");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				long document_id = rs.getLong("document_id");
				Document document = this.findById(document_id);
				documents.add(document);
			}
			return documents;
		} catch (Exception exception) {
			throw new DaoException();
		} finally {
			closeResultSet(rs);
			closeStatement(pstmt);
			closeConnection(conn);
		}
	}

	@Override
	public Document findBySn(String sn) throws BaseException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Document document = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from document_info where sn=?");
			pstmt.setString(1, sn);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				long document_id = rs.getLong("document_id");
				document = this.findById(document_id);
			}
		} catch (Exception exception) {
			throw new DaoException();
		} finally {
			closeResultSet(rs);
			closeStatement(pstmt);
			closeConnection(conn);
		}
		return document;
	}

}
