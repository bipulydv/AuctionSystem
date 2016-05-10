

/***********************************************************************
    Aricent Technologies Proprietary

	This source code is the sole property of Aricent Technologies. Any form of utilization
	of this source code in whole or in part is  prohibited without  written consent from
	Aricent Technologies

		  File Name	                :RegisterServlet
		  Principal Author      	:TH1_GR_02
		  Subsystem Name            :Auction_System
		  Module Name           	:Registration
		  Date of First Release 	:Apr 10, 2016, 03:30:39 PM
		  Author					:TH1_GR_02
		  Description           	:registers only company's employees


		  Change History

		  Version      		        :1.0
		  Date(DD/MM/YYYY)         	:10/04/2016
		  Modified by		        :TH1_GR_02
		  Description of change     :Initial version

	***********************************************************************/



	/**
	 * 
	 *	
	 *registers only company's employees
	 *    
	 *	@see    RegisterServlet.java 
	 *
	 *	@see     doPost 
	 *	@version  1.0
	 *	@author   TH1_GR_02
	 *  @since    Apr 10, 2016, 03:30:39 PM
	 */



package com.aricent.auction.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aricent.auction.database.Database;



@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet 
{
 
	private static final long serialVersionUID = 1L;
	List<String> list = new ArrayList<>();
	Database database=new Database();
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
  {

	  //used to implement sql statement
    Statement statement = null;
    //Connection is used establish database connection
    Connection connection = null;
    //creates database connection
    connection=database.createConnection();
    // Set response content type
    response.setContentType("text/html");

    try {
     
    	//implements sql statement
      statement = connection.createStatement();
      String sql;
      //query to get employee id
      sql = "SELECT employee_id from employee_details";
      //executes the query in sql
      ResultSet resultSet = statement.executeQuery(sql);
      
      while (resultSet.next())
      {
    	  //stores employee id from table 
        int id = resultSet.getInt("employee_id");

        String id_val = String.valueOf(id);
        //and adds it to the list
        list.add(id_val);
      }

      resultSet.close();
      statement.close();
      connection.close();
      doPost(request, response);

    } catch (Exception e) { }

  }

  //validates username and password and stores it in database
  protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
  {
    int flag1=0;
    List<String> list2=new ArrayList<>();
    PreparedStatement preparedstatement = null;
    //to create connection with database
    Connection connection = null;
    //to execute sql statement
    Statement statement=null;
    response.setContentType("text/html");
    try
    {//creates database connection
    	connection = database.createConnection();
    	//to execute sql query
    	statement=connection.createStatement();
    	//takes username and password  and confirm passwordfrom user
	    String name = request.getParameter("Username");
	    String confirmPassword = request.getParameter("ConfirmPassword");
	    String password = request.getParameter("Password");
	    //prepared statement is used to execute query
	    preparedstatement = connection.prepareStatement("insert into login_credentials(login_id,password) values(?,?)");
	    PrintWriter out=response.getWriter();
	      preparedstatement.setString(1, name);
	      preparedstatement.setString(2, password);
	      boolean isEmployee=false;
	      //checks if name and password and confirm password is null
	      if(name.equals("") || password.equals("") || confirmPassword.equals("") )
	      {
	        out.println("<script type=\"text/javascript\">");
        out.println("alert('Username or password is null')");
        
        out.println("</script>");
        
        
        RequestDispatcher rd=request.getRequestDispatcher("/Register.jsp");
        rd.include(request, response);
      }
      
      //checks if user name and password length matches validation details and displays alert
      else if(name.length()!=5||password.length()<8||password.length()>12)
		{
			out.println("<script type=\"text/javascript\">");

			out.println("alert('username or password length not matched');");

			out.println("</script>");

			RequestDispatcher rd=request.getRequestDispatcher("/Login.jsp");
			rd.include(request, response);
		}
	      //checks if password and confirm password matches
      else if( !(password.equals(confirmPassword) )){
    	  
    	  out.println("<script type=\"text/javascript\">");
          out.println("alert('password does not match')");
          
          out.println("</script>");
          RequestDispatcher rd=request.getRequestDispatcher("/Register.jsp");
          rd.include(request, response);
    	  
      }
      
      else
      {
      for (String s : list)
      {
    	  //if user is an internal employee
        if (s.equals(name))
        {
          isEmployee=true;
          String sql="select login_id from login_credentials";
          
          ResultSet rs = statement.executeQuery(sql);
          while (rs.next()) 
          {
            int id1 = rs.getInt("login_id");

            String id_val = String.valueOf(id1);
            list2.add(id_val);
          }
      //check if user is already registered
          for(String s1:list2)
          {
            if(s1.equals(name))
            {
            
              flag1=1;
              //if employee is already registered
              out.println("<script type=\"text/javascript\">");
              out.println("alert('Employee with this username has already registered');");
              out.println("</script>");
              RequestDispatcher rd = request.getRequestDispatcher("/Register.jsp");
              rd.include(request, response);
             break;
            }
          }
          //if user validation is true then registers successfully
          if(flag1==0)
          {
          
            preparedstatement.execute();
            
            out.println("<script type=\"text/javascript\">");
            out.println("alert('You have successfully registered');");
            out.println("</script>");
            RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
            rd.include(request, response);

          }
          break;
        }
          
      }
      //if invalid employee
      if(!isEmployee)
      {
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Invalid Username');");
        out.println("</script>");
        RequestDispatcher rd = request.getRequestDispatcher("/Register.jsp");
        rd.include(request, response);
      }
      }
      
    } catch (Exception e) {

    }

  }

}

