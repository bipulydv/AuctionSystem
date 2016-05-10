

/***********************************************************************
    Aricent Technologies Proprietary

	This source code is the sole property of Aricent Technologies. Any form of utilization
	of this source code in whole or in part is  prohibited without  written consent from
	Aricent Technologies

		  File Name	                :AddProductServlet
		  Principal Author      	:TH1_GR_02
		  Subsystem Name            :Auction_System
		  Module Name           	:Product_Management
		  Date of First Release 	:Apr 10, 2016, 03:30:39 PM
		  Author					:TH1_GR_02
		  Description           	:it takes input values for product from admin and adds to database


		  Change History

		  Version      		        :1.0
		  Date(DD/MM/YYYY)         	:10/04/2016
		  Modified by		        :TH1_GR_02
		  Description of change     :Initial version

	***********************************************************************/



	/**
	 * 
	 *	
	 *
	 *    
	 *	@see     
	 *
	 *	@see      AddProductServlet
	 *	@version  1.0
	 *	@author   TH1_GR_02
	 *  @since    Apr 10, 2016, 03:30:39 PM
	 */





package com.aricent.auction.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aricent.auction.database.Database;

@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {


	


	private static final long serialVersionUID = 558081127949336212L;

	//doGet method of servlet   takes input values for product from admin and adds to database
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//gets connection from database
		Connection connection;
		//statement is used to implement  SQL statement
		Statement statement;
		//PreparedStatement object is used for SQL statements that take parameters
		PreparedStatement pst=null;
		//resultset is used to store data fetched from database
		ResultSet rst=null;
		
		int temp=0;
		int flag=0;
		//it reads image
		FileInputStream fis;
		//Database object is created 
		Database database=new Database();
		//to create database connection
		connection=database.createConnection();

//gets productid 
		String product_id=request.getParameter("product_id");
		String product_name=request.getParameter("product_name");
		String product_description=request.getParameter("product_description");
		String product_min_bid=request.getParameter("product_min_bid");
		String product_category=request.getParameter("product_category");
		String product_image=request.getParameter("product_image");

		System.out.println(product_image);

		if(product_id.equals("") ||product_name.equals("") || product_description.equals("") || product_min_bid.equals("") || product_category.equals("") || product_image.equals("")){
			PrintWriter out = response.getWriter();

			out.println("<script type=\"text/javascript\">");

			out.println("alert('fields cant be null');");
			out.print("window.history.back();");
			out.println("</script>");
			
			

			temp=1;
		}

		if(temp==0){
			
			

			

			for(int i=0;i<product_id.length();i++)
			{
				
				if((int)product_id.charAt(i)<48||(int)product_id.charAt(i)>57)
				{
					flag=1;
					
					break;
				}
			}

			
			for(int i=0;i<product_min_bid.length();i++)
			{
				
				if((int)product_min_bid.charAt(i)<48||(int)product_min_bid.charAt(i)>57)
				{
					flag=1;
					
					break;
				}
			}


			if(flag==1){	

				
				PrintWriter out = response.getWriter();

				out.println("<script type=\"text/javascript\">");

				out.println("alert('only numeric value is allowed in product id and min bid');");
				out.print("window.history.back();");
				out.println("</script>");
				
				

			}

			else{
				
				//zero means product does not exist
				int productexist=0;

				try {
					connection=database.createConnection();
					statement=connection.createStatement();
					rst=statement.executeQuery("select * from product_details");
					while(rst.next()){
						if(product_id.equals(rst.getString("product_id")) || product_id.equals(rst.getString("product_name")))
							productexist=1;

					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();}
					finally{
						connection=null;
						statement=null;
						rst=null;
					}
				
				if(productexist==1){

					PrintWriter out = response.getWriter();
					out.println("<script type=\"text/javascript\">");

					out.println("alert('product id or product name already exist');");
					out.print("window.history.back();");
					out.println("</script>");
					

				}
				else{

					try {
						connection=database.createConnection();
						System.out.println("connected");
						pst=connection.prepareStatement("insert into product_details values(?,?,?,?,?,?,?,?,?)");
						
						pst.setString(1,product_id);
						pst.setString(2,product_name);
						
						pst.setString(3,product_description);
						pst.setString(4,product_min_bid);
						pst.setString(5,"available");
						pst.setString(6,product_category);
						
						
						fis = new FileInputStream(product_image); 
						File image=new File(product_image);
						pst.setBinaryStream(7, (InputStream)fis, (int)(image.length())); 
						pst.setString(8,"2016-05-15 12:00:00");
						pst.setInt(9,0);
						
						
						pst.execute();
					
						PrintWriter out=response.getWriter();

						out.println("<script type=\"text/javascript\">");

						out.println("alert('Product Added successfully');");
						out.println("window.location.href='AdminWelcome.jsp';");
						out.println("</script>");
					
					} catch (SQLException e) {
						
					}

				}

			}
		}
	}
}
