package com.aricent.auction.timer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.aricent.auction.database.Database;

public class Timer implements Runnable{
	public static Timer en;

	private Connection conn;
	private Statement stmt1;
	private Statement stmt2;
	
	Thread thread;

	
	private Timer() throws SQLException,ClassNotFoundException
	{
		
		

	
		Database database=new Database();
		


		try {
			
			
			

			conn = database.createConnection();
		
		stmt1 = conn.createStatement();
		stmt2 = conn.createStatement();
		thread = new Thread(this);
		thread.setName("Timer");
		thread.start();
	
		}catch(Exception e){}
	}
	
	
	static public Timer getInstance() throws SQLException,ClassNotFoundException
	{
		if(null == en)
			en = new Timer();
		
		return en;
	}
	
	
	public void run()
	{
		System.out.println("timer started");
		ResultSet result;
		while(true)
		{
			try
			{
				result = stmt1.executeQuery("select product_id from product_details");
				while(result.next())
				{
						stmt2.execute("select computeExpiry(" + result.getInt("product_id") + ")");
				}
				Thread.sleep(1000);
			}
			catch(SQLException sql)
			{
					System.out.println(sql);
			}
			catch(InterruptedException ie)
			{
				System.out.println(ie);
			}
		}
	}
	
	

}


