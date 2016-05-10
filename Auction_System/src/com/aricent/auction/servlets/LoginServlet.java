
/***********************************************************************
    Aricent Technologies Proprietary

	This source code is the sole property of Aricent Technologies. Any form of utilization
	of this source code in whole or in part is  prohibited without  written consent from
	Aricent Technologies

		  File Name	                :LoginServlet
		  Principal Author      	:TH1_GR_02
		  Subsystem Name            :Auction_System
		  Module Name           	:Login
		  Date of First Release 	:Apr 10, 2016, 03:30:39 PM
		  Author					:TH1_GR_02
		  Description           	:takes user name and password from user and validates


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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.aricent.auction.database.Database;
import com.aricent.auction.timer.Timer;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Timer timer;

	// LoginServlet default constructor is called
	public LoginServlet() {
		try {
			// creates an instance of Timer class
			timer = Timer.getInstance();
		} catch (ClassNotFoundException e) {

		} catch (SQLException e) {

		}
	}

	// it takes user name and password from user and validates
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// userName taken from loginpage

		String userName = request.getParameter("Username");
		// password taken from login page
		String password = request.getParameter("Password");
		// printwriter object is used to write on webpage
		PrintWriter out = response.getWriter();
		// checks if user name and password is null
		if (userName.equals("") || password.equals("")) {

			out.println("<script type=\"text/javascript\">");
			//prints alert message if username or password is null
			out.println("alert('username or password is null');");
			out.println("</script>");

			//and goes back to login page
			RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
			rd.include(request, response);

		}
		//checks if username's length is 5 and password's length is greater than 8 and lesser than 12
		else if (userName.length() != 5 || password.length() < 8 || password.length() > 12) 
		{
			out.println("<script type=\"text/javascript\">");
			//alerts the user that the entered username or password is not a valid one
			out.println("alert('invalid username or password!!length not matched');");
			out.println("</script>");
			//and goes back to login page
			RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
			rd.include(request, response);
		}
		
		else 
		{
			//callin doPost
			doPost(request, response);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//create database object
		Database database = new Database();
		//creates a Connection with database
		Connection connection = database.createConnection();
		//Statement is used to implement sql statement
		Statement statement = null;
		//resultSet stores the value retrieved from database
		ResultSet resultSet = null;

		//gets the value entered in login page
		String userName = request.getParameter("Username");
		//gets the password entered in login page
		String password = request.getParameter("Password");

		// zero means user does not exist
		int userExistence = 0;
		int checkPassword = 0;
		int flag = 0;

		for (int i = 0; i < userName.length(); i++) 
		{
			if ((int) userName.charAt(i) < 48 || (int) userName.charAt(i) > 57) 
			{
				flag = 1;
				break;
			}
		}
		
		//if the username is not valid then alert message is displayed
		if (flag == 1) 
		{

			PrintWriter out = response.getWriter();

			out.println("<script type=\"text/javascript\">");

			out.println("alert('invalid username');");

			out.println("</script>");
			RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
			rd.include(request, response);

		}

		else {

			try {
				//to implement sql statement
				statement = connection.createStatement();
				//stores the resultant value from executing the query
				resultSet = statement.executeQuery("select * from login_credentials");
				
				while (resultSet.next()) 
				{
					//stores the login id in emp_id
					int emp_id = resultSet.getInt("login_id");
					if (Integer.parseInt(userName) == emp_id) 
					{
						userExistence++;
						break;
					}

				}

			} 
			catch (SQLException e) {} 
			finally {
				statement = null;
				resultSet = null;
			}

			//if user doesnt exist displays alert message
			if (userExistence == 0)
			{

				PrintWriter out = response.getWriter();

				out.println("<script type=\"text/javascript\">");

				out.println("alert('invalid username');");

				out.println("</script>");

				RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
				rd.include(request, response);
			}

			else {

				try {
					//to implement sql statement
					statement = connection.createStatement();
					//stores the resultant value from executing the query
					resultSet = statement.executeQuery("select * from login_credentials");
					int emp_id;
					String password1;
					while (resultSet.next())
					{
						//stores login id and password in local variables
						emp_id = resultSet.getInt("login_id");
						password1 = resultSet.getString("password");

						//validates the login
						if (Integer.parseInt(userName) == emp_id && password.equals(password1)) {
							checkPassword++;
							break;
						}

					}

				} 
				catch (SQLException e) {}
				finally 
				{
					statement = null;
					resultSet = null;
				}

			}

			//if the user exists
			if (userExistence != 0) 
			{
				//if password is wrong alert is dispalyed
				if (checkPassword == 0) 
				{
					PrintWriter out = response.getWriter();

					out.println("<script type=\"text/javascript\">");

					out.println("alert('wrong password entered');");

					out.println("</script>");

					RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
					rd.include(request, response);

				} 
				//if user credentials are valid
				else 
				{
					response.setContentType("text/html");
					HttpSession session = request.getSession();
					session.setAttribute("Username", userName);
					//checks for admin
					if (Integer.parseInt(userName) == 44302) 
					{
						response.sendRedirect(request.getContextPath() + "/AdminWelcome.jsp");
					} 
					else 
					{

						response.sendRedirect(request.getContextPath() + "/UserWelcome.jsp");

					}

				}

			}

		}
	}

}
