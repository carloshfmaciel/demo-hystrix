package com.example.demohystrix.utils;

import java.util.Date;

public class Utils {

	public static void wait3Seconds() {
		try {
			Thread.sleep(3000);
			System.out.println("\n\n\n\n\n\n\n\n");
			System.out.println("Hora da Request: " + new Date());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
