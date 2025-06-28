package automation.pageLocator;

import org.openqa.selenium.By;

public class CRMStarPage {
    public static By txtUserName = By.id("email"); 
    public static By txtPassword = By.id("password");
    public static By btnLogin = By.name("signin");
    public static By btnThemMoi = By.xpath("//button[contains(text(),'Thêm mới')]");
    public static By txtKLVCode = By.name("work_areas_code");
    public static By txtKLVName = By.name("name");
    public static By btnLuu = By.xpath("//button[@onclick='submit_new_workarea()']");
    public static By btnThem = By.xpath("//button[@form='add_workarea' and contains(text(),'Thêm')]");
    public static By toastSuccess = By.xpath("//div[contains(text(),'Thêm mới khu vực làm việc thành công')]");
    public static By errorKLVName = By.id("name_validate"); 
    public static By klvByName(String name) {
        return By.xpath("//td[@class='name-work-area' and text()='" + name + "']");
    }

    public static By deleteButtonByKLVName(String name) {
        return By.xpath("//td[text()='" + name + "']/following-sibling::td/button[contains(@class,'btnDelete')]");
    }
}

