package com.aaron.dog.dto;

import java.util.List;

public class DogSubBreed {
	private List<String> message;
	private String status;
	public List<String> getMessage() {
		return message;
	}
	public void setMessage(List<String> message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
