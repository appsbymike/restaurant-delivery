package del.res.dao;

import java.sql.*;
import java.util.ArrayList;

import del.res.models.Item;
import del.res.models.PastOrder;
import del.res.models.ReceiptSummary;
import del.res.utilities.db_interact;

public class OrdersDAO {
	
	//Tested in OrderModule
	public int createOrder(String user_id, String store_id, String cc_number, String sec_number, String zipcode, String pretax_revenue, String tax_revenue){
		int key = 0;
		System.out.println("User ID: " + user_id + "\nStore ID: " + store_id + "\nCC Number: " + cc_number + "\nSecurity Number: " + sec_number + "\nZipcode: " + zipcode + "\nPretax Revenue: " + pretax_revenue + "\nTax Revenue: " + tax_revenue);
		String sql = "INSERT INTO TP_ORDERS (user_id,store_id,order_cc_number,order_sec_number,order_zipcode,order_pretax_revenue,order_tax_revenue) "
				+ "VALUES (?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = db_interact.newDatabase().prepareStatement(sql,new String[] {"ORDER_ID"});
			ps.setInt(1,Integer.parseInt(user_id));
			ps.setInt(2,Integer.parseInt(store_id));
			ps.setInt(3,Integer.parseInt(cc_number));
			ps.setInt(4,Integer.parseInt(sec_number));
			ps.setInt(5,Integer.parseInt(zipcode));
			ps.setDouble(6,Double.parseDouble(pretax_revenue));
			ps.setDouble(7,Double.parseDouble(tax_revenue));
			System.out.println("Test");
			ps.executeUpdate();
			System.out.println("What");
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				key = rs.getInt(1);
			}
			return key;
		}
		catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	public int addItemToOrder(int orderID, int itemID) throws SQLException, Exception {
		String sql = "INSERT INTO TP_ORDER_ITEMS VALUES (?,?)";
		PreparedStatement ps = db_interact.newDatabase().prepareStatement(sql);
		ps.setInt(1, orderID);
		ps.setInt(2, itemID);
		System.out.println("Creating Order Item with following info: --------------------------------------------------");
		System.out.println(sql);
		System.out.println("-------------------------------------------------------------------------------------------");
		return ps.executeUpdate();
	}
	
	public ArrayList<PastOrder> getOrdersByUser(int userID){
		//Define sql queries to use
		String sql = "SELECT ORDER_ID FROM TP_ORDERS WHERE USER_ID=? ORDER BY ORDER_ID DESC";
		String itemsSQL = "SELECT ITEM_PICTURE, TO_CHAR(ITEM_PRICE,'$9,999.99'), ITEM_NAME " + 
				"FROM TP_ITEMS INNER JOIN TP_ORDER_ITEMS ON TP_ITEMS.ITEM_ID = TP_ORDER_ITEMS.ITEM_ID " + 
				"WHERE TP_ORDER_ITEMS.ORDER_ID = ?";
		String summarySQL = "SELECT TP_ORDERS.ORDER_PRETAX_REVENUE, TP_ORDERS.ORDER_TAX_REVENUE, TP_STORES.STORE_NAME, TP_STORES.STORE_ADDRESS, COUNT(TP_ORDER_ITEMS.ITEM_ID) " + 
				"FROM TP_ORDERS INNER JOIN TP_ORDER_ITEMS ON TP_ORDERS.ORDER_ID=TP_ORDER_ITEMS.ORDER_ID  INNER JOIN TP_STORES ON TP_ORDERS.STORE_ID=TP_STORES.STORE_ID " + 
				"WHERE TP_ORDERS.ORDER_ID=? " + 
				"GROUP BY TP_ORDERS.ORDER_PRETAX_REVENUE, TP_ORDERS.ORDER_TAX_REVENUE, TP_STORES.STORE_NAME, TP_STORES.STORE_ADDRESS";
		
		//Define model for results to be placed in
		ArrayList<PastOrder> orders = new ArrayList<PastOrder>();
		//Create connection to be used
		Connection conn = db_interact.newDatabase();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();
			ArrayList<Integer> orderIDs = new ArrayList<Integer>();
			while(rs.next()) {
				Integer id = rs.getInt(1);
				orderIDs.add(id);
			}
			ps.close();
			
			for(Integer id : orderIDs) {
				ArrayList<Item> items = new ArrayList<Item>();
				ReceiptSummary summary = null;
				
				//Add items to ArrayList<ReceiptItem>
				ps = conn.prepareStatement(itemsSQL);
				ps.setInt(1, id);
				ResultSet rsItems = ps.executeQuery();
				while(rsItems.next()) {
					Item item = new Item();
					item.setImageSrc(rsItems.getString(1));
					item.setItemPrice(rsItems.getString(2));
					item.setItemName(rsItems.getString(3));
					items.add(item);
				}
				ps.close();
				
				//Get ReceiptSummary
				ps=conn.prepareStatement(summarySQL);
				ps.setInt(1, id);
				ResultSet rsSummary = ps.executeQuery();
				if(rsSummary.next()) {
					Double orderPreTax = rsSummary.getDouble(1);
					Double orderTax = rsSummary.getDouble(2);
					String storeDescription = rsSummary.getString(3);
					String storeAddress = rsSummary.getString(4);
					int itemCount = rsSummary.getInt(5);
					summary = new ReceiptSummary(orderPreTax, orderTax, itemCount, storeDescription, storeAddress);
				}
				ps.close();
				
				if(items != null && summary != null) {
					//Add ArrayList<ReceiptItem> and ReceiptSummary to PastOrder
					PastOrder order = new PastOrder(items,summary);
					
					//Add PastOrder to ArrayList<PastOrder>
					orders.add(order);
				}
				
			}
			return orders;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
			
		}
	}
	
	//Admin-specific query
	public ArrayList<ReceiptSummary> getAllOrders(){
		ArrayList<ReceiptSummary> list = new ArrayList<ReceiptSummary>();
		String sql = "SELECT COUNT(ITEM_ID), TO_CHAR((ORDER_PRETAX_REVENUE + ORDER_TAX_REVENUE),'FM$9,999.00'), TP_ORDERS.ORDER_ID FROM TP_ORDERS INNER JOIN TP_ORDER_ITEMS ON TP_ORDERS.ORDER_ID=TP_ORDER_ITEMS.ORDER_ID\r\n" + 
				"GROUP BY TP_ORDERS.ORDER_ID, (ORDER_PRETAX_REVENUE + ORDER_TAX_REVENUE), (ORDER_PRETAX_REVENUE,ORDER_TAX_REVENUE)\r\n" + 
				"ORDER BY TP_ORDERS.ORDER_ID ";
		try {
			PreparedStatement ps = db_interact.newDatabase().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ReceiptSummary summary = new ReceiptSummary();
				summary.setItemCount(rs.getInt(1));
				summary.setOrderTotal(rs.getString(2));
				summary.setOrderID(rs.getString(3));
				
				list.add(summary);
			}
			
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	//Test-specific query
	public boolean removeOrder(String orderID) {
		String sql = "DELETE FROM TP_ORDERS WHERE ORDER_ID=?";
		try {
			PreparedStatement ps = db_interact.newDatabase().prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(orderID));
			int results = ps.executeUpdate();
			if(results == 0) {
				return false;
			}
			else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean removeOrderItem(int orderID, int itemID) {
		String sql = "DELETE FROM TP_ORDER_ITEMS WHERE ORDER_ID=? AND ITEM_ID=?";
		try {
			PreparedStatement ps = db_interact.newDatabase().prepareStatement(sql);
			ps.setInt(1, orderID);
			ps.setInt(2, itemID);
			int results = ps.executeUpdate();
			return (results != 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
}
