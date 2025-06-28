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

public class Muatragop extends CommonBase {
    HomePage homePage;

    @BeforeMethod
    public void openBrowser() {
        driver = initChromeBrowser(CT_PageURL.BEPANTOAN_URL);
        homePage = new HomePage(driver);
    }

    @Test
    public void muaTraGop_ThanhCong() throws InterruptedException {
        homePage.clickMayRuaChenBat();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement productImg = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//img[contains(@alt,'BOSCH SMS6ZCI42E')]")));
        js.executeScript("arguments[0].scrollIntoView(true);", productImg);
        productImg.click();

        WebElement btnMuaTraGop = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//span[translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='mua trả góp']")));
        js.executeScript("arguments[0].scrollIntoView(true);", btnMuaTraGop);
        js.executeScript("arguments[0].click();", btnMuaTraGop);

        WebElement hoTen = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//input[@placeholder='Nhập họ và tên' and not(ancestor::div[contains(@style,'display: none')])]")));
        hoTen.sendKeys("Nguyễn Văn A");

        WebElement soDT = driver.findElement(By.xpath("//input[@placeholder='Nhập số điện thoại' and not(ancestor::div[contains(@style,'display: none')])]"));
        soDT.sendKeys("0912345678");

        WebElement diaChi = driver.findElement(By.xpath("//input[@placeholder='Nhập địa chỉ' and not(ancestor::div[contains(@style,'display: none')])]"));
        diaChi.sendKeys("123 Lê Lợi, Q.1, TP.HCM");

        WebElement ghiChu = driver.findElement(By.xpath("//textarea[@placeholder='Nhập lưu ý' and not(ancestor::div[contains(@style,'display: none')])]"));
        ghiChu.sendKeys("Trả góp kỳ hạn 12 tháng");

        WebElement btnNhanTuVan = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//div[contains(@class,'btn-linear-orange') and .//span[normalize-space()='Nhận tư vấn']]")));
        js.executeScript("arguments[0].scrollIntoView(true);", btnNhanTuVan);
        Thread.sleep(500);
        js.executeScript("arguments[0].click();", btnNhanTuVan);

        Thread.sleep(2000);
        Boolean popupShown = (Boolean) js.executeScript("return document.body.innerText.includes('Đăng ký thành công');");
        Assert.assertTrue(popupShown, "❌ Không thấy text 'Đăng ký thành công' trên trang.");
    }

    @Test
    public void muaTraGop_ThongTinKhongHopLe() throws InterruptedException {
        homePage.clickMayRuaChenBat();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement productImg = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//img[contains(@alt,'BOSCH SMS6ZCI42E')]")));
        js.executeScript("arguments[0].scrollIntoView(true);", productImg);
        productImg.click();

        WebElement btnMuaTraGop = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//span[translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='mua trả góp']")));
        js.executeScript("arguments[0].scrollIntoView(true);", btnMuaTraGop);
        js.executeScript("arguments[0].click();", btnMuaTraGop);

        WebElement hoTen = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//input[@placeholder='Nhập họ và tên' and not(ancestor::div[contains(@style,'display: none')])]")));
        hoTen.clear();

        WebElement soDT = driver.findElement(By.xpath("//input[@placeholder='Nhập số điện thoại' and not(ancestor::div[contains(@style,'display: none')])]"));
        soDT.clear();
        soDT.sendKeys("123"); // sai định dạng

        WebElement diaChi = driver.findElement(By.xpath("//input[@placeholder='Nhập địa chỉ' and not(ancestor::div[contains(@style,'display: none')])]"));
        diaChi.clear();

        WebElement ghiChu = driver.findElement(By.xpath("//textarea[@placeholder='Nhập lưu ý' and not(ancestor::div[contains(@style,'display: none')])]"));
        ghiChu.sendKeys("Test case thông tin không hợp lệ");

        WebElement btnNhanTuVan = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//div[contains(@class,'btn-linear-orange') and .//span[normalize-space()='Nhận tư vấn']]")));
        js.executeScript("arguments[0].scrollIntoView(true);", btnNhanTuVan);
        Thread.sleep(1000);
        js.executeScript("arguments[0].click();", btnNhanTuVan);

        Thread.sleep(1500);
        boolean hasHoTenError = driver.getPageSource().contains("Họ và tên không hợp lệ");
        boolean hasSDTError = driver.getPageSource().contains("Số điện thoại không hợp lệ");
        boolean hasDiaChiError = driver.getPageSource().contains("Địa chỉ không hợp lệ");

        Assert.assertTrue(hasHoTenError, "❌ Không thấy lỗi họ tên.");
        Assert.assertTrue(hasSDTError, "❌ Không thấy lỗi số điện thoại.");
        Assert.assertTrue(hasDiaChiError, "❌ Không thấy lỗi địa chỉ.");
    }
}
