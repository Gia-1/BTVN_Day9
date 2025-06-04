package BTVN_Day13;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

import automation.common.CommonBase;
import automation.pageLocator.LoginPage;
import automation.constant.CT_PageURL; 

public class Logintest extends CommonBase {
    private WebDriver driver;

    @BeforeMethod
    public void openChrome() {
        driver = initChromeBrowser(CT_PageURL.ALADA_URL); 
    }
   
    public void closeChrome()
    {
    	driver.close();
    }

    @Test
    public void LoginSuccessfully() {
        LoginPage login = new LoginPage(driver);
        login.LoginFunction("conquy00q@gmail.com", "123456zxc");

        WebElement khctText = driver.findElement(By.xpath("//a[text()='Khóa học của tôi' and contains(@class,'khct')]"));
       
    }

   @Test
    public void loginFaill_IncorrectEmail() {
        LoginPage login = new LoginPage(driver);
        login.LoginFunction("demoemail_incorrectEmail@gmail.com", "123456zxc");

        WebElement emailSai = driver.findElement(By.xpath("//p[text()='Email này chưa được đăng ký.']"));
        Assert.assertTrue(emailSai.isDisplayed());
    }

    @Test
    public void loginFail_IncorrectPassword() {
        LoginPage login = new LoginPage(driver);
        login.LoginFunction("conquy00q@gmail.com", "saimatkhau123");

        WebElement passSai = driver.findElement(By.xpath("//p[text()='Mật khẩu sai.']"));
        Assert.assertTrue(passSai.isDisplayed());
    }
}


	
		
	


  