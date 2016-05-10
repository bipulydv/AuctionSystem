

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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aricent.auction.database.Database;

/**
 * Servlet implementation class MailDetailServlet
 */
@WebServlet("/MailDetailServlet")
public class MailDetailServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con=null;
		Statement st=null;
		ResultSet rst=null;
		
		Database database=new Database();
		con=database.createConnection();
		
		try {
			st=con.createStatement();
		System.out.println("connected");
			rst=st.executeQuery("select * from mailing_details");
			while(rst.next())
			{
				
				request.setAttribute("to",rst.getString("email_id"));
				System.out.println("got email");
				System.out.println(request.getAttribute("to"));
				request.setAttribute("subject","congratulation user"+rst.getString("employee_id"));
				System.out.println("got subject");
				request.setAttribute("msg","you have successfully purchased item no."+rst.getString("product_id")+"naming" +rst.getString("product_name")+"\n"+rst.getString("product_details"));
				System.out.println("got msg");
				RequestDispatcher rs=request.getRequestDispatcher("SendMailServlet");
				rs.include(request,response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
