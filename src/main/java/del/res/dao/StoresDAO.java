package del.res.dao;


import java.sql.*;
import java.util.ArrayList;

import del.res.models.Store;
public class StoresDAO extends DAO {
	
	//Nothing to really test...
	public  ArrayList<Store> getAllStores(){
		this.open();
		ArrayList<Store> stores = new ArrayList<Store> ();
		String sql =
				"SELECT STORE_NAME, STORE_DESC, STORE_ADDRESS, STORE_ZIPCODE, STORE_ADD_DESC, STORE_NUMBERSTAFF, STORE_PICTURE, STORE_ID "
				+ "FROM TP_STORES";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Store store = new Store();
				store.setStoreName(rs.getString(1));
				store.setStoreDesc(rs.getString(2));
				store.setStoreAddress(rs.getString(3));
				store.setStoreZipcode(rs.getString(4));
				store.setStoreAddDesc(rs.getString(5));
				store.setStoreStaffCount(rs.getString(6));
				store.setImageSrc(rs.getString(7));
				store.setStoreID(rs.getString(8));
				
				stores.add(store);
			}

			this.close();
			return stores;
		} catch (Exception e) {
			e.printStackTrace();
			this.close();
			return null;
		}
	}
	
	//Admin-specific query
	public boolean updateStore(Store store) {
		this.open();
		String sql = "UPDATE TP_STORES SET "
				+ "STORE_NAME=?, "
				+ "STORE_DESC=?,"
				+ "STORE_ADDRESS=?,"
				+ "STORE_ZIPCODE=?,"
				+ "STORE_ADD_DESC=?,"
				+ "STORE_NUMBERSTAFF=?,"
				+ "STORE_PICTURE=? "
				+ "WHERE STORE_ID=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, store.getStoreName());
			ps.setString(2,store.getStoreDesc());
			ps.setString(3, store.getStoreAddress());
			ps.setInt(4, Integer.parseInt(store.getStoreZipcode()));
			ps.setString(5, store.getStoreAddDesc());
			ps.setString(6, store.getStoreStaffCount());
			ps.setString(7, store.getImageSrc());
			ps.setInt(8, Integer.parseInt(store.getStoreID()));
			ps.executeQuery();

			this.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.close();
			return false;
		}
		
	}
	
	//Admin-specific query
	public boolean removeStore(String storeID) {
		this.open();
		String sql = "DELETE FROM TP_STORES WHERE STORE_ID=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(storeID));
			ps.executeQuery();
			this.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.close();
			return false;
		}
		
	}
	
	//Admin-specific query
	public int createStore(Store store) {
		this.open();
		String sql = "INSERT INTO TP_STORES (STORE_NAME, STORE_DESC, STORE_ADDRESS, STORE_ZIPCODE, STORE_ADD_DESC, STORE_NUMBERSTAFF, STORE_PICTURE) VALUES "
				+ "(?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql,new String[] {"STORE_ID"});
			ps.setString(1, store.getStoreName());
			ps.setString(2,store.getStoreDesc());
			ps.setString(3, store.getStoreAddress());
			ps.setInt(4, Integer.parseInt(store.getStoreZipcode()));
			ps.setString(5, store.getStoreAddDesc());
			ps.setString(6, store.getStoreStaffCount());
			ps.setString(7, store.getImageSrc());
			ps.executeUpdate();
			System.out.println("What");
			ResultSet rs = ps.getGeneratedKeys();
			int key = 0;
			if(rs.next()) {
				key = rs.getInt(1);
			}
			this.close();
			return key;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.close();
			return 0;
		}
		
	}
	
	public Store getStoreByID(String storeID) {
		this.open();
		String sql = "SELECT STORE_NAME, STORE_DESC, STORE_ADDRESS, STORE_ZIPCODE, STORE_ADD_DESC, STORE_NUMBERSTAFF, STORE_PICTURE "
				+ "FROM TP_STORES WHERE STORE_ID=?";
		Store store = new Store();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(storeID));
			ResultSet rs = ps.executeQuery();
			rs.next();
			store.setStoreName(rs.getString(1));
			store.setStoreDesc(rs.getString(2));
			store.setStoreAddress(rs.getString(3));
			store.setStoreZipcode(rs.getString(4));
			store.setStoreAddDesc(rs.getString(5));
			store.setStoreStaffCount(rs.getString(6));
			store.setImageSrc(rs.getString(7));
			store.setStoreID(storeID);
			this.close();
			return store;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.close();
			return null;
		}
	}
	
}