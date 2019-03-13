package com.example.demohystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.ApplicationContext;

import com.example.demohystrix.api.Api;
import com.example.demohystrix.tasks.SimulateManyActiveThreads;
import com.example.demohystrix.tasks.SimulateManyErrors;
import com.example.demohystrix.tasks.SimulateTimeout;

@EnableHystrix
@EnableHystrixDashboard
@SpringBootApplication
public class DemoHystrixApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(DemoHystrixApplication.class, args);
		Api api = ctx.getBean(Api.class);

//		 SimulateTimeout.simulate(api);
		
//		SimulateManyActiveThreads.simulate(api);

//		 SimulateManyErrors.simulate(api);
		
	}
}
