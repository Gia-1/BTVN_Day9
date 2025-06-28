package BTVNDay12;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import automation.common.CommonBase;
public class Checkdropdownlist extends CommonBase {
    @Test
    public void selectCountryFromDropdown() {
        driver = initChromeBrowser("https://www.globalsqa.com/demo-site/select-dropdown-menu/#Select%20Country");                
        Select countryDropdown = new Select(driver.findElement(By.tagName("select")));
        countryDropdown.selectByVisibleText("Albania");
        System.out.println("số lượng options của dropdown là:" + countryDropdown.getOptions().size());
        System.out.println("Giá trị sau khi chọn là:"+countryDropdown.getFirstSelectedOption().getText());
        System.out.println("Multiple dropdown:" + countryDropdown.isMultiple());
    }
}
