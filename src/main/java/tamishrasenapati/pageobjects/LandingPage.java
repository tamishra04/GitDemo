package tamishrasenapati.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tamishrasenapati.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent{
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		
		super (driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmail= driver.findElement(By.id("userEmail"));
	//WebElement password= driver.findElement(By.id("userPassword"));
	//WebElement submit= driver.findElement(By.id("login"))
	//.ng-tns-c4-9.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	//PageFactory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessag;
	
	public ProductCatalogue loginApplication(String email,String password) {
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue= new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String getErrorMessage() {
		waitForElementToAppear(errorMessag);
		return errorMessag.getText();
		
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	
	

}
