package BTVN_Day17;

import automation.common.CommonBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import automation.constant.CT_PageURL;



public class ZaloChatTest extends CommonBase {

	@Test
    public void testZaloChatIframe() {    
		 initChromeBrowser(CT_PageURL.ZALO_URL);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
            By.cssSelector("iframe[src*='zalo']")));
        WebElement zaloIcon = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[@class='za-chat__head-box']")));
        zaloIcon.click();
        WebElement chatNhanh = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[contains(@class,'zbtn') and contains(text(),'Chat nhanh')]")));
        chatNhanh.click();
        WebElement chatContent = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.card__content")));
        Assert.assertTrue(chatContent.isDisplayed(), "❌ Không thấy nội dung chat Zalo.");
    }
}



