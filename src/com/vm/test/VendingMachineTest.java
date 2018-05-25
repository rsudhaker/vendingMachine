package com.vm.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vm.VendingMachine;
import com.vm.constants.Constants;

public class VendingMachineTest {

	private VendingMachine vm;

	@Before
	public void setUp() {
		vm = new VendingMachine();
	}

	@Test
	public void verifyInsertCoinMessage() {
		Assert.assertTrue("INSERT COIN when no coins inserted", vm.show().equals(Constants.STATUS_INSERTCOIN));
	}

	@Test
	public void acceptQuarter() {
		vm.accept(Constants.QUARTER);
		Assert.assertTrue("Value of quarter", vm.show().equals("0.25"));
	}

	@Test
	public void acceptDime() {
		vm.accept(Constants.DIME);
		Assert.assertTrue("Value of dime", vm.show().equals("0.10"));
	}

	@Test
	public void acceptNickel() {
		vm.accept(Constants.NICKEL);
		Assert.assertTrue("Value of nickel", vm.show().equals("0.05"));
	}

	@Test
	public void acceptsMultipleValidCoins() {
		vm.accept(Constants.QUARTER);
		vm.accept(Constants.DIME);
		vm.accept(Constants.NICKEL);
		Assert.assertTrue("Total amount of coins inserted", vm.show().equals("0.40"));
	}

	@Test
	public void rejectInvalidCoins() {
		vm.accept(Constants.PENNY);
		Assert.assertTrue("INSERT COIN as Penny is invalid coin",
				vm.show().equals(Constants.STATUS_INSERTCOIN));
	}

	@Test
	public void acceptValidAndRejectInvalidCoins() {
		vm.accept(Constants.PENNY);
		vm.accept(Constants.QUARTER);
		vm.accept(Constants.PENNY);
		vm.accept(Constants.DIME);
		vm.accept(Constants.NICKEL);
		Assert.assertTrue("Total amount of coins inserted rejecting invalid coins", vm.show().equals("0.40"));
	}

	@Test
	public void dispenseColaWithCorrectAmount() {
		vm.accept(Constants.QUARTER);
		vm.accept(Constants.QUARTER);
		vm.accept(Constants.QUARTER);
		vm.accept(Constants.QUARTER);
		vm.selectProduct(Constants.COLA);
		Assert.assertTrue("THANK YOU after vending", vm.show().equals(Constants.STATUS_THANK_YOU));
		Assert.assertTrue("INSERT COIN after show of THANK YOU", vm.show().equals(Constants.STATUS_INSERTCOIN));
	}

	@Test
	public void dispenseChipsWithCorrectAmount() {
		vm.accept(Constants.QUARTER);
		vm.accept(Constants.QUARTER);
		vm.selectProduct(Constants.CHIPS);
		Assert.assertTrue("THANK YOU after vending", vm.show().equals(Constants.STATUS_THANK_YOU));
		Assert.assertTrue("INSERT COIN after show of THANK YOU", vm.show().equals(Constants.STATUS_INSERTCOIN));
	}

	@Test
	public void verifyshowWhenProductIsSelectedWithIncorrectAmount() {
		vm.accept(Constants.QUARTER);
		vm.accept(Constants.QUARTER);
		vm.selectProduct(Constants.COLA);
		Assert.assertTrue("PRICE of the selected product", vm.show().equals("PRICE 1.00"));
	}

	@Test
	public void getChangeAfterColaPurchase() {
		vm.accept(Constants.QUARTER);
		vm.accept(Constants.QUARTER);
		vm.accept(Constants.QUARTER);
		vm.accept(Constants.QUARTER);
		vm.accept(Constants.DIME);
		vm.selectProduct(Constants.COLA);
		Assert.assertTrue("THANK YOU after vending", vm.show().equals(Constants.STATUS_THANK_YOU));
		Assert.assertTrue("INSERT COIN after show of THANK YOU", vm.show().equals(Constants.STATUS_INSERTCOIN));
		Assert.assertTrue("return proper change", vm.getChange().equals("0.10"));
	}

	@Test
	public void getChangeAfterChipsPurchase() {
		vm.accept(Constants.QUARTER);
		vm.accept(Constants.QUARTER);
		vm.accept(Constants.QUARTER);
		vm.accept(Constants.DIME);
		vm.selectProduct(Constants.CHIPS);
		Assert.assertTrue("THANK YOU after vending", vm.show().equals(Constants.STATUS_THANK_YOU));
		Assert.assertTrue("INSERT COIN after show of THANK YOU", vm.show().equals(Constants.STATUS_INSERTCOIN));
		Assert.assertTrue("return proper change", vm.getChange().equals("0.35"));
	}

	@Test
	public void coinsReturned() {
		vm.accept(Constants.NICKEL);
		vm.accept(Constants.QUARTER);
		vm.accept(Constants.NICKEL);
		vm.accept(Constants.QUARTER);
		vm.accept(Constants.DIME);
		vm.accept(Constants.DIME);
		Assert.assertTrue("amount of change inserted.", vm.show().equals("0.80"));
		vm.returnCoins();
		Assert.assertTrue("INSERT COIN after coins are returned", vm.show().equals(Constants.STATUS_INSERTCOIN));
	}

	@Test
	public void verifySoldOutMessage() {
		vm.selectProduct(Constants.CANDY);
		Assert.assertTrue("SOLD OUT when product is sold out", vm.show().equals(Constants.STATUS_SOLD_OUT));
		Assert.assertTrue("INSERT COIN since machine has no inserted coins",
				vm.show().equals(Constants.STATUS_INSERTCOIN));
	}

	@Test
	public void verifySoldOutMessageWithCoins() {
		vm.accept(Constants.NICKEL);
		vm.selectProduct(Constants.CANDY);
		Assert.assertTrue("SOLD OUT when product is sold out", vm.show().equals(Constants.STATUS_SOLD_OUT));
		Assert.assertTrue("Amount of change inserted", vm.show().equals("0.05"));
	}

	@Test
	public void verifyExactChangeMessage() {
		VendingMachine vm = new VendingMachine(true);
		Assert.assertTrue("EXACT CHANGE ONLY since machine has no change to return",
				vm.show().equals(Constants.STATUS_EXACT_CHANGE));
	}

}
