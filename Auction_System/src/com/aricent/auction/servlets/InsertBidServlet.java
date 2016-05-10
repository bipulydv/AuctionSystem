
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
import java.sql.Statement;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aricent.auction.database.Database;



@WebServlet("/InsertBidServlet")
public class InsertBidServlet extends HttpServlet
{  
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{ 
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		Connection conn=null;
		Statement stmt=null;
		Statement stmt1=null;
		Statement stmt2=null;
		
		ResultSet res=null,res1=null,res2=null,res3=null;
		Database database=new Database();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			HttpSession session=request.getSession();
			conn=DriverManager.getConnection("jdbc:mysql://10.203.161.94:3306/auction_system","root","root");
			
			
			String bid_price=request.getParameter("bid");
		
			
		
			if(bid_price.equals(""))
			{
				out.println("<script type=\"text/javascript\">");
				out.println("alert('please enter a valid bid amount')");
				out.print("window.history.back();");
				out.println("</script>");
		
			}
			else{
				stmt=conn.createStatement();
				stmt1=conn.createStatement();
				stmt2=conn.createStatement();
				Double bd=Double.parseDouble(bid_price);
			String emp_id=(String)session.getAttribute("Username");
			System.out.println(emp_id);
			System.out.println(bd+"bd");
			int prod_id=(int)session.getAttribute("p_id");
			
			String msg="New bid price for Product id:"+prod_id+" is ";
			
			Double max_bid=(Double)session.getAttribute("price");
			System.out.println(max_bid+"hey");
			res3=stmt.executeQuery("select * from notification where product_id="+prod_id);
			res1=stmt1.executeQuery("Select initial_bid,status from product_details where product_id="+prod_id);
			res=stmt2.executeQuery("Select * from bidding_details where employee_id='"+emp_id+"'and product_id="+prod_id);
			int flag=0;
			boolean noti=res3.next();
			if(res1.next())
			{
	if(res1.getString("status").equals("available"))
	       {
				if(res1.getFloat("initial_bid")>Integer.parseInt(bid_price))
				{
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Your bid amount is less than the minimum bid amount for this product')");
					out.print("window.history.back();");
					out.println("</script>");
					flag=1;
				}
				if(flag==0)
				{
					
					if(res.next())
					{
						
				
						if(res.getInt("bid_price")<Integer.parseInt(bid_price))
						{
							stmt.executeUpdate("update bidding_details set bid_price="+bid_price+" where employee_id="+emp_id+" and product_id="+prod_id);
							stmt.executeUpdate("update notification set message='"+(msg+bid_price)+"' where product_id="+prod_id);
							out.println("<script type=\"text/javascript\">");
							out.println("alert('You have already bid!! New bidding price for product id:"+prod_id+" is :"+bid_price+"')");
							out.println("</script>");
							RequestDispatcher rd=request.getRequestDispatcher("/UserWelcome.jsp");
							rd.include(request, response);
					
					
					
						}		
						else
						{ 
							out.println("<script type=\"text/javascript\">");
							out.println("alert('New bidding price should be greater than previous bid price="+res.getInt("bid_price")+"')");
							out.print("window.history.back();");
							out.println("</script>");
					
					
							
						}
					}
			
					else
					{
						
						if(bd>max_bid)
						{
						
						stmt.executeUpdate("Insert into bidding_details values("+prod_id+","+bid_price+","+emp_id+")");
					
						//stmt.executeUpdate("insert into notification values("+prod_id+",'"+(msg+bid_price)+"')");
					
						//res2=stmt.executeQuery("select max(bid_price) from bidding_details where product_id="+prod_id);
						if(noti)
						{
							System.out.println("hello2");

							stmt.executeUpdate("Update notification set message='"+(msg+bid_price)+"' where product_id="+prod_id);
						}
						else
						{
							System.out.println("hello3");

							stmt.executeUpdate("Insert into notification values("+prod_id+",'"+(msg+bid_price)+"')");
						}
						out.println("<script type=\"text/javascript\">");
						out.println("alert('User with employee id:"+emp_id+" has successfully submit bid of rs"+bid_price+" on product id:"+prod_id+"')");
						
						out.println("</script>");
						
						
						RequestDispatcher rd=request.getRequestDispatcher("/UserWelcome.jsp");
						rd.include(request, response);
						}
						else
						{
							out.println("<script type=\"text/javascript\">");
							out.println("alert('Your bid amount should be greater than the current highest bid')");
							out.print("window.history.back();");
							out.println("</script>");
						}
							
					}
				}
			}
	else
	{
		out.println("<script type=\"text/javascript\">");
		out.println("alert('sorryyy!!!bid time over!!!!')");
		
		out.println("</script>");
		RequestDispatcher rd=request.getRequestDispatcher("/BiddingLaptop.jsp");
		rd.include(request, response);
		
	}
			}
		}}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}



