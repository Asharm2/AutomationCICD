package com.AmanEnterprise.tests;
import com.AmanEnterprise.TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rahulshettyacademy.pageobjects.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String, String> input) throws InterruptedException {
        String countryName = "India";

        //Login Page
        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

        //Product catalog
        productCatalogue.addProductToCart(input.get("product"));
        CartPage cartPage = productCatalogue.goToCartPage();

        //CART PAGE
        Boolean match = cartPage.verifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckOut();

        //Checkout Page
        checkoutPage.selectCountry(countryName);
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();

        //Confirmation Page
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
    }

    //verify if ZARA COAT 3 is displaying in orders page
    @Test(dependsOnMethods = {"submitOrder"})
    public void OrderHistoryTest(){
        ProductCatalogue productCatalogue = landingPage.loginApplication("testsel007@gmail.com", "Test@123");
        OrderPage orderPage = productCatalogue.goToOrdersPage();
        boolean orderPresent = orderPage.verifyOrderDisplay(productName);
        Assert.assertTrue(orderPresent);

    }

    @DataProvider
    public Object[][] getData() throws IOException {
        //THIRD WAY -> Using Json File
        String filePath = "//src//test//java//com//AmanEnterprise//Data//PurchaseOrder.json";
        List<HashMap<String, String>> data = getJsonDataToMap(filePath);
        return new Object[][] {{data.get(0)}, {data.get(1)}} ;

    }


}

        /* FIRST WAY OF SENDING DATA

        return new Object[][] {{"testsel007@gmail.com","Test@123", "ZARA COAT 3"}, {"djdj@gmail.com", "Djdj@123", "ADIDAS ORIGINAL"}};

        //SECOND WAY

        HashMap<String, String> map = new HashMap<>();
        map.put("email", "testsel007@gmail.com");
        map.put("password", "Test@123");
        map.put("product", "ZARA COAT 3");

        HashMap<String, String> map1 = new HashMap<>();
        map1.put("email", "djdj@gmail.com");
        map1.put("password", "Djdj@123");
        map1.put("product", "ADIDAS ORIGINAL");

        return new Object[][] {{map}, {map1}};

         */
