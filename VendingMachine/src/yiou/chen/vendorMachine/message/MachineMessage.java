package yiou.chen.vendorMachine.message;

import yiou.chen.vendorMachine.objects.Product;

public class MachineMessage {
	public static class InsufficientMoney extends Message{
		private int mMoney;
		public int getMoney() {
			return mMoney;
		}
		public InsufficientMoney(int money) {
			mMoney=money;
		}
		@Override
		protected int setAutorityLevel() {
			return Message.CUSTOMER;
		}
		@Override
		public String showDiscription() {
			return "Insufficient Money";
		}
	}
	public static class GiveProduct extends Message{

		private Product mProduct;
		private int mChange;
		
		public GiveProduct(Product product,int change) {
			mProduct=product;		
			mChange=change;
		}
		@Override
		protected int setAutorityLevel() {
			return Message.CUSTOMER;
		}
		@Override
		public String showDiscription() {
			return super.showDiscription();
		}
		public Product getProduct() {
			return mProduct;
		}
		public int getChange(){
			return mChange;
		}
	}
	public static class ProductNotAvailable extends Message{

		private int mProductNumber;
		public ProductNotAvailable(int productNumber) {
			mProductNumber=productNumber;
		}
		@Override
		protected int setAutorityLevel() {
			return Message.CUSTOMER;
		}
		public int getProductNumber() {
			return mProductNumber;
		}
		@Override
		public String showDiscription() {
			return "Product not available";
		}
	}
	public static class NoAuthority extends Message{

		@Override
		protected int setAutorityLevel() {
			return Message.NO_AUTHORITY;
		}
		
	}
	public static class RemoveMoney extends Message{

		private int mMoney;
		public int getMoney() {
			return mMoney;
		}
		public RemoveMoney(int money) {
			mMoney=money;
		}
		@Override
		protected int setAutorityLevel() {
			return Message.OPERATOR;
		}
		
	}
}
