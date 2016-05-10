

/***********************************************************************
    Aricent Technologies Proprietary

	This source code is the sole property of Aricent Technologies. Any form of utilization
	of this source code in whole or in part is  prohibited without  written consent from
	Aricent Technologies

		  File Name	                :
		  Principal Author      	:TH1_GR_02
		  Subsystem Name            :Auction_System
		  Module Name           	:
		  Date of First Release 	:Apr 10, 2016, 03:30:39 PM
		  Author					:TH1_GR_02
		  Description           	:


		  Change History

		  Version      		        :1.0
		  Date(DD/MM/YYYY)         	:10/04/2016
		  Modified by		        :TH1_GR_02
		  Description of change     :Initial version

	***********************************************************************/



	/**
	 * 
	 *	constructors are used,get and set method are used to set variables,clone method is used
	 *
	 *    
	 *	@see     
	 *
	 *	@see      
	 *	@version  1.0
	 *	@author   TH1_GR_02
	 *  @since    Apr 10, 2016, 03:30:39 PM
	 */



package com.aricent.auction.servlets;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aricent.auction.database.Database;

@WebServlet("/MaxBidServlet")
public class MaxBidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		 
	        Connection conn = null;
	        Statement stmt = null;
	        ResultSet result = null;
	        Database database=new Database();
	        
	        try
	        {
	         
	        	Class.forName("com.mysql.jdbc.Driver");
				//HttpSession session=request.getSession();
				conn=DriverManager.getConnection("jdbc:mysql://10.203.161.94:3306/auction_system","root","root");
	          stmt=conn.createStatement();
	          
	         
	          HttpSession session=request.getSession();
	          int prod_id=(int)session.getAttribute("p_id");
	          System.out.println("in max bid servlet");
	          result=stmt.executeQuery("select max(bid_price) from bidding_details where product_id="+prod_id);
	          while(result.next())
	          {
	           
	            request.setAttribute("price",result.getDouble("max(bid_price)"));
	          } 
	       
	        
	        } catch(Exception e)
	        {
	          System.out.println("db not conntd");
	          e.printStackTrace();
	        }

	        
	      }


		
		


	
	
}
