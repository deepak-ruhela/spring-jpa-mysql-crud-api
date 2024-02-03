package com.myapp.model;

public enum TaskType {
	PRODUCT("Product"), BLOG("Blog"), WEBSITE("Website"), DATABASE("Database");

	private final String value;

	private TaskType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
