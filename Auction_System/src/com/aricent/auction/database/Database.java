

package com.aricent.auction.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author GUR44303
 *
 */
public class Database 
{
	 Connection con;
	
	public Connection createConnection()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con=DriverManager.getConnection("jdbc:mysql://10.203.161.94:3306/auction_system","root","root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
		
	} 
	
	
	public void endConnection()
	{
		
		con=null;
	}
	

}
