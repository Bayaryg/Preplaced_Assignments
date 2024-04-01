package Maven.General;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Verify_File_Upload {
	WebDriver driver;
	@BeforeMethod
	public void launchURL()
	{
		driver=new ChromeDriver();
		driver.get("https://www.ilovepdf.com/word_to_pdf");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	@Test
	public void upoladFile() throws AWTException
	{
		WebElement uploadfile=driver.findElement(By.id("pickfiles"));
		uploadfile.click();
		Robot r = new Robot();	
		r.delay(2000);
		StringSelection s = new StringSelection("C:\\Users\\udupa\\OneDrive - MSFT\\Desktop\\Automation\\Updated\\Archana Bayary_7YOE_Manual_Automation Testing");
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s,null);
		//CNTRL+V
	    r.delay(2000);
	    	    
	    r.keyPress(KeyEvent.VK_CONTROL);
	    r.keyPress(KeyEvent.VK_V);	    
	   
	    r.keyRelease(KeyEvent.VK_CONTROL);//release cntl
	    r.keyRelease(KeyEvent.VK_V);//release 
	    r.delay(2000);
	  //Enter
	    r.keyPress(KeyEvent.VK_ENTER);
	    r.keyRelease(KeyEvent.VK_ENTER);
	    
	    WebElement isFileUploaded=driver.findElement(By.xpath("//span[@class='file__info__name']"));	    
	    Assert.assertTrue(isFileUploaded.isDisplayed());
	      
	}
	@AfterMethod
	public void closeBrowser() 
	{
		driver.quit();
	}

}
