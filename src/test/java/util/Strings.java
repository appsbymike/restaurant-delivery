package util;

public class Strings {
	
	
	
	public static String htmlRoot = "http://localhost/RestaurantDelivery-MichaelGabriel-Maven/";
	public static String htmlAdminRoot = htmlRoot + "admin/";
	public static String applicationRoot = System.getProperty("user.dir");
	
	
	public static String LoginPage = htmlRoot + "Login.jsp";
	public static String MenuPage = htmlRoot + "Menu.jsp";
	public static String LocationsPage = htmlRoot + "Stores.jsp";
	public static String PaymentPage = htmlRoot + "PaymentInfo.jsp";
	public static String OrderReviewPage = htmlRoot + "OrderReview.jsp";
	public static String ErrorPage = htmlRoot + "Error.jsp";
	public static String ReceiptPage = htmlRoot + "Receipt?order_id=3";
	
	public static String AdminUserPage = htmlAdminRoot + "adminUserList";
	public static String AdminItemsPage = htmlAdminRoot + "adminItemList";
	public static String AdminLocationsPage = htmlAdminRoot + "adminLocationList";
	public static String AdminOrdersPage = htmlAdminRoot + "adminOrderList";
	
	public static String LoginHeader = "Restaurant Delivery";
	public static String RegistrationHeader = "User Details";
	
	
}
