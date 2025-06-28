package BTVN_Day16;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import java.util.Random;
import org.openqa.selenium.JavascriptExecutor;


import automation.common.CommonBase;
import automation.constant.CT_PageURL;

import java.time.Duration;

public class CRMStarTest extends CommonBase {
    
    @BeforeClass
    public void setup() {
        driver = initFireFoxBrowser(CT_PageURL.CRMSTAR_URL);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(priority = 1)
    public void testLogin_Success() {
        sendKeys(By.id("email"), "admin@gmail.com");
        sendKeys(By.id("password"), "12345678");
        click(By.name("signin"));
        Assert.assertTrue(
            isElementPresent(By.xpath("//p[text()='Quản lý người dùng']")),
            "Login không thành công"
        );
    }
    @Test(priority = 2)
    public void testAddKLV_Success(ITestContext context) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        click(By.xpath("//a[contains(text(),'Quản lý khu làm việc')]"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Thêm mới')]"))).click();    
        String maKhuLamViec = "KLV" + new Random().nextInt(10000);
        String tenKhuLamViec = "KVLVTuDong" + new Random().nextInt(1000);    
        sendKeys(By.name("work_areas_code"), maKhuLamViec);
        sendKeys(By.name("name"), tenKhuLamViec);
        click(By.xpath("//button[@onclick='submit_new_workarea()']"));     
        By btnThem = By.xpath("//button[@form='add_workarea' and contains(text(),'Thêm')]");
        wait.until(ExpectedConditions.elementToBeClickable(btnThem)).click();  
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[contains(text(),'Thêm mới khu vực làm việc thành công')]")
        ));      
        By searchInput = By.name("query");
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        sendKeys(searchInput, tenKhuLamViec);
        click(By.xpath("//button[contains(text(),'Tìm kiếm')]"));

        By resultRow = By.xpath("//td[@class='name-work-area' and text()='" + tenKhuLamViec + "']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(resultRow));       
        Assert.assertTrue(isElementPresent(resultRow), "Không tìm thấy khu làm việc vừa thêm sau khi tìm kiếm");
        context.setAttribute("klvName", tenKhuLamViec);
    }
    @Test(priority = 3)
    public void testDeleteKLV_Success(ITestContext context) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String klvName = (String) context.getAttribute("klvName");
        if (klvName == null || klvName.isEmpty()) {
            Assert.fail("Không có tên khu làm việc để xóa từ context");
        }
        By searchInput = By.name("query");
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput)).clear();
        sendKeys(searchInput, klvName);
        click(By.xpath("//button[contains(text(),'Tìm kiếm')]"));
        By deleteLink = By.xpath("//td[text()='" + klvName + "']/following-sibling::td//a[contains(text(),'Xóa')]");
        wait.until(ExpectedConditions.elementToBeClickable(deleteLink)).click();
        driver.switchTo().alert().accept();
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput)).clear();
        sendKeys(searchInput, klvName);
        click(By.xpath("//button[contains(text(),'Tìm kiếm')]"));

        By noResultMessage = By.xpath("//h4[contains(text(),'Không tìm thấy kết quả')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(noResultMessage));
        Assert.assertTrue(isElementPresent(noResultMessage), "Xóa thất bại: vẫn còn kết quả tìm thấy");
    }
	    public void sendKeys(By locator, String text) {
	        getElementVisibility(locator).clear();
	        getElementVisibility(locator).sendKeys(text);
	    }
	}