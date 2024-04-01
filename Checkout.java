package Maven.General;

import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Checkout {

	WebDriver driver;

	@DataProvider(name = "Login")
	public Object[][] provideData() {
		Object[][] o1 = new Object[1][2];
		o1[0][0] = "dummy@gmail.com";
		o1[0][1] = "dummmy123";
		return o1;
	}

	@BeforeMethod
	public void launchURL() {
		driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Test(dataProvider = "Login")
	public void amazon_checkout(String uname, String pwd) throws EncryptedDocumentException, IOException {
		WebElement signin = driver.findElement(By.xpath("//span[.='Hello, sign in']"));
		String signin_text = signin.getText();
		signin.click();
		WebElement email = driver.findElement(By.id("ap_email"));
		email.sendKeys(uname);
		WebElement continue1 = driver.findElement(By.id("continue"));
		continue1.click();
		WebElement password = driver.findElement(By.id("ap_password"));
		password.sendKeys(pwd);
		WebElement signinclick = driver.findElement(By.id("signInSubmit"));
		signinclick.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
		search.sendKeys("gold ring");
		search.sendKeys(Keys.ENTER);
		WebElement firstitem = driver.findElement(By.xpath("(//div[@class='a-section aok-relative s-image-tall-aspect'])[1]"));
		firstitem.click();
		Set<String> winhandle = driver.getWindowHandles();
		Iterator<String> ids = winhandle.iterator();
		String parentid = ids.next();
		String childid = ids.next();
		driver.switchTo().window(childid);
		WebElement buynow = driver.findElement(By.id("buy-now-button"));
		buynow.click();
		WebElement selectpaymentmethod = driver.findElement(By.id("orderSummaryPrimaryActionBtn"));
		selectpaymentmethod.click();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bottomSubmitOrderButtonId")));
		
		WebElement placeorder = driver.findElement(By.id("bottomSubmitOrderButtonId"));
		Assert.assertTrue(placeorder.isDisplayed());
	}

	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}

}
