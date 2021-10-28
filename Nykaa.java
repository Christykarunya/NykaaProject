package week4.projects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions builder= new Actions(driver);
		
		WebElement brand = driver.findElement(By.xpath("//a[text()='brands']"));
		builder.moveToElement(brand).perform();
		
		driver.findElement(By.id("brandSearchBox")).sendKeys("L'Oreal Paris");
		driver.findElement(By.linkText("L'Oreal Paris")).click(); 
		String title = driver.getTitle();
		System.out.println(title);
		
		driver.findElement(By.xpath("//span[@class='sort-name']")).click();
		driver.findElement(By.xpath("(//div[@class='control-indicator radio '])[3]")).click();
		
		driver.findElement(By.xpath("//div[@id='first-filter']/div")).click();
		driver.findElement(By.xpath("(//ul[@id='custom-scroll']//div)[1]")).click();
		driver.findElement(By.xpath("//span[text()='Hair Care']/parent::div")).click();
		driver.findElement(By.xpath("(//label[@class='control control-checkbox'])[1]")).click();
		
		driver.findElement(By.xpath("//span[text()='Concern']/parent::div")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']/ancestor::label")).click();
		WebElement filterShampoo = driver.findElement(By.xpath("//span[@class='filter-value' and text()='Shampoo']"));
		String filtered = filterShampoo.getText();
		if(filtered.contains("Shampoo")) {
			System.out.println("Applied filter for Shampoo");
		}
		else {
			System.out.println("Filter not applied for Shampoo");
		}
		
		driver.findElement(By.xpath("(//div[contains(text(),'Paris Colour Protect Shampoo')])[1]")).click();
		
		Set<String> winSet = driver.getWindowHandles();
		List<String> winList=new ArrayList<String>(winSet);
		driver.switchTo().window(winList.get(1));
		
		WebElement size = driver.findElement(By.xpath("//select[@title='SIZE']"));
		Select dd=new Select(size);
		dd.selectByVisibleText("175ml");
		
		String mrp = driver.findElement(By.xpath("(//span[text()='MRP:'])[1]/following-sibling::span")).getText();
		System.out.println("MRP :" +mrp);
		
		driver.findElement(By.xpath("(//span[text()='ADD TO BAG'])[1]")).click();
		driver.findElement(By.xpath("//span[@class='cart-count']/parent::button")).click();
		
		WebElement frame = driver.findElement(By.xpath("//iframe[@class='css-acpm4k']"));
		driver.switchTo().frame(frame);
		String grandTotal = driver.findElement(By.xpath("//div[@class='first-col']/div")).getText();
		System.out.println("Grand Total of the Product :" +grandTotal);
		
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();
		//driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//button[@class='btn full big']")).click();
		
		String finalAmt = driver.findElement(By.xpath("//div[text()='Grand Total']/following-sibling::div")).getText();
		System.out.println("Final Amount :" +finalAmt);
		if(grandTotal.equals(finalAmt)) {
			System.out.println("Both are equal");
		}
		else {
			System.out.println("Both are different");
		}
		
		driver.quit();
	}
	
}
