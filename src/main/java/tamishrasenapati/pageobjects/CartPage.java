package tamishrasenapati.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tamishrasenapati.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	/*List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cart h3"));
	boolean match=cartProducts.stream().anyMatch(cartProd->cartProd.getText().equalsIgnoreCase(productName));
	
	driver.findElement(By.cssSelector("li.totalRow button")).click();*/
	
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".cart h3")
	List<WebElement> productTitles;
	
	@FindBy(css="li.totalRow button")
	WebElement checkoutEle;

	
	public boolean verifyProductDisplay(String productName) {
		
		boolean match= productTitles.stream().anyMatch(cartProd->cartProd.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	
	public CheckoutPage goToCheckout() {
		
		checkoutEle.click();
		return new CheckoutPage(driver);
	}

}
