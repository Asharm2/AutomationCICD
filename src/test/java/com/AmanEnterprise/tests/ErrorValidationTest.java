package com.AmanEnterprise.tests;

import com.AmanEnterprise.TestComponents.BaseTest;
import com.AmanEnterprise.TestComponents.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;


public class ErrorValidationTest extends BaseTest {

    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void LoginErrorValidation() {
        //Login Page
        landingPage.loginApplication("testsel007@gmail.com", "Tes@123");
        String errorMessage = landingPage.getErrorMessage();
        Assert.assertEquals(errorMessage,"Incorrect email or password.");
    }

    @Test
    public void productErrorValidation() throws InterruptedException {

        String productName = "ZARA COAT 3";

        //Login Page
        ProductCatalogue productCatalogue = landingPage.loginApplication("testsel007@gmail.com", "Test@123");

        //Product catalog
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();


        //CART PAGE
        Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
        Assert.assertFalse(match);

    }
}
