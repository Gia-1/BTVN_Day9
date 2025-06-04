package Day12_Checkbox;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import automation.common.CommonBase;
import automation.constant.CT_PageURL;
import org.testng.annotations.BeforeMethod;

public class Day12_Checkbox extends CommonBase {
	@BeforeMethod
	public void setup() 

	{
		 driver = initChromeBrowser(CT_PageURL.DEMOQA_URL);
	
	}
 
    @Test
    public void clickCheckbox() 
    {
      
      
        WebElement chbSport = driver.findElement(By.xpath("//label[@for='hobbies-checkbox-1']"));
        scrollToElement(chbSport);
        if (chbSport.isSelected()==false) { 
        	chbSport.click();
            System.out.println("The Sport checkbox has been checked");
        }

        WebElement chbReading = driver.findElement(By.xpath("//label[@for='hobbies-checkbox-2']"));
        if (chbReading.isSelected()== false)
        {  
        	chbReading.click();
            System.out.println("The Reading checkbox has been checked");
        }

        WebElement chbMusic = driver.findElement(By.xpath("//label[@for='hobbies-checkbox-3']"));
        if (chbMusic.isSelected()== false) {  
        	chbMusic.click();
            System.out.println("The Music checkbox has been checked");
        }
    }

    @Test
    public void clickCheckbox_2() {
        driver = initChromeBrowser(CT_PageURL.DEMOQA_URL);
        List<WebElement> listLabelCheckbox = driver.findElements(By.xpath("//label[contains(@for,'hobbies-checkbox')]"));
        scrollToElement(listLabelCheckbox.get(0));

        for (WebElement item : listLabelCheckbox) {
           if(item.isSelected()==false)
           {
                item.click();  
                System.out.println("Checkbox " +item + " has been clicked");
            }
        }
        for(int i=0; i< listLabelCheckbox.size();i++)
        {
        	if(listLabelCheckbox.get(i).isSelected()==false)
        	{
        		listLabelCheckbox.get(i).click();
        		System.out.println("Checkbox " + (i + 1) + " has been clicked");


        	}
        }
    }
}
    



