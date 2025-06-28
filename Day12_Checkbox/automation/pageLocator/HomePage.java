package automation.pageLocator;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private By mayRuaChenBatMenu = By.xpath("//a[contains(text(),'Máy Rửa Chén Bát')]");
    private By logoHangEvilla = By.xpath("//img[contains(@src,'Evilla.png')]");

    public void clickMayRuaChenBat() {
        try {
            WebElement menu = wait.until(ExpectedConditions.presenceOfElementLocated(mayRuaChenBatMenu));
            wait.until(ExpectedConditions.visibilityOf(menu));
            wait.until(ExpectedConditions.elementToBeClickable(menu));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", menu);
            menu.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("❌ Không thể click vào menu 'Máy Rửa Chén Bát'. Kiểm tra lại XPath hoặc trạng thái element.");
        } catch (Exception e) {
            throw new RuntimeException("❌ Lỗi khác khi click vào menu: " + e.getMessage());
        }
    }

    public void clickLogoHangEvilla() {
        try {
            WebElement logo = wait.until(ExpectedConditions.elementToBeClickable(logoHangEvilla));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", logo);
            logo.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("❌ Không thể click vào logo hãng Evilla. Kiểm tra lại XPath hoặc trạng thái element.");
        }
    }
}


