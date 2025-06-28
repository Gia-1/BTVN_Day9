package automation.pageLocator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Alada_register_page {
    private WebDriver driver;

    @FindBy(id = "txtFirstname") WebElement txtFirstname;
    @FindBy(id = "txtEmail") WebElement txtEmail;
    @FindBy(id = "txtCEmail") WebElement txtCEmail;
    @FindBy(id = "txtPassword") WebElement txtPassword;
    @FindBy(id ="txtCPassword") WebElement txtConfirmPassword;
    @FindBy(id = "txtPhone") WebElement txtPhone;
    @FindBy(xpath = "//button[@type='submit' and contains(text(),'ĐĂNG KÝ')]") WebElement btnRegister;

    public Alada_register_page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterFirstname(String firstname) {
        txtFirstname.sendKeys(firstname);
    }

    public void enterEmail(String email) {
        txtEmail.sendKeys(email);
    }

    public void enterConfirmEmail(String email) {
        txtCEmail.sendKeys(email);
    }

    public void enterPassword(String password) {
        txtPassword.sendKeys(password);
    }

    public void enterConfirmPassword(String password) {
        txtConfirmPassword.sendKeys(password);
    }

    public void enterPhone(String phone) {
        txtPhone.sendKeys(phone);
    }

    public void clickRegisterButton() {
        btnRegister.click();
    }

    public void fillRegisterForm(String firstname, String email, String password, String phone) {
        enterFirstname(firstname);
        enterEmail(email);
        enterConfirmEmail(email);
        enterPassword(password);
        enterConfirmPassword(password);
        enterPhone(phone);
    }
    public void register(String firstname, String email, String password, String phone) {
        fillRegisterForm(firstname, email, password, phone);
        clickRegisterButton();
    }
}





