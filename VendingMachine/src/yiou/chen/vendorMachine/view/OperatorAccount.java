package yiou.chen.vendorMachine.view;

import yiou.chen.vendorMachine.controller.Machine;
import yiou.chen.vendorMachine.message.MachineMessage;
import yiou.chen.vendorMachine.message.Message;
import yiou.chen.vendorMachine.message.OperatorMessage;

public class OperatorAccount implements IAccount {
	public int moneyTakenOut;
	private Machine machine;
	public OperatorAccount(Machine machine){
		this.machine=machine;
	}
	@Override
	public int getAuthority() {
		return Message.OPERATOR;
	}
	public void removeMoney(){
		Message message=machine.handle(this, new OperatorMessage.RemoveMoney());
		if (message instanceof MachineMessage.RemoveMoney)
			moneyTakenOut=((MachineMessage.RemoveMoney)message).getMoney();
	}
	public void registerProduct(int productNumber, int price){
		machine.handle(this, new OperatorMessage.RegisterProduct(productNumber, price));
	}
	public void restock(int productNumber, int amount){
		machine.handle(this, new OperatorMessage.Restock(productNumber, amount));
	}
}
