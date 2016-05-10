

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
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aricent.auction.database.Database;


/**
 * Servlet implementation class BidDetails
 */
@WebServlet("/BiddingMobileServlet")
public class BiddingMobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		int prod_id =Integer.parseInt(request.getParameter("p_id"));
		String i_value=request.getParameter("i_value");
		HttpSession ss=request.getSession();
		ss.setAttribute("p_id", prod_id);
		ss.setAttribute("im_value",i_value);
		Database database=new Database();

		

		
		PreparedStatement stmt = null;
		Connection conn = null;

		response.setContentType("text/html");


		try {
			
			conn=database.createConnection();
			if(conn==null)
			{
				out.print("not connected");
			}
			stmt = conn.prepareStatement("SELECT * from product_details where product_id=? ");
			

			stmt.setInt(1, prod_id);

			ResultSet rs = stmt.executeQuery();

			
			request.setAttribute("bid_product", rs);

			RequestDispatcher rd = request.getRequestDispatcher("/BiddingMobile.jsp");
			rd.forward(request, response);
		

		
	}catch(Exception e){}
		
		
	}

	
}
