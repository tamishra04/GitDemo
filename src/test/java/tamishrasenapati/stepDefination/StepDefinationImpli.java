package tamishrasenapati.stepDefination;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.IOException;
import tamishrasenapati.TestComponents.BaseTest;
import tamishrasenapati.pageobjects.CartPage;
import tamishrasenapati.pageobjects.CheckoutPage;
import tamishrasenapati.pageobjects.ConfirmationPage;
import tamishrasenapati.pageobjects.LandingPage;
import tamishrasenapati.pageobjects.ProductCatalogue;

public class StepDefinationImpli extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public CheckoutPage checkoutPage;
	public ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		
		//code
		landingPage= launchApplication();
	}
	
	@Given ("^I logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) {
		
		productCatalogue=landingPage.loginApplication(username, password);
	}
	
	@When ("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productName) {
		
		List<WebElement> products= productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		
	}
	@When ("^ Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) {
		
		cartPage=productCatalogue.goToCart();
		boolean match=cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		
		checkoutPage=cartPage.goToCheckout();
		checkoutPage.selectCounrty("India");
		confirmationPage=checkoutPage.submitOrder();  
	}
	 //Then "THANKYOU FOR THE ORDER." message is displayed on confimationPage
	 @Then("{string} message is displayed on confimationPage")
	 public void message_displayed_confirmation_page(String string) {
		 String confirmMessage= confirmationPage.getConfirmatioMessage();
		 Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	 }

	 @Then("^\"([^\"]*)\" message is displayed$")
	 public void something_message_is_displayed(String strArg1) {
		 Assert.assertEquals(strArg1,landingPage.getErrorMessage());
		 driver.close();
	 }

}
