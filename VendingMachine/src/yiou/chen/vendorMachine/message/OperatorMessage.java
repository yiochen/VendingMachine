package yiou.chen.vendorMachine.message;

public class OperatorMessage {
	public static class Restock extends Message {
		private int mProductNumber;
		private int mAmount;

		public Restock(int productNumber, int amount) {
			mProductNumber = productNumber;
			mAmount = amount;
		}

		@Override
		protected int setAutorityLevel() {
			return Message.OPERATOR;
		}

		public int getProductNumber() {
			return mProductNumber;
		}

		public int getAmount() {
			return mAmount;
		}

	}

	public static class RemoveMoney extends Message {
		@Override
		protected int setAutorityLevel() {
			return Message.OPERATOR;
		}

	}

	public static class RegisterProduct extends Message {
		private int mProductNumber;
		private int mPrice;

		public RegisterProduct(int productNumber, int price) {
			mProductNumber = productNumber;
			mPrice = price;
		}

		@Override
		protected int setAutorityLevel() {
			return Message.OPERATOR;
		}

		public int getProductNumber() {
			return mProductNumber;
		}

		public int getPrice() {
			return mPrice;
		}
	}
}
