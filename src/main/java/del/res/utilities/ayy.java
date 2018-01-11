package del.res.utilities;

public class ayy {
	public static void main(String args[]) {
		String format = "[a-zA-Z]{1,20} [a-zA-Z]{1,20} Phone: [0-9]{10} Email: [a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$";
		String value = "Mike Mike Phone: 1234567890 Email: mike@mike.com";
		System.out.println(value.matches(format));
	}
}
