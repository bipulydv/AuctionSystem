package com.aricent.auction.mail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;

import com.aricent.auction.database.Database;

public class Schedule 
{
	Timer timer;
	Database database=new Database();
	Connection con=null;
	Statement st=null;
	ResultSet rst_product=null;
	ResultSet rst_bid=null;
	ResultSet rst_employee=null;
	PreparedStatement pst_bid=null;
	PreparedStatement pst_employee=null;
	
	public Schedule() 
	{
		con=database.createConnection();
		System.out.println("connected");
		try {
			st=con.createStatement();
			int day,month,year;
			int second,minutes,hour;
			
			int tableday,tablemonth,tableyear;
			int tablesecond,tableminutes,tablehour;
			GregorianCalendar date = new GregorianCalendar();

			day = date.get(Calendar.DAY_OF_MONTH);
			month = date.get(Calendar.MONTH);;
			year = date.get(Calendar.YEAR);
			second = date.get(Calendar.SECOND);
			minutes = date.get(Calendar.MINUTE);
			hour = date.get(Calendar.HOUR);
			
			String currentDate=year+"-"+(month+1)+"-"+day;
			System.out.println(currentDate);
			
			String currentTime=hour+"-"+minutes+"-"+second;
			System.out.println(currentTime);
			//Date today=new Date(year-1900, month, day);
			//System.out.println("Todays date:"+today);

			rst_product=st.executeQuery("select * from product_details where status='sold'");
			pst_bid=con.prepareStatement("select max(bid_price) from bidding_details where product_id=?");
			pst_employee=con.prepareStatement("select * from employee_details where employee_id=?");
			
			while(rst_product.next())
			{
				Timestamp time=rst_product.getTimestamp("end_time");
				
				tableday=time.getDate();
				tablemonth=time.getMonth();
				tableyear=time.getYear();
				String tableDate=(1900+tableyear)+"-"+(tablemonth+1)+"-"+tableday;
				System.out.println(tableDate);
				
				tablesecond=time.getSeconds();
				tableminutes=time.getMinutes();
				tablehour=time.getHours();
				String tableTime=tablehour+"-"+tableminutes+"-"+tablesecond;
				System.out.println(tableTime);
				
				if(year==tableyear && month==tablemonth && day==tableday && hour==tablehour && minutes==tableminutes && second==tablesecond){
				
				pst_bid.setInt(1,rst_product.getInt("product_id"));
				rst_bid=pst_bid.executeQuery();
				
				
				
				}
				//System.out.println(date);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		Calendar calendar = Calendar.getInstance();
//	    calendar.set(Calendar.HOUR_OF_DAY,7);
//	    calendar.set(Calendar.MINUTE, 41);
//	    calendar.set(Calendar.SECOND, 0);
//
//	    Date alarmTime =  calendar.getTime();
//	    
//	    timer = new Timer();
//        timer.schedule(new Mailer(), alarmTime,60*60*24*1000);
	 }

	   
//	    public static void main(String[] args) 
//	    {
//	    	Schedule a = new Schedule();
//			
//		}
//	    
	public static void main(String[] args) {
		Schedule sc=new Schedule();
	}
	   
}
