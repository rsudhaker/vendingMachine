package com.vm.constants;

public class Coin {

	private double value;
	private double weight;
	private double size;

	public Coin(double value, double weight, double size) {
		this.value = value;
		this.weight = weight;
		this.size = size;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}
}
