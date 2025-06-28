package BTVN_Day16;

import automation.constant.CT_PageURL;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class SeleniumAlertDemoTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(CT_PageURL.SELENIUM_PRACTISE_ALERT_URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void testAlertMessage() {
        driver.findElement(By.xpath("//button[text()='Try it']")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertEquals(alertText, "Welcome to Selenium WebDriver Tutorials", "Alert message không đúng");
        alert.accept();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

