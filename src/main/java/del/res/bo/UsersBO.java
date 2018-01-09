package del.res.bo;

import java.util.ArrayList;

import del.res.dao.UsersDAO;
import del.res.models.User;

public class UsersBO {
	UsersDAO usersDAO;
	
	public UsersBO() {
		usersDAO = new UsersDAO();
	}
	
	public  Boolean registerQuery(String username){
		return usersDAO.registerQuery(username);
	}
	
	public  int createUser(String username, String password, String firstname, String lastname, String gender, String address, String phone, String email){
		if (registerQuery(username) != true) {
			return usersDAO.createUser(username, password, firstname, lastname, gender, address, phone, email);
		}
		else {
			return 0;
		}
	}
	
	public  String loginQuery(String username, String password) {
		return usersDAO.loginQuery(username, password);
	}
	
	public boolean isAdmin(String userID) {
		return usersDAO.isAdmin(userID);
	}
	
	public  User getDetails(String userID) {
		return usersDAO.getDetails(userID);
	}
	public int updateUser(User info) {
		return usersDAO.updateUser(info);
	}
	
	public ArrayList<User> getNormalUsers(){
		return usersDAO.getNormalUsers();
	}
	
	public void deleteUser(String username) {
		usersDAO.deleteUser(username);
	}
}
