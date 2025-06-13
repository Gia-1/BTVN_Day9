package automation.common;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.edge.EdgeDriver;


public class CommonBase {
    public static WebDriver driver;
    public static WebDriverWait wait;  
    private int pageLoadTimeout = 20;
    private int initWaitTime = 5;

    public WebDriver initChromeBrowser(String URL) {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Duration.ofSeconds(initWaitTime));  
        return driver;
    }

    public WebDriver initFireFoxBrowser(String URL) {
        try {
            String geckoDriverPath = System.getProperty("user.dir") + "\\driver\\geckodriver.exe";
            System.setProperty("webdriver.gecko.driver", geckoDriverPath);
            driver = new FirefoxDriver();
            driver.get(URL);
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, Duration.ofSeconds(initWaitTime)); 
            return driver;
        } catch (Exception e) {
            System.out.println("Failed to initialize FirefoxDriver: " + e.getMessage());
            e.printStackTrace();
            return null;
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
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
        return driver;
    }

    private WebDriver initFireFoxBrowser() {
        // download geckoDriver.exe in https://github.com/mozilla/geckodriver/releases
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\driver\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
        return driver;
    }
    private WebDriver initEdgeBrowser() {
        // download geckoDriver.exe in https://github.com/mozilla/geckodriver/releases
        System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\driver\\msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
        return driver;
    }



}



    





