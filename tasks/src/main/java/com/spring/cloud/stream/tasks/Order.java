package com.spring.cloud.stream.tasks;

import java.io.Serializable;

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	private Types type;
	private String instruction;

	public Order() {

	}

	public Order(Types type, String instruction) {
		super();
		this.type = type;
		this.instruction = instruction;
	}

	public Types getType() {
		return type;
	}

	public String getInstruction() {
		return instruction;
	}

	@Override
	public String toString() {
		return "Order [type=" + type + ", instruction=" + instruction + "]";
	}

	public void setType(Types type) {
		this.type = type;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

}
