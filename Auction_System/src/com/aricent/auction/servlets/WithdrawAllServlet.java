

/***********************************************************************
    Aricent Technologies Proprietary

	This source code is the sole property of Aricent Technologies. Any form of utilization
	of this source code in whole or in part is  prohibited without  written consent from
	Aricent Technologies

		  File Name	                :WithdrawAllServlet
		  Principal Author      	:TH1_GR_02
		  Subsystem Name            :Auction_System
		  Module Name           	:AuctionEventManagement
		  Date of First Release 	:Apr 10, 2016, 03:30:39 PM
		  Author					:TH1_GR_02
		  Description           	:it withdrawsall bid of user


		  Change History

		  Version      		        :1.0
		  Date(DD/MM/YYYY)         	:10/04/2016
		  Modified by		        :TH1_GR_02
		  Description of change     :Initial version

	***********************************************************************/



	/**
	 * 
	 *	:it withdrawsall bid of user
	 *
	 *    
	 *	@see     WithdrawAllServlet.java
	 *
	 *	@see      doGet
	 *	@version  1.0
	 *	@author   TH1_GR_02
	 *  @since    Apr 10, 2016, 03:30:39 PM
	 */


package com.aricent.auction.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aricent.auction.database.Database;
import com.mysql.jdbc.Connection;

@WebServlet("/WithdrawAllServlet")
public class WithdrawAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Withdraw All bids of user
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		//takes product id from mybids
		String str=request.getParameter("prod_id");
		HttpSession session=request.getSession();
		//gets employee id from session
		String empId=(String) session.getAttribute("Username");
		//creates database object to connection
	Database database=new Database();
	//to execute sql statement with parameters
		PreparedStatement statement = null;
		//to create connection with database
		Connection connection = null;

		// Set response content type
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		try {
			//to create connection with database	
  connection=(Connection) database.createConnection();
			//delate all bid of employee
			statement= connection.prepareStatement("delete from bidding_details where employee_id=(?) ");
			statement.setString(1, empId);
			//executes sql statement
			statement.executeUpdate();
			//display alert message for successfully withdrawn
			out.println("<script type=\"text/javascript\">");
			out.println("alert('You have successfully withdrawn  all your bid')");
            out.println("</script>");
		   RequestDispatcher rd=request.getRequestDispatcher("/UserWelcome.jsp");
			rd.include(request, response);
		} catch (Exception e) {
			out.print("exception");
		}
	
		
	}
}


