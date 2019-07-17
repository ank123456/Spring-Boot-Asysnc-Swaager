package com.data.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;

@EnableAutoConfiguration
@Configuration
@Async
public class DataAsysnc {
	@Async
	public void myRes() {
		System.out.println("My Res is Good");
	}
}
