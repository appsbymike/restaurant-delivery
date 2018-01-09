package del.res.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import del.res.dao.OrdersDAO;
import del.res.models.PastOrder;
import del.res.models.ReceiptSummary;

public class OrdersBO {
	
	OrdersDAO ordersDAO;
	
	public OrdersBO() {
		ordersDAO = new OrdersDAO();
	}
	
	public int createOrder(String user_id, String store_id, String cc_number, String sec_number, String zipcode, String pretax_revenue, String tax_revenue){
		return ordersDAO.createOrder(user_id, store_id, cc_number, sec_number, zipcode, pretax_revenue, tax_revenue);
	}
	
	public int addItemToOrder(int orderID, int itemID) throws SQLException, Exception {
		return ordersDAO.addItemToOrder(orderID, itemID);
	}
	
	public ArrayList<PastOrder> getOrdersByUser(int userID){
		return ordersDAO.getOrdersByUser(userID);
	}
	
	public ArrayList<ReceiptSummary> getAllOrders(){
		return ordersDAO.getAllOrders();
	}
	
	//Below only used in tests =============================================== //
	
	public boolean deleteOrder(String orderID) {
		return ordersDAO.removeOrder(orderID);
	}
	
	public boolean deleteOrderItem(int orderID, int itemID) {
		return ordersDAO.removeOrderItem(orderID, itemID);
	}
}
