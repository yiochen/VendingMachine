package yiou.chen.vendorMachine.objects;

public class Product {
	private int mProductNumber;
	
	public Product(int productNumber){
		mProductNumber=productNumber;
	}
	
	public int getProductNumber() {
		return mProductNumber;
	}

	@Override
	public String toString() {
		return "Product [mProductNumber=" + mProductNumber + "]";
	}

}
