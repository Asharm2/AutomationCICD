package AmanEnterprise.tests;

import AmanEnterprise.TestComponent.BaseTest;
import AmanEnterprise.pageObjects.*;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.io.IOException;

import java.util.HashMap;
import java.util.List;

public class StandAloneTest2 extends BaseTest {

    //comment added just for webhook trigger check
    String proName = "qwerty";

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String, String> input) throws IOException {

        ProductCatalog productCatalog = landingPage.loginApplication(input.get("userEmail"), input.get("userPassword"));

        List<WebElement> products = productCatalog.getProductList();

        productCatalog.getProductByName(input.get("productName"));
        productCatalog.addProductToCart(input.get("productName"));

        CartPage cartPage = productCatalog.goToCartPage();

        Boolean match = cartPage.VerifyProductDisplay(input.get("productName"));
        Assert.assertTrue(match);

        CheckoutPage checkoutPage = cartPage.goToCheckout();

        checkoutPage.selectCountry();
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String confirmMessage = confirmationPage.getConfirmationMessage();

        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistoryTest() {
        ProductCatalog productCatalog = landingPage.loginApplication("testaman@gmail.com", "Test@123");
        OrderPage orderPage = productCatalog.goToOrderPage();
        Assert.assertTrue(orderPage.verifyOrderDisplay(proName));
    }


    @DataProvider
    public Object[][] getData() throws IOException {
//        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("userEmail","testaman@gmail.com");
//        map.put("userPassword", "Test@123");
//        map.put("productName", "adidas original");
//
//        HashMap<String, String> map1 = new HashMap<String, String>();
//        map1.put("userEmail","amantest@gmail.com");
//        map1.put("userPassword", "IamKing@123");
//        map1.put("productName", "qwerty");
        List<HashMap<String, String>> data = getJSONDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\AmanEnterprise\\data\\PurchaseOrder.json");
        return new Object[][]{{data.get(0)}, {data.get(1)}};

    }

}

//        assert prod != null;
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", prod);
//        Thread.sleep(2000L);

//        wait.until(ExpectedConditions.visibilityOf(prod));
//        wait.until(ExpectedConditions.elementToBeClickable(prod));