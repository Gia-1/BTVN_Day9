package BTVN_Day19;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import automation.pageLocator.HomePage;
import automation.common.CommonBase;
import automation.constant.CT_PageURL;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class KhaoSatTest extends CommonBase {
    HomePage homePage;

    @BeforeMethod
    public void openBrowser() {
        WebDriverManager.chromedriver().setup(); 
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(CT_PageURL.BEPANTOAN_URL);
        homePage = new HomePage(driver);
    }

    @Test
    public void khaoSatLapDatTaiNha() {
        homePage.clickMayRuaChenBat();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement productImg = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//img[contains(@alt,'BOSCH SMS6ZCI42E')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productImg);
        productImg.click();

        WebElement btnKhaoSat = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//span[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'khảo sát')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnKhaoSat);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnKhaoSat);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//input[@placeholder='Nhập họ và tên']"))).sendKeys("Nguyễn Văn A");

        driver.findElement(By.xpath("//input[@placeholder='Nhập số điện thoại']")).sendKeys("0912345678");
        driver.findElement(By.xpath("//input[@placeholder='Nhập địa chỉ']")).sendKeys("123 Lê Lợi, Q1");
        driver.findElement(By.xpath("//textarea[@placeholder='Nhập lưu ý']")).sendKeys("Gọi buổi sáng");

        WebElement btnGui = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//span[contains(text(),'Nhận tư vấn')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnGui);
    }

    @Test
    public void khaoSatLapDatTaiNha_ThongTinKhongHopLe_ThongBaoLoi() {
        homePage.clickMayRuaChenBat();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement productImg = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//img[contains(@alt,'BOSCH SMS6ZCI42E')]")));
        js.executeScript("arguments[0].scrollIntoView(true);", productImg);
        productImg.click();

        WebElement btnKhaoSat = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//span[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'khảo sát')]")));
        js.executeScript("arguments[0].click();", btnKhaoSat);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//input[@placeholder='Nhập họ và tên']")));
        driver.findElement(By.xpath("//input[@placeholder='Nhập số điện thoại']")).sendKeys("abcd1234");
        driver.findElement(By.xpath("//textarea[@placeholder='Nhập lưu ý']")).sendKeys("Test lỗi");

        WebElement btnGui = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//span[contains(text(),'Nhận tư vấn')]")));
        js.executeScript("arguments[0].click();", btnGui);

        WebElement hoTenError = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//small[contains(text(),'Họ và tên không hợp lệ')]")));
        WebElement sdtError = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//small[contains(text(),'Số điện thoại không hợp lệ')]")));
        WebElement diaChiError = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//small[contains(text(),'Địa chỉ không hợp lệ')]")));

        Assert.assertTrue(hoTenError.isDisplayed(), "❌ Không thấy lỗi Họ và tên");
        Assert.assertTrue(sdtError.isDisplayed(), "❌ Không thấy lỗi Số điện thoại");
        Assert.assertTrue(diaChiError.isDisplayed(), "❌ Không thấy lỗi Địa chỉ");
    }


	}



