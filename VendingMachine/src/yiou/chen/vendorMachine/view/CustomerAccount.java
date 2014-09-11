package yiou.chen.vendorMachine.view;

import yiou.chen.vendorMachine.controller.Machine;
import yiou.chen.vendorMachine.message.CustomerMessage;
import yiou.chen.vendorMachine.message.MachineMessage;
import yiou.chen.vendorMachine.message.Message;
import yiou.chen.vendorMachine.objects.Product;

public class CustomerAccount implements IAccount {
	public Product product;
	public int moneyReturned;
	private Machine machine;
	
	public CustomerAccount(Machine machine) {
		this.machine=machine;
	}
	@Override
	public int getAuthority() {
		return Message.CUSTOMER;
	}
	public void buyProduct(int productNumber,int money){
		Message message=machine.handle(this, new CustomerMessage.RequestProduct(productNumber, money));
		if (message instanceof MachineMessage.GiveProduct){
			product=((MachineMessage.GiveProduct)message).getProduct();
			moneyReturned=((MachineMessage.GiveProduct)message).getChange();
		}
		if (message instanceof MachineMessage.InsufficientMoney){
			product=null;
			moneyReturned=((MachineMessage.InsufficientMoney)message).getMoney();
			
		}
	}
}
