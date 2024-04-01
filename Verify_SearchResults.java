package Maven.General;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Verify_SearchResults {
	//2. Search Functionality Testing: Create a Selenium script to automate the search functionality on a webpage. 
	//Enter a search query, submit the search, and verify that the search results are displayed correctly.

	WebDriver driver;	
	 	
@BeforeMethod
public void launchURL()
{
	driver=new ChromeDriver();
	driver.get("https://www.amazon.in/");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
}
@Test

public void seachItem()
{
	String item="ring";
	WebElement search=driver.findElement(By.id("twotabsearchtextbox"));
	search.sendKeys(item);
	search.sendKeys(Keys.ENTER);
	
	List<WebElement> searchresults=driver.findElements(By.xpath("//div[@data-cy='title-recipe']/h2/a/span"));
	int count=searchresults.size();
	
	for(int i=0;i<count;i++)
	{
		String itemname=searchresults.get(i).getText().toLowerCase();	
		Assert.assertTrue(itemname.contains(item));
				
	}
}

@AfterMethod
public void closeBrowser() {
	driver.quit();
}

}
