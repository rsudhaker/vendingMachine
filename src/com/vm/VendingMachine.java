package com.vm;

import java.util.ArrayList;
import java.util.List;

import com.vm.constants.Coin;
import com.vm.constants.Constants;
import com.vm.constants.Product;

public class VendingMachine {

	private List<Double> coinsAccepted = new ArrayList<Double>();
	private Product product = null;
	private Double change = 0.0;
	private Boolean exactChange = false;

	private List<Coin> validCoins = new ArrayList<Coin>();

	public VendingMachine(Boolean exactChange) {
		this.exactChange = exactChange;
	}

	public VendingMachine() {
		getValidCoins();
	}

	private void getValidCoins() {
		validCoins.add(Constants.NICKEL);
		validCoins.add(Constants.DIME);
		validCoins.add(Constants.QUARTER);
	}

	public void accept(Coin coin) {
		for (Coin validCoin : validCoins) {
			if (coin.getWeight() == validCoin.getWeight() && coin.getSize() == validCoin.getSize()) {
				coinsAccepted.add(coin.getValue());
				break;
			}
		}
	}

	public void selectProduct(Product product) {
		this.product = product;
	}

	public String getChange() {
		String getChange = String.format("%.2f", change);
		change = 0.0;
		return getChange;
	}

	public void returnCoins() {
		product = null;
		coinsAccepted = new ArrayList<Double>();
	}

	public String show() {
		Double totalCents = sum(coinsAccepted);
		String msg = buildshow(totalCents);
		return msg;
	}

	private String buildshow(Double total) {
		if (product != null) {
			return shownOnProductSelected(total);
		} else {
			return shownOnProductNotSelected(total);
		}
	}

	private String shownOnProductSelected(Double total) {
		if (product.getQuantity() == 0.0) {
			product = null;
			return Constants.STATUS_SOLD_OUT;
		}
		if (total >= product.getPrice()) {
			change = sum(coinsAccepted) - product.getPrice();
			coinsAccepted = new ArrayList<Double>();
			product = null;
			return Constants.STATUS_THANK_YOU;
		} else {
			return String.format("PRICE %.2f", product.getPrice());
		}
	}

	private String shownOnProductNotSelected(Double total) {
		if (total > 0.0)
			return String.format("%.2f", total);
		else
			return (exactChange) ? Constants.STATUS_EXACT_CHANGE : Constants.STATUS_INSERTCOIN;
	}

	private Double sum(List<Double> coinList) {
		Double total = 0.0;
		for (Double coin : coinList)
			total += coin;
		return total;
	}
}
