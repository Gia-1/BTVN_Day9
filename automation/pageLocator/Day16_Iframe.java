package automation.pageLocator;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automation.common.CommonBase;
import automation.constant.CT_PageURL;

public class Day16_Iframe extends CommonBase {
    @BeforeMethod
    public void openChromeBrowser() {
        driver = initChromeBrowser(CT_PageURL.CODE_STAR_URL);
    }

    @Test
    public void handleIframe() {
        scrollToElement(By.xpath("//h2[contains(text(),'Đăng kí nhận tư vấn lộ trình')]"));
        driver.switchTo().frame(0);
        type(By.id("phone_number"), "0912345678");
        type(By.id("email"), "autotest@gmail.com");
        click(By.xpath("//button[normalize-space()='Gửi ngay']"));
        assertTrue(isElementPresent(By.xpath("//button[normalize-space()='Gửi ngay']")));
    }			
	}

