package yiou.chen.vendorMachine.controller;

import yiou.chen.vendorMachine.message.CustomerMessage;
import yiou.chen.vendorMachine.message.MachineMessage;
import yiou.chen.vendorMachine.message.Message;
import yiou.chen.vendorMachine.message.OperatorMessage;
import yiou.chen.vendorMachine.message.MachineMessage.InsufficientMoney;
import yiou.chen.vendorMachine.objects.Product;
import yiou.chen.vendorMachine.objects.ProductQueue;
import yiou.chen.vendorMachine.view.IAccount;

public class Machine {
	private ProductQueue productQueue;
	private int mMoney = 0;

	public Machine() {
		productQueue = new ProductQueue();
	}

	/**
	 * User input money to purchase something
	 * 
	 * @param requestProduct
	 * @return
	 */
	public Message handle(IAccount account,
			CustomerMessage.RequestProduct requestProduct) {
		if (account.getAuthority() < requestProduct.getAutority())
			return new MachineMessage.NoAuthority();

		int money = requestProduct.getMoneyIn();
		int productNumber = requestProduct.getProductNumber();
		if (!productQueue.checkAvailability(productNumber))
			return new MachineMessage.ProductNotAvailable(productNumber);
		if (money < productQueue.getPrice(productNumber))
			return new MachineMessage.InsufficientMoney(money);
		productQueue.retriveProduct(productNumber);
		mMoney += productQueue.getPrice(productNumber);
		return new MachineMessage.GiveProduct(new Product(productNumber), money
				- productQueue.getPrice(productNumber));

	}

	/**
	 * Operator remove money from the machine
	 * 
	 * @param removeMoney
	 * @return
	 */
	public Message handle(IAccount account,
			OperatorMessage.RemoveMoney removeMoney) {
		if (account.getAuthority() < removeMoney.getAutority())
			return new MachineMessage.NoAuthority();
		int money = mMoney;
		mMoney = 0;
		return new MachineMessage.RemoveMoney(money);
	}

	/**
	 * Operator restocks the product
	 * 
	 * @param restock
	 * @return
	 */
	public Message handle(IAccount account, OperatorMessage.Restock restock) {
		if (account.getAuthority() < restock.getAutority())
			return new MachineMessage.NoAuthority();
		int productNumber = restock.getProductNumber();
		int amount = restock.getAmount();
		productQueue.add(productNumber, amount);
		return null;
	}

	/**
	 * Operator register a product by setting the code for the product and the
	 * price of it.
	 * 
	 * @param account
	 * @return
	 */
	public Message handle(IAccount account,
			OperatorMessage.RegisterProduct registration) {
		if (account.getAuthority() < registration.getAutority())
			return new MachineMessage.NoAuthority();
		productQueue.registerProduct(registration.getProductNumber(),
				registration.getPrice());
		return null;

	}
}
