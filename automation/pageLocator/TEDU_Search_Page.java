package automation.pageLocator;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class TEDU_Search_Page {
    private WebDriver driver;
    @FindBy(name = "keyword")
    private WebElement searchInput;
    @FindBy(xpath = "//a[contains(text(),'ASP NET') or contains(text(),'Asp Net') or contains(text(),'asp net')]")
    private List<WebElement> searchResults;

    public TEDU_Search_Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void searchCourse(String keyword) {
        searchInput.clear();
        searchInput.sendKeys(keyword);
        searchInput.submit();
    }
}

