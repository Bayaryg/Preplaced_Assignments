package Maven.General;

import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Cookie_Handling {
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
	public void verify_cookieHandling()
	{		
		//add cookie
		driver.manage().addCookie(new Cookie("testaddcookie", "www.amazon.in"));
		Set<Cookie> cookielist=driver.manage().getCookies();
		int count=cookielist.size();
		System.out.println(count);
		for(Cookie ck:cookielist)
		{
			String cookiename=ck.getName();
			System.out.println(cookiename);
			if(cookiename.equals("testaddcookie"))
			{
				System.out.println("Cookie got added successfully");
			}
		}		
		driver.manage().deleteCookieNamed("testaddcookie");
		Set<Cookie> cookielist_afterdelete=driver.manage().getCookies();
		int newcount=cookielist_afterdelete.size();
		System.out.println(newcount);
	}		
	@AfterMethod
	public void closeBrowser() 
	{
		driver.quit();
	}

}
