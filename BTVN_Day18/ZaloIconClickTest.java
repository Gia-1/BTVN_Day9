package BTVN_Day18;

import automation.common.CommonBase;
import automation.constant.CT_PageURL;
import automation.pageLocator.ZALO_ĐMHA;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class ZaloIconClickTest extends CommonBase {
	@Parameters("browser")
	@BeforeMethod
	public void openWebsite(@Optional("Firefox") String browserName) {
	    driver = setupBrowser(browserName);
	    wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    driver.get(CT_PageURL.ZALO_CLICK_URL);
	}

	@Test
	public void testZaloIconRedirect() {
	    wait.until(ExpectedConditions.visibilityOfElementLocated(ZALO_ĐMHA.ZALOICON));
	    scrollToElement(ZALO_ĐMHA.ZALOICON);
	    wait.until(ExpectedConditions.elementToBeClickable(ZALO_ĐMHA.ZALOICON));
	    click(ZALO_ĐMHA.ZALOICON);
	    waitForNewTabAndSwitch();
	    String expectedUrlPart = "zalo.me";
	    wait.until(ExpectedConditions.urlContains(expectedUrlPart));
	    Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrlPart), "Không mở đúng trang Zalo.");
	}
}




