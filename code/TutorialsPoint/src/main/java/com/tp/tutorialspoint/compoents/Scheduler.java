package com.tp.tutorialspoint.compoents;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
//	execute the task every minute starting at 3:00 AM and ending at 3:59 AM, every day
	@Scheduled(cron = "0 * 3 * * ?")
	public void cronJobSch() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		String strDate = sdf.format(now);
//		System.out.println("Java cron job expression:: " + strDate);
	}

	@Scheduled(fixedRate = 1000)
//	executing a task on every second from the application startup
	public void fixedRateSch() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		Date now = new Date();
		String strDate = sdf.format(now);
//		System.out.println("Fixed Rate scheduler:: " + strDate);
	}

//	execute the task for every second after 3 seconds from the application startup has been completed
	@Scheduled(fixedDelay = 1000, initialDelay = 3000)
	public void fixedDelaySch() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		String strDate = sdf.format(now);
//		System.out.println("Fixed Delay scheduler:: " + strDate);
	}
}
