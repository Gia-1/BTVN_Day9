package BTVN_Day19;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import automation.common.CommonBase;
import automation.constant.CT_PageURL;
import automation.pageLocator.HomePage;
import java.time.Duration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
public class MayRuaBat extends CommonBase {

    HomePage homePage;

    @BeforeMethod
    public void openChromeBrowser() {
        driver = initChromeBrowser(CT_PageURL.BEPANTOAN_URL);
        homePage = new HomePage(driver);
    }
  @Test
    public void clickMayRuaChenBat() {
        homePage.clickMayRuaChenBat();

        WebElement heading = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h1[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'top máy rửa bát')]")));

        Assert.assertTrue(heading.isDisplayed(), "❌ Không tìm thấy tiêu đề danh mục máy rửa chén.");
    }
  @Test 
  public void clickHangSanXuatEvilla() {
      homePage.clickMayRuaChenBat();
      homePage.clickLogoHangEvilla();

      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      WebElement brandFilter = wait.until(ExpectedConditions.visibilityOfElementLocated(
              By.xpath("//*[contains(text(),'Sevilla')]")));

      Assert.assertTrue(brandFilter.isDisplayed(), "❌ Không tìm thấy bộ lọc theo hãng 'Sevilla'.");
  }    
    @Test
    public void dangKyKhuyenMaiThanhCong() {
        homePage.clickMayRuaChenBat();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement productImg = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//img[contains(@alt,'BOSCH SMS6ZCI42E')]")
        ));
        js.executeScript("arguments[0].scrollIntoView(true);", productImg);
        productImg.click();
        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//input[@placeholder='Nhập số điện thoại nhận thông tin khuyến mãi']")
        ));
        phoneInput.sendKeys("0912345678");
        WebElement btnDangKyNgay = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[contains(@class,'cursor-pointer') and contains(text(),'Đăng ký ngay')]")
        ));
        js.executeScript("arguments[0].scrollIntoView(true);", btnDangKyNgay);
        js.executeScript("arguments[0].click();", btnDangKyNgay);
        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[contains(@class,'text-primary') and normalize-space()='Đăng ký thành công']")
        ));
        Assert.assertTrue(successMsg.isDisplayed(), "❌ Không thấy thông báo 'Đăng ký thành công'");
    }
    @Test
    public void dangKyKhuyenMai_ThongTinKhongHopLe() throws InterruptedException {
        homePage.clickMayRuaChenBat();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement productImg = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//img[contains(@alt,'BOSCH SMS6ZCI42E')]")
        ));
        js.executeScript("arguments[0].scrollIntoView(true);", productImg);
        productImg.click();
        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//input[@placeholder='Nhập số điện thoại nhận thông tin khuyến mãi']")
        ));
        phoneInput.sendKeys("abc"); 
        WebElement btnDangKyNgay = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[contains(@class,'cursor-pointer') and contains(text(),'Đăng ký ngay')]")
        ));
        js.executeScript("arguments[0].scrollIntoView(true);", btnDangKyNgay);
        js.executeScript("arguments[0].click();", btnDangKyNgay);
        Thread.sleep(1000);
        boolean hasSDTError = driver.getPageSource().contains("Số điện thoại không hợp lệ");
        Assert.assertTrue(hasSDTError, "❌ Không thấy thông báo lỗi số điện thoại khi nhập sai định dạng.");
    }






}
