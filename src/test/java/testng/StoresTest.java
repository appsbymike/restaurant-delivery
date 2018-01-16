package testng;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.hamcrest.core.Every;
import org.hamcrest.core.IsInstanceOf;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import del.res.bo.StoresBO;
import del.res.models.Store;

public class StoresTest {
	String res = System.getProperty("user.dir") + "\\src\\main\\resources\\testng\\StoresTestRes\\";
	
	StoresBO storesBO;
	boolean createdStore;
	int createdStoreID;
	boolean updatedStore;
	Store oldStore;
	
	@BeforeMethod(alwaysRun=true)
	public void before() {
		storesBO = new StoresBO();
		createdStore = false;
		createdStoreID = 0;
		updatedStore = false;
		oldStore = new Store();
	}
	
	//getAllStores()
	//Takes: nothing
	//Returns: (ArrayList<Store>) all stores in database
	@Test(groups= {"functest", "usertest"})
	public void getAllStoresTest() {
		ArrayList<Store> result = storesBO.getAllStores();
		assertThat(result, Every.everyItem(IsInstanceOf.instanceOf(Store.class)));
	}
	
	//Admin Queries ===================================== //
	
	//updateStore
	//Takes: <Store> all updated store info
	//Returns: (Boolean) whether or not update was successful
	//Datastore: <Store> storeName, storeDesc, storeAddress, storeZipcode, storeAddDesc, storeStaffCount, imageSrc, storeID
	@DataProvider(name="updateStore")
	public Iterator<Object[]> updateStoreData() throws IOException{
		ArrayList<Object[]> params = new ArrayList<Object[]>();
		File f = new File(res + "updateStore.csv");
		Reader in = new FileReader(f);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
		for(CSVRecord record : records) {
			Store store = new Store();
			store.setStoreName(record.get("name"));
			store.setStoreDesc(record.get("desc"));
			store.setStoreAddress(record.get("address"));
			store.setStoreZipcode(record.get("zipcode"));
			store.setStoreAddDesc(record.get("add_desc"));
			store.setStoreStaffCount(record.get("staff"));
			store.setImageSrc(record.get("image"));
			store.setStoreID(record.get("id"));
			Object[] paramSet = {
					record.get("description"),
					store,
					Boolean.parseBoolean(record.get("expected"))
			};
			params.add(paramSet);
		}
		return params.iterator();
	}
	
	@Test(dataProvider="updateStore", groups= {"functest", "admintest"})
	public void updateStoreTest(String desc, Store props, boolean expected) {
		oldStore = storesBO.getStoreByID(props.getStoreID());
		boolean result = storesBO.updateStore(props);
		updatedStore = result;
		assertThat(result, equalTo(expected));
	}
	
	
	//createStore
	//Takes: <Store> all new store info
	//Returns: (Boolean) whether or not new store was created
	//Datastore: <Store> storeName, storeDesc, storeAddress, storeZipcode, storeAddDesc, storeStaffCount, imageSrc
	@DataProvider(name="createStore")
	public Iterator<Object[]> createStoreData() throws IOException{
		ArrayList<Object[]> params = new ArrayList<Object[]>();
		File f = new File(res + "createStore.csv");
		Reader in = new FileReader(f);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
		for(CSVRecord record : records) {
			Store store = new Store();
			store.setStoreName(record.get("name"));
			store.setStoreDesc(record.get("desc"));
			store.setStoreAddress(record.get("address"));
			store.setStoreZipcode(record.get("zipcode"));
			store.setStoreAddDesc(record.get("add_desc"));
			store.setStoreStaffCount(record.get("staff"));
			store.setImageSrc(record.get("image"));
			Object[] paramSet = {
					record.get("description"),
					store,
					Boolean.parseBoolean(record.get("expected"))
			};
			params.add(paramSet);
		}
		return params.iterator();
	}
	
	@Test(dataProvider="createStore", groups= {"functest", "admintest"})
	public void createStoreTest(String desc, Store props, boolean expected) {
		createdStoreID = storesBO.createStore(props);
		boolean result = (createdStoreID != 0);
		createdStore = result;
		assertThat(result,equalTo(expected));
	}
	
	@AfterMethod(alwaysRun=true)
	public void after() {
		if(updatedStore) {
			storesBO.updateStore(oldStore);
		}
		if(createdStore) {
			storesBO.removeStore(Integer.toString(createdStoreID));
		}
	}
}
