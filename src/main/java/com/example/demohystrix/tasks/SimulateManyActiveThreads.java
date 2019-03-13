package com.example.demohystrix.tasks;

import com.example.demohystrix.api.Api;
import com.example.demohystrix.utils.Utils;

public class SimulateManyActiveThreads {

	public static void simulate(Api api) {
		while (true) {

			Utils.wait3Seconds();

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
	
}
