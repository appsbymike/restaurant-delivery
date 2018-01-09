package del.res.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import del.res.models.User;
import del.res.utilities.db_interact;

public class UsersDAO {
	
	//Possible outcomes: User exists, user doesn't exist, error
	public  Boolean registerQuery(String username) {
		Connection conn = db_interact.newDatabase();
		String sql = "SELECT USER_USERNAME FROM TP_USERS WHERE "
				+ "USER_USERNAME=?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	//Possible outcomes: User exists, user doesn't exist, one or more fields are erroneous
	public int createUser(String username, String password, String firstname, String lastname, String gender, String address, String phone, String email) {
		Connection conn = db_interact.newDatabase();
		String sql = "INSERT INTO TP_USERS(USER_USERNAME, USER_PASSWORD, USER_FIRSTNAME, USER_LASTNAME, USER_GENDER, USER_ADDRESS, USER_PHONE, USER_EMAIL) VALUES "
				+ "(?,?,?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, firstname);
			ps.setString(4, lastname);
			ps.setString(5, gender);
			ps.setString(6, address);
			ps.setString(7, phone);
			ps.setString(8, email);
			System.out.println(ps);
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}
	
	//Possible outcomes: User exists, user doesn't exist, error
	public String loginQuery(String username, String password){
		Connection conn = db_interact.newDatabase();
		String sql = "SELECT USER_ID, USER_USERNAME, USER_PASSWORD FROM TP_USERS WHERE "
				+ "USER_USERNAME=? "
				+ " AND USER_PASSWORD=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
				}
			else {
				return null;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//Possible outcomes: Existing ID, non-existing ID, error
	public  User getDetails(String userID) {
		User a = null;
		String sql = "SELECT USER_FIRSTNAME, USER_LASTNAME, USER_PASSWORD, USER_ADDRESS, USER_PHONE, USER_EMAIL FROM TP_USERS "
				+ "WHERE USER_ID=?";
		try {
			PreparedStatement ps = db_interact.newDatabase().prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(userID));
			ResultSet rs = ps.executeQuery();
			rs.next();
			a = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
			return a;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//Possible outcomes: User exists, user doesn't exist, error
	public int updateUser(User info) {
		String sql = "UPDATE TP_USERS "
				+ "SET USER_FIRSTNAME=?,USER_LASTNAME=?,USER_PASSWORD=?,USER_ADDRESS=?,USER_PHONE=?,USER_EMAIL=? "
				+ "WHERE USER_ID=?";
		try {
			PreparedStatement ps = db_interact.newDatabase().prepareStatement(sql);
			//Set the columns being changed
			ps.setString(1,info.getFirstname());
			ps.setString(2, info.getLastname());
			ps.setString(3, info.getPassword());
			ps.setString(4, info.getAddress());
			ps.setString(5, info.getPhone());
			ps.setString(6, info.getEmail());
			//Set the where clause
			ps.setInt(7, Integer.parseInt(info.getId()));
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
		
	}
	
	//Admin-specific query
		public boolean isAdmin(String userID) {
			String sql = "SELECT USER_ISADMIN FROM TP_USERS "
					+ "WHERE USER_ID=?";
			if(userID != null) {
				try {
					PreparedStatement ps = db_interact.newDatabase().prepareStatement(sql);
					ps.setInt(1, Integer.parseInt(userID));
					ResultSet rs = ps.executeQuery();
					rs.next();
					if(rs.getInt(1) == 1) {
						return true;
					}
					else {
						return false;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
			}
			else {
				return false;
			}
		}
	
	//Admin-specific query
	public ArrayList<User> getNormalUsers(){
		String sql = "SELECT USER_FIRSTNAME, USER_LASTNAME, USER_PHONE, USER_EMAIL, USER_ID FROM TP_USERS WHERE USER_ISADMIN=0";
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			ResultSet rs = db_interact.newDatabase().prepareStatement(sql).executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setFirstname(rs.getString(1));
				user.setLastname(rs.getString(2));
				user.setPhone(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setId(rs.getString(5));
				users.add(user);
			}
			return users;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return users;
		}
		
	}
	
	//Only used in test cases
	public void deleteUser(String username) {
		String sql = "DELETE FROM TP_USERS WHERE USER_USERNAME=?";
		try {
			PreparedStatement ps = db_interact.newDatabase().prepareStatement(sql);
			ps.setString(1, username);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
