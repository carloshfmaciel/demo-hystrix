package com.example.demohystrix;

import java.util.Date;
import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@EnableHystrix
@SpringBootApplication
public class DemoHystrixApplication {

	public static void main(String[] args) {
		// SpringApplication.run(DemoHystrixApplication.class, args);
		ApplicationContext ctx = SpringApplication.run(DemoHystrixApplication.class, args);
		Api api = ctx.getBean(Api.class);
		DemoHystrixApplication demo = new DemoHystrixApplication();

		// demo.simulateExcessiveRequestsWaiting(api);

		// demo.simulateExcessiveErrors(api);

		// demo.simulateTimeout(api);
	}

	@Bean
	public Api controller() {
		return new Api();
	}

	private void simulateTimeout(Api api) {

		for (int i = 1; i <= 10; i++) {
			new Thread(new SimulateTimeout(i, api)).start();
		}
	}

	private void simulateExcessiveRequestsWaiting(Api api) {

		while (true) {

			wait3Seconds();

			for (int i = 1; i <= 20; i++) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							System.out.println(api.getHello());
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
		}

	}

	private void simulateExcessiveErrors(Api api) {

		while (true) {

			wait3Seconds();

			for (int i = 1; i <= 20; i++) {
				System.out.println("Doing request number : " + i);
				new Thread(new SimulateManyErrors(i, api)).start();
			}

		}

	}

	class SimulateTimeout implements Runnable {

		private int requestNumber;
		private Api api;

		public SimulateTimeout(int requestNumber, Api api) {
			super();
			this.requestNumber = requestNumber;
			this.api = api;
		}

		@Override
		public void run() {
			api.doSomeWithDelay(requestNumber, this.someNumberBetween1200And2500());
		}

		private long someNumberBetween1200And2500() {
			return new Random().nextInt(2500 - 1200) + 1200;
		}

	}

	class SimulateManyErrors implements Runnable {

		private int someValue;
		private Api api;

		public SimulateManyErrors(int someValue, Api api) {
			super();
			this.someValue = someValue;
			this.api = api;
		}

		@Override
		public void run() {
			api.doExceptionsForOddNumbers(someValue);
		}

	}

	private void wait3Seconds() {
		try {
			Thread.sleep(3000);
			System.out.println("\n\n\n\n\n\n\n\n");
			System.out.println("Hora da Request: " + new Date());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
