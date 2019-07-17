package com.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class IndexController {
	@Autowired
	DataResponse response;

	@Autowired
	DataAsysnc dataAsync;

	@RequestMapping("/")
	@Async
	public String home(ModelMap model) {
		model.addAttribute("message", "HowToDoInJava Reader !!");
		response.say();
		dataAsync.myRes();
		MultiValueMap<String, Integer> ha = new LinkedMultiValueMap<>();
		ha.add("1", 1);
		say("");
		System.out.println(ha);

		return "index";
	}

	@RequestMapping("/next")
	public String next(ModelMap model) {
		// model.put("message", "You are in new page !!");
		model.addAttribute("message", "You are in new page !!");

		return "next";
	}

	@Retryable
	public static void say(String s) {

		try {
			final String uri = "http://localhost:8080/springrestexample";

			RestTemplate restTemplate = new RestTemplate();
			String result = restTemplate.getForObject(uri, String.class);

			System.out.println(result);

			System.out.println("Good to go");
		} catch (RuntimeException e) {
			getBackendResponseFallback(e);

		}
	}

	@Recover
	public static String getBackendResponseFallback(RuntimeException e) {
		System.out.println("All retries completed, so Fallback method called!!!");
		return "All retries completed, so Fallback method called!!!";
	}
}