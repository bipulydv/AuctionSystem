package com.aricent.auction.mail;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

//import com.aricent.database.operation.DataBaseConnector;
//import com.aricent.database.operation.StoreMailInfoToDB;
//import com.aricent.props.PropertyWriter;
//import com.aricent.plansheet.*;


public class Mailer extends TimerTask
{
	
	public void sendToTrainer()
	{
//		PropertyWriter pw=new PropertyWriter();
//		
//		try 
//		{
//			pw.instantiateReadObjects();
//		} 
//		catch (IOException e) 
//		{
//			e.printStackTrace();
//		}
//		File file=new File(pw.getExcelSheetPath());
//		ScheduleContainer.clearPreviousDailySchedule();
//		ExcelSheetOperations.readExcelSheet(file);
//		ScheduleContainer.createDailySchedule();
//		HashMap<String, Trainer> trainersSchedule=ScheduleContainer.getTrainersDailySchedule();
//		
//		
//		for(Map.Entry<String, Trainer> me:trainersSchedule.entrySet())
//		{
//			generateMail(me.getValue());
//		}

	}
	
	
	
	public static void generateMail(Trainer trainer)
	{
		
		String mailId = trainer.getEmailId();
		String sessionDetails="";
		String sessionDate="";
		String name = trainer.getName();
		String subject=null;
//		ArrayList<TrainerSession> sessionList=trainer.getSessionList();
//		Comparator<TrainerSession > com=new SortByTime();
//		Collections.sort(sessionList, com);
//		
//		PropertyWriter pw=new PropertyWriter();
//		
//		try 
//		{
//			pw.instantiateReadObjects();
//		} 
//		catch (IOException e) 
//		{
//			e.printStackTrace();
//		}
//		
//		for(TrainerSession temp:sessionList)
//		{
//			String sessionName = temp.getSessionName();
//			sessionDate= temp.getSessionDate();
//			String sessionStartTime = temp.getSessionStartTime();
//			String sessionEndTime = temp.getSessionEndTime();
//			String venue=temp.getSessionVenue();
//			subject="Training schedule for "+sessionDate;
//			 sessionDetails+= 
//					"<tr>"
//						+ "<td> "+sessionName+" </td>"
//						+ "<td> "+sessionStartTime+" </td>"
//						+ "<td> "+sessionEndTime+" </td>"
//						+ "<td> "+venue+" </td>"
//					+"</tr>" ;
//					
//		}
//		
//		String mailBody = 
//				
//				"<html> <body> Hi  "+ name + ", <br/> "+subject+"<br/>"
//						+ " <table border=\"1\">"
//						+ "<tr>"
//								+ "<th> Session	</th>"
//								+ "<th> Start Time </th>"
//								+ "<th> End Time </th>"
//								+ "<th> Venue </th>"
//							+"</tr>" 
//							+sessionDetails;
//		System.out.println(mailBody);
//		
//		   StringBuffer sb = new StringBuffer();
//		StringBuffer buffer = sb.append("BEGIN:VCALENDAR\n" +
//                "PRODID:-//Microsoft Corporation//Outlook 9.0 MIMEDIR//EN\n" +
//                "VERSION:2.0\n" +
//                "METHOD:REQUEST\n" +
//                "BEGIN:VEVENT\n" +
//                "ATTENDEE;ROLE=REQ-PARTICIPANT;RSVP=TRUE:MAILTO:"+mailId+"\n" +
//                "ORGANIZER:MAILTO:"+pw.getEmailSource()+"\n" +
//                "DTSTART:20051208T053000Z\n" +
//                "DTEND:20051208T060000Z\n" +
//                "LOCATION:+TH-1\n" +
//                "TRANSP:OPAQUE\n" +
//                "SEQUENCE:0\n" +
//                "UID:040000008200E00074C5B7101A82E00800000000002FF466CE3AC5010000000000000000100\n" +
//                " 000004377FE5C37984842BF9440448399EB02\n" +
//                "DTSTAMP:20051206T120102Z\n" +
//                "CATEGORIES:Session\n" +
//                "DESCRIPTION:\n\n" +
//                "SUMMARY:Session for tomorrow\n" +
//                "PRIORITY:5\n" +
//                "CLASS:PUBLIC\n" +
//                "BEGIN:VALARM\n" +
//                "TRIGGER:PT1440M\n" +
//                "ACTION:DISPLAY\n" +
//                "DESCRIPTION:Reminder\n" +
//                "END:VALARM\n" +
//                "END:VEVENT\n" +
//                "END:VCALENDAR");
//		
//			
		
	MailSender.sendMail(mailId,subject,mailBody,buffer);
		
	}

	@Override
	public void run() {
		sendToTrainer();
		
	}
	

}
