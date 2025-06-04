package Day12_Checkbox;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import automation.common.CommonBase;
import automation.constant.CT_PageURL;
public class Day12_Radio extends CommonBase {
	@Test
	public void clickRadio()
	{
		driver = initChromeBrowser(CT_PageURL.DEMOQA_URL);
		WebElement radioMale = driver.findElement(By.xpath("//label[@for='gender-radio-1']"));
		radioMale.click();
		System.out.println("Radio Male has been clicked");
	}
	
	

}
