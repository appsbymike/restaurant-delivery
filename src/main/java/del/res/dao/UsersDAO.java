package del.res.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import del.res.models.User;

public class UsersDAO extends DAO {
	
	//Possible outcomes: User exists, user doesn't exist, error
	public  Boolean registerQuery(String username) {
		this.open();
		String sql = "SELECT USER_USERNAME FROM TP_USERS WHERE "
				+ "USER_USERNAME=?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			boolean result = rs.next();
			this.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.close();
			return false;
		}
		
	}
	
	//Possible outcomes: User exists, user doesn't exist, one or more fields are erroneous
	public int createUser(String username, String password, String firstname, String lastname, String gender, String address, String phone, String email) {
		this.open();
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
			int result = ps.executeUpdate();
			this.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.close();
			return 0;
		}
		
	}
	
	//Possible outcomes: User exists, user doesn't exist, error
	public String loginQuery(String username, String password){
		this.open();
		String sql = "SELECT USER_ID, USER_USERNAME, USER_PASSWORD FROM TP_USERS WHERE "
				+ "USER_USERNAME=? "
				+ " AND USER_PASSWORD=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String result = rs.getString(1);
				this.close();
				return result;
				}
			else {
				this.close();
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
		this.open();
		User a = null;
		String sql = "SELECT USER_FIRSTNAME, USER_LASTNAME, USER_PASSWORD, USER_ADDRESS, USER_PHONE, USER_EMAIL FROM TP_USERS "
				+ "WHERE USER_ID=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(userID));
			ResultSet rs = ps.executeQuery();
			rs.next();
			a = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
			this.close();
			return a;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.close();
			return null;
		}
	}
	
	//Possible outcomes: User exists, user doesn't exist, error
	public int updateUser(User info) {
		this.open();
		String sql = "UPDATE TP_USERS "
				+ "SET USER_FIRSTNAME=?,USER_LASTNAME=?,USER_PASSWORD=?,USER_ADDRESS=?,USER_PHONE=?,USER_EMAIL=? "
				+ "WHERE USER_ID=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			//Set the columns being changed
			ps.setString(1,info.getFirstname());
			ps.setString(2, info.getLastname());
			ps.setString(3, info.getPassword());
			ps.setString(4, info.getAddress());
			ps.setString(5, info.getPhone());
			ps.setString(6, info.getEmail());
			//Set the where clause
			ps.setInt(7, Integer.parseInt(info.getId()));
			
			int result = ps.executeUpdate();
			this.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.close();
			return 0;
		}
		
		
	}
	
	//Admin-specific query
		public boolean isAdmin(String userID) {
			this.open();
			String sql = "SELECT USER_ISADMIN FROM TP_USERS "
					+ "WHERE USER_ID=?";
			if(userID != null) {
				try {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, Integer.parseInt(userID));
					ResultSet rs = ps.executeQuery();
					rs.next();
					if(rs.getInt(1) == 1) {
						this.close();
						return true;
					}
					else {
						this.close();
						return false;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					this.close();
					return false;
				}
			}
			else {
				this.close();
				return false;
			}
		}
	
	//Admin-specific query
	public ArrayList<User> getNormalUsers(){
		this.open();
		String sql = "SELECT USER_FIRSTNAME, USER_LASTNAME, USER_PHONE, USER_EMAIL, USER_ID FROM TP_USERS WHERE USER_ISADMIN=0";
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			ResultSet rs = conn.prepareStatement(sql).executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setFirstname(rs.getString(1));
				user.setLastname(rs.getString(2));
				user.setPhone(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setId(rs.getString(5));
				users.add(user);
			}
			this.close();
			return users;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.close();
			return users;
		}
		
	}
	
	//Only used in test cases
	public void deleteUser(String username) {
		this.open();
		String sql = "DELETE FROM TP_USERS WHERE USER_USERNAME=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.executeUpdate();
			this.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.close();
		}
		
	}
}
