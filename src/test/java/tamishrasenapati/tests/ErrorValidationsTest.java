package tamishrasenapati.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import tamishrasenapati.TestComponents.BaseTest;
import tamishrasenapati.TestComponents.Retry;
import tamishrasenapati.pageobjects.CartPage;
import tamishrasenapati.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest{

	@Test(groups = "ErrorHandling",retryAnalyzer =Retry.class)
	public void loginErrorValidation() throws IOException {
		
		landingPage.loginApplication("senapatitamishra1@gmail.com", "A@123456");
		//.ng-tns-c4-9.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
		Assert.assertEquals("Incorrect email  password.",landingPage.getErrorMessage());
		
	}
	
	@Test
	public void orderErrorValidation() throws IOException {
		
		String productName="ADIDAS ORIGINAL";
		String counrtyName="India";		
		ProductCatalogue productCatalogue=landingPage.loginApplication("tamishrasenapati8@gmail.com", "Aa@123456");
		List<WebElement> products= productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage=productCatalogue.goToCart();
		boolean match=cartPage.verifyProductDisplay("ADIDAS ORIGINALs");
		Assert.assertFalse(match);
	}

}
