package del.res.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	//Validation methods
		public boolean isValidNormal (String normal) {
			String regex = "[a-zA-Z0-9]{1,20}";
			Pattern pattern = Pattern.compile(regex);
			Matcher match = pattern.matcher(normal);
			return match.matches();
		}
		
		public boolean isValidGender (String gender) {
			String regex = "[mf]{1}";
			Pattern pattern = Pattern.compile(regex);
			Matcher match = pattern.matcher(gender);
			return match.matches();
		}
		
		public boolean isValidAddress (String address) {
			String regex = "[a-zA-Z0-9 .]{1,100}";
			Pattern pattern = Pattern.compile(regex);
			Matcher match = pattern.matcher(address);
			return match.matches();
		}
		
		public boolean isValidPhone (String phone) {
			String regex = "[0-9]{10}";
			Pattern pattern = Pattern.compile(regex);
			Matcher match = pattern.matcher(phone);
			return match.matches();
		}
		
		public boolean isValidEmail (String email) {
			String regex = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}";
			Pattern pattern = Pattern.compile(regex);
			Matcher match = pattern.matcher(email);
			return match.matches();
		}
		
		public boolean isValidCreditCardNumber (String creditCardNumber) {
			String regex = "[0-9]{1,20}";
			Pattern pattern = Pattern.compile(regex);
			Matcher match = pattern.matcher(creditCardNumber);
			return match.matches();
		}
		
		public boolean isValidSecurityCode (String securityCode) {
			String regex = "[0-9]{3,}";
			Pattern pattern = Pattern.compile(regex);
			Matcher match = pattern.matcher(securityCode);
			return match.matches();
		}
		
		public boolean isValidZipCode (String zipCode) {
			String regex = "[0-9]{5,}";
			Pattern pattern = Pattern.compile(regex);
			Matcher match = pattern.matcher(zipCode);
			return match.matches();
		}
		
		public boolean isValidRegistration(String username, String password, String repassword, String firstname, String lastname, String gender, String address, String phone, String email){
			if(isValidNormal(username) && isValidNormal(password) && isValidNormal(repassword) && isValidNormal(firstname) &&isValidNormal(lastname) && isValidGender(gender) && isValidAddress(address) && isValidPhone(phone) && isValidEmail(email)) {
				return true;
			}
			else {
				return false;
			}
		}
		
		public boolean isValidLogin(String username, String password) {
			if(isValidNormal(username) && isValidNormal(password)) {
				return true;
			}
			else {
				return false;
			}
		}
		
		public boolean isValidUpdate(String password, String repassword, String firstname, String lastname, String address, String phone, String email){
			if(isValidNormal(password) && isValidNormal(repassword) && isValidNormal(firstname) &&isValidNormal(lastname) && isValidAddress(address) && isValidPhone(phone) && isValidEmail(email)) {
				return true;
			}
			else {
				return false;
			}
		}
		
		public boolean isValidPaymentInfo(String creditCardNumber, String securityCode, String zipCode) {
			if(isValidCreditCardNumber(creditCardNumber) && isValidSecurityCode(securityCode) && isValidZipCode(zipCode)) {
				return true;
			}
			else {
				return false;
			}
		}
		
		public boolean isValidReview(String reviewText) {
			if(reviewText != null &&  reviewText.length() <= 500) {
				return true;
			}
			else {
				return false;
			}
		}
}
