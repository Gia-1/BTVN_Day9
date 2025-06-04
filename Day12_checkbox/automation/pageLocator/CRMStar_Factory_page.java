package automation.pageLocator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CRMStar_Factory_page {
	private WebDriver driver;
	@FindBy(id="email") WebElement txtEmail;
	@FindBy(id="password") WebElement txtPassword;
	@FindBy(name="signin") WebElement btnSignin;
	public CRMStar_Factory_page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements( driver, this);
	}
	
	public void LoginFunction(String email, String pass) {
		txtEmail.sendKeys(email);
		txtPassword.sendKeys(pass);
		btnSignin.click();
	}
	

}
