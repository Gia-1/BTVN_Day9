package BTVN_Day13;

import automation.pageLocator.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.Assert;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import automation.pageLocator.Alada_ChangePassword_page;


public class AladaTest {
    WebDriver driver;
    String email = "conquy" + System.currentTimeMillis() + "@gmail.com";
    String oldPass = "12345678";
    String newPass = "matkhaumoi123";

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        System.out.println("Email đăng ký là: " + email);
    }

    @Test(priority = 1)
    public void registerAccount() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        Alada_register_page register = new Alada_register_page(driver);
        register.fillRegisterForm("Con Quy", email, oldPass, "0988888888");
        register.clickRegisterButton();
        Assert.assertTrue(driver.findElement(By.id("thongbao")).isDisplayed(), "Thông báo không hiển thị");
    }
    @Test(priority = 2, dependsOnMethods = "registerAccount")
    public void changePassword() {
        WebElement avatar = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.avatar2")));
        avatar.click();
        WebElement editInfoBtn = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Chỉnh sửa thông tin')]")));
        editInfoBtn.click();
        Alada_ChangePassword_page changePass = new Alada_ChangePassword_page(driver);
        changePass.changePassword(oldPass, newPass, newPass);
        WebDriverWait waitAlert = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitAlert.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "Cập nhật mật khẩu mới thành công!");
        alert.accept();
        avatar = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.avatar2")));
        avatar.click();
        WebElement logoutBtn = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Thoát']")));
        logoutBtn.click();
    }


    @Test(priority = 3, dependsOnMethods = "changePassword")
    public void testClickLoginButton() {
        driver.get("https://alada.vn/tai-khoan/dang-nhap.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("button.btn.btn_pink.btn_submit")));
        loginBtn.click();
    }
    @AfterClass
    public void tearDown() {      
            driver.quit();
        }
    }




