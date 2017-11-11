package edu.whut.cs.oo.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.whut.cs.oo.common.Constants;

public abstract class BaseDaoJdbc
{
	private String driver = Constants.DB_DRIVER;
	
    private String sConnStr = Constants.DB_CONNECTION;

    private String user = Constants.DB_USER;

    private String password = Constants.DB_PASSWORD;

    protected Connection getConnection() throws SQLException
    {
    	try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        return DriverManager.getConnection(sConnStr, user, password);
    }
    
    protected void closeConnection(Connection conn)
    {
        if (conn != null)
        {
            try
            {
                conn.close();
            }
            catch (SQLException e)
            {
            }
            conn = null;
        }
    }

    protected void closeResultSet(ResultSet rs)
    {
        if (rs != null)
        {
            try
            {
                rs.close();
            }
            catch (SQLException e)
            {
            }
            rs = null;
        }
    }

    protected void closeStatement(Statement st)
    {
        if (st != null)
        {
            try
            {
                st.close();
            }
            catch (SQLException e)
            {
            }
            st = null;
        }
    }
}