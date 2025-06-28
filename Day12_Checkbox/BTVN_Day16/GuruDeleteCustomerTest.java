package BTVN_Day16;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import automation.constant.CT_PageURL;

import java.time.Duration;

public class GuruDeleteCustomerTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(CT_PageURL.DEMO_GURU4_URL);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       
    }

    @Test
    public void testDeleteCustomer() {
        driver.findElement(By.name("cusid")).sendKeys("123456");
        driver.findElement(By.name("submit")).click();
        Alert alert1 = wait.until(ExpectedConditions.alertIsPresent());
        String alert1Text = alert1.getText();
        System.out.println("Alert 1: " + alert1Text);
        alert1.accept();
        Alert alert2 = wait.until(ExpectedConditions.alertIsPresent());
        String alert2Text = alert2.getText();
        System.out.println("Alert 2: " + alert2Text);
        Assert.assertTrue(alert2Text.contains("Customer"), "Nội dung alert 2 không đúng");
        alert2.accept();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cusid")));
        Assert.assertEquals(driver.getCurrentUrl(), "https://demo.guru99.com/test/delete_customer.php");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}



