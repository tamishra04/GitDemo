package tamishrasenapati.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tamishrasenapati.AbstractComponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//driver.findElement(By.cssSelector("input[placeholder='Select Country'] ")).sendKeys("India");
	//driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
	//driver.findElement(By.cssSelector(".action__submit")).click();
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	
	@FindBy(css="input[placeholder='Select Country'] ")
	WebElement country;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	By results=By.cssSelector(".ta-results");
	
	public void selectCounrty(String counrtyName) {
		country.sendKeys(counrtyName);
		horizontalScroll();
		waitForElementToAppear(results);
		selectCountry.click();
		
	}
	
	public ConfirmationPage submitOrder() {
		submit.click();
		return new ConfirmationPage(driver);
	}

}
