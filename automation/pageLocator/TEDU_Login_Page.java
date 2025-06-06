package automation.pageLocator;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TEDU_Login_Page {
	private WebDriver driver;
	@FindBy(xpath="//a[@title='Đăng nhập']") WebElement linkDangNhap;
	@FindBy(name="UserName") WebElement textUserName;
	@FindBy(name="Password") WebElement textPassword;
	@FindBy(xpath="//button[text()='Đăng nhập']") WebElement btnDangNhap;
	
	public TEDU_Login_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public void loginFunction(String userName, String password )
	{
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", linkDangNhap);
		textUserName.sendKeys(userName);
		textPassword.sendKeys(password);
		btnDangNhap.click();
	}

}
