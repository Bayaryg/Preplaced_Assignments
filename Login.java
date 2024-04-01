package Maven.General;

import java.io.IOException;
import java.time.Duration;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Login {	
	 WebDriver driver;
	 
	 @DataProvider(name="Login")
		public Object[][] provideData()
		{					
			Object[][] o1=new Object[1][2];
			o1[0][0]="dummy@gmail.com";
			o1[0][1]="dummy123";
			return o1;
		}
	  	 	
	@BeforeMethod
	public void launchURL()
	{
		driver=new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}	
	
		
	
	@Test(dataProvider="Login")

	public void amazon_login(String uname, String pwd) throws EncryptedDocumentException, IOException
	{
	   WebElement signin=driver.findElement(By.xpath("//span[.='Hello, sign in']"));
	   String signin_text=signin.getText();
	   signin.click();
	   
	    
	   WebElement email= driver.findElement(By.id("ap_email"));
	   email.sendKeys(uname);
	   WebElement continue1=driver.findElement(By.id("continue"));
	   continue1.click();
	   
	   WebElement password= driver.findElement(By.id("ap_password"));
	   password.sendKeys(pwd);
	   WebElement signinclick=driver.findElement(By.id("signInSubmit"));
	   signinclick.click();
	   
	   String login_text=driver.findElement(By.xpath("//span[@id='nav-link-accountList-nav-line-1']")).getText();
	   Assert.assertNotEquals(signin_text, login_text);
		  	   
	}
	@AfterMethod
	public void closeBrowser() 
	{
		driver.quit();
	}

}
