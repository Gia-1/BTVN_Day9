package BTVN_Day13;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import org.testng.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import automation.common.CommonBase;
import automation.pageLocator.LoginPage;
import automation.constant.CT_PageURL_;


public class LoginTest_ extends CommonBase {

    @BeforeMethod
    public void openBrowser() {
        driver = initFireFoxBrowser(CT_PageURL_.CRMSTAR_URL);
    }

       public void closeBrowser() {
        driver.quit();
    }

       @Test
       public void LoginFunction() {
           LoginPage login = new LoginPage(driver);
           login.LoginFunction("admin@gmail.com", "12345678");
           WebElement toastMessage = driver.findElement(By.className("toast-message"));
           Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Quản lý người dùng']")).isDisplayed());
       }
       
        @Test

       public void LoginFail_IncorrectEmail() throws InterruptedException {
           LoginPage login = new LoginPage(driver);
           login.LoginFunction("wrongemail@gmail.com", "12345678");
           Thread.sleep(3000); 
           WebElement Emailsai = driver.findElement(By.className("fl-message"));
           Assert.assertTrue((driver.findElement(By.xpath("//span[text()='Email hoặc mật khẩu không đúng']")).isDisplayed()));
       }

      @Test
    public void LoginFail_IncorrectPassword()  throws InterruptedException {
        LoginPage login = new LoginPage(driver);
        login.LoginFunction("admin@gmail.com", "wrongpassword");
        Thread.sleep(3000); 
        WebElement Passsai = driver.findElement(By.className("fl-message"));
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Email hoặc mật khẩu không đúng']")).isDisplayed());
    }

    
    public void TLogin_IncorrectEmail_Password() throws InterruptedException {
        LoginPage login = new LoginPage(driver);
        login.LoginFunction("wrong@gmail.com", "wrongpass");
        Thread.sleep(3000); 
        WebElement errorMsg = driver.findElement(By.className("fl-message"));
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Email hoặc mật khẩu không đúng']")).isDisplayed());
    }
    @Test
    public void LogoutFunction() {
        LoginPage login = new LoginPage(driver);
        login.LoginFunction("admin@gmail.com", "12345678");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));       
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("toast-message")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("toast-message")));      
        WebElement btnAdmin = wait.until(ExpectedConditions.elementToBeClickable(By.id("dropdownMenuLink")));
        btnAdmin.click();      
        WebElement btnLogoutDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Đăng xuất' and @type='button']")));
        btnLogoutDropdown.click();       
        WebElement btnConfirmLogout = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@form='logout' and text()='Đăng xuất']")));
        btnConfirmLogout.click();        
        WebElement btnDangNhap = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Đăng nhập']")));
        Assert.assertTrue(btnDangNhap.isDisplayed(), "Không quay lại màn hình đăng nhập sau khi đăng xuất.");
    }
}
	


