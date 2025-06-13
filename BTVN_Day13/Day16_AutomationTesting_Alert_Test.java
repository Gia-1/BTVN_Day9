package BTVN_Day13;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automation.common.CommonBase;
import automation.constant.CT_PageURL;

import automation.constant.CT_PageURL;

public class Day16_AutomationTesting_Alert_Test extends CommonBase {

	@BeforeMethod
	public void openBrowser() {
	    System.out.println("BeforeMethod - openBrowser started");
	    System.out.println("URL = " + CT_PageURL.AUTOMATIONTESTING_URL);
	    driver = initChromeBrowser(CT_PageURL.AUTOMATIONTESTING_URL);
	    System.out.println("Driver initialized? " + (driver != null));
	}

    @Test
    public void Alert_Ok() {
        click(By.xpath("//button[@class='btn btn-danger']"));
       // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.switchTo().alert().accept();
        assertTrue(isElementPresent(By.xpath("//button[@class='btn btn-danger']")));
    }
     @Test
    public void Alert_Ok_Cancel() {
        click(By.xpath("//a[text()='Alert with OK & Cancel ']"));
        click(By.xpath("//button[text()='click the button to display a confirm box ']"));
        driver.switchTo().alert().dismiss();
        assertTrue(isElementPresent(By.xpath("//p[text()='You Pressed Cancel']")));
    }
}
