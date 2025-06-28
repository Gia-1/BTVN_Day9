package BTVN_Day19;

import automation.common.CommonBase;
import automation.constant.CT_PageURL;
import automation.pageLocator.HomePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class filterGia extends CommonBase {
    HomePage homePage;

    @BeforeMethod
    public void openChromeBrowser() {
        driver = initChromeBrowser(CT_PageURL.BEPANTOAN_URL);
        homePage = new HomePage(driver);
    }

    @Test
    public void kiemTraGiaTatCaTabLoc() {
        homePage.clickMayRuaChenBat();  
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement locGia = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(text(),'5.000.000') and contains(text(),'10.000.000')]")
            ));
            locGia.click();
            Thread.sleep(1000);
        } catch (Exception e) {
            Assert.fail("❌ Không tìm thấy bộ lọc giá 5.000.000 > 10.000.000");
        }
        List<WebElement> tabElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
            By.xpath("//a[contains(@class, 'menu-tab')]")
        ));
        Set<String> tabTexts = new LinkedHashSet<>();
        for (WebElement tab : tabElements) {
            tabTexts.add(tab.getText().trim());
        }
        for (String tabText : tabTexts) {
            WebElement tabToClick = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@class, 'menu-tab') and normalize-space()='" + tabText + "']"))
            );
            System.out.println("👉 Đang kiểm tra tab: " + tabText);
            tabToClick.click();
            try {
                wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.cssSelector("div.loading") 
                ));
            } catch (Exception e) {
            }
            List<WebElement> giaElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//span[contains(@class, 'sale-price')]")
            ));
            Assert.assertFalse(giaElements.isEmpty(), "❌ Không có sản phẩm trong tab: " + tabText);
            for (WebElement giaElement : giaElements) {
                String giaText = giaElement.getText()
                    .replace(".", "")
                    .replace("₫", "")
                    .replace("\u00a0", "")
                    .trim();
                try {
                    int gia = Integer.parseInt(giaText);
                    System.out.println("✅ [" + tabText + "] Giá sản phẩm: " + gia);
                    Assert.assertTrue(gia >= 5000000 && gia <= 10000000,
                        "❌ [" + tabText + "] Giá không nằm trong khoảng 5-10 triệu: " + gia);
                } catch (NumberFormatException e) {
                    Assert.fail("❌ [" + tabText + "] Giá không hợp lệ: " + giaText);
                }
            }
        }
    }
}
