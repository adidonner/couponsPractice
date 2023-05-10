package app.core;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import app.core.services.DailyJobService;

@Component
@Order(2)
public class CouponExpiretionDailyJob implements Runnable {

	@Autowired
	private DailyJobService dailyJobService;
	
	// check every certain time(5 seconds) if coupons are expiered
	@Scheduled(timeUnit = TimeUnit.SECONDS, fixedRate = 5)
	public void run() {	
			System.out.println("--- checking coupon expiration");
			
				System.out.println(LocalDate.now());
				dailyJobService.checkCouponExpiration();
		}


}
