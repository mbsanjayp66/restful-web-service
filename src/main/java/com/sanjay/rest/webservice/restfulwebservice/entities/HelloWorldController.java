package com.sanjay.rest.webservice.restfulwebservice.entities;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController{
	
	private MessageSource messageSource;
	public HelloWorldController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@GetMapping("/hello-world")
	public HelloWorld helloWorld() {
		return new HelloWorld("Hello-world");
	}
	
	@GetMapping("/hello-world/path-variable/{name}")
	public HelloWorld helloWorld(@PathVariable String name) {
		return new HelloWorld(String.format("Hello World, %s", name));
	}
	
	@GetMapping("/hello-world-i18n")
	public String helloWorldI18n() {
		Locale locale  = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
	}
}
