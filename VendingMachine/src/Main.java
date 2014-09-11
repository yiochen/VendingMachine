import yiou.chen.vendorMachine.controller.Machine;
import yiou.chen.vendorMachine.view.CustomerAccount;
import yiou.chen.vendorMachine.view.OperatorAccount;

public class Main {

	public static void main(String[] args) {
		Machine machine = new Machine();
		OperatorAccount operator = new OperatorAccount(machine);
		CustomerAccount customer = new CustomerAccount(machine);
		
		System.out.println("operator registers two kinds of products");
		operator.registerProduct(1, 10);
		operator.registerProduct(2, 20);
		
		System.out.println("operator restocks");
		operator.restock(1, 100);
		operator.restock(2,200);
		
		System.out.println("customer use 10 dollar to buy product 1");
		customer.buyProduct(1, 10);
		System.out.println("this is what the customer get from the machine"+customer.product.toString());
		System.out.println("the machine returned "+customer.moneyReturned);
		
		System.out.println("customer use 30 dollar to buy product 2");
		customer.buyProduct(2, 30);
		System.out.println("this is what the customer get from the machine"+customer.product.toString());
		System.out.println("the machine returned "+customer.moneyReturned);
		
		System.out.println("customer use 5 dollar to buy product 1");
		customer.buyProduct(1, 5);
		System.out.println("the machine returned "+customer.moneyReturned);
		
		System.out.println("Operator remove the money from the machine");
		operator.removeMoney();
		System.out.println("operator got "+operator.moneyTakenOut);
	}

}
