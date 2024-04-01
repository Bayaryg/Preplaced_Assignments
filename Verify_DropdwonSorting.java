package Maven.General;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Verify_DropdwonSorting {
	WebDriver driver;
	@BeforeMethod
	public void launchURL()
	{
		driver=new ChromeDriver();
		driver.get("https://www.saucedemo.com/v1/index.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}		
	
	@Test
	public void dropdown_sorting() throws InterruptedException
	{
		
		WebElement uname=driver.findElement(By.id("user-name"));
		uname.sendKeys("standard_user");
		WebElement pwd=driver.findElement(By.id("password"));
		pwd.sendKeys("secret_sauce");
		WebElement loginbutton=driver.findElement(By.id("login-button"));
		loginbutton.click();
		List<WebElement> beforesortprice=driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
		ArrayList<Double> pricelist_bs=new ArrayList<>();
		
		for(WebElement p:beforesortprice)
		{
			Double price=Double.valueOf(p.getText().replace("$", ""));
			pricelist_bs.add(price);
		}
		
		Select select=new Select(driver.findElement(By.className("product_sort_container")));
		select.selectByVisibleText("Price (low to high)");
		
		List<WebElement> aftersortprice=driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
		ArrayList<Double> pricelist_as=new ArrayList<>();
		
		for(WebElement q:aftersortprice)
		{
			Double price_updated=Double.valueOf(q.getText().replace("$", ""));
			pricelist_as.add(price_updated);
		}
		
		Collections.sort(pricelist_bs);
		Assert.assertEquals(pricelist_bs, pricelist_as);
	}
	@AfterMethod
	public void closeBrowser() 
	{
		driver.quit();
	}

}
