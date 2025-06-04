package automation.constant;

import org.openqa.selenium.By;

public class CT_PageURL_ {
        public static final String CRMSTAR_URL = "http://test-system.crmstar.vn/";   
    public static final By TEXTEmail = By.id("email");              
    public static final By TEXTPass = By.id("password");            
    public static final By BTNDangNhap = By.xpath("//button[text()='Đăng nhập']"); 
    public static final By VERIFY_ELEMENT_AFTER_LOGIN = By.xpath("//span[contains(text(),'Xin chào')]");
    public static final By BTNLogout = By.xpath("//a[text()='Đăng xuất']");
}
