package del.res.models;

import del.res.utilities.toCurrency;

public class ReceiptSummary {
	private String orderTotal;
	private String orderTax;
	private int itemCount;
	
	//NOTE: This is actually the store name
	private String storeDescription;
	private String storeAddress;
	private String orderID;
	
	public ReceiptSummary() {}
	
	public ReceiptSummary(Double orderPreTax, Double orderTax, int itemCount, String storeDescription, String storeAddress) {
		this.orderTotal = "$" + toCurrency.format(orderPreTax + orderTax);
		this.orderTax = "$" + toCurrency.format(orderTax);
		this.itemCount = itemCount;
		this.storeDescription = storeDescription;
		this.storeAddress = storeAddress;
	}
	
	public String getOrderTotal() {
		return this.orderTotal;
	}
	public String getOrderTax() {
		return this.orderTax;
	}
	public int getItemCount() {
		return this.itemCount;
	}
	public String getStoreDescription() {
		return this.storeDescription;
	}
	public String getStoreAddress() {
		return this.storeAddress;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public void setOrderTotal(String orderTotal) {
		this.orderTotal = orderTotal;
	}

	public void setOrderTax(String orderTax) {
		this.orderTax = orderTax;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public void setStoreDescription(String storeDescription) {
		this.storeDescription = storeDescription;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

}
