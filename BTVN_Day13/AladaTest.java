package BTVN_Day13;

import automation.pageLocator.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.Assert;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    // 1. Đăng ký tài khoản mới
    @Test(priority = 1)
    public void registerAccount() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        Alada_register_page register = new Alada_register_page(driver);
        register.fillRegisterForm("Con Quy", email, oldPass, "0988888888");
        register.clickRegisterButton();
        Assert.assertTrue(driver.findElement(By.id("thongbao")).isDisplayed(), "Thông báo không hiển thị");
    }

    
    // 3. Đổi mật khẩu
    @Test(priority = 2, dependsOnMethods = "loginAfterRegister")
    public void changePassword() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement avatar = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.avatar2")));
        avatar.click();
        WebElement editInfoBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Chỉnh sửa thông tin')]")));
        editInfoBtn.click();

        Alada_ChangePassword_page changePass = new Alada_ChangePassword_page(driver);
        changePass.changePassword(oldPass, newPass, newPass);

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "Cập nhật mật khẩu mới thành công!");
        alert.accept();

        avatar = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.avatar2")));
        avatar.click();
        WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Thoát']")));
        logoutBtn.click();
    }

    // 4. Đăng nhập lại với mật khẩu mới để xác nhận thay đổi thành công
    @Test(priority = 3, dependsOnMethods = "changePassword")
    public void loginWithNewPassword() {
        driver.get("https://alada.vn/tai-khoan/dang-nhap.html");
        driver.findElement(By.id("txtLoginUsername")).sendKeys(email);
        driver.findElement(By.id("txtLoginPassword")).sendKeys(newPass);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("button.btn.btn_pink.btn_submit")));
        loginBtn.click();

        WebElement avatar = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("div.avatar2")));
        Assert.assertTrue(avatar.isDisplayed(), "Không đăng nhập lại được sau khi đổi mật khẩu.");
    }

    
    public void tearDown() {
        driver.quit();
    }
}




