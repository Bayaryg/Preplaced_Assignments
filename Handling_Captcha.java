package Maven.General;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Handling_Captcha {
	
	public static void main(String[] args) throws IOException, TesseractException, InterruptedException {
		
		
		ChromeOptions opt=new ChromeOptions();
		opt.addArguments("--disable-notifications");		
		WebDriver driver=new ChromeDriver(opt);
		driver.get("https://www.irctc.co.in/nget/train-search");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath(" //a[contains(text(),'LOGIN')]")).click();
		//Thread.sleep(5000);
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='captcha-img']")));
		File source=driver.findElement(By.xpath("//img[@class='captcha-img']")).getScreenshotAs(OutputType.FILE);		
		String path="C:\\Users\\udupa\\Tess4j\\Tess4J-3.4.8-src\\Tess4J\\captcha.png";
		FileHandler.copy(source, new File(path));
		Tesseract image=new Tesseract();
		
		image.setDatapath("C:\\Users\\udupa\\Tess4j\\Tess4J-3.4.8-src\\Tess4J\\tessdata");
		String imagetext =image.doOCR(new File(path));
		
		WebElement entercaptcha=driver.findElement(By.id("captcha"));
		entercaptcha.sendKeys(imagetext);
		
	}
	
	

}
