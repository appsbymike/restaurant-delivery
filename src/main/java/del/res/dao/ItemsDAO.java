package del.res.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;

import del.res.models.Item;
import del.res.utilities.db_interact;

public class ItemsDAO {
	
	public  ArrayList<Item> getAllActiveItems(){
		String sql = "SELECT "
				+ "ITEM_PICTURE, "
				+ "ITEM_NAME, "
				+ "ITEM_DESC, "
				+ "TO_CHAR(ITEM_PRICE,'FM$9,999.00'), "
				+ "ITEM_ID,"
				+ "ITEM_ISACTIVE "
				+ "FROM TP_ITEMS WHERE ITEM_ISACTIVE=1";
		ArrayList<Item> menu = new ArrayList<Item> ();
		
		try {
			ResultSet rs = db_interact.newDatabase().createStatement().executeQuery(sql);
			while(rs.next()) {
				Item item = new Item();
				//Individual Item
				item.setImageSrc(rs.getString(1));
				item.setItemName(rs.getString(2));
				item.setItemDesc(rs.getString(3));
				item.setItemPrice(rs.getString(4));
				item.setItemID(rs.getString(5));
				item.setItemIsActive(rs.getString(6));
				menu.add(item);
			}
			return menu;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
	}
	
	public int getItemsCount(){
		int i = 0;
		try {
			String sql = "SELECT COUNT(*)"
					+ " FROM TP_ITEMS";
			ResultSet rs = db_interact.newDatabase().createStatement().executeQuery(sql);
			rs.next();
			i=rs.getInt(1);
			return i;
		}
		catch (Exception e) {
			return i;
		}
		
	}
	
	public  ArrayList<Item> getCartItems(HashSet<Integer> cart){
		ArrayList<Item> items = new ArrayList<Item> ();
		
		Connection conn = db_interact.newDatabase();
		String sql = "SELECT ITEM_PICTURE, ITEM_NAME, TO_CHAR(ITEM_PRICE,'FM$9,999.00'), ITEM_ID FROM TP_ITEMS WHERE ITEM_ID=?";
		
		try {
			for(int id : cart) {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, id);
					ResultSet rs = ps.executeQuery();
					rs.next();
					Item r = new Item();
					r.setImageSrc(rs.getString(1));
					r.setItemName(rs.getString(2));
					r.setItemPrice(rs.getString(3));
					r.setItemID(rs.getString(4));
					items.add(r);
					ps.close();
				}
			conn.close();
			return items;
		
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public  ArrayList<Double> getPrices(HashSet<Integer> cartIDs) {
		String sql = "SELECT ITEM_PRICE FROM TP_ITEMS WHERE ITEM_ID=?";
		Connection conn = db_interact.newDatabase();
		PreparedStatement ps;
		ArrayList<Double> prices = new ArrayList<Double>();
		try {
			for(int id : cartIDs) {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					prices.add(rs.getDouble(1));
				}
				ps.close();
			}
			return prices;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public  ArrayList<Item> getAllItems(){
		String sql = "SELECT "
				+ "ITEM_PICTURE, "
				+ "ITEM_NAME, "
				+ "ITEM_DESC, "
				+ "TO_CHAR(ITEM_PRICE,'FM9,999.00'), "
				+ "ITEM_ISACTIVE, "
				+ "ITEM_CATEGORY, "
				+ "ITEM_ID "
				+ "FROM TP_ITEMS "
				+ "ORDER BY ITEM_ID";
		ArrayList<Item> menu = new ArrayList<Item> ();
		
		try {
			ResultSet rs = db_interact.newDatabase().createStatement().executeQuery(sql);
			while(rs.next()) {
				Item item = new Item();
				//Individual Item
				item.setImageSrc(rs.getString(1));
				item.setItemName(rs.getString(2));
				item.setItemDesc(rs.getString(3));
				item.setItemPrice(rs.getString(4));
				item.setItemIsActive(rs.getString(5));
				item.setItemCategory(rs.getString(6));
				item.setItemID(rs.getString(7));
				
				
				menu.add(item);
			}
			return menu;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
	}
	
	//Admin-specific query
	public boolean updateItem(Item item) {
		String sql = "UPDATE TP_ITEMS SET " + 
				"ITEM_NAME=?, " + 
				"ITEM_DESC=?, " + 
				"ITEM_PRICE=?, " + 
				"ITEM_PICTURE=?, " + 
				"ITEM_ISACTIVE=?, " + 
				"ITEM_CATEGORY=? " + 
				"WHERE ITEM_ID=?";
		try {
			PreparedStatement ps = db_interact.newDatabase().prepareStatement(sql);
			ps.setString(1, item.getItemName());
			ps.setString(2, item.getItemDesc());
			ps.setDouble(3, Double.parseDouble(item.getItemPrice()));
			ps.setString(4, item.getImageSrc());
			ps.setInt(5, Integer.parseInt(item.getItemIsActive()));
			ps.setString(6, item.getItemCategory());
			ps.setInt(7, Integer.parseInt(item.getItemID()));
			int result = ps.executeUpdate();
			return (result != 0);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	//Admin-specific query
	public int addItem(Item item) {
		String sql = "INSERT INTO TP_ITEMS (ITEM_NAME,ITEM_DESC,ITEM_PRICE,ITEM_PICTURE,ITEM_ISACTIVE,ITEM_CATEGORY) VALUES " + 
				"(?,?,?,?,?,?)";
		try {
			PreparedStatement ps = db_interact.newDatabase().prepareStatement(sql,new String[] {"ITEM_ID"});
			ps.setString(1, item.getItemName());
			ps.setString(2, item.getItemDesc());
			ps.setDouble(3, Double.parseDouble(item.getItemPrice()));
			ps.setString(4, item.getImageSrc());
			ps.setInt(5, Integer.parseInt(item.getItemIsActive()));
			ps.setString(6, item.getItemCategory());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			int key = 0;
			if(rs.next()) {
				key = rs.getInt(1);
			}
			return key;
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}
	
	//Admin-specific query
	public boolean removeItem(String itemID) {
		String sql = "DELETE FROM TP_ITEMS WHERE ITEM_ID=?";
		try {
			PreparedStatement ps = db_interact.newDatabase().prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(itemID));
			int result = ps.executeUpdate();
			return (result != 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public Item getItemByID(String itemID) {
		Item item = new Item();
		String sql = "SELECT "
				+ "ITEM_NAME, "
				+ "ITEM_DESC, "
				+ "TO_CHAR(ITEM_PRICE,'FM9,999.00'), "
				+ "ITEM_PICTURE, "
				+ "ITEM_ISACTIVE, "
				+ "ITEM_CATEGORY, "
				+ "ITEM_ID "
				+ "FROM TP_ITEMS "
				+ "WHERE ITEM_ID=?";
		try {
			PreparedStatement ps = db_interact.newDatabase().prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(itemID));
			ResultSet rs = ps.executeQuery();
			rs.next();
			item.setItemName(rs.getString(1));
			item.setItemDesc(rs.getString(2));
			item.setItemPrice(rs.getString(3));
			item.setImageSrc(rs.getString(4));
			item.setItemIsActive(rs.getString(5));
			item.setItemCategory(rs.getString(6));
			item.setItemID(rs.getString(7));
			return item;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
