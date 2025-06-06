package automation.pageLocator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Alada_ChangePassword_page {
    WebDriver driver;
    public Alada_ChangePassword_page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "txtpassword") WebElement currentPasswordInput;
    @FindBy(id = "txtnewpass") WebElement newPasswordInput;
    @FindBy(id = "txtrenewpass") WebElement confirmNewPasswordInput;
    @FindBy(xpath = "//button[text()='Lưu mật khẩu mới']")  WebElement savePasswordButton;
    public void changePassword(String currentPass, String newPass, String confirmPass) {
        currentPasswordInput.sendKeys(currentPass);
        newPasswordInput.sendKeys(newPass);
        confirmNewPasswordInput.sendKeys(confirmPass);
        savePasswordButton.click();
    }
}


