package del.res.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import del.res.models.Item;
import del.res.models.ReceiptSummary;

public class ReceiptsDAO extends DAO {
	
	public  ReceiptSummary getReceiptSummary(int orderID){
		this.open();
		String sql = "SELECT TP_ORDERS.ORDER_PRETAX_REVENUE, TP_ORDERS.ORDER_TAX_REVENUE, TP_STORES.STORE_NAME, TP_STORES.STORE_ADDRESS, COUNT(TP_ORDER_ITEMS.ITEM_ID) " + 
				"FROM TP_ORDERS INNER JOIN TP_ORDER_ITEMS ON TP_ORDERS.ORDER_ID=TP_ORDER_ITEMS.ORDER_ID  INNER JOIN TP_STORES ON TP_ORDERS.STORE_ID=TP_STORES.STORE_ID " + 
				"WHERE TP_ORDERS.ORDER_ID=? " + 
				"GROUP BY TP_ORDERS.ORDER_PRETAX_REVENUE, TP_ORDERS.ORDER_TAX_REVENUE, TP_STORES.STORE_NAME, TP_STORES.STORE_ADDRESS";
		ReceiptSummary r = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, orderID);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Double orderPreTax = rs.getDouble(1);
				Double orderTax = rs.getDouble(2);
				String storeDescription = rs.getString(3);
				String storeAddress = rs.getString(4);
				int itemCount = rs.getInt(5);
				r = new ReceiptSummary(orderPreTax, orderTax, itemCount, storeDescription, storeAddress);
				this.close();
				return r;
			}
			else{
				this.close();
				return null;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			this.close();
			return null;
		}
	}
	
	public  boolean validateUser(int orderID, int userID){
		this.open();
		String sql = "SELECT * FROM TP_ORDERS WHERE ORDER_ID= ? AND USER_ID= ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, orderID);
			ps.setInt(2, userID);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				this.close();
				return true;
			}
			else {
				this.close();
				return false;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			this.close();
			return false;
		}
	}
	
	public  ArrayList<Item> getReceiptItems(int orderID){
		this.open();
		ArrayList<Item> items = new ArrayList<Item>();
		String sql = "SELECT ITEM_PICTURE, TO_CHAR(ITEM_PRICE,'$9,999.99'), ITEM_NAME " + 
				"FROM TP_ITEMS INNER JOIN TP_ORDER_ITEMS ON TP_ITEMS.ITEM_ID = TP_ORDER_ITEMS.ITEM_ID " + 
				"WHERE TP_ORDER_ITEMS.ORDER_ID = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, orderID);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Item item = new Item();
				item.setImageSrc(rs.getString(1));
				item.setItemPrice(rs.getString(2));
				item.setItemName(rs.getString(3));
				items.add(item);
			}
			this.close();
			return items;
		}
		catch (Exception e) {
			e.printStackTrace();
			this.close();
			return null;
		}
		
		
	}
}
