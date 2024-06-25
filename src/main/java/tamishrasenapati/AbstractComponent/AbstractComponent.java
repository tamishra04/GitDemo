package tamishrasenapati.AbstractComponent;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import tamishrasenapati.pageobjects.CartPage;
import tamishrasenapati.pageobjects.OrderPage;

public class AbstractComponent {
	protected WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink='/dashboard/myorders']")
	WebElement orderHeader;

	public void waitForElementToAppear(By findBy) {
		
	WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForElementToAppear(WebElement findBy) {
		
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
		}
	
	public CartPage goToCart() {
		cartHeader.click();
		CartPage cartPage= new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrdersPage() {
		orderHeader.click();
		OrderPage orderPage= new OrderPage(driver);
		return orderPage;
	}
	
	public void waitForElementToDisappear(WebElement ele) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public void horizontalScroll() {
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
	}
}
