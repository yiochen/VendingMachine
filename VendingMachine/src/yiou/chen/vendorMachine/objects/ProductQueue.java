package yiou.chen.vendorMachine.objects;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductQueue extends ArrayList<Integer> {
	private ArrayList<Integer> priceList;
	private HashMap<Integer, Integer> registrationList;

	/**
	 * Register a product. You can also use this method to change the price if
	 * the product is registered already.
	 * 
	 * @param productNumber
	 * @param price
	 * @return true if register successfully, false if unsuccessfully. Changing
	 *         price is consider successful
	 */
	public boolean registerProduct(int productNumber, int price) {
		if (productNumber < 0)
			return false;
		if (registrationList.containsKey(productNumber))
			priceList.set(registrationList.get(productNumber), price);
		else {
			this.add(0);
			priceList.add(price);
			registrationList.put(productNumber, this.size() - 1);
		}

		return true;
	}

	@Override
	public void add(int productNumber, Integer amount) {
		if (!registrationList.containsKey(productNumber)) throw new IllegalArgumentException("The product is not registered");
		else {
			int pos=registrationList.get(productNumber);
			this.set(pos, this.get(pos)+amount);
		}

	}

	public ProductQueue() {
		priceList = new ArrayList<>();
		registrationList = new HashMap<Integer, Integer>();
	}

	/**
	 * Check if a product is available.
	 * 
	 * @param productNumber
	 * @return true if the product is available, false unregister or all sold
	 *         out.
	 */
	public boolean checkAvailability(int productNumber) {
		if (!registrationList.containsKey(productNumber)) return false;
		if (this.get(registrationList.get(productNumber))>0) return true;
		return false;
	}

	/**
	 * Get the price of the product
	 * 
	 * @param productNumber
	 * @return the price, -1 if the productNumber doesnt match any product.
	 */
	public int getPrice(int productNumber){
		if (!registrationList.containsKey(productNumber)) return -1;
		return priceList.get(registrationList.get(productNumber));
	}

	public void retriveProduct(int productNumber) {
		if (!registrationList.containsKey(productNumber)) return;
		int pos=registrationList.get(productNumber);
		if (checkAvailability(productNumber)){
			this.set(pos, this.get(pos) - 1);
		}
			
	}
}
