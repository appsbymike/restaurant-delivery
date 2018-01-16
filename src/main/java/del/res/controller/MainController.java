package del.res.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import del.res.bo.ItemsBO;
import del.res.bo.OrdersBO;
import del.res.bo.ReviewsBO;
import del.res.bo.StoresBO;
import del.res.bo.UsersBO;
import del.res.models.User;
import del.res.models.Item;
import del.res.models.PastReview;
import del.res.models.Picture;
import del.res.models.ReceiptSummary;
import del.res.models.Store;
import del.res.utilities.PicturesUtility;
import del.res.utilities.Validator;

@Controller
public class MainController {
	
	//Admin panel navigation page
	@RequestMapping(value="adminNavigate", method=RequestMethod.GET)
	public ModelAndView adminNavigate() {
		ModelAndView model = new ModelAndView("adminNavigate");
		return model;
	}
	
	//====================================================================================
	
	//List of regular users	
	@RequestMapping(value="adminUserList", method=RequestMethod.GET)
	public ModelAndView adminUserList() {
		ModelAndView model = new ModelAndView("adminUserList");
		UsersBO usersBO = new UsersBO();
		ArrayList<User> users = usersBO.getNormalUsers();
		model.addObject("users",users);
		return model;
	}
	
	//Getting individual user information	
	@RequestMapping(value="adminGetUser", method=RequestMethod.GET)
	public ModelAndView adminGetUser(
			@RequestParam(value="id", required=true) String userID) {
		
		ModelAndView model;
		if(userID != null) {
			UsersBO usersBO = new UsersBO();
			//Get the user info, make sure repassword are userid are set
			User info = usersBO.getDetails(userID);
			info.setRepassword(info.getPassword());
			info.setId(userID);
			
			//Get reviews by this user
			ReviewsBO reviewsBO = new ReviewsBO();
			ArrayList<PastReview> reviews = reviewsBO.getReviewsByUser(userID);
			
			//Pass user info to the forms in the view
			model = new ModelAndView("adminGetUser","command",info);
			//Pass the user reviews as an object to the view
			model.addObject("reviews",reviews);
		}
		else {
			model = new ModelAndView("adminError");
		}
		return model;
		
	}
	
	//Delete a review or update a user
	@RequestMapping(value="adminGetUser", method=RequestMethod.POST)
	public ModelAndView adminGetUserPost(
			@ModelAttribute("SpringWeb") User info, 
			@RequestParam(value="review_id",required=false) String reviewID) {
		
		ModelAndView model;
		ReviewsBO reviewsBO = new ReviewsBO();
		
		if(info.getId() != null) {
			//Check if user is deleting a review
			if(reviewID != null) {
				//Delete review based on userID
				reviewsBO.deleteReview(reviewID);
				//Call the regular GetUser method to easily load user's info
				model = adminGetUser(info.getId());
				//Add attribute to show view that delete was successful
				model.addObject("deleted",true);
			}
			else {
				//Save all user info for validation
				String firstname = info.getFirstname();
				String lastname = info.getLastname();
				String password = info.getPassword();
				String repassword = info.getRepassword();
				String address = info.getAddress();
				String phone = info.getPhone();
				String email = info.getEmail();
				Validator v = new Validator();
				
				
				
				//Validate user info
				if(v.isValidUpdate(password, repassword, firstname, lastname, address, phone, email)) {
					//Validate password is same as repassword
					if(password.equals(repassword)) {
						//Update the user
						UsersBO usersBO = new UsersBO();
						usersBO.updateUser(info);
						//Add attribute to show view that update was successful
						model = new ModelAndView("redirect:/admin/adminUserList");
//						model.addObject("success", true);
					}
					else {
						//Add attribute to show view that password didn't match
						model = new ModelAndView("adminGetUser","command",info);
						model.addObject("nomatch",true);
					}
				}
				else {
					//Add attribute to show view that format was incorrect
					model = new ModelAndView("adminGetUser","command",info);
					model.addObject("invalidformat",true);
				}
				
				//Pass the user reviews as an object to the view if regular GetUser method isn't being used
				ArrayList<PastReview> reviews = reviewsBO.getReviewsByUser(info.getId());
				model.addObject("reviews",reviews);
				
			}

		}
		else {
			//Send view to Error page if userID isn't valid
			model = new ModelAndView("adminError");
		}
		
		//Display the view
		return model;
	}
	
	//=====================================================================================
	
	//Get the list of items
	@RequestMapping(value="adminItemList", method=RequestMethod.GET)
	public ModelAndView adminItemList() {
		System.out.println("Starting");
		ModelAndView model = new ModelAndView("adminItemList");
		
		//Add all items to the model
		ItemsBO itemsBO = new ItemsBO();
		ArrayList<Item> items = itemsBO.getAllItems();
		model.addObject("items",items);
		System.out.println("Added Items");
		
		//Add all pictures of items to the model
		PicturesUtility picturesUtility = new PicturesUtility();
		System.out.println("Trying to add pictures");
		ArrayList<Picture> pictures = picturesUtility.getItemPictures();
		System.out.println(pictures);
		model.addObject("pictures",pictures);
		
		//Return the model
		return model;
	}
	
	//Update an item
	@RequestMapping(value="UpdateItem", method=RequestMethod.POST)
	public ModelAndView adminUpdateItem(
			@RequestParam(value="name", required=true) String itemName,
			@RequestParam(value="price", required=true) String itemPrice,
			@RequestParam(value="desc", required=true) String itemDesc,
			@RequestParam(value="image", required=true) String imageSrc,
			@RequestParam(value="active", required=true) String itemIsActive,
			@RequestParam(value="category", required=true) String itemCategory,
			@RequestParam(value="update", required=false) String update,
			@RequestParam(value="delete", required=false) String delete) {
		
		ItemsBO itemsBO = new ItemsBO();
		ModelAndView model;
		
		if(update != null) {
			//Instantiate and populate the updated item information
			Item item = new Item();
			item.setItemName(itemName);
			item.setItemPrice(itemPrice);
			item.setItemDesc(itemDesc);
			item.setImageSrc(imageSrc);
			item.setItemIsActive(itemIsActive);
			item.setItemCategory(itemCategory);
			item.setItemID(update);
			
			//Attempt to update the item
			Boolean success = itemsBO.updateItem(item);
			
			//Return user to get page after updating the item
			model = new ModelAndView("redirect:/admin/adminItemList");
		}
		else if(delete != null) {
			//Attempt to delete the item
			Boolean success = itemsBO.removeItem(delete);
			//Return user to get page
			model = new ModelAndView("redirect:/admin/adminItemList");
		}
		else {
			//If neither parameter was passed, send to error page.
			model = new ModelAndView("adminError");
		}
		
		return model;
	}
	
	//Create a new item
	@RequestMapping(value="CreateItem",method=RequestMethod.POST)
	public ModelAndView adminCreateItem(
			@RequestParam(value="name", required=true) String itemName,
			@RequestParam(value="price", required=true) String itemPrice,
			@RequestParam(value="desc", required=true) String itemDesc,
			@RequestParam(value="image", required=true) String imageSrc,
			@RequestParam(value="active", required=true) String itemIsActive,
			@RequestParam(value="category", required=true) String itemCategory) {
		
		ItemsBO itemsBO = new ItemsBO();
		
		//Instantiate and populate the new item with information
		Item item = new Item();
		item.setItemName(itemName);
		item.setItemPrice(itemPrice);
		item.setItemDesc(itemDesc);
		item.setImageSrc(imageSrc);
		item.setItemIsActive(itemIsActive);
		item.setItemCategory(itemCategory);
		
		//Execute the query and find out if it was successful
		boolean success = (itemsBO.addItem(item) != 0);
		
		//Return user to get page after updating the item
		ModelAndView model;
		model = new ModelAndView("redirect:/admin/adminItemList");
		return model;
	}
	
	//=====================================================================================
	
	//Get the list of orders
	@RequestMapping(value="adminOrderList", method=RequestMethod.GET)
	public ModelAndView adminOrderList() {
		ModelAndView model = new ModelAndView("adminOrderList");
		
		OrdersBO ordersBO = new OrdersBO();
		ArrayList<ReceiptSummary> orders = ordersBO.getAllOrders();
		
		model.addObject("orders",orders);
		
		return model;
	}
	
	//Remove an order
	@RequestMapping(value="RemoveOrder", method=RequestMethod.POST)
	public ModelAndView adminRemoveOrder(
			@RequestParam(value="id", required=true) String orderID) {
		
		ModelAndView model;
		
		//Call ordersBO and attempt to delete the order
		OrdersBO ordersBO = new OrdersBO();
		boolean success = ordersBO.deleteOrder(orderID);
		
		//Direct the model to the get page
		model = new ModelAndView("redirect:/admin/adminOrderList");
		
		//Return the view
		return model;
	}
	
	//=====================================================================================
	
	//Get the list of locations
	@RequestMapping(value="adminLocationList", method=RequestMethod.GET)
	public ModelAndView adminLocationList() {
		ModelAndView model = new ModelAndView("adminLocationList");
				
		//Add all items to the model
		StoresBO storesBO = new StoresBO();
		ArrayList<Store> stores = storesBO.getAllStores();
		model.addObject("locations",stores);
		
		//Add all pictures of items to the model
		PicturesUtility picturesUtility = new PicturesUtility();
		ArrayList<Picture> pictures = picturesUtility.getStorePictures();
		model.addObject("pictures",pictures);
		
		//Return the model		
		return model;
	}
	
	//Update or remove a location
	@RequestMapping(value="UpdateLocation", method=RequestMethod.POST)
	public ModelAndView adminUpdateLocation(
			@RequestParam(value="name", required=true) String storeName,
			@RequestParam(value="desc", required=true) String storeDesc,
			@RequestParam(value="address", required=true) String storeAddress,
			@RequestParam(value="zipcode", required=true) String storeZipcode,
			@RequestParam(value="addDesc", required=true) String storeAddDesc,
			@RequestParam(value="staff", required=true) String storeStaffCount,
			@RequestParam(value="image", required=true) String imageSrc,
			@RequestParam(value="update", required=false) String update,
			@RequestParam(value="delete", required=false) String delete) {
		
		StoresBO storesBO = new StoresBO();
		ModelAndView model;
		
		if(update != null) {
			//Instantiate and populate the updated item information
			Store store = new Store();
			store.setStoreName(storeName);
			store.setStoreDesc(storeDesc);
			store.setStoreAddress(storeAddress);
			store.setStoreZipcode(storeZipcode);
			store.setStoreAddDesc(storeAddDesc);
			store.setStoreStaffCount(storeStaffCount);
			store.setImageSrc(imageSrc);
			store.setStoreID(update);
			
			
			//Attempt to update the item
			boolean success = storesBO.updateStore(store);
			
			//Return user to get page after updating the item
			model = new ModelAndView("redirect:/admin/adminLocationList");
		}
		else if(delete != null) {
			//Attempt to delete the item
			Boolean success = storesBO.removeStore(delete);
			//Return user to get page
			model = new ModelAndView("redirect:/admin/adminLocationList");
		}
		else {
			//If neither parameter was passed, send to error page.
			model = new ModelAndView("adminError");
		}
		
		return model;
	}
	
	//Add a location
	@RequestMapping(value="CreateLocation",method=RequestMethod.POST)
	public ModelAndView adminCreateLocation(
			@RequestParam(value="name", required=true) String storeName,
			@RequestParam(value="desc", required=true) String storeDesc,
			@RequestParam(value="address", required=true) String storeAddress,
			@RequestParam(value="zipcode", required=true) String storeZipcode,
			@RequestParam(value="addDesc", required=true) String storeAddDesc,
			@RequestParam(value="staff", required=true) String storeStaffCount,
			@RequestParam(value="image", required=true) String imageSrc) {
		
		StoresBO storesBO = new StoresBO();
		
		//Instantiate and populate the new item with information
		//Instantiate and populate the updated item information
		Store store = new Store();
		store.setStoreName(storeName);
		store.setStoreDesc(storeDesc);
		store.setStoreAddress(storeAddress);
		store.setStoreZipcode(storeZipcode);
		store.setStoreAddDesc(storeAddDesc);
		store.setStoreStaffCount(storeStaffCount);
		store.setImageSrc(imageSrc);
		
		//Execute the query and find out if it was successful
		boolean success = (storesBO.createStore(store) != 0);
		
		//Return user to get page after updating the item
		ModelAndView model;
		model = new ModelAndView("redirect:/admin/adminLocationList");
		return model;
	}
	
	//====================================================================================
	
	//Get Admin Account Details
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView adminUpdateAccountGet() {
		//Get the current session
		ServletRequestAttributes request = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = request.getRequest().getSession(true);
		ModelAndView model;
		
		//Get the admin's user ID from the session
		String userID = (String) session.getAttribute("user_id");
		if(userID != null) {
			//Get admin's info with their ID
			UsersBO usersBO = new UsersBO();
			User info = usersBO.getDetails(userID);
			//Make sure repassword is set
			info.setRepassword(info.getPassword());
			//Pass admin's info to the view's form
			model = new ModelAndView("adminAccountDetails","command",info);
		}
		else {
			//If admin's ID is null for some reason, send to error page
			model = new ModelAndView("adminError");
		}		
		
		//Display the view
		return model;
	}
	
	//Update admin account details
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ModelAndView adminUpdateAccountPost(
			@ModelAttribute("SpringWeb") User info) {
		
		//Get the session
		ServletRequestAttributes request = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = request.getRequest().getSession(true);
		ModelAndView model;
		//Get the admin's ID from the session
		String userID = (String) session.getAttribute("user_id");
		
		
		if(userID != null) {
			info.setId(userID);
			//Save admin info for validation
			String firstname = info.getFirstname();
			String lastname = info.getLastname();
			String password = info.getPassword();
			String repassword = info.getRepassword();
			String address = info.getAddress();
			String phone = info.getPhone();
			String email = info.getEmail();
			Validator v = new Validator();
			
			model = new ModelAndView(("adminAccountDetails"),"command",info);
			
			//Validate admin info
			if(v.isValidUpdate(password, repassword, firstname, lastname, address, phone, email)) {
				//Validate that password and repassword are the same
				if(password.equals(repassword)) {
					//Update admin's info
					UsersBO usersBO = new UsersBO();
					usersBO.updateUser(info);
					//Add attribute to show view that update was successful
					model.addObject("success", true);
				}
				else {
					//Add attribute to show view that password didn't match
					model.addObject("nomatch",true);
				}
			}
			else {
				//Add attribute to show view that info was in an invalid format
				model.addObject("invalidformat",true);
			}
		}
		else {
			//If admin's ID is null for some reason, send to error page
			model = new ModelAndView("adminError");
		}
		
		//Display the view
		return model;
	}
}
