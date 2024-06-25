package tamishrasenapati.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tamishrasenapati.AbstractComponent.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	WebDriver driver;
	public ProductCatalogue (WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> productsList= driver.findElements(By.cssSelector(".col-lg-4"));
	//PageFactory
	@FindBy(css=".col-lg-4")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	
	By productsBy= By.cssSelector(".mb-3");
	By addToCart= By.cssSelector("button[class='btn w-10 rounded']");
	By toastMessage= By.id("toast-container");
	
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod= getProductList().stream().filter(prodList->prodList.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
		
	}
	
	public void addProductToCart(String productName) {
		WebElement prod= getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}
	
}
