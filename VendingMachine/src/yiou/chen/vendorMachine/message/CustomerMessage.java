package yiou.chen.vendorMachine.message;

public class CustomerMessage {
	public static class RequestProduct extends Message{
		private int mProductNumber;
		private int mMoneyIn;
		public RequestProduct(int productNumber,int money){
			mProductNumber=productNumber;
			mMoneyIn=money;
		}
		public int getProductNumber() {
			return mProductNumber;
		}
		public int getMoneyIn() {
			return mMoneyIn;
		}
		@Override
		protected int setAutorityLevel() {
			return Message.CUSTOMER;
		}
		
	} 
	
}
