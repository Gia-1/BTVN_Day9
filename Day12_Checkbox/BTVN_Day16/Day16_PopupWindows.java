package BTVN_Day16;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automation.common.CommonBase;
import automation.constant.CT_PageURL;
import java.time.Duration;



public class Day16_PopupWindows extends CommonBase {

    @BeforeMethod
    public void openChromeBrowser() {
        driver = initChromeBrowser(CT_PageURL.DEMO_GURU2_URL);
    }

    @Test
    public void handleTabs() {
        click(By.xpath("//a[text()='Click Here']"));

        String mainWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(driver -> driver.getWindowHandles().size() > 1);

        for (String childWindow : driver.getWindowHandles()) {
            if (!childWindow.equals(mainWindow)) {
                driver.switchTo().window(childWindow);
                System.out.println("Đã chuyển sang tab con");
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("emailid")));
                type(By.name("emailid"), "testEmail@gmail.com");
                click(By.name("btnLogin"));
                Assert.assertTrue(isElementPresent(By.xpath("//h2[contains(text(),'Access details')]")));
                driver.close(); 
            }
        }

        driver.switchTo().window(mainWindow);
        System.out.println("Đã quay lại tab chính");
    }
}

