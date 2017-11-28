package edu.whut.cs.oo.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.whut.cs.oo.common.Constants;
import edu.whut.cs.oo.exception.DaoException;

public abstract class BaseDaoJdbc
{
	private String driver = Constants.DB_DRIVER;
	
    private String sConnStr = Constants.DB_CONNECTION;

    private String user = Constants.DB_USER;

    private String password = Constants.DB_PASSWORD;

    protected Connection getConnection() throws SQLException, DaoException
    {
    	try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DaoException();
		}
        return DriverManager.getConnection(sConnStr, user, password);
    }
    
    protected void closeConnection(Connection conn) throws DaoException
    {
        if (conn != null)
        {
            try
            {
                conn.close();
            }
            catch (SQLException e)
            {
            	throw new DaoException();
            }
            conn = null;
        }
    }

    protected void closeResultSet(ResultSet rs) throws DaoException
    {
        if (rs != null)
        {
            try
            {
                rs.close();
            }
            catch (SQLException e)
            {
            	throw new DaoException();
            }
            rs = null;
        }
    }

    protected void closeStatement(Statement st) throws DaoException
    {
        if (st != null)
        {
            try
            {
                st.close();
            }
            catch (SQLException e)
            {
            	throw new DaoException();
            }
            st = null;
        }
    }
}