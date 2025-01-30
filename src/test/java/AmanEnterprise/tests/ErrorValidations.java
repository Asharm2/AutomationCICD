package AmanEnterprise.tests;

import AmanEnterprise.TestComponent.BaseTest;
import AmanEnterprise.TestComponent.Retry;
import AmanEnterprise.pageObjects.CartPage;
import AmanEnterprise.pageObjects.ProductCatalog;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidations extends BaseTest {

    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void loginErrorValidation() throws IOException {
        landingPage.loginApplication("testaaman@gmail.com", "Test@123");
        String errorMessage = landingPage.getErrorMessage();
        Assert.assertEquals(errorMessage,"Incorrect email or password." );

    }
    @Test
    public void productErrorValidation() throws IOException {

        String proName = "banarsi saree";
        ProductCatalog productCatalog = landingPage.loginApplication("testaman@gmail.com", "Test@123");
        List<WebElement> products = productCatalog.getProductList();
        productCatalog.getProductByName(proName);
        productCatalog.addProductToCart(proName);
        CartPage cartPage = productCatalog.goToCartPage();
        Boolean match = cartPage.VerifyProductDisplay("help");
        Assert.assertFalse(match);

    }

}

//        assert prod != null;
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", prod);
//        Thread.sleep(2000L);

//        wait.until(ExpectedConditions.visibilityOf(prod));
//        wait.until(ExpectedConditions.elementToBeClickable(prod));