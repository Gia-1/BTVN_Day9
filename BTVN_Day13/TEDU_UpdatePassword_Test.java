package BTVN_Day13;
import static org.testng.Assert.assertTrue;
import automation.pageLocator.TEDU_UpdatePassword_Page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import automation.common.CommonBase;
import automation.constant.CT_PageURL;
import automation.pageLocator.TEDU_Login_Page;
import static org.testng.Assert.assertTrue;

public class TEDU_UpdatePassword_Test  extends CommonBase {
	String pass1 = "123456";
	String pass2 = "abcdef";
	@BeforeMethod
	public void openChromeBrowser()  {
		driver = initChromeBrowser(CT_PageURL.TEDU_URL);
		TEDU_Login_Page login = new TEDU_Login_Page(driver);
		login.loginFunction("conquy@gmail.com", pass1);
	
	}
	
	
	public void updatePass_NotSuccessfully() 
	{
		 TEDU_UpdatePassword_Page updatePassPage = new TEDU_UpdatePassword_Page(driver);
		 updatePassPage.updatePasswordFunction(pass1, pass2);
//		String tmp = pass2;
//		pass1 = pass2;
		 //	pass2 = tmp;
		 //	assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-success']")).isDisplayed());
	}
	 
	
	public void updatePass_Faill_InvalidOloPass()
	{
		
		
		
	}  
	@Test
	public void searchCourse_ASPNET() throws InterruptedException {
	    WebElement searchInput = driver.findElement(By.name("keyword"));
	    searchInput.sendKeys("ASP Net");
	    searchInput.submit();
	    Thread.sleep(2000); // nên dùng WebDriverWait thay thế

	    WebElement result = driver.findElement(By.xpath("//a[contains(text(),'ASP NET')]"));
	    assertTrue(result.isDisplayed(), "Không tìm thấy kết quả chứa 'ASP NET'");
	}
}


    

