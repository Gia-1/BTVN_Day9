package automation.common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class CommonBase {
    protected WebDriver driver;
    public WebDriverWait wait;
    private int pageLoadTimeout = 20;
    private int initWaitTime = 5;

    public WebDriver initChromeBrowser(String URL) {
        WebDriverManager.chromedriver().setup(); // Tự động tải đúng ChromeDriver
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
        wait = new WebDriverWait(driver, Duration.ofSeconds(initWaitTime));
        return driver;
    }

    public WebDriver initFireFoxBrowser(String URL) {
        try {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.get(URL);
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
            wait = new WebDriverWait(driver, Duration.ofSeconds(initWaitTime));
            return driver;
        } catch (Exception e) {
            System.out.println("Failed to initialize FirefoxDriver: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public boolean isAlertPresent() {
        try {
            WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(initWaitTime));
            localWait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public WebElement getElementVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void click(By locator) {
        WebElement element = getElementVisibility(locator);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void sendKeys(By locator, String text) {
        WebElement element = getElementVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    public void type(By locator, String text) {
        sendKeys(locator, text);
    }

    public WebElement getElementPresentDOM(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElement(locator);
    }

    public boolean isElementPresent(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    public String getText(By locator) {
        return getElementVisibility(locator).getText();
    }

    public String getAttribute(By locator, String attributeName) {
        return getElementVisibility(locator).getAttribute(attributeName);
    }

    public void scrollToElement(By locator) {
        WebElement element = getElementVisibility(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    private WebDriver initChromeBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
        return driver;
    }

    private WebDriver initFireFoxBrowser() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
        return driver;
    }

    private WebDriver initEdgeBrowser() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
        return driver;
    }

    public WebDriver setupBrowser(String browserName) {
        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                driver = initChromeBrowser();
                System.out.println("Initialize Chrome browser successfully...");
                break;
            case "firefox":
                driver = initFireFoxBrowser();
                System.out.println("Initialize Firefox browser successfully...");
                break;
            case "edge":
                driver = initEdgeBrowser();
                System.out.println("Initialize Edge browser successfully...");
                break;
            default:
                driver = initChromeBrowser();
                System.out.println("Unknown browser => Default to Chrome browser...");
        }
        return driver;
    }

    public void waitForNewTabAndSwitch() {
        int retry = 0;
        while (driver.getWindowHandles().size() < 2 && retry < 10) {
            sleep(1000);
            retry++;
        }

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        if (tabs.size() >= 2) {
            driver.switchTo().window(tabs.get(1));
        } else {
            throw new RuntimeException("Không mở được tab mới sau khi click.");
        }
    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}



    





