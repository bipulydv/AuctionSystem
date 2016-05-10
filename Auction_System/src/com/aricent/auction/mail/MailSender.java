package com.aricent.auction.mail;

import java.io.IOException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;


public class MailSender 
{
	public static void sendMail(String emailId,String subject,String mailBody,StringBuffer buffer)
	{
	
		try
		{
			String smtpHost=pw.getHostNameMail();
			String smtpPort=pw.getPortNumber();
			final String fromAddress=pw.getEmailSource();
			final String password=pw.getEmailPassword();
						
			String SSL_FACTORY="javax.net.ssl.SSLSocketFactory";
			
						
			Properties props=new Properties();
			
			props.put("mail.smtp.host", smtpHost);
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug","true");
			props.put("mail.smtp.port", smtpPort);
			props.put("mail.smtp.socketFactory.port",smtpPort);
			props.put("mail.smtp.socketFactory.class",SSL_FACTORY);
			props.put("mail.smtp.starttls.enable","ture");
			props.put("mail.smtp.socketFactory.fallback","false");
			
			Session ses=Session.getDefaultInstance(props,new javax.mail.Authenticator()
			{
				protected PasswordAuthentication getPasswordAuthentication(){
					return new PasswordAuthentication(fromAddress,password);
				}
			});
			
			ses.setDebug(true);
			Message msg=new MimeMessage(ses);
			
			InternetAddress addressFrom=new InternetAddress(fromAddress);
			msg.setFrom(addressFrom);
			
			InternetAddress addressTo=new InternetAddress(emailId);
			msg.setRecipient(Message.RecipientType.TO, addressTo);
			
			msg.setSubject(subject);
			
	      
	         System.out.println(mailBody);
	            

	            // Create the message part
	            BodyPart messageCalendar = new MimeBodyPart();

	            // Fill the message
	            messageCalendar.setHeader("Content-Class", "urn:content-  classes:calendarmessage");
	            messageCalendar.setHeader("Content-ID", "calendar_message");
	            messageCalendar.setContent(mailBody, "BAKCHODI");
	            messageCalendar.setDataHandler(new DataHandler(
	                    new ByteArrayDataSource(buffer.toString(), "text/calendar")));// very important
	            
	            MimeBodyPart bc = new MimeBodyPart();
	            bc.setContent(mailBody,"text/html");
	            
	            BodyPart messageBody = bc;
	            
	            // Create a Multipart
	            Multipart multipart = new MimeMultipart();
	        
	            // Add part one
	            multipart.addBodyPart(messageBody);
	            multipart.addBodyPart(messageCalendar);
	           
	    
	            //Put parts in message
	            msg.setContent(multipart);
	          
			Transport.send(msg);
			

			
		}
		catch(Exception e)
		{
			
		}
	}
}
