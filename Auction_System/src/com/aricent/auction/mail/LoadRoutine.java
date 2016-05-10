package com.aricent.auction.mail;
import java.util.concurrent.ScheduledExecutorService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class LoadRoutine implements ServletContextListener
{
	private ScheduledExecutorService scheduler;

	@Override public void contextInitialized(ServletContextEvent event)
	{
		Schedule scheduleMailer = new Schedule();
	}
	 
	@Override public void contextDestroyed(ServletContextEvent event)
	{
        	scheduler.shutdownNow();
	}
}
