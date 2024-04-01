package Maven.General;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Verify_Dynamic_Dropdowns {
	
	WebDriver driver;
	@BeforeMethod
	public void launchURL()
	{
		driver=new ChromeDriver();
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@Test
	public void verify_dynamicdropdown()
	{
        WebElement text=driver.findElement(By.name("q"));
        text.sendKeys("India");    
        List<WebElement> auto=driver.findElements(By.xpath("//div[@class='OBMEnb']/ul/li"));          
        int count=auto.size();        
        String exptext="IndiaMART";
        
        for(int i=0;i<count;i++)
        {
        	String acttext=auto.get(i).getText();
        	if(acttext.equalsIgnoreCase(exptext))
        	{
        		auto.get(i).click();
        		break;
        	}
        }
        
        Assert.assertEquals(driver.getTitle(), "indiamart - Google Search");
	}
	
	@AfterMethod
	public void closeBrowser() 
	{
		driver.quit();
	}

}
