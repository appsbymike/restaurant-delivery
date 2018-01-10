package del.res.bo;

import java.util.ArrayList;

import del.res.dao.ReceiptsDAO;
import del.res.models.Item;
import del.res.models.ReceiptSummary;

public class ReceiptsBO {
	public ReceiptSummary getReceiptSummary(int orderID){
		ReceiptsDAO receiptsDAO = new ReceiptsDAO();
		return receiptsDAO.getReceiptSummary(orderID);
	}
	
	public  boolean validateUser(int orderID, int userID){
		ReceiptsDAO receiptsDAO = new ReceiptsDAO();
		return receiptsDAO.validateUser(orderID, userID);
	}
	
	public  ArrayList<Item> getReceiptItems(int orderID){
		ReceiptsDAO receiptsDAO = new ReceiptsDAO();
		return receiptsDAO.getReceiptItems(orderID);
	}
}

