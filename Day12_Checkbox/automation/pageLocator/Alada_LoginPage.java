package automation.pageLocator;

import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
public class Alada_LoginPage {
	private WebDriver driver;

	public Alada_LoginPage(WebDriver driver) {
		  this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

	    @FindBy(id = "txtLoginUsername")
	    public WebElement emailInput;

	    @FindBy(id = "txtLoginPassword")
	    public WebElement passwordInput;

	    @FindBy(xpath = "//button[text()='ĐĂNG NHẬP']")
	    public WebElement loginButton;

	    public void login(String email, String password) {
	        emailInput.sendKeys(email);
	        passwordInput.sendKeys(password);

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            By.cssSelector("button.btn.btn_pink.btn_submit")));
	        loginBtn.click();

}
}

