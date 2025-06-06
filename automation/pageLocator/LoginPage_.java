package automation.pageLocator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By; // Bị thiếu import này
import automation.constant.CT_PageURL_;

public class LoginPage_ {
    private WebDriver driver;

    public LoginPage_(WebDriver _driver) {
        this.driver = _driver;
    }

    public void LoginFunction(String email, String pass) {
        WebElement txtEmail = driver.findElement(CT_PageURL_.TEXTEmail);
        if(txtEmail.isDisplayed()) {
            txtEmail.sendKeys(email);
        }
        WebElement txtPass = driver.findElement(CT_PageURL_.TEXTPass);
        if(txtPass.isDisplayed()) {
            txtPass.sendKeys(pass);
        }
        WebElement btnDangNhap = driver.findElement(CT_PageURL_.BTNDangNhap);
        if(btnDangNhap.isEnabled()) {
            btnDangNhap.click();        	
        }
    }
    public void LogoutFunction() { 
    	driver.findElement(By.id("dropdownMenuLink")).click();          
        driver.findElement(By.xpath("//button[contains(text(),'Đăng xuất')]")).click();
    }
}




