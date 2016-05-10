

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
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aricent.auction.database.Database;
@WebServlet("/LaptopServlet")
public class LaptopServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		Database database=new Database();
		Statement stmt = null;
		Connection conn = null;
		
		// Set response content type
		response.setContentType("text/html");
		try {
			conn = database.createConnection();

			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * from product_details where category='laptop' and status='available' ";
			ResultSet rs = stmt.executeQuery(sql);


			request.setAttribute("resultSet",rs);
			System.out.println("resultSet");
			RequestDispatcher rd = request.getRequestDispatcher("/Laptop.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
		}
	}


}
