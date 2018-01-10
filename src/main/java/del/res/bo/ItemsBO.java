package del.res.bo;

import java.util.ArrayList;
import java.util.HashSet;


import del.res.dao.ItemsDAO;
import del.res.models.Item;

public class ItemsBO {
	
	private ItemsDAO itemsDAO;
	
	public ItemsBO() {
		this.itemsDAO = new ItemsDAO();
	}
	
	public ArrayList<Double> getTotals(HashSet<Integer> cartIDs){
		ArrayList<Double> totals = new ArrayList<Double> ();
		
		double preTax = 0.00;
		double taxCost = 0.00;
		double postTax = 0.00;
		
			ArrayList<Double> prices = itemsDAO.getPrices(cartIDs);
			for(double price : prices) {
				preTax += price;
			}
			taxCost = preTax * 0.0875;
			postTax = preTax + taxCost;
			
			totals.add(preTax);
			totals.add(taxCost);
			totals.add(postTax);
			
			return totals;
			
	}
					
	
	public  ArrayList<Item> getAllActiveItems(){
		return itemsDAO.getAllActiveItems();
	}
	
	public int getItemsCount() {
		return itemsDAO.getItemsCount();
	}
	
	public  ArrayList<Item> getCartItems(HashSet<Integer> cart){
		return itemsDAO.getCartItems(cart);
	}
	
	public  ArrayList<Item> getAllItems(){
		return itemsDAO.getAllItems();
	}
	
	public boolean updateItem(Item item) {
		return itemsDAO.updateItem(item);
	}
	
	public int addItem(Item item) {
		return itemsDAO.addItem(item);
	}
	
	public boolean removeItem(String itemID) {
		return itemsDAO.removeItem(itemID);
	}
	
	public Item getItemByID(String itemID) {
		return itemsDAO.getItemByID(itemID);
	}
	
}
