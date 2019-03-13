package com.example.demohystrix.tasks;

import com.example.demohystrix.api.Api;
import com.example.demohystrix.utils.Utils;

public class SimulateManyErrors implements Runnable {

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

	public static void simulate(Api api) {

		while (true) {

			Utils.wait3Seconds();

			for (int i = 1; i <= 20; i++) {
//				System.out.println("Doing request number : " + i);
				new Thread(new SimulateManyErrors(i, api)).start();
			}

		}

	}
	
}