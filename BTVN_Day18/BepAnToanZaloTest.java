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
        driver = setupBrowser(browserName); // Hàm của CommonBase
        wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // ✅ Thêm dòng này
        driver.get(CT_PageURL.BEPANTOAN_URL);
    }

    @Test
    public void testZaloChatRedirect_BepAnToan() {
        // Click vào nút Zalo Chat
        click(HomePage_BepAnToan.CHAT_WITH_US_BTN);

        // Chờ tab mới được mở và chuyển sang
        waitForNewTabAndSwitch();

        // Kiểm tra xem URL của tab mới có chứa zalo.me không
        String expectedUrlPart = "zalo.me/0912331335";
        wait.until(ExpectedConditions.urlContains(expectedUrlPart));
        Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrlPart),
                "Không mở đúng trang Zalo.");
    }

            
        }
    


