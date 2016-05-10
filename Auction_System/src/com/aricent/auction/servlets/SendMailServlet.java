
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
  

import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
@WebServlet("/SendMailServlet")  
public class SendMailServlet extends HttpServlet {  
public void doGet(HttpServletRequest request,   HttpServletResponse response)  throws ServletException, IOException {  
  
    response.setContentType("text/html");  
    PrintWriter out = response.getWriter();  
      
    String to=(String)request.getAttribute("to"); 
    System.out.println("gor email");
    System.out.println(to);
    String subject=(String)request.getAttribute("subject");  
    System.out.println("got subject");
    String msg=(String)request.getAttribute("msg");
    System.out.println("got msg");
    
    
    
    MailerServlet.send(to, subject, msg);  
    System.out.println("sent");
    out.print("message has been sent successfully");  
    out.close();  
    }  
  
}  