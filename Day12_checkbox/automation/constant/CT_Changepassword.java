package automation.constant;

import org.openqa.selenium.By;

public class CT_Changepassword {
	    public static final By TEXT_CURRENT_PASSWORD = By.id("txtpassword");
	    public static final By TEXT_NEW_PASSWORD = By.id("txtnewpass");
	    public static final By TEXT_REENTER_NEW_PASSWORD = By.id("txtrenewpass");
	    public static final By BUTTON_SAVE = By.xpath("//button[text()='Lưu mật khẩu mới']");
	    public static final By ERROR_CURRENT_PASSWORD = By.id("txtpassword-error");
	    public static final By ERROR_NEW_PASSWORD = By.id("txtnewpass-error");
	    public static final By ERROR_REENTER_PASSWORD = By.id("txtrenewpass-error");
	}
