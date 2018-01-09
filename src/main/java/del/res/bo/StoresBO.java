package del.res.bo;


import java.util.ArrayList;

import del.res.dao.StoresDAO;
import del.res.models.Store;

public class StoresBO {
	
	StoresDAO storesDAO;
	
	public StoresBO() {
		storesDAO = new StoresDAO();
	}
	
	public ArrayList<Store> getAllStores(){
		return storesDAO.getAllStores();
	}
	
	public int createStore(Store store) {
		return storesDAO.createStore(store);
	}
	
	public boolean updateStore(Store store) {
		return storesDAO.updateStore(store);
	}
	
	public boolean removeStore(String storeID) {
		return storesDAO.removeStore(storeID);
	}
	public Store getStoreByID(String storeID) {
		return storesDAO.getStoreByID(storeID);
	}
}