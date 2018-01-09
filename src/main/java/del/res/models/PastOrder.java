package del.res.models;

import java.util.ArrayList;

public class PastOrder {
	private ArrayList<Item> items = null;
	private ReceiptSummary summary = null;
	
	public PastOrder(ArrayList<Item> items , ReceiptSummary s){
		this.items = items;
		this.summary= s;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public ReceiptSummary getSummary() {
		return summary;
	}
	
	
}
