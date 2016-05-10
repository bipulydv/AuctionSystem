/***********************************************************************
                         Aricent Technologies Proprietary

This source code is the sole property of Aricent Technologies. Any form of utilization
of this source code in whole or in part is  prohibited without  written consent from
Aricent Technologies

	  File Name	                :Database.java
	  Principal Author      	:Sachin Mittal
	  Subsystem Name            :Training
	  Module Name           	:Training
	  Date of First Release 	:Apr 6, 2016 3:24:12 PM
	  Author			        :Sachin Mittal 
	  Description           	:Read the contents of a file line by line.


	  Change History

	  Version      		        :1.0
	  Date(DD/MM/YYYY)         	:Apr 6, 2016
	  Modified by		        :Sachin Mittal
	  Description of change     :Initial version

***********************************************************************/
/**
 * 
 *	Read the contents of a file line by line.
 *	@see      Database.java
 *	@see      main()
 *	@version  1.0
 *	@author   GUR44303
 *  @since    Apr 6, 2016 3:24:12 PM
 */

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
