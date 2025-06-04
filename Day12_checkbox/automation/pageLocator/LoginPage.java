package automation.pageLocator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import automation.constant.CT_Account;

public class LoginPage {
	private WebDriver driver;
	public LoginPage(WebDriver _driver) {
		this.driver = _driver;
	}
	public void LoginFunction (String email, String pass)	{
		WebElement txtEmail = driver.findElement(CT_Account.TEXTEmail);
		if (txtEmail.isDisplayed()) {
			txtEmail.sendKeys(email);
		}
		WebElement txtPass = driver.findElement(CT_Account.TEXTPass);
		if (txtPass.isDisplayed()) {
			txtPass.sendKeys(pass);
		}
		WebElement btnDangNhap = driver.findElement(CT_Account.BTNDangNhap);
		if (btnDangNhap.isEnabled()) {
			btnDangNhap.click();
			
		}
	
	}
	public void LogoutFunction() {

		
	}

}
