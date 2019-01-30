package com.b2c.Helper.DataBase_Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;
import com.b2c.Helper.Logger_Helper.LoggerHelper;


public class DataBase {

	public static final Logger log = LoggerHelper.getLogger(DataBase.class.getName());
	public static Statement getStatement(String connString,String username,String password) throws SQLException, ClassNotFoundException
	{
		//currently server is local and databasename is cutomer
		//register the driver
		try {
			//Loading the required JDBC Driver class
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
                catch (ClassNotFoundException e) {
			log.info("MySQL JDBC driver not found.");
			//e.printStackTrace();
		}
		//set connection
		Connection con = null;
		try {
		 con = DriverManager.getConnection(connString,username,password);
		log.info("connection has been success.");
		}catch(Exception e)
		{
log.info("Connection has not been connected" + e);
		}
		//get Statement
		Statement stmt = con.createStatement();
		
		return stmt;
	}
	public void insertData(String query,String connString, String username, String password) throws ClassNotFoundException, SQLException
	{
		Statement st = getStatement(connString,username,password);
		st.executeUpdate(query);
		
	}
	
	public ResultSet updateData(String query,String connString, String username, String password) throws ClassNotFoundException, SQLException
	{
		Statement st = getStatement(connString,username,password);
		ResultSet rs = st.executeQuery(query);
		return rs;
	}
	
	
	
	public static String getData(String query, String columnname, String connString, String username, String password) throws ClassNotFoundException, SQLException
	{
		String data =  null;
		Statement st = getStatement(connString,username,password);
		log.info("getstatement result of statement" + st);
		ResultSet rs = st.executeQuery(query);
		log.info("Result data " + rs);
		while (rs.next())
        {
      	 data = rs.getString(columnname);
      	}
     return data;
		
	}
}
 