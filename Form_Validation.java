package Maven.General;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Form_Validation {
	
	
	WebDriver driver;
	@BeforeMethod
	public void launchURL()
	{
		driver=new ChromeDriver();
		driver.get("https://www.foundit.in/seeker/registration?spl=IN-Acq-SEM-Google-IP_IN_SER_Monster_Brand_AllMatch-GSN-Monster_Resume-foundit%20careers-Both-Brand---637202034919---CPC-6645446156&utm_campaign=IN_Acq_SEM-Google-IP_IN_SER_Monster_Brand_AllMatch-GSN-Monster_Resume-foundit%20careers-&utm_medium=SEM&utm_source=Google-GSN-CPC&utm_term=SEM_foundit%20careers&gad_source=1&gclid=EAIaIQobChMIpZ_Ng6eehQMVn45LBR1bIwlREAAYASAAEgIvpfD_BwE");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@Test
	public void verify_formvalidation()
	{
		SoftAssert softAssert=new SoftAssert();
        WebElement mobilenum=driver.findElement(By.id("mobileNumber"));
        mobilenum.sendKeys("8763412");
        WebElement emailid=driver.findElement(By.id("emailId"));
        emailid.sendKeys("archanabggmail.com");
        WebElement password=driver.findElement(By.id("password"));
        password.sendKeys("abg11");
        WebElement continuebutton=driver.findElement(By.xpath("//button[contains(text(),'Continue')]"));
        continuebutton.click();
        
        WebElement invalid_mobnum=driver.findElement(By.xpath("//span[text()='Enter a valid mobile number']"));
        softAssert.assertTrue(invalid_mobnum.isDisplayed());
        WebElement invalid_email= driver.findElement(By.xpath("//span[text()='Enter a valid email']"));
        softAssert.assertTrue(invalid_email.isDisplayed());
        WebElement invalid_pwd=driver.findElement(By.xpath("//span[contains(text(),'It should have a minimum of 6 characters')]"));
        softAssert.assertTrue(invalid_pwd.isDisplayed());
        softAssert.assertAll();
        
	}
	
	@AfterMethod
	public void closeBrowser() 
	{
		driver.quit();
	}


}
