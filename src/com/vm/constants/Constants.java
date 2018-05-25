package com.vm.constants;

public final class Constants {

	public static final String STATUS_INSERTCOIN = "INSERT COIN";
	public static final String STATUS_SOLD_OUT = "SOLD OUT";
	public static final String STATUS_THANK_YOU = "THANK YOU";
	public static final String STATUS_EXACT_CHANGE = "EXACT CHANGE ONLY";

	public static final double PENNY_VALUE = 0.01;
	public static final double NICKEL_VALUE = 0.05;
	public static final double DIME_VALUE = 0.10;
	public static final double QUARTER_VALUE = 0.25;

	public static final Coin PENNY = new Coin(PENNY_VALUE, 2.5, 0.5);
	public static final Coin NICKEL = new Coin(NICKEL_VALUE, 5, 0.8);
	public static final Coin DIME = new Coin(DIME_VALUE, 2.2, 0.7);
	public static final Coin QUARTER = new Coin(QUARTER_VALUE, 5.5, 1);

	public static final double COLA_PRICE = 1.0;
	public static final double CHIPS_PRICE = 0.5;
	public static final double CANDY_PRICE = 0.65;

	public static final Product COLA = new Product(COLA_PRICE, 10);
	public static final Product CHIPS = new Product(CHIPS_PRICE, 5);
	public static final Product CANDY = new Product(CANDY_PRICE, 0);

}
