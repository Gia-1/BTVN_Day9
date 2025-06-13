package BTVN_Day15;

import automation.common.CommonBase;
import automation.constant.CT_PageURL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;


public class Day15_DatePicker_Test extends CommonBase {
	
	@BeforeMethod
	public void openeChromeBrowser() {
		driver = initChromeBrowser(CT_PageURL.GIRI_TEST_URL);
		
	}
	@Test
	public void inputDatePicker() {
		WebElement textBday = driver.findElement(By.name("bdaytime"));
		textBday.sendKeys("10152025");
		textBday.sendKeys(Keys.TAB);
		textBday.sendKeys(null);
	}

}
