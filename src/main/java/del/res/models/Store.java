package del.res.models;

public class Store {
	private String imageSrc = null;
	private String storeName = null;
	private String storeDesc = null;
	private String storeAddress = null;
	private String storeZipcode = null;
	private String storeAddDesc = null;	
	private String storeStaffCount = null;
	private String storeID = null;
	
	public Store() {}
	
	public Store(String img, String name, String desc, String address, String addDesc, String id) {
		 imageSrc = img;
		 storeName = name;
		 storeDesc = desc;
		 storeAddress = address;
		 storeAddDesc = addDesc;	
		 storeID = id;
	}

	public String getImageSrc() {
		return imageSrc;
	}

	public String getStoreName() {
		return storeName;
	}

	public String getStoreDesc() {
		return storeDesc;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public String getStoreAddDesc() {
		return storeAddDesc;
	}

	public String getStoreID() {
		return storeID;
	}

	public String getStoreStaffCount() {
		return storeStaffCount;
	}

	public void setStoreStaffCount(String storeStaffCount) {
		this.storeStaffCount = storeStaffCount;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public void setStoreDesc(String storeDesc) {
		this.storeDesc = storeDesc;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public void setStoreAddDesc(String storeAddDesc) {
		this.storeAddDesc = storeAddDesc;
	}

	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}

	public String getStoreZipcode() {
		return storeZipcode;
	}

	public void setStoreZipcode(String storeZipcode) {
		this.storeZipcode = storeZipcode;
	}
	
	
}
