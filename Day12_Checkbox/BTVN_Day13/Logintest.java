package BTVN_Day13;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import org.testng.Assert;
import automation.common.CommonBase;
import automation.pageLocator.LoginPage;
import automation.constant.CT_PageURL;

public class Logintest extends CommonBase {
    WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    public void openBrowser(@Optional("firefox") String browser) {
        if (browser.equalsIgnoreCase("firefox")) {
        	driver = initFireFoxBrowser(CT_PageURL.ALADA_URL);

        } else {
            System.out.println("Chưa hỗ trợ trình duyệt: " + browser);
        }
    }

    @AfterMethod
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void LoginSuccessfully() {
        LoginPage login = new LoginPage(driver);
        login.LoginFunction("conquy00q@gmail.com", "123456zxc");

        WebElement khctText = driver.findElement(By.xpath("//a[text()='Khóa học của tôi' and contains(@class,'khct')]"));
        Assert.assertTrue(khctText.isDisplayed(), "Login thành công nhưng không thấy text 'Khóa học của tôi'");
    }

    @Test
    public void loginFail_IncorrectEmail() {
        LoginPage login = new LoginPage(driver);
        login.LoginFunction("demoemail_incorrectEmail@gmail.com", "123456zxc");

        WebElement emailSai = driver.findElement(By.xpath("//p[text()='Email này chưa được đăng ký.']"));
        Assert.assertTrue(emailSai.isDisplayed(), "Không hiển thị lỗi email chưa được đăng ký");
    }

    @Test
    public void loginFail_IncorrectPassword() {
        LoginPage login = new LoginPage(driver);
        login.LoginFunction("conquy00q@gmail.com", "saimatkhau123");

        WebElement passSai = driver.findElement(By.xpath("//p[text()='Mật khẩu sai.']"));
        Assert.assertTrue(passSai.isDisplayed(), "Không hiển thị lỗi mật khẩu sai");
    }
}



	
		
	


  