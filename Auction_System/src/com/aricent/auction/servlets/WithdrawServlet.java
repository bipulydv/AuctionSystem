

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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aricent.auction.database.Database;
import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class WithdrawServlet
 */
@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
	response.setContentType("text/html");
	String str=request.getParameter("prod_id");
	HttpSession session=request.getSession();
	String empId=(String) session.getAttribute("Username");
	
Database database=new Database();

	PreparedStatement stmt = null;
	Connection conn = null;
	Statement stmnt=null;
	ResultSet res=null;
	// Set response content type
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	  
	try {
		

		// Open a connection
		

	conn=(Connection) database.createConnection();
		
		String msg="New bid price for Product id:"+str+" is ";
		
		stmt= conn.prepareStatement("delete from bidding_details where employee_id=(?) and product_id=(?)");
		stmt.setString(1, empId);
		stmt.setString(2,str);
		stmt.executeUpdate();
		stmnt=conn.createStatement();	
		res=stmnt.executeQuery("Select max(bid_price) from bidding_details where product_id="+str);
		if(res.next())
		stmnt.executeUpdate("Update notification set message='"+msg+res.getDouble("max(bid_price)")+"' where product_id="+str);
		out.println("<script type=\"text/javascript\">");
		out.println("alert('You have successfully withdrawn your bid')");
		out.println("</script>");
		RequestDispatcher rd=request.getRequestDispatcher("/UserWelcome.jsp");
		rd.include(request, response);
	} catch (Exception e) {
		out.print("exception");
	}

}


}
