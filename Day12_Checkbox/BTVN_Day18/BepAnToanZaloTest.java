package BTVN_Day18;

import automation.common.CommonBase;
import automation.constant.CT_PageURL;
import automation.pageLocator.HomePage_BepAnToan;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class BepAnToanZaloTest extends CommonBase {

    @Parameters("browser")
    @BeforeMethod
    public void openWebsite(@Optional("chrome") String browserName) {
        driver = setupBrowser(browserName); 
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get(CT_PageURL.BEPANTOAN_URL);
    }

    @Test
    public void testZaloChatRedirect_BepAnToan() {      
        click(HomePage_BepAnToan.CHAT_WITH_US_BTN);      
        waitForNewTabAndSwitch();     
        String expectedUrlPart = "zalo.me/0912331335";
        wait.until(ExpectedConditions.urlContains(expectedUrlPart));
        Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrlPart),
                "Không mở đúng trang Zalo.");
    }

            
        }
    


