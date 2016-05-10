

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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aricent.auction.database.Database;


@WebServlet("/NotificationServlet")
public class NotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//Database database=new Database();
	Connection conn = null;
    Statement stmt = null;
    Statement stmt1 = null;
    ResultSet result = null;
    ResultSet result1 = null;
    Database database=new Database();
    PrintWriter out=response.getWriter();
    try
    {
     
      conn=database.createConnection();
     
      stmt=conn.createStatement();
      stmt1=conn.createStatement();
     System.out.println("connected");
      HttpSession session=request.getSession();
      String emp_id=(String)session.getAttribute("Username");
      System.out.println(emp_id);
      result=stmt.executeQuery("select product_id from bidding_details where employee_id="+emp_id);
   
      out.println("<html><link href=\"Details.css\" type=\"text/css\" rel=\"stylesheet\"><body class=\"bids\">");
     	out.println("<p  class=\"back_button\" ><button type=\"button\" onclick=\"location.href=\'UserWelcome.jsp\'\">Back</button></p>");
      while(result.next())
      {
    	  int prod_id=result.getInt("product_id");
    	  System.out.println(prod_id);
       result1=stmt1.executeQuery("select message from notification where product_id="+prod_id);
    
       if(result1.next())
       {
    	   out.println("<br>");
    	   out.println(result1.getString("message"));
    	   out.println("<br>");
       }
      } 
      out.println("</body></html>");
   
    
    } catch(SQLException e)
    {
      System.out.println("db not conntd");
      e.printStackTrace();
    }

	
}
	}

	
	

