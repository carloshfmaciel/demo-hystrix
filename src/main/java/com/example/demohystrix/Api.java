package com.example.demohystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

public class Api {

	@HystrixCommand(fallbackMethod = "fallbackGetHello")
	public String getHello() throws InterruptedException {
		Thread.sleep(1000);
		return "Hello! Requisição processada!";
	}

	public String fallbackGetHello() {
		return "Calling Fallback! Requisição rejeitada pelo Hystrix!";
	}

	@HystrixCommand(fallbackMethod = "fallbackDoExceptionsForOddNumbers")
	public void doExceptionsForOddNumbers(int someValue) {

		System.out.println("Conectando no banco de dados for request number " + someValue);

		if (someValue % 2 != 0) {
			throw new RuntimeException("Banco de dados indisponível!");
		}

	}

	public void fallbackDoExceptionsForOddNumbers(int someValue, Throwable exception) {
		System.out.println(String.format("Fallback being executed to request number %d . Exception: %s.", someValue,
				exception.getMessage()));
	}

	@HystrixCommand(fallbackMethod = "fallbackDoSomeWithDelay")
	public void doSomeWithDelay(int requestNumber, long delayTime) {
		System.out.println(String.format("Conectando no banco de dados for request number %d will take %d ms",
				requestNumber, delayTime));
		try {
			Thread.sleep(delayTime);
		} catch (InterruptedException e) {
			System.out.println(String.format("Request number %d interrupted by exceed timeout!", requestNumber));
		}
	}

	public void fallbackDoSomeWithDelay(int requestNumber, long delayTime, Throwable exception) {
		System.out.println(String.format("Fallback being executed to request number %d . Exception: %s.", requestNumber,
				exception.getMessage()));
	}

}
