package automation.pageLocator;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TEDU_UpdatePassword_Page {
	private WebDriver driver;

	@FindBy(name = "UserName") 	WebElement textUserName;
	@FindBy(name = "Password") 	WebElement textPassword;
	@FindBy(xpath = "//button[text()='Đăng nhập']") 	WebElement btnDangNhap;
    @FindBy(id = "my_account") 	WebElement buttonTaikhoan;
    @FindBy(xpath = "//a[@title='Đổi mật khẩu']") WebElement buttonDoiMatKhau;
	@FindBy(id = "OldPassword") 	WebElement textOldPass;
	@FindBy(id = "NewPassword") 	WebElement textNewPass;
	@FindBy(id = "ConfirmNewPassword") 	WebElement textConfirmPass;
	@FindBy(xpath = "//input[@value='Cập nhật']") 	WebElement btnCapNhat;

	public TEDU_UpdatePassword_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		 
	}
	public void updatePasswordFunction(String oldPass, String newPass) {
	    JavascriptExecutor js = (JavascriptExecutor) driver;	    	      
	    js.executeScript("arguments[0].click();", buttonDoiMatKhau);    
	    textOldPass.sendKeys(oldPass);
	    textNewPass.sendKeys(newPass);
	    textConfirmPass.sendKeys(newPass);
	    btnCapNhat.click();
	}


}

