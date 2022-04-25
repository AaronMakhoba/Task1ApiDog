package com.aaron.dog.dto;

import com.fasterxml.jackson.databind.JsonNode;

public class DogList {
	private JsonNode message;
	private String status;
	public JsonNode getMessage() {
		return message;
	}
	public void setMessage(JsonNode message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
