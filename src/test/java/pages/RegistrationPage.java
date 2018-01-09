package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
	WebDriver driver;
	By txtUsername = By.name("username");
	By txtPassword = By.name("password");
	By txtRepassword = By.name("repassword");
	By txtFirstname = By.name("firstname");
	By txtLastname = By.name("lastname");
	By optGender = By.name("gender");
	By txtAddress = By.name("address");
	By txtPhone = By.name("phone");
	By txtEmail = By.name("email");
	By btnRegister = By.name("register");
	By btnCancel = By.name("cancel");
}
