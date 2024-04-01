package Maven.General;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Verify_Pagination {
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
public void verify_pagination() throws InterruptedException
{
	SoftAssert softassert=new SoftAssert();
	String item="ring";
	WebElement search=driver.findElement(By.id("twotabsearchtextbox"));
	search.sendKeys(item);
	search.sendKeys(Keys.ENTER);
	WebElement element=driver.findElement(By.xpath("//span[@class='s-pagination-item s-pagination-disabled']"));
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript("arguments[0].scrollIntoView();", element);
	String pagenum=element.getText();
	int total_pages=Integer.parseInt(pagenum);
	
	for(int i=0;i<total_pages-1;i++)
	{
		WebElement nextbutton=driver.findElement(By.xpath("//a[text()='Next']"));
		nextbutton.click();
		Thread.sleep(3000);
		List<WebElement> searchresults=driver.findElements(By.xpath("//div[@data-cy='title-recipe']/h2/a/span"));
		int count=searchresults.size();
				for(int j=0;j<count;j++)
		{
			String itemname=searchresults.get(j).getText().toLowerCase();	
			softassert.assertTrue(itemname.contains(item));					
		}
	}
	
	softassert.assertAll();
	}

@AfterMethod
public void closeBrowser() {
	driver.quit();
}

}
