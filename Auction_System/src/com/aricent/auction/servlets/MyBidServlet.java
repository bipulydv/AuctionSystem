

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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class MyBid
 */
@WebServlet("/MyBidServlet")
public class MyBidServlet extends HttpServlet
{
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rest=null;
		try
		{
			int value;
			Class.forName("com.mysql.jdbc.Driver");
	    	HttpSession session=req.getSession();
	    	
	    	conn=DriverManager.getConnection("jdbc:mysql://10.203.161.94:3306/auction_system","root","root");
	    	stmt=conn.createStatement();
	    	String emp_id=(String)session.getAttribute("Username");
	    	
	    	rest=stmt.executeQuery("select a.product_id,b.product_name,b.description,a.bid_price from bidding_details a,product_details b where a.product_id=b.product_id and employee_id="+emp_id);
	    	out.println("<html><link href=\"Details.css\" type=\"text/css\" rel=\"stylesheet\"><body class=\"bids\">");
	    	out.println("<p class=\"details\" text align=\"center\"> Your bids till now..!!</p>");
	    	out.println("<p class=\"back_button\"><button  type=\"button\" onclick=\"location.href=\'UserWelcome.jsp\'\">Back</button></p>");
	    	while(rest.next())
	    	{
	    		out.println("Product Id: "+rest.getInt("product_id")+"<br/>Product name: "+rest.getString("product_name")+"<br/>Product Description: "+rest.getString("description")+"<br/>Bid Price:"+rest.getDouble("bid_price"));
	    		out.println("<form action=\"WithdrawServlet\" method=\"get\"" );
	    		out.println("<br/><input type=\"hidden\" name=\"prod_id\" value=\""+rest.getInt("product_id")+"\"/>");
	    		out.println("<br/><input type=\"submit\" value=\"Withdraw\"/>");
	    		out.println("</form><br/><br/>");
	    	}
	    	out.println("<button type=\"button\" onclick=\"location.href=\'WithdrawAllServlet\'\">Withdraw All</button>");
	    	out.println("</body></html>");
		}
		catch(SQLException e)
		{
			e.getMessage();
		}
		catch(ClassNotFoundException e)
		{
			e.getMessage();
		}
	}
}
