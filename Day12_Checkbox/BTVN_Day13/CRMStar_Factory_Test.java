package BTVN_Day13;

import automation.common.CommonBase;
import automation.constant.CT_PageURL_;
import automation.pageLocator.CRMStar_Factory_page;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue; 
public class CRMStar_Factory_Test extends CommonBase {

    @BeforeMethod
    @Parameters("browser")
    public void initFireFoxBrowserTest(@Optional("edge")String browser) {
        driver = initFireFoxBrowser(CT_PageURL_.CRMSTAR_URL);
    }

    @Test
    public void loginSuccessfully() {
        CRMStar_Factory_page factory = new CRMStar_Factory_page(driver);
        factory.LoginFunction("admin@gmail.com", "12345678");
        assertTrue(driver.findElement(By.xpath("//p[text()='Quản lý người dùng']")).isDisplayed());
    }

    @Test
    public void loginFail_IncorrectEmail() throws InterruptedException {
        CRMStar_Factory_page factory = new CRMStar_Factory_page(driver);
        factory.LoginFunction("admin_incorrect@gmail.com", "12345678");
        Thread.sleep(2000);
        assertTrue(driver.findElement(By.xpath("//span[text()='Email hoặc mật khẩu không đúng']")).isDisplayed());
    }

    @Test
    public void loginFail_IncorrectPassword() throws InterruptedException {
        CRMStar_Factory_page factory = new CRMStar_Factory_page(driver);
        factory.LoginFunction("admin@gmail.com", "1234567");
        Thread.sleep(2000);
        assertTrue(driver.findElement(By.xpath("//span[text()='Email hoặc mật khẩu không đúng']")).isDisplayed());
    }
}


