package com.sanjay.rest.webservice.restfulwebservice.entities;

public class HelloWorld {
	
	private String message;

	public HelloWorld(String msg) {
		this.message = msg;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "HelloWorld [message=" + message + "]";
	}
	

}
