package tamishrasenapati.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		String myProd="ADIDAS ORIGINAL";
		
		//logIn
		driver.findElement(By.id("userEmail")).sendKeys("senapatitamishra1@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Aa@123456");
		driver.findElement(By.id("login")).click();
		
		//adding an item to cart
		List<WebElement> productsList= driver.findElements(By.cssSelector(".col-lg-4"));
		for(WebElement prodList:productsList) {
			if(prodList.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(myProd)) {
				prodList.findElement(By.cssSelector("button[class='btn w-10 rounded']")).click();
			}
		}
		//using stream
		/*WebElement prod= productsList.stream().filter(prodList->prodList.findElement(By.cssSelector("b")).getText().equals("ADIDAS ORIGINAL")).findFirst().orElse(null);
		prod.findElement(By.cssSelector("button[class='btn w-10 rounded']")).click();*/
		
		//waiting to toast message disappear and animating image disappear
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("toast-container"))));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//go to cart and get list of all products
		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cart h3"));
		boolean match=cartProducts.stream().anyMatch(cartProd->cartProd.getText().equalsIgnoreCase(myProd));
		Assert.assertTrue(match);
		
		//proceed for checkout
		driver.findElement(By.cssSelector("li.totalRow button")).click();
		
		//enter country name in country auto-suggestive field
		driver.findElement(By.cssSelector("input[placeholder='Select Country'] ")).sendKeys("India");
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		List<WebElement> suggestions= driver.findElements(By.cssSelector("button[class='ta-item list-group-item ng-star-inserted']"));
		
		for(WebElement sugg:suggestions) {
			if(sugg.getText().equalsIgnoreCase("India")) {
				System.out.println(sugg.getText());
				sugg.click();
				
			}
		}
		//place order and get thank u message text                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		String message=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		
	}

}
