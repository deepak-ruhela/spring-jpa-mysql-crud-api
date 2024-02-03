package com.myapp.model;

public enum TaskStatus {
	DO("Do"), DOING("Doing"), Done("Done");

	private final String value;

	private TaskStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
